package com.mhl.tools;

import java.sql.ResultSet;

public class PageModel {
	//��ǰҳ
	private int pageNow;
	//��ҳ��
	private int pageCount;
	//ҳ���С
	private int pageSize;
	//�ܼ�¼��
	private int rowCount;
	private SqlHelper sqlHelper;
	
	//��ʼ����ҳ���ĸ�����
	public PageModel(SqlHelper sqlHelper,int pageSize, String pageNow){
		String sql = "select * from user1";
		String paras[] = new String[0];
		ResultSet rs = sqlHelper.query(sql, paras);
		try{
		if(rs!=null && rs.next()){
			rs.last(); //��ָ���ƶ�������������һ��
			this.setRowCount(rs.getRow()); //�����ܼ�¼��
			this.pageSize = pageSize; //����ҳ���С
			this.setPageCount();  //������ҳ��					
			//this.pageNow = pageNow; //������pageCount
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
		
			
		} catch (NumberFormatException e) { //��֤�������������ʽ
			// TODO: handle exception
			this.pageNow = 1;
			e.printStackTrace();
		}
		if(this.pageNow <= 1) this.pageNow = 1; //ת��֮��С��1����1
		
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
