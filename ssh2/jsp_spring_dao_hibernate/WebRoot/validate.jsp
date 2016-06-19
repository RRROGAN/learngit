<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="org.springframework.context.*,org.springframework.context.support.*,
		org.easybooks.bookstore.dao.*,org.easybooks.bookstore.dao.impl.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'validate.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <%
    	String usr = request.getParameter("username");
    	String pwd = request.getParameter("password");
    	boolean validated = false;
    	ApplicationContext context = new ClassPathXmlApplicationContext("/applicationContext.xml");
    	IUserDAO userDAO = (IUserDAO)context.getBean("userDAO");
    	//直接使用持久层封装好了的验证功能
    	if(userDAO.validateUser(usr,pwd) != null){
    		validated = true;
    	}
    	
    	if(validated){
    		//验证成功
    		%>
    		<jsp:forward page="welcome.jsp"></jsp:forward>
    	<%
    	}
    	else{
    	//验证失败
    	%>
    		<jsp:forward page="error.jsp" />
   	<%
    		
    	}
     %>
  </body>
</html>
