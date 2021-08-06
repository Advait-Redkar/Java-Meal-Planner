package com.techelevator.dao;

import com.techelevator.model.Mealplan;
import com.techelevator.model.Recipe;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.security.Principal;
@Component
public class JdbcMealplanDao implements MealplanDao{
    private final JdbcTemplate jdbcTemplate;
    private UserDao userDao;

    public JdbcMealplanDao(JdbcTemplate jdbcTemplate, UserDao userDao) {
        this.jdbcTemplate = jdbcTemplate;
        this.userDao = userDao;

    }
    @Override
    public void createMealplan(Mealplan mealplan, Principal principal) {
        String sql = "INSERT INTO mealplan(mealplan_name, mealplan_time, mealplan_day, user_id) " +
                     "VALUES(?,?,?,?) "+
                     "RETURNING mealplan_id;";
        String currentUserName= principal.getName();
        int currentUserId=userDao.findIdByUsername(currentUserName);
        int mealplanId = jdbcTemplate.queryForObject(sql, int.class, mealplan.getMealplanName(), mealplan.getMealplanTime(), mealplan.getMealplanDay(), currentUserId);
        for(Recipe recipe: mealplan.getRecipeList()){
            String name  = recipe.getRecipeName();
            sql = "INSERT INTO mealplan_recipes(recipe_id, mealplan_id) " +
                    "VALUES(?,?);";
            jdbcTemplate.update(sql, getRecipeIdFromRecipeName(name,currentUserId),mealplanId);
        }

    }
    private int getRecipeIdFromRecipeName(String name, int userId){
        int recipeId;
        String sql="SELECT recipes.recipe_id " +
                "FROM recipes " +
                "JOIN users_recipes ur ON ur.recipe_id = recipes.recipe_id "+
                "WHERE recipe_name = ? AND user_id = ?;";
        SqlRowSet rs = jdbcTemplate.queryForRowSet(sql,name, userId);
        if(rs.next()){
            recipeId = rs.getInt("recipe_id");
        }else{
            String sql2 = "INSERT INTO recipes(recipe_name) " +
                    "VALUES (?) " +
                    "RETURNING recipe_id;";
            recipeId = jdbcTemplate.queryForObject(sql2,int.class,name);
            sql2 = "INSERT INTO users_recipes(user_id, recipe_id) "+
                    "VALUES(?,?);";
            jdbcTemplate.update(sql2, userId,recipeId);
        }

        return recipeId;

    }
}
