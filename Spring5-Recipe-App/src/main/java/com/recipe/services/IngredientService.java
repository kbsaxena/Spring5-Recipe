package com.recipe.services;

import com.recipe.commands.IngredientCommand;

public interface IngredientService {
	IngredientCommand findByRecipeIdAndId(Long recipeId, Long ingredientId);
}
