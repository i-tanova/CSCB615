package com.tanovait.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;
import com.tanovait.model.*;

public class PageDBUtil {

	DataSource dataSource;

	public PageDBUtil(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public List<Page> getPages(){
		Connection conn = null;
		Statement stmt = null;
		ResultSet resSet = null;

		List<Page> pages = new ArrayList();

		try {
			conn = this.dataSource.getConnection();
			
			String sql = "SELECT language.id as lang_id, language.name as lang_name, language.acronym as lang_acronym,\n" + 
					"page.id as page_id, page.name as page_name, page.level_id as level_id, page.theme as page_theme,\n" + 
					"page.to_date as page_date, page_type.id as page_type_id, page_type.name as page_type_name,\n" + 
					" page_type.metadata as page_type_metadata, page_type.lang_id as page_type_lang_id\n" + 
					"FROM page\n" + 
					"INNER JOIN language ON page.lang_id=language.id\n" + 
					"INNER JOIN page_type ON page.page_type_id=page_type.id\n" + 
					"ORDER BY page_name;";
			
			stmt = conn.createStatement();
			resSet = stmt.executeQuery(sql);

			while (resSet.next()) {
				int langId = resSet.getInt("lang_id");
				String langName = resSet.getString("lang_name");
				String langAcronym = resSet.getString("lang_acronym");
				
				Language lang = new Language(langId, langName, langAcronym);
				
				int pageId = resSet.getInt("page_id");
				String pageName = resSet.getString("page_name");
				int levelId = resSet.getInt("level_id");
				String pageTheme = resSet.getString("page_theme");
			    Date date = resSet.getDate("page_date");
			    
			    int pageTypeId = resSet.getInt("page_type_id");
			    String pageTypeName = resSet.getString("page_type_name");
			    String pageMetadata = resSet.getString("page_type_metadata");
			    int pageTypeLangId = resSet.getInt("page_type_lang_id");
			    
			    PageType pageType = new PageType(pageTypeId, pageTypeName, pageMetadata, pageTypeLangId);

				Page page = new Page(pageId, lang, pageType, levelId, pageName, pageTheme, date);
				pages.add(page);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// close JDBC objects
			close(resSet, stmt, conn);
		}

		return pages;
	}

	public List<Language> getLanguages() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet resSet = null;

		List<Language> languages = new ArrayList();

		try {
			conn = this.dataSource.getConnection();

			String sql = "select * from language order by name";
			stmt = conn.createStatement();
			resSet = stmt.executeQuery(sql);

			while (resSet.next()) {
				int id = resSet.getInt("id");
				String name = resSet.getString("name");
				String acronym = resSet.getString("acronym");

				Language language = new Language(id, name, acronym);
				languages.add(language);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// close JDBC objects
			close(resSet, stmt, conn);
		}

		return languages;
	}

	public void deleteLanguage(String id) {
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = this.dataSource.getConnection();

			String sql = "delete from language where id=" + id;
			stmt = conn.prepareStatement(sql);

			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(null, stmt, conn);
		}
	}
	
	public void deletePage(String id) {
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = this.dataSource.getConnection();

			String sql = "delete from page where id=" + id;
			stmt = conn.prepareStatement(sql);

			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(null, stmt, conn);
		}
	}
	
	public void deletePageType(String id) {
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = this.dataSource.getConnection();

			String sql = "delete from page_type where id=" + id;
			stmt = conn.prepareStatement(sql);

			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(null, stmt, conn);
		}
	}


	public void addLanguage(Language language) {
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = this.dataSource.getConnection();

			String sql = "insert into language" + "(name, acronym)" + "values(?, ?)";
			stmt = conn.prepareStatement(sql);

			stmt.setString(1, language.getName());
			stmt.setString(2, language.getAcronym());

			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(null, stmt, conn);
		}
	}
	
	public void addPage(Page page) {
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = this.dataSource.getConnection();

			String sql = "insert into page" + "(id, page_type_id, lang_id, "
					+ "level_id, name, theme)" + "values(?, ?, ?, ?, ?, ?)";
			stmt = conn.prepareStatement(sql);

			stmt.setString(1, page.getName());
			stmt.setString(2, page.getPageType());

			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(null, stmt, conn);
		}
	}

	public Language loadLanguage(String id) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet resSet = null;
		Language language = null;

		try {
			conn = this.dataSource.getConnection();
			String sql = "select * from language where id =" + id;
			stmt = conn.prepareStatement(sql);
			resSet = stmt.executeQuery();

			while (resSet.next()) {
				String name = resSet.getString("name");
				String acronym = resSet.getString("acronym");

				language = new Language(Integer.parseInt(id), name, acronym);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(null, stmt, conn);
		}

		return language;
	}

	private void close(ResultSet resSet, Statement stmt, Connection conn) {
		try {
			if (resSet != null) {
				resSet.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			if (stmt != null) {
				stmt.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			if (conn != null) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void updateLanguage(Language language) {
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = this.dataSource.getConnection();

			String sql = "update language" + " set name=?, acronym=?" + " where id=?";
			stmt = conn.prepareStatement(sql);

			stmt.setString(1, language.getName());
			stmt.setString(2, language.getAcronym());
			stmt.setInt(3, language.getId());

			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(null, stmt, conn);
		}

	}

}
