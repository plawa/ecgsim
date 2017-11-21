package parser.mapper;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.xml.bind.JAXBException;

import org.junit.Test;

import parser.generated.jaxb.Ecg;
import resources.TestConstants;

public class EcgMarshallerTest {

	private static final String EXPECTED_XML = "<?xmlversion=\"1.0\"encoding=\"UTF-8\"?><ecgrate=\"50\"length=\"7000\"><leadname=\"leadName\"><part>leadPart</part></lead></ecg>";

	@Test
	public void testUnmarshall() throws JAXBException {
		String filepath = TestConstants.TESTDATA_DIR + "1.ekg";
		Ecg ecg = EcgMarshaller.unmarshall(filepath);
		assertEquals(Short.valueOf((short) 8000), ecg.getLength());
	}

	@Test
	public void testMarshall() throws JAXBException, FileNotFoundException {
		final String outputFilepath = TestConstants.TESTDATA_DIR + "output.xml";
		Ecg ecg = createTestEcg();
		EcgMarshaller.marshall(ecg, outputFilepath);

		String outputXml = readFile(outputFilepath);
		assertEquals(EXPECTED_XML, outputXml);
	}

	private String readFile(final String outputFilepath) throws FileNotFoundException {
		File fileWritten = new File(outputFilepath);
		String outputXml = null;
		try (Scanner scanner = new Scanner(fileWritten)) {
			StringBuilder s = new StringBuilder();
			while (scanner.hasNext()) {
				s.append(scanner.next());
			}
			outputXml = s.toString();
		}
		return outputXml;
	}

	private Ecg createTestEcg() {
		Ecg ecg = new Ecg();
		ecg.setLength(Short.valueOf((short) 7000));
		ecg.setRate(Short.valueOf((short) 50));
		Ecg.Lead lead = new Ecg.Lead();
		lead.setName("leadName");
		lead.setPart("leadPart");
		ecg.getLead().add(lead);
		return ecg;
	}
}
