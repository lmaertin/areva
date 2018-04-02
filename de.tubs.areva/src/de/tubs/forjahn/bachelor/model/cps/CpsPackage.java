/**
 */
package de.tubs.forjahn.bachelor.model.cps;

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
 * @see de.tubs.forjahn.bachelor.model.cps.CpsFactory
 * @model kind="package"
 * @generated
 */
public interface CpsPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "cps";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://com/forjahn/cps/";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "de.tubs.areva.forjahn.bachelor.model.cps";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	CpsPackage eINSTANCE = de.tubs.forjahn.bachelor.model.cps.impl.CpsPackageImpl.init();

	/**
	 * The meta object id for the '{@link de.tubs.forjahn.bachelor.model.cps.impl.CalculationParameterSelectionImpl <em>Calculation Parameter Selection</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.tubs.forjahn.bachelor.model.cps.impl.CalculationParameterSelectionImpl
	 * @see de.tubs.forjahn.bachelor.model.cps.impl.CpsPackageImpl#getCalculationParameterSelection()
	 * @generated
	 */
	int CALCULATION_PARAMETER_SELECTION = 0;

	/**
	 * The feature id for the '<em><b>Selected Domains</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALCULATION_PARAMETER_SELECTION__SELECTED_DOMAINS = 0;

	/**
	 * The feature id for the '<em><b>Selected Resource Failures</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALCULATION_PARAMETER_SELECTION__SELECTED_RESOURCE_FAILURES = 1;

	/**
	 * The feature id for the '<em><b>Quality Weight</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALCULATION_PARAMETER_SELECTION__QUALITY_WEIGHT = 2;

	/**
	 * The feature id for the '<em><b>Centrality Weight</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALCULATION_PARAMETER_SELECTION__CENTRALITY_WEIGHT = 3;

	/**
	 * The feature id for the '<em><b>Resource Weight</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALCULATION_PARAMETER_SELECTION__RESOURCE_WEIGHT = 4;

	/**
	 * The feature id for the '<em><b>Difference Threshold</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALCULATION_PARAMETER_SELECTION__DIFFERENCE_THRESHOLD = 5;

	/**
	 * The number of structural features of the '<em>Calculation Parameter Selection</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALCULATION_PARAMETER_SELECTION_FEATURE_COUNT = 6;

	/**
	 * The number of operations of the '<em>Calculation Parameter Selection</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALCULATION_PARAMETER_SELECTION_OPERATION_COUNT = 0;


	/**
	 * Returns the meta object for class '{@link de.tubs.forjahn.bachelor.model.cps.CalculationParameterSelection <em>Calculation Parameter Selection</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Calculation Parameter Selection</em>'.
	 * @see de.tubs.forjahn.bachelor.model.cps.CalculationParameterSelection
	 * @generated
	 */
	EClass getCalculationParameterSelection();

	/**
	 * Returns the meta object for the reference list '{@link de.tubs.forjahn.bachelor.model.cps.CalculationParameterSelection#getSelectedDomains <em>Selected Domains</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Selected Domains</em>'.
	 * @see de.tubs.forjahn.bachelor.model.cps.CalculationParameterSelection#getSelectedDomains()
	 * @see #getCalculationParameterSelection()
	 * @generated
	 */
	EReference getCalculationParameterSelection_SelectedDomains();

	/**
	 * Returns the meta object for the reference list '{@link de.tubs.forjahn.bachelor.model.cps.CalculationParameterSelection#getSelectedResourceFailures <em>Selected Resource Failures</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Selected Resource Failures</em>'.
	 * @see de.tubs.forjahn.bachelor.model.cps.CalculationParameterSelection#getSelectedResourceFailures()
	 * @see #getCalculationParameterSelection()
	 * @generated
	 */
	EReference getCalculationParameterSelection_SelectedResourceFailures();

	/**
	 * Returns the meta object for the attribute '{@link de.tubs.forjahn.bachelor.model.cps.CalculationParameterSelection#getQualityWeight <em>Quality Weight</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Quality Weight</em>'.
	 * @see de.tubs.forjahn.bachelor.model.cps.CalculationParameterSelection#getQualityWeight()
	 * @see #getCalculationParameterSelection()
	 * @generated
	 */
	EAttribute getCalculationParameterSelection_QualityWeight();

	/**
	 * Returns the meta object for the attribute '{@link de.tubs.forjahn.bachelor.model.cps.CalculationParameterSelection#getCentralityWeight <em>Centrality Weight</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Centrality Weight</em>'.
	 * @see de.tubs.forjahn.bachelor.model.cps.CalculationParameterSelection#getCentralityWeight()
	 * @see #getCalculationParameterSelection()
	 * @generated
	 */
	EAttribute getCalculationParameterSelection_CentralityWeight();

	/**
	 * Returns the meta object for the attribute '{@link de.tubs.forjahn.bachelor.model.cps.CalculationParameterSelection#getResourceWeight <em>Resource Weight</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Resource Weight</em>'.
	 * @see de.tubs.forjahn.bachelor.model.cps.CalculationParameterSelection#getResourceWeight()
	 * @see #getCalculationParameterSelection()
	 * @generated
	 */
	EAttribute getCalculationParameterSelection_ResourceWeight();

	/**
	 * Returns the meta object for the attribute '{@link de.tubs.forjahn.bachelor.model.cps.CalculationParameterSelection#getDifferenceThreshold <em>Difference Threshold</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Difference Threshold</em>'.
	 * @see de.tubs.forjahn.bachelor.model.cps.CalculationParameterSelection#getDifferenceThreshold()
	 * @see #getCalculationParameterSelection()
	 * @generated
	 */
	EAttribute getCalculationParameterSelection_DifferenceThreshold();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	CpsFactory getCpsFactory();

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
		 * The meta object literal for the '{@link de.tubs.forjahn.bachelor.model.cps.impl.CalculationParameterSelectionImpl <em>Calculation Parameter Selection</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.tubs.forjahn.bachelor.model.cps.impl.CalculationParameterSelectionImpl
		 * @see de.tubs.forjahn.bachelor.model.cps.impl.CpsPackageImpl#getCalculationParameterSelection()
		 * @generated
		 */
		EClass CALCULATION_PARAMETER_SELECTION = eINSTANCE.getCalculationParameterSelection();

		/**
		 * The meta object literal for the '<em><b>Selected Domains</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CALCULATION_PARAMETER_SELECTION__SELECTED_DOMAINS = eINSTANCE.getCalculationParameterSelection_SelectedDomains();

		/**
		 * The meta object literal for the '<em><b>Selected Resource Failures</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CALCULATION_PARAMETER_SELECTION__SELECTED_RESOURCE_FAILURES = eINSTANCE.getCalculationParameterSelection_SelectedResourceFailures();

		/**
		 * The meta object literal for the '<em><b>Quality Weight</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CALCULATION_PARAMETER_SELECTION__QUALITY_WEIGHT = eINSTANCE.getCalculationParameterSelection_QualityWeight();

		/**
		 * The meta object literal for the '<em><b>Centrality Weight</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CALCULATION_PARAMETER_SELECTION__CENTRALITY_WEIGHT = eINSTANCE.getCalculationParameterSelection_CentralityWeight();

		/**
		 * The meta object literal for the '<em><b>Resource Weight</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CALCULATION_PARAMETER_SELECTION__RESOURCE_WEIGHT = eINSTANCE.getCalculationParameterSelection_ResourceWeight();

		/**
		 * The meta object literal for the '<em><b>Difference Threshold</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CALCULATION_PARAMETER_SELECTION__DIFFERENCE_THRESHOLD = eINSTANCE.getCalculationParameterSelection_DifferenceThreshold();

	}

} //CpsPackage
