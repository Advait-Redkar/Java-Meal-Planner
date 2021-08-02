package com.techelevator.dao;

import com.techelevator.model.Recipe;

import java.security.Principal;

public interface RecipeDao {
    public void createRecipe(Recipe newRecipe, Principal principal);
}
