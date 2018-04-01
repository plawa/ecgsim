//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0-b170531.0717 
//         See <a href="https://jaxb.java.net/">https://jaxb.java.net/</a> 
//         Any modifications to this file will be lost upon recompilation of the source schema. 
//         Generated on: 2018.03.04 at 09:30:56 PM CET 
//

package parser.generated.jaxb;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for leadType.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * <p>
 * 
 * <pre>
 * &lt;simpleType name="leadType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="I"/&gt;
 *     &lt;enumeration value="II"/&gt;
 *     &lt;enumeration value="III"/&gt;
 *     &lt;enumeration value="aVR"/&gt;
 *     &lt;enumeration value="aVL"/&gt;
 *     &lt;enumeration value="aVF"/&gt;
 *     &lt;enumeration value="V1"/&gt;
 *     &lt;enumeration value="V2"/&gt;
 *     &lt;enumeration value="V3"/&gt;
 *     &lt;enumeration value="V4"/&gt;
 *     &lt;enumeration value="V5"/&gt;
 *     &lt;enumeration value="V6"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "leadType")
@XmlEnum
public enum LeadType {

	@XmlEnumValue("I")
	I("I", "I/"),

	@XmlEnumValue("II")
	II("II", "II/"),

	@XmlEnumValue("III")
	III("III", "III/"),

	@XmlEnumValue("aVR")
	A_VR("aVR", "aVR/"),

	@XmlEnumValue("aVL")
	A_VL("aVL", "aVL/"),

	@XmlEnumValue("aVF")
	A_VF("aVF", "aVF/"),

	@XmlEnumValue("V1")
	V_1("V1", "V1/"),

	@XmlEnumValue("V2")
	V_2("V2", "V2/"),

	@XmlEnumValue("V3")
	V_3("V3", "V3/"),

	@XmlEnumValue("V4")
	V_4("V4", "V4/"),

	@XmlEnumValue("V5")
	V_5("V5", "V5/"),

	@XmlEnumValue("V6")
	V_6("V6", "V6/");

	private final String value, resourceSubPath;

	LeadType(String v, String resSubPath) {
		value = v;
		resourceSubPath = resSubPath;
	}

	public String value() {
		return value;
	}

	public String resourceSubPath() {
		return resourceSubPath;
	}

	public static LeadType fromValue(String v) {
		for (LeadType c : LeadType.values()) {
			if (c.value.equals(v)) {
				return c;
			}
		}
		throw new IllegalArgumentException(v);
	}

}
