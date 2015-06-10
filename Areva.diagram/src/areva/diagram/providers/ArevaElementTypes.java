package areva.diagram.providers;

import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.gmf.runtime.emf.type.core.ElementTypeRegistry;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.tooling.runtime.providers.DiagramElementTypeImages;
import org.eclipse.gmf.tooling.runtime.providers.DiagramElementTypes;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;

import areva.ArevaPackage;
import areva.diagram.edit.parts.CategoryEditPart;
import areva.diagram.edit.parts.EvaluationEditPart;
import areva.diagram.edit.parts.EvaluationsEditPart;
import areva.diagram.part.ArevaDiagramEditorPlugin;

/**
 * @generated
 */
public class ArevaElementTypes {

	/**
	 * @generated
	 */
	private ArevaElementTypes() {
	}

	/**
	 * @generated
	 */
	private static Map<IElementType, ENamedElement> elements;

	/**
	 * @generated
	 */
	private static DiagramElementTypeImages elementTypeImages = new DiagramElementTypeImages(
			ArevaDiagramEditorPlugin.getInstance()
					.getItemProvidersAdapterFactory());

	/**
	 * @generated
	 */
	private static Set<IElementType> KNOWN_ELEMENT_TYPES;

	/**
	 * @generated
	 */
	public static final IElementType Evaluations_1000 = getElementType("Areva.diagram.Evaluations_1000"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType Evaluation_2001 = getElementType("Areva.diagram.Evaluation_2001"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType Category_4001 = getElementType("Areva.diagram.Category_4001"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static ImageDescriptor getImageDescriptor(ENamedElement element) {
		return elementTypeImages.getImageDescriptor(element);
	}

	/**
	 * @generated
	 */
	public static Image getImage(ENamedElement element) {
		return elementTypeImages.getImage(element);
	}

	/**
	 * @generated
	 */
	public static ImageDescriptor getImageDescriptor(IAdaptable hint) {
		return getImageDescriptor(getElement(hint));
	}

	/**
	 * @generated
	 */
	public static Image getImage(IAdaptable hint) {
		return getImage(getElement(hint));
	}

	/**
	 * Returns 'type' of the ecore object associated with the hint.
	 * 
	 * @generated
	 */
	public static ENamedElement getElement(IAdaptable hint) {
		Object type = hint.getAdapter(IElementType.class);
		if (elements == null) {
			elements = new IdentityHashMap<IElementType, ENamedElement>();

			elements.put(Evaluations_1000,
					ArevaPackage.eINSTANCE.getEvaluations());

			elements.put(Evaluation_2001,
					ArevaPackage.eINSTANCE.getEvaluation());

			elements.put(Category_4001, ArevaPackage.eINSTANCE.getCategory());
		}
		return (ENamedElement) elements.get(type);
	}

	/**
	 * @generated
	 */
	private static IElementType getElementType(String id) {
		return ElementTypeRegistry.getInstance().getType(id);
	}

	/**
	 * @generated
	 */
	public static boolean isKnownElementType(IElementType elementType) {
		if (KNOWN_ELEMENT_TYPES == null) {
			KNOWN_ELEMENT_TYPES = new HashSet<IElementType>();
			KNOWN_ELEMENT_TYPES.add(Evaluations_1000);
			KNOWN_ELEMENT_TYPES.add(Evaluation_2001);
			KNOWN_ELEMENT_TYPES.add(Category_4001);
		}
		return KNOWN_ELEMENT_TYPES.contains(elementType);
	}

	/**
	 * @generated
	 */
	public static IElementType getElementType(int visualID) {
		switch (visualID) {
		case EvaluationsEditPart.VISUAL_ID:
			return Evaluations_1000;
		case EvaluationEditPart.VISUAL_ID:
			return Evaluation_2001;
		case CategoryEditPart.VISUAL_ID:
			return Category_4001;
		}
		return null;
	}

	/**
	 * @generated
	 */
	public static final DiagramElementTypes TYPED_INSTANCE = new DiagramElementTypes(
			elementTypeImages) {

		/**
		 * @generated
		 */
		@Override
		public boolean isKnownElementType(IElementType elementType) {
			return areva.diagram.providers.ArevaElementTypes
					.isKnownElementType(elementType);
		}

		/**
		 * @generated
		 */
		@Override
		public IElementType getElementTypeForVisualId(int visualID) {
			return areva.diagram.providers.ArevaElementTypes
					.getElementType(visualID);
		}

		/**
		 * @generated
		 */
		@Override
		public ENamedElement getDefiningNamedElement(
				IAdaptable elementTypeAdapter) {
			return areva.diagram.providers.ArevaElementTypes
					.getElement(elementTypeAdapter);
		}
	};

}
