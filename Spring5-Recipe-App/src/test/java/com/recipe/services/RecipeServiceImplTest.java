package com.recipe.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.recipe.converters.RecipeCommandToRecipe;
import com.recipe.converters.RecipeToRecipeCommand;
import com.recipe.domain.Recipe;
import com.recipe.repositories.RecipeRepository;

public class RecipeServiceImplTest {
	
	RecipeServiceImpl recipeService;
	
	@Mock
	RecipeRepository recipeRepository;
	
	@Mock
    RecipeToRecipeCommand recipeToRecipeCommand;

    @Mock
    RecipeCommandToRecipe recipeCommandToRecipe;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		recipeService = new RecipeServiceImpl(recipeRepository, recipeCommandToRecipe, recipeToRecipeCommand);
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
    
    @Test
    public void getRecipesById() {
    	Recipe recipe = new Recipe();
    	recipe.setId(1L);

		Mockito.when(recipeRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(recipe));
    	
    	Recipe recipeResponse = recipeService.findById(1L);
    	
    	assertNotNull(recipeResponse);
    	assertEquals(1L, recipeResponse.getId());
    
    	Mockito.verify(recipeRepository, Mockito.times(1)).findById(Mockito.anyLong());
    	Mockito.verify(recipeRepository, Mockito.never()).findAll();
    }
    
    @Test
    public void testDeleteById() throws Exception {

        //given
        Long idToDelete = Long.valueOf(2L);

        //when
        recipeService.deleteById(idToDelete);

        //no 'when', since method has void return type

        //then
        Mockito.verify(recipeRepository, Mockito.times(1)).deleteById(Mockito.anyLong());
    }
    
}