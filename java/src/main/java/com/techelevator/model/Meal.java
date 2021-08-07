package com.techelevator.model;

import java.util.List;

public class Meal {
    private int userId;
    private int mealId;
    private String mealName;
    private int mealType;
    private List<Recipe> recipeList;

    public Meal() {

    }

    public Meal(int userId, int mealId, String mealName, int mealType, List<Recipe> recipeList) {
        this.userId = userId;
        this.mealId = mealId;
        this.mealName = mealName;
        this.mealType = mealType;
        this.recipeList = recipeList;

    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getMealId() {
        return mealId;
    }

    public void setMealId(int mealId) {
        this.mealId = mealId;
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public int getMealType() {
        return mealType;
    }

    public void setMealType(int mealType) {
        this.mealType = mealType;
    }

    public List<Recipe> getRecipeList() {
        return recipeList;
    }

    public void setRecipeList(List<Recipe> recipeList) {
        this.recipeList = recipeList;
    }
}