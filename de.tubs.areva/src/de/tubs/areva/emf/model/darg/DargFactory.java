/**
 */
package de.tubs.areva.emf.model.darg;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see de.tubs.areva.emf.model.darg.DargPackage
 * @generated
 */
public interface DargFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	DargFactory eINSTANCE = de.tubs.areva.emf.model.darg.impl.DargFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>ARG</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>ARG</em>'.
	 * @generated
	 */
	ARG createARG();

	/**
	 * Returns a new object of class '<em>Architecture</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Architecture</em>'.
	 * @generated
	 */
	Architecture createArchitecture();

	/**
	 * Returns a new object of class '<em>Quality Assignment</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Quality Assignment</em>'.
	 * @generated
	 */
	QualityAssignment createQualityAssignment();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	DargPackage getDargPackage();

} //DargFactory
