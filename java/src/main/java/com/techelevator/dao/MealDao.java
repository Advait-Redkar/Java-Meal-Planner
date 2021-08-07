package com.techelevator.dao;

import com.techelevator.model.Meal;
import com.techelevator.model.Mealplan;

import java.security.Principal;
import java.util.List;

public interface MealDao {
public void createMeal(Meal meal, Principal principal);
public List<Meal> getAllMeals(Principal principal);
}
