package com.techelevator.controller;

import com.techelevator.dao.RecipeDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Recipe;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")
public class RecipeController {
    private RecipeDao recipeDao;
    private UserDao userDao;

    public RecipeController(RecipeDao recipeDao, UserDao userDao) {
        this.recipeDao = recipeDao;
        this.userDao=userDao;

    }
    @RequestMapping(value = "/createrecipe", method = RequestMethod.POST)
    public void createRecipe(@RequestBody Recipe recipe, Principal principal){
       recipeDao.createRecipe(recipe, principal);
    }

    @RequestMapping(value= "/recipes", method = RequestMethod.GET)
    public List<Recipe> recipesList(Principal principal){
        String loggedInUsername= principal.getName();
        int loggedInUserId= userDao.findIdByUsername(loggedInUsername);
        return recipeDao.getMyRecipes(principal);
    }
}
