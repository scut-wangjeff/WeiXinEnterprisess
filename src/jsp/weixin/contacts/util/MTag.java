package jsp.weixin.contacts.util;

import net.sf.json.JSONObject;
import jsp.weixin.ParamesAPI.util.ParamesAPI;
import jsp.weixin.ParamesAPI.util.WeixinUtil;

/**
 * 通讯录标签管理类
 * 
 * @date 2015.4.22
 * */
public class MTag {
	// 创建标签地址
	public static String CREATE_TAG_URL = "https://qyapi.weixin.qq.com/cgi-bin/tag/create?access_token=ACCESS_TOKEN";
	// 更新标签地址
	public static String UPDATA_TAG_URL = "https://qyapi.weixin.qq.com/cgi-bin/tag/update?access_token=ACCESS_TOKEN";
	// 删除标签地址
	public static String DELETE_TAG_URL = "https://qyapi.weixin.qq.com/cgi-bin/tag/delete?access_token=ACCESS_TOKEN&tagid=ID";
	// 获取标签成员地址
	public static String GET_TAG_PERSON = "https://qyapi.weixin.qq.com/cgi-bin/tag/get?access_token=ACCESS_TOKEN&tagid=ID";
	// 增加标签成员地址
	public static String ADD_TAG_PERSON = "https://qyapi.weixin.qq.com/cgi-bin/tag/addtagusers?access_token=ACCESS_TOKEN";
	// 删除标签成员地址
	public static String DELETE_TAG_PERSON = "https://qyapi.weixin.qq.com/cgi-bin/tag/deltagusers?access_token=ACCESS_TOKEN";
	// 获取标签列表
	public static String GET_TAG_LIST = "https://qyapi.weixin.qq.com/cgi-bin/tag/list?access_token=ACCESS_TOKEN";

	/**
	 * 创建标签
	 * 
	 * @param tagname
	 *            标签名称。长度为1~64个字符，标签不可与其他同组的标签重名，也不可与全局标签重名
	 * */
	public static String Create_Tag(String tagname) {
		String PostData = "{\"tagname\":\"%s\"}";
		return String.format(PostData, tagname);
	}

	/**
	 * 更新标签名字
	 * 
	 * @param tagid
	 *            标签ID
	 * @param tagname
	 *            标签名称。最长64个字符
	 * */
	public static String Updata_Tag(String tagid, String tagname) {
		String PostData = "{\"tagid\":\"%s\",\"tagname\":\"%s\"}";
		return String.format(PostData, tagid, tagname);
	}

	/**
	 * 删除标签
	 * 
	 * @param tagid
	 *            标签ID
	 * */
	public static String Delete_Tag(String tagid) {
		String delete_url = DELETE_TAG_URL.replace("ID", tagid);
		return delete_url;
	}

	/**
	 * 获取标签成员
	 * 
	 * @param tagid
	 *            标签ID
	 * */
	public static String Get_Tag_Person(String tagid) {
		String get_tagperson_url = GET_TAG_PERSON.replace("ID", tagid);
		return get_tagperson_url;
	}

	/**
	 * 增加标签成员
	 * 
	 * @param tagid
	 *            标签ID
	 * @param userlist
	 *            企业员工ID列表 格式："userlist":[ "user1","user2"]
	 * */
	public static String Add_Tag_Person(String tagid, String userlist,
			String partylist) {
		String PostData = "{\"tagid\": \"%s\",\"userlist\":%s,\"[partylist\":%s}";
		return String.format(PostData, tagid, userlist, partylist);
	}

	/**
	 * 删除标签成员
	 * 
	 * @param tagid
	 *            标签ID
	 * @param userlist
	 *            企业员工ID列表 格式："userlist":[ "user1","user2"]
	 * */
	public static String Delete_Tag_Person(String tagid, String userlist,
			String partylist) {
		String PostData = "{\"tagid\": \"%s\",\"userlist\":%s,\"partylist\":%s}";
		return String.format(PostData, tagid, userlist, partylist);
	}

	/**
	 * 创建标签
	 * 
	 * @param tagname
	 *            标签名称。长度为1~64个字符，标签不可与其他同组的标签重名，也不可与全局标签重名
	 * */
	public static int CreateTag(String tagname,String access_token) {
		// 拼装数据
		String PostData = Create_Tag(tagname);
		// 提交数据，获取结果
		int result = WeixinUtil.PostMessage(access_token, "POST",
				CREATE_TAG_URL, PostData);
		// 打印结果
		if (0 == result) {
			System.out.println("操作成功");
		} else {
			System.out.println("操作失败");
		}

		return result;
	}

	/**
	 * 更新标签
	 * 
	 * @param tagid
	 *            标签ID
	 * @param tagname
	 *            标签名称。长度为1~64个字符，标签不可与其他同组的标签重名，也不可与全局标签重名
	 * */
	public static int UpdateTag(String tagid, String tagname,String access_token) {
		// 拼装数据
		String PostData = Updata_Tag(tagid, tagname);
		// 提交数据，获取结果
		int result = WeixinUtil.PostMessage(access_token, "POST",
				UPDATA_TAG_URL, PostData);
		// 打印结果
		if (0 == result) {
			System.out.println("操作成功");
		} else {
			System.out.println("操作失败");
		}

		return result;
	}

	/**
	 * 删除标签
	 * 
	 * @param tagid
	 *            标签ID
	 * */
	public static int DeleteTag(String tagid,String access_token) {
		// 拼装数据
		String PostData = Delete_Tag(tagid);
		// 提交数据，获取结果
		int result = WeixinUtil.PostMessage(access_token, "POST",
				DELETE_TAG_URL, PostData);
		// 打印结果
		if (0 == result) {
			System.out.println("操作成功");
		} else {
			System.out.println("操作失败");
		}

		return result;
	}

	/**
	 * 获取标签成员
	 * 
	 * @param tagid
	 *            标签ID
	 * */
	public static JSONObject GetTagPerson(String tagid,String access_token) {
		// 拼装数据
		String PostData = Get_Tag_Person(tagid);
		// 提交数据，获取结果
		JSONObject jsonObject = WeixinUtil.GetMessage(access_token, "POST",
				GET_TAG_PERSON, PostData);
		return jsonObject;
	}

	/**
	 * 增加标签成员
	 * 
	 * @param tagid
	 *            标签ID
	 * @param userlist
	 *            企业员工ID列表 格式："userlist":[ "user1","user2"]
	 * @param partylist
	 *            企业部门ID列表，格式 "partylist": [4] 注意：userlist、partylist不能同时为空
	 * */
	public static int AddTagPersons(String tagid, String userlist,
			String partylist,String access_token) {

		// 拼装数据
		String PostData = Add_Tag_Person(tagid, userlist, partylist);
		// 提交数据，获取结果
		int result = WeixinUtil.PostMessage(access_token, "POST",
				ADD_TAG_PERSON, PostData);
		// 打印结果
		if (0 == result) {
			System.out.println("操作成功");
		} else {
			System.out.println("操作失败");
		}

		return result;

	}

	/**
	 * 删除标签成员
	 * 
	 * @param tagid
	 *            标签ID
	 * @param userlist
	 *            企业员工ID列表 格式："userlist":[ "user1","user2"]
	 * @param partylist
	 *            企业部门ID列表，格式 "partylist": [4] 注意：userlist、partylist不能同时为空
	 * */
	public static int DeleteTagPersons(String tagid, String userlist,
			String partylist,String access_token) {
		// 拼装数据
		String PostData = Delete_Tag_Person(tagid, userlist, partylist);
		// 提交数据，获取结果
		int result = WeixinUtil.PostMessage(access_token, "POST",
				DELETE_TAG_PERSON, PostData);
		// 打印结果
		if (0 == result) {
			System.out.println("操作成功");
		} else {
			System.out.println("操作失败");
		}

		return result;
	}

	/**
	 * 
	 * 获取标签列表
	 * 
	 * 
	 * */
	public static JSONObject GetTagList(String access_token) {
		// 提交数据，获取结果
		JSONObject jsonObject = WeixinUtil.PostMessage(access_token, "POST",
				GET_TAG_LIST);
		return jsonObject;
	}

	// 测试标签功能
	public static void main(String[] args) {
		// 调取凭证
		@SuppressWarnings("unused")
		String access_token = WeixinUtil.getAccessToken(ParamesAPI.corpId,
				ParamesAPI.secret).getToken();
		/** 新建标签 */
		/* CreateTag("新建标签2",access_token); */
		/** 更新标签 */
		/* UpdateTag("3", "新建",access_token); */
		/** 删除标签 */
		/* DeleteTag("3",access_token); */
		/** 获取标签成员 */
		/* System.out.println(GetTagPerson("1"),access_token); */
		/** 增加标签成员 */
		/* AddTagPersons("4", "[\"liuwenzhuo\",\"wuzhe\"]", "",access_token); */
		/** 删除标签成员 */
		/* DeleteTagPersons("4", "[\"liuwenzhuo\",\"wuzhe\"]", "",access_token); */
		/** 获取标签列表 */
		/* System.out.println(GetTagList(),access_token); */
	}
}
