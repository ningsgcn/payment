<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'pay.jsp' starting page</title>
  </head>
  
  <body>
    <form action="${pageContext.request.contextPath }/servlet/QueryRequestServlet" method="post">
    	 要查询的订单是:<input type="text" name="orderid" value="">
    	<input type="submit" value="查询">
    </form>
  </body>
</html>
