package com.tanovait.model;

public class PageType {
	int id;
	String name;
	String metadata;
	int langId;
	
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
	public String getMetadata() {
		return metadata;
	}
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}
	public int getLangId() {
		return langId;
	}
	public void setLangId(int langId) {
		this.langId = langId;
	}
	public PageType(int id, String name, String metadata, int langId) {
		super();
		this.id = id;
		this.name = name;
		this.metadata = metadata;
		this.langId = langId;
	}
}
