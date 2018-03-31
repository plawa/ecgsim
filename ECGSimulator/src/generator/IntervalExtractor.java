package generator;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import com.google.inject.internal.util.Lists;

import javafx.util.Pair;
import resources.Constants;

public class IntervalExtractor {

	private static final int END_INTERVAL_NUMBER = 8;
	private static final int START_INTERVAL_NUMBER = 2;

	public static List<String> extract(String signalData) {
		List<String> stringPoints = Lists.newArrayList(signalData.split(Constants.CHARACTER_SPACE));
		List<Integer> points = stringPoints.stream().map(Integer::parseInt).collect(Collectors.toList());
		List<Integer> indexOfTop = findIndexesOfTops(points);
		List<String> cutIntervals = cutIntervals(stringPoints, indexOfTop);
		return cutIntervals;
	}

	private static List<String> cutIntervals(List<String> stringPoints, List<Integer> indexOfTop) {
		final int cutRange = Constants.ECG_SIGNAL_SAMPLES_PER_SECOND / 2;
		return indexOfTop.stream().map(i -> stringPoints.subList(i - cutRange, i + cutRange))
				.map(i -> String.join(Constants.CHARACTER_SPACE, i))
				.collect(Collectors.toList());
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
