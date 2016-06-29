<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'login.jsp' starting page</title>
    
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
   <h1>用户登录</h1>
    <form action="login.action" method="post">
     <table>
     	<tr><td>姓名：<input type="text" name="user.name"/></td></tr>
    	<tr><td>密码：<input type="password" name="user.password"/></td></tr>
    	<tr><td><input type="submit" value="登录" /><input type="reset" value="重置"/></td></tr>
  	  <tr><td><a href="register.jsp">注册</a></td></tr>
     </table>
    </form>
  </body>
</html>
