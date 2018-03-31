package application;

import org.gillius.jfxutils.chart.ChartPanManager;
import org.gillius.jfxutils.chart.JFXChartUtil;

import javafx.event.EventHandler;
import javafx.scene.chart.LineChart;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class ChartSupport {

	final private static EventHandler<? super MouseEvent> LEFT_BUTTON_ONLY = me -> {
		if (me.getButton() != MouseButton.PRIMARY)
			me.consume();
	};
	final private static EventHandler<? super MouseEvent> RIGHT_BUTTON_ONLY = me -> {
		if (me.getButton() != MouseButton.SECONDARY)
			me.consume();
	};

	public static void setupZooming(LineChart<?, ?> chart) {

		JFXChartUtil.setupZooming(chart, RIGHT_BUTTON_ONLY);
		JFXChartUtil.addDoublePrimaryClickAutoRangeHandler(chart);
		setupMovingByLeftMouseButton(chart);
	}

	private static void setupMovingByLeftMouseButton(LineChart<?, ?> chart) {
		ChartPanManager panner = new ChartPanManager(chart);
		panner.setMouseFilter(LEFT_BUTTON_ONLY);
		panner.start();
	}

}
