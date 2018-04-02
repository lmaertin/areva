/**
 */
package de.tubs.areva.emf.model.qadag;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see de.tubs.areva.emf.model.qadag.QadagPackage
 * @generated
 */
public interface QadagFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	QadagFactory eINSTANCE = de.tubs.areva.emf.model.qadag.impl.QadagFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>QADAG</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>QADAG</em>'.
	 * @generated
	 */
	QADAG createQADAG();

	/**
	 * Returns a new object of class '<em>Leaf Node</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Leaf Node</em>'.
	 * @generated
	 */
	LeafNode createLeafNode();

	/**
	 * Returns a new object of class '<em>Composite Node</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Composite Node</em>'.
	 * @generated
	 */
	CompositeNode createCompositeNode();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	QadagPackage getQadagPackage();

} //QadagFactory
