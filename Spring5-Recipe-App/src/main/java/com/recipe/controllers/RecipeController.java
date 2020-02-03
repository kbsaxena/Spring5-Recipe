package com.recipe.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.recipe.domain.Recipe;
import com.recipe.services.RecipeService;

@Controller
@RequestMapping("/recipe")
public class RecipeController {
	
	private final RecipeService recipeService;
	
	public RecipeController(RecipeService recipeService) {
		this.recipeService = recipeService;
	}

	@RequestMapping({"/show/{id}"})
	public String getRecipe(Model model, @PathVariable String id) {
		Recipe recipe = recipeService.findById(Long.valueOf(id));
		
		model.addAttribute("recipe", recipe);
		
		return "recipe/index";
	}
}
