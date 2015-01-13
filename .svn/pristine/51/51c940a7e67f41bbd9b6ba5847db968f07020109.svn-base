package com.huawei.action;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.List;

import org.apache.struts2.json.annotations.JSON;

import com.huawei.domain.Faq;
import com.huawei.service.IFaqService;
import com.opensymphony.xwork2.ModelDriven;

public class FaqAction extends BaseAction implements ModelDriven<Faq>{
	private Faq faq = new Faq();
	private IFaqService<Faq> faqService;
	int total;
	List<Faq> rowsList;
	private String ids;

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public int getTotal() {
		return total;
	}

	@JSON(name="rows")
	public List<Faq> getRowsList() {
		return rowsList;
	}

	public void setRowsList(List<Faq> rowsList) {
		this.rowsList = rowsList;
	}

	public void setTotal(int total) {
		this.total = total;
	}
	public void setFaqService(IFaqService<Faq> faqService) {
		this.faqService = faqService;
	}

	@Override
	public Faq getModel() {
		// TODO Auto-generated method stub
		return faq;
	}


	public String list(){
		String hql = "from Faq f where f.handle=0";
		rowsList = faqService.list(hql, (page -1)*rows, rows, null);
		hql = "select count(*) from Faq f where f.handle=0";
		total = faqService.getTotalCount(hql, null);
		
		return SUCCESS;
	}
	
	public String addFaq() throws UnsupportedEncodingException{
		faq.setDateCreated(new Date());
		faq.setHandle(0); 
		faqService.save(faq);
		return SUCCESS;
	}

	public String delete(){
		System.out.println(ids);
		faqService.modifyHandle(ids);
		return SUCCESS;
	}
	
	public String modify(){
		return SUCCESS;
	}
}
