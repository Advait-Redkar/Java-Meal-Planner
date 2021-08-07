package com.techelevator.controller;

import com.techelevator.dao.MealDao;
import com.techelevator.dao.MealplanDao;
import com.techelevator.model.Meal;
import com.techelevator.model.Mealplan;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")
public class MealController {
    private MealDao mealDao;


    public MealController(MealDao mealDao) {
        this.mealDao = mealDao;
    }

    @RequestMapping(value="/createmeal", method= RequestMethod.POST)
    public void createMeal(@RequestBody Meal meal, Principal principal){
        mealDao.createMeal(meal, principal);
    }

    @RequestMapping(value="/meals", method = RequestMethod.GET)
    public List<Meal> mealList(Principal principal){

        return mealDao.getAllMeals(principal);
    }
}
