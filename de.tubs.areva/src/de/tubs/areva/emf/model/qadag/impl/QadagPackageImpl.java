/**
 */
package de.tubs.areva.emf.model.qadag.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import de.tubs.areva.emf.model.darg.DargPackage;
import de.tubs.areva.emf.model.darg.impl.DargPackageImpl;
import de.tubs.areva.emf.model.opmode.OpmodePackage;
import de.tubs.areva.emf.model.opmode.impl.OpmodePackageImpl;
import de.tubs.areva.emf.model.qadag.CompositeNode;
import de.tubs.areva.emf.model.qadag.LeafNode;
import de.tubs.areva.emf.model.qadag.Node;
import de.tubs.areva.emf.model.qadag.QadagFactory;
import de.tubs.areva.emf.model.qadag.QadagPackage;
import de.tubs.areva.resourcerelations.ResourcerelationsPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class QadagPackageImpl extends EPackageImpl implements QadagPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass qadagEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass nodeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass leafNodeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass compositeNodeEClass = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see de.tubs.areva.emf.model.qadag.QadagPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private QadagPackageImpl() {
		super(eNS_URI, QadagFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link QadagPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static QadagPackage init() {
		if (isInited) return (QadagPackage)EPackage.Registry.INSTANCE.getEPackage(QadagPackage.eNS_URI);

		// Obtain or create and register package
		QadagPackageImpl theQadagPackage = (QadagPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof QadagPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new QadagPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		ResourcerelationsPackage.eINSTANCE.eClass();

		// Obtain or create and register interdependencies
		OpmodePackageImpl theOpmodePackage = (OpmodePackageImpl)(EPackage.Registry.INSTANCE.getEPackage(OpmodePackage.eNS_URI) instanceof OpmodePackageImpl ? EPackage.Registry.INSTANCE.getEPackage(OpmodePackage.eNS_URI) : OpmodePackage.eINSTANCE);
		DargPackageImpl theDargPackage = (DargPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(DargPackage.eNS_URI) instanceof DargPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(DargPackage.eNS_URI) : DargPackage.eINSTANCE);

		// Create package meta-data objects
		theQadagPackage.createPackageContents();
		theOpmodePackage.createPackageContents();
		theDargPackage.createPackageContents();

		// Initialize created meta-data
		theQadagPackage.initializePackageContents();
		theOpmodePackage.initializePackageContents();
		theDargPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theQadagPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(QadagPackage.eNS_URI, theQadagPackage);
		return theQadagPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getQADAG() {
		return qadagEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getQADAG_Root() {
		return (EReference)qadagEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getQADAG_Name() {
		return (EAttribute)qadagEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getNode() {
		return nodeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getNode_Nodes() {
		return (EReference)nodeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getNode_Value() {
		return (EAttribute)nodeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getNode_LowerLimit() {
		return (EAttribute)nodeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getNode_Unit() {
		return (EAttribute)nodeEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getNode_Weight() {
		return (EAttribute)nodeEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLeafNode() {
		return leafNodeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLeafNode_MinValue() {
		return (EAttribute)leafNodeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLeafNode_MaxValue() {
		return (EAttribute)leafNodeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCompositeNode() {
		return compositeNodeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getNode_Name() {
		return (EAttribute)nodeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public QadagFactory getQadagFactory() {
		return (QadagFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		qadagEClass = createEClass(QADAG);
		createEReference(qadagEClass, QADAG__ROOT);
		createEAttribute(qadagEClass, QADAG__NAME);

		nodeEClass = createEClass(NODE);
		createEAttribute(nodeEClass, NODE__NAME);
		createEReference(nodeEClass, NODE__NODES);
		createEAttribute(nodeEClass, NODE__VALUE);
		createEAttribute(nodeEClass, NODE__LOWER_LIMIT);
		createEAttribute(nodeEClass, NODE__UNIT);
		createEAttribute(nodeEClass, NODE__WEIGHT);

		leafNodeEClass = createEClass(LEAF_NODE);
		createEAttribute(leafNodeEClass, LEAF_NODE__MIN_VALUE);
		createEAttribute(leafNodeEClass, LEAF_NODE__MAX_VALUE);

		compositeNodeEClass = createEClass(COMPOSITE_NODE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		leafNodeEClass.getESuperTypes().add(this.getNode());
		compositeNodeEClass.getESuperTypes().add(this.getNode());

		// Initialize classes, features, and operations; add parameters
		initEClass(qadagEClass, de.tubs.areva.emf.model.qadag.QADAG.class, "QADAG", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getQADAG_Root(), this.getNode(), null, "root", null, 0, 1, de.tubs.areva.emf.model.qadag.QADAG.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getQADAG_Name(), ecorePackage.getEString(), "name", null, 0, 1, de.tubs.areva.emf.model.qadag.QADAG.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(nodeEClass, Node.class, "Node", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getNode_Name(), ecorePackage.getEString(), "name", null, 0, 1, Node.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getNode_Nodes(), this.getNode(), null, "nodes", null, 0, -1, Node.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getNode_Value(), ecorePackage.getEDouble(), "value", null, 0, 1, Node.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getNode_LowerLimit(), ecorePackage.getEDouble(), "lowerLimit", null, 0, 1, Node.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getNode_Unit(), ecorePackage.getEString(), "unit", null, 0, 1, Node.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getNode_Weight(), ecorePackage.getEDouble(), "weight", null, 0, 1, Node.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(leafNodeEClass, LeafNode.class, "LeafNode", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getLeafNode_MinValue(), ecorePackage.getEDouble(), "minValue", null, 0, 1, LeafNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLeafNode_MaxValue(), ecorePackage.getEDouble(), "maxValue", null, 0, 1, LeafNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(compositeNodeEClass, CompositeNode.class, "CompositeNode", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		// Create resource
		createResource(eNS_URI);
	}

} //QadagPackageImpl
