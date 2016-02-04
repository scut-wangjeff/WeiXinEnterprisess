package jsp.weixin.ParamesAPI.util;

/**
 * @author Engineer-Jsp
 * @date 2014.10.09
 * 请求数据通用类*/
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;

import jsp.weixin.menu.util.Menu;
import net.sf.json.JSONObject;

public class WeixinUtil {
	/**
	 * 发起https请求并获取结果
	 * 
	 * @param requestUrl
	 *            请求地址
	 * @param requestMethod
	 *            请求方式（GET、POST）
	 * @param outputStr
	 *            提交的数据
	 * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
	 */
	public static JSONObject HttpRequest(String request, String RequestMethod,
			String output) {
		JSONObject jsonObject = null;
		StringBuffer buffer = new StringBuffer();
		try {
			//设置代理
			Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("cmproxy.gmcc.net", 8081));
			// 建立连接
			URL url = new URL(request);
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection(proxy);
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setConnectTimeout(10000);
			connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			connection.setUseCaches(false);
			connection.setRequestMethod(RequestMethod);
			if (output != null) {
				OutputStream out = connection.getOutputStream();
				out.write(output.getBytes("UTF-8"));
				out.close();
			}
			// 流处理
			InputStream input = connection.getInputStream();
			InputStreamReader inputReader = new InputStreamReader(input,
					"UTF-8");
			BufferedReader reader = new BufferedReader(inputReader);
			String line;
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}
			// 关闭连接、释放资源
			reader.close();
			inputReader.close();
			input.close();
			input = null;
			connection.disconnect();
			jsonObject = JSONObject.fromObject(buffer.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObject;
	}

	// 获取access_token的接口地址（GET）
	public final static String access_token_url = "https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=CorpID&corpsecret=SECRET";

	// 获取jsapi_ticket的接口地址
	public final static String jsapi_ticket_url = "https://qyapi.weixin.qq.com/cgi-bin/get_jsapi_ticket?access_token=ACCESS_TOKE";

	/**
	 * 获取access_token
	 * 
	 * @param CorpID
	 *            企业Id
	 * @param SECRET
	 *            管理组的凭证密钥，每个secret代表了对应用、通讯录、接口的不同权限；不同的管理组拥有不同的secret
	 * @return
	 */
	public static AccessToken getAccessToken(String corpID, String secret) {
		AccessToken accessToken = null;

		String requestUrl = access_token_url.replace("CorpID", corpID).replace(
				"SECRET", secret);
		JSONObject jsonObject = HttpRequest(requestUrl, "GET", null);
		// 如果请求成功
		if (null != jsonObject) {
			try {
				accessToken = new AccessToken();
				accessToken.setToken(jsonObject.getString("access_token"));
				accessToken.setExpiresIn(jsonObject.getInt("expires_in"));
				System.out.println("获取token成功:"
						+ jsonObject.getString("access_token") + "————"
						+ jsonObject.getInt("expires_in"));
			} catch (Exception e) {
				accessToken = null;
				// 获取token失败
				String error = String.format("获取token失败 errcode:{} errmsg:{}",
						jsonObject.getInt("errcode"),
						jsonObject.getString("errmsg"));
				System.out.println(error);
			}
		}
		return accessToken;
	}

	/**
	 * 获取jsapi_ticket
	 * 
	 * @param access_token
	 *            有效的凭证
	 * @return
	 */
	public static Jsapi_Ticket getjsapi_ticket(String access_token) {
		Jsapi_Ticket jsapi_Ticket = null;
		String requestUrl = jsapi_ticket_url.replace("ACCESS_TOKE",
				access_token);
		JSONObject jsonObject = HttpRequest(requestUrl, "GET", null);
		// 如果请求成功
		if (null != jsonObject) {
			try {
				jsapi_Ticket = new Jsapi_Ticket();
				jsapi_Ticket.setTicket(jsonObject.getString("ticket"));
				jsapi_Ticket.setExpires_in(jsonObject.getInt("expires_in"));
				System.out.println("获取tiket成功:"
						+ jsonObject.getString("ticket") + "————"
						+ jsonObject.getInt("expires_in"));
			} catch (Exception e) {
				jsapi_Ticket = null;
				// 获取ticket失败
				String error = String.format(
						"获取ticket失败 errcode:{%s} errmsg:{%s}",
						jsonObject.getInt("errcode"),
						jsonObject.getString("errmsg"));
				System.out.println(error);
			}
		}
		return jsapi_Ticket;
	}

	// 菜单创建（POST）
	public static String menu_create_url = "https://qyapi.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN&agentid=AGENTID";

	// 菜单删除(POST)
	public static String menu_delete_url = "https://qyapi.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN&agentid=AGENTID";

	// 获取菜单
	public static String get_menu_url = "https://qyapi.weixin.qq.com/cgi-bin/menu/get?access_token=ACCESS_TOKEN&agentid=AGENTID";

	/**
	 * 创建菜单
	 * 
	 * @param menu
	 *            菜单实例
	 * @param accessToken
	 *            有效的access_token
	 * @param agentid
	 *            企业应用的id，整型，可在应用的设置页面查看
	 * @return 0表示成功，其他值表示失败
	 */
	public static int createMenu(Menu menu, String accessToken, String AGENTID) {
		int result = 0;

		// 拼装创建菜单的url
		String url = menu_create_url.replace("ACCESS_TOKEN", accessToken)
				.replace("AGENTID", AGENTID);
		// 将菜单对象转换成json字符串
		String jsonMenu = JSONObject.fromObject(menu).toString();
		// 调用接口创建菜单
		JSONObject jsonObject = HttpRequest(url, "POST", jsonMenu);

		if (null != jsonObject) {
			if (0 != jsonObject.getInt("errcode")) {
				result = jsonObject.getInt("errcode");
				String error = String.format("创建菜单失败 errcode:{%s} errmsg:{%s}",
						jsonObject.getInt("errcode"),
						jsonObject.getString("errmsg"));
				System.out.println(error);
			}
		}
		return result;
	}

	/**
	 * 删除自定义菜单
	 * 
	 * @param accessToken
	 *            有效的access_token
	 * @param agentid
	 *            企业应用的id，整型，可在应用的设置页面查看
	 * */
	public static int deletemenu(String accessToken, String AGENTID) {
		int result = 0;
		// 拼装创建菜单的url
		String url = menu_delete_url.replace("ACCESS_TOKEN", accessToken)
				.replace("AGENTID", AGENTID);
		// 调用接口创建菜单
		JSONObject jsonObject = HttpRequest(url, "POST", "");

		if (null != jsonObject) {
			if (0 != jsonObject.getInt("errcode")) {
				result = jsonObject.getInt("errcode");
				String error = String.format("创建菜单失败 errcode:{%s} errmsg:{%s}",
						jsonObject.getInt("errcode"),
						jsonObject.getString("errmsg"));
				System.out.println(error);
			}
		}
		return result;
	}

	/**
	 * 获取自定义菜单列表
	 * 
	 * 
	 * @param accessToken
	 *            有效的access_token
	 * 
	 * @param AGENTID
	 *            企业应用的id，整型，可在应用的设置页面查看
	 * 
	 */
	public static JSONObject GetMenu(String accessToken, String AGENTID) {
		// 拼装创建菜单的url
		String url = get_menu_url.replace("ACCESS_TOKEN", accessToken).replace(
				"AGENTID", AGENTID);
		// 调用接口创建菜单
		JSONObject jsonObject = HttpRequest(url, "POST", "");
		return jsonObject;
	}

	public static String URLEncoder(String str) {
		String result = str;
		try {
			result = java.net.URLEncoder.encode(result, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 根据内容类型判断文件扩展名
	 * 
	 * @param contentType
	 *            内容类型
	 * @return
	 */
	public static String getFileEndWitsh(String contentType) {
		String fileEndWitsh = "";
		if ("image/jpeg".equals(contentType))
			fileEndWitsh = ".jpg";
		else if ("audio/mpeg".equals(contentType))
			fileEndWitsh = ".mp3";
		else if ("audio/amr".equals(contentType))
			fileEndWitsh = ".amr";
		else if ("video/mp4".equals(contentType))
			fileEndWitsh = ".mp4";
		else if ("video/mpeg4".equals(contentType))
			fileEndWitsh = ".mp4";
		return fileEndWitsh;
	}

	/**
	 * 数据提交与请求通用方法
	 * 
	 * @param access_token
	 *            凭证
	 * @param RequestMt
	 *            请求方式
	 * @param RequestURL
	 *            请求地址
	 * @param outstr
	 *            提交json数据
	 * */
	public static int PostMessage(String access_token, String RequestMt,
			String RequestURL, String outstr) {
		int result = 0;
		RequestURL = RequestURL.replace("ACCESS_TOKEN", access_token);
		JSONObject jsonobject = WeixinUtil.HttpRequest(RequestURL, RequestMt,
				outstr);
		if (null != jsonobject) {
			if (0 != jsonobject.getInt("errcode")) {
				result = jsonobject.getInt("errcode");
				String error = String.format("操作失败 errcode:{%s} errmsg:{%s}",
						jsonobject.getInt("errcode"),
						jsonobject.getString("errmsg"));
				System.out.println(error);
			}
		}
		return result;
	}

	/**
	 * 数据提交与请求通用方法
	 * 
	 * @param access_token
	 *            凭证
	 * @param RequestMt
	 *            请求方式
	 * @param RequestURL
	 *            请求地址
	 * */
	public static JSONObject PostMessage(String access_token, String RequestMt,
			String RequestURL) {
		RequestURL = RequestURL.replace("ACCESS_TOKEN", access_token);
		JSONObject jsonobject = WeixinUtil.HttpRequest(RequestURL, RequestMt,
				RequestURL);
		if (null != jsonobject) {
			if (0 != jsonobject.getInt("errcode")) {
				jsonobject.getInt("errcode");
				String error = String.format("操作失败 errcode:{%s} errmsg:{%s}",
						jsonobject.getInt("errcode"),
						jsonobject.getString("errmsg"));
				System.out.println(error);
			}
		}
		return jsonobject;
	}

	/**
	 * 数据提交与请求通用方法
	 * 
	 * @param access_token
	 *            凭证
	 * @param RequestMt
	 *            请求方式
	 * @param RequestURL
	 *            请求地址
	 * @param outstr
	 *            提交json数据
	 * */
	public static JSONObject GetMessage(String access_token, String RequestMt,
			String RequestURL, String outstr) {
		RequestURL = RequestURL.replace("ACCESS_TOKEN", access_token);
		JSONObject jsonobject = WeixinUtil.HttpRequest(RequestURL, RequestMt,
				outstr);
		if (null != jsonobject) {
			if (0 != jsonobject.getInt("errcode")) {
				jsonobject.getInt("errcode");
				String error = String.format("操作失败 errcode:{%s} errmsg:{%s}",
						jsonobject.getInt("errcode"),
						jsonobject.getString("errmsg"));
				System.out.println(error);
			}
		}
		return jsonobject;
	}
}
