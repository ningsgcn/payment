<%@page import="org.springframework.http.ResponseEntity"%>
<%@page import="org.springframework.web.client.RestTemplate"%>
<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%@ page import="java.net.URLEncoder"%>
<%@ page import="java.lang.*"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'queryconfirm.jsp' starting page</title>
    <script type="text/javascript" src="../statics/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="../statics/json2.js"></script>
  </head>
	<%
	String parter = String.valueOf(request.getAttribute("parter"));	//合作者ID
	String orderid = String.valueOf(request.getAttribute("orderid"));	//订单号
	String sign = String.valueOf(request.getAttribute("sign"));
	
	String url = "http://gateway2019.payzhf.net/Search.aspx";
	
	url += "?parter="+parter;
	url += "&orderid="+orderid;
	url += "&sign="+sign;
	RestTemplate rest=new RestTemplate();
	ResponseEntity<String> result=rest.getForEntity(url, String.class, new Object[1]);
	String jsonStr=result.getBody();
// 	response.sendRedirect(url);		
	%>	

  <body>
	<script type="text/javascript">
	var res=jQuery.parseJSON('<%=jsonStr%>');
	function checkRes(res){
		if ( res.opstate==0 ) {  
	  	  console.log('支付成功',res);
	    } else {  
	  	  console.log('失败',res);
	    } 
	}
	
	$(function(){
		checkRes(res);
	});
	</script>
  </body>
      
</html>