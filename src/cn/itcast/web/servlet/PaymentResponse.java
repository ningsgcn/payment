package cn.itcast.web.servlet;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.utils.PayConfig;
import cn.itcast.utils.PaymentUtil;
/**
 * 接收处理支付网关返回的异步通知
 * @author Administrator
 *
 */
public class PaymentResponse extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("PaymentResponse is running!");

		response.setCharacterEncoding("gb2312");
		response.setContentType("text/html;charset=gb2312");
		
		String orderid = request.getParameter("orderid");//商户订单号
		String opstate = request.getParameter("opstate");//订单结果
		String ovalue = request.getParameter("ovalue");//订单金额
		String sign = request.getParameter("sign");//MD5签名
		String sysorderid = request.getParameter("sysorderid");//银通订单号
		String completiontime = request.getParameter("completiontime");//银通订单时间
		String attach = request.getParameter("attach")==null?"":new String(request.getParameter("attach").getBytes("ISO-8859-1"),"GB2312");//备注信息
		String msg = request.getParameter("msg")==null?"":new String(request.getParameter("msg").getBytes("ISO-8859-1"),"GB2312");//订单结果说明
		
		System.out.println("支付网关返回的异步通知的签名字符串sign:" + sign);		
		
		String key = PayConfig.getValue("keyValue");
		
		System.out.println("orderid = " + orderid + ", opstate = " + opstate + ", ovalue = " + ovalue
				+ ", sysorderid = " + sysorderid +", completiontime = " + completiontime 
				+ ", attach = "	+ attach + ", msg = " + msg);
		
		boolean b = PaymentUtil.verifyCallback(sign, orderid, opstate, ovalue, key);
		System.out.println("对支付网关返回的异步通知数据校验结果数据校验结果:  " + b);
		
		if(!b){
			//银通支付异步通知中的签名不正确
//			response.getWriter().write("交易签名已被修改！！！");
			//同步返回银通接口opstate=-2
			response.getWriter().println("opstate=-2");
		}else {
			//银通支付异步通知中的签名正确
			//同步返回银通接口opstate=0
			response.getWriter().println("opstate=0");
			if("0".equals(opstate)){  //处理支付成功
				//按照成功处理商户系统内订单的业务代码
				response.getWriter().println("支付成功！！");
			}
			if("-1".equals(opstate)){
				//按照失败处理商户系统内订单的业务代码
				response.getWriter().println("请求参数无效！！");
			}
			if("-2".equals(opstate)){
				//按照失败处理商户系统内订单的业务代码
				response.getWriter().println("签名错误");//向银通支付请求的数据签名有误			
			}
			
		}
		

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}