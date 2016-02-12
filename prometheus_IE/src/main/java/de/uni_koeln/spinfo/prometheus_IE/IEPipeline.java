package de.uni_koeln.spinfo.prometheus_IE;

import java.io.File;
import java.util.Set;
import java.util.SortedMap;

import javax.xml.bind.JAXBException;

import de.uni_koeln.spinfo.prometheus_IE.components.NERParser;
import de.uni_koeln.spinfo.prometheus_IE.components.PrometheusReader;
import de.uni_koeln.spinfo.prometheus_IE.components.TimeExParser;

public class IEPipeline {
	
	public static void doIE(File toProcess) throws JAXBException{
		
		PrometheusReader pr = new PrometheusReader();
		
		NERParser nerp = new NERParser();
		
		TimeExParser texp = new TimeExParser();
		
		SortedMap<String, String> descriptions = pr.getDescriptions(toProcess);
		Set<String> keySet = descriptions.keySet();
		for (String key : keySet) {
			String doneNER = texp.deTimeEx(descriptions.get(key));// nerp.doNER(descriptions.get(key));
			
			System.out.println(doneNER);
			System.out.println("************************************");
			descriptions.put(key, doneNER);
		}
	}

}
