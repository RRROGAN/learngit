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
  <center><h3>用户登录</h3></center>
  <body style="text-align:center;">
  
    <form action="${pageContext.request.contextPath }/LoginServlet" method="post">
    <table border="1" width="600px" cellpadding="0" cellspacing="0" align="center"> 
    <tr>
    <td height="30" align="center">用户名：</td>
    <td>&nbsp;&nbsp;<input type="text" name="username"/>${errorMsg}</td>
    </tr>
    <tr>
    <td height="30" align="center">密&nbsp;码：</td>
    <td>&nbsp;&nbsp;<input type="password" name="password"/></td>
    </tr>
    
    <tr>
    <td height="35" align="center">自动登录时间</td>
    <td><input type="radio" name="autologin" value="${60*60*24*31}" />一个月
    <input type="radio" name="autologin" value="${60*60*24*31*3}" />三个月
    <input type="radio" name="autologin" value="${60*60*24*31*6}" />半年
    <input type="radio" name="autologin" value="${60*60*24*31*12}" />一年
    </td>
    </tr>
    <tr>
    <td height="30" colspan="2" align="center">
    <input type="submit" value="登录"/>&nbsp;&nbsp;&nbsp;&nbsp;
    <input type="reset" value="重置"/>
    </td>
    </tr>
    </table>
    </form>
  </body>
</html>
