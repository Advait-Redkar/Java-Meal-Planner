package com.techelevator.dao;

import com.techelevator.model.Ingredient;
import com.techelevator.model.Recipe;

import java.security.Principal;
import java.util.List;

public interface RecipeDao {
    public void createRecipe(Recipe newRecipe, Principal principal);
    public List<Recipe> getMyRecipes(Principal principal);
    public Recipe getRecipeById(int recipeId, Principal principal);
    public void editRecipe(int recipeId, Recipe recipe, Principal principal);
    public List<Ingredient> getRecipeIngredients(int recipeId, Principal principal);
}
