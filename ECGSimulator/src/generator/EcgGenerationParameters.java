package generator;

import enums.PredefinedLead;
import enums.RythmType;

public class EcgGenerationParameters {

	private RythmType rythmType;
	private int heartRate;
	private PredefinedLead lead;

	public EcgGenerationParameters(RythmType rythmType, int heartRate, PredefinedLead lead) {
		this.rythmType = rythmType;
		this.heartRate = heartRate;
	}

	public EcgGenerationParameters() {
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

	public PredefinedLead getLead() {
		return lead;
	}

	public void setLead(PredefinedLead lead) {
		this.lead = lead;
	}

	public EcgGenerationParameters withRythmType(RythmType rythmType) {
		this.rythmType = rythmType;
		return this;
	}

	public EcgGenerationParameters withHeartRate(int heartRate) {
		this.heartRate = heartRate;
		return this;
	}

	public EcgGenerationParameters withLead(PredefinedLead lead) {
		this.lead = lead;
		return this;
	}

}
