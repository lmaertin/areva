package areva.qualityRatingsPerspective;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.WorkbenchException;
import org.eclipse.ui.internal.Workbench;

/**
 * Action for opening the QR-perspective.
 * @author Sven von Höveling
 * @version 1.0
 */
@SuppressWarnings("restriction")
public class OpenQualityRatingsAction implements IWorkbenchWindowActionDelegate{


	IWorkbenchWindow activeWindow = null;

	/** Run the action. Display the Hello World message
	 */		
	@Override
	public void run(IAction proxyAction) {
		// proxyAction has UI information from manifest file (ignored)
		Shell shell = activeWindow.getShell();
		try {
			   Workbench.getInstance().showPerspective("areva.views.FunctionPerspective", Workbench.getInstance().getActiveWorkbenchWindow());
			   
			   
			} catch (WorkbenchException e) {
			   MessageDialog.openInformation(shell,"Error","Not able to open QualityRatingPerspective");
			  
			}
	}

	// IActionDelegate method
	@Override
	public void selectionChanged(IAction proxyAction, ISelection selection) {
		// do nothing, action is not dependent on the selection
	}
	
	// IWorkbenchWindowActionDelegate method
	@Override
	public void init(IWorkbenchWindow window) {
		activeWindow = window;
	}
	
	// IWorkbenchWindowActionDelegate method
	@Override
	public void dispose() {
		//  nothing to do
	}
}