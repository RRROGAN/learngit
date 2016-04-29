package com.mhl.tools;

import java.sql.ResultSet;

public class PageModel {
	//当前页
	private int pageNow;
	//总页数
	private int pageCount;
	//页面大小
	private int pageSize;
	//总记录数
	private int rowCount;
	private SqlHelper sqlHelper;
	
	//初始化分页的四个变量
	public PageModel(SqlHelper sqlHelper,int pageSize, String pageNow){
		String sql = "select * from user1";
		String paras[] = new String[0];
		ResultSet rs = sqlHelper.query(sql, paras);
		try{
		if(rs!=null && rs.next()){
			rs.last(); //将指针移动到结果集的最后一行
			this.setRowCount(rs.getRow()); //设置总记录数
			this.pageSize = pageSize; //设置页面大小
			this.setPageCount();  //设置总页数					
			//this.pageNow = pageNow; //先设置pageCount
			this.setPageNow(pageNow);
			//System.out.println(pageHelper.getRowCount());
			
		}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public int getPageNow() {
		return pageNow;
	}
	
	public void setPageNow(String pageNow) {
		if(pageNow == null || pageNow.equals("")){
			pageNow="1";  
		}
		try {
			this.pageNow = Integer.parseInt(pageNow);
		
			
		} catch (NumberFormatException e) { //保证输入的是数字形式
			// TODO: handle exception
			this.pageNow = 1;
			e.printStackTrace();
		}
		if(this.pageNow <= 1) this.pageNow = 1; //转换之后，小于1，置1
		
		if(this.pageNow > pageCount) this.pageNow = pageCount;
		
		
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount() {
		pageCount = (rowCount % pageSize == 0)?(rowCount/pageSize):(rowCount/pageSize + 1);
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getRowCount() {
		return rowCount;
	}
	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}
	
	
	
}
