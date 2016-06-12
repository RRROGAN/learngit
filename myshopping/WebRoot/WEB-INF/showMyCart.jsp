<%@ page language="java" import="java.util.*,com.rogan.dao.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'showMyCart.jsp' starting page</title>
      
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="css/comm.css">
	-->

  </head>
  
  <body>
   <h1>我的购物车</h1>
   <form action="/myshopping/ShoppingCLServlet?type=update" method="post">
   <table border="1" style="border-collapse: collapse;width:600px">
   <tr><td>BookId</td><td>书名</td><td>价格</td><td>出版社</td><td>数量</td><td>是否删除</td></tr>
   <%
   		ArrayList al = (ArrayList)request.getAttribute("bookList");
   		for(int i=0; i < al.size(); i++){
   			Book book = (Book)al.get(i);
   			%>
   			   <tr><td><%=book.getId() %><input type="hidden" name="id" value="<%=book.getId()%>"/></td>
   			   <td><%=book.getName() %></td>
   			   <td><%=book.getPrice() %></td>
   			   <td><%=book.getPublishHouse() %></td>
   			   <td><input type="text" name="num" value="<%=book.getShoppingNum() %>"/></td>
   			   <td><a href="/myshopping/ShoppingCLServlet?id=<%=book.getId()%>&type=del">删除</a></td></tr>
   			   <%
   		}
    %>
   <tr><td colspan="6"><input type="submit" value="update"></td></tr>
   <tr><td colspan="6">购物车的总价格：${totalPrice}元</td></tr>
   </table>
   <a href="/myshopping/GoHallUI">返回购物大厅 </a>
   <a href="/myshopping/GoMyOrder">提交订单</a>
   </form>
  </body>
</html>
