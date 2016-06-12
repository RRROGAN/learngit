package com.rogan.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.rogan.dao.Users;
import com.rogan.util.SqlHelper;

public class UsersService {
	
	public boolean checkUsers(Users user){
		String sql = "select * from users where name=? and pwd=?";
		String[] paras = {user.getName(),user.getPwd()};
		//String[] paras = {"rogan","rogan"};
		System.out.println(user.getName()+"......");
		try {
			SqlHelper sqlHelper = new SqlHelper();
			//System.out.println("³É¹¦Ã´");
			ArrayList al = sqlHelper.query(sql, paras);
			if(al.size()==0){
				return false;
				}else{
					HashMap map = (HashMap)al.get(0);
					user.setEmail((String)map.get("email"));
					user.setId(Integer.parseInt(map.get("id").toString()));
					user.setGrade(Integer.parseInt(map.get("grade").toString()));
					user.setTel((String)map.get("tel"));
					return true;
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}finally{
			//new SqlHelper().close();
		}
		
		
	}
}
