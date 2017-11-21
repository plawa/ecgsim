package parser.mapper;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import parser.generated.jaxb.Ecg;

/**
 * @author Piotrek
 *
 */
public class EcgMarshaller {

	private static final String VALUE_JAXB_JAXB_CONTEXT_FACTORY = "org.eclipse.persistence.jaxb.JAXBContextFactory";
	private static final String PROPERTY_JAVAX_XML_BIND_JAXB_CONTEXT_FACTORY = "javax.xml.bind.JAXBContextFactory";

	static {
		System.setProperty(PROPERTY_JAVAX_XML_BIND_JAXB_CONTEXT_FACTORY, VALUE_JAXB_JAXB_CONTEXT_FACTORY);
	}

	public static Ecg unmarshall(String fileUrl) throws JAXBException {
		File file = new File(fileUrl);
		JAXBContext jaxbContext = JAXBContext.newInstance(Ecg.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		Ecg ecg = (Ecg) jaxbUnmarshaller.unmarshal(file);
		return ecg;
	}

	public static void marshall(Ecg data, String fileUrl) throws JAXBException {
		File outputFile = new File(fileUrl);
		JAXBContext jaxbContext = JAXBContext.newInstance(Ecg.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		jaxbMarshaller.marshal(data, outputFile);
	}
}
