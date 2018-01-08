package generator;

import resources.RythmType;

public class EcgGenerationConfig {

	RythmType rythmType;
	int heartRate;

	public EcgGenerationConfig(RythmType rythmType, int heartRate) {
		this.rythmType = rythmType;
		this.heartRate = heartRate;
	}

	public EcgGenerationConfig() {
	}

	public RythmType getRythmType() {
		return rythmType;
	}

	public void setRythmType(RythmType rythmType) {
		this.rythmType = rythmType;
	}

	public int getHeartRate() {
		return heartRate;
	}

	public void setHeartRate(int heartRate) {
		this.heartRate = heartRate;
	}

}
