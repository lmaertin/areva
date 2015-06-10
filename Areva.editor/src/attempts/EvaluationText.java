package attempts;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

public class EvaluationText extends ViewPart {
	private ScrolledComposite composite;
	//private Composite c;

	public EvaluationText() {
		super();
	}

	@Override
	public void setFocus() {
		composite.setFocus();
	}

	@Override
	public void createPartControl(Composite parent) {
		composite = new ScrolledComposite(parent, SWT.BORDER | SWT.V_SCROLL);

		// b1.setSize(50, 50);
		// c1.setContent(b1);

		// this button has a minimum size of 400 x 400. If the window is resized
		// to be big
		// enough to show more than 400 x 400, the button will grow in size. If
		// the window
		// is made too small to show 400 x 400, scrollbars will appear.

		// c2.setContent(b2);
		// c2.setExpandHorizontal(true);
		// c2.setExpandVertical(true);
		// c2.setMinWidth(25);
		// c2.setMinHeight(25);

	}

	public void startEvaluation() {
		// composite.getChildren()[0].
		// Evaluation.startEvaluation(composite);
		composite.redraw();
	}
}
