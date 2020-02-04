package com.recipe.converters;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;
import com.recipe.commands.CategoryCommand;
import com.recipe.domain.Category;
import com.sun.istack.Nullable;

import lombok.Synchronized;

@Component
public class CategoryCommandToCategory  implements Converter<CategoryCommand, Category>{

	@Override
	@Nullable
	@Synchronized
	public Category convert(CategoryCommand value) {
		if(value == null) {
			return null;
		}
		
		final Category cat = new Category();
		cat.setId(value.getId());
		cat.setDescription(value.getDescription());
		
		return cat;
	}

	@Override
	public JavaType getInputType(TypeFactory typeFactory) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JavaType getOutputType(TypeFactory typeFactory) {
		// TODO Auto-generated method stub
		return null;
	}

}
