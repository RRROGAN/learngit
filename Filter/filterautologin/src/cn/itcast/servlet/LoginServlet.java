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
		//����û���������
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		//����û���������
		if("rogan".equals(username) && "rogan".equals(password)){
			//��¼�ɹ�
			//���û�״̬user�������session��
			User user = new User();
			user.setUsername(username);
			user.setPassword(password);
			request.getSession().setAttribute("user", user);
			//�����Զ���¼��cookie,�õ�radio����е�ʱ�䣬���Ϊ�գ���дcookie
			String autologin = request.getParameter("autologin");
			if(autologin != null){
				Cookie cookie = new Cookie("autologin",username+"-"+password);
				cookie.setMaxAge(Integer.parseInt(autologin));
				cookie.setPath(request.getContextPath());
				response.addCookie(cookie);
			}
			
			//��ת����ҳ
			response.sendRedirect(request.getContextPath()+"/index.jsp");
			
		}else{
			request.setAttribute("errorMsg", "�û������������");
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
