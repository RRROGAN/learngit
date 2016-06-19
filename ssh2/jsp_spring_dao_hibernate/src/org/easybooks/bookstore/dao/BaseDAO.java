package org.easybooks.bookstore.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class BaseDAO {
	
	private SessionFactory sessionFactory;

	/**
	 * @return the sessionFactory
	 */
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	/**
	 * @param sessionFactory the sessionFactory to set
	 */
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public Session getSession(){
		Session session = sessionFactory.openSession();
		return session;
	}
}
