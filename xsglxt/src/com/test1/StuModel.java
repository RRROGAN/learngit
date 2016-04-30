package com.test1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

/*
 * �����ҵ�һ��stuģ��
 * */
public class StuModel extends AbstractTableModel {

	PreparedStatement ps = null;
	Connection ct = null;
	ResultSet rs = null;
	//rowData������������ݣ�columNames�������
	Vector rowData, columnNames;
	
	public void init(String sql){
		if(sql.equals("")){
			sql = "select * from stu";
		}
		  columnNames = new Vector();
			//��������
			columnNames.add("ѧ��");
			columnNames.add("����");
			columnNames.add("�Ա�");
			columnNames.add("����");
			columnNames.add("����");
			columnNames.add("ϵ��");
			
			//rowData���Դ�Ŷ���
			rowData = new Vector();
			

			//1.��������
			try {
				Class.forName("com.mysql.jdbc.Driver");
				//2.�õ�����
				String url="jdbc:mysql://localhost:3306/user?user=root&password=root&useUnicode=true&characterEncoding=GBK" ;
				ct = DriverManager.getConnection(url);
				//3.Ԥ����
				ps = ct.prepareStatement(sql);
				//4.�õ������
				rs = ps.executeQuery();
				//5.��������
				while(rs.next()){
					Vector hang = new Vector();  //ֻ�ܷ�������
					
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
					//6.�ر�����
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
	//���캯������ʼ�����ǵ�����ģ��

	
	//�õ����ж�����
	@Override   //��Щ�����Զ�����
	public int getRowCount() {
		// TODO Auto-generated method stub
		return this.rowData.size();
	}

	//�õ����ж�����
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return this.columnNames.size();
	}

	//�õ�ĳ��ĳ�е�����
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
