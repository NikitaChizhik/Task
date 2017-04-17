package com.nikitachizhik91.model;

public class Category extends Entity {

	public Category(int id, String title) {
		super(id, title);
	}

	public String getTitle() {
		return super.getTitle();
	}

	
	
	@Override
	public String toString() {
		return title;
	}
}
