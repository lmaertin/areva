/**
 */
package de.tubs.areva.resourcerelations.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;

import org.palladiosimulator.pcm.allocation.Allocation;

import de.tubs.areva.resourcerelations.Resource;
import de.tubs.areva.resourcerelations.ResourceType;
import de.tubs.areva.resourcerelations.ResourcerelationsPackage;
import de.tubs.areva.resourcerelations.Specification;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Resource</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link de.tubs.areva.resourcerelations.impl.ResourceImpl#getType <em>Type</em>}</li>
 *   <li>{@link de.tubs.areva.resourcerelations.impl.ResourceImpl#getName <em>Name</em>}</li>
 *   <li>{@link de.tubs.areva.resourcerelations.impl.ResourceImpl#getSpecs <em>Specs</em>}</li>
 *   <li>{@link de.tubs.areva.resourcerelations.impl.ResourceImpl#getInterface <em>Interface</em>}</li>
 *   <li>{@link de.tubs.areva.resourcerelations.impl.ResourceImpl#getRedundant <em>Redundant</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ResourceImpl extends MinimalEObjectImpl.Container implements Resource {
	/**
	 * The default value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected static final ResourceType TYPE_EDEFAULT = ResourceType.SENSOR;

	/**
	 * The cached value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected ResourceType type = TYPE_EDEFAULT;

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
	 * The cached value of the '{@link #getSpecs() <em>Specs</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSpecs()
	 * @generated
	 * @ordered
	 */
	protected EList<Specification> specs;

	/**
	 * The cached value of the '{@link #getInterface() <em>Interface</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInterface()
	 * @generated
	 * @ordered
	 */
	protected Allocation interface_;

	/**
	 * The cached value of the '{@link #getRedundant() <em>Redundant</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRedundant()
	 * @generated
	 * @ordered
	 */
	protected EList<Resource> redundant;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ResourceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ResourcerelationsPackage.Literals.RESOURCE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ResourceType getType() {
		return type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setType(ResourceType newType) {
		ResourceType oldType = type;
		type = newType == null ? TYPE_EDEFAULT : newType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ResourcerelationsPackage.RESOURCE__TYPE, oldType, type));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ResourcerelationsPackage.RESOURCE__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Specification> getSpecs() {
		if (specs == null) {
			specs = new EObjectResolvingEList<Specification>(Specification.class, this, ResourcerelationsPackage.RESOURCE__SPECS);
		}
		return specs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Allocation getInterface() {
		if (interface_ != null && ((EObject)interface_).eIsProxy()) {
			InternalEObject oldInterface = (InternalEObject)interface_;
			interface_ = (Allocation)eResolveProxy(oldInterface);
			if (interface_ != oldInterface) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ResourcerelationsPackage.RESOURCE__INTERFACE, oldInterface, interface_));
			}
		}
		return interface_;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Allocation basicGetInterface() {
		return interface_;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInterface(Allocation newInterface) {
		Allocation oldInterface = interface_;
		interface_ = newInterface;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ResourcerelationsPackage.RESOURCE__INTERFACE, oldInterface, interface_));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Resource> getRedundant() {
		if (redundant == null) {
			redundant = new EObjectResolvingEList<Resource>(Resource.class, this, ResourcerelationsPackage.RESOURCE__REDUNDANT);
		}
		return redundant;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ResourcerelationsPackage.RESOURCE__TYPE:
				return getType();
			case ResourcerelationsPackage.RESOURCE__NAME:
				return getName();
			case ResourcerelationsPackage.RESOURCE__SPECS:
				return getSpecs();
			case ResourcerelationsPackage.RESOURCE__INTERFACE:
				if (resolve) return getInterface();
				return basicGetInterface();
			case ResourcerelationsPackage.RESOURCE__REDUNDANT:
				return getRedundant();
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
			case ResourcerelationsPackage.RESOURCE__TYPE:
				setType((ResourceType)newValue);
				return;
			case ResourcerelationsPackage.RESOURCE__NAME:
				setName((String)newValue);
				return;
			case ResourcerelationsPackage.RESOURCE__SPECS:
				getSpecs().clear();
				getSpecs().addAll((Collection<? extends Specification>)newValue);
				return;
			case ResourcerelationsPackage.RESOURCE__INTERFACE:
				setInterface((Allocation)newValue);
				return;
			case ResourcerelationsPackage.RESOURCE__REDUNDANT:
				getRedundant().clear();
				getRedundant().addAll((Collection<? extends Resource>)newValue);
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
			case ResourcerelationsPackage.RESOURCE__TYPE:
				setType(TYPE_EDEFAULT);
				return;
			case ResourcerelationsPackage.RESOURCE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case ResourcerelationsPackage.RESOURCE__SPECS:
				getSpecs().clear();
				return;
			case ResourcerelationsPackage.RESOURCE__INTERFACE:
				setInterface((Allocation)null);
				return;
			case ResourcerelationsPackage.RESOURCE__REDUNDANT:
				getRedundant().clear();
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
			case ResourcerelationsPackage.RESOURCE__TYPE:
				return type != TYPE_EDEFAULT;
			case ResourcerelationsPackage.RESOURCE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case ResourcerelationsPackage.RESOURCE__SPECS:
				return specs != null && !specs.isEmpty();
			case ResourcerelationsPackage.RESOURCE__INTERFACE:
				return interface_ != null;
			case ResourcerelationsPackage.RESOURCE__REDUNDANT:
				return redundant != null && !redundant.isEmpty();
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
		result.append(" (type: ");
		result.append(type);
		result.append(", name: ");
		result.append(name);
		result.append(')');
		return result.toString();
	}

} //ResourceImpl
