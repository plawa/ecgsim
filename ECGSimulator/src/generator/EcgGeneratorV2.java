package generator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.google.common.base.Joiner;

import common.enums.Complex;
import common.enums.DiseaseType;
import common.utils.PathJoiner;
import parser.generated.jaxb.Ecg;
import parser.generated.jaxb.Ecg.Lead;
import parser.generated.jaxb.LeadType;
import resources.Constants;

public class EcgGeneratorV2 {

	private static Random generator = new Random();

	public static Ecg generate(EcgGenerationParameters config) {
		Ecg result = initializeResult(config);
		DiseaseType rythmType = config.getRythmType();
		final List<ComplexFilenamePair> signalDetails = assignGenerationSources(rythmType);
		for (LeadType leadType : LeadType.values()) {
			List<List<Integer>> complexesOrdered = loadComplexesSignalsAsPoints(signalDetails, leadType);
			addNoise(complexesOrdered, config.getNoiseLevel());
			result.addLead(generateLeadPartWithSignal(complexesOrdered, leadType));
		}
		return result;
	}

	private static List<ComplexFilenamePair> assignGenerationSources(DiseaseType rythmType) {
		final List<ComplexFilenamePair> sourcesFilenames = new ArrayList<>();
		final List<Complex> complexesToGenerateSignalFor = randomizeSpecificComplexesOrderPosition(rythmType);
		for (Complex complexNow : complexesToGenerateSignalFor) {
			final String pathToPickFrom = PathJoiner.join(Constants.COMPLEXES_PATH, complexNow.getCode());
			sourcesFilenames.add(new ComplexFilenamePair(complexNow, getRandomFilename(pathToPickFrom)));
		}
		return sourcesFilenames;
	}

	private static List<Complex> randomizeSpecificComplexesOrderPosition(DiseaseType rythmType) {
		final List<Complex> result = new ArrayList<>(
				Collections.nCopies(Constants.ECG_SIGNAL_NUMBER_OF_COMPLEXES, Complex.S));
		final Iterator<Complex> complexesToInsert = Stream.of(rythmType.getComplexesOrder()).iterator();
		final int specificComplexesOrderStartPosition = generator
				.nextInt(Constants.ECG_SIGNAL_MAX_COMPLEX_POSITION_FOR_SPECIFIC_DISEASE_COMPLEXES);
		for (int i = specificComplexesOrderStartPosition; complexesToInsert.hasNext(); i++) {
			result.set(i, complexesToInsert.next());
		}
		return result;
	}

	private static void addNoise(List<List<Integer>> extractedIntervals, int noiseLevel) {
		if (noiseLevel > 0) {
			for (int i = 0; i < extractedIntervals.size(); i++) {
				List<Integer> unnoisedInterval = extractedIntervals.get(i);
				List<Integer> noisedInterval = unnoisedInterval.stream()
						.map(point -> point + generator.nextInt(noiseLevel)).collect(Collectors.toList());
				extractedIntervals.set(i, noisedInterval);
			}
		}
	}

	private static List<List<Integer>> loadComplexesSignalsAsPoints(List<ComplexFilenamePair> signalDetails,
			LeadType leadType) {
		final List<List<Integer>> complexesIntPoints = new ArrayList<>();
		for (ComplexFilenamePair complexData : signalDetails) {
			String pathToRead = PathJoiner.join(Constants.COMPLEXES_PATH, complexData.getComplex().getCode(),
					leadType.ordinal(), complexData.getFilename());
			InputStream signalPart = EcgGeneratorV2.class.getResourceAsStream(PathJoiner.slashBegin(pathToRead));
			try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(signalPart))) {
				final List<Integer> complexIntPoints = mapToIntPoints(bufferedReader.readLine());
				complexesIntPoints.add(complexIntPoints);
			} catch (Exception e) {
				e.printStackTrace();
				throw new IllegalArgumentException("Cannot read the Complex file: " + pathToRead);
			}
		}
		return complexesIntPoints;
	}

	private static List<Integer> mapToIntPoints(String complexSignalContent) {
		final String[] pointsSplit = complexSignalContent.split(Constants.CHARACTER_SPACE);
		final List<Integer> pointsInt = Stream.of(pointsSplit).map(Integer::parseInt).collect(Collectors.toList());
		return pointsInt;
	}

	private static String getRandomFilename(String resourcePath) {
		List<String> signalPartsList = getResourceFiles(resourcePath);
		return signalPartsList.get(generator.nextInt(signalPartsList.size()));
	}

	private static Ecg initializeResult(EcgGenerationParameters config) {
		Ecg result = new Ecg();
		result.setLength((short) Constants.ECG_SIGNAL_LENGTH);
		result.setRate((short) config.getHeartRate());
		return result;
	}

	private static Lead generateLeadPartWithSignal(List<List<Integer>> complexesSignals, LeadType leadType) {
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

		final String pathToReadFrom = PathJoiner.join(path, LeadType.I.ordinal());
		try (InputStream in = getResourceAsStream(pathToReadFrom);
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
