package com.techelevator.controller;

import com.techelevator.dao.MealplanDao;
import com.techelevator.model.Mealplan;
import com.techelevator.model.Recipe;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")
public class MealplanController {
    private MealplanDao mealplanDao;


    public MealplanController(MealplanDao mealplanDao) {
        this.mealplanDao = mealplanDao;
    }
    @RequestMapping(value="/createmealplan", method= RequestMethod.POST)
    public void createMealplan(@RequestBody Mealplan mealplan, Principal principal){
        mealplanDao.createMealplan(mealplan, principal);
    }

    @RequestMapping(value="/mealplans", method = RequestMethod.GET)
    public List<Mealplan> mealplanList(Principal principal){
        return mealplanDao.getAllMealPlans(principal);
    }

    @RequestMapping(value="/mealplans/{mealplanId}",method = RequestMethod.GET)
    public Mealplan getMealplanById(@PathVariable int mealplanId, Principal principal){
        return mealplanDao.getMealPlanById(mealplanId,principal);
    }

    @RequestMapping(value="/mealplans/edit/{mealplanId}", method = RequestMethod.PUT)
    public void editMealplan(@PathVariable int mealplanId, @RequestBody Mealplan mealplan, Principal principal){
        mealplanDao.editMealPlan(mealplanId, mealplan, principal);
    }
}
