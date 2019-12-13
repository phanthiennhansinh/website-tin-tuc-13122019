package com.hocjavaweb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hocjavaweb.entity.NewsEntity;

public interface INewsRepository extends JpaRepository<NewsEntity, Long>{

	@Query(value = "SELECT * FROM news a WHERE a.title like %:keyword% or a.content like %:keyword%",nativeQuery = true)
	List<NewsEntity> findAllByKeyword(@Param("keyword") String keyword);
	
	
	@Query(value = "SELECT * FROM news a WHERE a.category_id = :id ",nativeQuery = true)
	List<NewsEntity> findAllByCategoryId(@Param("id") Long id);
	
}
