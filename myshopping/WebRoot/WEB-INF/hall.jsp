<%@ page language="java" import="java.util.*,com.rogan.dao.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>hall jsp</title>
    
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
   <h1>欢迎光临购物大厅</h1>
   <table border=1>
   <tr><td>BookId</td><td>书名</td><td>出版社</td><td>价格</td><td>点击购买</td></tr>
   <%
   		ArrayList al = (ArrayList)request.getAttribute("book");
   		for(int i=0; i< al.size();i++){
   			Book book = new Book();
   			book = (Book)al.get(i);
   			%>
   			 <tr><td><%=book.getId() %></td><td><%=book.getName() %></td><td><%=book.getPublishHouse() %></td><td><%=book.getPrice() %></td><td><a href="/myshopping/ShoppingCLServlet?id=<%=book.getId() %>&type=add">购买</a></td></tr>
   			 <%
   		}
    %>
   
   <tr><td colspan="6"><input type="button" value="查看购物车"/></td></tr>
   </table>
   <a href="/myshopping">返回重新登录</a>
   
    </body>
</html>
