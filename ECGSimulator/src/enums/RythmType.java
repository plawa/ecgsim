package enums;

import resources.Constants;

public enum RythmType {

	SINUS_RYTHM(1, "Sinus Rythm", Constants.SAMPLES_PATH + "\\_1_sinus_rythm"),
	TACHYCARDIA_SUPRAVENTRICULARIS(2, "Tachycardia Supraventricularis", Constants.SAMPLES_PATH + "\\_2_tachycardia"),
	BRADYCARDIA(3, "Bradycardia", Constants.SAMPLES_PATH + "\\_3_bradycardia"),
	ATRIAL_FIBRILATION(4, "Atrial Fibrilation", Constants.SAMPLES_PATH + "\\_4_atrial_fib");

	int id;
	String name;
	String resourcePath;

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getResourcePath() {
		return resourcePath;
	}

	private RythmType(int id, String name, String resourcePath) {
		this.id = id;
		this.name = name;
	}

}
