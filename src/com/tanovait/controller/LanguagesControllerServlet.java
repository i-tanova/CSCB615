package com.tanovait.controller;

import java.io.IOException;
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
import com.tanovait.util.Constants;
import com.tanovait.util.PageDBUtil;

/**
 * Servlet implementation class LanguagesServlet
 */
@WebServlet("/LanguagesControllerServlet")
public class LanguagesControllerServlet extends HttpServlet {
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

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String command = request.getParameter(Constants.COMMAND_PARAM);
		if (command == null) {
			command = Constants.COMMAND_LIST;
		}
		switch (command) {
		case Constants.COMMAND_LIST: {
			listLanguages(request, response);
			break;
		}
		case Constants.COMMAND_ADD: {
			addLanguage(request, response);
			break;
		}
		
		case Constants.COMMAND_DELETE: {
			deleteLanguage(request, response);
			break;
		}
		
		case Constants.COMMAND_UPDATE: {
			updateLanguage(request, response);
			break;
		}
		
		case Constants.COMMAND_LOAD: {
			loadLanguage(request, response);
			break;
		}


		default: {
			listLanguages(request, response);
		}
		}
	}

	private void loadLanguage(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		Language lang = pageDBUtil.loadLanguage(id);
		
		request.setAttribute(Constants.LANGUAGE_ARGUMENT, lang);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/update-language.jsp");
		try {
			dispatcher.forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void updateLanguage(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String acronym = request.getParameter("acronym");
		
		pageDBUtil.updateLanguage(new Language(Integer.parseInt(id), name, acronym));
		
		listLanguages(request, response);
	}

	private void deleteLanguage(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		pageDBUtil.deleteLanguage(id);
		
		listLanguages(request, response);
	}
	
	private void listLanguages(HttpServletRequest request, HttpServletResponse response) {
		List<Language> languages = pageDBUtil.getLanguages();
		request.setAttribute(Constants.LANGUAGES_ARGUMENT, languages);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/languages-list.jsp");
		try {
			dispatcher.forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void addLanguage(HttpServletRequest request, HttpServletResponse response) {
		String name = request.getParameter("name");
		String acronym = request.getParameter("acronym");

		pageDBUtil.addLanguage(new Language(name, acronym));

		listLanguages(request, response);
	}

}
