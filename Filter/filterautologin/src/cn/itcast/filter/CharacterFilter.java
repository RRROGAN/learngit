package cn.itcast.filter;
/*
 * CharacterFilter类用于拦截用户请求，实现统一全战编码的功能。但是，由于请求方式post和get解决乱码的方式不同
 * 为此，可以通过HttpServletRequestWrapper类对HttpServletRequest进行包装，，通过重写getParameter()
 * 的方式来设置get方式提交参数的编码。
 * */
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

public class CharacterFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		//拦截所有请求，解决全战中文乱码
		//指定request和response的编码
		request.setCharacterEncoding("utf-8"); //只对消息体有效
		response.setContentType("text/html;charset=utf-8");
		//在放行时，应该给目标资源一个request对象，让目标资源调用
		CharacterRequest characterRequest = new CharacterRequest(request);
		chain.doFilter(characterRequest, response);
		
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

}

//针对request对象进行包装
//继承默认包装类HttpServletRequestWrapper
class CharacterRequest extends HttpServletRequestWrapper{

	public CharacterRequest(HttpServletRequest request) {
		super(request);
		// TODO Auto-generated constructor stub
	}
	//子类继承父类一定会复写一些方法，此处用于重写getParameter()方法
	public String getParameter(String name){
		//调用被包装对象getParameter()方法，获得请求参数
		String value = super.getParameter(name);
		if(value==null){
			return null;
		}
		//判断请求方式
		String method = super.getMethod();
		if("get".equalsIgnoreCase(method)){
			try {
				value = new String(value.getBytes("iso-8859-1"),"utf-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//解决乱码后返回结果
		return value;
	}
	
}
