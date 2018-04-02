/**
 */
package de.tubs.areva.resourcerelations.impl;

import de.tubs.areva.resourcerelations.Resource;
import de.tubs.areva.resourcerelations.ResourceOptions;
import de.tubs.areva.resourcerelations.ResourcerelationsPackage;
import de.tubs.areva.resourcerelations.Specification;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;

import org.palladiosimulator.pcm.repository.BasicComponent;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Resource Options</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link de.tubs.areva.resourcerelations.impl.ResourceOptionsImpl#getResources <em>Resources</em>}</li>
 *   <li>{@link de.tubs.areva.resourcerelations.impl.ResourceOptionsImpl#getName <em>Name</em>}</li>
 *   <li>{@link de.tubs.areva.resourcerelations.impl.ResourceOptionsImpl#getMinElements <em>Min Elements</em>}</li>
 *   <li>{@link de.tubs.areva.resourcerelations.impl.ResourceOptionsImpl#getMaxElements <em>Max Elements</em>}</li>
 *   <li>{@link de.tubs.areva.resourcerelations.impl.ResourceOptionsImpl#getReferencedComponent <em>Referenced Component</em>}</li>
 *   <li>{@link de.tubs.areva.resourcerelations.impl.ResourceOptionsImpl#getSpecs <em>Specs</em>}</li>
 *   <li>{@link de.tubs.areva.resourcerelations.impl.ResourceOptionsImpl#getSubGroups <em>Sub Groups</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ResourceOptionsImpl extends MinimalEObjectImpl.Container implements ResourceOptions {
	/**
	 * The cached value of the '{@link #getResources() <em>Resources</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResources()
	 * @generated
	 * @ordered
	 */
	protected EList<Resource> resources;

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
	 * The default value of the '{@link #getMinElements() <em>Min Elements</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMinElements()
	 * @generated
	 * @ordered
	 */
	protected static final String MIN_ELEMENTS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getMinElements() <em>Min Elements</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMinElements()
	 * @generated
	 * @ordered
	 */
	protected String minElements = MIN_ELEMENTS_EDEFAULT;

	/**
	 * The default value of the '{@link #getMaxElements() <em>Max Elements</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaxElements()
	 * @generated
	 * @ordered
	 */
	protected static final String MAX_ELEMENTS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getMaxElements() <em>Max Elements</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaxElements()
	 * @generated
	 * @ordered
	 */
	protected String maxElements = MAX_ELEMENTS_EDEFAULT;

	/**
	 * The cached value of the '{@link #getReferencedComponent() <em>Referenced Component</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReferencedComponent()
	 * @generated
	 * @ordered
	 */
	protected BasicComponent referencedComponent;

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
	 * The cached value of the '{@link #getSubGroups() <em>Sub Groups</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSubGroups()
	 * @generated
	 * @ordered
	 */
	protected EList<ResourceOptions> subGroups;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ResourceOptionsImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ResourcerelationsPackage.Literals.RESOURCE_OPTIONS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Resource> getResources() {
		if (resources == null) {
			resources = new EObjectResolvingEList<Resource>(Resource.class, this, ResourcerelationsPackage.RESOURCE_OPTIONS__RESOURCES);
		}
		return resources;
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
			eNotify(new ENotificationImpl(this, Notification.SET, ResourcerelationsPackage.RESOURCE_OPTIONS__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getMinElements() {
		return minElements;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMinElements(String newMinElements) {
		String oldMinElements = minElements;
		minElements = newMinElements;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ResourcerelationsPackage.RESOURCE_OPTIONS__MIN_ELEMENTS, oldMinElements, minElements));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getMaxElements() {
		return maxElements;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMaxElements(String newMaxElements) {
		String oldMaxElements = maxElements;
		maxElements = newMaxElements;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ResourcerelationsPackage.RESOURCE_OPTIONS__MAX_ELEMENTS, oldMaxElements, maxElements));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BasicComponent getReferencedComponent() {
		if (referencedComponent != null && ((EObject)referencedComponent).eIsProxy()) {
			InternalEObject oldReferencedComponent = (InternalEObject)referencedComponent;
			referencedComponent = (BasicComponent)eResolveProxy(oldReferencedComponent);
			if (referencedComponent != oldReferencedComponent) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ResourcerelationsPackage.RESOURCE_OPTIONS__REFERENCED_COMPONENT, oldReferencedComponent, referencedComponent));
			}
		}
		return referencedComponent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BasicComponent basicGetReferencedComponent() {
		return referencedComponent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setReferencedComponent(BasicComponent newReferencedComponent) {
		BasicComponent oldReferencedComponent = referencedComponent;
		referencedComponent = newReferencedComponent;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ResourcerelationsPackage.RESOURCE_OPTIONS__REFERENCED_COMPONENT, oldReferencedComponent, referencedComponent));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Specification> getSpecs() {
		if (specs == null) {
			specs = new EObjectResolvingEList<Specification>(Specification.class, this, ResourcerelationsPackage.RESOURCE_OPTIONS__SPECS);
		}
		return specs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ResourceOptions> getSubGroups() {
		if (subGroups == null) {
			subGroups = new EObjectResolvingEList<ResourceOptions>(ResourceOptions.class, this, ResourcerelationsPackage.RESOURCE_OPTIONS__SUB_GROUPS);
		}
		return subGroups;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ResourcerelationsPackage.RESOURCE_OPTIONS__RESOURCES:
				return getResources();
			case ResourcerelationsPackage.RESOURCE_OPTIONS__NAME:
				return getName();
			case ResourcerelationsPackage.RESOURCE_OPTIONS__MIN_ELEMENTS:
				return getMinElements();
			case ResourcerelationsPackage.RESOURCE_OPTIONS__MAX_ELEMENTS:
				return getMaxElements();
			case ResourcerelationsPackage.RESOURCE_OPTIONS__REFERENCED_COMPONENT:
				if (resolve) return getReferencedComponent();
				return basicGetReferencedComponent();
			case ResourcerelationsPackage.RESOURCE_OPTIONS__SPECS:
				return getSpecs();
			case ResourcerelationsPackage.RESOURCE_OPTIONS__SUB_GROUPS:
				return getSubGroups();
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
			case ResourcerelationsPackage.RESOURCE_OPTIONS__RESOURCES:
				getResources().clear();
				getResources().addAll((Collection<? extends Resource>)newValue);
				return;
			case ResourcerelationsPackage.RESOURCE_OPTIONS__NAME:
				setName((String)newValue);
				return;
			case ResourcerelationsPackage.RESOURCE_OPTIONS__MIN_ELEMENTS:
				setMinElements((String)newValue);
				return;
			case ResourcerelationsPackage.RESOURCE_OPTIONS__MAX_ELEMENTS:
				setMaxElements((String)newValue);
				return;
			case ResourcerelationsPackage.RESOURCE_OPTIONS__REFERENCED_COMPONENT:
				setReferencedComponent((BasicComponent)newValue);
				return;
			case ResourcerelationsPackage.RESOURCE_OPTIONS__SPECS:
				getSpecs().clear();
				getSpecs().addAll((Collection<? extends Specification>)newValue);
				return;
			case ResourcerelationsPackage.RESOURCE_OPTIONS__SUB_GROUPS:
				getSubGroups().clear();
				getSubGroups().addAll((Collection<? extends ResourceOptions>)newValue);
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
			case ResourcerelationsPackage.RESOURCE_OPTIONS__RESOURCES:
				getResources().clear();
				return;
			case ResourcerelationsPackage.RESOURCE_OPTIONS__NAME:
				setName(NAME_EDEFAULT);
				return;
			case ResourcerelationsPackage.RESOURCE_OPTIONS__MIN_ELEMENTS:
				setMinElements(MIN_ELEMENTS_EDEFAULT);
				return;
			case ResourcerelationsPackage.RESOURCE_OPTIONS__MAX_ELEMENTS:
				setMaxElements(MAX_ELEMENTS_EDEFAULT);
				return;
			case ResourcerelationsPackage.RESOURCE_OPTIONS__REFERENCED_COMPONENT:
				setReferencedComponent((BasicComponent)null);
				return;
			case ResourcerelationsPackage.RESOURCE_OPTIONS__SPECS:
				getSpecs().clear();
				return;
			case ResourcerelationsPackage.RESOURCE_OPTIONS__SUB_GROUPS:
				getSubGroups().clear();
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
			case ResourcerelationsPackage.RESOURCE_OPTIONS__RESOURCES:
				return resources != null && !resources.isEmpty();
			case ResourcerelationsPackage.RESOURCE_OPTIONS__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case ResourcerelationsPackage.RESOURCE_OPTIONS__MIN_ELEMENTS:
				return MIN_ELEMENTS_EDEFAULT == null ? minElements != null : !MIN_ELEMENTS_EDEFAULT.equals(minElements);
			case ResourcerelationsPackage.RESOURCE_OPTIONS__MAX_ELEMENTS:
				return MAX_ELEMENTS_EDEFAULT == null ? maxElements != null : !MAX_ELEMENTS_EDEFAULT.equals(maxElements);
			case ResourcerelationsPackage.RESOURCE_OPTIONS__REFERENCED_COMPONENT:
				return referencedComponent != null;
			case ResourcerelationsPackage.RESOURCE_OPTIONS__SPECS:
				return specs != null && !specs.isEmpty();
			case ResourcerelationsPackage.RESOURCE_OPTIONS__SUB_GROUPS:
				return subGroups != null && !subGroups.isEmpty();
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
		result.append(", minElements: ");
		result.append(minElements);
		result.append(", maxElements: ");
		result.append(maxElements);
		result.append(')');
		return result.toString();
	}

} //ResourceOptionsImpl
