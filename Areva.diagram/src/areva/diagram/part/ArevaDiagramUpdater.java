package areva.diagram.part;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import java.util.Map;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.gmf.tooling.runtime.update.DiagramUpdater;

import areva.ArevaPackage;
import areva.Category;
import areva.Evaluation;
import areva.Evaluations;
import areva.diagram.edit.parts.CategoryEditPart;
import areva.diagram.edit.parts.EvaluationEditPart;
import areva.diagram.edit.parts.EvaluationsEditPart;
import areva.diagram.providers.ArevaElementTypes;

/**
 * @generated
 */
public class ArevaDiagramUpdater {

	/**
	 * @generated
	 */
	public static List<ArevaNodeDescriptor> getSemanticChildren(View view) {
		switch (ArevaVisualIDRegistry.getVisualID(view)) {
		case EvaluationsEditPart.VISUAL_ID:
			return getEvaluations_1000SemanticChildren(view);
		}
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ArevaNodeDescriptor> getEvaluations_1000SemanticChildren(
			View view) {
		if (!view.isSetElement()) {
			return Collections.emptyList();
		}
		Evaluations modelElement = (Evaluations) view.getElement();
		LinkedList<ArevaNodeDescriptor> result = new LinkedList<ArevaNodeDescriptor>();
		for (Iterator<?> it = modelElement.getEvaluations().iterator(); it
				.hasNext();) {
			Evaluation childElement = (Evaluation) it.next();
			int visualID = ArevaVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == EvaluationEditPart.VISUAL_ID) {
				result.add(new ArevaNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ArevaLinkDescriptor> getContainedLinks(View view) {
		switch (ArevaVisualIDRegistry.getVisualID(view)) {
		case EvaluationsEditPart.VISUAL_ID:
			return getEvaluations_1000ContainedLinks(view);
		case EvaluationEditPart.VISUAL_ID:
			return getEvaluation_2001ContainedLinks(view);
		case CategoryEditPart.VISUAL_ID:
			return getCategory_4001ContainedLinks(view);
		}
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ArevaLinkDescriptor> getIncomingLinks(View view) {
		switch (ArevaVisualIDRegistry.getVisualID(view)) {
		case EvaluationEditPart.VISUAL_ID:
			return getEvaluation_2001IncomingLinks(view);
		case CategoryEditPart.VISUAL_ID:
			return getCategory_4001IncomingLinks(view);
		}
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ArevaLinkDescriptor> getOutgoingLinks(View view) {
		switch (ArevaVisualIDRegistry.getVisualID(view)) {
		case EvaluationEditPart.VISUAL_ID:
			return getEvaluation_2001OutgoingLinks(view);
		case CategoryEditPart.VISUAL_ID:
			return getCategory_4001OutgoingLinks(view);
		}
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ArevaLinkDescriptor> getEvaluations_1000ContainedLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ArevaLinkDescriptor> getEvaluation_2001ContainedLinks(
			View view) {
		Evaluation modelElement = (Evaluation) view.getElement();
		LinkedList<ArevaLinkDescriptor> result = new LinkedList<ArevaLinkDescriptor>();
		result.addAll(getContainedTypeModelFacetLinks_Category_4001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ArevaLinkDescriptor> getCategory_4001ContainedLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ArevaLinkDescriptor> getEvaluation_2001IncomingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ArevaLinkDescriptor> getCategory_4001IncomingLinks(
			View view) {
		Category modelElement = (Category) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<ArevaLinkDescriptor> result = new LinkedList<ArevaLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_Category_4001(
				modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ArevaLinkDescriptor> getEvaluation_2001OutgoingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ArevaLinkDescriptor> getCategory_4001OutgoingLinks(
			View view) {
		Category modelElement = (Category) view.getElement();
		LinkedList<ArevaLinkDescriptor> result = new LinkedList<ArevaLinkDescriptor>();
		result.addAll(getOutgoingTypeModelFacetLinks_Category_4001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<ArevaLinkDescriptor> getContainedTypeModelFacetLinks_Category_4001(
			Evaluation container) {
		LinkedList<ArevaLinkDescriptor> result = new LinkedList<ArevaLinkDescriptor>();
		for (Iterator<?> links = container.getCategories().iterator(); links
				.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof Category) {
				continue;
			}
			Category link = (Category) linkObject;
			if (CategoryEditPart.VISUAL_ID != ArevaVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			List targets = link.getCategories();
			Object theTarget = targets.size() == 1 ? targets.get(0) : null;
			if (false == theTarget instanceof Category) {
				continue;
			}
			Category dst = (Category) theTarget;
			List sources = link.getCategories();
			Object theSource = sources.size() == 1 ? sources.get(0) : null;
			if (false == theSource instanceof Category) {
				continue;
			}
			Category src = (Category) theSource;
			result.add(new ArevaLinkDescriptor(src, dst, link,
					ArevaElementTypes.Category_4001, CategoryEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<ArevaLinkDescriptor> getIncomingTypeModelFacetLinks_Category_4001(
			Category target,
			Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences) {
		LinkedList<ArevaLinkDescriptor> result = new LinkedList<ArevaLinkDescriptor>();
		Collection<EStructuralFeature.Setting> settings = crossReferences
				.get(target);
		for (EStructuralFeature.Setting setting : settings) {
			if (setting.getEStructuralFeature() != ArevaPackage.eINSTANCE
					.getCategory_Categories()
					|| false == setting.getEObject() instanceof Category) {
				continue;
			}
			Category link = (Category) setting.getEObject();
			if (CategoryEditPart.VISUAL_ID != ArevaVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			List sources = link.getCategories();
			Object theSource = sources.size() == 1 ? sources.get(0) : null;
			if (false == theSource instanceof Category) {
				continue;
			}
			Category src = (Category) theSource;
			result.add(new ArevaLinkDescriptor(src, target, link,
					ArevaElementTypes.Category_4001, CategoryEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<ArevaLinkDescriptor> getOutgoingTypeModelFacetLinks_Category_4001(
			Category source) {
		Evaluation container = null;
		// Find container element for the link.
		// Climb up by containment hierarchy starting from the source
		// and return the first element that is instance of the container class.
		for (EObject element = source; element != null && container == null; element = element
				.eContainer()) {
			if (element instanceof Evaluation) {
				container = (Evaluation) element;
			}
		}
		if (container == null) {
			return Collections.emptyList();
		}
		LinkedList<ArevaLinkDescriptor> result = new LinkedList<ArevaLinkDescriptor>();
		for (Iterator<?> links = container.getCategories().iterator(); links
				.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof Category) {
				continue;
			}
			Category link = (Category) linkObject;
			if (CategoryEditPart.VISUAL_ID != ArevaVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			List targets = link.getCategories();
			Object theTarget = targets.size() == 1 ? targets.get(0) : null;
			if (false == theTarget instanceof Category) {
				continue;
			}
			Category dst = (Category) theTarget;
			List sources = link.getCategories();
			Object theSource = sources.size() == 1 ? sources.get(0) : null;
			if (false == theSource instanceof Category) {
				continue;
			}
			Category src = (Category) theSource;
			if (src != source) {
				continue;
			}
			result.add(new ArevaLinkDescriptor(src, dst, link,
					ArevaElementTypes.Category_4001, CategoryEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static final DiagramUpdater TYPED_INSTANCE = new DiagramUpdater() {
		/**
		 * @generated
		 */
		@Override
		public List<ArevaNodeDescriptor> getSemanticChildren(View view) {
			return ArevaDiagramUpdater.getSemanticChildren(view);
		}

		/**
		 * @generated
		 */
		@Override
		public List<ArevaLinkDescriptor> getContainedLinks(View view) {
			return ArevaDiagramUpdater.getContainedLinks(view);
		}

		/**
		 * @generated
		 */
		@Override
		public List<ArevaLinkDescriptor> getIncomingLinks(View view) {
			return ArevaDiagramUpdater.getIncomingLinks(view);
		}

		/**
		 * @generated
		 */
		@Override
		public List<ArevaLinkDescriptor> getOutgoingLinks(View view) {
			return ArevaDiagramUpdater.getOutgoingLinks(view);
		}
	};

}
