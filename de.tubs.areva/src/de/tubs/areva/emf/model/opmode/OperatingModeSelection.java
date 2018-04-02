/**
 */
package de.tubs.areva.emf.model.opmode;

import de.tubs.areva.emf.model.darg.Architecture;

import de.tubs.areva.resourcerelations.Resource;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Operating Mode Selection</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.tubs.areva.emf.model.opmode.OperatingModeSelection#getStartArchitecture <em>Start Architecture</em>}</li>
 *   <li>{@link de.tubs.areva.emf.model.opmode.OperatingModeSelection#getFailingResources <em>Failing Resources</em>}</li>
 *   <li>{@link de.tubs.areva.emf.model.opmode.OperatingModeSelection#getName <em>Name</em>}</li>
 * </ul>
 *
 * @see de.tubs.areva.emf.model.opmode.OpmodePackage#getOperatingModeSelection()
 * @model
 * @generated
 */
public interface OperatingModeSelection extends EObject {
	/**
	 * Returns the value of the '<em><b>Start Architecture</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Start Architecture</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Start Architecture</em>' reference.
	 * @see #setStartArchitecture(Architecture)
	 * @see de.tubs.areva.emf.model.opmode.OpmodePackage#getOperatingModeSelection_StartArchitecture()
	 * @model
	 * @generated
	 */
	Architecture getStartArchitecture();

	/**
	 * Sets the value of the '{@link de.tubs.areva.emf.model.opmode.OperatingModeSelection#getStartArchitecture <em>Start Architecture</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Start Architecture</em>' reference.
	 * @see #getStartArchitecture()
	 * @generated
	 */
	void setStartArchitecture(Architecture value);

	/**
	 * Returns the value of the '<em><b>Failing Resources</b></em>' reference list.
	 * The list contents are of type {@link de.tubs.areva.resourcerelations.Resource}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Failing Resources</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Failing Resources</em>' reference list.
	 * @see de.tubs.areva.emf.model.opmode.OpmodePackage#getOperatingModeSelection_FailingResources()
	 * @model
	 * @generated
	 */
	EList<Resource> getFailingResources();

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
	 * @see de.tubs.areva.emf.model.opmode.OpmodePackage#getOperatingModeSelection_Name()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link de.tubs.areva.emf.model.opmode.OperatingModeSelection#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

} // OperatingModeSelection
