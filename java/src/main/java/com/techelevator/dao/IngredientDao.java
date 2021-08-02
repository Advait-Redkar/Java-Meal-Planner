package com.techelevator.dao;

import com.techelevator.model.Ingredient;

import java.security.Principal;

public interface IngredientDao {
    public void createIngredient(Ingredient newIngredient, Principal principal);
}
