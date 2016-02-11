package prometheus_IE;

import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.SortedMap;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;

import org.junit.Test;
import org.xml.sax.SAXException;

import de.uni_koeln.spinfo.prometheus_IE.components.NERParser;
import de.uni_koeln.spinfo.prometheus_IE.components.PrometheusReader;

public class ReaderTests {

	@Test
	public void test() throws SAXException, IOException, ParserConfigurationException, JAXBException {
		File testFile = new File("data/descriptionFiles/dmr_description.xml");
		
		PrometheusReader pr = new PrometheusReader();
		
		NERParser nerp = new NERParser();
		
		SortedMap<String, String> descriptions = pr.getDescriptions(testFile);
		Set<String> keySet = descriptions.keySet();
		for (String key : keySet) {
			nerp.doNER(descriptions.get(key));
			System.out.println();
		}
	}
}
