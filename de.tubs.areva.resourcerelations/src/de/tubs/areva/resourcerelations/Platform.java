/**
 */
package de.tubs.areva.resourcerelations;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Platform</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.tubs.areva.resourcerelations.Platform#getOptions <em>Options</em>}</li>
 *   <li>{@link de.tubs.areva.resourcerelations.Platform#getSpecs <em>Specs</em>}</li>
 *   <li>{@link de.tubs.areva.resourcerelations.Platform#getProps <em>Props</em>}</li>
 *   <li>{@link de.tubs.areva.resourcerelations.Platform#getResources <em>Resources</em>}</li>
 *   <li>{@link de.tubs.areva.resourcerelations.Platform#getRules <em>Rules</em>}</li>
 * </ul>
 *
 * @see de.tubs.areva.resourcerelations.ResourcerelationsPackage#getPlatform()
 * @model
 * @generated
 */
public interface Platform extends EObject {
	/**
	 * Returns the value of the '<em><b>Options</b></em>' containment reference list.
	 * The list contents are of type {@link de.tubs.areva.resourcerelations.ResourceOptions}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Options</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Options</em>' containment reference list.
	 * @see de.tubs.areva.resourcerelations.ResourcerelationsPackage#getPlatform_Options()
	 * @model containment="true"
	 * @generated
	 */
	EList<ResourceOptions> getOptions();

	/**
	 * Returns the value of the '<em><b>Specs</b></em>' containment reference list.
	 * The list contents are of type {@link de.tubs.areva.resourcerelations.Specification}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Specs</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Specs</em>' containment reference list.
	 * @see de.tubs.areva.resourcerelations.ResourcerelationsPackage#getPlatform_Specs()
	 * @model containment="true"
	 * @generated
	 */
	EList<Specification> getSpecs();

	/**
	 * Returns the value of the '<em><b>Props</b></em>' containment reference list.
	 * The list contents are of type {@link de.tubs.areva.resourcerelations.Property}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Props</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Props</em>' containment reference list.
	 * @see de.tubs.areva.resourcerelations.ResourcerelationsPackage#getPlatform_Props()
	 * @model containment="true"
	 * @generated
	 */
	EList<Property> getProps();

	/**
	 * Returns the value of the '<em><b>Resources</b></em>' containment reference list.
	 * The list contents are of type {@link de.tubs.areva.resourcerelations.Resource}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Resources</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Resources</em>' containment reference list.
	 * @see de.tubs.areva.resourcerelations.ResourcerelationsPackage#getPlatform_Resources()
	 * @model containment="true"
	 * @generated
	 */
	EList<Resource> getResources();

	/**
	 * Returns the value of the '<em><b>Rules</b></em>' containment reference list.
	 * The list contents are of type {@link de.tubs.areva.resourcerelations.Rule}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Rules</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Rules</em>' containment reference list.
	 * @see de.tubs.areva.resourcerelations.ResourcerelationsPackage#getPlatform_Rules()
	 * @model containment="true"
	 * @generated
	 */
	EList<Rule> getRules();

} // Platform
