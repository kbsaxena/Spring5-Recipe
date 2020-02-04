package com.recipe.commands;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class IngredientCommand {
	
	private Long id;
	private UnitOfMeasureCommand uom;
	private BigDecimal amount;
	private String description;
}
