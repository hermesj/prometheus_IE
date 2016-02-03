package de.uni_koeln.spinfo.prometheus_IE;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Search {	
	
	private String query;
	
	private int count;
	
	private Results results;	

	public Results getResults() {
		return results;
	}

	@XmlElement
	public void setResults(Results results) {
		this.results = results;
	}

	public String getQuery() {
		return query;
	}

	@XmlElement
	public void setQuery(String query) {
		this.query = query;
	}

	public int getCount() {
		return count;
	}

	@XmlElement
	public void setCount(int count) {
		this.count = count;
	}

	
	

}
