package com.recipe.controllers;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.recipe.commands.IngredientCommand;
import com.recipe.commands.RecipeCommand;
import com.recipe.services.IngredientService;
import com.recipe.services.RecipeService;

public class IngredientControllerTest {
	
	@Mock
    RecipeService recipeService;
	
	@Mock
	IngredientService ingredientService;

    IngredientController controller;
    
    MockMvc mockMVC;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        controller = new IngredientController(recipeService, ingredientService);
        mockMVC = MockMvcBuilders.standaloneSetup(controller).build();
    }
    
    /*
     * Testing MVCs
     */
    @Test
    public void testListIngredient() throws Exception{
    	
    	//given
    	RecipeCommand rec = new RecipeCommand();
    	when(recipeService.findCommandById(Mockito.anyLong())).thenReturn(rec);
    	
    	//when
    	mockMVC.perform(get("/recipe/1/ingredients"))
    	.andExpect(status().isOk())
    	.andExpect(view().name("recipe/ingredient/list"))
    	.andExpect(model().attributeExists("recipe"));
    	
    	//then
    	verify(recipeService, times(1)).findCommandById(Mockito.anyLong());
    }
    
    @Test
    public void testShowRecipeIngredient() throws Exception{
    	
    	//given
    	IngredientCommand ingredientCommand = new IngredientCommand();
    	when(ingredientService.findByRecipeIdAndId(Mockito.anyLong(), Mockito.anyLong())).thenReturn(ingredientCommand);
    	
    	//when
    	mockMVC.perform(get("/recipe/1/ingredient/1/show"))
    	.andExpect(status().isOk())
    	.andExpect(view().name("recipe/ingredient/show"))
    	.andExpect(model().attributeExists("ingredient"));
    	
    	//then
    	verify(ingredientService, times(1)).findByRecipeIdAndId(Mockito.anyLong(), Mockito.anyLong());
    }

}
