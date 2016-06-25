package cn.itcast.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.vo.User;

public class LoginServlet extends HttpServlet {

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
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		//获得用户名和密码
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		//检查用户名和密码
		if("rogan".equals(username) && "rogan".equals(password)){
			//登录成功
			//将用户状态user对象存入session域
			User user = new User();
			user.setUsername(username);
			user.setPassword(password);
			request.getSession().setAttribute("user", user);
			//发动自动登录的cookie,得到radio组件中的时间，如果为空，则不写cookie
			String autologin = request.getParameter("autologin");
			if(autologin != null){
				Cookie cookie = new Cookie("autologin",username+"-"+password);
				cookie.setMaxAge(Integer.parseInt(autologin));
				cookie.setPath(request.getContextPath());
				response.addCookie(cookie);
			}
			
			//跳转至首页
			response.sendRedirect(request.getContextPath()+"/index.jsp");
			
		}else{
			request.setAttribute("errorMsg", "用户名或密码错误");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
		
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

		doGet(request, response);
	}

}
