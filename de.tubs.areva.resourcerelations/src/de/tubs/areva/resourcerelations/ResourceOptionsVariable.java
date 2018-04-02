/**
 */
package de.tubs.areva.resourcerelations;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Resource Options Variable</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.tubs.areva.resourcerelations.ResourceOptionsVariable#getResourceOptions <em>Resource Options</em>}</li>
 * </ul>
 *
 * @see de.tubs.areva.resourcerelations.ResourcerelationsPackage#getResourceOptionsVariable()
 * @model
 * @generated
 */
public interface ResourceOptionsVariable extends Expression {
	/**
	 * Returns the value of the '<em><b>Resource Options</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Resource Options</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Resource Options</em>' reference.
	 * @see #setResourceOptions(ResourceOptions)
	 * @see de.tubs.areva.resourcerelations.ResourcerelationsPackage#getResourceOptionsVariable_ResourceOptions()
	 * @model
	 * @generated
	 */
	ResourceOptions getResourceOptions();

	/**
	 * Sets the value of the '{@link de.tubs.areva.resourcerelations.ResourceOptionsVariable#getResourceOptions <em>Resource Options</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Resource Options</em>' reference.
	 * @see #getResourceOptions()
	 * @generated
	 */
	void setResourceOptions(ResourceOptions value);

} // ResourceOptionsVariable
