package attempts;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

public class PackageExplorerAccess {
	private static boolean loaded = false;
	private static IFile f;
	
	public static IFile getFile() {
		if(!loaded) {
			file();
		}
		return f;
		
	}
	
	
	/**
	 * @return the editingDomain of the Areva-Editor
	 */
	private static void file() {
		try {

			//Workbench.getInstance().showPerspective(
				//	"areva.views.ArevaDefaultPerspective",
					//Workbench.getInstance().getActiveWorkbenchWindow());

			//IWorkbenchPage workbenchPage = Workbench.getInstance()
				//	.getActiveWorkbenchWindow().getActivePage();
			//workbenchPage.saveAllEditors(false);

			
			IWorkbenchWindow window =    PlatformUI.getWorkbench().getActiveWorkbenchWindow();
			
			ISelectionService service = window.getSelectionService();
					
			IStructuredSelection structured = (IStructuredSelection) service.getSelection("org.eclipse.jdt.ui.PackageExplorer");
			//org.eclipse.jdt.ui.PackageExplorer e;
							
			IFile file =(IFile) structured.getFirstElement();
			//file = (IFile) file.getP
				
			IProject a;
			//a.
			IPath path = file.getLocation();
					
			System.out.println(path.toPortableString());
			
			
			/*ArevaEditor a = null;
			for (int i = 0; i < workbenchPage.getEditorReferences().length; i++) {
				if (workbenchPage.getEditorReferences()[i].getId().equals(
						"org.eclipse.jdt.ui.PackageExplorer")) {
					a = (ArevaEditor) workbenchPage.getEditorReferences()[i]
							.getEditor(true);
				}
			}*/

			//ed = a.getEditingDomain();
			//loaded = true;
			

		} catch (Exception f) {
			 System.out.println("ActionsOfButtons in TabFolderMQR");
			f.printStackTrace();
		}
	}
}
