/**
 */
package de.tubs.areva.emf.model.opmode;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see de.tubs.areva.emf.model.opmode.OpmodePackage
 * @generated
 */
public interface OpmodeFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	OpmodeFactory eINSTANCE = de.tubs.areva.emf.model.opmode.impl.OpmodeFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Operating Mode Selection</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Operating Mode Selection</em>'.
	 * @generated
	 */
	OperatingModeSelection createOperatingModeSelection();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	OpmodePackage getOpmodePackage();

} //OpmodeFactory
