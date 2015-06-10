package areva.evaluation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.ui.WorkbenchException;
import org.eclipse.ui.internal.Workbench;

import areva.AConsole;
import areva.content.Function;
import areva.content.ModelProvider;
import areva.impl.CategoryImpl;
import areva.impl.EvaluationImpl;
import areva.impl.EvaluationsImpl;
import areva.math.Interval;
import areva.math.PointsCalculator;

/**
 * This class realizes the evaluation
 * 
 * @author Sven von Höveling
 * @version 1.0
 * 
 */
@SuppressWarnings("restriction")
public class Evaluation {

	static final Double EPS = 0.1E-8;
	static private String parserName;
	static private String architecture;
	static private String architectureType;
	
	/**
	 * start-method for all evaluations
	 */
	public static void evaluateAll() {
		AConsole.out.getConsole().clearConsole();
		AConsole.out.println("Begin evaluations\n");

		//get the domain of the qadag
		EditingDomain ed = QADAGCon.getEditingDomain();

		//change perspective
		changeToEvaPerspective();

		EList<EObject> list = ed.getResourceSet().getResources().get(0)
				.getContents();

		EvaluationsImpl evaluationsImpl = (EvaluationsImpl) list.get(0);

		EvaluationImpl e;
		//trough all evaluations
		for (int i = 0; i < evaluationsImpl.getEvaluations().size(); i++) {
			e = (EvaluationImpl) evaluationsImpl.getEvaluations().get(i);

			AConsole.out
					.println("\n\n----------------------------------------\n\n");
			AConsole.out.print("Begin with evaluation " + (i + 1) + ": "
					+ e.getName() + "\n");
			//if structure is okay
			if (checkStructure(e)) {
				//if calculation is not possible, reset
				if (!calc(e)) {
					resetRatings(e);
				}
			} else {
				resetRatings(e);
			}
		}

	}

	/**
	 * opens the evaluation perspective
	 */
	public static void changeToEvaPerspective() {
		try {
			Workbench.getInstance().showPerspective(
					"areva.views.EvaluationPerspective",
					Workbench.getInstance().getActiveWorkbenchWindow());
		} catch (WorkbenchException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	/**
	 * start method for one evaluation (with index i)
	 * 
	 * @param i
	 *            , number of the evaluation
	 */
	public static void evaluate(int i) {
		AConsole.out.getConsole().clearConsole();
		AConsole.out.println("Begin evaluations\n");

		//get the domain of the qadag
		EditingDomain ed = QADAGCon.getEditingDomain();

		EList<EObject> list = ed.getResourceSet().getResources().get(0)
				.getContents();

		EvaluationsImpl evaluationsImpl = (EvaluationsImpl) list.get(0);

		//Evaluation i
		EvaluationImpl e = (EvaluationImpl) evaluationsImpl.getEvaluations()
				.get(i);
		AConsole.out.print("Begin with evaluation " + (i + 1) + ": "
				+ e.getName() + "\n");
		//if structure is okay
		if (checkStructure(e)) {
			//if calculation fails, reset
			if (!calc(e)) {
				resetRatings(e);
			}
		} else {
			resetRatings(e);
		}
	}

	/**
	 * calculate all categories directly under the evaluation item, for the
	 * levels below use recursion if one fails, "break"
	 * 
	 * @param ed
	 *            the editingDomain
	 * @return true if calculation was successful, else false
	 */
	private static boolean calc(EvaluationImpl evaluationImpl) {

		for (int i = 0; i < evaluationImpl.getCategories().size(); i++) {
			if (!calcCategory((CategoryImpl) evaluationImpl.getCategories()
					.get(i))) {
				return false;

			}
		}
		double rating = 0.0;
		for (int i = 0; i < evaluationImpl.getCategories().size(); i++) {

			rating += evaluationImpl.getCategories().get(i).getRating()
					* evaluationImpl.getCategories().get(i).getPercentage()
					/ 100;
		}

		evaluationImpl.setRating(rating);
		AConsole.out.println("\n\nEvaluation successful\nRating: " + rating);

		return true;
	}

	/**
	 * uses recursion to calculate all categories, breaks if one fails
	 * 
	 * @param c
	 *            category which is wanted to be calculated
	 * @return true if this level was successful, else false
	 */
	private static boolean calcCategory(CategoryImpl c) {
		AConsole.out.println("\ncalculate Category " + c.getName() + "\n");
		double rating = 0;
		//all categories
		for (int i = 0; i < c.getCategories().size(); i++) {

			//false if one could not be calculated
			if (!calcCategory((CategoryImpl) c.getCategories().get(i))) {
				return false;
			}
			//add ratings of underlying categories
			rating += c.getCategories().get(i).getRating()
					* c.getCategories().get(i).getPercentage() / 100;
		}
		//all elements on this level
		for (int i = 0; i < c.getElements().size(); i++) {
			AConsole.out.println("calculate element "
					+ c.getElements().get(i).getMetric());
			int index = ModelProvider.findMetricPosition(c.getElements().get(i)
					.getMetric());
			Double d = Evaluation.runMetricCode(ModelProvider
					.getMetrics().get(index).getCode(), false);
			//problem at one element
			if (d == null) {
				AConsole.out.println("Problem at Element "
						+ c.getElements().get(i).getName() + ": ");
				Evaluation.runMetricCode(
						ModelProvider.getMetrics().get(index).getCode(), true);
				return false;
			}
			
			//set the rating into the qadag
			c.getElements().get(i).setRating(d);
			AConsole.out.println("metric rating: " + d);

			//transfer the rating into a quality
			Double d2 = transferMetricRating(c.getElements().get(i)
					.getQualityRating(), d);
			//rating not in the domain of the qr
			if (d2 == null) {
				return false;
			}
			c.getElements().get(i).setQuality(d2);
			
			//add quality to rating
			rating += d2 * c.getElements().get(i).getPercentage() / 100;

			AConsole.out.println("quality:" + d2);
			// is mustHave reached?
			if (d2 < c.getElements().get(i).getMustHave()) {
				AConsole.out.println("Error: The rating of "
						+ c.getElements().get(i).getName()
						+ " is too low (mustHave: "
						+ c.getElements().get(i).getMustHave() + ")");
				return false;
			}

		}
		AConsole.out.println("quality of Category: " + rating);
		// is mustHave reached?
		if (rating < c.getMustHave()) {
			AConsole.out.println("Error: The rating of " + c.getName()
					+ " is too low (mustHave: " + c.getMustHave() + ")");
			return false;
		}
		c.setRating(rating);

		return true;
	}

	/**
	 * structure test before calculating. Tests the categories under
	 * Evaluation-item directly and the others with recursion
	 * 
	 * @param ed
	 * @return true if successful, else false
	 */
	private static boolean checkStructure(EvaluationImpl evaluationImpl) {
		// if (ed != null) {
		try {
			AConsole.out.println("Check structure...");

			//check all categories (recursion)
			for (int i = 0; i < evaluationImpl.getCategories().size(); i++) {
				if (!checkLevel((CategoryImpl) evaluationImpl.getCategories()
						.get(i))) {
					AConsole.out.println("structure test failed, please check");
					return false;
				}
			}
			//check if sum is 100
			double sum = 0.0;
			for (int i = 0; i < evaluationImpl.getCategories().size(); i++) {
				sum += evaluationImpl.getCategories().get(i).getPercentage();
			}
			if (!(Math.abs(sum - 100.0) < EPS)) {
				AConsole.out.println("structure test failed, please check");
				return false;
			}
			AConsole.out.println("done, structure okay");
			return true;

		} catch (Exception e) {
			// }
		}
		AConsole.out.println("structure test failed, please check");
		return false;

	}

	/**
	 * recursion to check category levels
	 * 
	 * @param c
	 *            category which is wanted to be checked
	 * @return boolean if successful
	 */
	private static boolean checkLevel(CategoryImpl c) {
		double sum = 0;
		for (int i = 0; i < c.getCategories().size(); i++) {
			sum += c.getCategories().get(i).getPercentage();
			if (!checkLevel((CategoryImpl) c.getCategories().get(i))) {
				//TODO if a category with 0% is okay
				// if the percentage of the category is 0.0, it's okay
				// if (!(Math.abs(((CategoryImpl) c.getCategories().get(i))
				// .getPercentage() - 0.0) < EPS)) {
				return false;
				// }

			}
		}
		//check for all elements on this level
		for (int i = 0; i < c.getElements().size(); i++) {
			sum += c.getElements().get(i).getPercentage();
			int a = ModelProvider.findMetricPosition(c.getElements().get(i)
					.getMetric());
			//no fitting metric exists
			if (a == -1) {
				AConsole.out.println("Problem at element "
						+ c.getElements().get(i).getName() + ": "
						+ c.getElements().get(i).getMetric() + " not found");
				return false;
			}
			int b = ModelProvider.findQualityRatingPosition(c.getElements()
					.get(i).getQualityRating());
			//no fitting qr exists
			if (b == -1) {
				AConsole.out.println("Problem at element "
						+ c.getElements().get(i).getName() + ": "
						+ c.getElements().get(i).getQualityRating()
						+ " not found");
				return false;
			}
		}

		if (Math.abs(sum - 100.0) < EPS) {
			return true;
			// if the percentage is close enough to 0.0 it's okay
		} else if (Math.abs(c.getPercentage()) < EPS) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * calculate the rating of one metric into a quality
	 * 
	 * @param qualityRating
	 * @param rating
	 * @return double value in percent, calculated with given qualityRating,
	 *         null if not successfull
	 */
	private static Double transferMetricRating(String qualityRating,
			Double rating) {
		int index = ModelProvider.findQualityRatingPosition(qualityRating);
		ArrayList<Function> array = ModelProvider.getQualityRatings()
				.get(index).getFunctions();
		for (int i = 0; i < array.size(); i++) {
			Interval interval = new Interval();
			interval.setInterval(array.get(i).getInterval());
			if (interval.getIntervalOK() && interval.decimalInInterval(rating)) {
				try {
					return PointsCalculator.calcSinglePoint(rating, array
							.get(i).getTerm());
				} catch (IllegalArgumentException e) {
					AConsole.out.println("Error: rating: " + rating
							+ " qualityRating:" + qualityRating);
					return null;
				}
			}
		}
		AConsole.out.println("Error: " + qualityRating + " not defined for "
				+ rating);
		return null;

	}

	/**
	 * compiles and runs the given String, together with code before and behind
	 * 
	 * @param metricCode
	 * @param withComments
	 *            if true: comments on the console
	 * @return The double value which is the rating of the metricCode, null if
	 *         there is an error
	 */
	public static Double runMetricCode(String metricCode, boolean withComments) {
		architectureType = ArchitectureCon.getXMLSelectionView().getArchitectureType();
		parserName = ArchitectureCon.getXMLSelectionView().getParserName();
		architecture = ArchitectureCon.getXMLSelectionView().getArchitecturePathFull();

		//TODO check this code to implement function for other parser and architectures
		
		//This case is for Autosar and autosarExportExplorer-parser
		if (parserName.equals("AutosarExportExplorer.class") && architectureType.equals("AUTOSAR")) {
			//create java file one
			JavaFileCreation.buildFileAutosarOne(metricCode);
			//TODO windows specific, path needs / instead of \, maybe problem at linux
			architecture = architecture.replaceAll("\\\\", "/");
			//create java file two
			JavaFileCreation.buildFileAutosarTwo(architecture);

			if (withComments) {
				AConsole.out.println("compiling...\n");
			}
			//if compiling was successful
			if (compileAutosar()) {
				if (withComments) {
					AConsole.out.println("No error");
					AConsole.out.println("\nrunning code...\n");
				}
				//try to run classes
				Double rating = runClassAutosar(withComments);
			
				if (rating != null && withComments) {
					AConsole.out.println("No error");
				}
				//rating was calculated successful
				return rating;
			} else {
				return null;
			}

		} else {
			AConsole.out.println("Combination of architecture type and parser unknown");
		}
		return null;
	}

	/**
	 * run class, which is created by code of one metric (Autosar  as architecture type)
	 * 
	 * @param withComments
	 *            if true: comments on the console
	 * @return The double value which is the rating of the metricCode, null if
	 *         there is an error
	 */
	private static Double runClassAutosar(boolean withComments) {
		try {
			String line;
			Process p = Runtime.getRuntime().exec(
					"java -classpath " + Settings.getWorkspace()+ "/ "
							+ Settings.TEMP_FILE_NAME + "2");
			// TODO
			InputStream stderr = p.getErrorStream();
			InputStream stdout = p.getInputStream();

			BufferedReader errorReader = new BufferedReader(
					new InputStreamReader(stderr));
			BufferedReader outputReader = new BufferedReader(
					new InputStreamReader(stdout));

			boolean noError = true;
			//check the errorReader shows an error, if so, retur null
			while ((line = errorReader.readLine()) != null) {
				if (withComments) {
					AConsole.out.println("error: " + line);
				}
				noError = false;
			}
			if (!noError) {
				return null;
			}
			String output = "";
			while ((line = outputReader.readLine()) != null) {
				output += line;
			}
			//split the output to separate the System.out.print lines of the output from the rating
			String[] s = output.split(";");
			for (int i = 0; i < s.length - 1; i++) {
				if (withComments) {
					AConsole.out.println("codeOutput: " + s[i]);
				}
			}
			//get the rating of the last part of the output string
			Double rating = Double.parseDouble(s[s.length - 1]);
			if (withComments) {
				AConsole.out.println("Rating:" + rating);
			}
			return rating;

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (NumberFormatException e) {
			return null;
		}
	}

	/**
	 * method to compile on runtime (autosar as architecture type)
	 * 
	 * @return true if compiling was successful
	 */
	private static boolean compileAutosar() {
		try {
			//compile file one
			Process p = Runtime.getRuntime().exec(
					"javac -classpath " + Settings.getWorkspace() + "/ "
							+ Settings.getWorkspace() + "/"
							+ Settings.TEMP_FILE_NAME + ".java");
			String line;

			InputStream stderr = p.getErrorStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					stderr));

			boolean noError = true;
			//check if the errorReader symbolizes errors
			while ((line = reader.readLine()) != null) {
				AConsole.out.println("Error: " + line);
				noError = false;
			}
			//compile file two
			p = Runtime.getRuntime().exec(
					"javac -classpath " + Settings.getWorkspace() + "/ "
							+ Settings.getWorkspace() + "/"
							+ Settings.TEMP_FILE_NAME + "2.java");
			stderr = p.getErrorStream();
			reader = new BufferedReader(new InputStreamReader(stderr));
			
			//check if the errorReader symbolizes errors
			while ((line = reader.readLine()) != null) {
				AConsole.out.println("Error: " + line);
				noError = false;
			}
			reader.close();
			return noError;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * sets the ratings back to 0.
	 * 
	 * @param evaluationImpl
	 */
	private static void resetRatings(EvaluationImpl evaluationImpl) {
		AConsole.out.println("Reset ratings...");

		evaluationImpl.setRating(0.0);
		for (int i = 0; i < evaluationImpl.getCategories().size(); i++) {
			resetLevel((CategoryImpl) evaluationImpl.getCategories().get(i));
		}
	}

	/**
	 * called by resetRatings, recursion to set ratings back to 0. * @param c
	 */
	private static void resetLevel(CategoryImpl c) {
		c.setRating(0.0);
		for (int i = 0; i < c.getCategories().size(); i++) {
			resetLevel((CategoryImpl) c.getCategories().get(i));
		}
		for (int i = 0; i < c.getElements().size(); i++) {
			c.getElements().get(i).setRating(0.0);
			c.getElements().get(i).setQuality(0.0);
		}
	}
}
