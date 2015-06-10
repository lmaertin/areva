package areva.diagram.part;

import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.gmf.tooling.runtime.structure.DiagramStructure;

import areva.ArevaPackage;
import areva.Evaluations;
import areva.diagram.edit.parts.CategoryEditPart;
import areva.diagram.edit.parts.CategoryNameEditPart;
import areva.diagram.edit.parts.EvaluationEditPart;
import areva.diagram.edit.parts.EvaluationNameEditPart;
import areva.diagram.edit.parts.EvaluationsEditPart;

/**
 * This registry is used to determine which type of visual object should be
 * created for the corresponding Diagram, Node, ChildNode or Link represented
 * by a domain model object.
 * 
 * @generated
 */
public class ArevaVisualIDRegistry {

	/**
	 * @generated
	 */
	private static final String DEBUG_KEY = "Areva.diagram/debug/visualID"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static int getVisualID(View view) {
		if (view instanceof Diagram) {
			if (EvaluationsEditPart.MODEL_ID.equals(view.getType())) {
				return EvaluationsEditPart.VISUAL_ID;
			} else {
				return -1;
			}
		}
		return areva.diagram.part.ArevaVisualIDRegistry.getVisualID(view
				.getType());
	}

	/**
	 * @generated
	 */
	public static String getModelID(View view) {
		View diagram = view.getDiagram();
		while (view != diagram) {
			EAnnotation annotation = view.getEAnnotation("Shortcut"); //$NON-NLS-1$
			if (annotation != null) {
				return (String) annotation.getDetails().get("modelID"); //$NON-NLS-1$
			}
			view = (View) view.eContainer();
		}
		return diagram != null ? diagram.getType() : null;
	}

	/**
	 * @generated
	 */
	public static int getVisualID(String type) {
		try {
			return Integer.parseInt(type);
		} catch (NumberFormatException e) {
			if (Boolean.TRUE.toString().equalsIgnoreCase(
					Platform.getDebugOption(DEBUG_KEY))) {
				ArevaDiagramEditorPlugin.getInstance().logError(
						"Unable to parse view type as a visualID number: "
								+ type);
			}
		}
		return -1;
	}

	/**
	 * @generated
	 */
	public static String getType(int visualID) {
		return Integer.toString(visualID);
	}

	/**
	 * @generated
	 */
	public static int getDiagramVisualID(EObject domainElement) {
		if (domainElement == null) {
			return -1;
		}
		if (ArevaPackage.eINSTANCE.getEvaluations().isSuperTypeOf(
				domainElement.eClass())
				&& isDiagram((Evaluations) domainElement)) {
			return EvaluationsEditPart.VISUAL_ID;
		}
		return -1;
	}

	/**
	 * @generated
	 */
	public static int getNodeVisualID(View containerView, EObject domainElement) {
		if (domainElement == null) {
			return -1;
		}
		String containerModelID = areva.diagram.part.ArevaVisualIDRegistry
				.getModelID(containerView);
		if (!EvaluationsEditPart.MODEL_ID.equals(containerModelID)) {
			return -1;
		}
		int containerVisualID;
		if (EvaluationsEditPart.MODEL_ID.equals(containerModelID)) {
			containerVisualID = areva.diagram.part.ArevaVisualIDRegistry
					.getVisualID(containerView);
		} else {
			if (containerView instanceof Diagram) {
				containerVisualID = EvaluationsEditPart.VISUAL_ID;
			} else {
				return -1;
			}
		}
		switch (containerVisualID) {
		case EvaluationsEditPart.VISUAL_ID:
			if (ArevaPackage.eINSTANCE.getEvaluation().isSuperTypeOf(
					domainElement.eClass())) {
				return EvaluationEditPart.VISUAL_ID;
			}
			break;
		}
		return -1;
	}

	/**
	 * @generated
	 */
	public static boolean canCreateNode(View containerView, int nodeVisualID) {
		String containerModelID = areva.diagram.part.ArevaVisualIDRegistry
				.getModelID(containerView);
		if (!EvaluationsEditPart.MODEL_ID.equals(containerModelID)) {
			return false;
		}
		int containerVisualID;
		if (EvaluationsEditPart.MODEL_ID.equals(containerModelID)) {
			containerVisualID = areva.diagram.part.ArevaVisualIDRegistry
					.getVisualID(containerView);
		} else {
			if (containerView instanceof Diagram) {
				containerVisualID = EvaluationsEditPart.VISUAL_ID;
			} else {
				return false;
			}
		}
		switch (containerVisualID) {
		case EvaluationsEditPart.VISUAL_ID:
			if (EvaluationEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case EvaluationEditPart.VISUAL_ID:
			if (EvaluationNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case CategoryEditPart.VISUAL_ID:
			if (CategoryNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		}
		return false;
	}

	/**
	 * @generated
	 */
	public static int getLinkWithClassVisualID(EObject domainElement) {
		if (domainElement == null) {
			return -1;
		}
		if (ArevaPackage.eINSTANCE.getCategory().isSuperTypeOf(
				domainElement.eClass())) {
			return CategoryEditPart.VISUAL_ID;
		}
		return -1;
	}

	/**
	 * User can change implementation of this method to handle some specific
	 * situations not covered by default logic.
	 * 
	 * @generated
	 */
	private static boolean isDiagram(Evaluations element) {
		return true;
	}

	/**
	 * @generated
	 */
	public static boolean checkNodeVisualID(View containerView,
			EObject domainElement, int candidate) {
		if (candidate == -1) {
			//unrecognized id is always bad
			return false;
		}
		int basic = getNodeVisualID(containerView, domainElement);
		return basic == candidate;
	}

	/**
	 * @generated
	 */
	public static boolean isCompartmentVisualID(int visualID) {
		return false;
	}

	/**
	 * @generated
	 */
	public static boolean isSemanticLeafVisualID(int visualID) {
		switch (visualID) {
		case EvaluationsEditPart.VISUAL_ID:
			return false;
		case EvaluationEditPart.VISUAL_ID:
			return true;
		default:
			break;
		}
		return false;
	}

	/**
	 * @generated
	 */
	public static final DiagramStructure TYPED_INSTANCE = new DiagramStructure() {
		/**
		 * @generated
		 */
		@Override
		public int getVisualID(View view) {
			return areva.diagram.part.ArevaVisualIDRegistry.getVisualID(view);
		}

		/**
		 * @generated
		 */
		@Override
		public String getModelID(View view) {
			return areva.diagram.part.ArevaVisualIDRegistry.getModelID(view);
		}

		/**
		 * @generated
		 */
		@Override
		public int getNodeVisualID(View containerView, EObject domainElement) {
			return areva.diagram.part.ArevaVisualIDRegistry.getNodeVisualID(
					containerView, domainElement);
		}

		/**
		 * @generated
		 */
		@Override
		public boolean checkNodeVisualID(View containerView,
				EObject domainElement, int candidate) {
			return areva.diagram.part.ArevaVisualIDRegistry.checkNodeVisualID(
					containerView, domainElement, candidate);
		}

		/**
		 * @generated
		 */
		@Override
		public boolean isCompartmentVisualID(int visualID) {
			return areva.diagram.part.ArevaVisualIDRegistry
					.isCompartmentVisualID(visualID);
		}

		/**
		 * @generated
		 */
		@Override
		public boolean isSemanticLeafVisualID(int visualID) {
			return areva.diagram.part.ArevaVisualIDRegistry
					.isSemanticLeafVisualID(visualID);
		}
	};

}
