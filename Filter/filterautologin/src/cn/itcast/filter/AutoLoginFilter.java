package cn.itcast.filter;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import cn.itcast.vo.User;

public class AutoLoginFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		//ServletRequest类中没有getCookies()方法，所以要转换
		HttpServletRequest request = (HttpServletRequest)arg0;
		//获取名为autologin的cookie
		Cookie[] cookies = request.getCookies();
		String autologin = null;
		//cookies != null必须加上，不然会报空指针异常
		for(int i=0; cookies != null && i<cookies.length;i++){
			if("autologin".equals(cookies[i].getName())){
				//找到了指定的cookie
				autologin = cookies[i].getValue();
				break;
			}
		}
		
		//自动登录
		if(autologin != null){
			//做自动登录
			String[] parts = autologin.split("-");
			String username = parts[0];
			String password = parts[1];
			//检查用户名和密码
			if("rogan".equals(username) && "rogan".equals(password)){
				//登录成功，将用户状态user对象存入session域中
				User user = new User();
				user.setUsername(username);
				user.setPassword(password);
				request.getSession().setAttribute("user", user);
			}
		}
		//放行
		chain.doFilter(request,response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
