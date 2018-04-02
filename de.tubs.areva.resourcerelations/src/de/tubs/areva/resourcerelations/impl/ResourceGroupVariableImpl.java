/**
 */
package de.tubs.areva.resourcerelations.impl;

import de.tubs.areva.resourcerelations.ResourceGroupVariable;
import de.tubs.areva.resourcerelations.ResourceOptions;
import de.tubs.areva.resourcerelations.ResourcerelationsPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Resource Group Variable</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link de.tubs.areva.resourcerelations.impl.ResourceGroupVariableImpl#getResourceOptions <em>Resource Options</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ResourceGroupVariableImpl extends ExpressionImpl implements ResourceGroupVariable {
	/**
	 * The cached value of the '{@link #getResourceOptions() <em>Resource Options</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResourceOptions()
	 * @generated
	 * @ordered
	 */
	protected ResourceOptions resourceOptions;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ResourceGroupVariableImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ResourcerelationsPackage.Literals.RESOURCE_OPTIONS_VARIABLE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ResourceOptions getResourceOptions() {
		if (resourceOptions != null && resourceOptions.eIsProxy()) {
			InternalEObject oldResourceOptions = (InternalEObject)resourceOptions;
			resourceOptions = (ResourceOptions)eResolveProxy(oldResourceOptions);
			if (resourceOptions != oldResourceOptions) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ResourcerelationsPackage.RESOURCE_OPTIONS_VARIABLE__RESOURCE_OPTIONS, oldResourceOptions, resourceOptions));
			}
		}
		return resourceOptions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ResourceOptions basicGetResourceOptions() {
		return resourceOptions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setResourceOptions(ResourceOptions newResourceOptions) {
		ResourceOptions oldResourceOptions = resourceOptions;
		resourceOptions = newResourceOptions;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ResourcerelationsPackage.RESOURCE_OPTIONS_VARIABLE__RESOURCE_OPTIONS, oldResourceOptions, resourceOptions));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ResourcerelationsPackage.RESOURCE_OPTIONS_VARIABLE__RESOURCE_OPTIONS:
				if (resolve) return getResourceOptions();
				return basicGetResourceOptions();
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
			case ResourcerelationsPackage.RESOURCE_OPTIONS_VARIABLE__RESOURCE_OPTIONS:
				setResourceOptions((ResourceOptions)newValue);
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
			case ResourcerelationsPackage.RESOURCE_OPTIONS_VARIABLE__RESOURCE_OPTIONS:
				setResourceOptions((ResourceOptions)null);
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
			case ResourcerelationsPackage.RESOURCE_OPTIONS_VARIABLE__RESOURCE_OPTIONS:
				return resourceOptions != null;
		}
		return super.eIsSet(featureID);
	}

} //ResourceGroupVariableImpl
