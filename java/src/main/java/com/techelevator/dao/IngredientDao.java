package com.techelevator.dao;

import com.techelevator.model.Ingredient;

import java.security.Principal;
import java.util.List;

public interface IngredientDao {
    public void createIngredient(Ingredient newIngredient, Principal principal);
    public List<Ingredient> listIngredients(Principal principal);
}
