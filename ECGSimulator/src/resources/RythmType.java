package resources;

public enum RythmType {

	SINUS_RYTHM(1, "Sinus Rythm"),
	TACHYCARDIA_SUPRAVENTRICULARIS(2, "Tachycardia Supraventricularis"),
	BRADYCARDIA(3, "Bradycardia"),
	ATRIAL_FIBRILATION(4, "Atrial Fibrilation");

	int id;
	String name;

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	private RythmType(int id, String name) {
		this.id = id;
		this.name = name;
	}

}
