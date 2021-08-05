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
    String sql = "INSERT INTO recipes(recipe_name,recipe_instructions,recipe_description) "+
                 "VALUES(?,?,?) " +
            "RETURNING recipe_id;";
    //model what obj on front end is going to look like

    //drop down on front end to pick from ingredients
    //select as many as they want, attach them to recipe object sent from front end (array of ingredients)
    //when you get recipe object on back end, insert recipe, insert into user recipes, take ids of ingredient objects
    //insert recipe ID and ingredient ID together into linker table
        int recipeId=jdbcTemplate.queryForObject(sql, int.class, newRecipe.getRecipeName(), newRecipe.getInstructions(),newRecipe.getDescription());
        sql="INSERT INTO users_recipes(user_id, recipe_id) " +
                "VALUES(?,?);";
        String currentUserName= principal.getName();
        int currentUserId=userDao.findIdByUsername(currentUserName);
        jdbcTemplate.update(sql,currentUserId,recipeId);
        
        for (int i=0; i<newRecipe.getIngredients().size();i++){
            String sql2="INSERT INTO recipes_ingredients(ingredient_id,recipe_id) " +
                    "VALUES ";
            Ingredient ingredient = newRecipe.getIngredients().get(i);
            String ingredientName= ingredient.getIngredientName();
            String newStr="("+getIngredientIdFromIngredientName(ingredientName, currentUserId)+","+recipeId+")";
            sql2=sql2+newStr;
            jdbcTemplate.update(sql2);
        }
    }

    @Override
    public List<Recipe> getMyRecipes(Principal principal) {
        List<Recipe>recipes=new ArrayList<>();
        String currentUserName= principal.getName();
        int currentUserId=userDao.findIdByUsername(currentUserName);
        String sql= "SELECT recipe_name, recipe_instructions, recipes.recipe_id AS recipe_id,recipe_description" +
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

    @Override
    public Recipe getRecipeById(int recipeId, Principal principal) {
        Recipe recipe = new Recipe();
        String sql = "SELECT recipe_id, recipe_name, recipe_instructions, recipe_description " +
                "FROM recipes " +
                "WHERE recipe_id = ?;";
        SqlRowSet rs = jdbcTemplate.queryForRowSet(sql, recipeId);
        if(rs.next()){
            recipe = mapRowToRecipe(rs);
            recipe.setIngredients(getRecipeIngredients(recipeId, principal));
        }

        return recipe;
    }

    @Override
    public void editRecipe(int recipeId, Recipe recipe, Principal principal) {
        String sql=
                    "UPDATE recipes " +
                    "SET recipe_name=?, recipe_instructions=?, recipe_description=? " +
                    "WHERE recipe_id = ?; ";
        jdbcTemplate.update(sql,recipe.getRecipeName(),recipe.getInstructions(),recipe.getDescription(),recipeId);

        //from FE send recipe object modeled off of input fields
        //must pass in whole recipe
        //modify recipe form component, send that with put to the back end
        //update statement, update whatever table sent in form
        //reset all columns from values of object sent
        //make sure to do WHERE for recipe ID
        //ingredients----do a delete, reinsert for the ingredients based on the form
    }

    @Override
    public List<Ingredient> getRecipeIngredients(int recipeId, Principal principal) {
        List<Ingredient> ingredients = new ArrayList<>();
        String sql = "SELECT ingredient_name, user_id, ingredients.ingredient_id " +
                "FROM ingredients " +
                "JOIN recipes_ingredients ON recipes_ingredients.ingredient_id = ingredients.ingredient_id "+
                "WHERE recipe_id = ?;";
        SqlRowSet rs = jdbcTemplate.queryForRowSet(sql, recipeId);
        while(rs.next()){
            Ingredient ingredient = mapIngredientsToRecipe(rs);
            ingredients.add(ingredient);
        }
        return ingredients;
    }


    private int getIngredientIdFromIngredientName(String name, int userId){
        int ingredientId;
        String sql="SELECT ingredient_id " +
                "FROM ingredients " +
                "WHERE ingredient_name = ? AND user_id = ?;";
        SqlRowSet rs = jdbcTemplate.queryForRowSet(sql,name, userId);
        if(rs.next()){
            ingredientId = rs.getInt("ingredient_id");
        }else{
            String sql2 = "INSERT INTO ingredients(ingredient_name, user_id) " +
                    "VALUES (?,?) " +
                    "RETURNING ingredient_id;";
            ingredientId = jdbcTemplate.queryForObject(sql2,int.class,name, userId);
        }

        return ingredientId;
    }
    private Recipe mapRowToRecipe(SqlRowSet rs){
        Recipe recipe = new Recipe();
        recipe.setRecipeId(rs.getInt("recipe_id"));
        recipe.setRecipeName(rs.getString("recipe_name"));
        recipe.setInstructions(rs.getString("recipe_instructions"));
        recipe.setDescription(rs.getString("recipe_description"));
        //recipe.setIngredients(rs.getObject("recipe_ingredients"));
        return recipe;
    }

//don't know about this

    private Ingredient mapIngredientsToRecipe(SqlRowSet rs){
        Ingredient ingredient = new Ingredient();
        ingredient.setUserId(rs.getInt("user_id"));
        ingredient.setIngredientId(rs.getInt("ingredient_id"));
        ingredient.setIngredientName(rs.getString("ingredient_name"));
        return ingredient;
    }
    //seperate map method to add to ingredient list in recipe object
}
