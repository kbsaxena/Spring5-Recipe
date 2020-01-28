package com.recipe.repositories;

import org.springframework.data.repository.CrudRepository;

import com.recipe.domain.Category;

public interface CategoryRepository extends CrudRepository<Category, Long>{

}
