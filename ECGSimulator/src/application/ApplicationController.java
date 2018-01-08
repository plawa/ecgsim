package application;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

import javax.xml.bind.JAXBException;

import org.gillius.jfxutils.chart.JFXChartUtil;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import parser.generated.jaxb.Ecg;
import parser.generated.jaxb.Ecg.Lead;
import parser.mapper.EcgMarshaller;
import resources.RythmType;

public class ApplicationController {

	private static final String SPACE = " ";

	private static final int SAMPLING_STEP = 5;

	@FXML
	private TextField filepath;

	@FXML
	private Button browse, generate;

	@FXML
	private ComboBox<RythmType> diseaseCombobox;

	@FXML
	private ComboBox<String> leadCombobox;

	@FXML
	private LineChart<Integer, Integer> chart;

	@FXML
	private NumberAxis xAxis, yAxis;

	private final FileChooser fileChooser = new FileChooser();

	private Ecg currentEcgSignal;

	private Stage primaryStage;

	@FXML
	private void initialize() {
		diseaseCombobox.getItems().setAll(RythmType.values());
		JFXChartUtil.setupZooming(chart);
		JFXChartUtil.addDoublePrimaryClickAutoRangeHandler(chart);
	}

	@FXML
	private void browsePressed() throws JAXBException {
		File fileChoosen = fileChooser.showOpenDialog(primaryStage);
		if (fileChoosen != null) {
			filepath.setText(fileChoosen.getAbsolutePath());
			loadFile();
		}
	}

	@FXML
	private void loadFile() throws JAXBException {
		currentEcgSignal = EcgMarshaller.unmarshall(filepath.getText());
		populateLeadCombobox();
	}

	@FXML
	private void visualize() throws JAXBException {
		loadChart(currentEcgSignal);
		resetChartZoom();
	}

	@FXML
	private void generate() {
		// TODO Auto-generated method stub

	}

	private void loadChart(Ecg signal) {
		String part = signal.getLead().stream().filter(l -> l.getName().equals(leadCombobox.getValue()))
				.map(Lead::getPart).findAny().get();
		XYChart.Series<Integer, Integer> series = new XYChart.Series<>();
		String[] points = part.split(SPACE);
		for (int i = 0; i < points.length; i += SAMPLING_STEP) {
			series.getData().add(new XYChart.Data<Integer, Integer>(Integer.valueOf(i), Integer.parseInt(points[i])));
		}
		chart.getData().clear();
		chart.getData().addAll(series);
	}

	private void populateLeadCombobox() {
		List<String> leadNames = currentEcgSignal.getLead().stream().map(Lead::getName).collect(Collectors.toList());
		leadCombobox.getItems().clear();
		leadCombobox.getItems().setAll(leadNames);
	}

	private void resetChartZoom() {
		chart.getXAxis().setAutoRanging(true);
		chart.getYAxis().setAutoRanging(true);
	}

	public void setStage(Stage stage) {
		this.primaryStage = stage;
	}

}
