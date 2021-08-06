package com.techelevator.dao;

import com.techelevator.model.Mealplan;

import java.security.Principal;

public interface MealplanDao {
public void createMealplan(Mealplan mealplan,Principal principal);
}
