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
		//���������û�����������͵�ǰ������������·��ƥ�䣬�÷����ᱻ����
				PrintWriter out = response.getWriter();
				out.write("MyFilter02 Before<br/>");
				//doFilter()���������󴫵ݸ���һ��Filter
				chain.doFilter(request, response);
				out.write("<br/>MyFilter02 After<br/>");

	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

}
