package areva.metricsPerspective;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.internal.Workbench;

import areva.content.ModelProvider;
import areva.evaluation.Evaluation;

/**
 * This class offers is a connection between Evaluation class and MetricEditor
 * to run and test code in the MetricPerspective
 * 
 * @author Sven von Höveling
 * @version 1.0
 * 
 */
@SuppressWarnings("restriction")
public class RunMetricCode implements IWorkbenchWindowActionDelegate {


	//Action only if MetricPerspective and MetricEditor is opened
	@Override
	public void run(IAction action) {
		try {
			IWorkbenchPage workbenchPage = Workbench.getInstance()
					.getActiveWorkbenchWindow().getActivePage();
			workbenchPage.saveAllEditors(false);
			//if the opened perspective is the the metric perspective, else do nothing
			if (workbenchPage.getPerspective().getId()
					.equals("areva.views.MetricPerspective")) {
				MetricEditor m = null;
				//find the editor area
				for (int i = 0; i < workbenchPage.getViewReferences().length; i++) {
					if (workbenchPage.getViewReferences()[i].getPartName()
							.equals("MetricEditor")) {
						m = (MetricEditor) workbenchPage.getViewReferences()[i]
								.getView(false);
					}
				}
				//if the metric editor was found, else do nothing
				if (m != null) {
					//if one metric is selected, run the code
					if (m.getSelectionNumber() != -1) {
						Evaluation.runMetricCode(ModelProvider.getMetrics()
								.get(m.getSelectionNumber()).getCode(), true);
					}
				}
			}

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
