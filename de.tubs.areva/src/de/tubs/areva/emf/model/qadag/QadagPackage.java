/**
 */
package de.tubs.areva.emf.model.qadag;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see de.tubs.areva.emf.model.qadag.QadagFactory
 * @model kind="package"
 * @generated
 */
public interface QadagPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "qadag";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://com/areva/qadag/";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "de.tubs.areva.emf.model.qadag";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	QadagPackage eINSTANCE = de.tubs.areva.emf.model.qadag.impl.QadagPackageImpl.init();

	/**
	 * The meta object id for the '{@link de.tubs.areva.emf.model.qadag.impl.QADAGImpl <em>QADAG</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.tubs.areva.emf.model.qadag.impl.QADAGImpl
	 * @see de.tubs.areva.emf.model.qadag.impl.QadagPackageImpl#getQADAG()
	 * @generated
	 */
	int QADAG = 0;

	/**
	 * The feature id for the '<em><b>Root</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QADAG__ROOT = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QADAG__NAME = 1;

	/**
	 * The number of structural features of the '<em>QADAG</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QADAG_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>QADAG</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QADAG_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link de.tubs.areva.emf.model.qadag.impl.NodeImpl <em>Node</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.tubs.areva.emf.model.qadag.impl.NodeImpl
	 * @see de.tubs.areva.emf.model.qadag.impl.QadagPackageImpl#getNode()
	 * @generated
	 */
	int NODE = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE__NAME = 0;

	/**
	 * The feature id for the '<em><b>Nodes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE__NODES = 1;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE__VALUE = 2;

	/**
	 * The feature id for the '<em><b>Lower Limit</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE__LOWER_LIMIT = 3;

	/**
	 * The feature id for the '<em><b>Unit</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE__UNIT = 4;

	/**
	 * The feature id for the '<em><b>Weight</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE__WEIGHT = 5;

	/**
	 * The number of structural features of the '<em>Node</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE_FEATURE_COUNT = 6;

	/**
	 * The number of operations of the '<em>Node</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link de.tubs.areva.emf.model.qadag.impl.LeafNodeImpl <em>Leaf Node</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.tubs.areva.emf.model.qadag.impl.LeafNodeImpl
	 * @see de.tubs.areva.emf.model.qadag.impl.QadagPackageImpl#getLeafNode()
	 * @generated
	 */
	int LEAF_NODE = 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEAF_NODE__NAME = NODE__NAME;

	/**
	 * The feature id for the '<em><b>Nodes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEAF_NODE__NODES = NODE__NODES;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEAF_NODE__VALUE = NODE__VALUE;

	/**
	 * The feature id for the '<em><b>Lower Limit</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEAF_NODE__LOWER_LIMIT = NODE__LOWER_LIMIT;

	/**
	 * The feature id for the '<em><b>Unit</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEAF_NODE__UNIT = NODE__UNIT;

	/**
	 * The feature id for the '<em><b>Weight</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEAF_NODE__WEIGHT = NODE__WEIGHT;

	/**
	 * The feature id for the '<em><b>Min Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEAF_NODE__MIN_VALUE = NODE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Max Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEAF_NODE__MAX_VALUE = NODE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Leaf Node</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEAF_NODE_FEATURE_COUNT = NODE_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Leaf Node</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEAF_NODE_OPERATION_COUNT = NODE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link de.tubs.areva.emf.model.qadag.impl.CompositeNodeImpl <em>Composite Node</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.tubs.areva.emf.model.qadag.impl.CompositeNodeImpl
	 * @see de.tubs.areva.emf.model.qadag.impl.QadagPackageImpl#getCompositeNode()
	 * @generated
	 */
	int COMPOSITE_NODE = 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_NODE__NAME = NODE__NAME;

	/**
	 * The feature id for the '<em><b>Nodes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_NODE__NODES = NODE__NODES;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_NODE__VALUE = NODE__VALUE;

	/**
	 * The feature id for the '<em><b>Lower Limit</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_NODE__LOWER_LIMIT = NODE__LOWER_LIMIT;

	/**
	 * The feature id for the '<em><b>Unit</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_NODE__UNIT = NODE__UNIT;

	/**
	 * The feature id for the '<em><b>Weight</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_NODE__WEIGHT = NODE__WEIGHT;

	/**
	 * The number of structural features of the '<em>Composite Node</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_NODE_FEATURE_COUNT = NODE_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Composite Node</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_NODE_OPERATION_COUNT = NODE_OPERATION_COUNT + 0;

	/**
	 * Returns the meta object for class '{@link de.tubs.areva.emf.model.qadag.QADAG <em>QADAG</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>QADAG</em>'.
	 * @see de.tubs.areva.emf.model.qadag.QADAG
	 * @generated
	 */
	EClass getQADAG();

	/**
	 * Returns the meta object for the containment reference '{@link de.tubs.areva.emf.model.qadag.QADAG#getRoot <em>Root</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Root</em>'.
	 * @see de.tubs.areva.emf.model.qadag.QADAG#getRoot()
	 * @see #getQADAG()
	 * @generated
	 */
	EReference getQADAG_Root();

	/**
	 * Returns the meta object for the attribute '{@link de.tubs.areva.emf.model.qadag.QADAG#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see de.tubs.areva.emf.model.qadag.QADAG#getName()
	 * @see #getQADAG()
	 * @generated
	 */
	EAttribute getQADAG_Name();

	/**
	 * Returns the meta object for class '{@link de.tubs.areva.emf.model.qadag.Node <em>Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Node</em>'.
	 * @see de.tubs.areva.emf.model.qadag.Node
	 * @generated
	 */
	EClass getNode();

	/**
	 * Returns the meta object for the containment reference list '{@link de.tubs.areva.emf.model.qadag.Node#getNodes <em>Nodes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Nodes</em>'.
	 * @see de.tubs.areva.emf.model.qadag.Node#getNodes()
	 * @see #getNode()
	 * @generated
	 */
	EReference getNode_Nodes();

	/**
	 * Returns the meta object for the attribute '{@link de.tubs.areva.emf.model.qadag.Node#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see de.tubs.areva.emf.model.qadag.Node#getValue()
	 * @see #getNode()
	 * @generated
	 */
	EAttribute getNode_Value();

	/**
	 * Returns the meta object for the attribute '{@link de.tubs.areva.emf.model.qadag.Node#getLowerLimit <em>Lower Limit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Lower Limit</em>'.
	 * @see de.tubs.areva.emf.model.qadag.Node#getLowerLimit()
	 * @see #getNode()
	 * @generated
	 */
	EAttribute getNode_LowerLimit();

	/**
	 * Returns the meta object for the attribute '{@link de.tubs.areva.emf.model.qadag.Node#getUnit <em>Unit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Unit</em>'.
	 * @see de.tubs.areva.emf.model.qadag.Node#getUnit()
	 * @see #getNode()
	 * @generated
	 */
	EAttribute getNode_Unit();

	/**
	 * Returns the meta object for the attribute '{@link de.tubs.areva.emf.model.qadag.Node#getWeight <em>Weight</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Weight</em>'.
	 * @see de.tubs.areva.emf.model.qadag.Node#getWeight()
	 * @see #getNode()
	 * @generated
	 */
	EAttribute getNode_Weight();

	/**
	 * Returns the meta object for class '{@link de.tubs.areva.emf.model.qadag.LeafNode <em>Leaf Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Leaf Node</em>'.
	 * @see de.tubs.areva.emf.model.qadag.LeafNode
	 * @generated
	 */
	EClass getLeafNode();

	/**
	 * Returns the meta object for the attribute '{@link de.tubs.areva.emf.model.qadag.LeafNode#getMinValue <em>Min Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Min Value</em>'.
	 * @see de.tubs.areva.emf.model.qadag.LeafNode#getMinValue()
	 * @see #getLeafNode()
	 * @generated
	 */
	EAttribute getLeafNode_MinValue();

	/**
	 * Returns the meta object for the attribute '{@link de.tubs.areva.emf.model.qadag.LeafNode#getMaxValue <em>Max Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Max Value</em>'.
	 * @see de.tubs.areva.emf.model.qadag.LeafNode#getMaxValue()
	 * @see #getLeafNode()
	 * @generated
	 */
	EAttribute getLeafNode_MaxValue();

	/**
	 * Returns the meta object for class '{@link de.tubs.areva.emf.model.qadag.CompositeNode <em>Composite Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Composite Node</em>'.
	 * @see de.tubs.areva.emf.model.qadag.CompositeNode
	 * @generated
	 */
	EClass getCompositeNode();

	/**
	 * Returns the meta object for the attribute '{@link de.tubs.areva.emf.model.qadag.Node#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see de.tubs.areva.emf.model.qadag.Node#getName()
	 * @see #getNode()
	 * @generated
	 */
	EAttribute getNode_Name();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	QadagFactory getQadagFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link de.tubs.areva.emf.model.qadag.impl.QADAGImpl <em>QADAG</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.tubs.areva.emf.model.qadag.impl.QADAGImpl
		 * @see de.tubs.areva.emf.model.qadag.impl.QadagPackageImpl#getQADAG()
		 * @generated
		 */
		EClass QADAG = eINSTANCE.getQADAG();

		/**
		 * The meta object literal for the '<em><b>Root</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference QADAG__ROOT = eINSTANCE.getQADAG_Root();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute QADAG__NAME = eINSTANCE.getQADAG_Name();

		/**
		 * The meta object literal for the '{@link de.tubs.areva.emf.model.qadag.impl.NodeImpl <em>Node</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.tubs.areva.emf.model.qadag.impl.NodeImpl
		 * @see de.tubs.areva.emf.model.qadag.impl.QadagPackageImpl#getNode()
		 * @generated
		 */
		EClass NODE = eINSTANCE.getNode();

		/**
		 * The meta object literal for the '<em><b>Nodes</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference NODE__NODES = eINSTANCE.getNode_Nodes();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NODE__VALUE = eINSTANCE.getNode_Value();

		/**
		 * The meta object literal for the '<em><b>Lower Limit</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NODE__LOWER_LIMIT = eINSTANCE.getNode_LowerLimit();

		/**
		 * The meta object literal for the '<em><b>Unit</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NODE__UNIT = eINSTANCE.getNode_Unit();

		/**
		 * The meta object literal for the '<em><b>Weight</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NODE__WEIGHT = eINSTANCE.getNode_Weight();

		/**
		 * The meta object literal for the '{@link de.tubs.areva.emf.model.qadag.impl.LeafNodeImpl <em>Leaf Node</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.tubs.areva.emf.model.qadag.impl.LeafNodeImpl
		 * @see de.tubs.areva.emf.model.qadag.impl.QadagPackageImpl#getLeafNode()
		 * @generated
		 */
		EClass LEAF_NODE = eINSTANCE.getLeafNode();

		/**
		 * The meta object literal for the '<em><b>Min Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LEAF_NODE__MIN_VALUE = eINSTANCE.getLeafNode_MinValue();

		/**
		 * The meta object literal for the '<em><b>Max Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LEAF_NODE__MAX_VALUE = eINSTANCE.getLeafNode_MaxValue();

		/**
		 * The meta object literal for the '{@link de.tubs.areva.emf.model.qadag.impl.CompositeNodeImpl <em>Composite Node</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.tubs.areva.emf.model.qadag.impl.CompositeNodeImpl
		 * @see de.tubs.areva.emf.model.qadag.impl.QadagPackageImpl#getCompositeNode()
		 * @generated
		 */
		EClass COMPOSITE_NODE = eINSTANCE.getCompositeNode();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NODE__NAME = eINSTANCE.getNode_Name();

	}

} //QadagPackage
