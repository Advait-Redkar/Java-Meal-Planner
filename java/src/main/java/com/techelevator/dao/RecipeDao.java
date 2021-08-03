package com.techelevator.dao;

import com.techelevator.model.Recipe;

import java.security.Principal;
import java.util.List;

public interface RecipeDao {
    public void createRecipe(Recipe newRecipe, Principal principal);
    public List<Recipe> getMyRecipes(Principal principal);
}
