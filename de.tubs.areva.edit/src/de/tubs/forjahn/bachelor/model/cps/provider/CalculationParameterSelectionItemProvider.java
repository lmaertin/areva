/**
 */
package de.tubs.forjahn.bachelor.model.cps.provider;


import de.tubs.forjahn.bachelor.model.cps.CalculationParameterSelection;
import de.tubs.forjahn.bachelor.model.cps.CpsPackage;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.ResourceLocator;

import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.provider.ViewerNotification;

/**
 * This is the item provider adapter for a {@link de.tubs.forjahn.bachelor.model.cps.CalculationParameterSelection} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class CalculationParameterSelectionItemProvider 
	extends ItemProviderAdapter
	implements
		IEditingDomainItemProvider,
		IStructuredItemContentProvider,
		ITreeItemContentProvider,
		IItemLabelProvider,
		IItemPropertySource {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CalculationParameterSelectionItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This returns the property descriptors for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object) {
		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);

			addSelectedDomainsPropertyDescriptor(object);
			addSelectedResourceFailuresPropertyDescriptor(object);
			addQualityWeightPropertyDescriptor(object);
			addCentralityWeightPropertyDescriptor(object);
			addResourceWeightPropertyDescriptor(object);
			addDifferenceThresholdPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Selected Domains feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addSelectedDomainsPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_CalculationParameterSelection_SelectedDomains_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_CalculationParameterSelection_SelectedDomains_feature", "_UI_CalculationParameterSelection_type"),
				 CpsPackage.Literals.CALCULATION_PARAMETER_SELECTION__SELECTED_DOMAINS,
				 true,
				 false,
				 true,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Selected Resource Failures feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addSelectedResourceFailuresPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_CalculationParameterSelection_SelectedResourceFailures_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_CalculationParameterSelection_SelectedResourceFailures_feature", "_UI_CalculationParameterSelection_type"),
				 CpsPackage.Literals.CALCULATION_PARAMETER_SELECTION__SELECTED_RESOURCE_FAILURES,
				 true,
				 false,
				 true,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Quality Weight feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addQualityWeightPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_CalculationParameterSelection_QualityWeight_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_CalculationParameterSelection_QualityWeight_feature", "_UI_CalculationParameterSelection_type"),
				 CpsPackage.Literals.CALCULATION_PARAMETER_SELECTION__QUALITY_WEIGHT,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.REAL_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Centrality Weight feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addCentralityWeightPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_CalculationParameterSelection_CentralityWeight_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_CalculationParameterSelection_CentralityWeight_feature", "_UI_CalculationParameterSelection_type"),
				 CpsPackage.Literals.CALCULATION_PARAMETER_SELECTION__CENTRALITY_WEIGHT,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.REAL_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Resource Weight feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addResourceWeightPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_CalculationParameterSelection_ResourceWeight_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_CalculationParameterSelection_ResourceWeight_feature", "_UI_CalculationParameterSelection_type"),
				 CpsPackage.Literals.CALCULATION_PARAMETER_SELECTION__RESOURCE_WEIGHT,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.REAL_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Difference Threshold feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addDifferenceThresholdPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_CalculationParameterSelection_DifferenceThreshold_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_CalculationParameterSelection_DifferenceThreshold_feature", "_UI_CalculationParameterSelection_type"),
				 CpsPackage.Literals.CALCULATION_PARAMETER_SELECTION__DIFFERENCE_THRESHOLD,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.INTEGRAL_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This returns CalculationParameterSelection.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/CalculationParameterSelection"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		CalculationParameterSelection calculationParameterSelection = (CalculationParameterSelection)object;
		return getString("_UI_CalculationParameterSelection_type") + " " + calculationParameterSelection.getQualityWeight();
	}
	

	/**
	 * This handles model notifications by calling {@link #updateChildren} to update any cached
	 * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void notifyChanged(Notification notification) {
		updateChildren(notification);

		switch (notification.getFeatureID(CalculationParameterSelection.class)) {
			case CpsPackage.CALCULATION_PARAMETER_SELECTION__QUALITY_WEIGHT:
			case CpsPackage.CALCULATION_PARAMETER_SELECTION__CENTRALITY_WEIGHT:
			case CpsPackage.CALCULATION_PARAMETER_SELECTION__RESOURCE_WEIGHT:
			case CpsPackage.CALCULATION_PARAMETER_SELECTION__DIFFERENCE_THRESHOLD:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
				return;
		}
		super.notifyChanged(notification);
	}

	/**
	 * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children
	 * that can be created under this object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
		super.collectNewChildDescriptors(newChildDescriptors, object);
	}

	/**
	 * Return the resource locator for this item provider's resources.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ResourceLocator getResourceLocator() {
		return CpsEditPlugin.INSTANCE;
	}

}
