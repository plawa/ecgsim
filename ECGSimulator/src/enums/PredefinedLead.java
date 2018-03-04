package enums;

public enum PredefinedLead {
	I("I"),
	II("II"),
	III("III"),
	AVR("aVR");

	String name;

	private PredefinedLead(String name) {
		this.name = name;
	}

	public String getStringValue() {
		return name;
	}
}
