package com.rogan.util;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class TestTool {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SqlHelper tool = new SqlHelper();
		String sql = "select * from users where name=? and pwd=?";
		String[] paras = {"rogan","rogan"};
		try {
			ArrayList al = tool.query(sql, paras);
			if(al.size()==1){
				System.out.println("存在该用户");
				HashMap map = (HashMap)al.get(0);
				System.out.println(map.get("name"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
