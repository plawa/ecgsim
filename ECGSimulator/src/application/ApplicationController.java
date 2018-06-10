package application;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

import javax.xml.bind.JAXBException;

import common.enums.DiseaseType;
import common.utils.PathJoiner;
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
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.DirectoryChooser;
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
	private Button loadBowseButton, saveBrowseButton, generateButton;

	@FXML
	private Label saveDirectoryLabel;

	@FXML
	private ComboBox<DiseaseType> diseaseCombobox;

	@FXML
	private ComboBox<LeadType> leadCombobox;

	@FXML
	private Slider noiseLevelSlider;

	@FXML
	private LineChart<Integer, Integer> chart;

	@FXML
	private NumberAxis xAxis, yAxis;

	private ObjectProperty<DiseaseType> diseaseProperty = new SimpleObjectProperty<>();

	private ObjectProperty<Ecg> currentEcgSignal = new SimpleObjectProperty<>();

	private final FileChooser fileChooser = new FileChooser();

	private final DirectoryChooser directoryChooser = new DirectoryChooser();

	private Stage primaryStage;

	@FXML
	private void initialize() {
		diseaseCombobox.getItems().setAll(DiseaseType.values());
		directoryChooser.setInitialDirectory(Constants.DEFAULT_SAVE_DIR);
		bindProperties();
		filepathTextField.setText("A:\\Studia\\Praca magisterska\\Disc\\CSE_diagnostics\\D_00011.ekg");
		ChartSupport.setupZooming(chart);
	}

	private void bindProperties() {
		diseaseCombobox.valueProperty().bindBidirectional(diseaseProperty);
		generateButton.disableProperty().bind(diseaseProperty.isNull());
		leadCombobox.disableProperty().bind(currentEcgSignal.isNull());
		saveDirectoryLabel.textProperty().bind(directoryChooser.initialDirectoryProperty().asString());
		filepathTextField.focusedProperty().addListener(new FilepathTextFieldFocusLostChangeListener());
	}

	@FXML
	private void loadBrowsePressed() throws JAXBException {
		File fileChoosen = fileChooser.showOpenDialog(primaryStage);
		if (fileChoosen != null) {
			filepathTextField.setText(fileChoosen.getAbsolutePath());
			loadFile();
		}
	}

	@FXML
	private void saveBrowsePressed() throws JAXBException {
		File dirChoosen = directoryChooser.showDialog(primaryStage);
		if (dirChoosen != null) {
			directoryChooser.setInitialDirectory(dirChoosen);
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
		currentEcgSignal.set(ecgLoaded);
		populateLeadCombobox();
		defaultLead();
	}

	private void defaultLead() {
		LeadType firstValue = leadCombobox.getItems().iterator().next();
		leadCombobox.setValue(firstValue);
	}

	@FXML
	private void visualize() throws JAXBException {
		loadChart(currentEcgSignal.get());
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
		String savePath = PathJoiner.join(directoryChooser.getInitialDirectory().getAbsolutePath(),
				Constants.DEFAULT_SAVE_FILENAME);
		saveFile(ecgGenerated, savePath);
		filepathTextField.setText(savePath);
	}

	private void saveFile(Ecg ecgGenerated, String savePath) {
		try {
			EcgMarshaller.marshall(ecgGenerated, savePath);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	private EcgGenerationParameters retrieveGenerationParameters() {
		DiseaseType rythmType = diseaseProperty.get();
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
		List<LeadType> leadNames = currentEcgSignal.get().getLead().stream().map(Lead::getLeadType)
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
