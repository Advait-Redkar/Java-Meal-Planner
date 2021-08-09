package com.techelevator.model;

import java.util.List;

public class Mealplan {
    private int userId;

    private int mealplanId;
    private String mealplanName;
    private List<Meal>mealList;
    private int mealplanDay;




    public Mealplan(int userId, int mealplanId, String mealplanName, int mealplanDay, List<Meal> mealList) {
        this.userId = userId;
        this.mealplanId = mealplanId;
        this.mealplanName = mealplanName;

        this.mealplanDay = mealplanDay;
        this.mealList = mealList;
    }

    public Mealplan() {

    }

    public List<Meal> getMealList() {
        return mealList;
    }

    public void setMealList(List<Meal> mealList) {
        this.mealList = mealList;
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

    public int getMealplanDay() {
        return mealplanDay;
    }

    public void setMealplanDay(int mealplanDay) {
        this.mealplanDay = mealplanDay;
    }
}
