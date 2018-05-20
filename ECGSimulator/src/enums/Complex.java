package enums;

import resources.Constants;

public enum Complex {

	S("S", Constants.COMPLEXES_PATH + "S/"),
	SV("SV", Constants.COMPLEXES_PATH + "SV/"),
	V("V", Constants.COMPLEXES_PATH + "V/");

	String code;
	String resourcePath;

	private Complex(String code, String resourcePath) {
		this.code = code;
		this.resourcePath = resourcePath;
	}

	public String getCode() {
		return code;
	}

	public String getResourcePath() {
		return resourcePath;
	}
}
