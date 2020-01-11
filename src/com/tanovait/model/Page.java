package com.tanovait.model;

import java.util.Date;

public class Page {
	int id;
	public Page(int id, Language language, PageType pageType, int level_id, String name, String theme, Date date) {
		super();
		this.id = id;
		this.language = language;
		this.pageType = pageType;
		this.level_id = level_id;
		this.name = name;
		this.theme = theme;
		this.date = date;
	}
	public Language getLanguage() {
		return language;
	}
	public void setLanguage(Language language) {
		this.language = language;
	}
	public PageType getPageType() {
		return pageType;
	}
	public void setPageType(PageType pageType) {
		this.pageType = pageType;
	}
	Language language;
	PageType pageType;
	int level_id;
	String name;
	String theme;
	Date date;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getLevel_id() {
		return level_id;
	}
	public void setLevel_id(int level_id) {
		this.level_id = level_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTheme() {
		return theme;
	}
	public void setTheme(String theme) {
		this.theme = theme;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
}
