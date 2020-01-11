package com.tanovait.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.tanovait.model.Language;
import com.tanovait.model.Page;
import com.tanovait.model.PageType;
import com.tanovait.util.Constants;
import com.tanovait.util.PageDBUtil;

/**
 * Servlet implementation class PagesControllerServlet
 */
@WebServlet("/PagesControllerServlet")
public class PagesControllerServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	@Resource(name = "jdbc/PagesDB")
	private DataSource dataSource;
	PageDBUtil pageDBUtil;

	@Override
	public void init() throws ServletException {
		super.init();

		try {
			pageDBUtil = new PageDBUtil(dataSource);
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}
      

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String command = request.getParameter(Constants.COMMAND_PARAM);
		if (command == null) {
			command = Constants.COMMAND_LIST;
		}
		switch (command) {
		case Constants.COMMAND_LIST: {
			listPages(request, response);
			break;
		}
		case Constants.COMMAND_GO_TO_ADD: {
			prepareAddPage(request, response);
			break;
		}
		case Constants.COMMAND_ADD: {
			addPage(request, response);
			break;
		}
		
		case Constants.COMMAND_DELETE: {
			deletePage(request, response);
			break;
		}
		
		case Constants.COMMAND_UPDATE: {
			updatePage(request, response);
			break;
		}
		
		case Constants.COMMAND_LOAD: {
			loadPage(request, response);
			break;
		}

		default: {
			listPages(request, response);
		}
		}
	}

	private void prepareAddPage(HttpServletRequest request, HttpServletResponse response) {
		List pageTypes = pageDBUtil.gePageTypes();
		List languages = pageDBUtil.getLanguages();
		
		request.setAttribute(Constants.PAGE_TYPES, pageTypes);
		request.setAttribute(Constants.LANGUAGES_ARGUMENT, languages);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/add-page.jsp");
		try {
			dispatcher.forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	private void loadPage(HttpServletRequest request, HttpServletResponse response) {
	    String id = request.getParameter("id");
	    
	    Page page = pageDBUtil.loadPage(id);
	    List<Language> languages = pageDBUtil.getLanguages();
		List<PageType> pageTypes = pageDBUtil.gePageTypes();
		
	    request.setAttribute(Constants.LANGUAGES_ARGUMENT, languages);
	    request.setAttribute(Constants.PAGE_TYPES, pageTypes);
	    request.setAttribute(Constants.PAGE_ARGUMENT, page);
	    
	    RequestDispatcher dispatcher = request.getRequestDispatcher("/update-page.jsp");
		try {
			dispatcher.forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	private void updatePage(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String theme = request.getParameter("theme");
		String langId = request.getParameter("language");
		String pageTypeId = request.getParameter("pageType");
		
		Language lang = pageDBUtil.loadLanguage(langId);
		PageType pageType = pageDBUtil.loadPageType(pageTypeId);
		
        pageDBUtil.updatePage(new Page(Integer.parseInt(id), lang, pageType, 1, name, theme));

		listPages(request, response);
	}


	private void deletePage(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		pageDBUtil.deletePage(id);
		
		listPages(request, response);
	}


	private void addPage(HttpServletRequest request, HttpServletResponse response) {
		@SuppressWarnings("unused")
		String name = request.getParameter("name");
		String theme = request.getParameter("theme");
		String langId = request.getParameter("language");
		String pageTypeId = request.getParameter("pageType");
		
		Language lang = pageDBUtil.loadLanguage(langId);
		PageType pageType = pageDBUtil.loadPageType(pageTypeId);
		
        pageDBUtil.addPage(new Page(lang, pageType, 1, name, theme));

		listPages(request, response);
	}


	private void listPages(HttpServletRequest request, HttpServletResponse response) {
		List<Page> pages = pageDBUtil.getPages();
		request.setAttribute(Constants.PAGES_ARGUMENT, pages);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/pages-list.jsp");
		try {
			dispatcher.forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
