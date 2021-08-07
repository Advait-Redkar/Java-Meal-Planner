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
        for(Meal meal: mealplan.getMealList()){
            String name  = meal.getMealName();
            sql = "INSERT INTO mealplan_meal(mealplan_id, meal_id) " +
                    "VALUES(?,?);";
            jdbcTemplate.update(sql, getMealIdFromMealName(name,currentUserId),mealplanId);
        }

    }

    @Override
    public List<Mealplan> getAllMealPlans(Principal principal) {
        List<Mealplan>mealplans=new ArrayList<>();
        String currentUserName= principal.getName();
        int currentUserId=userDao.findIdByUsername(currentUserName);
        String sql="SELECT user_id, mealplan_id, mealplan_name, mealplan_time, mealplan_day " +
                "FROM mealplan " +
                "WHERE user_id=?;";
        SqlRowSet results= jdbcTemplate.queryForRowSet(sql,currentUserId);
        while(results.next()){
            Mealplan mealplan=mapRowToMealPlan(results);
            mealplans.add(mealplan);
        }
        return mealplans;
    }

    private int getMealIdFromMealName(String name, int userId){
        int mealId = 0;
        String sql="SELECT meal_id " +
                "FROM meals " +
                "WHERE meal_name = ? AND user_id = ?;";
        SqlRowSet rs = jdbcTemplate.queryForRowSet(sql,name, userId);
        if(rs.next()){
            mealId = rs.getInt("meal_id");
        }/*else{
            String sql2 = "INSERT INTO meals(meal_name) " +
                    "VALUES (?) " +
                    "RETURNING meal_id;";
            mealId = jdbcTemplate.queryForObject(sql2,int.class,name);
            sql2 = "INSERT INTO users_recipes(user_id, recipe_id) "+
                    "VALUES(?,?);";
            jdbcTemplate.update(sql2, userId,recipeId);
        }*/

        return mealId;

    }
    private Mealplan mapRowToMealPlan(SqlRowSet rs){
        Mealplan mealplan = new Mealplan();
        mealplan.setMealplanId(rs.getInt("mealplan_id"));
        mealplan.setMealplanName(rs.getString("mealplan_name"));
        mealplan.setMealplanTime(rs.getString("mealplan_time"));
        mealplan.setMealplanDay(rs.getInt("mealplan_day"));
        mealplan.setUserId(rs.getInt("user_id"));
        //recipe.setIngredients(rs.getObject("recipe_ingredients"));
        return mealplan;
    }

}
