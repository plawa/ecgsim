/**
 * 
 */
package parser.mapper;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import parser.generated.jaxb.Ecg;

/**
 * @author Piotrek
 *
 */
public class EcgMarshaller {

	static {
		System.setProperty("javax.xml.bind.JAXBContextFactory", "org.eclipse.persistence.jaxb.JAXBContextFactory");
	}

	public static Ecg unmarshall(String fileUrl) throws JAXBException {
		File file = new File(fileUrl);
		JAXBContext jaxbContext = JAXBContext.newInstance(Ecg.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		Ecg ecg = (Ecg) jaxbUnmarshaller.unmarshal(file);
		return ecg;
	}
}
