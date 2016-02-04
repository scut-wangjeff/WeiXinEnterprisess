package jsp.weixin.servlet.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;
import jsp.weixin.ParamesAPI.util.ParamesAPI;
import jsp.weixin.ParamesAPI.util.WeixinUtil;
import jsp.weixin.encryption.util.SHA1Util;

public class GetTicket extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub	
		String wxurl =request.getParameter("wxurl");
		System.out.println("url==="+wxurl);
		String access_token = WeixinUtil.getAccessToken(ParamesAPI.corpId,
				ParamesAPI.secret).getToken();
		String jspapi_ticket = WeixinUtil.getjsapi_ticket(access_token)
				.getTicket();
		PrintWriter out = response.getWriter();		
		Map<String,String>	signature = SHA1Util.sign(jspapi_ticket,wxurl);	
		JSONObject jo=JSONObject.fromObject(signature);//转化Map对象  
		out.print(jo);
		out.close();
	
	}

	public static void main(String[] args) {
		String access_token = WeixinUtil.getAccessToken(ParamesAPI.corpId,
				ParamesAPI.secret).getToken();
		String jspapi_ticket = WeixinUtil.getjsapi_ticket(access_token)
				.getTicket();
		try {
			String url="http://wuzhe128520.xicp.net:48615/WeiXinEnterprisess/frontedjs.html";
		/**	signature = SHA1Util.sign(jspapi_ticket,url); **/
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
