<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'clientlocale.jsp' starting page</title>
    
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
    	Locale perferlocale = request.getLocale();
    	out.println("客户端首选的本地信息为："+perferlocale);
     %><br/>
     <br/>客户端支持的所有本地信息列表，按优先级的降序排列：
     <br/>
     <%
     	Enumeration locales = request.getLocales();
      %>
      <li>本地信息&nbsp;&nbsp;本地信息的显示名称</li>
     <%
     	while(locales.hasMoreElements()){
     		Locale locale = (Locale)locales.nextElement();
     		  %>
     	<li>
     	<%=locale %>&nbsp;&nbsp;&nbsp;&nbsp;
     	<%=locale.getDisplayName() %>
     	</li>
     	<%
     	}
     	 %>
     	
     	
    
   
     
     
      </li>
  </body>
</html>
