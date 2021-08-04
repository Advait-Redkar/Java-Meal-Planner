package com.techelevator.dao;

import com.techelevator.model.Ingredient;
import com.techelevator.model.Recipe;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.prefs.PreferenceChangeEvent;

@Component
public class JdbcRecipeDao implements RecipeDao{

    private final JdbcTemplate jdbcTemplate;
    private UserDao userDao;

    public JdbcRecipeDao(JdbcTemplate jdbcTemplate, UserDao userDao) {
        this.jdbcTemplate = jdbcTemplate;
        this.userDao = userDao;
    }

    @Override
    public void createRecipe(Recipe newRecipe, Principal principal) {
    String sql = "INSERT INTO recipes(recipe_name,recipe_instructions,recipe_ingredients,recipe_description) "+
                 "VALUES(?,?,?,?) " +
            "RETURNING recipe_id;";
    //model what obj on front end is going to look like

    //drop down on front end to pick from ingredients
    //select as many as they want, attach them to recipe object sent from front end (array of ingredients)
    //when you get recipe object on back end, insert recipe, insert into user recipes, take ids of ingredient objects
    //insert recipe ID and ingredient ID together into linker table
        int recipeId=jdbcTemplate.queryForObject(sql, int.class, newRecipe.getRecipeName(), newRecipe.getInstructions(), newRecipe.getIngredients(), newRecipe.getDescription());
        sql="INSERT INTO users_recipes(user_id, recipe_id) " +
                "VALUES(?,?);";
        String currentUserName= principal.getName();
        int currentUserId=userDao.findIdByUsername(currentUserName);
        jdbcTemplate.update(sql,currentUserId,recipeId);
        
        for (int i=0; i<newRecipe.getIngredients().size();i++){
            String sql2="INSERT INTO recipes_ingredients(ingredient_id,recipe_id) " +
                    "VALUES ";
            Ingredient ingredient = newRecipe.getIngredients().get(i);
            String newStr="("+ingredient.getIngredientId()+","+recipeId+")";
            sql2=sql2+newStr;
            jdbcTemplate.update(sql2);
        }
    }

    @Override
    public List<Recipe> getMyRecipes(Principal principal) {
        List<Recipe>recipes=new ArrayList<>();
        String currentUserName= principal.getName();
        int currentUserId=userDao.findIdByUsername(currentUserName);
        String sql= "SELECT recipe_name, recipe_instructions, recipes.recipe_id AS recipe_id" +
                " FROM recipes " +
                "INNER JOIN users_recipes ON users_recipes.recipe_id = recipes.recipe_id " +
                "INNER JOIN users ON users.user_id = users_recipes.user_id " +
                " WHERE users.user_id=?;";
        SqlRowSet results= jdbcTemplate.queryForRowSet(sql,currentUserId);
        while(results.next()){
            Recipe recipe=mapRowToRecipe(results);
            recipes.add(recipe);
        }
        return recipes;

        //for each statement on each of recipes take ID a
    }

    private Recipe mapRowToRecipe(SqlRowSet rs){
        Recipe recipe = new Recipe();
        recipe.setRecipeId(rs.getInt("recipe_id"));
        recipe.setRecipeName(rs.getString("recipe_name"));
        recipe.setInstructions(rs.getString("recipe_instructions"));
        //recipe.setIngredients(rs.getObject("recipe_ingredients"));
        return recipe;
    }

    //seperate map method to add to ingredient list in recipe object
}
