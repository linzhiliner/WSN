package com.huawei.action;

import java.io.File;
import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport implements SessionAware, RequestAware {

	protected Map session;
	protected Map request;
	
	protected int index;
	protected int rows = 10;
	protected int page = 1;
	
	private File file;
	private String fileFileName;
	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	

	public void setSession(Map session)
	{
		this.session = session;
	}
	
	public void setRequest(Map request)
	{
		this.request = request;
	}
	
	public int getIndex()
	{
		return index;
	}
	public void setIndex(int index)
	{
		this.index = index;
	}

	
	public int getRows() {
		return rows;
	}


	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}
}
