package org.easybooks.bookstore.dao.impl;

import java.util.List;

import org.easybooks.bookstore.dao.BaseDAO;
import org.easybooks.bookstore.dao.IUserDAO;
import org.easybooks.bookstore.vo.User;
import org.hibernate.Query;
import org.hibernate.Session;

public class UserDAO extends BaseDAO implements IUserDAO {

	@Override
	public User validateUser(String username, String password) {
		// TODO Auto-generated method stub
		String sql = "from User u where u.name=? and u.password=?";
		Session session = getSession();
		Query query = session.createQuery(sql);
		query.setParameter(0, username);
		query.setParameter(1, password);
		
		List users = query.list();
		if(users.size() != 0){
			User user = (User)users.get(0);
			return user;
		}
		session.close();
		
	
	return null;
	}

}
