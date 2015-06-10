package areva.content;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

/**
 * This class represents a qualityRating listed in the qualityRatings-view
 * @author Sven von Höveling
 * @version 1.0
 *
 */
public class QualityRating {
	private String name;
	private ArrayList<Function> functions;

	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(
			this);

	/**
	 * constructor with default values
	 */
	public QualityRating() {
		name = "---";
		functions = new ArrayList<Function>();
	}

	/**
	 * constructor
	 * @param name of the qr
	 */
	public QualityRating(String name) {
		super();
		this.name = name;
		functions = new ArrayList<Function>();
	}

	/**
	 * @param propertyName
	 * @param listener
	 */
	public void addPropertyChangeListener(String propertyName,
			PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(propertyName, listener);
	}

	/**
	 * @param listener
	 */
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		propertyChangeSupport.removePropertyChangeListener(listener);
	}

	/**
	 * @return the name of the qr
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return functions of the qr
	 */
	public ArrayList<Function> getFunctions() {
		return functions;
	}

	/**
	 * @param name
	 */
	public void setName(String name) {
		propertyChangeSupport.firePropertyChange("name", this.name,
				this.name = name);
	}

	/**
	 * @param functions
	 */
	public void setFunctions(ArrayList<Function> functions) {
		this.functions = functions;
	}

	@Override
	public String toString() {
		return name;
	}

}
