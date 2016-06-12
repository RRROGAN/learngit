package com.rogan.dao;

public class Book {
	 
	 int id;
	 String name;
	 String author;
	 String publishHouse;
	 float price;
	 int bookNums;
	 int shoppingNum=0;
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}
	/**
	 * @param author the author to set
	 */
	public void setAuthor(String author) {
		this.author = author;
	}
	/**
	 * @return the publishHouse
	 */
	public String getPublishHouse() {
		return publishHouse;
	}
	/**
	 * @param publishHouse the publishHouse to set
	 */
	public void setPublishHouse(String publishHouse) {
		this.publishHouse = publishHouse;
	}
	/**
	 * @return the price
	 */
	public float getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(float price) {
		this.price = price;
	}
	/**
	 * @return the bookNums
	 */
	public int getBookNums() {
		return bookNums;
	}
	/**
	 * @param bookNums the bookNums to set
	 */
	public void setBookNums(int bookNums) {
		this.bookNums = bookNums;
	}
	/**
	 * @return the shoppingNum
	 */
	public int getShoppingNum() {
		return shoppingNum;
	}
	/**
	 * @param shoppingNum the shoppingNum to set
	 */
	public void setShoppingNum(int shoppingNum) {
		this.shoppingNum = shoppingNum;
	}
	 
	 
}
