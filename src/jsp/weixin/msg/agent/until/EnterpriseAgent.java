package jsp.weixin.msg.agent.until;

/**
 * 
 * 企业应用设置参数
 * 
 * */
public class EnterpriseAgent {
	// 企业应用id
	private String agentid;
	// 是否打开地理位置上报
	private String report_location_flag;
	// 企业应用头像的mediaid
	private String logo_mediaid;
	// 企业应用名称
	private String name;
	// 企业应用详情
	private String decription;
	// 企业应用可信域名
	private String redirect_domain;
	// 是否接收用户变更通知。0：不接收；1：接收
	private int isreportuser;
	// 是否上报用户进入应用事件。0：不接收；1：接收
	private int isreportenter;

	public String getAgentid() {
		return agentid;
	}

	public void setAgentid(String agentid) {
		this.agentid = agentid;
	}


	public String getLogo_mediaid() {
		return logo_mediaid;
	}

	public void setLogo_mediaid(String logo_mediaid) {
		this.logo_mediaid = logo_mediaid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDecription() {
		return decription;
	}

	public void setDecription(String decription) {
		this.decription = decription;
	}

	public String getRedirect_domain() {
		return redirect_domain;
	}

	public void setRedirect_domain(String redirect_domain) {
		this.redirect_domain = redirect_domain;
	}

	public int getIsreportuser() {
		return isreportuser;
	}

	public void setIsreportuser(int isreportuser) {
		this.isreportuser = isreportuser;
	}

	public int getIsreportenter() {
		return isreportenter;
	}

	public void setIsreportenter(int isreportenter) {
		this.isreportenter = isreportenter;
	}

	public String getReport_location_flag() {
		return report_location_flag;
	}

	public void setReport_location_flag(String report_location_flag) {
		this.report_location_flag = report_location_flag;
	}

}
