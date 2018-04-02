/**
 */
package de.tubs.areva.resourcerelations;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Specification</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.tubs.areva.resourcerelations.Specification#getValue <em>Value</em>}</li>
 *   <li>{@link de.tubs.areva.resourcerelations.Specification#getAssignedProp <em>Assigned Prop</em>}</li>
 *   <li>{@link de.tubs.areva.resourcerelations.Specification#getName <em>Name</em>}</li>
 * </ul>
 *
 * @see de.tubs.areva.resourcerelations.ResourcerelationsPackage#getSpecification()
 * @model
 * @generated
 */
public interface Specification extends EObject {
	/**
	 * Returns the value of the '<em><b>Value</b></em>' attribute.
	 * The default value is <code>"0.0"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Value</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Value</em>' attribute.
	 * @see #setValue(double)
	 * @see de.tubs.areva.resourcerelations.ResourcerelationsPackage#getSpecification_Value()
	 * @model default="0.0" dataType="org.eclipse.emf.ecore.xml.type.Double" required="true"
	 * @generated
	 */
	double getValue();

	/**
	 * Sets the value of the '{@link de.tubs.areva.resourcerelations.Specification#getValue <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value</em>' attribute.
	 * @see #getValue()
	 * @generated
	 */
	void setValue(double value);

	/**
	 * Returns the value of the '<em><b>Assigned Prop</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Assigned Prop</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Assigned Prop</em>' reference.
	 * @see #setAssignedProp(Property)
	 * @see de.tubs.areva.resourcerelations.ResourcerelationsPackage#getSpecification_AssignedProp()
	 * @model
	 * @generated
	 */
	Property getAssignedProp();

	/**
	 * Sets the value of the '{@link de.tubs.areva.resourcerelations.Specification#getAssignedProp <em>Assigned Prop</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Assigned Prop</em>' reference.
	 * @see #getAssignedProp()
	 * @generated
	 */
	void setAssignedProp(Property value);

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see de.tubs.areva.resourcerelations.ResourcerelationsPackage#getSpecification_Name()
	 * @model required="true"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link de.tubs.areva.resourcerelations.Specification#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

} // Specification
