package com.recipe.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.recipe.commands.RecipeCommand;
import com.recipe.domain.Recipe;
import com.recipe.services.RecipeService;

@Controller
@RequestMapping("/recipe")
public class RecipeController {
	
	private final RecipeService recipeService;
	
	public RecipeController(RecipeService recipeService) {
		this.recipeService = recipeService;
	}

	@GetMapping({"/{id}/show"})
	public String getRecipe(Model model, @PathVariable String id) {
		Recipe recipe = recipeService.findById(Long.valueOf(id));
		
		model.addAttribute("recipe", recipe);
		
		return "recipe/index";
	}
	
	@GetMapping({"/{id}/update"})
	public String updateRecipe(Model model, @PathVariable String id) {
		RecipeCommand recipe = recipeService.findCommandById(Long.valueOf(id));
		
		model.addAttribute("recipe", recipe);
		
		return "recipe/recipeNew";
	}
	
	@GetMapping({"/new"})
	public String newRecipe(Model model) {
		model.addAttribute("recipe", new RecipeCommand());
		return "recipe/recipeNew";
	}
	
	@PostMapping({"/"})
	public String saveorUpdate(@ModelAttribute RecipeCommand command) {
		RecipeCommand recipeCommand = recipeService.saveRecipeCommand(command);
		
		return "redirect:/recipe/"+recipeCommand.getId() +"/show";
	}
	
	@GetMapping({"/{id}/delete"})
	public String deleteRecipe(@PathVariable String id) {
		recipeService.deleteById(Long.valueOf(id));
		
		return "redirect:/";
	}
	
}
