package com.hocjavaweb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hocjavaweb.entity.CategoryEntity;

public interface ICategoryRepository extends JpaRepository<CategoryEntity, Long>{
	
	 CategoryEntity findOneByCode(String code);
	
//	@Query(value = "SELECT c FROM category c ORDER BY c.name DESC")
//	List<CategoryEntity> findAllByNameDesc();

	 @Query(value = "SELECT * FROM category c WHERE c.code like %:keyword% or c.name like %:keyword%", nativeQuery = true)
	 List<CategoryEntity> findAllOrderByNameDesc(@Param("keyword") String keyword);
}
