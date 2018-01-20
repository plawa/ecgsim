package generator;

import resources.RythmType;

public class EcgGenerationConfiguration {

	RythmType rythmType;

	/**
	 * Heart rate interval in miliseconds.
	 */
	int heartRate;

	public EcgGenerationConfiguration(RythmType rythmType, int heartRate) {
		this.rythmType = rythmType;
		this.heartRate = heartRate;
	}

	public EcgGenerationConfiguration() {
	}

	public RythmType getRythmType() {
		return rythmType;
	}

	public void setRythmType(RythmType rythmType) {
		this.rythmType = rythmType;
	}

	/**
	 * Heart rate interval in miliseconds.
	 */
	public int getHeartRate() {
		return heartRate;
	}

	/**
	 * Sets heart rate interval in miliseconds.
	 */
	public void setHeartRate(int heartRate) {
		this.heartRate = heartRate;
	}

}
