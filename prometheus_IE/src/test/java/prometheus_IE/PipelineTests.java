package prometheus_IE;

import static org.junit.Assert.*;

import java.io.File;

import javax.xml.bind.JAXBException;

import org.junit.Test;

import de.uni_koeln.spinfo.prometheus_IE.IEPipeline;

public class PipelineTests {

	@Test
	public void test() throws JAXBException {
		IEPipeline.doIE(new File("data/descriptionFiles/dmr_description.xml"));
	}

}
 