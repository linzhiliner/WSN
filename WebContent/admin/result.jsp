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
			jsonObject.put("msg", "�����쳣�������³���!");
	}
	else {
		
		if ("add".equals(action))
		{
			if ("ok".equals(result))
			{
				jsonObject.put("success", "true");
				jsonObject.put("msg", "���ӳɹ���");
			}
			else if ("NotIn".equals(result))
			{
				jsonObject.put("success", "false");
				jsonObject.put("msg", "�㲻�ڸ�Ⱥ�����ܷ���");
			}
			else if ("group".equals(result))
			{
				jsonObject.put("success", "true");
				jsonObject.put("msg", "");
				jsonObject.put("groupId", session.getAttribute("Id"));
				jsonObject.put("groupName", session.getAttribute("groupName"));
			}
			else if ("error".equals(result)){
				jsonObject.put("msg", "����ʧ�ܣ�<br />�����쳣�������³���!");
			}
			else if("existed".equals(result)){
				jsonObject.put("success", "false");
				jsonObject.put("msg", "���и��û���");
			}else if("existedFocus".equals(result)){
				jsonObject.put("success", "false");
				jsonObject.put("msg", "�ѹ�ע���̼ң�");
			}
		}
		if ("modify".equals(action))
		{
			if ("ok".equals(result))
			{
				jsonObject.put("success", "true");
				jsonObject.put("msg", "�޸ĳɹ���");
			}
			else if ("error".equals(result)){
				jsonObject.put("msg", "�޸�ʧ�ܣ�<br />�����쳣�������³���!");
			}
			else if("modusernamefail".equals(result)){
				jsonObject.put("msg", "�������Ѿ�����!");
				jsonObject.put("success", "false");
			}
			else if("errorPwd".equals(result)){
				jsonObject.put("msg", "���벻��ȷ!");
				jsonObject.put("success", "false");
			}
		}

		if ("delete".equals(action))
		{
			if ("ok".equals(result))
			{
				jsonObject.put("success", "true");
				jsonObject.put("msg", "ɾ���ɹ���");
			}
			else if ("error".equals(result))
				jsonObject.put("msg", "ɾ��ʧ�ܣ�<br />�����쳣�������³���!");
		}
		if ("login".equals(action))
		{
			if ("ok".equals(result))
			{
				jsonObject.put("success", "true");
				jsonObject.put("msg", "��¼�ɹ���");
				jsonObject.put("id", session.getAttribute("loginId"));
				//add by zzq 2013-12-2
				jsonObject.put("userType", session.getAttribute("userType"));
				jsonObject.put("imgPath", session.getAttribute("imgPath"));
				
			}
			else if ("error".equals(result))
			{
				jsonObject.put("success", "false");
				jsonObject.put("msg", "��¼ʧ�ܣ������³���!");
			}
		}
		
		if ("existedIdol".equals(action))
		{
			if ("existedIdol".equals(result))
			{
				jsonObject.put("success", "false");
				jsonObject.put("msg", "�ѹ�ע���̼�");
			}

		}
		
		else //else all
		{
			if ("ok".equals(result))
			{
				jsonObject.put("success", "true");
				jsonObject.put("msg", "�����ɹ���");
			}		
		}
	}
	out.println(jsonObject.toString());
%>
