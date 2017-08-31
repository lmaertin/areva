/**
 */
package de.tubs.forjahn.bachelor.model.cps;

import de.tubs.areva.emf.model.darg.ARG;

import de.tubs.areva.resourcerelations.Resource;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Calculation Parameter Selection</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.tubs.forjahn.bachelor.model.cps.CalculationParameterSelection#getSelectedDomains <em>Selected Domains</em>}</li>
 *   <li>{@link de.tubs.forjahn.bachelor.model.cps.CalculationParameterSelection#getSelectedResourceFailures <em>Selected Resource Failures</em>}</li>
 *   <li>{@link de.tubs.forjahn.bachelor.model.cps.CalculationParameterSelection#getQualityWeight <em>Quality Weight</em>}</li>
 *   <li>{@link de.tubs.forjahn.bachelor.model.cps.CalculationParameterSelection#getCentralityWeight <em>Centrality Weight</em>}</li>
 *   <li>{@link de.tubs.forjahn.bachelor.model.cps.CalculationParameterSelection#getResourceWeight <em>Resource Weight</em>}</li>
 *   <li>{@link de.tubs.forjahn.bachelor.model.cps.CalculationParameterSelection#getDifferenceThreshold <em>Difference Threshold</em>}</li>
 * </ul>
 *
 * @see de.tubs.forjahn.bachelor.model.cps.CpsPackage#getCalculationParameterSelection()
 * @model
 * @generated
 */
public interface CalculationParameterSelection extends EObject {
	/**
	 * Returns the value of the '<em><b>Selected Domains</b></em>' reference list.
	 * The list contents are of type {@link de.tubs.areva.emf.model.darg.ARG}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Selected Domains</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Selected Domains</em>' reference list.
	 * @see de.tubs.forjahn.bachelor.model.cps.CpsPackage#getCalculationParameterSelection_SelectedDomains()
	 * @model
	 * @generated
	 */
	EList<ARG> getSelectedDomains();

	/**
	 * Returns the value of the '<em><b>Selected Resource Failures</b></em>' reference list.
	 * The list contents are of type {@link de.tubs.areva.resourcerelations.Resource}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Selected Resource Failures</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Selected Resource Failures</em>' reference list.
	 * @see de.tubs.forjahn.bachelor.model.cps.CpsPackage#getCalculationParameterSelection_SelectedResourceFailures()
	 * @model
	 * @generated
	 */
	EList<Resource> getSelectedResourceFailures();

	/**
	 * Returns the value of the '<em><b>Quality Weight</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Quality Weight</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Quality Weight</em>' attribute.
	 * @see #setQualityWeight(double)
	 * @see de.tubs.forjahn.bachelor.model.cps.CpsPackage#getCalculationParameterSelection_QualityWeight()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.Double"
	 * @generated
	 */
	double getQualityWeight();

	/**
	 * Sets the value of the '{@link de.tubs.forjahn.bachelor.model.cps.CalculationParameterSelection#getQualityWeight <em>Quality Weight</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Quality Weight</em>' attribute.
	 * @see #getQualityWeight()
	 * @generated
	 */
	void setQualityWeight(double value);

	/**
	 * Returns the value of the '<em><b>Centrality Weight</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Centrality Weight</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Centrality Weight</em>' attribute.
	 * @see #setCentralityWeight(double)
	 * @see de.tubs.forjahn.bachelor.model.cps.CpsPackage#getCalculationParameterSelection_CentralityWeight()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.Double"
	 * @generated
	 */
	double getCentralityWeight();

	/**
	 * Sets the value of the '{@link de.tubs.forjahn.bachelor.model.cps.CalculationParameterSelection#getCentralityWeight <em>Centrality Weight</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Centrality Weight</em>' attribute.
	 * @see #getCentralityWeight()
	 * @generated
	 */
	void setCentralityWeight(double value);

	/**
	 * Returns the value of the '<em><b>Resource Weight</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Resource Weight</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Resource Weight</em>' attribute.
	 * @see #setResourceWeight(double)
	 * @see de.tubs.forjahn.bachelor.model.cps.CpsPackage#getCalculationParameterSelection_ResourceWeight()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.Double"
	 * @generated
	 */
	double getResourceWeight();

	/**
	 * Sets the value of the '{@link de.tubs.forjahn.bachelor.model.cps.CalculationParameterSelection#getResourceWeight <em>Resource Weight</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Resource Weight</em>' attribute.
	 * @see #getResourceWeight()
	 * @generated
	 */
	void setResourceWeight(double value);

	/**
	 * Returns the value of the '<em><b>Difference Threshold</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Difference Threshold</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Difference Threshold</em>' attribute.
	 * @see #setDifferenceThreshold(int)
	 * @see de.tubs.forjahn.bachelor.model.cps.CpsPackage#getCalculationParameterSelection_DifferenceThreshold()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.Int"
	 * @generated
	 */
	int getDifferenceThreshold();

	/**
	 * Sets the value of the '{@link de.tubs.forjahn.bachelor.model.cps.CalculationParameterSelection#getDifferenceThreshold <em>Difference Threshold</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Difference Threshold</em>' attribute.
	 * @see #getDifferenceThreshold()
	 * @generated
	 */
	void setDifferenceThreshold(int value);

} // CalculationParameterSelection
