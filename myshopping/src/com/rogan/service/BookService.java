package com.rogan.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.rogan.dao.Book;
import com.rogan.util.SqlHelper;

public class BookService {
	//得到所有的书籍信息
	public ArrayList getAllBook(){
		String sql = "select * from book where 1=?";
		String[] paras = {"1"};
		SqlHelper sqlHelper = new SqlHelper();
		ArrayList<Book> newAl = new ArrayList();
		try {
			ArrayList al = sqlHelper.query(sql,paras);
			for(int i=0; i < al.size(); i++){
				Map map =(HashMap)al.get(i);
				Book book = new Book();
				book.setId(Integer.parseInt(map.get("id").toString()));
				book.setName(map.get("name").toString());
				book.setAuthor(map.get("author").toString());
				book.setPublishHouse(map.get("publishHouse").toString());
				book.setPrice(Float.parseFloat(map.get("price").toString()));
				book.setBookNums(Integer.parseInt(map.get("nums").toString()));
				newAl.add(book);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return newAl;
		
		
	}
	
	//根据书的编号，返回一个Book
	public Book getBookById(String id) throws SQLException{
		Book book = new Book();
		String sql = "select * from book where id=?";
		String[] paras = {id};
		SqlHelper sqlHelper = new SqlHelper();
		ArrayList al = sqlHelper.query(sql, paras);
		if(al.size()==1){
			HashMap map = (HashMap)al.get(0);
			book.setId(Integer.parseInt(map.get("id").toString()));
			book.setName(map.get("name").toString());
			book.setAuthor(map.get("author").toString());
			book.setPublishHouse(map.get("publishHouse").toString());
			book.setPrice(Float.parseFloat(map.get("price").toString()));
			book.setBookNums(Integer.parseInt(map.get("nums").toString()));
			
		}
		return book;
		
	}
}
