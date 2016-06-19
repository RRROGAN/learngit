<%@ page language="java" import="java.util.*,java.sql.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<jsp:useBean id="MySqlBean" scope="page" class="org.easybooks.bookstore.jdbc.MySQLConnBean" />

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
	  	//查询user表中的记录
	  	String sql = "select * from user";
	  	ResultSet rs = MySqlBean.executeQuery(sql);
	  	while(rs.next()){
	  		if(rs.getString("name").compareTo(usr)==0 && rs.getString("password").compareTo(pwd)==0){
	  			validated = true;  //标示为true标示验证成功
	  		}
	  	}
	  	
	  	MySqlBean.close();
	  	
	  	if(validated){
	  		//验证成功跳转到welcome.jsp
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
