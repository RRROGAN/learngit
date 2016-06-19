<%@ page language="java" import="java.util.*,org.easybooks.bookstore.factory.*,org.hibernate.*" pageEncoding="utf-8"%>
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
	  	//查询表中记录
	  	String sql = "from User u where u.name=? and u.password=?";
	  	Query query = HibernateSessionFactory.getSession().createQuery(sql);
	  	query.setParameter(0, usr);
	  	query.setParameter(1, pwd);
	  	List users = query.list();
	  	if(users.size() != 0){
	  		validated = true;
	  	}
	  	
	  	HibernateSessionFactory.closeSession();
	  	if(validated){
	  		%>
	  		<jsp:forward page="welcome.jsp"></jsp:forward>
	  <%
	  	}else{
	  	%>
	  		<jsp:forward page="error.jsp"></jsp:forward>
	  <%
	  	}
  %>
  </body>
</html>
