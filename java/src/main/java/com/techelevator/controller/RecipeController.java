package com.techelevator.controller;

import com.techelevator.dao.IngredientDao;
import com.techelevator.dao.RecipeDao;
import com.techelevator.model.Recipe;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")
public class RecipeController {
    private RecipeDao RecipeDao;

    public RecipeController(com.techelevator.dao.RecipeDao recipeDao) {
        RecipeDao = recipeDao;

    }
    @RequestMapping(value = "/createrecipe", method = RequestMethod.POST)
    public void createRecipe(@RequestBody Recipe recipe, Principal principal){
       RecipeDao.createRecipe(recipe, principal);
    }
}
