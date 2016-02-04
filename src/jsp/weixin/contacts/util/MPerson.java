package jsp.weixin.contacts.util;

import net.sf.json.JSONObject;
import jsp.weixin.ParamesAPI.util.ParamesAPI;
import jsp.weixin.ParamesAPI.util.WeixinUtil;

/**
 * 通讯录成员管理类
 * 
 * @date 2015.4.22
 * */
public class MPerson {
	// 创建成员地址
	public static String CREATE_URL = "https://qyapi.weixin.qq.com/cgi-bin/user/create?access_token=ACCESS_TOKEN";
	// 更新成员地址
	public static String UPDATA_URL = "https://qyapi.weixin.qq.com/cgi-bin/user/update?access_token=ACCESS_TOKEN";
	// 删滁成员地址
	public static String DELETE_URL = "https://qyapi.weixin.qq.com/cgi-bin/user/delete?access_token=ACCESS_TOKEN&userid=ID";
	// 批量删滁成员地址
	public static String DELETES_URL = "https://qyapi.weixin.qq.com/cgi-bin/user/batchdelete?access_token=ACCESS_TOKEN";
	// 获取成员地址
	public static String GET_PERSON_URL = "https://qyapi.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN&userid=ID";
	// 获取部门成员地址
	public static String GET_GROUP_URL = "https://qyapi.weixin.qq.com/cgi-bin/user/simplelist?access_token=ACCESS_TOKEN&department_id=ID&fetch_child=0&status=0";
	// 获取部门成员详情地址
	public static String GET_GROUPINFO_URL = "https://qyapi.weixin.qq.com/cgi-bin/user/list?access_token=ACCESS_TOKEN&department_id=ID&fetch_child=0&status=0";
	// 邀请成员关注
	public static String INVITE_PERSON_CONCERN = "https://qyapi.weixin.qq.com/cgi-bin/invite/send?access_token=ACCESS_TOKEN ";

	/**
	 * 创建成员
	 * 
	 * @param userid
	 *            员工UserID。对应管理端的帐号，企业内必须唯一。长度为1~64个字符
	 * @param name
	 *            成员名称。长度为1~64个字符
	 * @param department
	 *            成员所属部门id列表 格式： "department": [x, y]
	 * @param position
	 *            职位信息
	 * @param mobile
	 *            手机号码。企业内必须唯一，mobile/weixinid/email三者不能同时为空
	 * @param gender
	 *            性别。gender=0表示男，=1表示女。默认gender=0
	 * @param tel
	 *            办公电话。长度为0~64个字符
	 * @param email
	 *            邮箱。长度为0~64个字符。企业内必须唯一
	 * @param weixinid
	 *            微信号。企业内必须唯一
	 * */
	public static String Create(String userid, String name, String department,
			String position, String mobile, String tel, String email,
			String weixinid) {
		String PostData = "{\"userid\": \"%s\",\"name\":\"%s\",\"department\":%s,\"position\": \"%s\",\"mobile\": \"%s\",\"tel\": \"%s\",\"email\": \"%s\",\"weixinid\":\"%s\"}";
		return String.format(PostData, userid, name, department, position,
				mobile, tel, email, weixinid);
	}

	/**
	 * 更新成员
	 * 
	 * @param userid
	 *            员工UserID。对应管理端的帐号，企业内必须唯一。长度为1~64个字符
	 * @param name
	 *            成员名称。长度为1~64个字符
	 * @param department
	 *            成员所属部门id列表 格式： "department": [x]
	 * @param position
	 *            职位信息
	 * @param mobile
	 *            手机号码。企业内必须唯一，mobile/weixinid/email三者不能同时为空
	 * @param gender
	 *            性别。gender=0表示男，=1表示女。默认gender=0
	 * @param tel
	 *            办公电话。长度为0~64个字符
	 * @param email
	 *            邮箱。长度为0~64个字符。企业内必须唯一
	 * @param weixinid
	 *            微信号。企业内必须唯一
	 * @param enable
	 *            启用/禁用成员。1表示启用成员，0表示禁用成员
	 * */
	public static String Updata(String userid, String name, String department,
			String position, String mobile, String tel, String email,
			String weixinid, String enable) {
		String PostData = "{\"userid\": \"%s\",\"name\": \"%s\",\"department\":%s,\"position\": \"%s\",\"mobile\": \"%s\",\"tel\": \"%s\",\"email\": \"%s\",\"weixinid\": \"%s\",\"enable\": \"%s\"}";
		return String.format(PostData, userid, name, department, position,
				mobile, tel, email, weixinid, enable);
	}

	/**
	 * 删除成员
	 * 
	 * @param userid
	 *            员工UserID。对应管理端的帐号
	 * */
	public static String Delete(String userid) {
		String delete_url = DELETE_URL.replace("ID", userid);
		return delete_url;
	}

	/**
	 * 批量删除成员
	 * 
	 * @param userid
	 *            员工UserID。对应管理端的帐号
	 * */
	public static String Deletes(String userids) {
		String PostData = "{\"useridlist\":%s}";
		return String.format(PostData, userids);
	}

	/**
	 * 邀请成员关注
	 * 
	 * @param userid
	 *            员工UserID。对应管理端的帐号
	 * */
	public static String Invite(String userid) {
		String PostData = "{\"userid\":\"%s\"}";
		return String.format(PostData, userid);
	}

	/**
	 * 获取成员
	 * 
	 * @param userid
	 *            员工UserID。对应管理端的帐号
	 * */
	public static String GPerson(String userid) {
		String getperson_url = GET_PERSON_URL.replace("ID", userid);
		return getperson_url;
	}

	/**
	 * 获取部门成员
	 * 
	 * @param department_id
	 *            获取的部门id
	 * @param fetch_child
	 *            1/0：是否递归获取子部门下面的成员 （可选）
	 * @param status
	 *            0获取全部员工，1获取已关注成员列表，2获取禁用成员列表，4获取未关注成员列表。status可叠加 （可选）
	 * */
	public static String GGroup(String department_id) {
		String getgroup_url = GET_GROUP_URL.replace("ID", department_id);
		return getgroup_url;

	}

	/**
	 * 添加 修改成员
	 * 
	 * @param userid
	 *            员工UserID。对应管理端的帐号，企业内必须唯一。长度为1~64个字符
	 * @param name
	 *            成员名称。长度为1~64个字符
	 * @param department
	 *            成员所属部门id列表 格式： "department": [x, y]
	 * @param position
	 *            职位信息
	 * @param mobile
	 *            手机号码。企业内必须唯一，mobile/weixinid/email三者不能同时为空
	 * @param gender
	 *            性别。gender=0表示男，=1表示女。默认gender=0
	 * @param tel
	 *            办公电话。长度为0~64个字符
	 * @param email
	 *            邮箱。长度为0~64个字符。企业内必须唯一
	 * @param weixinid
	 *            微信号。企业内必须唯一
	 * */
	public static int UpdatePerson(String URL_TYPE, String userid, String name,
			String department, String position, String mobile, String tel,
			String email, String weixinid, String enable, String access_token) {

		int result = 0;
		String PostData = "";

		// 拼装数据
		if (URL_TYPE == "add") {
			PostData = Create(userid, name, position, department, mobile, tel,
					email, weixinid);
			result = WeixinUtil.PostMessage(access_token, "POST", CREATE_URL,
					PostData);
		} else {
			PostData = Updata(userid, name, department, position, mobile, tel,
					email, weixinid, enable);
			result = WeixinUtil.PostMessage(access_token, "POST", UPDATA_URL,
					PostData);
		}
		System.out.println(PostData);
		// 打印结果
		if (0 == result) {
			System.out.println("操作成功");
		} else {
			System.out.println("操作失败");
		}
		return result;
	}

	/**
	 * 删除成员
	 * 
	 * @param userid
	 *            员工UserID。对应管理端的帐号
	 * */
	public static int UpdatePerson(String userid, String access_token) {
		// 拼装数据
		String PostData = Delete(userid);
		// 获取结果
		int result = WeixinUtil.PostMessage(access_token, "POST", PostData,
				PostData);
		if (0 == result) {
			System.out.println("操作成功");
		} else {
			System.out.println("操作失败");
		}
		return result;
	}

	/**
	 * 批量删除成员
	 * 
	 * @param userid
	 *            员工UserID。对应管理端的帐号
	 * */
	public static int DeletePersons(String userids, String access_token) {
		// 拼组数据
		String PostData = Deletes(userids);
		System.out.println(PostData);
		// 提交数据，获取结果
		int result = WeixinUtil.PostMessage(access_token, "POST", DELETES_URL,
				PostData);
		if (0 == result) {
			System.out.println("操作成功");
		} else {
			System.out.println("操作失败");
		}
		return result;
	}

	/**
	 * 获取成员
	 * 
	 * @param userid
	 *            员工UserID。对应管理端的帐号
	 * */
	public static JSONObject GetPerson(String userid, String access_token) {
		// 拼组数据
		String PostData = GPerson(userid);
		// 提交数据，获取结果
		JSONObject jsonObject = WeixinUtil.GetMessage(access_token, "POST",
				GET_PERSON_URL, PostData);
		return jsonObject;
	}

	/**
	 * 获取部门成员
	 * 
	 * @param department_id
	 *            获取的部门id
	 * @param fetch_child
	 *            1/0：是否递归获取子部门下面的成员 （可选）
	 * @param status
	 *            0获取全部员工，1获取已关注成员列表，2获取禁用成员列表，4获取未关注成员列表。status可叠加 （可选）
	 * */
	public static JSONObject GetDepartmentPersons(String departmentid,
			String access_token) {
		// 拼组数据
		String PostData = GGroup(departmentid);
		// 提交数据，获取结果
		JSONObject jsonObject = WeixinUtil.GetMessage(access_token, "POST",
				GET_GROUP_URL, PostData);
		return jsonObject;
	}

	/**
	 * 获取部门成员(详情)
	 * 
	 * @param department_id
	 *            获取的部门id
	 * @param fetch_child
	 *            1/0：是否递归获取子部门下面的成员 （可选）
	 * @param status
	 *            0获取全部员工，1获取已关注成员列表，2获取禁用成员列表，4获取未关注成员列表。status可叠加 （可选）
	 * */
	public static JSONObject GetDepartmentPersonsinfo(String departmentid,
			String access_token) {
		// 拼组数据
		String PostData = GGroup(departmentid);
		// 提交数据，获取结果
		JSONObject jsonObject = WeixinUtil.GetMessage(access_token, "POST",
				GET_GROUPINFO_URL, PostData);
		return jsonObject;
	}

	/**
	 * 邀请成员关注
	 * 
	 * @param userid
	 *            成员用户名
	 * 
	 * @param invite_tips
	 *            邀请提示语（只有认证号可以使用）
	 */
	public static int Getinvite_personconcern(String userid, String access_token) {

		String PostData = Invite(userid);
		int result = WeixinUtil.PostMessage(access_token, "POST",
				INVITE_PERSON_CONCERN, PostData);
		if (0 == result) {
			System.out.println("操作成功");
		} else {
			System.out.println("操作失败");
		}
		return result;
	}

	// 测试

	@SuppressWarnings("unused")
	public static void main(String[] args) {

		/** 创建成员 */
		// 调取凭证
		String access_token = WeixinUtil.getAccessToken(ParamesAPI.corpId,
				ParamesAPI.secret).getToken();
		/*
		 * UpdatePerson("add", "ww", "ww", "程序猿", "[1]", "15555551234",
		 * "0731-666666", "ww@qq.com", "a55555555", "",access_token);
		 */
		/** 修改成员 */
		/*
		 * UpdatePerson("update", "ls", "ls", "程序猿", "[1]", "15212124545",
		 * "0731-888888", "ls@qq.com", "a456456465", "1",access_token);
		 */
		/** 删除成员 */
		/*
		 * UpdatePerson("zhangsan",access_token);
		 */
		/** 批量删除成员 */
		/*
		 * DeletePersons("[\"ls\",\"ww\"]",access_token);
		 */
		/** 获取成员 */
		/*
		 * GetPerson("liuwenzhuo",access_token);
		 */
		/** 获取部门成员 */
		/*
		 * GetDepartmentPersons("1",access_token)
		 */
		/** 获取部门详情 */
		/*
		 * GetDepartmentPersonsinfo("1",access_token);
		 */
		/** 邀请成员关注 */
		/*
		 * Getinvite_personconcern("wuzhe",access_token);
		 */

	}

}
