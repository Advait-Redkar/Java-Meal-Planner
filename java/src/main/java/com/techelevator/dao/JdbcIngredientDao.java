package com.techelevator.dao;

import com.techelevator.model.Ingredient;
import org.springframework.data.relational.core.sql.In;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcIngredientDao implements IngredientDao{
    private final JdbcTemplate jdbcTemplate;
    private UserDao userDao;
    public JdbcIngredientDao(JdbcTemplate jdbcTemplate, UserDao userDao) {
        this.jdbcTemplate = jdbcTemplate;
        this.userDao = userDao;
    }

    @Override
    public void createIngredient(Ingredient newIngredient, Principal principal) {
        String sql = "INSERT INTO ingredients(ingredient_name)" +
                     "VALUES(?)";
        jdbcTemplate.update(sql, newIngredient.getIngredientName());
    }
    @Override
    public List<Ingredient> listIngredients(Principal principal){
        List<Ingredient> ingredients = new ArrayList<>();
        String currentUserName= principal.getName();
        int currentUserId=userDao.findIdByUsername(currentUserName);
        String sql = "SELECT ingredient_name, ingredient_id, user_id " +
                     "FROM ingredients " +
                     "WHERE user_id = ?;";
       SqlRowSet rs = jdbcTemplate.queryForRowSet(sql, currentUserId);
        while(rs.next()){
            Ingredient ingredient = mapRowToIngredient(rs);
            ingredients.add(ingredient);
        }
        return ingredients;
    }

    private Ingredient mapRowToIngredient(SqlRowSet rs){
        Ingredient ingredient = new Ingredient();
        ingredient.setUserId(rs.getInt("user_id"));
        ingredient.setIngredientId(rs.getInt("ingredient_id"));
        ingredient.setIngredientName(rs.getString("ingredient_name"));
        return ingredient;
    }
}
