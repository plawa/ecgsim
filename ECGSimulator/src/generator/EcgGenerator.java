package generator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import parser.generated.jaxb.Ecg;
import parser.generated.jaxb.Ecg.Lead;
import parser.generated.jaxb.LeadType;
import resources.Constants;

public class EcgGenerator {

	public static Ecg generate(EcgGenerationParameters config) {
		int heartRate = config.getHeartRate();
		Ecg result = initializeResult(heartRate);
		String selectedSignalPattern = selectRandomSignalPart();
		List<String> extractedIntervals = IntervalExtractor.extract(selectedSignalPattern);
		Lead lead = createLeadPart(heartRate, extractedIntervals);
		result.addLead(lead);
		return result;
	}

	private static String selectRandomSignalPart() {
		Random generator = new Random();
		List<String> signalPartsList = getResourceFiles(Constants.SAMPLES_PATH);
		String randomSignalPartFilename = signalPartsList.get(generator.nextInt(signalPartsList.size()));
		System.out.println("Selected signal part: " + randomSignalPartFilename);
		InputStream signalPart = EcgGenerator.class
				.getResourceAsStream(Constants.SAMPLES_PATH + randomSignalPartFilename);
		String signalPartContent = null;
		try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(signalPart))) {
			signalPartContent = bufferedReader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return signalPartContent;
	}

	private static Ecg initializeResult(int heartRate) {
		Ecg result = new Ecg();
		result.setLength((short) Constants.ECG_SIGNAL_LENGTH);
		result.setRate((short) heartRate);
		return result;
	}

	private static Lead createLeadPart(int heartRate, List<String> extractedIntervals) {
		Lead lead = new Lead();
		lead.setName(LeadType.I);
		String part = generateSignal(heartRate, extractedIntervals, Constants.ECG_SIGNAL_LENGTH);
		lead.setPart(part);
		return lead;
	}

	private static String generateSignal(int heartRate, List<String> extractedIntervals, int length) {
		StringBuilder sb = new StringBuilder();
		Random generator = new Random();
		for (int i = 0; i < 8; i++) {
			String got = sb.toString();
			String newPart = extractedIntervals.get(generator.nextInt(extractedIntervals.size()));
			sb.append(newPart + Constants.CHARACTER_SPACE);
		}
		return sb.toString();
	}

	private static List<String> getResourceFiles(String path) {
		List<String> filenames = new ArrayList<>();

		try (
				InputStream in = getResourceAsStream(path);
				BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
			String resource;

			while ((resource = br.readLine()) != null) {
				filenames.add(resource);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return filenames;
	}

	private static InputStream getResourceAsStream(String resource) {
		final InputStream in = getContextClassLoader().getResourceAsStream(resource);

		return in == null ? EcgGenerator.class.getResourceAsStream(resource) : in;
	}

	private static ClassLoader getContextClassLoader() {
		return Thread.currentThread().getContextClassLoader();
	}
}
