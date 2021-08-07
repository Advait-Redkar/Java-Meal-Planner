package com.techelevator.dao;

import com.techelevator.model.Meal;
import com.techelevator.model.Mealplan;
import com.techelevator.model.Recipe;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcMealDao implements MealDao {
    private final JdbcTemplate jdbcTemplate;
    private UserDao userDao;

    public JdbcMealDao(JdbcTemplate jdbcTemplate, UserDao userDao) {
        this.jdbcTemplate = jdbcTemplate;
        this.userDao = userDao;
    }


    @Override
    public void createMeal(Meal meal, Principal principal) {
        String sql = "INSERT INTO meals(user_id, meal_name, meal_type) " +
                "VALUES(?, ?, ?) " +
                "RETURNING meal_id;";
        String currentUserName = principal.getName();
        int currentUserId = userDao.findIdByUsername(currentUserName);
        int mealId = jdbcTemplate.queryForObject(sql, int.class, currentUserId, meal.getMealName(), meal.getMealType());
        for (Recipe recipe : meal.getRecipeList()) {
            String name = recipe.getRecipeName();
            sql = "INSERT INTO meals_recipes(recipe_id, meal_id) " +
                    "VALUES(?,?);";
            jdbcTemplate.update(sql, getRecipeIdFromRecipeName(name, currentUserId), mealId);
        }


    }

    @Override
    public List<Meal> getAllMeals(Principal principal) {
        List<Meal>mealList=new ArrayList<>();
        String currentUserName= principal.getName();
        int currentUserId=userDao.findIdByUsername(currentUserName);
        String sql="SELECT user_id, meal_id, meal_name, meal_type " +
                "FROM meals " +
                "WHERE user_id=?;";
        SqlRowSet results= jdbcTemplate.queryForRowSet(sql,currentUserId);
        while(results.next()){
            Meal meal=mapRowToMeal(results);
            mealList.add(meal);
        }
        return mealList;
    }

    private int getRecipeIdFromRecipeName(String name, int userId) {
        int recipeId;
        String sql = "SELECT recipes.recipe_id " +
                "FROM recipes " +
                "JOIN users_recipes ur ON ur.recipe_id = recipes.recipe_id " +
                "WHERE recipe_name = ? AND user_id = ?;";
        SqlRowSet rs = jdbcTemplate.queryForRowSet(sql, name, userId);
        if (rs.next()) {
            recipeId = rs.getInt("recipe_id");
        } else {
            String sql2 = "INSERT INTO recipes(recipe_name) " +
                    "VALUES (?) " +
                    "RETURNING recipe_id;";
            recipeId = jdbcTemplate.queryForObject(sql2, int.class, name);
            sql2 = "INSERT INTO users_recipes(user_id, recipe_id) " +
                    "VALUES(?,?);";
            jdbcTemplate.update(sql2, userId, recipeId);
        }

        return recipeId;

    }

    private Meal mapRowToMeal(SqlRowSet rs){
        Meal meal = new Meal();
        meal.setMealId(rs.getInt("meal_id"));
        meal.setMealName(rs.getString("meal_name"));
        meal.setMealType(rs.getInt("meal_type"));
        meal.setUserId(rs.getInt("user_id"));
        //recipe.setIngredients(rs.getObject("recipe_ingredients"));
        return meal;
    }
}
