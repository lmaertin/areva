/**
 */
package de.tubs.forjahn.bachelor.model.cps.impl;

import de.tubs.areva.emf.model.darg.DargPackage;

import de.tubs.areva.emf.model.qadag.QadagPackage;

import de.tubs.areva.resourcerelations.ResourcerelationsPackage;

import de.tubs.forjahn.bachelor.model.cps.CalculationParameterSelection;
import de.tubs.forjahn.bachelor.model.cps.CpsFactory;
import de.tubs.forjahn.bachelor.model.cps.CpsPackage;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.emf.ecore.xml.type.XMLTypePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class CpsPackageImpl extends EPackageImpl implements CpsPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass calculationParameterSelectionEClass = null;

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
	 * @see de.tubs.forjahn.bachelor.model.cps.CpsPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private CpsPackageImpl() {
		super(eNS_URI, CpsFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link CpsPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static CpsPackage init() {
		if (isInited) return (CpsPackage)EPackage.Registry.INSTANCE.getEPackage(CpsPackage.eNS_URI);

		// Obtain or create and register package
		CpsPackageImpl theCpsPackage = (CpsPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof CpsPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new CpsPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		DargPackage.eINSTANCE.eClass();
		QadagPackage.eINSTANCE.eClass();
		XMLTypePackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theCpsPackage.createPackageContents();

		// Initialize created meta-data
		theCpsPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theCpsPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(CpsPackage.eNS_URI, theCpsPackage);
		return theCpsPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCalculationParameterSelection() {
		return calculationParameterSelectionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCalculationParameterSelection_SelectedDomains() {
		return (EReference)calculationParameterSelectionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCalculationParameterSelection_SelectedResourceFailures() {
		return (EReference)calculationParameterSelectionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCalculationParameterSelection_QualityWeight() {
		return (EAttribute)calculationParameterSelectionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCalculationParameterSelection_CentralityWeight() {
		return (EAttribute)calculationParameterSelectionEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCalculationParameterSelection_ResourceWeight() {
		return (EAttribute)calculationParameterSelectionEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCalculationParameterSelection_DifferenceThreshold() {
		return (EAttribute)calculationParameterSelectionEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CpsFactory getCpsFactory() {
		return (CpsFactory)getEFactoryInstance();
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
		calculationParameterSelectionEClass = createEClass(CALCULATION_PARAMETER_SELECTION);
		createEReference(calculationParameterSelectionEClass, CALCULATION_PARAMETER_SELECTION__SELECTED_DOMAINS);
		createEReference(calculationParameterSelectionEClass, CALCULATION_PARAMETER_SELECTION__SELECTED_RESOURCE_FAILURES);
		createEAttribute(calculationParameterSelectionEClass, CALCULATION_PARAMETER_SELECTION__QUALITY_WEIGHT);
		createEAttribute(calculationParameterSelectionEClass, CALCULATION_PARAMETER_SELECTION__CENTRALITY_WEIGHT);
		createEAttribute(calculationParameterSelectionEClass, CALCULATION_PARAMETER_SELECTION__RESOURCE_WEIGHT);
		createEAttribute(calculationParameterSelectionEClass, CALCULATION_PARAMETER_SELECTION__DIFFERENCE_THRESHOLD);
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
		XMLTypePackage theXMLTypePackage = (XMLTypePackage)EPackage.Registry.INSTANCE.getEPackage(XMLTypePackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes

		// Initialize classes, features, and operations; add parameters
		initEClass(calculationParameterSelectionEClass, CalculationParameterSelection.class, "CalculationParameterSelection", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getCalculationParameterSelection_SelectedDomains(), theDargPackage.getARG(), null, "SelectedDomains", null, 0, -1, CalculationParameterSelection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCalculationParameterSelection_SelectedResourceFailures(), theResourcerelationsPackage.getResource(), null, "SelectedResourceFailures", null, 0, -1, CalculationParameterSelection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCalculationParameterSelection_QualityWeight(), theXMLTypePackage.getDouble(), "QualityWeight", null, 0, 1, CalculationParameterSelection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCalculationParameterSelection_CentralityWeight(), theXMLTypePackage.getDouble(), "CentralityWeight", null, 0, 1, CalculationParameterSelection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCalculationParameterSelection_ResourceWeight(), theXMLTypePackage.getDouble(), "ResourceWeight", null, 0, 1, CalculationParameterSelection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCalculationParameterSelection_DifferenceThreshold(), theXMLTypePackage.getInt(), "DifferenceThreshold", null, 0, 1, CalculationParameterSelection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Create resource
		createResource(eNS_URI);
	}

} //CpsPackageImpl
