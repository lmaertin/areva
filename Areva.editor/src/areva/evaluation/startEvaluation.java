package areva.evaluation;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;

/**
 * This class offers the button action in the toolbar to start the evaluation
 * 
 * @author Sven von Höveling
 * @version 1.0
 * 
 */
public class startEvaluation implements IWorkbenchWindowActionDelegate {
	@Override
	public void run(IAction action) {
		try {

			Evaluation.evaluateAll();

		} catch (Exception f) {
			f.printStackTrace();

		}
	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init(IWorkbenchWindow window) {
		// TODO Auto-generated method stub

	}
}
