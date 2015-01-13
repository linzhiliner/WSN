package com.huawei.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.json.annotations.JSON;

import com.huawei.domain.Sensor;
import com.huawei.service.ISensorService;
import com.opensymphony.xwork2.ModelDriven;

public class SensorAction extends BaseAction implements ModelDriven<Sensor> {
	private Sensor sensor = new Sensor();
	private ISensorService<Sensor> sensorService;
	int total;
	List<Sensor> rowsList;
	
	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public void setSensorService(ISensorService<Sensor> sensorService) {
		this.sensorService = sensorService;
	}

	@JSON(name="rows")
	public List<Sensor> getRowsList() {
		return rowsList;
	}

	public void setRowsList(List<Sensor> rowsList) {
		this.rowsList = rowsList;
	}

	@JSON(serialize=false)
	public Sensor getModel() {
		// TODO Auto-generated method stub
		return sensor;
	}
	
	public String list() throws Exception
	{
		String ip =sensor.getIp();
		String hql ="from Sensor s where s.sign=false and s.ip='"+sensor.getIp()+"'";
		sensor = sensorService.get(hql);
		if(sensor==null){
			hql="from Sensor s where s.id=(select max(id) from s where s.ip='"+ip+"') ";
			sensor=sensorService.get(hql);
		}
		System.out.println(sensor.getValue());
		rowsList = new ArrayList<Sensor>();
		rowsList.add(sensor);
		sensor.setSign(true);
		sensorService.update(sensor);
		return SUCCESS;
	}
	
	public String sensorList() throws Exception
	{
		
		String hql ="select distinct s.ip from Sensor s where s.dataType='"+sensor.getDataType()+"' order by s.ip ";
		rowsList = sensorService.list(hql, (page -1)*rows, rows, null);
		return SUCCESS;
	}

}
