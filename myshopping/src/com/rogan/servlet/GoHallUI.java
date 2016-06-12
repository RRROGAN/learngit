package com.rogan.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rogan.dao.Users;
import com.rogan.service.BookService;
import com.rogan.service.MyCart;
import com.rogan.service.UsersService;

public class GoHallUI extends HttpServlet {

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

			doPost(request, response);
		
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		
		//判断该用户是否已经登录，如果已经登录，直接跳转到购物大厅
		if(request.getSession().getAttribute("users")!=null){
			BookService bookService = new BookService();
			ArrayList al = bookService.getAllBook();
			request.setAttribute("book", al);
			request.getRequestDispatcher("/WEB-INF/hall.jsp").forward(request, response);
			return;
		}
		//得到从页面传递过来的用户名和密码
		String name = request.getParameter("name");
		String passwd = request.getParameter("passwd");
		//创建一个Users对象
		Users users = new Users();
		users.setName(name);
		users.setPwd(passwd);
		
		UsersService us = new UsersService();
		//System.out.println("hello");
		if(us.checkUsers(users)){
			//合法
			MyCart myCart = new MyCart();
			request.getSession().setAttribute("myCart", myCart);
			request.getSession().setAttribute("users", users);
			BookService bookService = new BookService();
			ArrayList al = bookService.getAllBook();
			request.setAttribute("book", al);
			request.getRequestDispatcher("/WEB-INF/hall.jsp").forward(request, response);
		}else{
			request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
		}
		
		out.close();
	}

}
