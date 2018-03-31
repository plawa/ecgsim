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
		Ecg result = initializeResult(config);
		String randomSignalPartFilename = getRandomFilename();
		for (LeadType leadType : LeadType.values()) {
			String selectedSignalPattern = extractSignalPart(randomSignalPartFilename, leadType);
			List<String> extractedIntervals = IntervalExtractor.extract(selectedSignalPattern);
			Lead leadElement = createLeadPart(extractedIntervals, leadType);
			result.addLead(leadElement);
		}
		return result;
	}

	private static String extractSignalPart(String filename, LeadType leadType) {
		InputStream signalPart = EcgGenerator.class
				.getResourceAsStream(Constants.SAMPLES_PATH + leadType.resourceSubPath() + "/" + filename);
		String signalPartContent = null;
		try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(signalPart))) {
			signalPartContent = bufferedReader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return signalPartContent;
	}

	private static String getRandomFilename() {
		Random generator = new Random();
		List<String> signalPartsList = getResourceFiles(Constants.SAMPLES_PATH + "/" + LeadType.I.resourceSubPath());
		String randomSignalPartFilename = signalPartsList.get(generator.nextInt(signalPartsList.size()));
		System.out.println("Selected signal part: " + randomSignalPartFilename);
		return randomSignalPartFilename;
	}

	private static Ecg initializeResult(EcgGenerationParameters config) {
		Ecg result = new Ecg();
		result.setLength((short) Constants.ECG_SIGNAL_LENGTH);
		result.setRate((short) config.getHeartRate());
		return result;
	}

	private static Lead createLeadPart(List<String> extractedIntervals, LeadType leadType) {
		Lead lead = new Lead();
		lead.setName(leadType);
		String part = generateSignal(extractedIntervals);
		lead.setPart(part);
		return lead;
	}

	private static String generateSignal(List<String> extractedIntervals) {
		StringBuilder sb = new StringBuilder();
		Random generator = new Random();
		for (int i = 0; i < 8; i++) {
			String newPart = extractedIntervals.get(generator.nextInt(extractedIntervals.size()));
			sb.append(newPart + Constants.CHARACTER_SPACE);
		}
		return sb.toString();
	}

	private static List<String> getResourceFiles(String path) {
		List<String> filenames = new ArrayList<>();

		try (InputStream in = getResourceAsStream(path);
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
