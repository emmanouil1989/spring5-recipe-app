package com.manos.spring5recipeapp.controllers;

import com.manos.spring5recipeapp.commands.IngredientCommand;
import com.manos.spring5recipeapp.commands.RecipeCommand;
import com.manos.spring5recipeapp.commands.UnitOfMeasureCommand;
import com.manos.spring5recipeapp.models.Recipe;
import com.manos.spring5recipeapp.models.UnitOfMeasure;
import com.manos.spring5recipeapp.services.IngrentientService;
import com.manos.spring5recipeapp.services.RecipeService;
import com.manos.spring5recipeapp.services.UnitOfMeasureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Controller
public class IngrentientController {

    @Autowired
    RecipeService recipeService;

    @Autowired
    IngrentientService ingrentientService;

    @Autowired
    UnitOfMeasureService unitOfMeasureService;

    @GetMapping("/recipe/{id}/ingredients")
    public String listOfIngrentients(@PathVariable String id, Model model){
        model.addAttribute("recipe",recipeService.findCommandById(Long.valueOf(id)));
        return "recipe/ingredient/list";
    }

    @GetMapping("/recipe/{recipeId}/ingredient/{ingredientId}/show")
    public String showRecipeIngredient(@PathVariable String recipeId, @PathVariable String ingredientId ,Model model){
        IngredientCommand ingredientCommand = ingrentientService.findByRecipeIdAndIngrentientId(Long.valueOf(recipeId), Long.valueOf(ingredientId));
        model.addAttribute("ingredient",ingredientCommand);
        return "recipe/ingredient/show";
    }

    @GetMapping("/recipe/{recipeId}/ingredient/{ingredientId}/update")
    public String showIngredient(@PathVariable String recipeId, @PathVariable String ingredientId ,Model model){
        IngredientCommand ingredientCommand = ingrentientService.findByRecipeIdAndIngrentientId(Long.valueOf(recipeId), Long.valueOf(ingredientId));

        Set<UnitOfMeasureCommand> all = unitOfMeasureService.findAll();
        model.addAttribute("ingredient",ingredientCommand);
        model.addAttribute("uomList",all);
        return "recipe/ingredient/ingredientform";
    }

    @PostMapping("recipe/{recipeId}/ingredient")
    public String updateIngredient(@ModelAttribute IngredientCommand command){
        IngredientCommand ingredientCommand = ingrentientService.saveIngredient(command);
        return "redirect:/recipe/" + ingredientCommand.getRecipeId()+ "/ingredient/" + ingredientCommand.getId() + "/show";
    }

    @GetMapping("/recipe/{recipeId}/ingredient/new")
    public String saveIngredient(@PathVariable String recipeId,Model model){
        RecipeCommand recipe = recipeService.findCommandById(Long.valueOf(recipeId));

        IngredientCommand command = new IngredientCommand();
        command.setRecipeId(recipe.getId());
        model.addAttribute("ingredient",command);
        command.setUom(new UnitOfMeasureCommand());

        model.addAttribute("uomList",unitOfMeasureService.findAll());

        return "recipe/ingredient/ingredientform";
    }

    @GetMapping("/recipe/{recipeId}/ingredient/{id}/delete")
    public String deleteIngredient(@PathVariable String recipeId ,@PathVariable String id){
        ingrentientService.deleteIngredient(Long.valueOf(recipeId),Long.valueOf(id));
        return "redirect:/recipe/"+ recipeId +"/ingredients";
    }

}
