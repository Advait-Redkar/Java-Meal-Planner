package com.techelevator.dao;

import com.techelevator.model.Mealplan;

import java.security.Principal;
import java.util.List;

public interface MealplanDao {
public void createMealplan(Mealplan mealplan,Principal principal);
public List<Mealplan> getAllMealPlans(Principal principal);
}
