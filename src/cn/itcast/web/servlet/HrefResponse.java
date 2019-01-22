package cn.itcast.web.servlet;
import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.utils.PayConfig;
import cn.itcast.utils.PaymentUtil;
/**
 * 接收处理支付网关返回的同步通知
 * @author Administrator
 *如果在配置文件中设置hrefURL=http://2q28e67388.51mypc.cn/payment/hrefBack.jsp则直接跳转到页面, 不需要此servlet
 */
public class HrefResponse extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("HrefResponse is running!");

		response.setCharacterEncoding("gb2312");
		response.setContentType("text/html;charset=gb2312");
		
		Enumeration<String> hrefRequestList = request.getParameterNames();
		int i = 0;
		if(hrefRequestList.hasMoreElements()) {			
			System.out.println("hrefRequestList.nextElement():" + hrefRequestList.nextElement());
			String[] hrefRequest = request.getParameterValues(hrefRequestList.nextElement());
			System.out.println("hrefRequest" + i + ":" + hrefRequest.toString());
			for(int j=0;j<hrefRequest.length;j++) {
				System.out.println("hrefRequest" + j + ":" + hrefRequest[j]);
			}
			i++;
		}
//		response.sendRedirect("../hrefBack.jsp"); //通过重定向可以跳转到指定页面.
		request.getRequestDispatcher("/hrefBack.jsp").forward(request, response);//通过转发也可以调到指定页面,使用"../hrefBack.jsp"也可以
		
		
		/*
		String orderid = request.getParameter("orderid");//商户订单号
		String opstate = request.getParameter("opstate");//订单结果
		String ovalue = request.getParameter("ovalue");//订单金额
		String sign = request.getParameter("sign");//MD5签名
		String sysorderid = request.getParameter("sysorderid");//银通订单号
		String completiontime = request.getParameter("completiontime");//银通订单时间
		String attach = request.getParameter("attach")==null?"":new String(request.getParameter("attach").getBytes(),"GB2312");//备注信息
		String msg = request.getParameter("msg")==null?"":new String(request.getParameter("msg").getBytes(),"GB2312");//订单结果说明
		
		System.out.println("支付网关返回的同步通知的签名字符串sign:" + sign);		
		
		String key = PayConfig.getValue("keyValue");
		
		System.out.println("orderid = " + orderid + ", opstate = " + opstate + ", ovalue = " + ovalue
				+ ", sysorderid = " + sysorderid +", completiontime = " + completiontime 
				+ ", attach = "	+ attach + ", msg = " + msg);
		
		boolean b = PaymentUtil.verifyCallback(sign, orderid, opstate, ovalue, key);
		System.out.println("对支付网关返回的同步通知数据校验结果:  " + b);
		
		if(!b){
			//银通支付异步通知中的签名不正确
			response.getWriter().write("同步通知交易签名已被修改！！！");
		}
		*/

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}