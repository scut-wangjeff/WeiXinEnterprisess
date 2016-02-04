package jsp.weixin.contacts.util;

import net.sf.json.JSONObject;
import jsp.weixin.ParamesAPI.util.ParamesAPI;
import jsp.weixin.ParamesAPI.util.WeixinUtil;

/**
 * 通讯录部门管理类
 * 
 * 
 * @date 2015.4.22
 */
public class MGroup {

	// 创建部门地址
	public static String CREATE_URL = "https://qyapi.weixin.qq.com/cgi-bin/department/create?access_token=ACCESS_TOKEN";
	// 更新部门地址
	public static String UPDATE_URL = "https://qyapi.weixin.qq.com/cgi-bin/department/update?access_token=ACCESS_TOKEN";
	// 删除部门地址
	public static String DELETE_URL = "https://qyapi.weixin.qq.com/cgi-bin/department/delete?access_token=ACCESS_TOKEN&id=ID";
	// 获取部门列表地址
	public static String GETLIST_URL = "https://qyapi.weixin.qq.com/cgi-bin/department/list?access_token=ACCESS_TOKEN";

	/**
	 * 创建部门
	 * 
	 * @param name
	 *            部门名称。长度限制为1~64个字符
	 * @param parentid
	 *            父亲部门id。根部门id为1
	 * */
	public static String Create(String name, String parentid) {
		String Postjson = "{\"name\":\"%s\",\"parentid\":\"%s\"}";
		return String.format(Postjson, name, parentid);
	}

	/**
	 * 更新部门
	 * 
	 * @param name
	 *            更新的部门名称。长度限制为0~64个字符。修改部门名称时指定该参数
	 * @param id
	 *            部门id
	 * */
	public static String Update(String name, String id) {
		String Postjson = "{\"id\":\"%s\",\"name\":\"%s\"";
		return String.format(Postjson, name, id);
	}

	/**
	 * 删除部门
	 * 
	 * @param id
	 *            部门id
	 * */
	public static String Delete(String id) {
		String delete_url = DELETE_URL.replace("ID", id);
		return delete_url;
	}

	// 部门增删改
	public static int UpdateDepartment(String URL_TYPE, String deptmentid,
			String departmentname, String access_token) {
		// 拼装数据
		/* String PostData = Create("新建部门", "1"); */

		// 删除部门
		/* String PostData = Delete("5"); */

		// 修改部门
		/* String PostData = Update("6", "实施部"); */

		// 查询部门

		/* System.out.println(PostData); */
		int result = 0;
		String PostData = null;
		// 提交数据,获取结果
		if (URL_TYPE.equals("add")) {
			PostData = Create(departmentname, deptmentid);
			result = WeixinUtil.PostMessage(access_token, "POST", CREATE_URL,
					PostData);
		} else if (URL_TYPE.equals("delete")) {
			PostData = Delete(deptmentid);
			result = WeixinUtil.PostMessage(access_token, "POST", DELETE_URL,
					PostData);
		} else {
			PostData = Update(deptmentid, departmentname);
			result = WeixinUtil.PostMessage(access_token, "POST", UPDATE_URL,
					PostData);
		}

		// 打印结果
		if (0 == result) {
			System.out.println("操作成功");
		} else {
			System.out.println("操作失败");
		}
		return result;
	}

	// 获取部门列表
	public static JSONObject GetAll_Department(String access_token) {

		JSONObject jsonObject = WeixinUtil.PostMessage(access_token, "POST",
				GETLIST_URL);
		return jsonObject;
	}

	/**
	 * 测试方法
	 */
	public static void main(String[] args) {
		// 调取凭证
		//@SuppressWarnings("unused")

		
		String access_token = WeixinUtil.getAccessToken(ParamesAPI.corpId,
				ParamesAPI.secret).getToken();
		System.out.println(access_token);
		/**
		 * 测试获取部门列表 System.out.println(GetAll_Department(),access_token); 添加部门
		 * System.out.println(UpdateDepartment("add", "1", "销售部"),access_token);
		 * 修改部门 System.out.println(UpdateDepartment("update", "7",
		 * "工程部"),access_token);
		 * 删除部门System.out.println(UpdateDepartment("delete", "7",
		 * ""),access_token);
		 */

	}
}
