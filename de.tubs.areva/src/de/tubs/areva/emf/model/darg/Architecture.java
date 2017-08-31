/**
 */
package de.tubs.areva.emf.model.darg;

import de.tubs.areva.emf.model.qadag.QADAG;
import de.tubs.areva.resourcerelations.Resource;
import de.tubs.areva.resourcerelations.ResourceOptions;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.palladiosimulator.pcm.repository.BasicComponent;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Architecture</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.tubs.areva.emf.model.darg.Architecture#getQuality <em>Quality</em>}</li>
 *   <li>{@link de.tubs.areva.emf.model.darg.Architecture#getQadag <em>Qadag</em>}</li>
 *   <li>{@link de.tubs.areva.emf.model.darg.Architecture#getBoundResourceOptions <em>Bound Resource Options</em>}</li>
 *   <li>{@link de.tubs.areva.emf.model.darg.Architecture#getQualityassignments <em>Qualityassignments</em>}</li>
 *   <li>{@link de.tubs.areva.emf.model.darg.Architecture#isOptimal <em>Optimal</em>}</li>
 *   <li>{@link de.tubs.areva.emf.model.darg.Architecture#isHidden <em>Hidden</em>}</li>
 *   <li>{@link de.tubs.areva.emf.model.darg.Architecture#getMarked <em>Marked</em>}</li>
 *   <li>{@link de.tubs.areva.emf.model.darg.Architecture#getId <em>Id</em>}</li>
 *   <li>{@link de.tubs.areva.emf.model.darg.Architecture#getRelatedArchitectures <em>Related Architectures</em>}</li>
 *   <li>{@link de.tubs.areva.emf.model.darg.Architecture#getBoundResources <em>Bound Resources</em>}</li>
 *   <li>{@link de.tubs.areva.emf.model.darg.Architecture#getBoundBasicComponents <em>Bound Basic Components</em>}</li>
 * </ul>
 *
 * @see de.tubs.areva.emf.model.darg.DargPackage#getArchitecture()
 * @model
 * @generated
 */
public interface Architecture extends EObject {
	/**
	 * Returns the value of the '<em><b>Quality</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Quality</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Quality</em>' attribute.
	 * @see #setQuality(double)
	 * @see de.tubs.areva.emf.model.darg.DargPackage#getArchitecture_Quality()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.Double"
	 * @generated
	 */
	double getQuality();

	/**
	 * Sets the value of the '{@link de.tubs.areva.emf.model.darg.Architecture#getQuality <em>Quality</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Quality</em>' attribute.
	 * @see #getQuality()
	 * @generated
	 */
	void setQuality(double value);

	/**
	 * Returns the value of the '<em><b>Qadag</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Qadag</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Qadag</em>' containment reference.
	 * @see #setQadag(QADAG)
	 * @see de.tubs.areva.emf.model.darg.DargPackage#getArchitecture_Qadag()
	 * @model containment="true"
	 * @generated
	 */
	QADAG getQadag();

	/**
	 * Sets the value of the '{@link de.tubs.areva.emf.model.darg.Architecture#getQadag <em>Qadag</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Qadag</em>' containment reference.
	 * @see #getQadag()
	 * @generated
	 */
	void setQadag(QADAG value);

	/**
	 * Returns the value of the '<em><b>Bound Resource Options</b></em>' reference list.
	 * The list contents are of type {@link de.tubs.areva.resourcerelations.ResourceOptions}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Bound Resource Options</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Bound Resource Options</em>' reference list.
	 * @see de.tubs.areva.emf.model.darg.DargPackage#getArchitecture_BoundResourceOptions()
	 * @model
	 * @generated
	 */
	EList<ResourceOptions> getBoundResourceOptions();

	/**
	 * Returns the value of the '<em><b>Qualityassignments</b></em>' containment reference list.
	 * The list contents are of type {@link de.tubs.areva.emf.model.darg.QualityAssignment}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Qualityassignments</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Qualityassignments</em>' containment reference list.
	 * @see de.tubs.areva.emf.model.darg.DargPackage#getArchitecture_Qualityassignments()
	 * @model containment="true"
	 * @generated
	 */
	EList<QualityAssignment> getQualityassignments();

	/**
	 * Returns the value of the '<em><b>Optimal</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Optimal</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Optimal</em>' attribute.
	 * @see #setOptimal(boolean)
	 * @see de.tubs.areva.emf.model.darg.DargPackage#getArchitecture_Optimal()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 * @generated
	 */
	boolean isOptimal();

	/**
	 * Sets the value of the '{@link de.tubs.areva.emf.model.darg.Architecture#isOptimal <em>Optimal</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Optimal</em>' attribute.
	 * @see #isOptimal()
	 * @generated
	 */
	void setOptimal(boolean value);

	/**
	 * Returns the value of the '<em><b>Hidden</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Hidden</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Hidden</em>' attribute.
	 * @see #setHidden(boolean)
	 * @see de.tubs.areva.emf.model.darg.DargPackage#getArchitecture_Hidden()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 * @generated
	 */
	boolean isHidden();

	/**
	 * Sets the value of the '{@link de.tubs.areva.emf.model.darg.Architecture#isHidden <em>Hidden</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Hidden</em>' attribute.
	 * @see #isHidden()
	 * @generated
	 */
	void setHidden(boolean value);

	/**
	 * Returns the value of the '<em><b>Marked</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Marked</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Marked</em>' attribute.
	 * @see #setMarked(float)
	 * @see de.tubs.areva.emf.model.darg.DargPackage#getArchitecture_Marked()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.Float"
	 * @generated
	 */
	float getMarked();

	/**
	 * Sets the value of the '{@link de.tubs.areva.emf.model.darg.Architecture#getMarked <em>Marked</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Marked</em>' attribute.
	 * @see #getMarked()
	 * @generated
	 */
	void setMarked(float value);

	/**
	 * Returns the value of the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see #setId(String)
	 * @see de.tubs.areva.emf.model.darg.DargPackage#getArchitecture_Id()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link de.tubs.areva.emf.model.darg.Architecture#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

	/**
	 * Returns the value of the '<em><b>Related Architectures</b></em>' reference list.
	 * The list contents are of type {@link de.tubs.areva.emf.model.darg.Architecture}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Related Architectures</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Related Architectures</em>' reference list.
	 * @see de.tubs.areva.emf.model.darg.DargPackage#getArchitecture_RelatedArchitectures()
	 * @model
	 * @generated
	 */
	EList<Architecture> getRelatedArchitectures();

	/**
	 * Returns the value of the '<em><b>Bound Resources</b></em>' reference list.
	 * The list contents are of type {@link de.tubs.areva.resourcerelations.Resource}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Bound Resources</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Bound Resources</em>' reference list.
	 * @see de.tubs.areva.emf.model.darg.DargPackage#getArchitecture_BoundResources()
	 * @model
	 * @generated
	 */
	EList<Resource> getBoundResources();

	/**
	 * Returns the value of the '<em><b>Bound Basic Components</b></em>' reference list.
	 * The list contents are of type {@link org.palladiosimulator.pcm.repository.BasicComponent}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Bound Basic Components</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Bound Basic Components</em>' reference list.
	 * @see de.tubs.areva.emf.model.darg.DargPackage#getArchitecture_BoundBasicComponents()
	 * @model
	 * @generated
	 */
	EList<BasicComponent> getBoundBasicComponents();

} // Architecture
