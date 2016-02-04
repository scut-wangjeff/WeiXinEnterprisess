package jsp.weixin.msg.Resp;

/*
 * 
 * 关注/取消关注事件
 * 
 * */
public class SubscribeEvent extends BaseEvent {

	// 企业应用的ID,整型.可在应用的设置页面获取;如果id为0,则表示 是整个企业号的关注/取消关注事件
	private long AgentID;

	public long getAgentID() {
		return AgentID;
	}

	public void setAgentID(long agentID) {
		AgentID = agentID;
	}
}
