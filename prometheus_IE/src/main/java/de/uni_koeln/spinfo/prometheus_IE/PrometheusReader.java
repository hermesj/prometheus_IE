package de.uni_koeln.spinfo.prometheus_IE;

import java.io.File;
import java.util.List;
import java.util.SortedMap;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class PrometheusReader {

	public PrometheusReader() {

	}

	public SortedMap<String, String> getDescriptions(File descriptionFile)
			throws JAXBException {

		JAXBContext jaxbContext = JAXBContext.newInstance(Search.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		Search search = (Search) jaxbUnmarshaller.unmarshal(descriptionFile);
		List<Result> results = search.getResults().getResults();
		for (Result result : results) {
			System.out.println(result.getDescription());
		}

		return null;

	}

}
