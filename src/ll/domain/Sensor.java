package ll.domain;

import java.io.Serializable;
import java.util.Date;

public class Sensor extends BaseDomainObject implements Serializable{
	private String dataType;
	private double value;
	private String ip;    
	private boolean sign; 
	private static final long serialVersionUID = 48L;


	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}


	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public boolean isSign() {
		return sign;
	}

	public void setSign(boolean sign) {
		this.sign = sign;
	}

}
