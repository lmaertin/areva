package areva.defaultPerspective;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.WorkbenchException;
import org.eclipse.ui.internal.Workbench;

/**
 * Action for opening the default-perspective.
 * 
 * @author Sven von H�veling
 * @version 1.0
 */
@SuppressWarnings("restriction")
public class OpenArevaDefault implements IWorkbenchWindowActionDelegate {

	IWorkbenchWindow activeWindow = null;

	/**
	 * changes perspective
	 */
	@SuppressWarnings("restriction")
	@Override
	public void run(IAction proxyAction) {
		// proxyAction has UI information from manifest file (ignored)
		Shell shell = activeWindow.getShell();
		try {
			//open default-perspective
			Workbench.getInstance().showPerspective(
					"areva.views.ArevaDefaultPerspective",
					Workbench.getInstance().getActiveWorkbenchWindow());

		} catch (WorkbenchException e) {
			MessageDialog.openInformation(shell, "Error",
					"Not able to open ArevaDefaultPerspective");
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
		// nothing to do
	}
}