package com.recipe.converters;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.recipe.commands.CategoryCommand;
import com.recipe.domain.Category;

public class CategoryCommandToCategoryTest {
	
	private static final String DESCRIPTION = "description";
	private static final Long ID = 1L;
	
	CategoryCommandToCategory converter;

	@BeforeEach
	public void setUp() throws Exception {
		converter = new CategoryCommandToCategory();
	}

	@Test
	public final void testNullParameter() {
		assertNull(converter.convert(null));
	}
	
	@Test
	public final void testEmptyObject() {
		assertNotNull(converter.convert(new CategoryCommand()));
	}
	
	@Test
	public final void testConvert() {
		//given
		CategoryCommand cc = new CategoryCommand();
		cc.setId(ID);
		cc.setDescription(DESCRIPTION);
		
		//when
		Category cat = converter.convert(cc);
		
		//then
		assertNotNull(cat);
		assertEquals(ID, cat.getId());
		assertEquals(DESCRIPTION, cat.getDescription());
	}

}
