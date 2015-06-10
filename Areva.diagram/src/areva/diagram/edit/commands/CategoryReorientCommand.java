package areva.diagram.edit.commands;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.emf.type.core.commands.EditElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.ReorientRelationshipRequest;

import areva.Category;
import areva.Evaluation;
import areva.diagram.edit.policies.ArevaBaseItemSemanticEditPolicy;

/**
 * @generated
 */
public class CategoryReorientCommand extends EditElementCommand {

	/**
	 * @generated
	 */
	private final int reorientDirection;

	/**
	 * @generated
	 */
	private final EObject oldEnd;

	/**
	 * @generated
	 */
	private final EObject newEnd;

	/**
	 * @generated
	 */
	public CategoryReorientCommand(ReorientRelationshipRequest request) {
		super(request.getLabel(), request.getRelationship(), request);
		reorientDirection = request.getDirection();
		oldEnd = request.getOldRelationshipEnd();
		newEnd = request.getNewRelationshipEnd();
	}

	/**
	 * @generated
	 */
	public boolean canExecute() {
		if (false == getElementToEdit() instanceof Category) {
			return false;
		}
		if (reorientDirection == ReorientRelationshipRequest.REORIENT_SOURCE) {
			return canReorientSource();
		}
		if (reorientDirection == ReorientRelationshipRequest.REORIENT_TARGET) {
			return canReorientTarget();
		}
		return false;
	}

	/**
	 * @generated
	 */
	protected boolean canReorientSource() {
		if (!(oldEnd instanceof Category && newEnd instanceof Category)) {
			return false;
		}
		if (getLink().getCategories().size() != 1) {
			return false;
		}
		Category target = (Category) getLink().getCategories().get(0);
		if (!(getLink().eContainer() instanceof Evaluation)) {
			return false;
		}
		Evaluation container = (Evaluation) getLink().eContainer();
		return ArevaBaseItemSemanticEditPolicy.getLinkConstraints()
				.canExistCategory_4001(container, getLink(), getNewSource(),
						target);
	}

	/**
	 * @generated
	 */
	protected boolean canReorientTarget() {
		if (!(oldEnd instanceof Category && newEnd instanceof Category)) {
			return false;
		}
		if (getLink().getCategories().size() != 1) {
			return false;
		}
		Category source = (Category) getLink().getCategories().get(0);
		if (!(getLink().eContainer() instanceof Evaluation)) {
			return false;
		}
		Evaluation container = (Evaluation) getLink().eContainer();
		return ArevaBaseItemSemanticEditPolicy.getLinkConstraints()
				.canExistCategory_4001(container, getLink(), source,
						getNewTarget());
	}

	/**
	 * @generated
	 */
	protected CommandResult doExecuteWithResult(IProgressMonitor monitor,
			IAdaptable info) throws ExecutionException {
		if (!canExecute()) {
			throw new ExecutionException(
					"Invalid arguments in reorient link command"); //$NON-NLS-1$
		}
		if (reorientDirection == ReorientRelationshipRequest.REORIENT_SOURCE) {
			return reorientSource();
		}
		if (reorientDirection == ReorientRelationshipRequest.REORIENT_TARGET) {
			return reorientTarget();
		}
		throw new IllegalStateException();
	}

	/**
	 * @generated
	 */
	protected CommandResult reorientSource() throws ExecutionException {
		getLink().getCategories().remove(getOldSource());
		getLink().getCategories().add(getNewSource());
		return CommandResult.newOKCommandResult(getLink());
	}

	/**
	 * @generated
	 */
	protected CommandResult reorientTarget() throws ExecutionException {
		getLink().getCategories().remove(getOldTarget());
		getLink().getCategories().add(getNewTarget());
		return CommandResult.newOKCommandResult(getLink());
	}

	/**
	 * @generated
	 */
	protected Category getLink() {
		return (Category) getElementToEdit();
	}

	/**
	 * @generated
	 */
	protected Category getOldSource() {
		return (Category) oldEnd;
	}

	/**
	 * @generated
	 */
	protected Category getNewSource() {
		return (Category) newEnd;
	}

	/**
	 * @generated
	 */
	protected Category getOldTarget() {
		return (Category) oldEnd;
	}

	/**
	 * @generated
	 */
	protected Category getNewTarget() {
		return (Category) newEnd;
	}
}
