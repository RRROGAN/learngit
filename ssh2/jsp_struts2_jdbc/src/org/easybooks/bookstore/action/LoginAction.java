package org.easybooks.bookstore.action;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.easybooks.bookstore.jdbc.MySQLConnBean;

import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport {
	
	private User user;

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
	
	public String execute() throws SQLException{
		boolean validated = false;
		MySQLConnBean MySqlBean = new MySQLConnBean();  //创建连接对象
		//查询user表中的记录
		String sql = "select * from user";
		ResultSet rs = MySqlBean.executeQuery(sql);
		while(rs.next()){
			if(rs.getString("name").compareTo(user.getUsername())==0 && rs.getString("password").compareTo(user.getPassword())==0){
	  			validated = true;  //标示为true标示验证成功
		}
		
		
		
	}
		MySqlBean.close();
		if(validated){
			return "success";
		}else
		return "error";
	

}
}
	
