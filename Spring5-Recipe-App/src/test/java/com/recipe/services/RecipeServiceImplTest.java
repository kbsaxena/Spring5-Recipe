package com.recipe.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.recipe.domain.Recipe;
import com.recipe.repositories.RecipeRepository;

public class RecipeServiceImplTest {
	
	RecipeServiceImpl recipeService;
	
	@Mock
	RecipeRepository recipeRepository;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		recipeService = new RecipeServiceImpl(recipeRepository);
	}
	

    @Test
    public void getRecipes() {
    	HashSet<Recipe> recipeData = new HashSet<>();
    	recipeData.add(new Recipe());

		Mockito.when(recipeRepository.findAll()).thenReturn(recipeData);
    	
    	
    	Set<Recipe> recipes = recipeService.getRecipes();
    	
    	assertEquals(1, recipes.size());
    
    	Mockito.verify(recipeRepository, Mockito.times(1)).findAll();
    	// Mockito.verify(recipeService, Mockito.times(2));  //NotAMockException... Works only with Mocks
    }
    
    
}