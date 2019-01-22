package cn.itcast.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.utils.PayConfig;
import cn.itcast.utils.PaymentUtil;
//处理支付请求
public class PayRequestServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String p1_parter = PayConfig.getValue("p1_MerId");	//商户ID
		String p2_type = request.getParameter("pd_FrpId");	//所选银行代码
		String p3_value = request.getParameter("money");	//订单金额
		String p4_orderid = request.getParameter("orderid");	//订单号
		String p5_callbackurl = PayConfig.getValue("responseURL");
		String p6_hrefbackurl = PayConfig.getValue("hrefURL");
		String p7_payerIp = PaymentUtil.getIpAddrByRequest(request);
		String p8_attach = "这是我提交的备注信息";	
		String key = PayConfig.getValue("keyValue");	//商户秘钥
				
		String p9_sign = PaymentUtil.buildHmac( p1_parter, p2_type, p3_value, p4_orderid, p5_callbackurl, key);
		System.out.println("p7_payerIp:" + p7_payerIp);
		System.out.println("提交的签名字符串p9_sign:" + p9_sign);
		
		request.setAttribute("p1_parter", p1_parter);
		request.setAttribute("p2_type", p2_type);
		request.setAttribute("p3_value", p3_value);
		request.setAttribute("p4_orderid", p4_orderid);
		request.setAttribute("p5_callbackurl", p5_callbackurl);
		request.setAttribute("p6_hrefbackurl",p6_hrefbackurl);
		request.setAttribute("p7_payerIp", p7_payerIp);
		request.setAttribute("p8_attach", p8_attach);
		request.setAttribute("p9_sign", p9_sign);

		request.getRequestDispatcher("/payconfirm.jsp").forward(request, response);
		
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}