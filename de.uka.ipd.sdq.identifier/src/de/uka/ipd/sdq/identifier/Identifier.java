/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.uka.ipd.sdq.identifier;

import org.eclipse.emf.cdo.CDOObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Identifier</b></em>'. <!--
 * end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * <p>
 * Inherit from this entity to make an element uniquely identifiable.<br />
 * Identifiers&nbsp;are&nbsp;not&nbsp;fixed&nbsp;to&nbsp;one&nbsp;realization.<br />
 * GUIDs&nbsp;are&nbsp;recommend.&nbsp;GUIDs&nbsp;are&nbsp;described&nbsp;in&nbsp;their&nbsp;own&nbsp;model.&nbsp;See&nbsp;GUIDModel&nbsp;(GUID.emx).<br />
 * Identifier&nbsp;implementations&nbsp;can&nbsp;be&nbsp;found&nbsp;in&nbsp;external&nbsp;projects&nbsp;only.
 * </p>
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.uka.ipd.sdq.identifier.Identifier#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.uka.ipd.sdq.identifier.IdentifierPackage#getIdentifier()
 * @model abstract="true"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot null='Identifier.allInstances()->isUnique(p: Identifier | p.id)'"
 * @extends CDOObject
 * @generated
 */
public interface Identifier extends CDOObject {
    /**
     * Returns the value of the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Id</em>' attribute isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc --> <!-- begin-model-doc --> Identifier attribute, in the default PCM
     * implementation, this field is filled with a randomly generated UUID value <!-- end-model-doc
     * -->
     * 
     * @return the value of the '<em>Id</em>' attribute.
     * @see #setId(String)
     * @see de.uka.ipd.sdq.identifier.IdentifierPackage#getIdentifier_Id()
     * @model id="true" required="true"
     *        extendedMetaData="name='id' namespace='http://sdq.ipd.uka.de/Identifier/1.0'"
     * @generated
     */
    String getId();

    /**
     * Sets the value of the '{@link de.uka.ipd.sdq.identifier.Identifier#getId <em>Id</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Id</em>' attribute.
     * @see #getId()
     * @generated
     */
    void setId(String value);

} // Identifier
