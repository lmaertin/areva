/**
 */
package de.tubs.areva.emf.model.darg;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Quality Assignment</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.tubs.areva.emf.model.darg.QualityAssignment#getAttribute <em>Attribute</em>}</li>
 *   <li>{@link de.tubs.areva.emf.model.darg.QualityAssignment#getValue <em>Value</em>}</li>
 * </ul>
 *
 * @see de.tubs.areva.emf.model.darg.DargPackage#getQualityAssignment()
 * @model
 * @generated
 */
public interface QualityAssignment extends EObject {
	/**
	 * Returns the value of the '<em><b>Attribute</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Attribute</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Attribute</em>' attribute.
	 * @see #setAttribute(String)
	 * @see de.tubs.areva.emf.model.darg.DargPackage#getQualityAssignment_Attribute()
	 * @model
	 * @generated
	 */
	String getAttribute();

	/**
	 * Sets the value of the '{@link de.tubs.areva.emf.model.darg.QualityAssignment#getAttribute <em>Attribute</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Attribute</em>' attribute.
	 * @see #getAttribute()
	 * @generated
	 */
	void setAttribute(String value);

	/**
	 * Returns the value of the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Value</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Value</em>' attribute.
	 * @see #setValue(double)
	 * @see de.tubs.areva.emf.model.darg.DargPackage#getQualityAssignment_Value()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.Double"
	 * @generated
	 */
	double getValue();

	/**
	 * Sets the value of the '{@link de.tubs.areva.emf.model.darg.QualityAssignment#getValue <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value</em>' attribute.
	 * @see #getValue()
	 * @generated
	 */
	void setValue(double value);

} // QualityAssignment
