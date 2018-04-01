package generator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import com.google.common.base.Joiner;

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
			final List<List<Integer>> extractedIntervals = IntervalExtractor.extract(selectedSignalPattern);
			addNoiseIfRequested(extractedIntervals, config.getNoiseLevel());
			result.addLead(createLeadPart(extractedIntervals, leadType));
		}
		return result;
	}

	private static void addNoiseIfRequested(List<List<Integer>> extractedIntervals, int noiseLevel) {
		if (noiseLevel > 0) {
			Random generator = new Random();
			for (int i = 0; i < extractedIntervals.size(); i++) {
				List<Integer> unnoisedInterval = extractedIntervals.get(i);
				List<Integer> noisedList = unnoisedInterval.stream().map(point -> point + generator.nextInt(noiseLevel))
						.collect(Collectors.toList());
				extractedIntervals.set(i, noisedList);
			}
		}
	}

	private static String extractSignalPart(String filename, LeadType leadType) {
		InputStream signalPart = EcgGenerator.class.getResourceAsStream(
				String.format("%s%s%s", Constants.SAMPLES_PATH, leadType.resourceSubPath(), filename));
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
		List<String> signalPartsList = getResourceFiles(
				String.format("%s%s", Constants.SAMPLES_PATH, LeadType.I.resourceSubPath()));
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

	private static Lead createLeadPart(List<List<Integer>> extractedIntervals, LeadType leadType) {
		Lead lead = new Lead();
		lead.setLeadType(leadType);
		String part = generateSignal(extractedIntervals);
		lead.setSignal(part);
		return lead;
	}

	private static String generateSignal(List<List<Integer>> extractedIntervals) {
		Random generator = new Random();
		final int generatorUpperBound = extractedIntervals.size();
		List<Integer> signal = new ArrayList<>();
		while (signal.size() < Constants.ECG_SIGNAL_LENGTH) {
			List<Integer> intervalToAdd = extractedIntervals.get(generator.nextInt(generatorUpperBound));
			signal.addAll(intervalToAdd);
		}
		List<Integer> signalTrimmed = signal.subList(0, Constants.ECG_SIGNAL_LENGTH);
		return Joiner.on(Constants.CHARACTER_SPACE).join(signalTrimmed);
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
