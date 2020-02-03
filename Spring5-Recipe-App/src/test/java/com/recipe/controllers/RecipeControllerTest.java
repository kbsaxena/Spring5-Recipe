package com.recipe.controllers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import com.recipe.commands.RecipeCommand;
import com.recipe.services.RecipeService;

public class RecipeControllerTest {
	
	@Mock
    RecipeService recipeService;

    @Mock
    Model model;

    RecipeController controller;
    
    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        controller = new RecipeController(recipeService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
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
    	
    	mockMVC.perform(get("/recipe/1/show"))
    	.andExpect(status().isOk())
    	//.andExpect(model().attributeExists("recipe")) // Need to fix this
    	.andExpect(view().name("recipe/index"));
    	
    }
    
    @Test
    public void testGetNewRecipeForm() throws Exception {

        mockMvc.perform(get("/recipe/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/recipeNew"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("recipe"));
    }

    @Test
    public void testPostNewRecipeForm() throws Exception {
        RecipeCommand command = new RecipeCommand();
        command.setId(2L);

        when(recipeService.saveRecipeCommand(Mockito.any())).thenReturn(command);

        mockMvc.perform(post("/recipe/")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "")
                .param("description", "some string"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/recipe/2/show"));
    }

    @Test
    public void testGetUpdateView() throws Exception {
        RecipeCommand command = new RecipeCommand();
        command.setId(2L);

        when(recipeService.findCommandById(Mockito.anyLong())).thenReturn(command);

        mockMvc.perform(get("/recipe/1/update"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/recipeNew"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("recipe"));
    }

}
