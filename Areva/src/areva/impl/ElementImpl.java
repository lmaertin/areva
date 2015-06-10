/**
 */
package areva.impl;

import areva.ArevaPackage;
import areva.Element;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Element</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link areva.impl.ElementImpl#getName <em>Name</em>}</li>
 *   <li>{@link areva.impl.ElementImpl#getPercentage <em>Percentage</em>}</li>
 *   <li>{@link areva.impl.ElementImpl#getRating <em>Rating</em>}</li>
 *   <li>{@link areva.impl.ElementImpl#getQuality <em>Quality</em>}</li>
 *   <li>{@link areva.impl.ElementImpl#getMetric <em>Metric</em>}</li>
 *   <li>{@link areva.impl.ElementImpl#getQualityRating <em>Quality Rating</em>}</li>
 *   <li>{@link areva.impl.ElementImpl#getUnit <em>Unit</em>}</li>
 *   <li>{@link areva.impl.ElementImpl#getMustHave <em>Must Have</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ElementImpl extends MinimalEObjectImpl.Container implements Element {
	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = "";
	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;
	/**
	 * The default value of the '{@link #getPercentage() <em>Percentage</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPercentage()
	 * @generated
	 * @ordered
	 */
	protected static final double PERCENTAGE_EDEFAULT = 0.0;
	/**
	 * The cached value of the '{@link #getPercentage() <em>Percentage</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPercentage()
	 * @generated
	 * @ordered
	 */
	protected double percentage = PERCENTAGE_EDEFAULT;
	/**
	 * The default value of the '{@link #getRating() <em>Rating</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRating()
	 * @generated
	 * @ordered
	 */
	protected static final double RATING_EDEFAULT = 0.0;
	/**
	 * The cached value of the '{@link #getRating() <em>Rating</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRating()
	 * @generated
	 * @ordered
	 */
	protected double rating = RATING_EDEFAULT;
	/**
	 * The default value of the '{@link #getQuality() <em>Quality</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getQuality()
	 * @generated
	 * @ordered
	 */
	protected static final double QUALITY_EDEFAULT = 0.0;
	/**
	 * The cached value of the '{@link #getQuality() <em>Quality</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getQuality()
	 * @generated
	 * @ordered
	 */
	protected double quality = QUALITY_EDEFAULT;
	/**
	 * The default value of the '{@link #getMetric() <em>Metric</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMetric()
	 * @generated
	 * @ordered
	 */
	protected static final String METRIC_EDEFAULT = "";
	/**
	 * The cached value of the '{@link #getMetric() <em>Metric</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMetric()
	 * @generated
	 * @ordered
	 */
	protected String metric = METRIC_EDEFAULT;
	/**
	 * The default value of the '{@link #getQualityRating() <em>Quality Rating</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getQualityRating()
	 * @generated
	 * @ordered
	 */
	protected static final String QUALITY_RATING_EDEFAULT = "";
	/**
	 * The cached value of the '{@link #getQualityRating() <em>Quality Rating</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getQualityRating()
	 * @generated
	 * @ordered
	 */
	protected String qualityRating = QUALITY_RATING_EDEFAULT;
	/**
	 * The default value of the '{@link #getUnit() <em>Unit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUnit()
	 * @generated
	 * @ordered
	 */
	protected static final String UNIT_EDEFAULT = null;
	/**
	 * The cached value of the '{@link #getUnit() <em>Unit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUnit()
	 * @generated
	 * @ordered
	 */
	protected String unit = UNIT_EDEFAULT;

	/**
	 * The default value of the '{@link #getMustHave() <em>Must Have</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMustHave()
	 * @generated
	 * @ordered
	 */
	protected static final double MUST_HAVE_EDEFAULT = 0.0;
	/**
	 * The cached value of the '{@link #getMustHave() <em>Must Have</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMustHave()
	 * @generated
	 * @ordered
	 */
	protected double mustHave = MUST_HAVE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ElementImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ArevaPackage.Literals.ELEMENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ArevaPackage.ELEMENT__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getPercentage() {
		return percentage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPercentage(double newPercentage) {
		double oldPercentage = percentage;
		percentage = newPercentage;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ArevaPackage.ELEMENT__PERCENTAGE, oldPercentage, percentage));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getRating() {
		return rating;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRating(double newRating) {
		double oldRating = rating;
		rating = newRating;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ArevaPackage.ELEMENT__RATING, oldRating, rating));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getQuality() {
		return quality;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setQuality(double newQuality) {
		double oldQuality = quality;
		quality = newQuality;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ArevaPackage.ELEMENT__QUALITY, oldQuality, quality));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getMetric() {
		return metric;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMetric(String newMetric) {
		String oldMetric = metric;
		metric = newMetric;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ArevaPackage.ELEMENT__METRIC, oldMetric, metric));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getQualityRating() {
		return qualityRating;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setQualityRating(String newQualityRating) {
		String oldQualityRating = qualityRating;
		qualityRating = newQualityRating;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ArevaPackage.ELEMENT__QUALITY_RATING, oldQualityRating, qualityRating));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getUnit() {
		return unit;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUnit(String newUnit) {
		String oldUnit = unit;
		unit = newUnit;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ArevaPackage.ELEMENT__UNIT, oldUnit, unit));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getMustHave() {
		return mustHave;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMustHave(double newMustHave) {
		double oldMustHave = mustHave;
		mustHave = newMustHave;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ArevaPackage.ELEMENT__MUST_HAVE, oldMustHave, mustHave));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ArevaPackage.ELEMENT__NAME:
				return getName();
			case ArevaPackage.ELEMENT__PERCENTAGE:
				return getPercentage();
			case ArevaPackage.ELEMENT__RATING:
				return getRating();
			case ArevaPackage.ELEMENT__QUALITY:
				return getQuality();
			case ArevaPackage.ELEMENT__METRIC:
				return getMetric();
			case ArevaPackage.ELEMENT__QUALITY_RATING:
				return getQualityRating();
			case ArevaPackage.ELEMENT__UNIT:
				return getUnit();
			case ArevaPackage.ELEMENT__MUST_HAVE:
				return getMustHave();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ArevaPackage.ELEMENT__NAME:
				setName((String)newValue);
				return;
			case ArevaPackage.ELEMENT__PERCENTAGE:
				setPercentage((Double)newValue);
				return;
			case ArevaPackage.ELEMENT__RATING:
				setRating((Double)newValue);
				return;
			case ArevaPackage.ELEMENT__QUALITY:
				setQuality((Double)newValue);
				return;
			case ArevaPackage.ELEMENT__METRIC:
				setMetric((String)newValue);
				return;
			case ArevaPackage.ELEMENT__QUALITY_RATING:
				setQualityRating((String)newValue);
				return;
			case ArevaPackage.ELEMENT__UNIT:
				setUnit((String)newValue);
				return;
			case ArevaPackage.ELEMENT__MUST_HAVE:
				setMustHave((Double)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case ArevaPackage.ELEMENT__NAME:
				setName(NAME_EDEFAULT);
				return;
			case ArevaPackage.ELEMENT__PERCENTAGE:
				setPercentage(PERCENTAGE_EDEFAULT);
				return;
			case ArevaPackage.ELEMENT__RATING:
				setRating(RATING_EDEFAULT);
				return;
			case ArevaPackage.ELEMENT__QUALITY:
				setQuality(QUALITY_EDEFAULT);
				return;
			case ArevaPackage.ELEMENT__METRIC:
				setMetric(METRIC_EDEFAULT);
				return;
			case ArevaPackage.ELEMENT__QUALITY_RATING:
				setQualityRating(QUALITY_RATING_EDEFAULT);
				return;
			case ArevaPackage.ELEMENT__UNIT:
				setUnit(UNIT_EDEFAULT);
				return;
			case ArevaPackage.ELEMENT__MUST_HAVE:
				setMustHave(MUST_HAVE_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case ArevaPackage.ELEMENT__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case ArevaPackage.ELEMENT__PERCENTAGE:
				return percentage != PERCENTAGE_EDEFAULT;
			case ArevaPackage.ELEMENT__RATING:
				return rating != RATING_EDEFAULT;
			case ArevaPackage.ELEMENT__QUALITY:
				return quality != QUALITY_EDEFAULT;
			case ArevaPackage.ELEMENT__METRIC:
				return METRIC_EDEFAULT == null ? metric != null : !METRIC_EDEFAULT.equals(metric);
			case ArevaPackage.ELEMENT__QUALITY_RATING:
				return QUALITY_RATING_EDEFAULT == null ? qualityRating != null : !QUALITY_RATING_EDEFAULT.equals(qualityRating);
			case ArevaPackage.ELEMENT__UNIT:
				return UNIT_EDEFAULT == null ? unit != null : !UNIT_EDEFAULT.equals(unit);
			case ArevaPackage.ELEMENT__MUST_HAVE:
				return mustHave != MUST_HAVE_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (name: ");
		result.append(name);
		result.append(", percentage: ");
		result.append(percentage);
		result.append(", rating: ");
		result.append(rating);
		result.append(", quality: ");
		result.append(quality);
		result.append(", metric: ");
		result.append(metric);
		result.append(", qualityRating: ");
		result.append(qualityRating);
		result.append(", unit: ");
		result.append(unit);
		result.append(", mustHave: ");
		result.append(mustHave);
		result.append(')');
		return result.toString();
	}

} //ElementImpl
