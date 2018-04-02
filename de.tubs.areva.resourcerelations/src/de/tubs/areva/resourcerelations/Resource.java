/**
 */
package de.tubs.areva.resourcerelations;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

import org.palladiosimulator.pcm.allocation.Allocation;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Resource</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.tubs.areva.resourcerelations.Resource#getType <em>Type</em>}</li>
 *   <li>{@link de.tubs.areva.resourcerelations.Resource#getName <em>Name</em>}</li>
 *   <li>{@link de.tubs.areva.resourcerelations.Resource#getSpecs <em>Specs</em>}</li>
 *   <li>{@link de.tubs.areva.resourcerelations.Resource#getInterface <em>Interface</em>}</li>
 *   <li>{@link de.tubs.areva.resourcerelations.Resource#getRedundant <em>Redundant</em>}</li>
 * </ul>
 *
 * @see de.tubs.areva.resourcerelations.ResourcerelationsPackage#getResource()
 * @model
 * @generated
 */
public interface Resource extends EObject {
	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * The default value is <code>"sensor"</code>.
	 * The literals are from the enumeration {@link de.tubs.areva.resourcerelations.ResourceType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see de.tubs.areva.resourcerelations.ResourceType
	 * @see #setType(ResourceType)
	 * @see de.tubs.areva.resourcerelations.ResourcerelationsPackage#getResource_Type()
	 * @model default="sensor" required="true"
	 * @generated
	 */
	ResourceType getType();

	/**
	 * Sets the value of the '{@link de.tubs.areva.resourcerelations.Resource#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see de.tubs.areva.resourcerelations.ResourceType
	 * @see #getType()
	 * @generated
	 */
	void setType(ResourceType value);

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
	 * @see de.tubs.areva.resourcerelations.ResourcerelationsPackage#getResource_Name()
	 * @model required="true"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link de.tubs.areva.resourcerelations.Resource#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Specs</b></em>' reference list.
	 * The list contents are of type {@link de.tubs.areva.resourcerelations.Specification}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Specs</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Specs</em>' reference list.
	 * @see de.tubs.areva.resourcerelations.ResourcerelationsPackage#getResource_Specs()
	 * @model
	 * @generated
	 */
	EList<Specification> getSpecs();

	/**
	 * Returns the value of the '<em><b>Interface</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Interface</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Interface</em>' reference.
	 * @see #setInterface(Allocation)
	 * @see de.tubs.areva.resourcerelations.ResourcerelationsPackage#getResource_Interface()
	 * @model
	 * @generated
	 */
	Allocation getInterface();

	/**
	 * Sets the value of the '{@link de.tubs.areva.resourcerelations.Resource#getInterface <em>Interface</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Interface</em>' reference.
	 * @see #getInterface()
	 * @generated
	 */
	void setInterface(Allocation value);

	/**
	 * Returns the value of the '<em><b>Redundant</b></em>' reference list.
	 * The list contents are of type {@link de.tubs.areva.resourcerelations.Resource}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Redundant</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Redundant</em>' reference list.
	 * @see de.tubs.areva.resourcerelations.ResourcerelationsPackage#getResource_Redundant()
	 * @model
	 * @generated
	 */
	EList<Resource> getRedundant();

} // Resource
