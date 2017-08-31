/**
 */
package de.tubs.areva.emf.model.darg.impl;

import de.tubs.areva.emf.model.darg.ARG;
import de.tubs.areva.emf.model.darg.Architecture;
import de.tubs.areva.emf.model.darg.DargPackage;

import de.tubs.areva.emf.model.qadag.QADAG;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>ARG</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link de.tubs.areva.emf.model.darg.impl.ARGImpl#getName <em>Name</em>}</li>
 *   <li>{@link de.tubs.areva.emf.model.darg.impl.ARGImpl#getArchitectures <em>Architectures</em>}</li>
 *   <li>{@link de.tubs.areva.emf.model.darg.impl.ARGImpl#getQadag <em>Qadag</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ARGImpl extends MinimalEObjectImpl.Container implements ARG {
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
	 * The cached value of the '{@link #getArchitectures() <em>Architectures</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getArchitectures()
	 * @generated
	 * @ordered
	 */
	protected EList<Architecture> architectures;

	/**
	 * The cached value of the '{@link #getQadag() <em>Qadag</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getQadag()
	 * @generated
	 * @ordered
	 */
	protected QADAG qadag;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ARGImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DargPackage.Literals.ARG;
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
			eNotify(new ENotificationImpl(this, Notification.SET, DargPackage.ARG__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Architecture> getArchitectures() {
		if (architectures == null) {
			architectures = new EObjectContainmentEList<Architecture>(Architecture.class, this, DargPackage.ARG__ARCHITECTURES);
		}
		return architectures;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public QADAG getQadag() {
		if (qadag != null && qadag.eIsProxy()) {
			InternalEObject oldQadag = (InternalEObject)qadag;
			qadag = (QADAG)eResolveProxy(oldQadag);
			if (qadag != oldQadag) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, DargPackage.ARG__QADAG, oldQadag, qadag));
			}
		}
		return qadag;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public QADAG basicGetQadag() {
		return qadag;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setQadag(QADAG newQadag) {
		QADAG oldQadag = qadag;
		qadag = newQadag;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DargPackage.ARG__QADAG, oldQadag, qadag));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case DargPackage.ARG__ARCHITECTURES:
				return ((InternalEList<?>)getArchitectures()).basicRemove(otherEnd, msgs);
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
			case DargPackage.ARG__NAME:
				return getName();
			case DargPackage.ARG__ARCHITECTURES:
				return getArchitectures();
			case DargPackage.ARG__QADAG:
				if (resolve) return getQadag();
				return basicGetQadag();
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
			case DargPackage.ARG__NAME:
				setName((String)newValue);
				return;
			case DargPackage.ARG__ARCHITECTURES:
				getArchitectures().clear();
				getArchitectures().addAll((Collection<? extends Architecture>)newValue);
				return;
			case DargPackage.ARG__QADAG:
				setQadag((QADAG)newValue);
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
			case DargPackage.ARG__NAME:
				setName(NAME_EDEFAULT);
				return;
			case DargPackage.ARG__ARCHITECTURES:
				getArchitectures().clear();
				return;
			case DargPackage.ARG__QADAG:
				setQadag((QADAG)null);
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
			case DargPackage.ARG__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case DargPackage.ARG__ARCHITECTURES:
				return architectures != null && !architectures.isEmpty();
			case DargPackage.ARG__QADAG:
				return qadag != null;
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

} //ARGImpl
