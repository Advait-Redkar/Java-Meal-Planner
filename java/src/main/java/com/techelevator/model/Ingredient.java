package com.techelevator.model;

public class Ingredient {
    private int ingredientId;
    private String ingredientName;
    private int userId;

    public Ingredient(int ingredientId, String ingredientName, int userId) {
        this.userId = userId;
        this.ingredientId = ingredientId;
        this.ingredientName = ingredientName;
    }

    public Ingredient() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(int ingredientId) {
        this.ingredientId = ingredientId;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }
}
