package com.rogan.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rogan.dao.Book;
import com.rogan.service.BookService;
import com.rogan.service.MyCart;

public class ShoppingCLServlet extends HttpServlet {

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
		PrintWriter out = response.getWriter();  //����λ��%%%%%%%%%%%%%%%%       
		String type = request.getParameter("type");
		if(type.equals("add")){
			//�����û��빺�����Ʒid
			String id = request.getParameter("id");
			BookService bookService = new BookService();
			try {
				Book book = bookService.getBookById(id);
				MyCart myCart = (MyCart) request.getSession().getAttribute("myCart");
				myCart.addBook(id, bookService.getBookById(id));
				request.setAttribute("bookList", myCart.showMyCart());
				request.setAttribute("totalPrice", myCart.getTotalPrice());
				request.getRequestDispatcher("/WEB-INF/showMyCart.jsp").forward(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(type.equals("del")){
			String id = request.getParameter("id");
			MyCart myCart = (MyCart) request.getSession().getAttribute("myCart");
			myCart.delBook(id);
			//����Ʒ��Ϣ�Ż�myCart��
			request.setAttribute("bookList", myCart.showMyCart());
			request.setAttribute("totalPrice", myCart.getTotalPrice());
			request.getRequestDispatcher("/WEB-INF/showMyCart.jsp").forward(request,response);

		}else if(type.equals("update")){
			System.out.println("Ӵ��update");
			//�õ��û�ϣ�����µ���ź�����
			String[] id= request.getParameterValues("id");
			String[] nums = request.getParameterValues("num");
			MyCart myCart = (MyCart) request.getSession().getAttribute("myCart");

			for(int i=0;i < id.length;i++){
				//System.out.println(id[i] + ":" + nums[i]);
				myCart.update(id[i], nums[i]);
			}
			request.setAttribute("bookList", myCart.showMyCart());
			request.setAttribute("totalPrice", myCart.getTotalPrice());
			request.getRequestDispatcher("/WEB-INF/showMyCart.jsp").forward(request,response);

			
		}
		//out.close();
	}

}
