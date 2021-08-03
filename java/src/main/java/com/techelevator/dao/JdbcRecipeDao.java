package com.techelevator.dao;

import com.techelevator.model.Recipe;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

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
    String sql = "INSERT INTO recipes(recipe_name,recipe_instructions) "+
                 "VALUES(?,?) " +
            "RETURNING recipe_id;";

        int recipeId=jdbcTemplate.queryForObject(sql, int.class, newRecipe.getRecipeName(), newRecipe.getInstructions());
        sql="INSERT INTO users_recipes(user_id, recipe_id) " +
                "VALUES(?,?);";
        String currentUserName= principal.getName();
        int currentUserId=userDao.findIdByUsername(currentUserName);
        jdbcTemplate.update(sql,currentUserId,recipeId);
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
    }

    private Recipe mapRowToRecipe(SqlRowSet rs){
        Recipe recipe = new Recipe();
        recipe.setRecipeId(rs.getInt("recipe_id"));
        recipe.setRecipeName(rs.getString("recipe_name"));
        recipe.setInstructions(rs.getString("recipe_instructions"));
        return recipe;
    }
}
