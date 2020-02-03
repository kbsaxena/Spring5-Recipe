package com.recipe.services;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.recipe.domain.Recipe;
import com.recipe.repositories.RecipeRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {

	RecipeRepository recipeRepository;
	
	public RecipeServiceImpl(RecipeRepository recipeRepository) {
		this.recipeRepository = recipeRepository;
	}

	@Override
	public Set<Recipe> getRecipes() {
		log.debug("I am in Service");
		
		Set<Recipe> recipeSet = new HashSet<Recipe>();
		recipeRepository.findAll().iterator().forEachRemaining(recipeSet::add);
		return recipeSet;
	}

	@Override
	public Recipe findById(Long id) { 
		Optional<Recipe> recipe = recipeRepository.findById(id);
		
		if(!recipe.isPresent()) {
			throw new RuntimeException("Recipe Not Found");
		}
		
		return recipe.get();
	}
	
}
 