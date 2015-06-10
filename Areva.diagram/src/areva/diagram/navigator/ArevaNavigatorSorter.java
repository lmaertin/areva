package areva.diagram.navigator;

import org.eclipse.jface.viewers.ViewerSorter;

import areva.diagram.part.ArevaVisualIDRegistry;

/**
 * @generated
 */
public class ArevaNavigatorSorter extends ViewerSorter {

	/**
	 * @generated
	 */
	private static final int GROUP_CATEGORY = 2003;

	/**
	 * @generated
	 */
	public int category(Object element) {
		if (element instanceof ArevaNavigatorItem) {
			ArevaNavigatorItem item = (ArevaNavigatorItem) element;
			return ArevaVisualIDRegistry.getVisualID(item.getView());
		}
		return GROUP_CATEGORY;
	}

}
