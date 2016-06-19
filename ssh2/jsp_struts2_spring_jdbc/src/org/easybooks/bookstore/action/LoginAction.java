package org.easybooks.bookstore.action;

import java.sql.ResultSet;

import org.easybooks.bookstore.jdbc.MySQLConnBean;

import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport{
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
	
	//�����û������execute����
	public String execute() throws Exception{
		String usr = getUsername();
		String pwd = getPassword();
		boolean validated = false;
		MySQLConnBean MySqlBean = new MySQLConnBean();  //�������Ӷ���
		//��ѯuser���еļ�¼
		String sql = "select * from user";
		ResultSet rs = MySqlBean.executeQuery(sql);
		while(rs.next()){
			if(rs.getString("name").compareTo(usr)==0 && rs.getString("password").compareTo(pwd)==0){
	  			validated = true;  //��ʾΪtrue��ʾ��֤�ɹ�
		}
		
		
		
	}
		
		MySqlBean.close();
		if(validated){
			return "success";
		}else
		return "error";
}
}