package jsp.weixin.msg.agent.until;

import jsp.weixin.ParamesAPI.util.ParamesAPI;
import jsp.weixin.ParamesAPI.util.WeixinUtil;
import net.sf.json.JSONObject;

/**
 * 
 * 管理企业应用
 * 
 * @time 2015.04.27
 * 
 * */
public class ManageAgent {
	// 获取企业号某个应用的基本信息
	private static String GetAgentInfo_URL = "https://qyapi.weixin.qq.com/cgi-bin/agent/get?access_token=ACCESS_TOKEN&agentid=AGENTID";
	// 设置企业应用的选项设置信息，如：地理位置上报等。
	private static String SetAgentInfo_URL = "https://qyapi.weixin.qq.com/cgi-bin/agent/set?access_token=ACCESS_TOKEN";
	// 获取secret所在管理组内的应用概况，会返回管理组内应用的id及名称、头像等信息
	private static String GetAgentInfos_URL = "https://qyapi.weixin.qq.com/cgi-bin/agent/list?access_token=ACCESS_TOKEN";

	/**
	 * 获取企业号应用
	 * 
	 * @param access_token
	 *            有效的凭证
	 * @param agentid
	 *            获取企业id
	 * 
	 * */
	public static JSONObject Get_AgentInfo(String access_token, String agentid) {
		// 拼接url参数
		String request_url = GetAgentInfo_URL.replace("ACCESS_TOKEN",
				access_token).replace("AGENTID", agentid);
		// 发送请求,获取返回值
		JSONObject jsonObject = WeixinUtil.PostMessage(access_token, "POST",
				request_url);
		return jsonObject;
	}

	/**
	 * 设置企业应用的选项设置信息
	 * 
	 * @param access_token
	 *            有效的凭证
	 * @param agent
	 *            应用设置参数
	 *
	 * */
	public static int SetAgentInfo(String access_token, EnterpriseAgent agent) {
		// 拼接url参数
		String request_url = SetAgentInfo_URL.replace("ACCESS_TOKEN",
				access_token);
		String PostData = "{\"agentid\":\"%s\",\"report_location_flag\":%s,\"logo_mediaid\":\"%s\",\"name\":\"%s\",\"description\":\"%s\",\"redirect_domain\":\"%s\",\"isreportuser\":%s,\"isreportenter\":%s}";
		PostData = String.format(PostData, agent.getAgentid(),
				agent.getReport_location_flag(), agent.getLogo_mediaid(),
				agent.getName(), agent.getDecription(),
				agent.getRedirect_domain(), agent.getIsreportuser(),
				agent.getIsreportenter());
		System.out.println(request_url);
		System.out.println(PostData);
		// 发送请求,获取返回值
		int result = WeixinUtil.PostMessage(access_token, "GET", request_url,
				PostData);

		return result;
	}

	/**
	 * 获取应用概况
	 * 
	 * @param access_token
	 *            有效的凭证
	 * @param agentid
	 *            企业应用的编号
	 * 
	 * */
	public static JSONObject Get_AgentInfos(String access_token, String agentid) {
		// 拼接url参数
		String request_url = GetAgentInfos_URL.replace("ACCESS_TOKEN",
				access_token).replace("AGENTID", agentid);
		// 发送请求,获取返回值
		JSONObject jsonObject = WeixinUtil.PostMessage(access_token, "POST",
				request_url);
		return jsonObject;
	}

	// 测试接口
	public static void main(String[] args) {
		// 获取凭证

		String access_token = WeixinUtil.getAccessToken(ParamesAPI.corpId,
				ParamesAPI.secret).getToken();
		/**
		 * 获取应用基本信息
		 * */

		/*
		 * JSONObject jsonObject = Get_AgentInfo(access_token, "3");
		 * System.out.println(jsonObject);
		 */

		/**
		 * 设置应用选项
		 * */

		EnterpriseAgent eAgent = new EnterpriseAgent();
		eAgent.setAgentid("5");
		eAgent.setReport_location_flag("0");
		eAgent.setLogo_mediaid("1rf2uM2SVAOtEyxG93pYCkZ-u3oCPzgQ8i9JwuqjSNupzjIsrZxTz_bTxjASc0-0Q");
		eAgent.setName("测试应用");
		eAgent.setDecription("投票测试");
		eAgent.setRedirect_domain("www.baidu.com");
		eAgent.setIsreportuser(0);
		eAgent.setIsreportenter(0);

		int result = SetAgentInfo(access_token, eAgent);
		if (0 != result) {
			System.out.println("设置失败");
		} else {
			System.out.println("设置成功");
		}

		/**
		 * 获取应用基本信息列表
		 * */
		/*
		 * JSONObject jsonObject = Get_AgentInfos(access_token, "3");
		 * System.out.println(jsonObject);
		 */
	}
}
