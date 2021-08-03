package com.techelevator.controller;

import com.techelevator.dao.IngredientDao;
import com.techelevator.model.Ingredient;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")
public class IngredientController {
    private IngredientDao ingredientDao;

    public IngredientController(IngredientDao ingredientDao) {
        this.ingredientDao = ingredientDao;
    }
    @RequestMapping(value="/createingredient", method = RequestMethod.POST)
    public void createIngredient(@RequestBody Ingredient ingredient, Principal principal){
        ingredientDao.createIngredient(ingredient,principal);
    }
}
