package application;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javax.xml.bind.JAXBException;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import parser.generated.jaxb.Ecg;
import parser.mapper.EcgMarshaller;

public class ApplicationController implements Initializable {

	@FXML
	private TextField filepath;

	@FXML
	private Button browse;

	@FXML
	private Button visualize;

	@FXML
	private LineChart<?, ?> chart;

	@FXML
	private CategoryAxis xAxis;

	@FXML
	private NumberAxis yAxis;

	private final FileChooser fileChooser = new FileChooser();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	}

	@FXML
	private void browsePressed() {
		File fileChoosen = fileChooser.showOpenDialog(null);
		if (fileChoosen != null) {
			filepath.setText(fileChoosen.getAbsolutePath());
		}
	}

	@FXML
	private void visualizePressed() throws JAXBException {
		Ecg ecgSignal = EcgMarshaller.unmarshall(filepath.getText());
		loadChart(ecgSignal);
	}

	private void loadChart(Ecg signal) {
		String part = signal.getLead().get(0).getPart();
		XYChart.Series series = new XYChart.Series<>();
		String[] split = part.split(" ");
		for (int i = 0; i < split.length; i += 20) {
			series.getData().add(new XYChart.Data(String.valueOf(i), Integer.parseInt(split[i])));
		}
		chart.getData().clear();
		chart.getData().addAll(series);
	}

}
