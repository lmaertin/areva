/**
 */
package de.tubs.areva.resourcerelations.impl;

import java.util.Collection;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import de.tubs.areva.resourcerelations.Platform;
import de.tubs.areva.resourcerelations.Property;
import de.tubs.areva.resourcerelations.Resource;
import de.tubs.areva.resourcerelations.ResourceOptions;
import de.tubs.areva.resourcerelations.ResourcerelationsPackage;
import de.tubs.areva.resourcerelations.Rule;
import de.tubs.areva.resourcerelations.Specification;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Platform</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link de.tubs.areva.resourcerelations.impl.PlatformImpl#getOptions <em>Options</em>}</li>
 *   <li>{@link de.tubs.areva.resourcerelations.impl.PlatformImpl#getSpecs <em>Specs</em>}</li>
 *   <li>{@link de.tubs.areva.resourcerelations.impl.PlatformImpl#getProps <em>Props</em>}</li>
 *   <li>{@link de.tubs.areva.resourcerelations.impl.PlatformImpl#getResources <em>Resources</em>}</li>
 *   <li>{@link de.tubs.areva.resourcerelations.impl.PlatformImpl#getRules <em>Rules</em>}</li>
 * </ul>
 *
 * @generated
 */
public class PlatformImpl extends MinimalEObjectImpl.Container implements Platform {
	/**
	 * The cached value of the '{@link #getOptions() <em>Options</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOptions()
	 * @generated
	 * @ordered
	 */
	protected EList<ResourceOptions> options;

	/**
	 * The cached value of the '{@link #getSpecs() <em>Specs</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSpecs()
	 * @generated
	 * @ordered
	 */
	protected EList<Specification> specs;

	/**
	 * The cached value of the '{@link #getProps() <em>Props</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProps()
	 * @generated
	 * @ordered
	 */
	protected EList<Property> props;

	/**
	 * The cached value of the '{@link #getResources() <em>Resources</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResources()
	 * @generated
	 * @ordered
	 */
	protected EList<Resource> resources;

	/**
	 * The cached value of the '{@link #getRules() <em>Rules</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRules()
	 * @generated
	 * @ordered
	 */
	protected EList<Rule> rules;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PlatformImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ResourcerelationsPackage.Literals.PLATFORM;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ResourceOptions> getOptions() {
		if (options == null) {
			options = new EObjectContainmentEList<ResourceOptions>(ResourceOptions.class, this, ResourcerelationsPackage.PLATFORM__OPTIONS);
		}
		return options;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Specification> getSpecs() {
		if (specs == null) {
			specs = new EObjectContainmentEList<Specification>(Specification.class, this, ResourcerelationsPackage.PLATFORM__SPECS);
		}
		return specs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Property> getProps() {
		if (props == null) {
			props = new EObjectContainmentEList<Property>(Property.class, this, ResourcerelationsPackage.PLATFORM__PROPS);
		}
		return props;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Resource> getResources() {
		if (resources == null) {
			resources = new EObjectContainmentEList<Resource>(Resource.class, this, ResourcerelationsPackage.PLATFORM__RESOURCES);
		}
		return resources;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Rule> getRules() {
		if (rules == null) {
			rules = new EObjectContainmentEList<Rule>(Rule.class, this, ResourcerelationsPackage.PLATFORM__RULES);
		}
		return rules;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ResourcerelationsPackage.PLATFORM__OPTIONS:
				return ((InternalEList<?>)getOptions()).basicRemove(otherEnd, msgs);
			case ResourcerelationsPackage.PLATFORM__SPECS:
				return ((InternalEList<?>)getSpecs()).basicRemove(otherEnd, msgs);
			case ResourcerelationsPackage.PLATFORM__PROPS:
				return ((InternalEList<?>)getProps()).basicRemove(otherEnd, msgs);
			case ResourcerelationsPackage.PLATFORM__RESOURCES:
				return ((InternalEList<?>)getResources()).basicRemove(otherEnd, msgs);
			case ResourcerelationsPackage.PLATFORM__RULES:
				return ((InternalEList<?>)getRules()).basicRemove(otherEnd, msgs);
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
			case ResourcerelationsPackage.PLATFORM__OPTIONS:
				return getOptions();
			case ResourcerelationsPackage.PLATFORM__SPECS:
				return getSpecs();
			case ResourcerelationsPackage.PLATFORM__PROPS:
				return getProps();
			case ResourcerelationsPackage.PLATFORM__RESOURCES:
				return getResources();
			case ResourcerelationsPackage.PLATFORM__RULES:
				return getRules();
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
			case ResourcerelationsPackage.PLATFORM__OPTIONS:
				getOptions().clear();
				getOptions().addAll((Collection<? extends ResourceOptions>)newValue);
				return;
			case ResourcerelationsPackage.PLATFORM__SPECS:
				getSpecs().clear();
				getSpecs().addAll((Collection<? extends Specification>)newValue);
				return;
			case ResourcerelationsPackage.PLATFORM__PROPS:
				getProps().clear();
				getProps().addAll((Collection<? extends Property>)newValue);
				return;
			case ResourcerelationsPackage.PLATFORM__RESOURCES:
				getResources().clear();
				getResources().addAll((Collection<? extends Resource>)newValue);
				return;
			case ResourcerelationsPackage.PLATFORM__RULES:
				getRules().clear();
				getRules().addAll((Collection<? extends Rule>)newValue);
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
			case ResourcerelationsPackage.PLATFORM__OPTIONS:
				getOptions().clear();
				return;
			case ResourcerelationsPackage.PLATFORM__SPECS:
				getSpecs().clear();
				return;
			case ResourcerelationsPackage.PLATFORM__PROPS:
				getProps().clear();
				return;
			case ResourcerelationsPackage.PLATFORM__RESOURCES:
				getResources().clear();
				return;
			case ResourcerelationsPackage.PLATFORM__RULES:
				getRules().clear();
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
			case ResourcerelationsPackage.PLATFORM__OPTIONS:
				return options != null && !options.isEmpty();
			case ResourcerelationsPackage.PLATFORM__SPECS:
				return specs != null && !specs.isEmpty();
			case ResourcerelationsPackage.PLATFORM__PROPS:
				return props != null && !props.isEmpty();
			case ResourcerelationsPackage.PLATFORM__RESOURCES:
				return resources != null && !resources.isEmpty();
			case ResourcerelationsPackage.PLATFORM__RULES:
				return rules != null && !rules.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //PlatformImpl
