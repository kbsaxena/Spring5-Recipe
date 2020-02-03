package com.recipe.commands;

import java.math.BigDecimal;

import com.recipe.domain.Recipe;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class IngredientCommand {
	
	private Long id;
	private Recipe recipe;
	private UnitOfMeasureCommand uom;
	private BigDecimal amount;
	private String description;
}
