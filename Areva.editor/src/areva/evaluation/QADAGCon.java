package areva.evaluation;

import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.internal.Workbench;

import areva.presentation.ArevaEditor;

/**
 * Gives access to the editing Domain of the Editor
 * 
 * @author Sven
 * @version 1.0
 */
@SuppressWarnings("restriction")
public class QADAGCon {

	private static boolean loaded = false;
	private static EditingDomain ed = null;
	private static ArevaEditor a = null;

	public static EditingDomain getEditingDomain() {
		if (!loaded) {
			loadEditingDomainOfTheEditor();
		}
		return ed;

	}

	public static ArevaEditor getQADAGEditor() {
		if (!loaded) {
			loadEditingDomainOfTheEditor();
		}
		return a;
	}

	/**
	 * opens the default perspective to get the evaluation structure and to return the editing domain.
	 * @return the editingDomain of the Areva-Editor
	 */
	private static void loadEditingDomainOfTheEditor() {
		try {

			Workbench.getInstance().showPerspective(
					"areva.views.ArevaDefaultPerspective",
					Workbench.getInstance().getActiveWorkbenchWindow());

			IWorkbenchPage workbenchPage = Workbench.getInstance()
					.getActiveWorkbenchWindow().getActivePage();
			workbenchPage.saveAllEditors(false);

			for (int i = 0; i < workbenchPage.getEditorReferences().length; i++) {
				if (workbenchPage.getEditorReferences()[i].getId().equals(
						"areva.presentation.ArevaEditorID")) {
					a = (ArevaEditor) workbenchPage.getEditorReferences()[i]
							.getEditor(true);
				}
			}

			ed = a.getEditingDomain();
			loaded = true;

		} catch (Exception f) {
			System.out.println("ActionsOfButtons in TabFolderMQR");
			f.printStackTrace();
		}
	}
}
