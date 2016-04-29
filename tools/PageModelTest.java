package com.mhl.tools;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PageModelTest {

	public static void main(String[] args) throws SQLException {
		SqlHelper sqlHelper = new SqlHelper();
		PageModel pageHelper = new PageModel(sqlHelper,2,"4");
				
		//分页sql语句，不同的数据库分页语句也有 
		String sql2 = "select * from user1 order by id limit ?,?";
		
		String a = String.valueOf((pageHelper.getPageNow()-1 ) * pageHelper.getPageSize());
		String b = String.valueOf(pageHelper.getPageSize());
		String[] param = {a,b};
		ResultSet rs2 = sqlHelper.query(sql2, param);
		
		while(rs2.next()){
			
			int id = rs2.getInt("id");
			String name = rs2.getString("name");
			System.out.println(id + "," + name);
		}
		
		sqlHelper.close();
		
		
		
	}

}
