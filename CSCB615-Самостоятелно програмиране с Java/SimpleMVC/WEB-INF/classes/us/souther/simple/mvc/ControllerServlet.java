package us.souther.simple.mvc;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class ControllerServlet extends HttpServlet{
    
    public void doPost(HttpServletRequest  request, 
                       HttpServletResponse response) 
                  throws ServletException, IOException{

        ModelBean modelBean = new ModelBean();

        modelBean.setFirstName(request.getParameter("first_name")); 
        modelBean.setLastName( request.getParameter("last_name")); 
        modelBean.setEmail(    request.getParameter("email")); 
        modelBean.setPhone(    request.getParameter("phone")); 

        request.setAttribute("modelBean", modelBean);

        getServletContext()
	    .getRequestDispatcher("/view.jsp").forward(request, response);
    }

    public void doGet(HttpServletRequest  request, 
                      HttpServletResponse response) 
                  throws ServletException, IOException{

        getServletContext()
	    .getRequestDispatcher("/view.jsp").forward(request, response);
    }
}
