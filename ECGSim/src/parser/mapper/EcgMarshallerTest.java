package parser.mapper;

import static org.junit.Assert.assertEquals;

import javax.xml.bind.JAXBException;

import org.junit.Test;

import parser.generated.jaxb.Ecg;
import resources.TestConstants;

public class EcgMarshallerTest {

	@Test
	public void testUnmarshall() throws JAXBException {
		String filepath = TestConstants.TESTDATA_DIR + "1.ekg";
		Ecg ecg = EcgMarshaller.unmarshall(filepath);
		assertEquals(Short.valueOf((short) 8000), ecg.getLength());
	}

}
