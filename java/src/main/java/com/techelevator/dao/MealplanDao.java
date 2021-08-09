package com.techelevator.dao;

import com.techelevator.model.Meal;
import com.techelevator.model.Mealplan;

import java.security.Principal;
import java.util.List;

public interface MealplanDao {
public void createMealplan(Mealplan mealplan,Principal principal);
public List<Mealplan> getAllMealPlans(Principal principal);
public Mealplan getMealPlanById(int mealPlanId, Principal principal);
public List<Meal>getMealsForMealPlan(int mealPlanId,Principal principal);
}
