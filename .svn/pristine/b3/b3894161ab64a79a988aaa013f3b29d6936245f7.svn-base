<%@ page language="java" contentType="text/html; charset=gbk"
    pageEncoding="gbk" %>
<%@ page import="org.json.*" %>
<% 
	String result = (String)request.getParameter("result");
	String action = (String)request.getParameter("action");
	
	JSONObject jsonObject = new JSONObject();
	if (action == null)//global exception
	{
		if ("error".equals(result))
			jsonObject.put("success", "false");
			jsonObject.put("msg", "操作异常，请重新尝试!");
	}
	else {
		
		if ("add".equals(action))
		{
			if ("ok".equals(result))
			{
				jsonObject.put("success", "true");
				jsonObject.put("msg", "增加成功！");
			}
			else if ("NotIn".equals(result))
			{
				jsonObject.put("success", "false");
				jsonObject.put("msg", "你不在该群，不能发送");
			}
			else if ("group".equals(result))
			{
				jsonObject.put("success", "true");
				jsonObject.put("msg", "");
				jsonObject.put("groupId", session.getAttribute("Id"));
				jsonObject.put("groupName", session.getAttribute("groupName"));
			}
			else if ("error".equals(result)){
				jsonObject.put("msg", "增加失败！<br />操作异常，请重新尝试!");
			}
			else if("existed".equals(result)){
				jsonObject.put("success", "false");
				jsonObject.put("msg", "已有该用户！");
			}else if("existedFocus".equals(result)){
				jsonObject.put("success", "false");
				jsonObject.put("msg", "已关注该商家！");
			}
		}
		if ("modify".equals(action))
		{
			if ("ok".equals(result))
			{
				jsonObject.put("success", "true");
				jsonObject.put("msg", "修改成功！");
			}
			else if ("error".equals(result)){
				jsonObject.put("msg", "修改失败！<br />操作异常，请重新尝试!");
			}
			else if("modusernamefail".equals(result)){
				jsonObject.put("msg", "该名称已经存在!");
				jsonObject.put("success", "false");
			}
			else if("errorPwd".equals(result)){
				jsonObject.put("msg", "密码不正确!");
				jsonObject.put("success", "false");
			}
		}

		if ("delete".equals(action))
		{
			if ("ok".equals(result))
			{
				jsonObject.put("success", "true");
				jsonObject.put("msg", "删除成功！");
			}
			else if ("error".equals(result))
				jsonObject.put("msg", "删除失败！<br />操作异常，请重新尝试!");
		}
		if ("login".equals(action))
		{
			if ("ok".equals(result))
			{
				jsonObject.put("success", "true");
				jsonObject.put("msg", "登录成功！");
				jsonObject.put("id", session.getAttribute("loginId"));
				//add by zzq 2013-12-2
				jsonObject.put("userType", session.getAttribute("userType"));
				jsonObject.put("imgPath", session.getAttribute("imgPath"));
				
			}
			else if ("error".equals(result))
			{
				jsonObject.put("success", "false");
				jsonObject.put("msg", "登录失败，请重新尝试!");
			}
		}
		
		if ("existedIdol".equals(action))
		{
			if ("existedIdol".equals(result))
			{
				jsonObject.put("success", "false");
				jsonObject.put("msg", "已关注该商家");
			}

		}
		
		else //else all
		{
			if ("ok".equals(result))
			{
				jsonObject.put("success", "true");
				jsonObject.put("msg", "操作成功！");
			}		
		}
	}
	out.println(jsonObject.toString());
%>
