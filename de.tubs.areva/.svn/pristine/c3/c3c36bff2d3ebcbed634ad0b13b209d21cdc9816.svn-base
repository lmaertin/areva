/**
 */
package de.tubs.areva.emf.model.opmode.impl;

import de.tubs.areva.emf.model.darg.DargPackage;
import de.tubs.areva.emf.model.darg.impl.DargPackageImpl;
import de.tubs.areva.emf.model.opmode.OperatingModeSelection;
import de.tubs.areva.emf.model.opmode.OpmodeFactory;
import de.tubs.areva.emf.model.opmode.OpmodePackage;

import de.tubs.areva.emf.model.qadag.QadagPackage;
import de.tubs.areva.emf.model.qadag.impl.QadagPackageImpl;
import de.tubs.areva.resourcerelations.ResourcerelationsPackage;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class OpmodePackageImpl extends EPackageImpl implements OpmodePackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass operatingModeSelectionEClass = null;

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
	 * @see de.tubs.areva.emf.model.opmode.OpmodePackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private OpmodePackageImpl() {
		super(eNS_URI, OpmodeFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link OpmodePackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static OpmodePackage init() {
		if (isInited) return (OpmodePackage)EPackage.Registry.INSTANCE.getEPackage(OpmodePackage.eNS_URI);

		// Obtain or create and register package
		OpmodePackageImpl theOpmodePackage = (OpmodePackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof OpmodePackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new OpmodePackageImpl());

		isInited = true;

		// Initialize simple dependencies
		ResourcerelationsPackage.eINSTANCE.eClass();

		// Obtain or create and register interdependencies
		DargPackageImpl theDargPackage = (DargPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(DargPackage.eNS_URI) instanceof DargPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(DargPackage.eNS_URI) : DargPackage.eINSTANCE);
		QadagPackageImpl theQadagPackage = (QadagPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(QadagPackage.eNS_URI) instanceof QadagPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(QadagPackage.eNS_URI) : QadagPackage.eINSTANCE);

		// Create package meta-data objects
		theOpmodePackage.createPackageContents();
		theDargPackage.createPackageContents();
		theQadagPackage.createPackageContents();

		// Initialize created meta-data
		theOpmodePackage.initializePackageContents();
		theDargPackage.initializePackageContents();
		theQadagPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theOpmodePackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(OpmodePackage.eNS_URI, theOpmodePackage);
		return theOpmodePackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getOperatingModeSelection() {
		return operatingModeSelectionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getOperatingModeSelection_StartArchitecture() {
		return (EReference)operatingModeSelectionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getOperatingModeSelection_FailingResources() {
		return (EReference)operatingModeSelectionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OpmodeFactory getOpmodeFactory() {
		return (OpmodeFactory)getEFactoryInstance();
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
		operatingModeSelectionEClass = createEClass(OPERATING_MODE_SELECTION);
		createEReference(operatingModeSelectionEClass, OPERATING_MODE_SELECTION__START_ARCHITECTURE);
		createEReference(operatingModeSelectionEClass, OPERATING_MODE_SELECTION__FAILING_RESOURCES);
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
		DargPackage theDargPackage = (DargPackage)EPackage.Registry.INSTANCE.getEPackage(DargPackage.eNS_URI);
		ResourcerelationsPackage theResourcerelationsPackage = (ResourcerelationsPackage)EPackage.Registry.INSTANCE.getEPackage(ResourcerelationsPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes

		// Initialize classes, features, and operations; add parameters
		initEClass(operatingModeSelectionEClass, OperatingModeSelection.class, "OperatingModeSelection", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getOperatingModeSelection_StartArchitecture(), theDargPackage.getArchitecture(), null, "startArchitecture", null, 0, 1, OperatingModeSelection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getOperatingModeSelection_FailingResources(), theResourcerelationsPackage.getResource(), null, "failingResources", null, 0, -1, OperatingModeSelection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Create resource
		createResource(eNS_URI);
	}

} //OpmodePackageImpl
