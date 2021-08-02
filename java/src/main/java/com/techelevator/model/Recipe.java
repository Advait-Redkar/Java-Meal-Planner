package com.techelevator.model;

public class Recipe {
    private int recipeId;
    private String recipeName;
    private String instructions;

    public Recipe(int recipeId, String recipeName, String instructions) {
        this.recipeId = recipeId;
        this.recipeName = recipeName;
        this.instructions = instructions;
    }

    public Recipe() {
    }

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }
}
