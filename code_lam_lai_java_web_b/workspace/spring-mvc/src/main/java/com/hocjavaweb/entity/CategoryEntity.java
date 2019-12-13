package com.hocjavaweb.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "category")
public class CategoryEntity extends BaseEntity{
	
	@Column(name = "code")
	private String code;

	@Column(name = "name")
	private String name;

	@OneToMany(mappedBy = "categoryEntity",cascade = CascadeType.ALL)
	private List<NewsEntity> newsEntitiesList = new ArrayList<>();
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<NewsEntity> getNewsEntitiesList() {
		return newsEntitiesList;
	}

	public void setNewsEntitiesList(List<NewsEntity> newsEntitiesList) {
		this.newsEntitiesList = newsEntitiesList;
	}
	
}
