package jsp.weixin.msg.Util;

/** 
 * 发送消息类 
 *  
 * @date 2015.04.27 
 */

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import jsp.weixin.ParamesAPI.util.ParamesAPI;
import jsp.weixin.ParamesAPI.util.WeixinUtil;
import jsp.weixin.contacts.util.MPerson;
import jsp.weixin.msg.Resp.Article;
import jsp.weixin.msg.Resp.File;
import jsp.weixin.msg.Resp.Image;
import jsp.weixin.msg.Resp.ImageMessage;
import jsp.weixin.msg.Resp.NewsMessage;
import jsp.weixin.msg.Resp.TextMessage;
import jsp.weixin.msg.Resp.Video;
import jsp.weixin.msg.Resp.Voice;

public class SMessage {
	// 发送接口
	public static String POST_URL = "https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token=ACCESS_TOKEN";

	/**
	 * text消息
	 * 
	 * @param touser
	 *            UserID列表（消息接收者，多个接收者用‘|’分隔）。特殊情况：指定为@all，则向关注该企业应用的全部成员发送————
	 *            "touser": "UserID1|UserID2|UserID3"
	 * @param toparty
	 *            PartyID列表，多个接受者用‘|’分隔。当touser为@all时忽略本参数————"toparty":
	 *            " PartyID1 | PartyID2 "
	 * @param totag
	 *            TagID列表，多个接受者用‘|’分隔。当touser为@all时忽略本参数————"totag":
	 *            " TagID1 | TagID2 "
	 * @param msgtype
	 *            消息类型，此时固定为：text
	 * @param agentid
	 *            企业应用的id，整型。可在应用的设置页面查看
	 * @param content
	 *            消息内容
	 * @param safe
	 *            表示是否是保密消息，0表示否，1表示是，默认0
	 * */
	public static String STextMsg(String touser, String toparty, String totag,
			String agentid, TextMessage text) {
		String PostData = "{\"touser\": %s,\"toparty\": %s,\"totag\": %s,\"msgtype\": \"text\",\"agentid\": %s,\"text\": {\"content\": \"%s\"},\"safe\":\"0\"}";
		return String.format(PostData, touser, toparty, totag, agentid,
				text.getContent());
	}

	/**
	 * image消息
	 * 
	 * @param touser
	 *            UserID列表（消息接收者，多个接收者用‘|’分隔）。特殊情况：指定为@all，则向关注该企业应用的全部成员发送————
	 *            "touser": "UserID1|UserID2|UserID3"
	 * @param toparty
	 *            PartyID列表，多个接受者用‘|’分隔。当touser为@all时忽略本参数————"toparty":
	 *            " PartyID1 | PartyID2 "
	 * @param totag
	 *            TagID列表，多个接受者用‘|’分隔。当touser为@all时忽略本参数————"totag":
	 *            " TagID1 | TagID2 "
	 * @param msgtype
	 *            消息类型，此时固定为：image
	 * @param agentid
	 *            企业应用的id，整型。可在应用的设置页面查看
	 * @param media_id
	 *            媒体资源文件ID
	 * @param safe
	 *            表示是否是保密消息，0表示否，1表示是，默认0
	 * */
	public static String SImageMsg(String touser, String toparty,
			String agentid, Image image) {
		String PostData = "{\"touser\": \"%s\",\"toparty\": \"%s\",\"msgtype\": \"image\",\"agentid\": \"%s\",\"image\": {\"media_id\": \"%s\"},\"safe\":\"0\"}";
		return String.format(PostData, touser, toparty, agentid,
				image.getMediaId());
	}

	/**
	 * voice消息
	 * 
	 * @param touser
	 *            UserID列表（消息接收者，多个接收者用‘|’分隔）。特殊情况：指定为@all，则向关注该企业应用的全部成员发送————
	 *            "touser": "UserID1|UserID2|UserID3"
	 * @param toparty
	 *            PartyID列表，多个接受者用‘|’分隔。当touser为@all时忽略本参数————"toparty":
	 *            " PartyID1 | PartyID2 "
	 * @param totag
	 *            TagID列表，多个接受者用‘|’分隔。当touser为@all时忽略本参数————"totag":
	 *            " TagID1 | TagID2 "
	 * @param msgtype
	 *            消息类型，此时固定为：voice
	 * @param agentid
	 *            企业应用的id，整型。可在应用的设置页面查看
	 * @param media_id
	 *            媒体资源文件ID
	 * @param safe
	 *            表示是否是保密消息，0表示否，1表示是，默认0
	 * */
	public static String SVoiceMsg(String touser, String toparty, String totag,
			String agentid, Voice voice) {
		String PostData = "{\"touser\": \"%s\",\"toparty\": \"%s\",\"totag\": \"%s\",\"msgtype\": \"voice\",\"agentid\": \"%s\",\"voice\": {\"media_id\": \"%s\"},\"safe\":\"0\"}";
		return String.format(PostData, touser, toparty, totag, agentid,
				voice.getMediaId());
	}

	/**
	 * video消息
	 * 
	 * @param touser
	 *            UserID列表（消息接收者，多个接收者用‘|’分隔）。特殊情况：指定为@all，则向关注该企业应用的全部成员发送————
	 *            "touser": "UserID1|UserID2|UserID3"
	 * @param toparty
	 *            PartyID列表，多个接受者用‘|’分隔。当touser为@all时忽略本参数————"toparty":
	 *            " PartyID1 | PartyID2 "
	 * @param totag
	 *            TagID列表，多个接受者用‘|’分隔。当touser为@all时忽略本参数————"totag":
	 *            " TagID1 | TagID2 "
	 * @param msgtype
	 *            消息类型，此时固定为：video
	 * @param agentid
	 *            企业应用的id，整型。可在应用的设置页面查看
	 * @param media_id
	 *            媒体资源文件ID
	 * @param title
	 *            视频消息的标题
	 * @param description
	 *            视频消息的描述
	 * @param safe
	 *            表示是否是保密消息，0表示否，1表示是，默认0
	 */
	public static String SVideoMsg(String touser, String toparty, String totag,
			String agentid, Video video) {
		String PostData = "{\"touser\": \"%s\",\"toparty\": %s,\"totag\": %s,\"msgtype\":\"video\",\"agentid\": \"%s\",\"video\": {\"media_id\": \"%s\"},\"safe\":\"0\"}";
		return String.format(PostData, touser, toparty, totag, agentid,
				video.getMediaId(), video.getTitle(), video.getDescription());
	}

	/**
	 * file消息
	 * 
	 * @param touser
	 *            UserID列表（消息接收者，多个接收者用‘|’分隔）。特殊情况：指定为@all，则向关注该企业应用的全部成员发送————
	 *            "touser": "UserID1|UserID2|UserID3"
	 * @param toparty
	 *            PartyID列表，多个接受者用‘|’分隔。当touser为@all时忽略本参数————"toparty":
	 *            " PartyID1 | PartyID2 "
	 * @param totag
	 *            TagID列表，多个接受者用‘|’分隔。当touser为@all时忽略本参数————"totag":
	 *            " TagID1 | TagID2 "
	 * @param msgtype
	 *            消息类型，此时固定为：file
	 * @param agentid
	 *            企业应用的id，整型。可在应用的设置页面查看
	 * @param media_id
	 *            媒体资源文件ID
	 * @param safe
	 *            表示是否是保密消息，0表示否，1表示是，默认0
	 * */
	public static String SFileMsg(String touser, String toparty, String totag,
			String agentid, File file) {
		String PostData = "{\"touser\": %s,\"toparty\": %s,\"totag\": %s,\"msgtype\": \"file\",\"agentid\": %s,\"file\": {\"media_id\": \"%s\"},\"safe\":\"0\"}";
		return String.format(PostData, touser, toparty, totag, agentid,
				file.getMedia_id());
	}

	/**
	 * news消息
	 * 
	 * @param touser
	 *            UserID列表（消息接收者，多个接收者用‘|’分隔）。特殊情况：指定为@all，则向关注该企业应用的全部成员发送————
	 *            "touser": "UserID1|UserID2|UserID3"
	 * @param toparty
	 *            PartyID列表，多个接受者用‘|’分隔。当touser为@all时忽略本参数————"toparty":
	 *            " PartyID1 | PartyID2 "
	 * @param totag
	 *            TagID列表，多个接受者用‘|’分隔。当touser为@all时忽略本参数————"totag":
	 *            " TagID1 | TagID2 "
	 * @param msgtype
	 *            消息类型，此时固定为：news
	 * @param agentid
	 *            企业应用的id，整型。可在应用的设置页面查看
	 * @param articlesList
	 *            图文集合
	 */
	public static String SNewsMsg(String touser, String toparty, String totag,
			String agentid, String articlesList) {
		String postData = "{\"touser\": %s,\"toparty\": %s,\"totag\": %s,\"msgtype\": \"news\",\"agentid\": %s,\"news\": {\"articles\":%s}}";
		return String.format(postData, touser, toparty, totag, agentid,
				articlesList);
	}

	/**
	 * mpnews消息
	 * 
	 * @param touser
	 *            UserID列表（消息接收者，多个接收者用‘|’分隔）。特殊情况：指定为@all，则向关注该企业应用的全部成员发送————
	 *            "touser": "UserID1|UserID2|UserID3"
	 * @param toparty
	 *            PartyID列表，多个接受者用‘|’分隔。当touser为@all时忽略本参数————"toparty":
	 *            " PartyID1 | PartyID2 "
	 * @param totag
	 *            TagID列表，多个接受者用‘|’分隔。当touser为@all时忽略本参数————"totag":
	 *            " TagID1 | TagID2 "
	 * @param msgtype
	 *            消息类型，此时固定为：mpnews
	 * @param agentid
	 *            企业应用的id，整型。可在应用的设置页面查看
	 * @param articlesList
	 *            mpnews集合
	 */
	public static String SMpNewsMsg(String touser, String toparty,
			String totag, String agentid, String articlesList) {
		String postData = "{\"touser\": %s,\"toparty\": %s,\"totag\": %s,\"msgtype\": \"mpnews\",\"agentid\": %s,\"mpnews\": {\"articles\":%s}\"safe\":\"0\"}";
		return String.format(postData, touser, toparty, totag, agentid,
				articlesList);
	}

	// 发送消息测试
	public static void main(String[] args) {
		// 调取凭证
		String access_token = WeixinUtil.getAccessToken(ParamesAPI.corpId,
				ParamesAPI.secret).getToken();
		/**
		 * 发送news图文消息示例
		 * */
		/*
		 * // 新建图文 Article article1 = new Article();
		 * article1.setTitle("news消息测试-1"); article1.setDescription("");
		 * article1.setPicUrl("http://localhost:8080/WeiXinEnterprisess/123.jpg"
		 * );
		 * article1.setUrl("http://localhost:8080/WeiXinEnterprisess/123.jpg");
		 * Article article2 = new Article(); article2.setTitle("news消息测试-2");
		 * article2.setDescription("");
		 * article2.setPicUrl("http://localhost:8080/WeiXinEnterprisess/123.jpg"
		 * );
		 * article2.setUrl("http://localhost:8080/WeiXinEnterprisess/123.jpg");
		 * // 整合图文 List<Article> list = new ArrayList<Article>();
		 * list.add(article1); list.add(article2); // 图文转json String
		 * articlesList = JSONArray.fromObject(list).toString(); // Post的数据
		 * String PostData = SNewsMsg("3", "1", null, "3", articlesList);
		 */
		/**
		 * 发送text文本消息
		 * */
		/*
		 * TextMessage text = new TextMessage(); text.setContent("您好！在吗？");
		 * String PostData = STextMsg("3", "1", null, "3", text);
		 * System.out.println(PostData); int result =
		 * WeixinUtil.PostMessage(access_token, "POST", POST_URL, PostData);
		 */
		/**
		 * 发送image图片消息
		 * */
		/*
		 * Image image = new Image(); image.setMediaId(
		 * "1HOL6ohKorjnOOtztf9lKRtEXbPyd3vdAlxmdoGK5QVkpJ_igbWUnBHXw5mX2jBaE3vdJBmajLRxsWqmShWVDQA"
		 * ); String PostData = SImageMsg("liuwenzhuo", null, "3", image);
		 * System.out.println(PostData);
		 */

		/**
		 * 发送voice消息
		 * */
		/*
		 * Voice voice = new Voice(); voice.setMediaId(
		 * "1tCxrxE4Wiy7aiwyadZPONJRHk8vOdTJPG_h8CLcDqtCRtxSmgHQGzdcIJ6QevNrgk7UItovukpx7b0p-6w1d9Q"
		 * ); String PostData = SVoiceMsg("liuwenzhuo", null, null, "3", voice);
		 */
		/**
		 * 发送file普通文件消息
		 * */
	/*	
		  File file = new File(); file.setMedia_id(
		  "1UeuS-aHpX5Znqqn2ZgBeNNK27yzlStvAGe9krxawnaYvyz64UCXG45QCKg2MBa7eDV50SlK0q5hxVDg3vOAjUQ"
		  ); String PostData = SFileMsg(null, "1", null, "3", file);
		 */
		/**
		 * 发送video视频消息
		 * */
        /*
		Video video = new Video();
		video.setMediaId("13y7gq-intMsqm3yVYnXzWkE00rh2yjU733GkkW8ogyw0JVp88JPiiUUwLbSP3RuyPL66xcUICX8S9QTUTn2X8w");
		video.setDescription("很好看的哦");
		video.setTitle("视频");
		String PostData = SVideoMsg("liuwenzhuo", null, null, "3", video);
		*/

		/*
		 * 
		 * 发送本地news消息
		 */
		/*
		 * Article article1 = new Article(); article1.setTitle("news消息测试-1");
		 * article1.setDescription("");
		 * article1.setPicUrl("http://localhost:8080/WeiXinEnterprisess/123.jpg"
		 * );
		 * article1.setUrl("http://localhost:8080/WeiXinEnterprisess/123.jpg");
		 * Article article2 = new Article(); article2.setTitle("news消息测试-2");
		 * article2.setDescription("");
		 * article2.setPicUrl("http://localhost:8080/WeiXinEnterprisess/123.jpg"
		 * );
		 * article2.setUrl("http://localhost:8080/WeiXinEnterprisess/123.jpg");
		 * // 整合图文 List<Article> list = new ArrayList<Article>();
		 * list.add(article1); list.add(article2); // 图文转json String String
		 * articlesList = JSONArray.fromObject(list).toString(); // Post的数据
		 * String PostData = SNewsMsg("3", "1", null, "3", articlesList);
		 */
//		  System.out.println(PostData);
//		int result = WeixinUtil.PostMessage(access_token, "POST", POST_URL,
//				PostData); // 打印结果
//		if (0 == result) {
//			System.out.println("操作成功");
//		} else {
//			System.out.println("操作失败");
//		}
	}
}
