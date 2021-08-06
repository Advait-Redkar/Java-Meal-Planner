package com.techelevator.controller;

import com.techelevator.dao.MealplanDao;
import com.techelevator.model.Mealplan;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

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
}
