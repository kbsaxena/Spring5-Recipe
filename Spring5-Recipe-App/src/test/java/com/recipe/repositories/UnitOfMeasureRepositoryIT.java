package com.recipe.repositories;

import static org.junit.Assert.assertEquals;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.recipe.domain.UnitOfMeasure;
/*
 * This is an Integration Test as in this we are loading the Spring context
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class UnitOfMeasureRepositoryIT {
	
	@Autowired
	UnitOfMeasureRepository unitOfMeasureRepository;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	@DirtiesContext
	public void testFindByDescription() {
		Optional<UnitOfMeasure> findObj = unitOfMeasureRepository.findByDescription("Teaspoon");
		
		assertEquals("Teaspoon", findObj.get().getDescription());
	}
	
	@Test
	public void testFindByDescriptionCup() {
		Optional<UnitOfMeasure> findObj = unitOfMeasureRepository.findByDescription("Cup");
		
		assertEquals("Cup", findObj.get().getDescription());
	}
	
	/*
	 * if 2 methods are pointing to same context then second method takes lesser time.
	 * if you Add @DirtiesContext annotation that makes the context reload for the next test again
	 */

}
