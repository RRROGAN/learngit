package org.easybooks.bookstore.action;

import org.easybooks.bookstore.service.IUserService;
import org.easybooks.bookstore.vo.User;

import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport {
	private User user;
	protected IUserService userService;  //由spring注入
	
	//用户注册，由Service层帮助完成
	public String register(){
		User u = new User(user.getName(),user.getPassword());
		u.setType("1");
		u.setUserId(10);
		if(userService.registerUser(u) != null){
			return "success";
		}
		return "error";
	}
	
	//处理用户请求的execute方法,引入业务层的根本目的就是隔离Action控制块与DAO层组件
	public String execute() throws Exception{
		boolean validated = false;
		User u = userService.validateUser(user.getName(), user.getPassword());
		
		if(u != null) validated = true;
		if(validated) return "success";
		else return "error";
		
		
	}
	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}
	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}
	/**
	 * @return the userService
	 */
	public IUserService getUserService() {
		return userService;
	}
	/**
	 * @param userService the userService to set
	 */
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
}
