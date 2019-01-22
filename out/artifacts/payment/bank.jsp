<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>在线支付</title>
  </head>
  
  <body>
        <form action="${pageContext.request.contextPath }/servlet/PayRequestServlet" method="post">
      	<table width="60%">
			<input type="hidden" name="orderid" value="${param.orderid }">
    		<input type="hidden" name="money" value="${param.money }">
			<tr><td><br/></td></tr>
			<tr>
				<td>请您选择在线支付银行</td>
			</tr>
			<tr>
			  <td><INPUT TYPE="radio" NAME="pd_FrpId" value="970" id="970"><label for="970">招商银行 </label></td>
			  <td><INPUT TYPE="radio" NAME="pd_FrpId" value="967" id="967"><label for="967">工商银行 </label></td>
			  <td><INPUT TYPE="radio" NAME="pd_FrpId" value="964" id="964"><label for="964">农业银行 </label></td>
			  <td><INPUT TYPE="radio" NAME="pd_FrpId" value="965" id="965"><label for="965">建设银行 </label></td>
			</tr>
			<tr>
			  <td><INPUT TYPE="radio" NAME="pd_FrpId" value="980">中国民生银行总行</td>
			  <td><INPUT TYPE="radio" NAME="pd_FrpId" value="986" >光大银行 </td>
			  <td><INPUT TYPE="radio" NAME="pd_FrpId" value="981">交通银行</td>
			  <td><INPUT TYPE="radio" NAME="pd_FrpId" value="974">深圳发展银行</td>
			</tr>
			<tr>
			  <td><INPUT TYPE="radio" NAME="pd_FrpId" value="989">北京银行</td>
			  <td><INPUT TYPE="radio" NAME="pd_FrpId" value="972">兴业银行 </td>
			  <td><INPUT TYPE="radio" NAME="pd_FrpId" value="977">上海浦东发展银行 </td>
			  <td><INPUT TYPE="radio" NAME="pd_FrpId" value="962">中信银行</td>
			</tr>
			<tr>
			  <td><INPUT TYPE="radio" NAME="pd_FrpId" value="992" id="992"><label for="992">支付宝 </label></td>
			  <td><INPUT TYPE="radio" NAME="pd_FrpId" value="1002" id="1002"><label for="1002">支付宝H5支付 </label> </td>
			  <td><INPUT TYPE="radio" NAME="pd_FrpId" value="994" id="994"><label for="994">微信H5支付  </label></td>
			</tr>
			<tr><td><br/></td></tr>
			<tr>
			  <td><INPUT TYPE="submit" value="确定支付"></td>
			</tr>
     	</table>
   		</form>
  </body>
</html>
