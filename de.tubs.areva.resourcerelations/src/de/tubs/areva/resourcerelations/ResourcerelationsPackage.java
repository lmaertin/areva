/**
 */
package de.tubs.areva.resourcerelations;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
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
 * @see de.tubs.areva.resourcerelations.ResourcerelationsFactory
 * @model kind="package"
 * @generated
 */
public interface ResourcerelationsPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "resourcerelations";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://ips.tu-bs.de/ResourceRelationsModel/1.0";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "arr";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ResourcerelationsPackage eINSTANCE = de.tubs.areva.resourcerelations.impl.ResourcerelationsPackageImpl.init();

	/**
	 * The meta object id for the '{@link de.tubs.areva.resourcerelations.impl.ResourceImpl <em>Resource</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.tubs.areva.resourcerelations.impl.ResourceImpl
	 * @see de.tubs.areva.resourcerelations.impl.ResourcerelationsPackageImpl#getResource()
	 * @generated
	 */
	int RESOURCE = 0;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE__TYPE = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE__NAME = 1;

	/**
	 * The feature id for the '<em><b>Specs</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE__SPECS = 2;

	/**
	 * The feature id for the '<em><b>Interface</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE__INTERFACE = 3;

	/**
	 * The feature id for the '<em><b>Redundant</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE__REDUNDANT = 4;

	/**
	 * The number of structural features of the '<em>Resource</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_FEATURE_COUNT = 5;

	/**
	 * The number of operations of the '<em>Resource</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link de.tubs.areva.resourcerelations.impl.SpecificationImpl <em>Specification</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.tubs.areva.resourcerelations.impl.SpecificationImpl
	 * @see de.tubs.areva.resourcerelations.impl.ResourcerelationsPackageImpl#getSpecification()
	 * @generated
	 */
	int SPECIFICATION = 1;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPECIFICATION__VALUE = 0;

	/**
	 * The feature id for the '<em><b>Assigned Prop</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPECIFICATION__ASSIGNED_PROP = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPECIFICATION__NAME = 2;

	/**
	 * The number of structural features of the '<em>Specification</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPECIFICATION_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Specification</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPECIFICATION_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link de.tubs.areva.resourcerelations.impl.PlatformImpl <em>Platform</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.tubs.areva.resourcerelations.impl.PlatformImpl
	 * @see de.tubs.areva.resourcerelations.impl.ResourcerelationsPackageImpl#getPlatform()
	 * @generated
	 */
	int PLATFORM = 2;

	/**
	 * The feature id for the '<em><b>Options</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLATFORM__OPTIONS = 0;

	/**
	 * The feature id for the '<em><b>Specs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLATFORM__SPECS = 1;

	/**
	 * The feature id for the '<em><b>Props</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLATFORM__PROPS = 2;

	/**
	 * The feature id for the '<em><b>Resources</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLATFORM__RESOURCES = 3;

	/**
	 * The feature id for the '<em><b>Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLATFORM__RULES = 4;

	/**
	 * The number of structural features of the '<em>Platform</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLATFORM_FEATURE_COUNT = 5;

	/**
	 * The number of operations of the '<em>Platform</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLATFORM_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link de.tubs.areva.resourcerelations.impl.PropertyImpl <em>Property</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.tubs.areva.resourcerelations.impl.PropertyImpl
	 * @see de.tubs.areva.resourcerelations.impl.ResourcerelationsPackageImpl#getProperty()
	 * @generated
	 */
	int PROPERTY = 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY__NAME = 0;

	/**
	 * The feature id for the '<em><b>Unit</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY__UNIT = 1;

	/**
	 * The number of structural features of the '<em>Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link de.tubs.areva.resourcerelations.impl.ResourceOptionsImpl <em>Resource Options</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.tubs.areva.resourcerelations.impl.ResourceOptionsImpl
	 * @see de.tubs.areva.resourcerelations.impl.ResourcerelationsPackageImpl#getResourceOptions()
	 * @generated
	 */
	int RESOURCE_OPTIONS = 4;

	/**
	 * The feature id for the '<em><b>Resources</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_OPTIONS__RESOURCES = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_OPTIONS__NAME = 1;

	/**
	 * The feature id for the '<em><b>Min Elements</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_OPTIONS__MIN_ELEMENTS = 2;

	/**
	 * The feature id for the '<em><b>Max Elements</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_OPTIONS__MAX_ELEMENTS = 3;

	/**
	 * The feature id for the '<em><b>Referenced Component</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_OPTIONS__REFERENCED_COMPONENT = 4;

	/**
	 * The feature id for the '<em><b>Specs</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_OPTIONS__SPECS = 5;

	/**
	 * The feature id for the '<em><b>Sub Groups</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_OPTIONS__SUB_GROUPS = 6;

	/**
	 * The number of structural features of the '<em>Resource Options</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_OPTIONS_FEATURE_COUNT = 7;

	/**
	 * The number of operations of the '<em>Resource Options</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_OPTIONS_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link de.tubs.areva.resourcerelations.impl.RuleImpl <em>Rule</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.tubs.areva.resourcerelations.impl.RuleImpl
	 * @see de.tubs.areva.resourcerelations.impl.ResourcerelationsPackageImpl#getRule()
	 * @generated
	 */
	int RULE = 5;

	/**
	 * The feature id for the '<em><b>Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE__EXPRESSION = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE__NAME = 1;

	/**
	 * The number of structural features of the '<em>Rule</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Rule</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link de.tubs.areva.resourcerelations.impl.ExpressionImpl <em>Expression</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.tubs.areva.resourcerelations.impl.ExpressionImpl
	 * @see de.tubs.areva.resourcerelations.impl.ResourcerelationsPackageImpl#getExpression()
	 * @generated
	 */
	int EXPRESSION = 6;

	/**
	 * The number of structural features of the '<em>Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPRESSION_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPRESSION_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link de.tubs.areva.resourcerelations.impl.ResourceVariableImpl <em>Resource Variable</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.tubs.areva.resourcerelations.impl.ResourceVariableImpl
	 * @see de.tubs.areva.resourcerelations.impl.ResourcerelationsPackageImpl#getResourceVariable()
	 * @generated
	 */
	int RESOURCE_VARIABLE = 7;

	/**
	 * The feature id for the '<em><b>Resource</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_VARIABLE__RESOURCE = EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Resource Variable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_VARIABLE_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Resource Variable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_VARIABLE_OPERATION_COUNT = EXPRESSION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link de.tubs.areva.resourcerelations.impl.ImpliesImpl <em>Implies</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.tubs.areva.resourcerelations.impl.ImpliesImpl
	 * @see de.tubs.areva.resourcerelations.impl.ResourcerelationsPackageImpl#getImplies()
	 * @generated
	 */
	int IMPLIES = 8;

	/**
	 * The feature id for the '<em><b>Left Hand Side</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPLIES__LEFT_HAND_SIDE = 0;

	/**
	 * The feature id for the '<em><b>Right Hand Side</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPLIES__RIGHT_HAND_SIDE = 1;

	/**
	 * The feature id for the '<em><b>Left Hand Side Modifier</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPLIES__LEFT_HAND_SIDE_MODIFIER = 2;

	/**
	 * The number of structural features of the '<em>Implies</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPLIES_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Implies</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPLIES_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link de.tubs.areva.resourcerelations.impl.NotImpl <em>Not</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.tubs.areva.resourcerelations.impl.NotImpl
	 * @see de.tubs.areva.resourcerelations.impl.ResourcerelationsPackageImpl#getNot()
	 * @generated
	 */
	int NOT = 9;

	/**
	 * The feature id for the '<em><b>Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NOT__EXPRESSION = EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Not</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NOT_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Not</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NOT_OPERATION_COUNT = EXPRESSION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link de.tubs.areva.resourcerelations.impl.OrImpl <em>Or</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.tubs.areva.resourcerelations.impl.OrImpl
	 * @see de.tubs.areva.resourcerelations.impl.ResourcerelationsPackageImpl#getOr()
	 * @generated
	 */
	int OR = 10;

	/**
	 * The feature id for the '<em><b>Left Hand Side</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OR__LEFT_HAND_SIDE = EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Right Hand Side</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OR__RIGHT_HAND_SIDE = EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Or</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OR_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Or</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OR_OPERATION_COUNT = EXPRESSION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link de.tubs.areva.resourcerelations.impl.AndImpl <em>And</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.tubs.areva.resourcerelations.impl.AndImpl
	 * @see de.tubs.areva.resourcerelations.impl.ResourcerelationsPackageImpl#getAnd()
	 * @generated
	 */
	int AND = 11;

	/**
	 * The feature id for the '<em><b>Left Hand Side</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AND__LEFT_HAND_SIDE = EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Right Hand Side</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AND__RIGHT_HAND_SIDE = EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>And</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AND_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>And</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AND_OPERATION_COUNT = EXPRESSION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link de.tubs.areva.resourcerelations.impl.ResourceOptionsVariableImpl <em>Resource Options Variable</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.tubs.areva.resourcerelations.impl.ResourceOptionsVariableImpl
	 * @see de.tubs.areva.resourcerelations.impl.ResourcerelationsPackageImpl#getResourceOptionsVariable()
	 * @generated
	 */
	int RESOURCE_OPTIONS_VARIABLE = 12;

	/**
	 * The feature id for the '<em><b>Resource Options</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_OPTIONS_VARIABLE__RESOURCE_OPTIONS = EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Resource Options Variable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_OPTIONS_VARIABLE_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Resource Options Variable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_OPTIONS_VARIABLE_OPERATION_COUNT = EXPRESSION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link de.tubs.areva.resourcerelations.ResourceType <em>Resource Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.tubs.areva.resourcerelations.ResourceType
	 * @see de.tubs.areva.resourcerelations.impl.ResourcerelationsPackageImpl#getResourceType()
	 * @generated
	 */
	int RESOURCE_TYPE = 13;


	/**
	 * Returns the meta object for class '{@link de.tubs.areva.resourcerelations.Resource <em>Resource</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Resource</em>'.
	 * @see de.tubs.areva.resourcerelations.Resource
	 * @generated
	 */
	EClass getResource();

	/**
	 * Returns the meta object for the attribute '{@link de.tubs.areva.resourcerelations.Resource#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see de.tubs.areva.resourcerelations.Resource#getType()
	 * @see #getResource()
	 * @generated
	 */
	EAttribute getResource_Type();

	/**
	 * Returns the meta object for the attribute '{@link de.tubs.areva.resourcerelations.Resource#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see de.tubs.areva.resourcerelations.Resource#getName()
	 * @see #getResource()
	 * @generated
	 */
	EAttribute getResource_Name();

	/**
	 * Returns the meta object for the reference list '{@link de.tubs.areva.resourcerelations.Resource#getSpecs <em>Specs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Specs</em>'.
	 * @see de.tubs.areva.resourcerelations.Resource#getSpecs()
	 * @see #getResource()
	 * @generated
	 */
	EReference getResource_Specs();

	/**
	 * Returns the meta object for the reference '{@link de.tubs.areva.resourcerelations.Resource#getInterface <em>Interface</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Interface</em>'.
	 * @see de.tubs.areva.resourcerelations.Resource#getInterface()
	 * @see #getResource()
	 * @generated
	 */
	EReference getResource_Interface();

	/**
	 * Returns the meta object for the reference list '{@link de.tubs.areva.resourcerelations.Resource#getRedundant <em>Redundant</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Redundant</em>'.
	 * @see de.tubs.areva.resourcerelations.Resource#getRedundant()
	 * @see #getResource()
	 * @generated
	 */
	EReference getResource_Redundant();

	/**
	 * Returns the meta object for class '{@link de.tubs.areva.resourcerelations.Specification <em>Specification</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Specification</em>'.
	 * @see de.tubs.areva.resourcerelations.Specification
	 * @generated
	 */
	EClass getSpecification();

	/**
	 * Returns the meta object for the attribute '{@link de.tubs.areva.resourcerelations.Specification#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see de.tubs.areva.resourcerelations.Specification#getValue()
	 * @see #getSpecification()
	 * @generated
	 */
	EAttribute getSpecification_Value();

	/**
	 * Returns the meta object for the reference '{@link de.tubs.areva.resourcerelations.Specification#getAssignedProp <em>Assigned Prop</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Assigned Prop</em>'.
	 * @see de.tubs.areva.resourcerelations.Specification#getAssignedProp()
	 * @see #getSpecification()
	 * @generated
	 */
	EReference getSpecification_AssignedProp();

	/**
	 * Returns the meta object for the attribute '{@link de.tubs.areva.resourcerelations.Specification#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see de.tubs.areva.resourcerelations.Specification#getName()
	 * @see #getSpecification()
	 * @generated
	 */
	EAttribute getSpecification_Name();

	/**
	 * Returns the meta object for class '{@link de.tubs.areva.resourcerelations.Platform <em>Platform</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Platform</em>'.
	 * @see de.tubs.areva.resourcerelations.Platform
	 * @generated
	 */
	EClass getPlatform();

	/**
	 * Returns the meta object for the containment reference list '{@link de.tubs.areva.resourcerelations.Platform#getOptions <em>Options</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Options</em>'.
	 * @see de.tubs.areva.resourcerelations.Platform#getOptions()
	 * @see #getPlatform()
	 * @generated
	 */
	EReference getPlatform_Options();

	/**
	 * Returns the meta object for the containment reference list '{@link de.tubs.areva.resourcerelations.Platform#getSpecs <em>Specs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Specs</em>'.
	 * @see de.tubs.areva.resourcerelations.Platform#getSpecs()
	 * @see #getPlatform()
	 * @generated
	 */
	EReference getPlatform_Specs();

	/**
	 * Returns the meta object for the containment reference list '{@link de.tubs.areva.resourcerelations.Platform#getProps <em>Props</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Props</em>'.
	 * @see de.tubs.areva.resourcerelations.Platform#getProps()
	 * @see #getPlatform()
	 * @generated
	 */
	EReference getPlatform_Props();

	/**
	 * Returns the meta object for the containment reference list '{@link de.tubs.areva.resourcerelations.Platform#getResources <em>Resources</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Resources</em>'.
	 * @see de.tubs.areva.resourcerelations.Platform#getResources()
	 * @see #getPlatform()
	 * @generated
	 */
	EReference getPlatform_Resources();

	/**
	 * Returns the meta object for the containment reference list '{@link de.tubs.areva.resourcerelations.Platform#getRules <em>Rules</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Rules</em>'.
	 * @see de.tubs.areva.resourcerelations.Platform#getRules()
	 * @see #getPlatform()
	 * @generated
	 */
	EReference getPlatform_Rules();

	/**
	 * Returns the meta object for class '{@link de.tubs.areva.resourcerelations.Property <em>Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Property</em>'.
	 * @see de.tubs.areva.resourcerelations.Property
	 * @generated
	 */
	EClass getProperty();

	/**
	 * Returns the meta object for the attribute '{@link de.tubs.areva.resourcerelations.Property#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see de.tubs.areva.resourcerelations.Property#getName()
	 * @see #getProperty()
	 * @generated
	 */
	EAttribute getProperty_Name();

	/**
	 * Returns the meta object for the attribute '{@link de.tubs.areva.resourcerelations.Property#getUnit <em>Unit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Unit</em>'.
	 * @see de.tubs.areva.resourcerelations.Property#getUnit()
	 * @see #getProperty()
	 * @generated
	 */
	EAttribute getProperty_Unit();

	/**
	 * Returns the meta object for class '{@link de.tubs.areva.resourcerelations.ResourceOptions <em>Resource Options</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Resource Options</em>'.
	 * @see de.tubs.areva.resourcerelations.ResourceOptions
	 * @generated
	 */
	EClass getResourceOptions();

	/**
	 * Returns the meta object for the reference list '{@link de.tubs.areva.resourcerelations.ResourceOptions#getResources <em>Resources</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Resources</em>'.
	 * @see de.tubs.areva.resourcerelations.ResourceOptions#getResources()
	 * @see #getResourceOptions()
	 * @generated
	 */
	EReference getResourceOptions_Resources();

	/**
	 * Returns the meta object for the attribute '{@link de.tubs.areva.resourcerelations.ResourceOptions#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see de.tubs.areva.resourcerelations.ResourceOptions#getName()
	 * @see #getResourceOptions()
	 * @generated
	 */
	EAttribute getResourceOptions_Name();

	/**
	 * Returns the meta object for the attribute '{@link de.tubs.areva.resourcerelations.ResourceOptions#getMinElements <em>Min Elements</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Min Elements</em>'.
	 * @see de.tubs.areva.resourcerelations.ResourceOptions#getMinElements()
	 * @see #getResourceOptions()
	 * @generated
	 */
	EAttribute getResourceOptions_MinElements();

	/**
	 * Returns the meta object for the attribute '{@link de.tubs.areva.resourcerelations.ResourceOptions#getMaxElements <em>Max Elements</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Max Elements</em>'.
	 * @see de.tubs.areva.resourcerelations.ResourceOptions#getMaxElements()
	 * @see #getResourceOptions()
	 * @generated
	 */
	EAttribute getResourceOptions_MaxElements();

	/**
	 * Returns the meta object for the reference '{@link de.tubs.areva.resourcerelations.ResourceOptions#getReferencedComponent <em>Referenced Component</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Referenced Component</em>'.
	 * @see de.tubs.areva.resourcerelations.ResourceOptions#getReferencedComponent()
	 * @see #getResourceOptions()
	 * @generated
	 */
	EReference getResourceOptions_ReferencedComponent();

	/**
	 * Returns the meta object for the reference list '{@link de.tubs.areva.resourcerelations.ResourceOptions#getSpecs <em>Specs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Specs</em>'.
	 * @see de.tubs.areva.resourcerelations.ResourceOptions#getSpecs()
	 * @see #getResourceOptions()
	 * @generated
	 */
	EReference getResourceOptions_Specs();

	/**
	 * Returns the meta object for the reference list '{@link de.tubs.areva.resourcerelations.ResourceOptions#getSubGroups <em>Sub Groups</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Sub Groups</em>'.
	 * @see de.tubs.areva.resourcerelations.ResourceOptions#getSubGroups()
	 * @see #getResourceOptions()
	 * @generated
	 */
	EReference getResourceOptions_SubGroups();

	/**
	 * Returns the meta object for class '{@link de.tubs.areva.resourcerelations.Rule <em>Rule</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Rule</em>'.
	 * @see de.tubs.areva.resourcerelations.Rule
	 * @generated
	 */
	EClass getRule();

	/**
	 * Returns the meta object for the containment reference '{@link de.tubs.areva.resourcerelations.Rule#getExpression <em>Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Expression</em>'.
	 * @see de.tubs.areva.resourcerelations.Rule#getExpression()
	 * @see #getRule()
	 * @generated
	 */
	EReference getRule_Expression();

	/**
	 * Returns the meta object for the attribute '{@link de.tubs.areva.resourcerelations.Rule#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see de.tubs.areva.resourcerelations.Rule#getName()
	 * @see #getRule()
	 * @generated
	 */
	EAttribute getRule_Name();

	/**
	 * Returns the meta object for class '{@link de.tubs.areva.resourcerelations.Expression <em>Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Expression</em>'.
	 * @see de.tubs.areva.resourcerelations.Expression
	 * @generated
	 */
	EClass getExpression();

	/**
	 * Returns the meta object for class '{@link de.tubs.areva.resourcerelations.ResourceVariable <em>Resource Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Resource Variable</em>'.
	 * @see de.tubs.areva.resourcerelations.ResourceVariable
	 * @generated
	 */
	EClass getResourceVariable();

	/**
	 * Returns the meta object for the reference '{@link de.tubs.areva.resourcerelations.ResourceVariable#getResource <em>Resource</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Resource</em>'.
	 * @see de.tubs.areva.resourcerelations.ResourceVariable#getResource()
	 * @see #getResourceVariable()
	 * @generated
	 */
	EReference getResourceVariable_Resource();

	/**
	 * Returns the meta object for class '{@link de.tubs.areva.resourcerelations.Implies <em>Implies</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Implies</em>'.
	 * @see de.tubs.areva.resourcerelations.Implies
	 * @generated
	 */
	EClass getImplies();

	/**
	 * Returns the meta object for the containment reference '{@link de.tubs.areva.resourcerelations.Implies#getLeftHandSide <em>Left Hand Side</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Left Hand Side</em>'.
	 * @see de.tubs.areva.resourcerelations.Implies#getLeftHandSide()
	 * @see #getImplies()
	 * @generated
	 */
	EReference getImplies_LeftHandSide();

	/**
	 * Returns the meta object for the containment reference '{@link de.tubs.areva.resourcerelations.Implies#getRightHandSide <em>Right Hand Side</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Right Hand Side</em>'.
	 * @see de.tubs.areva.resourcerelations.Implies#getRightHandSide()
	 * @see #getImplies()
	 * @generated
	 */
	EReference getImplies_RightHandSide();

	/**
	 * Returns the meta object for the attribute '{@link de.tubs.areva.resourcerelations.Implies#isLeftHandSideModifier <em>Left Hand Side Modifier</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Left Hand Side Modifier</em>'.
	 * @see de.tubs.areva.resourcerelations.Implies#isLeftHandSideModifier()
	 * @see #getImplies()
	 * @generated
	 */
	EAttribute getImplies_LeftHandSideModifier();

	/**
	 * Returns the meta object for class '{@link de.tubs.areva.resourcerelations.Not <em>Not</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Not</em>'.
	 * @see de.tubs.areva.resourcerelations.Not
	 * @generated
	 */
	EClass getNot();

	/**
	 * Returns the meta object for the containment reference '{@link de.tubs.areva.resourcerelations.Not#getExpression <em>Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Expression</em>'.
	 * @see de.tubs.areva.resourcerelations.Not#getExpression()
	 * @see #getNot()
	 * @generated
	 */
	EReference getNot_Expression();

	/**
	 * Returns the meta object for class '{@link de.tubs.areva.resourcerelations.Or <em>Or</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Or</em>'.
	 * @see de.tubs.areva.resourcerelations.Or
	 * @generated
	 */
	EClass getOr();

	/**
	 * Returns the meta object for the containment reference '{@link de.tubs.areva.resourcerelations.Or#getLeftHandSide <em>Left Hand Side</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Left Hand Side</em>'.
	 * @see de.tubs.areva.resourcerelations.Or#getLeftHandSide()
	 * @see #getOr()
	 * @generated
	 */
	EReference getOr_LeftHandSide();

	/**
	 * Returns the meta object for the containment reference '{@link de.tubs.areva.resourcerelations.Or#getRightHandSide <em>Right Hand Side</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Right Hand Side</em>'.
	 * @see de.tubs.areva.resourcerelations.Or#getRightHandSide()
	 * @see #getOr()
	 * @generated
	 */
	EReference getOr_RightHandSide();

	/**
	 * Returns the meta object for class '{@link de.tubs.areva.resourcerelations.And <em>And</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>And</em>'.
	 * @see de.tubs.areva.resourcerelations.And
	 * @generated
	 */
	EClass getAnd();

	/**
	 * Returns the meta object for the containment reference '{@link de.tubs.areva.resourcerelations.And#getLeftHandSide <em>Left Hand Side</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Left Hand Side</em>'.
	 * @see de.tubs.areva.resourcerelations.And#getLeftHandSide()
	 * @see #getAnd()
	 * @generated
	 */
	EReference getAnd_LeftHandSide();

	/**
	 * Returns the meta object for the containment reference '{@link de.tubs.areva.resourcerelations.And#getRightHandSide <em>Right Hand Side</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Right Hand Side</em>'.
	 * @see de.tubs.areva.resourcerelations.And#getRightHandSide()
	 * @see #getAnd()
	 * @generated
	 */
	EReference getAnd_RightHandSide();

	/**
	 * Returns the meta object for class '{@link de.tubs.areva.resourcerelations.ResourceOptionsVariable <em>Resource Options Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Resource Options Variable</em>'.
	 * @see de.tubs.areva.resourcerelations.ResourceOptionsVariable
	 * @generated
	 */
	EClass getResourceOptionsVariable();

	/**
	 * Returns the meta object for the reference '{@link de.tubs.areva.resourcerelations.ResourceOptionsVariable#getResourceOptions <em>Resource Options</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Resource Options</em>'.
	 * @see de.tubs.areva.resourcerelations.ResourceOptionsVariable#getResourceOptions()
	 * @see #getResourceOptionsVariable()
	 * @generated
	 */
	EReference getResourceOptionsVariable_ResourceOptions();

	/**
	 * Returns the meta object for enum '{@link de.tubs.areva.resourcerelations.ResourceType <em>Resource Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Resource Type</em>'.
	 * @see de.tubs.areva.resourcerelations.ResourceType
	 * @generated
	 */
	EEnum getResourceType();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	ResourcerelationsFactory getResourcerelationsFactory();

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
		 * The meta object literal for the '{@link de.tubs.areva.resourcerelations.impl.ResourceImpl <em>Resource</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.tubs.areva.resourcerelations.impl.ResourceImpl
		 * @see de.tubs.areva.resourcerelations.impl.ResourcerelationsPackageImpl#getResource()
		 * @generated
		 */
		EClass RESOURCE = eINSTANCE.getResource();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RESOURCE__TYPE = eINSTANCE.getResource_Type();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RESOURCE__NAME = eINSTANCE.getResource_Name();

		/**
		 * The meta object literal for the '<em><b>Specs</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RESOURCE__SPECS = eINSTANCE.getResource_Specs();

		/**
		 * The meta object literal for the '<em><b>Interface</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RESOURCE__INTERFACE = eINSTANCE.getResource_Interface();

		/**
		 * The meta object literal for the '<em><b>Redundant</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RESOURCE__REDUNDANT = eINSTANCE.getResource_Redundant();

		/**
		 * The meta object literal for the '{@link de.tubs.areva.resourcerelations.impl.SpecificationImpl <em>Specification</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.tubs.areva.resourcerelations.impl.SpecificationImpl
		 * @see de.tubs.areva.resourcerelations.impl.ResourcerelationsPackageImpl#getSpecification()
		 * @generated
		 */
		EClass SPECIFICATION = eINSTANCE.getSpecification();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SPECIFICATION__VALUE = eINSTANCE.getSpecification_Value();

		/**
		 * The meta object literal for the '<em><b>Assigned Prop</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SPECIFICATION__ASSIGNED_PROP = eINSTANCE.getSpecification_AssignedProp();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SPECIFICATION__NAME = eINSTANCE.getSpecification_Name();

		/**
		 * The meta object literal for the '{@link de.tubs.areva.resourcerelations.impl.PlatformImpl <em>Platform</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.tubs.areva.resourcerelations.impl.PlatformImpl
		 * @see de.tubs.areva.resourcerelations.impl.ResourcerelationsPackageImpl#getPlatform()
		 * @generated
		 */
		EClass PLATFORM = eINSTANCE.getPlatform();

		/**
		 * The meta object literal for the '<em><b>Options</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PLATFORM__OPTIONS = eINSTANCE.getPlatform_Options();

		/**
		 * The meta object literal for the '<em><b>Specs</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PLATFORM__SPECS = eINSTANCE.getPlatform_Specs();

		/**
		 * The meta object literal for the '<em><b>Props</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PLATFORM__PROPS = eINSTANCE.getPlatform_Props();

		/**
		 * The meta object literal for the '<em><b>Resources</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PLATFORM__RESOURCES = eINSTANCE.getPlatform_Resources();

		/**
		 * The meta object literal for the '<em><b>Rules</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PLATFORM__RULES = eINSTANCE.getPlatform_Rules();

		/**
		 * The meta object literal for the '{@link de.tubs.areva.resourcerelations.impl.PropertyImpl <em>Property</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.tubs.areva.resourcerelations.impl.PropertyImpl
		 * @see de.tubs.areva.resourcerelations.impl.ResourcerelationsPackageImpl#getProperty()
		 * @generated
		 */
		EClass PROPERTY = eINSTANCE.getProperty();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROPERTY__NAME = eINSTANCE.getProperty_Name();

		/**
		 * The meta object literal for the '<em><b>Unit</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROPERTY__UNIT = eINSTANCE.getProperty_Unit();

		/**
		 * The meta object literal for the '{@link de.tubs.areva.resourcerelations.impl.ResourceOptionsImpl <em>Resource Options</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.tubs.areva.resourcerelations.impl.ResourceOptionsImpl
		 * @see de.tubs.areva.resourcerelations.impl.ResourcerelationsPackageImpl#getResourceOptions()
		 * @generated
		 */
		EClass RESOURCE_OPTIONS = eINSTANCE.getResourceOptions();

		/**
		 * The meta object literal for the '<em><b>Resources</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RESOURCE_OPTIONS__RESOURCES = eINSTANCE.getResourceOptions_Resources();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RESOURCE_OPTIONS__NAME = eINSTANCE.getResourceOptions_Name();

		/**
		 * The meta object literal for the '<em><b>Min Elements</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RESOURCE_OPTIONS__MIN_ELEMENTS = eINSTANCE.getResourceOptions_MinElements();

		/**
		 * The meta object literal for the '<em><b>Max Elements</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RESOURCE_OPTIONS__MAX_ELEMENTS = eINSTANCE.getResourceOptions_MaxElements();

		/**
		 * The meta object literal for the '<em><b>Referenced Component</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RESOURCE_OPTIONS__REFERENCED_COMPONENT = eINSTANCE.getResourceOptions_ReferencedComponent();

		/**
		 * The meta object literal for the '<em><b>Specs</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RESOURCE_OPTIONS__SPECS = eINSTANCE.getResourceOptions_Specs();

		/**
		 * The meta object literal for the '<em><b>Sub Groups</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RESOURCE_OPTIONS__SUB_GROUPS = eINSTANCE.getResourceOptions_SubGroups();

		/**
		 * The meta object literal for the '{@link de.tubs.areva.resourcerelations.impl.RuleImpl <em>Rule</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.tubs.areva.resourcerelations.impl.RuleImpl
		 * @see de.tubs.areva.resourcerelations.impl.ResourcerelationsPackageImpl#getRule()
		 * @generated
		 */
		EClass RULE = eINSTANCE.getRule();

		/**
		 * The meta object literal for the '<em><b>Expression</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RULE__EXPRESSION = eINSTANCE.getRule_Expression();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RULE__NAME = eINSTANCE.getRule_Name();

		/**
		 * The meta object literal for the '{@link de.tubs.areva.resourcerelations.impl.ExpressionImpl <em>Expression</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.tubs.areva.resourcerelations.impl.ExpressionImpl
		 * @see de.tubs.areva.resourcerelations.impl.ResourcerelationsPackageImpl#getExpression()
		 * @generated
		 */
		EClass EXPRESSION = eINSTANCE.getExpression();

		/**
		 * The meta object literal for the '{@link de.tubs.areva.resourcerelations.impl.ResourceVariableImpl <em>Resource Variable</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.tubs.areva.resourcerelations.impl.ResourceVariableImpl
		 * @see de.tubs.areva.resourcerelations.impl.ResourcerelationsPackageImpl#getResourceVariable()
		 * @generated
		 */
		EClass RESOURCE_VARIABLE = eINSTANCE.getResourceVariable();

		/**
		 * The meta object literal for the '<em><b>Resource</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RESOURCE_VARIABLE__RESOURCE = eINSTANCE.getResourceVariable_Resource();

		/**
		 * The meta object literal for the '{@link de.tubs.areva.resourcerelations.impl.ImpliesImpl <em>Implies</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.tubs.areva.resourcerelations.impl.ImpliesImpl
		 * @see de.tubs.areva.resourcerelations.impl.ResourcerelationsPackageImpl#getImplies()
		 * @generated
		 */
		EClass IMPLIES = eINSTANCE.getImplies();

		/**
		 * The meta object literal for the '<em><b>Left Hand Side</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IMPLIES__LEFT_HAND_SIDE = eINSTANCE.getImplies_LeftHandSide();

		/**
		 * The meta object literal for the '<em><b>Right Hand Side</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IMPLIES__RIGHT_HAND_SIDE = eINSTANCE.getImplies_RightHandSide();

		/**
		 * The meta object literal for the '<em><b>Left Hand Side Modifier</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IMPLIES__LEFT_HAND_SIDE_MODIFIER = eINSTANCE.getImplies_LeftHandSideModifier();

		/**
		 * The meta object literal for the '{@link de.tubs.areva.resourcerelations.impl.NotImpl <em>Not</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.tubs.areva.resourcerelations.impl.NotImpl
		 * @see de.tubs.areva.resourcerelations.impl.ResourcerelationsPackageImpl#getNot()
		 * @generated
		 */
		EClass NOT = eINSTANCE.getNot();

		/**
		 * The meta object literal for the '<em><b>Expression</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference NOT__EXPRESSION = eINSTANCE.getNot_Expression();

		/**
		 * The meta object literal for the '{@link de.tubs.areva.resourcerelations.impl.OrImpl <em>Or</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.tubs.areva.resourcerelations.impl.OrImpl
		 * @see de.tubs.areva.resourcerelations.impl.ResourcerelationsPackageImpl#getOr()
		 * @generated
		 */
		EClass OR = eINSTANCE.getOr();

		/**
		 * The meta object literal for the '<em><b>Left Hand Side</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OR__LEFT_HAND_SIDE = eINSTANCE.getOr_LeftHandSide();

		/**
		 * The meta object literal for the '<em><b>Right Hand Side</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OR__RIGHT_HAND_SIDE = eINSTANCE.getOr_RightHandSide();

		/**
		 * The meta object literal for the '{@link de.tubs.areva.resourcerelations.impl.AndImpl <em>And</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.tubs.areva.resourcerelations.impl.AndImpl
		 * @see de.tubs.areva.resourcerelations.impl.ResourcerelationsPackageImpl#getAnd()
		 * @generated
		 */
		EClass AND = eINSTANCE.getAnd();

		/**
		 * The meta object literal for the '<em><b>Left Hand Side</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference AND__LEFT_HAND_SIDE = eINSTANCE.getAnd_LeftHandSide();

		/**
		 * The meta object literal for the '<em><b>Right Hand Side</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference AND__RIGHT_HAND_SIDE = eINSTANCE.getAnd_RightHandSide();

		/**
		 * The meta object literal for the '{@link de.tubs.areva.resourcerelations.impl.ResourceOptionsVariableImpl <em>Resource Options Variable</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.tubs.areva.resourcerelations.impl.ResourceOptionsVariableImpl
		 * @see de.tubs.areva.resourcerelations.impl.ResourcerelationsPackageImpl#getResourceOptionsVariable()
		 * @generated
		 */
		EClass RESOURCE_OPTIONS_VARIABLE = eINSTANCE.getResourceOptionsVariable();

		/**
		 * The meta object literal for the '<em><b>Resource Options</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RESOURCE_OPTIONS_VARIABLE__RESOURCE_OPTIONS = eINSTANCE.getResourceOptionsVariable_ResourceOptions();

		/**
		 * The meta object literal for the '{@link de.tubs.areva.resourcerelations.ResourceType <em>Resource Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.tubs.areva.resourcerelations.ResourceType
		 * @see de.tubs.areva.resourcerelations.impl.ResourcerelationsPackageImpl#getResourceType()
		 * @generated
		 */
		EEnum RESOURCE_TYPE = eINSTANCE.getResourceType();

	}

} //ResourcerelationsPackage
