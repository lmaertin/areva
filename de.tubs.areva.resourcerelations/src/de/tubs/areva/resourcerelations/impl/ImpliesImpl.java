/**
 */
package de.tubs.areva.resourcerelations.impl;

import de.tubs.areva.resourcerelations.Expression;
import de.tubs.areva.resourcerelations.Implies;
import de.tubs.areva.resourcerelations.ResourceOptionsVariable;
import de.tubs.areva.resourcerelations.ResourcerelationsPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Implies</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link de.tubs.areva.resourcerelations.impl.ImpliesImpl#getLeftHandSide <em>Left Hand Side</em>}</li>
 *   <li>{@link de.tubs.areva.resourcerelations.impl.ImpliesImpl#getRightHandSide <em>Right Hand Side</em>}</li>
 *   <li>{@link de.tubs.areva.resourcerelations.impl.ImpliesImpl#isLeftHandSideModifier <em>Left Hand Side Modifier</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ImpliesImpl extends MinimalEObjectImpl.Container implements Implies {
	/**
	 * The cached value of the '{@link #getLeftHandSide() <em>Left Hand Side</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLeftHandSide()
	 * @generated
	 * @ordered
	 */
	protected ResourceOptionsVariable leftHandSide;

	/**
	 * The cached value of the '{@link #getRightHandSide() <em>Right Hand Side</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRightHandSide()
	 * @generated
	 * @ordered
	 */
	protected Expression rightHandSide;

	/**
	 * The default value of the '{@link #isLeftHandSideModifier() <em>Left Hand Side Modifier</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isLeftHandSideModifier()
	 * @generated
	 * @ordered
	 */
	protected static final boolean LEFT_HAND_SIDE_MODIFIER_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isLeftHandSideModifier() <em>Left Hand Side Modifier</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isLeftHandSideModifier()
	 * @generated
	 * @ordered
	 */
	protected boolean leftHandSideModifier = LEFT_HAND_SIDE_MODIFIER_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ImpliesImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ResourcerelationsPackage.Literals.IMPLIES;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ResourceOptionsVariable getLeftHandSide() {
		return leftHandSide;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetLeftHandSide(ResourceOptionsVariable newLeftHandSide, NotificationChain msgs) {
		ResourceOptionsVariable oldLeftHandSide = leftHandSide;
		leftHandSide = newLeftHandSide;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ResourcerelationsPackage.IMPLIES__LEFT_HAND_SIDE, oldLeftHandSide, newLeftHandSide);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLeftHandSide(ResourceOptionsVariable newLeftHandSide) {
		if (newLeftHandSide != leftHandSide) {
			NotificationChain msgs = null;
			if (leftHandSide != null)
				msgs = ((InternalEObject)leftHandSide).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ResourcerelationsPackage.IMPLIES__LEFT_HAND_SIDE, null, msgs);
			if (newLeftHandSide != null)
				msgs = ((InternalEObject)newLeftHandSide).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ResourcerelationsPackage.IMPLIES__LEFT_HAND_SIDE, null, msgs);
			msgs = basicSetLeftHandSide(newLeftHandSide, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ResourcerelationsPackage.IMPLIES__LEFT_HAND_SIDE, newLeftHandSide, newLeftHandSide));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Expression getRightHandSide() {
		return rightHandSide;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetRightHandSide(Expression newRightHandSide, NotificationChain msgs) {
		Expression oldRightHandSide = rightHandSide;
		rightHandSide = newRightHandSide;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ResourcerelationsPackage.IMPLIES__RIGHT_HAND_SIDE, oldRightHandSide, newRightHandSide);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRightHandSide(Expression newRightHandSide) {
		if (newRightHandSide != rightHandSide) {
			NotificationChain msgs = null;
			if (rightHandSide != null)
				msgs = ((InternalEObject)rightHandSide).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ResourcerelationsPackage.IMPLIES__RIGHT_HAND_SIDE, null, msgs);
			if (newRightHandSide != null)
				msgs = ((InternalEObject)newRightHandSide).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ResourcerelationsPackage.IMPLIES__RIGHT_HAND_SIDE, null, msgs);
			msgs = basicSetRightHandSide(newRightHandSide, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ResourcerelationsPackage.IMPLIES__RIGHT_HAND_SIDE, newRightHandSide, newRightHandSide));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isLeftHandSideModifier() {
		return leftHandSideModifier;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLeftHandSideModifier(boolean newLeftHandSideModifier) {
		boolean oldLeftHandSideModifier = leftHandSideModifier;
		leftHandSideModifier = newLeftHandSideModifier;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ResourcerelationsPackage.IMPLIES__LEFT_HAND_SIDE_MODIFIER, oldLeftHandSideModifier, leftHandSideModifier));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ResourcerelationsPackage.IMPLIES__LEFT_HAND_SIDE:
				return basicSetLeftHandSide(null, msgs);
			case ResourcerelationsPackage.IMPLIES__RIGHT_HAND_SIDE:
				return basicSetRightHandSide(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ResourcerelationsPackage.IMPLIES__LEFT_HAND_SIDE:
				return getLeftHandSide();
			case ResourcerelationsPackage.IMPLIES__RIGHT_HAND_SIDE:
				return getRightHandSide();
			case ResourcerelationsPackage.IMPLIES__LEFT_HAND_SIDE_MODIFIER:
				return isLeftHandSideModifier();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ResourcerelationsPackage.IMPLIES__LEFT_HAND_SIDE:
				setLeftHandSide((ResourceOptionsVariable)newValue);
				return;
			case ResourcerelationsPackage.IMPLIES__RIGHT_HAND_SIDE:
				setRightHandSide((Expression)newValue);
				return;
			case ResourcerelationsPackage.IMPLIES__LEFT_HAND_SIDE_MODIFIER:
				setLeftHandSideModifier((Boolean)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case ResourcerelationsPackage.IMPLIES__LEFT_HAND_SIDE:
				setLeftHandSide((ResourceOptionsVariable)null);
				return;
			case ResourcerelationsPackage.IMPLIES__RIGHT_HAND_SIDE:
				setRightHandSide((Expression)null);
				return;
			case ResourcerelationsPackage.IMPLIES__LEFT_HAND_SIDE_MODIFIER:
				setLeftHandSideModifier(LEFT_HAND_SIDE_MODIFIER_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case ResourcerelationsPackage.IMPLIES__LEFT_HAND_SIDE:
				return leftHandSide != null;
			case ResourcerelationsPackage.IMPLIES__RIGHT_HAND_SIDE:
				return rightHandSide != null;
			case ResourcerelationsPackage.IMPLIES__LEFT_HAND_SIDE_MODIFIER:
				return leftHandSideModifier != LEFT_HAND_SIDE_MODIFIER_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (leftHandSideModifier: ");
		result.append(leftHandSideModifier);
		result.append(')');
		return result.toString();
	}

} //ImpliesImpl
