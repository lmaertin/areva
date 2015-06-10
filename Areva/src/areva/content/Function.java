package areva.content;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * This class represents a term and a interval. It's used in the function
 * definition view to store the content of one line
 * 
 * @author Sven von Höveling
 * @version 1.0
 * 
 */
public class Function {
	private String term;
	private String interval;
	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(
			this);

	/**
	 * constructor with default values
	 */
	public Function() {
		this.term = "---";
		this.interval = "---";
	}

	/**
	 * constructor
	 * 
	 * @param interval
	 * @param term
	 */
	public Function(String interval, String term) {
		super();
		this.term = term;
		this.interval = interval;
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
	 * @return the term/function
	 */
	public String getTerm() {
		return term;
	}

	/**
	 * @return interval
	 */
	public String getInterval() {
		return interval;
	}

	/**
	 * @param term
	 *            new term of the function
	 */
	public void setTerm(String term) {
		propertyChangeSupport.firePropertyChange("term", this.term,
				this.term = term);
	}

	/**
	 * @param interval
	 */
	public void setInterval(String interval) {
		propertyChangeSupport.firePropertyChange("interval", this.interval,
				this.interval = interval);
	}

	@Override
	public String toString() {
		return interval + " " + term;
	}

}
