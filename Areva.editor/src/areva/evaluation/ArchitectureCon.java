package areva.evaluation;

import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.internal.Workbench;
import areva.defaultPerspective.XMLSelection;

/**
 * This class gives the evaluation class access to the architecture, by searching for the xml-
 * selection view and getting their variables
 * @author Sven
 * @version 1.0
 *
 */

@SuppressWarnings("restriction")
public class ArchitectureCon {
	private static boolean loaded = false;
	private static XMLSelection view;

	public static XMLSelection getXMLSelectionView() {
		if (!loaded) {
			load();
		}
		return view;

	}
	
	/**
	 * Loads the connection to the selection by opening one perspective which includes the
	 * xml-selection-view
	 */
	private static void load() {
		try {
			IWorkbenchPage workbenchPage = Workbench.getInstance()
					.getActiveWorkbenchWindow().getActivePage();
			workbenchPage.saveAllEditors(false);
			
			String id = workbenchPage.getPerspective().getId();
			Workbench.getInstance().showPerspective(
					"areva.views.ArevaDefaultPerspective",
					Workbench.getInstance().getActiveWorkbenchWindow());

			for (int i = 0; i < workbenchPage.getViewReferences().length; i++) {
				if (workbenchPage.getViewReferences()[i].getId().equals(
						"areva.views.XMLSelection")) {
					view = (XMLSelection) workbenchPage.getViewReferences()[i].getView(true);
				}
			}
			
			Workbench.getInstance().showPerspective(
					id,
					Workbench.getInstance().getActiveWorkbenchWindow());

			loaded = true;

		} catch (Exception f) {
			f.printStackTrace();
		}
	}
}
