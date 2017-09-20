package com.manos.spring5recipeapp.controllers;

import com.manos.spring5recipeapp.commands.IngredientCommand;
import com.manos.spring5recipeapp.services.IngrentientService;
import com.manos.spring5recipeapp.services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IngrentientController {

    @Autowired
    RecipeService recipeService;

    @Autowired
    IngrentientService ingrentientService;

    @GetMapping
    @RequestMapping("/recipe/{id}/ingredients")
    public String listOfIngrentients(@PathVariable String id, Model model){
        model.addAttribute("recipe",recipeService.findCommandById(Long.valueOf(id)));
        return "recipe/ingredient/list";
    }

    @GetMapping
    @RequestMapping("/recipe/{recipeId}/ingredient/{ingredientId}/show")
    public String showRecipeIngredient(@PathVariable String recipeId, @PathVariable String ingredientId ,Model model){
        IngredientCommand ingredientCommand = ingrentientService.findByRecipeIdAndIngrentientId(Long.valueOf(recipeId), Long.valueOf(ingredientId));
        model.addAttribute("ingredient",ingredientCommand);
        return "recipe/ingredient/show";
    }

}
