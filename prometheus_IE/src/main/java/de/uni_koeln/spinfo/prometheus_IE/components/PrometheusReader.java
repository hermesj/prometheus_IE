package de.uni_koeln.spinfo.prometheus_IE.components;

import java.io.File;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import de.uni_koeln.spinfo.prometheus_IE.data.Result;
import de.uni_koeln.spinfo.prometheus_IE.data.Search;

public class PrometheusReader {

	public PrometheusReader() {

	}

	public SortedMap<String, String> getDescriptions(File descriptionFile)
			throws JAXBException {
		
		SortedMap<String, String> toReturn = new TreeMap<String, String>();

		JAXBContext jaxbContext = JAXBContext.newInstance(Search.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		Search search = (Search) jaxbUnmarshaller.unmarshal(descriptionFile);
		List<Result> results = search.getResults().getResults();
		for (Result result : results) {
			String desc = result.getDescription();
			if(desc!=null && desc.trim().length()>0){
				toReturn.put(result.getPid(), desc);
			}
			else{
				System.out.println("Empty description of pid element " + result.getPid());
			} 
		}

		return toReturn;

	}

}
