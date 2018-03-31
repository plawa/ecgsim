package generator;

import enums.RythmType;
import parser.generated.jaxb.LeadType;

public class EcgGenerationParameters {

	private RythmType rythmType;
	private int heartRate;
	private int noiseLevel;
	private LeadType lead;

	public EcgGenerationParameters(RythmType rythmType, int heartRate, LeadType lead) {
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

	public LeadType getLead() {
		return lead;
	}

	public void setLead(LeadType lead) {
		this.lead = lead;
	}

	public int getNoiseLevel() {
		return noiseLevel;
	}

	public void setNoiseLevel(int noiseLevel) {
		this.noiseLevel = noiseLevel;
	}

	public EcgGenerationParameters withRythmType(RythmType rythmType) {
		this.rythmType = rythmType;
		return this;
	}

	public EcgGenerationParameters withHeartRate(int heartRate) {
		this.heartRate = heartRate;
		return this;
	}

	public EcgGenerationParameters withLead(LeadType lead) {
		this.lead = lead;
		return this;
	}

	public EcgGenerationParameters withNoiseLevel(int noiseLevel) {
		this.noiseLevel = noiseLevel;
		return this;
	}

}
