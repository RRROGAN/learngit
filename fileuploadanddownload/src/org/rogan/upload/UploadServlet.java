package org.rogan.upload;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.*;

public class UploadServlet extends HttpServlet {

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

		
			doPost(request, response);
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
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		//��������
		DiskFileItemFactory factory = new DiskFileItemFactory();
		File f = new File("e:\\Target");
		if(!f.exists()){
			f.mkdirs();
		}
		
		//�����ļ��Ļ���·��
		factory.setRepository(f);
		//����fileupload���
		ServletFileUpload fileupload = new ServletFileUpload(factory);
		fileupload.setHeaderEncoding("gbk");
		//����request
		try {
			List<FileItem> fileItems = fileupload.parseRequest(request);
			//��������
			for(FileItem fileItem:fileItems){
				//�ж��Ƿ�Ϊ��ͨ�ֶ�
				if(fileItem.isFormField()){
					//����ֶ������ֶ�ֵ
					String name = fileItem.getFieldName();
					String value = fileItem.getString("gbk");
					out.print("�ϴ��ߣ�" + value + "<br/>");
				}else{
					//�ϴ����ļ�·��
					String fileName = fileItem.getName();
					out.print("�ļ���Դ��" + fileName + "<br/>");
					//��ȡ�ļ���
					fileName = fileName.substring(fileName.lastIndexOf("\\") +1);
					out.print("�ɹ��ϴ����ļ���" + fileName+"<br>");
					//�ļ�����ҪΨһ��Ϊ�˷�ֹ�ļ����ظ������ϴ��ļ�������ǰ�����ǰ׺
					fileName = UUID.randomUUID().toString()+"_"+fileName;
					//�ڷ���������ͬ���ļ�
					String webPath = "/upload/";
					String filePath = getServletContext().getRealPath(webPath+fileName);
					//�����ļ�
					File file = new File(filePath);
					file.getParentFile().mkdirs();
					file.createNewFile();
					//����ϴ��ļ���
					InputStream in = fileItem.getInputStream();
					//���д���ļ���
					OutputStream os = new FileOutputStream(file);
					//���ĶԿ�
					byte[] buffer = new byte[1024];
					int len;
					while((len = in.read(buffer)) > 0){
						os.write(buffer,0,len);
						//�����ܹأ�Ҫһֱѭ��
						//ɾ����ʱ�ļ�
						fileItem.delete();
					}
				}
			}
			
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
