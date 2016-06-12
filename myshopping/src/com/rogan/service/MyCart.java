package com.rogan.service;

import java.util.*;
import java.util.HashMap;

import com.rogan.dao.Book;

public class MyCart {
	HashMap<String,Book> hm = new HashMap();
	
	//添加书
	public void addBook(String id,Book book){
		if(hm.containsKey(id)){
			book = hm.get(id);
			int shoppingNum = book.getShoppingNum();
			book.setShoppingNum(shoppingNum+1);
			hm.put(id,book);
		}else{
			book.setShoppingNum(1);
		hm.put(id, book);
		}
	}
	
	//删除书
	public void delBook(String id){
		hm.remove(id);
	}
	
	//更新书（对于购物车更新。。。）
	public void update(String id, String nums){
		Book book = hm.get(id);
		book.setShoppingNum(Integer.parseInt(nums));
	}
	
	//清空
	public void clear(){
		hm.clear();
	}
	
	//显示购物车中的所有商品信息
	public ArrayList showMyCart(){
		ArrayList al = new ArrayList<Book>();
		//遍历HashMap
		Iterator iterator=hm.keySet().iterator();
		while(iterator.hasNext()){
			String id = (String)iterator.next();
			//取出Book
			Book book = hm.get(id);
			al.add(book);
			
			
		}
		return al;
	}
	
	//返回该购物车的总价
	public float getTotalPrice(){
		float totalPrice = 0.0f;
		//得到总价格
		ArrayList<Book> al = new ArrayList<Book>();
		Iterator iterator = hm.keySet().iterator();
		while(iterator.hasNext()){
			String bookId = (String)iterator.next();
			//取出该书号对应的book
			Book book = hm.get(bookId);
			totalPrice += book.getPrice()*book.getShoppingNum();
		}
		return totalPrice;
		
	}
}
