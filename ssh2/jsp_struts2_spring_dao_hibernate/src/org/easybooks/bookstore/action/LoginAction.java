package org.easybooks.bookstore.action;

import org.easybooks.bookstore.dao.IUserDAO;
import org.easybooks.bookstore.vo.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class LoginAction {
	private String username;
	private String password;
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	//处理用户请求的execute方法
	public String execute() throws Exception{
		boolean validated = false;
		ApplicationContext ct = new ClassPathXmlApplicationContext("/applicationContext.xml");
		IUserDAO userDAO = (IUserDAO)ct.getBean("userDAO");
		User u = userDAO.validateUser(username, password);
		if(u != null ){
			validated = true;
			
		}
		if(validated){
			return "success";
		}
		else{
			return "error";
		}
		
		
	}
	
}
