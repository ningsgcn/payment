package cn.itcast.web.servlet;


import java.io.IOException;

import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import cn.itcast.utils.PayConfig;
import cn.itcast.utils.PaymentUtil;

import com.alibaba.fastjson.JSON;
/**
 * 查询订单支付结果
 * @author Administrator
 *
 */
public class QueryRequestServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String parter = PayConfig.getValue("p1_MerId");	//商户ID
		String orderid = request.getParameter("orderid");	//订单号
		String key = PayConfig.getValue("keyValue");	//商户秘钥
				
		String sign = PaymentUtil.buildQueryHmac( parter, orderid, key);
		System.out.println("提交查询请求的签名字符串sign:" + sign);
		/*如果通过jsp页面向服务器发送请求就使用下面的代码
		request.setAttribute("parter", parter);
		request.setAttribute("orderid", orderid);
		request.setAttribute("sign", sign);
		request.getRequestDispatcher("/queryconfirm.jsp").forward(request, response);
		*/
		
		/**
		 * 不使用jsp页面发送请求, 直接通过java代码向服务器发送请求并获取返回结果
		 */
		String url = "http://gateway2019.payzhf.net/Search.aspx";		
		url += "?parter="+parter;
		url += "&orderid="+orderid;
		url += "&sign="+sign;
		/**
		 * 是所有Spring提供的api实现get请求并获取返回数据
		 */
//		RestTemplate rest = new RestTemplate();
//		ResponseEntity<String> result = rest.getForEntity(url, String.class, new Object[1]);
//		String jsonStr = result.getBody();// {"orderid":"1548054890909","opstate":0,"ovalue":1.00,"sysorderid":"B5600158806127724188"}
//		System.out.println("jsonStr: "+jsonStr);
//		Map map = (Map)JSON.parse(jsonStr);
//		Integer i = (Integer) map.get("opstate");//opstate为0则说明订单支付成功.
//		System.out.println("i = " + i);

		/**
		 * 调用jdk自带api写的get请求发送方法
		 */
		String str = PaymentUtil.request(url);
		System.out.println("str: "+str);
		Map map = (Map)JSON.parse(str);
		Integer i = (Integer) map.get("opstate");//opstate为0则说明订单支付成功.
		System.out.println("i = " + i);

	}



	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}