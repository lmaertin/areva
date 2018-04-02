/**
 */
package de.tubs.forjahn.bachelor.model.cps.impl;

import de.tubs.forjahn.bachelor.model.cps.*;

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
public class CpsFactoryImpl extends EFactoryImpl implements CpsFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static CpsFactory init() {
		try {
			CpsFactory theCpsFactory = (CpsFactory)EPackage.Registry.INSTANCE.getEFactory(CpsPackage.eNS_URI);
			if (theCpsFactory != null) {
				return theCpsFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new CpsFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CpsFactoryImpl() {
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
			case CpsPackage.CALCULATION_PARAMETER_SELECTION: return createCalculationParameterSelection();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CalculationParameterSelection createCalculationParameterSelection() {
		CalculationParameterSelectionImpl calculationParameterSelection = new CalculationParameterSelectionImpl();
		return calculationParameterSelection;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CpsPackage getCpsPackage() {
		return (CpsPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static CpsPackage getPackage() {
		return CpsPackage.eINSTANCE;
	}

} //CpsFactoryImpl
