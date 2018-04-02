/**
 */
package de.tubs.areva.resourcerelations;

import org.eclipse.emf.ecore.EObject;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Implies</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.tubs.areva.resourcerelations.Implies#getLeftHandSide <em>Left Hand Side</em>}</li>
 *   <li>{@link de.tubs.areva.resourcerelations.Implies#getRightHandSide <em>Right Hand Side</em>}</li>
 *   <li>{@link de.tubs.areva.resourcerelations.Implies#isLeftHandSideModifier <em>Left Hand Side Modifier</em>}</li>
 * </ul>
 *
 * @see de.tubs.areva.resourcerelations.ResourcerelationsPackage#getImplies()
 * @model
 * @generated
 */
public interface Implies extends EObject {
	/**
	 * Returns the value of the '<em><b>Left Hand Side</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Left Hand Side</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Left Hand Side</em>' containment reference.
	 * @see #setLeftHandSide(ResourceOptionsVariable)
	 * @see de.tubs.areva.resourcerelations.ResourcerelationsPackage#getImplies_LeftHandSide()
	 * @model containment="true"
	 * @generated
	 */
	ResourceOptionsVariable getLeftHandSide();

	/**
	 * Sets the value of the '{@link de.tubs.areva.resourcerelations.Implies#getLeftHandSide <em>Left Hand Side</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Left Hand Side</em>' containment reference.
	 * @see #getLeftHandSide()
	 * @generated
	 */
	void setLeftHandSide(ResourceOptionsVariable value);

	/**
	 * Returns the value of the '<em><b>Right Hand Side</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Right Hand Side</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Right Hand Side</em>' containment reference.
	 * @see #setRightHandSide(Expression)
	 * @see de.tubs.areva.resourcerelations.ResourcerelationsPackage#getImplies_RightHandSide()
	 * @model containment="true"
	 * @generated
	 */
	Expression getRightHandSide();

	/**
	 * Sets the value of the '{@link de.tubs.areva.resourcerelations.Implies#getRightHandSide <em>Right Hand Side</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Right Hand Side</em>' containment reference.
	 * @see #getRightHandSide()
	 * @generated
	 */
	void setRightHandSide(Expression value);

	/**
	 * Returns the value of the '<em><b>Left Hand Side Modifier</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Left Hand Side Modifier</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Left Hand Side Modifier</em>' attribute.
	 * @see #setLeftHandSideModifier(boolean)
	 * @see de.tubs.areva.resourcerelations.ResourcerelationsPackage#getImplies_LeftHandSideModifier()
	 * @model default="false"
	 * @generated
	 */
	boolean isLeftHandSideModifier();

	/**
	 * Sets the value of the '{@link de.tubs.areva.resourcerelations.Implies#isLeftHandSideModifier <em>Left Hand Side Modifier</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Left Hand Side Modifier</em>' attribute.
	 * @see #isLeftHandSideModifier()
	 * @generated
	 */
	void setLeftHandSideModifier(boolean value);

} // Implies
