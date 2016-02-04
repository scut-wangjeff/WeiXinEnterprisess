<%@ page language="java" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>This is WeiXinEnterprises @author Engineer-Jsp</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
</head>

<body>
	<center>
		<p>
			<a
				href="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx9641b0c5199a748d&redirect_uri=http%3A%2F%2Fa763246630.xicp.net%3A16669%2FWeiXinEnterprisess%2Foauth2Servlet&response_type=code&scope=snsapi_userinfo&state=a123#wechat_redirect

">
				进入企业 </a>
		</p>
		<a href="frontedjs.html">测试wxjs</a>
		<%-- 		<%
			String UserID = request.getAttribute("UserID").toString();
			out.print("UserID:" + UserID);
		%> --%>

		<img src="C:/Users/Administrator/Desktop/13723251.jpg" style="margin-bottom: 5px; width: 60px; height: 60px;" />
	</center>
</body>
</html>
