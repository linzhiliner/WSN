package com.huawei.action;

import java.util.Date;
import java.util.List;

import org.apache.struts2.json.annotations.JSON;
import org.springframework.beans.BeanUtils;

import com.huawei.domain.Focus;
import com.huawei.domain.ShopBlog;
import com.huawei.domain.WeiBo;
import com.huawei.service.IShopBlogService;
import com.opensymphony.xwork2.ModelDriven;

public class ShopBlogAction extends BaseAction implements ModelDriven<ShopBlog> {
	private ShopBlog shopBlog = new ShopBlog();
	private IShopBlogService<ShopBlog> shopBlogService;

	
	int total;
	List<ShopBlog> rowsList;
	
	
	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	@JSON(name="rows")
	public List<ShopBlog> getRowsList() {
		return rowsList;
	}

	public void setRowsList(List<ShopBlog> rowsList) {
		this.rowsList = rowsList;
	}

	@Override
	public ShopBlog getModel() {
		return shopBlog;
	}

	public void setShopBlogService(IShopBlogService<ShopBlog> shopBlogService) {
		this.shopBlogService = shopBlogService;
	}

	public String list() throws Exception {

		String hql = "from ShopBlog sb left join fetch sb.registerUser ";
		rowsList = shopBlogService.list(hql, (page -1)*rows, rows, null);
		hql = "select count(*) from ShopBlog sb";
		total = shopBlogService.getTotalCount(hql, null);
		return SUCCESS;
	}

	public String add() throws Exception {
		shopBlog.setDateCreated(new Date());
		shopBlogService.save(shopBlog);
		return SUCCESS;
	}

	public String modify() throws Exception {
		ShopBlog shopBlogDataBase = shopBlogService.get(ShopBlog.class, shopBlog.getId());
		BeanUtils.copyProperties(shopBlog, shopBlogDataBase,new String[] { "dateCreated" });
		shopBlogService.update(shopBlogDataBase);
		return SUCCESS;
	}

	public String delete() throws Exception {
		shopBlogService.delete(ShopBlog.class, shopBlog.getId());
		return SUCCESS;
	}

	
	
	
}
