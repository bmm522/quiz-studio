package com.quizbatch.tasklets.makequiz.step1apirequest;

public enum CategoryTitle {

	JAVA("java"), DATA_STRUCTURE("datastructure"), DATABASE("database");

	private final String categoryTitle;

	CategoryTitle(String categoryTitle) {
		this.categoryTitle = categoryTitle;
	}

	public String get() {
		return categoryTitle;
	}
}
