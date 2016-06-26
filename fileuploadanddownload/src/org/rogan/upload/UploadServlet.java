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
		//创建工厂
		DiskFileItemFactory factory = new DiskFileItemFactory();
		File f = new File("e:\\Target");
		if(!f.exists()){
			f.mkdirs();
		}
		
		//设置文件的缓存路径
		factory.setRepository(f);
		//创建fileupload组件
		ServletFileUpload fileupload = new ServletFileUpload(factory);
		fileupload.setHeaderEncoding("gbk");
		//解析request
		try {
			List<FileItem> fileItems = fileupload.parseRequest(request);
			//遍历集合
			for(FileItem fileItem:fileItems){
				//判断是否为普通字段
				if(fileItem.isFormField()){
					//获得字段名和字段值
					String name = fileItem.getFieldName();
					String value = fileItem.getString("gbk");
					out.print("上传者：" + value + "<br/>");
				}else{
					//上传的文件路径
					String fileName = fileItem.getName();
					out.print("文件来源：" + fileName + "<br/>");
					//截取文件名
					fileName = fileName.substring(fileName.lastIndexOf("\\") +1);
					out.print("成功上传的文件：" + fileName+"<br>");
					//文件名需要唯一，为了防止文件名重复，在上传文件的名称前面加了前缀
					fileName = UUID.randomUUID().toString()+"_"+fileName;
					//在服务器创建同名文件
					String webPath = "/upload/";
					String filePath = getServletContext().getRealPath(webPath+fileName);
					//创建文件
					File file = new File(filePath);
					file.getParentFile().mkdirs();
					file.createNewFile();
					//获得上传文件流
					InputStream in = fileItem.getInputStream();
					//获得写入文件流
					OutputStream os = new FileOutputStream(file);
					//流的对拷
					byte[] buffer = new byte[1024];
					int len;
					while((len = in.read(buffer)) > 0){
						os.write(buffer,0,len);
						//流不能关，要一直循环
						//删除临时文件
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
