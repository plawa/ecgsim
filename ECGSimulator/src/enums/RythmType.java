package enums;

import java.util.List;

public enum RythmType {

	SINUS_RYTHM(
			"Sinus Rythm",
			List.of(Complex.S, Complex.S, Complex.S, Complex.S, Complex.S)),
	BIGEMINIA_SUPRAVENTRICULAR(
			"Bigeminia Supraventricularis",
			List.of(Complex.S, Complex.SV, Complex.S, Complex.SV, Complex.S)),
	TRIGEMINIA_SUPRAVENTRICULAR(
			"Trigeminia Supraventricularis",
			List.of(Complex.S, Complex.S, Complex.SV, Complex.S, Complex.S, Complex.SV, Complex.S, Complex.S)),
	BIGEMINIA_CHAMBER(
			"Bigeminia Chamber",
			List.of(Complex.S, Complex.V, Complex.S, Complex.V, Complex.S)),
	TRIGEMINIA_CHAMBER(
			"Trigeminia Chamber",
			List.of(Complex.S, Complex.S, Complex.V, Complex.S, Complex.S, Complex.V, Complex.S, Complex.S));

	String name;
	List<Complex> complexes;

	public String getName() {
		return name;
	}

	public List<Complex> getComplexesOrder() {
		return complexes;
	}

	private RythmType(String name, List<Complex> complexes) {
		this.name = name;
		this.complexes = complexes;
	}

	@Override
	public String toString() {
		return getName();
	}
}
