package org.easybooks.bookstore.service;

import org.easybooks.bookstore.vo.User;

public interface IUserService {
	public User validateUser(String username, String password);
	public User registerUser(User user);
}
