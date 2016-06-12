<%@ page language="java" import="java.util.*,com.rogan.dao.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'showMyOrder.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript">
	<!-- 
		function goSubmitOrder(){
			window.location.href="/myshopping/SubmitOrderServlet";
		}
	//-->
	</script>

  </head>
  
  <body>
   <h1>我的订单</h1>
   <h2>我的个人信息</h2>
   <table border="1">
   	<tr><td colspan="2">用户个人信息</td></tr>
   	<tr><td>用户名</td><td><%=((Users)session.getAttribute("users")).getName() %></td></tr>
   	<tr><td>电子邮件</td><td><%=((Users)session.getAttribute("users")).getEmail() %></td></tr>
   	<tr><td>用户级别</td><td><%=((Users)session.getAttribute("users")).getGrade() %></td></tr>
   	
   </table>
   <hr/>
   <table border="1">
    <tr><td>BookId</td><td>书名</td><td>价格</td><td>数量</td></tr>
    <%
    	ArrayList al = (ArrayList)request.getAttribute("orderInfo");
    	//循环显示购物车的内容
    	for(int i=0;i<al.size();i++){
    	 	Book book = (Book)al.get(i);
    	%>
    <tr><td><%=book.getId() %></td><td><%=book.getName() %></td><td><%=book.getPrice() %></td><td><%=book.getShoppingNum() %></td></tr>
    	
    	<%
    	}
     %>
     <tr><td colspan="5">总价格：<%=request.getAttribute("totalPrice") %></td></tr>
   </table>
   <input type="button" onclick="goSubmitOrder()"  value="确认订单"/ >
  </body>
</html>
