package com.techelevator.dao;

import com.techelevator.model.Ingredient;
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
        String sql = "INSERT INTO mealplan(mealplan_name, mealplan_day, user_id) " +
                     "VALUES(?,?,?) "+
                     "RETURNING mealplan_id;";
        String currentUserName= principal.getName();
        int currentUserId=userDao.findIdByUsername(currentUserName);
        int mealplanId = jdbcTemplate.queryForObject(sql, int.class, mealplan.getMealplanName(), mealplan.getMealplanDay(), currentUserId);
        for(Meal meal: mealplan.getMealList()){
            String name  = meal.getMealName();
            sql = "INSERT INTO mealplan_meal(mealplan_id, meal_id) " +
                    "VALUES(?,?);";
            jdbcTemplate.update(sql, mealplanId, getMealIdFromMealName(name,currentUserId));
        }

    }

    @Override
    public List<Mealplan> getAllMealPlans(Principal principal) {
        List<Mealplan>mealplans=new ArrayList<>();
        String currentUserName= principal.getName();
        int currentUserId=userDao.findIdByUsername(currentUserName);
        String sql="SELECT user_id, mealplan_id, mealplan_name, mealplan_day " +
                "FROM mealplan " +
                "WHERE user_id=?;";
        SqlRowSet results= jdbcTemplate.queryForRowSet(sql,currentUserId);
        while(results.next()){
            Mealplan mealplan=mapRowToMealPlan(results);
            mealplans.add(mealplan);
        }
        return mealplans;
    }

    @Override
    public Mealplan getMealPlanById(int mealPlanId, Principal principal) {
        Mealplan mealplan = new Mealplan();
        String sql= "SELECT mealplan_id, mealplan_name, mealplan_day, user_id " +
                "FROM mealplan " +
                "WHERE mealplan_id = ?;";
        SqlRowSet rs= jdbcTemplate.queryForRowSet(sql, mealPlanId);
        if(rs.next()){
            mealplan=mapRowToMealPlan(rs);
            mealplan.setMealList(getMealsForMealPlan(mealPlanId, principal));
        }

        return mealplan;
    }

    @Override
    public List<Meal> getMealsForMealPlan(int mealPlanId, Principal principal) {
        List<Meal>meals=new ArrayList<>();
        String sql="SELECT user_id, meals.meal_id, meal_name, meal_type " +
                "FROM meals " +
                "JOIN mealplan_meal ON mealplan_meal.meal_id = meals.meal_id " +
                "WHERE mealplan_id=?;";
        SqlRowSet rs = jdbcTemplate.queryForRowSet(sql,mealPlanId);
        while(rs.next()){
            Meal meal=mapMealsToMealPlan(rs);
            meals.add(meal);
        }
        return meals;
    }

    @Override
    public void editMealPlan(int mealPlanId, Mealplan mealplan, Principal principal) {
        String sql=
                "UPDATE mealplan " +
                        "SET mealplan_name=?, mealplan_day=?" +
                        "WHERE mealplan_id = ?; ";
        jdbcTemplate.update(sql, mealplan.getMealplanName(), mealplan.getMealplanDay(), mealPlanId);

        sql = "DELETE FROM mealplan_meal " +
                "WHERE mealplan_id = ?;";
        jdbcTemplate.update(sql, mealPlanId);

        String currentUserName= principal.getName();
        int currentUserId=userDao.findIdByUsername(currentUserName);

        for (int i=0; i<mealplan.getMealList().size();i++) {
            String sql2 = "INSERT INTO mealplan_meal(meal_id, mealplan_id) " +
                    "VALUES ";
            Meal meal = mealplan.getMealList().get(i);
            String mealName = meal.getMealName();
            String newStr = "(" + getMealIdFromMealName(mealName, currentUserId) + "," + mealPlanId + ")";
            sql2 = sql2 + newStr;
            jdbcTemplate.update(sql2);
        }
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
        mealplan.setMealplanDay(rs.getInt("mealplan_day"));
        mealplan.setUserId(rs.getInt("user_id"));
        //recipe.setIngredients(rs.getObject("recipe_ingredients"));
        return mealplan;
    }
    private Meal mapMealsToMealPlan(SqlRowSet rs){
        Meal meal = new Meal();
        meal.setUserId(rs.getInt("user_id"));
        meal.setMealId(rs.getInt("meal_id"));
        meal.setMealName(rs.getString("meal_name"));
        meal.setMealType(rs.getInt("meal_type"));
        return meal;
    }



}
