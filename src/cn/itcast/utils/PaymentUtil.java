package cn.itcast.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;

import javax.servlet.http.HttpServletRequest;

public class PaymentUtil {

	private static String encodingCharset = "UTF-8";
	
	/**
	 * 生成支付请求的hmac方法
	 
	 * @return
	 */
	public static String buildHmac(String p1_parter, String p2_type, String p3_value, String p4_orderid, String p5_callbackurl, String key) {
		StringBuilder sValue = new StringBuilder();
		// 商户编号
		sValue.append("parter=").append(p1_parter);
		// 银行类型
		sValue.append("&type=").append(p2_type);
		// 支付金额
		sValue.append("&value=").append(p3_value);
		// 商户订单号
		sValue.append("&orderid=").append(p4_orderid);
		// 下行异步通知地址
		sValue.append("&callbackurl=").append(p5_callbackurl);
		// 用户秘钥
		sValue.append(key);
		
		return PaymentUtil.hmacSign(sValue.toString());
	}
	
	/**
	 * 生成查询请求的hmac方法
	 
	 * @return
	 */
	public static String buildQueryHmac(String parter, String orderid, String key) {
		StringBuilder sValue = new StringBuilder();
		// 商户编号
		sValue.append("parter=").append(parter);
		// 商户订单号
		sValue.append("&orderid=").append(orderid);
		// 用户秘钥
		sValue.append(key);
		
		return PaymentUtil.hmacSign(sValue.toString());
	}
	
	/**
	 * 返回校验hmac方法
	 * 
	 * @param hmac 支付网关发来的加密验证码
	 * @param p1_MerId 商户编号
	 * @param r0_Cmd 业务类型
	 * @param r1_Code 支付结果
	 * @param r2_TrxId 易宝支付交易流水号
	 * @param r3_Amt 支付金额
	 * @param r4_Cur 交易币种
	 * @param r5_Pid 商品名称
	 * @param r6_Order 商户订单号
	 * @param r7_Uid 易宝支付会员ID
	 * @param r8_MP 商户扩展信息
	 * @param r9_BType 交易结果返回类型
	 * @param keyValue 密钥
	 * @return
	 */
	public static boolean verifyCallback(String sign, String orderid, String opstate, String ovalue, String key) {
		StringBuilder sValue = new StringBuilder();
		// 商户编号
		sValue.append("orderid=");
		sValue.append(orderid);
		// 业务类型
		sValue.append("&opstate=");//注意! 不能忘了加"&"
		sValue.append(opstate);
		// 支付结果
		sValue.append("&ovalue=");
		sValue.append(ovalue);
		// 易宝支付交易流水号
		sValue.append(key);
		
		String sNewString = PaymentUtil.hmacSign(sValue.toString());
		return sNewString.equals(sign);
	}
	
	/**
     *  使用Hmac进行签名, 对字符串进行MD5加密, 我见过的最简洁的写法
     * @param aValue  加密明文
     * @return
     */
    public static String hmacSign(String aValue) {
        try {
            byte[] input = aValue.getBytes();
            MessageDigest md = MessageDigest.getInstance("MD5");
            return toHex(md.digest(input));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

	/**
	 * 短信宝提供的MD5加密方法, 与上面的hmacSign方法效果一样
	 * @param plainText
	 * @return
	 */
	public static String md5(String plainText) {
		StringBuffer buf = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(plainText.getBytes());
			byte b[] = md.digest();
			int i;
			buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return buf.toString();
	}
    
	public static String toHex(byte input[]) {
		if (input == null)
			return null;
		StringBuffer output = new StringBuffer(input.length * 2);
		for (int i = 0; i < input.length; i++) {
			int current = input[i] & 0xff;
			if (current < 16)
				output.append("0");
			output.append(Integer.toString(current, 16));
		}

		return output.toString();
	}

	/**
	 * 
	 * @param args
	 * @param key
	 * @return
	 */
	public static String getHmac(String[] args, String key) {
		if (args == null || args.length == 0) {
			return (null);
		}
		StringBuffer str = new StringBuffer();
		for (int i = 0; i < args.length; i++) {
			str.append(args[i]);
		}
		return (hmacSign(str.toString()));
	}

	/**
	 * @param aValue
	 * @return
	 */
	public static String digest(String aValue) {
		aValue = aValue.trim();
		byte value[];
		try {
			value = aValue.getBytes(encodingCharset);
		} catch (UnsupportedEncodingException e) {
			value = aValue.getBytes();
		}
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
		return toHex(md.digest(value));

	}
	
	/**
	 * 获得客户端ip地址
	 * 
	 * @author <a href='ningsgcn@163.com'>Guide Ning</a>
	 * @date 2019-1-21 18:22:22
	 * @param request
	 * @return String
	 */
	public static String getIpAddrByRequest(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		if(ip.equals("127.0.0.1") || ip.equals("0:0:0:0:0:0:0:1")){
			//根据网卡获取本机配置的IP地址
			InetAddress inetAddress = null;
			try {
				inetAddress = InetAddress.getLocalHost();
				} catch (UnknownHostException e) {
					e.printStackTrace();
				}
			ip = inetAddress.getHostAddress();
		}        
		//对于通过多个代理的情况，第一个IP为客户端真实的IP地址，多个IP按照','分割
		if(ip.contains(",")) {
			return ip.split(",")[0];
		}else {
			return ip;
		}		
	}

	/**
	 * 使用jdk自带的api实现get请求并获取返回数据
	 * @param httpUrl
	 * @return
	 */
	public static String request(String httpUrl) {

		BufferedReader reader = null;
		String result = null;
		StringBuffer sbf = new StringBuffer();
		try {
			URL url = new URL(httpUrl);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.connect();
			InputStream is = connection.getInputStream();
			reader = new BufferedReader(new InputStreamReader(is, "GB2312"));
			String strRead = reader.readLine();
			if (strRead != null) {
				sbf.append(strRead);
				while ((strRead = reader.readLine()) != null) {
					sbf.append("\n");
					sbf.append(strRead);
				}
			}
			reader.close();
			result = sbf.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static void main(String[] args) {
        String key = "1234567890abcdefghijklmnopqrstuvwxyz";
        String s = hmacSign(key);
        String s1 = md5(key);
        System.out.println(s); //928f7bcdcd08869cc44c1bf24e7abec6
		System.out.println(s1);//928f7bcdcd08869cc44c1bf24e7abec6
    }
	
}