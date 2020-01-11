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

import com.tanovait.model.Page;
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


	private void loadPage(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}


	private void updatePage(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}


	private void deletePage(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}


	private void addPage(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
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
