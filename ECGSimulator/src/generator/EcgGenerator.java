package generator;

import parser.generated.jaxb.Ecg;
import parser.generated.jaxb.Ecg.Lead;
import parser.generated.jaxb.LeadType;
import resources.Constants;

public class EcgGenerator {

	public static Ecg generate(EcgGenerationParameters config) {
		int heartRate = config.getHeartRate();
		Ecg result = initializeResult(heartRate);
		Lead lead = createLeadPart(heartRate);
		result.addLead(lead);
		return result;
	}

	private static Ecg initializeResult(int heartRate) {
		Ecg result = new Ecg();
		result.setLength((short) Constants.ECG_SIGNAL_LENGTH);
		result.setRate((short) heartRate);
		return result;
	}

	private static Lead createLeadPart(int heartRate) {
		Lead lead = new Lead();
		lead.setName(LeadType.I);
		lead.setPart(generateSignal(heartRate, Constants.ECG_SIGNAL_LENGTH));
		return lead;
	}

	private static String generateSignal(int heartRate, int length) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < length - 1; i++) {
			sb.append(Integer.toString(1));
			sb.append(Constants.CHARACTER_SPACE);
		}
		sb.append("");
		return sb.toString();
	}
}
