package com.recipe.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.recipe.services.RecipeService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class IngredientController {

	private final RecipeService recipeService;
	
	public IngredientController(RecipeService recipeService) {
		this.recipeService = recipeService;
	}
	
	@RequestMapping("/recipe/{id}/ingredients")
	public String getIngredients(@PathVariable String id, Model model) {
		log.debug("Getting Ingredients List");
		
		model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(id)));
		
		return "recipe/ingredient/list";
	}
}
