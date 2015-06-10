/**
 */
package areva;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link areva.Element#getName <em>Name</em>}</li>
 *   <li>{@link areva.Element#getPercentage <em>Percentage</em>}</li>
 *   <li>{@link areva.Element#getRating <em>Rating</em>}</li>
 *   <li>{@link areva.Element#getQuality <em>Quality</em>}</li>
 *   <li>{@link areva.Element#getMetric <em>Metric</em>}</li>
 *   <li>{@link areva.Element#getQualityRating <em>Quality Rating</em>}</li>
 *   <li>{@link areva.Element#getUnit <em>Unit</em>}</li>
 *   <li>{@link areva.Element#getMustHave <em>Must Have</em>}</li>
 * </ul>
 * </p>
 *
 * @see areva.ArevaPackage#getElement()
 * @model
 * @generated
 */
public interface Element extends EObject {

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see areva.ArevaPackage#getElement_Name()
	 * @model default=""
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link areva.Element#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Percentage</b></em>' attribute.
	 * The default value is <code>"0.0"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Percentage</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Percentage</em>' attribute.
	 * @see #setPercentage(double)
	 * @see areva.ArevaPackage#getElement_Percentage()
	 * @model default="0.0"
	 * @generated
	 */
	double getPercentage();

	/**
	 * Sets the value of the '{@link areva.Element#getPercentage <em>Percentage</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Percentage</em>' attribute.
	 * @see #getPercentage()
	 * @generated
	 */
	void setPercentage(double value);

	/**
	 * Returns the value of the '<em><b>Rating</b></em>' attribute.
	 * The default value is <code>"0.0"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Rating</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Rating</em>' attribute.
	 * @see #setRating(double)
	 * @see areva.ArevaPackage#getElement_Rating()
	 * @model default="0.0"
	 * @generated
	 */
	double getRating();

	/**
	 * Sets the value of the '{@link areva.Element#getRating <em>Rating</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Rating</em>' attribute.
	 * @see #getRating()
	 * @generated
	 */
	void setRating(double value);

	/**
	 * Returns the value of the '<em><b>Quality</b></em>' attribute.
	 * The default value is <code>"0.0"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Quality</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Quality</em>' attribute.
	 * @see #setQuality(double)
	 * @see areva.ArevaPackage#getElement_Quality()
	 * @model default="0.0"
	 * @generated
	 */
	double getQuality();

	/**
	 * Sets the value of the '{@link areva.Element#getQuality <em>Quality</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Quality</em>' attribute.
	 * @see #getQuality()
	 * @generated
	 */
	void setQuality(double value);

	/**
	 * Returns the value of the '<em><b>Metric</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Metric</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Metric</em>' attribute.
	 * @see #setMetric(String)
	 * @see areva.ArevaPackage#getElement_Metric()
	 * @model default=""
	 * @generated
	 */
	String getMetric();

	/**
	 * Sets the value of the '{@link areva.Element#getMetric <em>Metric</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Metric</em>' attribute.
	 * @see #getMetric()
	 * @generated
	 */
	void setMetric(String value);

	/**
	 * Returns the value of the '<em><b>Quality Rating</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Quality Rating</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Quality Rating</em>' attribute.
	 * @see #setQualityRating(String)
	 * @see areva.ArevaPackage#getElement_QualityRating()
	 * @model default=""
	 * @generated
	 */
	String getQualityRating();

	/**
	 * Sets the value of the '{@link areva.Element#getQualityRating <em>Quality Rating</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Quality Rating</em>' attribute.
	 * @see #getQualityRating()
	 * @generated
	 */
	void setQualityRating(String value);

	/**
	 * Returns the value of the '<em><b>Unit</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Unit</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Unit</em>' attribute.
	 * @see #setUnit(String)
	 * @see areva.ArevaPackage#getElement_Unit()
	 * @model
	 * @generated
	 */
	String getUnit();

	/**
	 * Sets the value of the '{@link areva.Element#getUnit <em>Unit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Unit</em>' attribute.
	 * @see #getUnit()
	 * @generated
	 */
	void setUnit(String value);

	/**
	 * Returns the value of the '<em><b>Must Have</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Must Have</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Must Have</em>' attribute.
	 * @see #setMustHave(double)
	 * @see areva.ArevaPackage#getElement_MustHave()
	 * @model
	 * @generated
	 */
	double getMustHave();

	/**
	 * Sets the value of the '{@link areva.Element#getMustHave <em>Must Have</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Must Have</em>' attribute.
	 * @see #getMustHave()
	 * @generated
	 */
	void setMustHave(double value);
} // Element
