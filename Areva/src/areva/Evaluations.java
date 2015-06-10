/**
 */
package areva;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Evaluations</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link areva.Evaluations#getEvaluations <em>Evaluations</em>}</li>
 * </ul>
 * </p>
 *
 * @see areva.ArevaPackage#getEvaluations()
 * @model
 * @generated
 */
public interface Evaluations extends EObject {
	/**
	 * Returns the value of the '<em><b>Evaluations</b></em>' containment reference list.
	 * The list contents are of type {@link areva.Evaluation}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Evaluations</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Evaluations</em>' containment reference list.
	 * @see areva.ArevaPackage#getEvaluations_Evaluations()
	 * @model containment="true"
	 * @generated
	 */
	EList<Evaluation> getEvaluations();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model required="true"
	 * @generated
	 */
	double evaluateAll();

} // Evaluations
