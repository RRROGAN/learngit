package cn.itcast.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class MyFilter02 implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		//用于拦截用户的请求，如果和当前过滤器的拦截路径匹配，该方法会被调用
				PrintWriter out = response.getWriter();
				out.write("MyFilter02 Before<br/>");
				//doFilter()方法把请求传递给下一个Filter
				chain.doFilter(request, response);
				out.write("<br/>MyFilter02 After<br/>");

	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

}
