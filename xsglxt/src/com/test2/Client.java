package com.test2;
import java.io.*;
public class Client {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EncriptFacede ef = new EncriptFacede();
		ef.fileEncript("g:/src.txt", "g:/des.txt");
	}

}

//子系统：文件读取类
class FileReader{
	
	public String fileReader(String fileNameSrc){
		System.out.println("读取文件，获取明文：");
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
			System.out.println("文件不存在");
		}catch(IOException e){
			System.out.println("文件操作错误");
		}
		return sb.toString();
	}
}

//子系统：文件写入类
class FileWriter{
	public void fileWriter(String encriptStr,String fileNameDes){
		System.out.println("保存密文，写入文件");
		try {
			FileOutputStream fos = new FileOutputStream(fileNameDes);
			fos.write(encriptStr.getBytes()); //返回字节数组
			fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("文件不存在");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("文件操作错误");
			e.printStackTrace();
		}
		
	}
	
}

//子系统：加密类
class CipherMachine{
	public String encript(String plainText){
		System.out.println("数据加密，将明文转换为密文");  //提示作用
		String s="";
		for(int i = 0; i < plainText.length(); i++){
			String e = String.valueOf((plainText.charAt(i)%7));
			s+=e;
		}
		System.out.println(s);
		return s;
		
	}
	
}

//外观类
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