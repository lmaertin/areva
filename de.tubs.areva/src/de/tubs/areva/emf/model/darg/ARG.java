/**
 */
package de.tubs.areva.emf.model.darg;

import de.tubs.areva.emf.model.qadag.QADAG;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>ARG</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.tubs.areva.emf.model.darg.ARG#getName <em>Name</em>}</li>
 *   <li>{@link de.tubs.areva.emf.model.darg.ARG#getArchitectures <em>Architectures</em>}</li>
 *   <li>{@link de.tubs.areva.emf.model.darg.ARG#getQadag <em>Qadag</em>}</li>
 * </ul>
 *
 * @see de.tubs.areva.emf.model.darg.DargPackage#getARG()
 * @model
 * @generated
 */
public interface ARG extends EObject {
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
	 * @see de.tubs.areva.emf.model.darg.DargPackage#getARG_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link de.tubs.areva.emf.model.darg.ARG#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Architectures</b></em>' containment reference list.
	 * The list contents are of type {@link de.tubs.areva.emf.model.darg.Architecture}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Architectures</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Architectures</em>' containment reference list.
	 * @see de.tubs.areva.emf.model.darg.DargPackage#getARG_Architectures()
	 * @model containment="true"
	 * @generated
	 */
	EList<Architecture> getArchitectures();

	/**
	 * Returns the value of the '<em><b>Qadag</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Qadag</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Qadag</em>' reference.
	 * @see #setQadag(QADAG)
	 * @see de.tubs.areva.emf.model.darg.DargPackage#getARG_Qadag()
	 * @model
	 * @generated
	 */
	QADAG getQadag();

	/**
	 * Sets the value of the '{@link de.tubs.areva.emf.model.darg.ARG#getQadag <em>Qadag</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Qadag</em>' reference.
	 * @see #getQadag()
	 * @generated
	 */
	void setQadag(QADAG value);

} // ARG
