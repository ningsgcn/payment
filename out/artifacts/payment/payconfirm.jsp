<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%@ page import="java.net.URLEncoder"%>
<%@ page import="java.lang.*"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'payconfirm.jsp' starting page</title>
  </head>
	<%
	String parter = String.valueOf(request.getAttribute("p1_parter"));	//������ID
	String type = String.valueOf(request.getAttribute("p2_type"));//��ѡ���б��
	String value = String.valueOf(request.getAttribute("p3_value"));	//�������
	String orderid = String.valueOf(request.getAttribute("p4_orderid"));	//������
	String callbackurl = String.valueOf(request.getAttribute("p5_callbackurl"));
	String hrefbackurl = String.valueOf(request.getAttribute("p6_hrefbackurl"));
	String payerIp = String.valueOf(request.getAttribute("p7_payerIp"));
	String attach = String.valueOf(request.getAttribute("p8_attach"));	
	String sign = String.valueOf(request.getAttribute("p9_sign"));
	
	String url;
	if("994".equals(type)){
		//΢��֧���ύ
		url = "http://gateway2019.payzhf.net/scanpay.aspx";
	}else{
		//����֧���ύ
		url = "http://gateway2019.payzhf.net/chargebank.aspx";
	}	
	url += "?parter="+parter;
	url += "&type="+type;
	url += "&value="+value;
	url += "&orderid="+orderid;
	url += "&callbackurl="+callbackurl;
	url += "&hrefbackurl="+hrefbackurl;
	url += "&payerIp="+payerIp;
	url += "&attach="+attach;
	url += "&sign="+sign;
// 	url += "&agent=1024"; //���Է��ּӲ���"&agent=1024"����.	
	response.sendRedirect(url);
		
	%>
  <body>
<!-- �û��ĵ���Ҫ����jsp��ʹ��response.sendRedirect()�����ύ����, ���Է���ʹ��form���ύҲ����. -->
<!--     <form action="http://gateway2019.payzhf.net/chargebank.aspx" method="get"> -->
<%--     	<input type="hidden" name="parter" value="${p1_parter }"> --%>
<%--     	<input type="hidden" name="type" value="${p2_type }"> --%>
<%--     	<input type="hidden" name="value" value="${p3_value }"> --%>
<%--     	<input type="hidden" name="orderid" value="${p4_orderid }"> --%>
<%--     	<input type="hidden" name="callbackurl" value="${p5_callbackurl }"> --%>
<%--     	<input type="hidden" name="hrefbackurl" value="${p6_hrefbackurl }"> --%>
<%--     	<input type="hidden" name="payerIp" value="${p7_payerIp }"> --%>
<%--     	<input type="hidden" name="attach" value="${p8_attach }"> --%>
<%--     	<input type="hidden" name="sign" value="${p9_sign }"> --%>
<!--     	<input type="hidden" name="agent" value="1024"> -->
<!--     	<input type="submit" value="ȷ��֧��"> -->
<!--     </form>     -->
  </body>
</html>
