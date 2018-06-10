package common.enums;

public enum DiseaseType {

	SINUS_RYTHM(
			"Sinus Rythm",
			new Complex[] { Complex.S, Complex.S, Complex.S, Complex.S, Complex.S }),
	BIGEMINIA_SUPRAVENTRICULAR(
			"Bigeminia Supraventricularis",
			new Complex[] { Complex.S, Complex.SV, Complex.S, Complex.SV, Complex.S }),
	TRIGEMINIA_SUPRAVENTRICULAR(
			"Trigeminia Supraventricularis",
			new Complex[] { Complex.S, Complex.S, Complex.SV, Complex.S, Complex.S, Complex.SV, Complex.S, Complex.S }),
	BIGEMINIA_CHAMBER(
			"Bigeminia Chamber",
			new Complex[] { Complex.S, Complex.V, Complex.S, Complex.V, Complex.S }),
	TRIGEMINIA_CHAMBER(
			"Trigeminia Chamber",
			new Complex[] { Complex.S, Complex.S, Complex.V, Complex.S, Complex.S, Complex.V, Complex.S, Complex.S });

	String name;
	Complex[] complexes;

	public String getName() {
		return name;
	}

	public Complex[] getComplexesOrder() {
		return complexes;
	}

	private DiseaseType(String name, Complex[] complexes) {
		this.name = name;
		this.complexes = complexes;
	}

	@Override
	public String toString() {
		return getName();
	}
}
