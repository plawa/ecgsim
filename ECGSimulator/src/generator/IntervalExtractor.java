package generator;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javafx.util.Pair;
import resources.Constants;

public class IntervalExtractor {

	private static final int END_INTERVAL_NUMBER = 8;
	private static final int START_INTERVAL_NUMBER = 2;

	public static List<List<Integer>> extract(String signalData) {
		final String[] pointsSplit = signalData.split(Constants.CHARACTER_SPACE);
		final List<Integer> pointsInt = Stream.of(pointsSplit).map(Integer::parseInt).collect(Collectors.toList());
		final List<Integer> indexesOfTop = findIndexesOfTops(pointsInt);
		return cutIntervals(pointsInt, indexesOfTop);
	}

	public static List<List<Integer>> extract(String signalData, List<Integer> cutPoints) {
		final String[] pointsSplit = signalData.split(Constants.CHARACTER_SPACE);
		final List<Integer> pointsInt = Stream.of(pointsSplit).map(Integer::parseInt).collect(Collectors.toList());
		return cutIntervals(pointsInt, cutPoints);
	}

	public static List<Integer> retrieveCutPoints(String signalData) {
		final String[] pointsSplit = signalData.split(Constants.CHARACTER_SPACE);
		final List<Integer> pointsInt = Stream.of(pointsSplit).map(Integer::parseInt).collect(Collectors.toList());
		return findIndexesOfTops(pointsInt);
	}

	private static List<List<Integer>> cutIntervals(List<Integer> points, List<Integer> indexesOfTop) {
		final int cutRange = (indexesOfTop.get(1) - indexesOfTop.get(0)) / 2;
		return indexesOfTop.stream().map(i -> points.subList(i - cutRange, i + cutRange)).collect(Collectors.toList());
	}

	private static List<Integer> findIndexesOfTops(List<Integer> points) {
		List<Integer> topIndexes = new LinkedList<>();
		for (int i = START_INTERVAL_NUMBER; i < END_INTERVAL_NUMBER; i++) {
			Integer maxIndexValue = locateTop(points, i);
			topIndexes.add(maxIndexValue);
		}
		return topIndexes;
	}

	private static Integer locateTop(List<Integer> points, Integer intervalNumber) {
		Integer startIndex = (intervalNumber - 1) * Constants.ECG_SIGNAL_SAMPLES_PER_SECOND;
		Integer endIndex = intervalNumber * Constants.ECG_SIGNAL_SAMPLES_PER_SECOND;
		Pair<Integer, Integer> maxIndexValue = new Pair<>(-1, Integer.MIN_VALUE);
		for (int i = startIndex; i < endIndex; i++) {
			Integer currentValue = points.get(i);
			if (currentValue > maxIndexValue.getValue()) {
				maxIndexValue = new Pair<>(i, currentValue);
			}
		}
		return maxIndexValue.getKey();
	}
}
