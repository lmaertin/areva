/**
 */
package de.tubs.areva.emf.model.opmode.impl;

import de.tubs.areva.emf.model.darg.Architecture;

import de.tubs.areva.emf.model.opmode.OperatingModeSelection;
import de.tubs.areva.emf.model.opmode.OpmodePackage;

import de.tubs.areva.resourcerelations.Resource;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Operating Mode Selection</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link de.tubs.areva.emf.model.opmode.impl.OperatingModeSelectionImpl#getStartArchitecture <em>Start Architecture</em>}</li>
 *   <li>{@link de.tubs.areva.emf.model.opmode.impl.OperatingModeSelectionImpl#getFailingResources <em>Failing Resources</em>}</li>
 *   <li>{@link de.tubs.areva.emf.model.opmode.impl.OperatingModeSelectionImpl#getName <em>Name</em>}</li>
 * </ul>
 *
 * @generated
 */
public class OperatingModeSelectionImpl extends MinimalEObjectImpl.Container implements OperatingModeSelection {
	/**
	 * The cached value of the '{@link #getStartArchitecture() <em>Start Architecture</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStartArchitecture()
	 * @generated
	 * @ordered
	 */
	protected Architecture startArchitecture;

	/**
	 * The cached value of the '{@link #getFailingResources() <em>Failing Resources</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFailingResources()
	 * @generated
	 * @ordered
	 */
	protected EList<Resource> failingResources;

	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected OperatingModeSelectionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return OpmodePackage.Literals.OPERATING_MODE_SELECTION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Architecture getStartArchitecture() {
		if (startArchitecture != null && startArchitecture.eIsProxy()) {
			InternalEObject oldStartArchitecture = (InternalEObject)startArchitecture;
			startArchitecture = (Architecture)eResolveProxy(oldStartArchitecture);
			if (startArchitecture != oldStartArchitecture) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, OpmodePackage.OPERATING_MODE_SELECTION__START_ARCHITECTURE, oldStartArchitecture, startArchitecture));
			}
		}
		return startArchitecture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Architecture basicGetStartArchitecture() {
		return startArchitecture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStartArchitecture(Architecture newStartArchitecture) {
		Architecture oldStartArchitecture = startArchitecture;
		startArchitecture = newStartArchitecture;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OpmodePackage.OPERATING_MODE_SELECTION__START_ARCHITECTURE, oldStartArchitecture, startArchitecture));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Resource> getFailingResources() {
		if (failingResources == null) {
			failingResources = new EObjectResolvingEList<Resource>(Resource.class, this, OpmodePackage.OPERATING_MODE_SELECTION__FAILING_RESOURCES);
		}
		return failingResources;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OpmodePackage.OPERATING_MODE_SELECTION__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case OpmodePackage.OPERATING_MODE_SELECTION__START_ARCHITECTURE:
				if (resolve) return getStartArchitecture();
				return basicGetStartArchitecture();
			case OpmodePackage.OPERATING_MODE_SELECTION__FAILING_RESOURCES:
				return getFailingResources();
			case OpmodePackage.OPERATING_MODE_SELECTION__NAME:
				return getName();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case OpmodePackage.OPERATING_MODE_SELECTION__START_ARCHITECTURE:
				setStartArchitecture((Architecture)newValue);
				return;
			case OpmodePackage.OPERATING_MODE_SELECTION__FAILING_RESOURCES:
				getFailingResources().clear();
				getFailingResources().addAll((Collection<? extends Resource>)newValue);
				return;
			case OpmodePackage.OPERATING_MODE_SELECTION__NAME:
				setName((String)newValue);
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
			case OpmodePackage.OPERATING_MODE_SELECTION__START_ARCHITECTURE:
				setStartArchitecture((Architecture)null);
				return;
			case OpmodePackage.OPERATING_MODE_SELECTION__FAILING_RESOURCES:
				getFailingResources().clear();
				return;
			case OpmodePackage.OPERATING_MODE_SELECTION__NAME:
				setName(NAME_EDEFAULT);
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
			case OpmodePackage.OPERATING_MODE_SELECTION__START_ARCHITECTURE:
				return startArchitecture != null;
			case OpmodePackage.OPERATING_MODE_SELECTION__FAILING_RESOURCES:
				return failingResources != null && !failingResources.isEmpty();
			case OpmodePackage.OPERATING_MODE_SELECTION__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
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
		result.append(" (name: ");
		result.append(name);
		result.append(')');
		return result.toString();
	}

} //OperatingModeSelectionImpl
