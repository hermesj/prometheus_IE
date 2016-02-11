package de.uni_koeln.spinfo.prometheus_IE.data;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlList;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "results")
@XmlAccessorType (XmlAccessType.FIELD)
public class Results {
	
	@XmlElement(name="result")
    private List<Result> results;

	public List<Result> getResults() {
		return results;
	}

	
	public void setResult(List<Result> results) {
		this.results = results;
	}	
	
}
