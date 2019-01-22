<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'pay.jsp' starting page</title>
  </head>
  
  <body>
    <form action="${pageContext.request.contextPath }/bank.jsp" method="post">
    	 您的订单是:<input type="text" name="orderid" value="<%=System.currentTimeMillis()%>">
    	订单的金额是:<input type="text" name="money" value="1">
    	<input type="submit" value="支付">
    </form>
  </body>
</html>
