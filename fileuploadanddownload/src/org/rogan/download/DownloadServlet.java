package org.rogan.download;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DownloadServlet extends HttpServlet {

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		//PrintWriter out = response.getWriter();
		//֪ͨ����������صķ�ʽ��
//		response.addHeader("Content-Type", "application/octet-stream");
//		response.addHeader("Content-Disposition", "attachment;filename=����.jpg");
//		//ͨ���ļ�����ȡ�ļ�
//		InputStream in = getServletContext().getResourceAsStream("/download/����.jpg");
//		//��ȡresponse����������
//		OutputStream os = response.getOutputStream();
//		byte[] buffer = new byte[1024];
//		int len;
//		while((len = in.read(buffer)) != -1){
//			os.write(buffer,0,len);
//		}
		
		//����������������
		//��þ���·�������ļ�����
		String path = getServletContext().getRealPath("/download/����.jpg");
		File file  = new File(path);
		//֪ͨ����������صķ�ʽ���ļ�
		response.addHeader("Content-Type", "application/octet-stream");
		response.addHeader("Content-Disposition", "attachment;filename="+URLEncoder.encode(file.getName(),"utf-8"));
		//ͨ���ļ������ȡ�ļ���������
		InputStream in = new FileInputStream(file);
		//��ȡresponse����������
		OutputStream os = response.getOutputStream();
		byte[] buffer = new byte[1024];
		int len;
		while((len=in.read(buffer))!=-1){
			os.write(buffer,0,len);
		}
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
