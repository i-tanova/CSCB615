package com.tanovait.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class Test
 */
@WebServlet("/Test")
public class Test extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Resource(name="jdbc/PagesDB")
	private DataSource dataSource;
  

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Get print writer
				PrintWriter pw = response.getWriter();
				response.setContentType("text/plain");
				
				//Get connection to DB
				Connection conn = null;
				Statement stm = null;
				ResultSet resSet = null;
				
				try {
					conn = dataSource.getConnection();
					String sql = "select * from language";
					stm = conn.createStatement();
					resSet = stm.executeQuery(sql);
					while(resSet.next()) {
						String email = resSet.getString("name");
						pw.println(email);
					}
					
				}catch(Exception e) {
					e.printStackTrace();
				}
	}
}
