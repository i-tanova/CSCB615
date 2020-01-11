package com.tanovait.model;

public class Language {
	
	String name;
	String acronym;
	int id;
	
	public Language(String name, String acronym) {
		super();
		this.name = name;
		this.acronym = acronym;
	}
	
	public Language(int id, String name, String acronym) {
		super();
		this.id = id;
		this.name = name;
		this.acronym = acronym;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAcronym() {
		return acronym;
	}

	public void setAcronym(String acronym) {
		this.acronym = acronym;
	}
	
	

}
