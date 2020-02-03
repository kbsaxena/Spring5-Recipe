package com.recipe.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import com.recipe.services.RecipeService;

public class RecipeControllerTest {
	
	@Mock
    RecipeService recipeService;

    @Mock
    Model model;

    RecipeController controller;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        controller = new RecipeController(recipeService);
    }
    
    /*
     * Testing MVCs
     */
    @Test
    public void testGetRecipe() throws Exception{
    	/*
    	 * 2 variations :
    	 * webAppContextSetup - used in integration test as it loads the spring context as well
    	 * standaloneSetup - for unit Test
    	 */
    	MockMvc mockMVC = MockMvcBuilders.standaloneSetup(controller).build();
    	
    	mockMVC.perform(get("/recipe/show/1"))
    	.andExpect(status().isOk())
    	//.andExpect(model().attributeExists("recipe")) // Need to fix this
    	.andExpect(view().name("recipe/index"));
    	
    }

}
