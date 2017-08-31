/**
 */
package de.tubs.areva.emf.model.opmode.impl;

import de.tubs.areva.emf.model.opmode.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class OpmodeFactoryImpl extends EFactoryImpl implements OpmodeFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static OpmodeFactory init() {
		try {
			OpmodeFactory theOpmodeFactory = (OpmodeFactory)EPackage.Registry.INSTANCE.getEFactory(OpmodePackage.eNS_URI);
			if (theOpmodeFactory != null) {
				return theOpmodeFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new OpmodeFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OpmodeFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case OpmodePackage.OPERATING_MODE_SELECTION: return createOperatingModeSelection();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OperatingModeSelection createOperatingModeSelection() {
		OperatingModeSelectionImpl operatingModeSelection = new OperatingModeSelectionImpl();
		return operatingModeSelection;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OpmodePackage getOpmodePackage() {
		return (OpmodePackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static OpmodePackage getPackage() {
		return OpmodePackage.eINSTANCE;
	}

} //OpmodeFactoryImpl
