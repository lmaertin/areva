package areva.evaluation;

import java.io.File;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;

/**
 * This class collects some settings for the evaluation
 * 
 * @author Sven von Höveling
 * @version 1.0
 * 
 */
public class Settings {

	public static final String TEMP_FILE_NAME = "Temp";
	
	//Part 2 is the metric itself
	public static final String METRIC_CLASS_PART_1 = "import org.jdom.*; \n"
			+ "import java.util.*; \n"
			+ "public class Temp extends AutosarExportExplorer{ \n"
			+ "public Temp(AutosarExport ae){ \n"
			+ "super(ae);\n"
			+ "} \n";
	public static final String CLASS_PART_3 = "}";
	
	//Part 2 is the path of the architecture
	public static final String MAIN_CLASS_PART_1 = "import org.jdom.*; \n"
			+ "import java.util.*; \n"
			+ "public class Temp2 {\n"
			+ "public static void main(String[] args) {\n"
			+ "ARXMLLoader neu = new ARXMLLoader();\n"
			+ "AutosarExport a = neu.loadArchitecture(\"";
	public static final String MAIN_CLASS_PART_3 = "\");\n"
			+ "Temp tmp = new Temp(a);\n"
			+ "System.out.println(\"result:;\" + tmp.calcRating());\n" + "}\n"+ "}";

	/**
	 * @return path of the workspace
	 */
	public static String getWorkspace() {
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		File file = workspace.getRoot().getLocation().toFile();
		return file.getPath();
	}

	/**
	 * @return classpath
	 */
	public static String getClasspath() {
		return getWorkspace();
	}

}
