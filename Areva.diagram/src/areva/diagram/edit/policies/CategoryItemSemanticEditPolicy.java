package areva.diagram.edit.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.emf.type.core.commands.DestroyElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateRelationshipRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.ReorientRelationshipRequest;

import areva.diagram.edit.commands.CategoryCreateCommand;
import areva.diagram.edit.commands.CategoryReorientCommand;
import areva.diagram.edit.parts.CategoryEditPart;
import areva.diagram.providers.ArevaElementTypes;

/**
 * @generated
 */
public class CategoryItemSemanticEditPolicy extends
		ArevaBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	public CategoryItemSemanticEditPolicy() {
		super(ArevaElementTypes.Category_4001);
	}

	/**
	 * @generated
	 */
	protected Command getDestroyElementCommand(DestroyElementRequest req) {
		return getGEFWrapper(new DestroyElementCommand(req));
	}

	/**
	 * @generated
	 */
	protected Command getCreateRelationshipCommand(CreateRelationshipRequest req) {
		Command command = req.getTarget() == null ? getStartCreateRelationshipCommand(req)
				: getCompleteCreateRelationshipCommand(req);
		return command != null ? command : super
				.getCreateRelationshipCommand(req);
	}

	/**
	 * @generated
	 */
	protected Command getStartCreateRelationshipCommand(
			CreateRelationshipRequest req) {
		if (ArevaElementTypes.Category_4001 == req.getElementType()) {
			return getGEFWrapper(new CategoryCreateCommand(req,
					req.getSource(), req.getTarget()));
		}
		return null;
	}

	/**
	 * @generated
	 */
	protected Command getCompleteCreateRelationshipCommand(
			CreateRelationshipRequest req) {
		if (ArevaElementTypes.Category_4001 == req.getElementType()) {
			return getGEFWrapper(new CategoryCreateCommand(req,
					req.getSource(), req.getTarget()));
		}
		return null;
	}

	/**
	 * Returns command to reorient EClass based link. New link target or source
	 * should be the domain model element associated with this node.
	 * 
	 * @generated
	 */
	protected Command getReorientRelationshipCommand(
			ReorientRelationshipRequest req) {
		switch (getVisualID(req)) {
		case CategoryEditPart.VISUAL_ID:
			return getGEFWrapper(new CategoryReorientCommand(req));
		}
		return super.getReorientRelationshipCommand(req);
	}

}
