package com.techelevator.model;

import java.util.List;

public class Mealplan {
    private int userId;

    private int mealplanId;
    private String mealplanName;
    private String mealplanTime;
    private int mealplanDay;
    private List<Recipe> recipeList;

    public Mealplan(int userId, int mealplanId, String mealplanName, String mealplanTime, int mealplanDay, List<Recipe> recipeList) {
        this.userId = userId;
        this.mealplanId = mealplanId;
        this.mealplanName = mealplanName;
        this.mealplanTime = mealplanTime;
        this.mealplanDay = mealplanDay;
        this.recipeList = recipeList;
    }

    public Mealplan() {

    }

    public List<Recipe> getRecipeList() {
        return recipeList;
    }

    public void setRecipeList(List<Recipe> recipeList) {
        this.recipeList = recipeList;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getMealplanId() {
        return mealplanId;
    }

    public void setMealplanId(int mealplanId) {
        this.mealplanId = mealplanId;
    }

    public String getMealplanName() {
        return mealplanName;
    }

    public void setMealplanName(String mealplanName) {
        this.mealplanName = mealplanName;
    }

    public String getMealplanTime() {
        return mealplanTime;
    }

    public void setMealplanTime(String mealplanTime) {
        this.mealplanTime = mealplanTime;
    }

    public int getMealplanDay() {
        return mealplanDay;
    }

    public void setMealplanDay(int mealplanDay) {
        this.mealplanDay = mealplanDay;
    }
}
