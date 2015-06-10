

package areva.metricsPerspective;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.WorkbenchException;
import org.eclipse.ui.internal.Workbench;

/**
 * This class implements the function to open the metrics-perspective.
 * @author Sven von Höveling
 * @version 1.0
 *
 */

@SuppressWarnings("restriction")
public class OpenMetricsAction implements IWorkbenchWindowActionDelegate{


	IWorkbenchWindow activeWindow = null;

	/** Run the action. Display the Hello World message
	 */		
	@Override
	public void run(IAction proxyAction) {
		// proxyAction has UI information from manifest file (ignored)
		Shell shell = activeWindow.getShell();
		try {
			   Workbench.getInstance().showPerspective("areva.views.MetricPerspective", Workbench.getInstance().getActiveWorkbenchWindow());
			   
			   
			} catch (WorkbenchException e) {
			   MessageDialog.openInformation(shell,"Error","Not able to open MetricPerspective");
			  
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