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
		//ServletRequest����û��getCookies()����������Ҫת��
		HttpServletRequest request = (HttpServletRequest)arg0;
		//��ȡ��Ϊautologin��cookie
		Cookie[] cookies = request.getCookies();
		String autologin = null;
		//cookies != null������ϣ���Ȼ�ᱨ��ָ���쳣
		for(int i=0; cookies != null && i<cookies.length;i++){
			if("autologin".equals(cookies[i].getName())){
				//�ҵ���ָ����cookie
				autologin = cookies[i].getValue();
				break;
			}
		}
		
		//�Զ���¼
		if(autologin != null){
			//���Զ���¼
			String[] parts = autologin.split("-");
			String username = parts[0];
			String password = parts[1];
			//����û���������
			if("rogan".equals(username) && "rogan".equals(password)){
				//��¼�ɹ������û�״̬user�������session����
				User user = new User();
				user.setUsername(username);
				user.setPassword(password);
				request.getSession().setAttribute("user", user);
			}
		}
		//����
		chain.doFilter(request,response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
