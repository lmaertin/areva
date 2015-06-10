package areva.diagram.edit.parts;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;
import org.eclipse.gef.tools.CellEditorLocator;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ITextAwareEditPart;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.gmf.tooling.runtime.directedit.locator.CellEditorLocatorAccess;

import areva.diagram.part.ArevaVisualIDRegistry;

/**
 * @generated
 */
public class ArevaEditPartFactory implements EditPartFactory {

	/**
	 * @generated
	 */
	public EditPart createEditPart(EditPart context, Object model) {
		if (model instanceof View) {
			View view = (View) model;
			switch (ArevaVisualIDRegistry.getVisualID(view)) {

			case EvaluationsEditPart.VISUAL_ID:
				return new EvaluationsEditPart(view);

			case EvaluationEditPart.VISUAL_ID:
				return new EvaluationEditPart(view);

			case EvaluationNameEditPart.VISUAL_ID:
				return new EvaluationNameEditPart(view);

			case CategoryEditPart.VISUAL_ID:
				return new CategoryEditPart(view);

			case CategoryNameEditPart.VISUAL_ID:
				return new CategoryNameEditPart(view);

			}
		}
		return createUnrecognizedEditPart(context, model);
	}

	/**
	 * @generated
	 */
	private EditPart createUnrecognizedEditPart(EditPart context, Object model) {
		// Handle creation of unrecognized child node EditParts here
		return null;
	}

	/**
	 * @generated
	 */
	public static CellEditorLocator getTextCellEditorLocator(
			ITextAwareEditPart source) {
		return CellEditorLocatorAccess.INSTANCE
				.getTextCellEditorLocator(source);
	}

}
