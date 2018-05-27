package common.enums;

public enum Complex {

	S("S"),
	SV("SV"),
	V("V");

	String code;

	private Complex(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}
}
