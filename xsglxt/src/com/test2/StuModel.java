package com.test2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

/*
 * 这是我的一个stu模型
 * */
public class StuModel extends AbstractTableModel {

	PreparedStatement ps = null;
	Connection ct = null;
	ResultSet rs = null;
	//rowData用来存放行数据，columNames存放列名
	Vector rowData, columnNames;
	
	public void init(String sql){
		if(sql.equals("")){
			sql = "select * from stu";
		}
		  columnNames = new Vector();
			//设置列名
			columnNames.add("学号");
			columnNames.add("名字");
			columnNames.add("性别");
			columnNames.add("年龄");
			columnNames.add("籍贯");
			columnNames.add("系别");
			
			//rowData可以存放多行
			rowData = new Vector();
			

			//1.加载驱动
			try {
				Class.forName("com.mysql.jdbc.Driver");
				//2.得到连接
				String url="jdbc:mysql://localhost:3306/user?user=root&password=root&useUnicode=true&characterEncoding=GBK" ;
				ct = DriverManager.getConnection(url);
				//3.预编译
				ps = ct.prepareStatement(sql);
				//4.得到结果集
				rs = ps.executeQuery();
				//5.处理结果集
				while(rs.next()){
					Vector hang = new Vector();  //只能放在里面
					
					hang.add(rs.getString(1));
					hang.add(rs.getString(2));
					hang.add(rs.getString(3));
					hang.add(rs.getInt(4));
					hang.add(rs.getString(5));
					hang.add(rs.getString(6));
					
					rowData.add(hang);
				}
				
				// System.out.println("youmeiyou");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				try {
					//6.关闭连接
					if (rs != null)
						rs.close();
					if (ps != null)
						ps.close();
					if (ct != null)
						ct.close();
				} catch (Exception e2) {
					// TODO: handle exception
				} 
				
			}
	}
	public StuModel(String sql){
//		if(sql.equals("")){
//			this.init("select * from stu");
//		}else{
//			this.init(sql);
//		}
		this.init(sql);
		
	}
	//构造函数，初始化我们的数据模型

	public boolean updateStu(String sql, String[] paras){
		boolean b = true;
		PreparedStatement ps = null;
		Connection ct = null;
		ResultSet rs = null;
		
		//1.加载驱动
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//2.得到连接
			String url="jdbc:mysql://localhost:3306/user?user=root&password=root&useUnicode=true&characterEncoding=GBK" ;
			ct = DriverManager.getConnection(url);
			//3.预编译
			
			ps = ct.prepareStatement(sql);
			for(int i=0; i< paras.length;i++){
				ps.setString(i+1, paras[i]);
			}

			if(ps.executeUpdate() != 1){
				b = false;
			}
			System.out.println(b);  //是true
										
	}catch(Exception ex){
		b = false;
		ex.printStackTrace();
	}finally{
		if(rs != null){
			try {
				rs.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		if(ps != null){
			try {
				ps.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if(ct != null){
			try {
				ct.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
		return b;
		
	}
	
	//得到共有多少列
	@Override   //这些函数自动调用
	public int getRowCount() {
		// TODO Auto-generated method stub
		return this.rowData.size();
	}

	//得到共有多少行
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return this.columnNames.size();
	}

	//得到某行某列的数据
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return ((Vector)this.rowData.get(rowIndex)).get(columnIndex);
	}

	/* (non-Javadoc)
	 * @see javax.swing.table.AbstractTableModel#getColumnName(int)
	 */
	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return (String) this.columnNames.get(column);
	}

}
