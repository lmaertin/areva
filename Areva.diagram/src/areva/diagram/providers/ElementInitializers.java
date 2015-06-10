package areva.diagram.providers;

import areva.diagram.part.ArevaDiagramEditorPlugin;

/**
 * @generated
 */
public class ElementInitializers {

	protected ElementInitializers() {
		// use #getInstance to access cached instance
	}

	/**
	 * @generated
	 */
	public static ElementInitializers getInstance() {
		ElementInitializers cached = ArevaDiagramEditorPlugin.getInstance()
				.getElementInitializers();
		if (cached == null) {
			ArevaDiagramEditorPlugin.getInstance().setElementInitializers(
					cached = new ElementInitializers());
		}
		return cached;
	}
}
