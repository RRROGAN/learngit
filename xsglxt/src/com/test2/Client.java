package com.test2;
import java.io.*;
public class Client {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EncriptFacede ef = new EncriptFacede();
		ef.fileEncript("g:/src.txt", "g:/des.txt");
	}

}

//��ϵͳ���ļ���ȡ��
class FileReader{
	
	public String fileReader(String fileNameSrc){
		System.out.println("��ȡ�ļ�����ȡ���ģ�");
		StringBuffer sb = new StringBuffer();
		try {
			
			FileInputStream fis = new FileInputStream(fileNameSrc);
			int data;
			while (( data = fis.read()) != -1) {
				sb = sb.append((char)data);
			}
			fis.close();
			
			System.out.println(sb.toString());
		} catch (FileNotFoundException e) {
			// TODO: handle exception
			System.out.println("�ļ�������");
		}catch(IOException e){
			System.out.println("�ļ���������");
		}
		return sb.toString();
	}
}

//��ϵͳ���ļ�д����
class FileWriter{
	public void fileWriter(String encriptStr,String fileNameDes){
		System.out.println("�������ģ�д���ļ�");
		try {
			FileOutputStream fos = new FileOutputStream(fileNameDes);
			fos.write(encriptStr.getBytes()); //�����ֽ�����
			fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("�ļ�������");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("�ļ���������");
			e.printStackTrace();
		}
		
	}
	
}

//��ϵͳ��������
class CipherMachine{
	public String encript(String plainText){
		System.out.println("���ݼ��ܣ�������ת��Ϊ����");  //��ʾ����
		String s="";
		for(int i = 0; i < plainText.length(); i++){
			String e = String.valueOf((plainText.charAt(i)%7));
			s+=e;
		}
		System.out.println(s);
		return s;
		
	}
	
}

//�����
class EncriptFacede{
	private FileReader fr;
	private FileWriter fw;
	private CipherMachine cm;
	
		public EncriptFacede(){		
		fr = new FileReader();
		fw = new FileWriter();
		cm = new CipherMachine();
		
	}
		
		public void fileEncript(String fileNameSrc,String fileNameDes){
			String plainText = fr.fileReader(fileNameSrc);
			String encriptStr = cm.encript(plainText);
			fw.fileWriter(encriptStr, fileNameDes);
		}
}