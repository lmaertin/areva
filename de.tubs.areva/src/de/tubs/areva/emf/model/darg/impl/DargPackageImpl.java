/**
 */
package de.tubs.areva.emf.model.darg.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.emf.ecore.xml.type.XMLTypePackage;
import org.palladiosimulator.pcm.repository.RepositoryPackage;
import de.tubs.areva.emf.model.darg.Architecture;
import de.tubs.areva.emf.model.darg.DargFactory;
import de.tubs.areva.emf.model.darg.DargPackage;
import de.tubs.areva.emf.model.darg.QualityAssignment;
import de.tubs.areva.emf.model.opmode.OpmodePackage;
import de.tubs.areva.emf.model.opmode.impl.OpmodePackageImpl;
import de.tubs.areva.emf.model.qadag.QadagPackage;
import de.tubs.areva.emf.model.qadag.impl.QadagPackageImpl;
import de.tubs.areva.resourcerelations.ResourcerelationsPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class DargPackageImpl extends EPackageImpl implements DargPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass argEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass architectureEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass qualityAssignmentEClass = null;

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
	 * @see de.tubs.areva.emf.model.darg.DargPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private DargPackageImpl() {
		super(eNS_URI, DargFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link DargPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static DargPackage init() {
		if (isInited) return (DargPackage)EPackage.Registry.INSTANCE.getEPackage(DargPackage.eNS_URI);

		// Obtain or create and register package
		DargPackageImpl theDargPackage = (DargPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof DargPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new DargPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		ResourcerelationsPackage.eINSTANCE.eClass();
		XMLTypePackage.eINSTANCE.eClass();

		// Obtain or create and register interdependencies
		OpmodePackageImpl theOpmodePackage = (OpmodePackageImpl)(EPackage.Registry.INSTANCE.getEPackage(OpmodePackage.eNS_URI) instanceof OpmodePackageImpl ? EPackage.Registry.INSTANCE.getEPackage(OpmodePackage.eNS_URI) : OpmodePackage.eINSTANCE);
		QadagPackageImpl theQadagPackage = (QadagPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(QadagPackage.eNS_URI) instanceof QadagPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(QadagPackage.eNS_URI) : QadagPackage.eINSTANCE);

		// Create package meta-data objects
		theDargPackage.createPackageContents();
		theOpmodePackage.createPackageContents();
		theQadagPackage.createPackageContents();

		// Initialize created meta-data
		theDargPackage.initializePackageContents();
		theOpmodePackage.initializePackageContents();
		theQadagPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theDargPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(DargPackage.eNS_URI, theDargPackage);
		return theDargPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getARG() {
		return argEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getARG_Name() {
		return (EAttribute)argEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getARG_Architectures() {
		return (EReference)argEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getARG_Qadag() {
		return (EReference)argEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getARG_Threshold() {
		return (EAttribute)argEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getArchitecture() {
		return architectureEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getArchitecture_Quality() {
		return (EAttribute)architectureEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getArchitecture_Qadag() {
		return (EReference)architectureEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getArchitecture_BoundResourceOptions() {
		return (EReference)architectureEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getArchitecture_Qualityassignments() {
		return (EReference)architectureEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getArchitecture_Optimal() {
		return (EAttribute)architectureEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getArchitecture_Hidden() {
		return (EAttribute)architectureEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getArchitecture_Marked() {
		return (EAttribute)architectureEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getArchitecture_Id() {
		return (EAttribute)architectureEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getArchitecture_RelatedArchitectures() {
		return (EReference)architectureEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getArchitecture_BoundResources() {
		return (EReference)architectureEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getArchitecture_BoundBasicComponents() {
		return (EReference)architectureEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getArchitecture_BoundResourcesCount() {
		return (EAttribute)architectureEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getArchitecture_RawQualityAssignments() {
		return (EReference)architectureEClass.getEStructuralFeatures().get(12);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getQualityAssignment() {
		return qualityAssignmentEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getQualityAssignment_Attribute() {
		return (EAttribute)qualityAssignmentEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getQualityAssignment_Value() {
		return (EAttribute)qualityAssignmentEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DargFactory getDargFactory() {
		return (DargFactory)getEFactoryInstance();
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
		argEClass = createEClass(ARG);
		createEAttribute(argEClass, ARG__NAME);
		createEReference(argEClass, ARG__ARCHITECTURES);
		createEReference(argEClass, ARG__QADAG);
		createEAttribute(argEClass, ARG__THRESHOLD);

		architectureEClass = createEClass(ARCHITECTURE);
		createEAttribute(architectureEClass, ARCHITECTURE__QUALITY);
		createEReference(architectureEClass, ARCHITECTURE__QADAG);
		createEReference(architectureEClass, ARCHITECTURE__BOUND_RESOURCE_OPTIONS);
		createEReference(architectureEClass, ARCHITECTURE__QUALITYASSIGNMENTS);
		createEAttribute(architectureEClass, ARCHITECTURE__OPTIMAL);
		createEAttribute(architectureEClass, ARCHITECTURE__HIDDEN);
		createEAttribute(architectureEClass, ARCHITECTURE__MARKED);
		createEAttribute(architectureEClass, ARCHITECTURE__ID);
		createEReference(architectureEClass, ARCHITECTURE__RELATED_ARCHITECTURES);
		createEReference(architectureEClass, ARCHITECTURE__BOUND_RESOURCES);
		createEReference(architectureEClass, ARCHITECTURE__BOUND_BASIC_COMPONENTS);
		createEAttribute(architectureEClass, ARCHITECTURE__BOUND_RESOURCES_COUNT);
		createEReference(architectureEClass, ARCHITECTURE__RAW_QUALITY_ASSIGNMENTS);

		qualityAssignmentEClass = createEClass(QUALITY_ASSIGNMENT);
		createEAttribute(qualityAssignmentEClass, QUALITY_ASSIGNMENT__ATTRIBUTE);
		createEAttribute(qualityAssignmentEClass, QUALITY_ASSIGNMENT__VALUE);
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

		// Obtain other dependent packages
		QadagPackage theQadagPackage = (QadagPackage)EPackage.Registry.INSTANCE.getEPackage(QadagPackage.eNS_URI);
		XMLTypePackage theXMLTypePackage = (XMLTypePackage)EPackage.Registry.INSTANCE.getEPackage(XMLTypePackage.eNS_URI);
		ResourcerelationsPackage theResourcerelationsPackage = (ResourcerelationsPackage)EPackage.Registry.INSTANCE.getEPackage(ResourcerelationsPackage.eNS_URI);
		RepositoryPackage theRepositoryPackage = (RepositoryPackage)EPackage.Registry.INSTANCE.getEPackage(RepositoryPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes

		// Initialize classes, features, and operations; add parameters
		initEClass(argEClass, de.tubs.areva.emf.model.darg.ARG.class, "ARG", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getARG_Name(), ecorePackage.getEString(), "name", null, 0, 1, de.tubs.areva.emf.model.darg.ARG.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getARG_Architectures(), this.getArchitecture(), null, "architectures", null, 0, -1, de.tubs.areva.emf.model.darg.ARG.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getARG_Qadag(), theQadagPackage.getQADAG(), null, "qadag", null, 0, 1, de.tubs.areva.emf.model.darg.ARG.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getARG_Threshold(), theXMLTypePackage.getInt(), "threshold", null, 0, 1, de.tubs.areva.emf.model.darg.ARG.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(architectureEClass, Architecture.class, "Architecture", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getArchitecture_Quality(), theXMLTypePackage.getDouble(), "quality", null, 0, 1, Architecture.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getArchitecture_Qadag(), theQadagPackage.getQADAG(), null, "qadag", null, 0, 1, Architecture.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getArchitecture_BoundResourceOptions(), theResourcerelationsPackage.getResourceOptions(), null, "boundResourceOptions", null, 0, -1, Architecture.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getArchitecture_Qualityassignments(), this.getQualityAssignment(), null, "qualityassignments", null, 0, -1, Architecture.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getArchitecture_Optimal(), theXMLTypePackage.getBoolean(), "optimal", null, 0, 1, Architecture.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getArchitecture_Hidden(), theXMLTypePackage.getBoolean(), "hidden", null, 0, 1, Architecture.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getArchitecture_Marked(), theXMLTypePackage.getFloat(), "marked", null, 0, 1, Architecture.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getArchitecture_Id(), theXMLTypePackage.getString(), "id", null, 0, 1, Architecture.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getArchitecture_RelatedArchitectures(), this.getArchitecture(), null, "relatedArchitectures", null, 0, -1, Architecture.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getArchitecture_BoundResources(), theResourcerelationsPackage.getResource(), null, "boundResources", null, 0, -1, Architecture.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getArchitecture_BoundBasicComponents(), theRepositoryPackage.getBasicComponent(), null, "boundBasicComponents", null, 0, -1, Architecture.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getArchitecture_BoundResourcesCount(), theXMLTypePackage.getInt(), "boundResourcesCount", null, 0, 1, Architecture.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getArchitecture_RawQualityAssignments(), this.getQualityAssignment(), null, "rawQualityAssignments", null, 0, -1, Architecture.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(qualityAssignmentEClass, QualityAssignment.class, "QualityAssignment", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getQualityAssignment_Attribute(), ecorePackage.getEString(), "attribute", null, 0, 1, QualityAssignment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getQualityAssignment_Value(), theXMLTypePackage.getDouble(), "value", null, 0, 1, QualityAssignment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Create resource
		createResource(eNS_URI);
	}

} //DargPackageImpl
