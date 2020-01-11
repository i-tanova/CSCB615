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
import com.tanovait.model.PageType;
import com.tanovait.util.Constants;
import com.tanovait.util.PageDBUtil;

/**
 * Servlet implementation class PageTypesController
 */
@WebServlet("/PageTypesControllerServlet")
public class PageTypesController extends HttpServlet {
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
			listPageTypes(request, response);
			break;
		}
		case Constants.COMMAND_ADD: {
			addPageType(request, response);
			break;
		}
		
		case Constants.COMMAND_DELETE: {
			deletePageType(request, response);
			break;
		}
		
		case Constants.COMMAND_UPDATE: {
			updatePageType(request, response);
			break;
		}
		
		case Constants.COMMAND_LOAD: {
			loadPageType(request, response);
			break;
		}


		default: {
			listPageTypes(request, response);
		}
		}
	}

	private void loadPageType(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		PageType pageType = pageDBUtil.loadPageType(id);
		
		request.setAttribute(Constants.PAGE_TYPE_ARGUMENT, pageType);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/update-page-type.jsp");
		try {
			dispatcher.forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void updatePageType(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String metadata = request.getParameter("metadata");
		
		pageDBUtil.updatePageType(new PageType(Integer.parseInt(id), name, metadata, 1));
		
		listPageTypes(request, response);
	}

	private void deletePageType(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		pageDBUtil.deletePageType(id);
		
		listPageTypes(request, response);
	}
	
	private void listPageTypes(HttpServletRequest request, HttpServletResponse response) {
		List<PageType> pageTypes = pageDBUtil.gePageTypes();
		request.setAttribute(Constants.PAGE_TYPES, pageTypes);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/page-types-list.jsp");
		try {
			dispatcher.forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void addPageType(HttpServletRequest request, HttpServletResponse response) {
		String name = request.getParameter("name");
		String metadata = request.getParameter("metadata");

		pageDBUtil.addPageType(new PageType(name, metadata, 1));

		listPageTypes(request, response);
	}


}
