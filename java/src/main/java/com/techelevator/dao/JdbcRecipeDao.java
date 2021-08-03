package com.techelevator.dao;

import com.techelevator.model.Recipe;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.security.Principal;

@Component
public class JdbcRecipeDao implements RecipeDao{

    private final JdbcTemplate jdbcTemplate;

    public JdbcRecipeDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void createRecipe(Recipe newRecipe, Principal principal) {
    String sql = "INSERT INTO recipes(recipe_name,recipe_instructions)"+
                 "VALUES(?,?)";
        jdbcTemplate.update(sql, newRecipe.getRecipeName(), newRecipe.getInstructions());
    }
    private Recipe mapRowToRecipe(SqlRowSet rs){
        Recipe recipe = new Recipe();
        recipe.setRecipeId(rs.getInt("recipe_id"));
        recipe.setRecipeName(rs.getString("recipe_name"));
        recipe.setInstructions(rs.getString("recipe_instructions"));
        return recipe;
    }
}
