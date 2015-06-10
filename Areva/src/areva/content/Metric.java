package areva.content;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * This class represents a metric listed in the metrics-view
 * 
 * @author Sven von Höveling
 * @version 1.0
 * 
 */
public class Metric {
	private String name;
	private String code;
	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(
			this);

	/**
	 * constructor with default code
	 */
	public Metric() {
		this.name = "---";
		this.code = "private static Double calcRating() {\n"
				+ "        return 0.0;\n" + "}";
	}

	/**
	 * @param name
	 * @param code
	 */
	public Metric(String name, String code) {
		super();
		this.name = name;
		this.code = code;
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
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return code
	 */
	public String getCode() {
		return this.code;
	}

	/**
	 * @param name
	 */
	public void setName(String name) {
		propertyChangeSupport.firePropertyChange("name", this.name,
				this.name = name);

	}

	/**
	 * @param code
	 */
	public void setCode(String code) {
		propertyChangeSupport.firePropertyChange("code", this.code,
				this.code = code);
	}

	@Override
	public String toString() {
		return name;
	}

}
