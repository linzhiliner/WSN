package com.huawei.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.struts2.json.annotations.JSON;
import org.springframework.beans.BeanUtils;

import com.huawei.domain.Focus;
import com.huawei.domain.RegisterUser;
import com.huawei.service.IFocusService;
import com.huawei.service.IRegisterUserService;
import com.opensymphony.xwork2.ModelDriven;

public class FocusAction extends BaseAction implements ModelDriven<Focus> {
	private Focus focus = new Focus();
	private IFocusService<Focus> focusService;
	private IRegisterUserService<RegisterUser> registerUserService;

	int total;
	List<RegisterUser> rowsList;

	@JSON(serialize = false)
	public Focus getModel() {
		return focus;
	}

	public void setFocusService(IFocusService<Focus> focusService) {
		this.focusService = focusService;
	}

	public void setRegisterUserService(IRegisterUserService<RegisterUser> registerUserService) {
		this.registerUserService = registerUserService;
	}
	
	
	

	/**
	 * zzq
	 * 2014年1月17日下午12:31:53
	 * isFocused
	 * TODO 检测是否商铺已经被关注
	 * String
	 * @return
	 */
	public String existed() {
		RegisterUser u = (RegisterUser) session.get("registerUserLogin");
		String hql="from Focus f  where f.owner.id="+focus.getOwner().getId()+"and f.idol.id="+u.getId();
		Focus f=focusService.get(hql);
		if(f!=null){
			return "existedIdol";
		}
		return SUCCESS;

	}

	public String list() throws Exception {
		List<Focus> list = new ArrayList<Focus>();
		rowsList = new ArrayList<RegisterUser>();
		String hql = "from Focus f left join fetch f.idol where f.owner.id=" + focus.getOwner().getId()
				+ " order by f.id desc";
		list = focusService.list(hql, (page - 1) * rows, rows, null);
		for (Focus f : list) {
			if (f != null) {
				RegisterUser r = f.getIdol();
				r.setRole(null);
				r.setUserType(null);
				rowsList.add(r);
			}

		}
		hql = "select count(*) from Focus f  where f.owner.id=" + focus.getOwner().getId();
		total = focusService.getTotalCount(hql, null);
		return SUCCESS;
	}

	public String add() throws Exception {
		RegisterUser u = (RegisterUser) session.get("registerUserLogin");
		String hql = "from Focus f  where f.idol.id=" + u.getId() + "and f.owner.id=" + focus.getOwner().getId();
		Focus f = focusService.get(hql);
		if (f != null) {
			return "existedFocus";
		} else {
			focus.setIdol(u);
			focus.setDateCreated(new Date());
			focusService.save(focus);
		}
		return SUCCESS;
	}

	public String modify() throws Exception {
		Focus focusDataBase = focusService.get(Focus.class, focus.getId());
		BeanUtils.copyProperties(focus, focusDataBase, new String[] { "dateCreated" });
		focusService.update(focusDataBase);
		return SUCCESS;
	}

	public String delete() throws Exception {
		focusService.delete(Focus.class, focus.getId());
		return SUCCESS;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	@JSON(name = "rows")
	public List<RegisterUser> getRowsList() {
		return rowsList;
	}

	public void setRowsList(List<RegisterUser> rowsList) {
		this.rowsList = rowsList;
	}

}
