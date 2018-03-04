package application;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

import javax.xml.bind.JAXBException;

import org.gillius.jfxutils.chart.JFXChartUtil;

import enums.PredefinedLead;
import enums.RythmType;
import generator.EcgGenerationParameters;
import generator.EcgGenerator;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import parser.generated.jaxb.Ecg;
import parser.generated.jaxb.Ecg.Lead;
import parser.generated.jaxb.LeadType;
import parser.mapper.EcgMarshaller;
import resources.Constants;

public class ApplicationController {

	private static final String MSG_INVALID_PATH = "Error occured while loading file. Either the file is invalid or doesn't exist";

	@FXML
	private TextField filepath;

	@FXML
	private Button browse, generate;

	@FXML
	private ComboBox<RythmType> diseaseCombobox;

	@FXML
	private ComboBox<LeadType> leadCombobox;

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
		filepath.focusedProperty().addListener(new FilepathTextFieldFocusLostChangeListener());
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
	private void loadFile() {
		try {
			Ecg ecgLoaded = EcgMarshaller.unmarshall(filepath.getText());
			loadEcg(ecgLoaded);
		} catch (JAXBException e) {
			e.printStackTrace();
			new Alert(AlertType.ERROR, MSG_INVALID_PATH).show();
		}
	}

	private void loadEcg(Ecg ecgLoaded) {
		currentEcgSignal = ecgLoaded;
		populateLeadCombobox();
		defaultLead();
	}

	private void defaultLead() {
		LeadType firstValue = leadCombobox.getItems().iterator().next();
		leadCombobox.setValue(firstValue);
	}

	@FXML
	private void visualize() throws JAXBException {
		loadChart(currentEcgSignal);
		resetChartZoom();
	}

	@FXML
	private void generate() {
		EcgGenerationParameters config = retrieveGenerationParameters();
		Ecg ecgGenerated = EcgGenerator.generate(config);
		loadEcg(ecgGenerated);
		saveFile(ecgGenerated);
	}

	private void saveFile(Ecg ecgGenerated) {
		try {
			EcgMarshaller.marshall(ecgGenerated, Constants.DEFAULT_SAVE_PATH);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	private EcgGenerationParameters retrieveGenerationParameters() {
		RythmType rythmType = diseaseCombobox.getValue();
		int heartRate = 30;
		EcgGenerationParameters config = new EcgGenerationParameters(rythmType, heartRate, PredefinedLead.I);
		return config;
	}

	private void loadChart(Ecg signal) {
		LeadType selectedLead = leadCombobox.getValue();
		if (selectedLead != null) {
			String part = signal.getLead().stream().filter(l -> l.getName() == selectedLead)
					.map(Lead::getPart).findAny().get();
			XYChart.Series<Integer, Integer> series = new XYChart.Series<>();
			String[] points = part.split(Constants.CHARACTER_SPACE);
			for (int i = 0; i < points.length; i += Constants.CHART_SAMPLING_STEP) {
				series.getData().add(new XYChart.Data<>(Integer.valueOf(i), Integer.parseInt(points[i])));
			}
			chart.setData(FXCollections.observableArrayList(series));
		}
	}

	private void populateLeadCombobox() {
		List<LeadType> leadNames = currentEcgSignal.getLead().stream().map(Lead::getName)
				.collect(Collectors.toList());
		leadCombobox.setItems(FXCollections.observableArrayList(leadNames));
	}

	private void resetChartZoom() {
		chart.getXAxis().setAutoRanging(true);
		chart.getYAxis().setAutoRanging(true);
	}

	public void setStage(Stage stage) {
		this.primaryStage = stage;
	}

	private class FilepathTextFieldFocusLostChangeListener implements ChangeListener<Boolean> {

		@Override
		public void changed(ObservableValue<? extends Boolean> arg0, Boolean isOldValue, Boolean isNewValue) {
			if (!isNewValue) {
				loadFile();
				leadCombobox.requestFocus();
			}
		}

	}
}
