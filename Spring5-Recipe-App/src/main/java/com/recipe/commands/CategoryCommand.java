package com.recipe.commands;

import java.util.HashSet;
import java.util.Set;

import com.recipe.domain.Recipe;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryCommand {
	private Long id;
	private String description;
	private Set<Recipe> recipes  = new HashSet<>();
	
}
