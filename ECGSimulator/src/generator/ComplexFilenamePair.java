package generator;

import common.enums.Complex;

class ComplexFilenamePair {
	private Complex complex;
	private String filename;

	public Complex getComplex() {
		return complex;
	}

	public void setComplex(Complex complex) {
		this.complex = complex;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public ComplexFilenamePair(Complex complex, String filename) {
		this.complex = complex;
		this.filename = filename;
	}

}