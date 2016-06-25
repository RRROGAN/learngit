package cn.itcast.count;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class CountListener implements HttpSessionListener {

	private int count = 0;
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		// TODO Auto-generated method stub
		count++;  //session���󴴽�ʱcount������1
		ServletContext context = se.getSession().getServletContext();
		context.setAttribute("count", new Integer(count));
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		// TODO Auto-generated method stub
		count--; //session��������ʱcount������1
		ServletContext context = se.getSession().getServletContext();
		context.setAttribute("count", new Integer(count));
	}

}
