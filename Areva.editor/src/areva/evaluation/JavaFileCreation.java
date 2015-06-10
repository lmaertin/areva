package areva.evaluation;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * This class creates java files, which can be compiled later and which are used to calculate a metric
 * for evaluation
 * 
 * @author Sven von Höveling
 * @version 1.0
 * 
 */
public class JavaFileCreation {

	/**
	 * 
	 * @param metricCode
	 *            which should be compiled together with code before and behind
	 */
	public static void buildFileAutosarOne(String metricCode) {
		//TODO
		PrintWriter output;
		BufferedWriter buffer;
		FileWriter fileWriter;
		File outputFile = new File(Settings.getWorkspace() + "/"
				+ Settings.TEMP_FILE_NAME + ".java");
		try {
			fileWriter = new FileWriter(outputFile);
			buffer = new BufferedWriter(fileWriter);
			output = new PrintWriter(buffer);
			try {
				output.println(Settings.METRIC_CLASS_PART_1);
				output.println(metricCode);
				output.println(Settings.CLASS_PART_3);
			} finally {
				output.close();
				buffer.close();
				fileWriter.close();
			}
		} catch (IOException e) {
			System.out.println("Fehler");
		}
	}

	/**
	 * build main class which starts a method from AutosarOne, this class includes main-method
	 * @param architecturePath
	 */
	public static void buildFileAutosarTwo(String architecturePath) {
		PrintWriter output;
		BufferedWriter buffer;
		FileWriter fileWriter;
		File outputFile = new File(Settings.getWorkspace() + "/"
				+ Settings.TEMP_FILE_NAME + "2.java");

		try {
			fileWriter = new FileWriter(outputFile);
			buffer = new BufferedWriter(fileWriter);
			output = new PrintWriter(buffer);
			try {
				output.print(Settings.MAIN_CLASS_PART_1);
				output.print(architecturePath);
				output.println(Settings.MAIN_CLASS_PART_3);
			} finally {
				output.close();
				buffer.close();
				fileWriter.close();
			}
		} catch (IOException e) {
			System.out.println("Fehler");
		}
	}
}
