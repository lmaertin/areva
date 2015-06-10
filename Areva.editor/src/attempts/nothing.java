package attempts;

import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.ui.IViewReference;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.internal.Workbench;

import areva.AConsole;

public class nothing {

}

//Palladio stuff----------------------------------------------------------
/*
System.out.println(part.toString() + "mumumu");
System.out.println(part.getClass());
System.out.println(part.getClass().getCanonicalName());
System.out.println(part.getClass().getSimpleName().equals("PalladioComponentModelDiagramEditor"));
if (part.getClass().getSimpleName().equals("SystemEditor")) {
	IWorkbenchPage workbenchPage = Workbench.getInstance()
			.getActiveWorkbenchWindow().getActivePage();
	for (int i = 0; i < workbenchPage.getEditorReferences().length; i++) {
		try {
			System.out.println(workbenchPage.getEditorReferences()[i].getEditorInput().getName());
		} catch (PartInitException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			IEditingDomainProvider e = (IEditingDomainProvider) workbenchPage.getEditorReferences()[i].getEditor(true);
			System.out.println(e.getEditingDomain().getResourceSet().getResources().size());
			for (int j = 0; j < e.getEditingDomain().getResourceSet().getResources().size(); j++) {
				System.out.println("   " + e.getEditingDomain().getResourceSet().getResources().get(j).toString()); 
				for (int k = 0; k < e.getEditingDomain().getResourceSet().getResources().
						get(j).getContents().size(); k++)
				{
					try {
						AConsole.out.println();
						de.uka.ipd.sdq.pcm.system.System s = (de.uka.ipd.sdq.pcm.system.System) e.getEditingDomain().getResourceSet().getResources().
								get(j).getContents().get(k);
						System.out.println("juhu");
						for (int v = 0; v < s.getAssemblyContexts__ComposedStructure().size(); v++) {
							AConsole.out.println(s.getAssemblyContexts__ComposedStructure().get(v).toString());
							
						}
						for (int v = 0; v < s.getProvidedRoles_InterfaceProvidingEntity().size(); v++) {
							AConsole.out.println(s.getProvidedRoles_InterfaceProvidingEntity().get(v).toString());
							
						}
						for (int v = 0; v < s.getResourceRequiredDelegationConnectors_ComposedStructure().size(); v++) {
							AConsole.out.println(s.getResourceRequiredDelegationConnectors_ComposedStructure().get(v).toString());
							
						}
					} catch(Exception ef) {
						System.out.println("        not...");
					}
				}
			}
				
		} catch ( Exception e) {
		}
	}
}*/

/*
IViewReference[] tmp = getSite().getPage()
.getViewReferences();
for (int i = 0; i < tmp.length; i++) {

// AdapterFactoryEditingDomainResourceSet editingDomain
// =
// (AdapterFactoryEditingDomainResourceSet)a.getViewer().getInput();
// String
// s=a.getViewer().getInput().getClass().toString();

// System.out.println(getSite().getPage().getActiveEditor().;
// getSite().getPage().getActiveEditor().getEditorSite().

// if (tmp[i].getPartName().equals("Graph")) {
// g = (Graph) tmp[i].getView(false);

// } else if
// (tmp[i].getPartName().equals("CustomRange")) {
// cr = (CustomRange) tmp[i].getView(false);
// }
}*/
