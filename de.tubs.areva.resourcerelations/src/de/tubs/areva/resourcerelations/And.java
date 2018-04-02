/**
 */
package de.tubs.areva.resourcerelations;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>And</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.tubs.areva.resourcerelations.And#getLeftHandSide <em>Left Hand Side</em>}</li>
 *   <li>{@link de.tubs.areva.resourcerelations.And#getRightHandSide <em>Right Hand Side</em>}</li>
 * </ul>
 *
 * @see de.tubs.areva.resourcerelations.ResourcerelationsPackage#getAnd()
 * @model
 * @generated
 */
public interface And extends Expression {
	/**
	 * Returns the value of the '<em><b>Left Hand Side</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Left Hand Side</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Left Hand Side</em>' containment reference.
	 * @see #setLeftHandSide(Expression)
	 * @see de.tubs.areva.resourcerelations.ResourcerelationsPackage#getAnd_LeftHandSide()
	 * @model containment="true"
	 * @generated
	 */
	Expression getLeftHandSide();

	/**
	 * Sets the value of the '{@link de.tubs.areva.resourcerelations.And#getLeftHandSide <em>Left Hand Side</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Left Hand Side</em>' containment reference.
	 * @see #getLeftHandSide()
	 * @generated
	 */
	void setLeftHandSide(Expression value);

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
	 * @see de.tubs.areva.resourcerelations.ResourcerelationsPackage#getAnd_RightHandSide()
	 * @model containment="true"
	 * @generated
	 */
	Expression getRightHandSide();

	/**
	 * Sets the value of the '{@link de.tubs.areva.resourcerelations.And#getRightHandSide <em>Right Hand Side</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Right Hand Side</em>' containment reference.
	 * @see #getRightHandSide()
	 * @generated
	 */
	void setRightHandSide(Expression value);

} // And
