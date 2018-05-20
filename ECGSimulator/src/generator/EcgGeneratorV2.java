package generator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.google.common.base.Joiner;

import enums.Complex;
import enums.RythmType;
import parser.generated.jaxb.Ecg;
import parser.generated.jaxb.Ecg.Lead;
import parser.generated.jaxb.LeadType;
import resources.Constants;

public class EcgGeneratorV2 {

	public static Ecg generate(EcgGenerationParameters config) {
		var result = initializeResult(config);
		RythmType rythmType = config.getRythmType();
		final List<ComplexFilenamePair> signalDetails = assignGenerationSources(rythmType);
		for (LeadType leadType : LeadType.values()) {
			List<List<Integer>> complexesOrdered = loadComplexesSignalsAsPoints(signalDetails, leadType);
			addNoise(complexesOrdered, config.getNoiseLevel());
			result.addLead(createLeadPart(complexesOrdered, leadType));
		}
		return result;
	}

	private static List<ComplexFilenamePair> assignGenerationSources(RythmType rythmType) {
		final List<ComplexFilenamePair> sourcesFilenames = new ArrayList<>();
		final Iterator<Complex> complexexOrderIterator = rythmType.getComplexesOrder().iterator();
		for (int i = 0; i < Constants.ECG_SIGNAL_COMPLEX_NUMBER; i++) {
			Complex complexNow = complexexOrderIterator.hasNext() ? complexexOrderIterator.next() : Complex.S;
			String pathToPickFrom = complexNow.getResourceSubPath();
			sourcesFilenames.add(new ComplexFilenamePair(complexNow, getRandomFilename(pathToPickFrom)));
		}
		return sourcesFilenames;
	}

	private static void addNoise(List<List<Integer>> extractedIntervals, int noiseLevel) {
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

	private static List<List<Integer>> loadComplexesSignalsAsPoints(List<ComplexFilenamePair> signalDetails,
			LeadType leadType) {
		final List<List<Integer>> complexesSignals = new ArrayList<>();
		for (ComplexFilenamePair complexData : signalDetails) {
			String pathToRead = String.format("%s%s/%s", complexData.getComplex().getResourceSubPath(),
					leadType.ordinal(), complexData.getFilename());
			InputStream signalPart = EcgGeneratorV2.class.getResourceAsStream(pathToRead);
			try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(signalPart))) {
				String complexSignalContent = bufferedReader.readLine();
				final List<Integer> mapToIntPoints = mapToIntPoints(complexSignalContent);
				complexesSignals.add(mapToIntPoints);
			} catch (IOException e) {
				e.printStackTrace();
				throw new IllegalStateException("Cannot read the file: " + pathToRead);
			}
		}
		return complexesSignals;
	}

	private static List<Integer> mapToIntPoints(String complexSignalContent) {
		final String[] pointsSplit = complexSignalContent.split(Constants.CHARACTER_SPACE);
		final List<Integer> pointsInt = Stream.of(pointsSplit).map(Integer::parseInt).collect(Collectors.toList());
		return pointsInt;
	}

	private static String getRandomFilename(String resourcePath) {
		Random generator = new Random();
		List<String> signalPartsList = getResourceFiles(resourcePath);
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

	private static Lead createLeadPart(List<List<Integer>> complexesSignals, LeadType leadType) {
		Lead lead = new Lead();
		lead.setLeadType(leadType);
		String part = generateSignal(complexesSignals);
		lead.setSignal(part);
		return lead;
	}

	private static String generateSignal(List<List<Integer>> complexSignals) {
		LinkedList<Integer> signal = new LinkedList<>();
		final Iterator<List<Integer>> complexSignalsIterator = complexSignals.iterator();
		while (signal.size() < Constants.ECG_SIGNAL_LENGTH) {
			addNormalizedSignalValue(signal, complexSignalsIterator.next());
		}
		List<Integer> signalTrimmed = signal.subList(0, Constants.ECG_SIGNAL_LENGTH);
		return Joiner.on(Constants.CHARACTER_SPACE).join(signalTrimmed);
	}

	private static void addNormalizedSignalValue(LinkedList<Integer> signal, List<Integer> nextInterval) {
		List<Integer> intervalToAdd = nextInterval;
		if (!signal.isEmpty()) {
			Integer lastPoint = signal.getLast();
			Integer firstPointOfNewSignal = nextInterval.iterator().next();
			Integer diff = lastPoint - firstPointOfNewSignal;
			intervalToAdd = nextInterval.stream().mapToInt(Integer::intValue).map(p -> p + diff).boxed()
					.collect(Collectors.toList());
		}
		signal.addAll(intervalToAdd);
	}

	private static List<String> getResourceFiles(String path) {
		List<String> filenames = new ArrayList<>();

		try (InputStream in = getResourceAsStream(path + LeadType.I.ordinal());
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
		return in == null ? EcgGeneratorV2.class.getResourceAsStream(resource) : in;
	}

	private static ClassLoader getContextClassLoader() {
		return Thread.currentThread().getContextClassLoader();
	}
}
