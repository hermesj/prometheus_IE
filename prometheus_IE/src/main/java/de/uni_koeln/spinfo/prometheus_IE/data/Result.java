package de.uni_koeln.spinfo.prometheus_IE.data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "result")
@XmlAccessorType (XmlAccessType.FIELD)
public class Result {
	
	@XmlElement(name="description")
	private String desc;
	
	@XmlElement(name="pid")
	private String pid;

	public String getPid() {
		return pid;
	}

	
	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getDescription() {
		return desc;
	}

	
	public void setDescription(String desc) {
		this.desc = desc;
	}
	

}
