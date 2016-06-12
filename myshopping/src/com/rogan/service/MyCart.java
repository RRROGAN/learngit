package com.rogan.service;

import java.util.*;
import java.util.HashMap;

import com.rogan.dao.Book;

public class MyCart {
	HashMap<String,Book> hm = new HashMap();
	
	//�����
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
	
	//ɾ����
	public void delBook(String id){
		hm.remove(id);
	}
	
	//�����飨���ڹ��ﳵ���¡�������
	public void update(String id, String nums){
		Book book = hm.get(id);
		book.setShoppingNum(Integer.parseInt(nums));
	}
	
	//���
	public void clear(){
		hm.clear();
	}
	
	//��ʾ���ﳵ�е�������Ʒ��Ϣ
	public ArrayList showMyCart(){
		ArrayList al = new ArrayList<Book>();
		//����HashMap
		Iterator iterator=hm.keySet().iterator();
		while(iterator.hasNext()){
			String id = (String)iterator.next();
			//ȡ��Book
			Book book = hm.get(id);
			al.add(book);
			
			
		}
		return al;
	}
	
	//���ظù��ﳵ���ܼ�
	public float getTotalPrice(){
		float totalPrice = 0.0f;
		//�õ��ܼ۸�
		ArrayList<Book> al = new ArrayList<Book>();
		Iterator iterator = hm.keySet().iterator();
		while(iterator.hasNext()){
			String bookId = (String)iterator.next();
			//ȡ������Ŷ�Ӧ��book
			Book book = hm.get(bookId);
			totalPrice += book.getPrice()*book.getShoppingNum();
		}
		return totalPrice;
		
	}
}
