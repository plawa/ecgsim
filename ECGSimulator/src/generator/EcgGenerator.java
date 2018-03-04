package generator;

import java.util.List;

import parser.generated.jaxb.Ecg;
import parser.generated.jaxb.Ecg.Lead;
import parser.generated.jaxb.LeadType;
import resources.Constants;

public class EcgGenerator {

	public static Ecg generate(EcgGenerationParameters config) {
		Ecg result = new Ecg();
		int heartRate = config.getHeartRate();
		int length = calculateLength(heartRate);
		result.setLength((short) length);
		result.setRate((short) heartRate);
		List<Lead> leads = result.getLead();
		Lead leadAVR = new Lead();
		leadAVR.setName(LeadType.I);
		leadAVR.setPart(generateSignal(heartRate, length));
		leads.add(leadAVR);
		return result;
	}

	private static String generateSignal(int heartRate, int length) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < length - 1; i++) {
			sb.append(Integer.toString(1));
			sb.append(Constants.CHARACTER_SPACE);
		}
		// sb.append("last value!");
		return sb.toString();
	}

	private static int calculateLength(int heartRate) {
		return Constants.SIGNAL_TIME_IN_SECONDS * heartRate;
	}

}
