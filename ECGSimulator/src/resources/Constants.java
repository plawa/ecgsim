package resources;
/**
 * 
 */

import java.io.File;

import common.utils.PathJoiner;

/**
 * @author Piotrek
 *
 */
public class Constants {

	public static final int ECG_SIGNAL_START_POINT_Y = 0;
	public static final int ECG_SIGNAL_NUMBER_OF_COMPLEXES = 14;
	public static final int ECG_SIGNAL_TIME_IN_SECONDS = 8;
	public static final int ECG_SIGNAL_SAMPLES_PER_SECOND = 1000;
	public static final int ECG_SIGNAL_LENGTH = ECG_SIGNAL_TIME_IN_SECONDS * ECG_SIGNAL_SAMPLES_PER_SECOND;
	public static final int ECG_SIGNAL_MAX_COMPLEX_POSITION_FOR_SPECIFIC_DISEASE_COMPLEXES = 3;
	public static final int CHART_SAMPLING_STEP = 3;
	public static final String CHARACTER_SPACE = " ";
	public static final String RESOURCES_PATH = "resources";
	public static final String SAMPLES_PATH = PathJoiner.join(RESOURCES_PATH, "signal_samples");
	public static final String COMPLEXES_PATH = PathJoiner.join(RESOURCES_PATH, "complex_samples");
	public static final File DEFAULT_SAVE_DIR = new File("A:\\Studia\\Praca magisterska\\Disc\\ECG Console");// FileSystemView.getFileSystemView().getHomeDirectory();
	public static final String DEFAULT_SAVE_FILENAME = "8.ekg";

}
