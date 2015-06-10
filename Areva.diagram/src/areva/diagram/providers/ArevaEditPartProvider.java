package areva.diagram.providers;

import org.eclipse.gmf.tooling.runtime.providers.DefaultEditPartProvider;

import areva.diagram.edit.parts.ArevaEditPartFactory;
import areva.diagram.edit.parts.EvaluationsEditPart;
import areva.diagram.part.ArevaVisualIDRegistry;

/**
 * @generated
 */
public class ArevaEditPartProvider extends DefaultEditPartProvider {

	/**
	 * @generated
	 */
	public ArevaEditPartProvider() {
		super(new ArevaEditPartFactory(), ArevaVisualIDRegistry.TYPED_INSTANCE,
				EvaluationsEditPart.MODEL_ID);
	}

}
