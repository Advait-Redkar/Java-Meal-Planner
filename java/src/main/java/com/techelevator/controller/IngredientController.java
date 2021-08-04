package com.techelevator.controller;

import com.techelevator.dao.IngredientDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Ingredient;
import com.techelevator.model.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")
public class IngredientController {
    private IngredientDao ingredientDao;
    private UserDao userDao;

    public IngredientController(IngredientDao ingredientDao, UserDao userDao) {
        this.ingredientDao = ingredientDao;
        this.userDao = userDao;
    }
    @RequestMapping(value="/createingredient", method = RequestMethod.POST)
    public void createIngredient(@RequestBody Ingredient ingredient, Principal principal){
        ingredientDao.createIngredient(ingredient,principal);
    }
    @RequestMapping(value= "/ingredients", method = RequestMethod.GET)
    public List<Ingredient> listIngredients(Principal principal){
        return ingredientDao.listIngredients(principal);
    }
}
