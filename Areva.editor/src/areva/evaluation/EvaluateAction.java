package areva.evaluation;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.action.Action;
import areva.AConsole;
import areva.impl.EvaluationsImpl;

/**
 * This class represents the action to start a single evaluation or an evaluation of all.
 * Used by rightclick menu. If the selection doesn't fit, there is no action.
 * @author Sven von Höveling
 * @version 1.0
 * 
 * 
 */
public class EvaluateAction extends Action {
	public EvaluateAction() {
		super.setDescription("starts selected evaluation");
		super.setText("evaluate");
	}
	@Override
	public void run() {
		try {
	
			String string = QADAGCon.getQADAGEditor().getSelection().toString();
			EList<EObject> list = QADAGCon.getEditingDomain().getResourceSet().getResources().get(0)
					.getContents();

			EvaluationsImpl evaluationsImpl = (EvaluationsImpl) list.get(0);
			
			//if evaluationsImpl is selected, start all eval...
			if (string.equals("[" + evaluationsImpl.toString() + "]")) {
				Evaluation.changeToEvaPerspective();
				Evaluation.evaluateAll();
			
			//Otherwise check if one evaluation is selected and if so start it
			} else {
				int pos = -1;
			
				for (int i = 0; i < evaluationsImpl.getEvaluations().size(); i++) {
					if (string.equals("[" + evaluationsImpl.getEvaluations().get(i).toString() + "]"))
						pos = i;
				}
			
				if (pos == -1) {
					AConsole.out.println("No evaluation selected");
				} else {
					Evaluation.changeToEvaPerspective();
					Evaluation.evaluate(pos);
				}
			}
			 
		} catch (Exception e1) {
			AConsole.out.println("Fehler beim Berechnen");
			e1.printStackTrace();
		}
	}
}
