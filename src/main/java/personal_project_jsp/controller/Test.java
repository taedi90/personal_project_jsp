package personal_project_jsp.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 모든 servlet 상위 클래스는 HttpServlet 이어야함
@WebServlet({"/test7", "/test5", "/test6"})
public class Test extends HttpServlet {
   private static final long serialVersionUID = 1L;
       

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   System.out.println("test");
	   response.setContentType("text/html");
	   response.setCharacterEncoding("UTF-8");
	   PrintWriter out = response.getWriter();
	   out.print("Hello world!");
	   out.close();
   }

   
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

      
      
   }

}