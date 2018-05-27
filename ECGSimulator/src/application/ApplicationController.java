package application;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

import javax.xml.bind.JAXBException;

import common.enums.RythmType;
import generator.EcgGenerationParameters;
import generator.EcgGeneratorV2;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import parser.generated.jaxb.Ecg;
import parser.generated.jaxb.Ecg.Lead;
import parser.generated.jaxb.LeadType;
import parser.mapper.EcgMarshaller;
import resources.Constants;

public class ApplicationController {

	@FXML
	private TextField filepathTextField;

	@FXML
	private Button browseButton, generateButton;

	@FXML
	private ComboBox<RythmType> diseaseCombobox;

	@FXML
	private ComboBox<LeadType> leadCombobox;

	@FXML
	private Slider noiseLevelSlider;

	@FXML
	private LineChart<Integer, Integer> chart;

	@FXML
	private NumberAxis xAxis, yAxis;

	private ObjectProperty<RythmType> diseaseProperty = new SimpleObjectProperty<>();

	private final FileChooser fileChooser = new FileChooser();

	private Ecg currentEcgSignal;

	private Stage primaryStage;

	@FXML
	private void initialize() {
		diseaseCombobox.getItems().setAll(RythmType.values());
		diseaseCombobox.valueProperty().bindBidirectional(diseaseProperty);
		generateButton.disableProperty().bind(diseaseProperty.isNull());
		filepathTextField.focusedProperty().addListener(new FilepathTextFieldFocusLostChangeListener());
		filepathTextField.setText("A:\\Studia\\Praca magisterska\\Disc\\CSE_diagnostics\\D_00011.ekg");
		ChartSupport.setupZooming(chart);
	}

	@FXML
	private void browsePressed() throws JAXBException {
		File fileChoosen = fileChooser.showOpenDialog(primaryStage);
		if (fileChoosen != null) {
			filepathTextField.setText(fileChoosen.getAbsolutePath());
			loadFile();
		}
	}

	@FXML
	private void loadFile() {
		try {
			performLoadFile();
		} catch (JAXBException e) {
			handleFileLoadingException(e);
		}
	}

	private void performLoadFile() throws JAXBException {
		Ecg ecgLoaded = EcgMarshaller.unmarshall(filepathTextField.getText());
		loadEcg(ecgLoaded);
		filepathTextField.setBorder(Border.EMPTY);
	}

	private void handleFileLoadingException(JAXBException e) {
		e.printStackTrace();
		filepathTextField.setBorder(new Border(new BorderStroke(Color.RED,
				BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(2))));
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
		ChartSupport.resetChartZoom(chart);
	}

	@FXML
	private void generatePressed() {
		EcgGenerationParameters config = retrieveGenerationParameters();
		Ecg ecgGenerated = EcgGeneratorV2.generate(config);
		saveAndLoad(ecgGenerated);
	}

	private void saveAndLoad(Ecg ecgGenerated) {
		loadEcg(ecgGenerated);
		saveFile(ecgGenerated);
		filepathTextField.setText(Constants.DEFAULT_SAVE_PATH);
	}

	private void saveFile(Ecg ecgGenerated) {
		try {
			EcgMarshaller.marshall(ecgGenerated, Constants.DEFAULT_SAVE_PATH);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	private EcgGenerationParameters retrieveGenerationParameters() {
		RythmType rythmType = diseaseProperty.get();
		int heartRate = 60;
		EcgGenerationParameters config = new EcgGenerationParameters(rythmType, heartRate, LeadType.I);
		config.setNoiseLevel((int) noiseLevelSlider.getValue());
		return config;
	}

	private void loadChart(Ecg signal) {
		LeadType selectedLead = leadCombobox.getValue();
		if (selectedLead != null) {
			String part = signal.getLead().stream().filter(l -> l.getLeadType() == selectedLead)
					.map(Lead::getSignal).findAny().get();
			XYChart.Series<Integer, Integer> series = new XYChart.Series<>();
			String[] points = part.split(Constants.CHARACTER_SPACE);
			for (int i = 0; i < points.length; i += Constants.CHART_SAMPLING_STEP) {
				series.getData().add(new XYChart.Data<>(Integer.valueOf(i), Integer.parseInt(points[i])));
			}
			chart.setData(FXCollections.observableArrayList(series));
		}
	}

	private void populateLeadCombobox() {
		List<LeadType> leadNames = currentEcgSignal.getLead().stream().map(Lead::getLeadType)
				.collect(Collectors.toList());
		leadCombobox.setItems(FXCollections.observableArrayList(leadNames));
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
