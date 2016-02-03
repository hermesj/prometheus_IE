package prometheus_IE;

import java.io.File;
import java.io.IOException;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;

import org.junit.Test;
import org.xml.sax.SAXException;

import de.uni_koeln.spinfo.prometheus_IE.PrometheusReader;

public class ReaderTests {

	@Test
	public void test() throws SAXException, IOException, ParserConfigurationException, JAXBException {
		File testFile = new File("data/descriptionFiles/dmr_description.xml");
		PrometheusReader pr = new PrometheusReader();
		pr.getDescriptions(testFile);
	}

}
