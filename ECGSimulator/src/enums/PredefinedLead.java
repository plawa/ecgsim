package enums;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "leadName")
@XmlEnum
public enum PredefinedLead {
	@XmlEnumValue("I")
	I("I"),
	II("II"),
	III("III"),
	AVR("aVR");

	String name;

	private PredefinedLead(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public String value() {
		return name();
	}

	public static PredefinedLead fromValue(String v) {
		return valueOf(v);
	}
}
