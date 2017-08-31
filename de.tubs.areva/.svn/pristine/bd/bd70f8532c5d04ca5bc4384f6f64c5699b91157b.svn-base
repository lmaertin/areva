/**
 */
package de.tubs.areva.emf.model.qadag.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import de.tubs.areva.emf.model.qadag.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class QadagFactoryImpl extends EFactoryImpl implements QadagFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static QadagFactory init() {
		try {
			QadagFactory theQadagFactory = (QadagFactory)EPackage.Registry.INSTANCE.getEFactory(QadagPackage.eNS_URI);
			if (theQadagFactory != null) {
				return theQadagFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new QadagFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public QadagFactoryImpl() {
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
			case QadagPackage.QADAG: return createQADAG();
			case QadagPackage.LEAF_NODE: return createLeafNode();
			case QadagPackage.COMPOSITE_NODE: return createCompositeNode();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public QADAG createQADAG() {
		QADAGImpl qadag = new QADAGImpl();
		return qadag;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LeafNode createLeafNode() {
		LeafNodeImpl leafNode = new LeafNodeImpl();
		return leafNode;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CompositeNode createCompositeNode() {
		CompositeNodeImpl compositeNode = new CompositeNodeImpl();
		return compositeNode;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public QadagPackage getQadagPackage() {
		return (QadagPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static QadagPackage getPackage() {
		return QadagPackage.eINSTANCE;
	}

} //QadagFactoryImpl
