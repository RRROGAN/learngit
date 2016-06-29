package org.easybooks.bookstore.service.impl;

import org.easybooks.bookstore.dao.IUserDAO;
import org.easybooks.bookstore.service.IUserService;
import org.easybooks.bookstore.vo.User;

public class UserService implements IUserService {
	private IUserDAO userDAO;
	@Override
	public User validateUser(String username, String password) {
		// TODO Auto-generated method stu
		return userDAO.validateUser(username, password);
	}

	@Override
	public User registerUser(User user) {
		// TODO Auto-generated method stub
		userDAO.saveUser(user);  //把注册的信息写入数据
		return userDAO.validateUser(user.getName(), user.getPassword());
	}

	/**
	 * @return the userDAO
	 */
	public IUserDAO getUserDAO() {
		return userDAO;
	}

	/**
	 * @param userDAO the userDAO to set
	 */
	public void setUserDAO(IUserDAO userDAO) {
		this.userDAO = userDAO;
	}

}
