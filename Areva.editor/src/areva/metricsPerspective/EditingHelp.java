package areva.metricsPerspective;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.ExpandBar;
import org.eclipse.swt.widgets.ExpandItem;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.part.ViewPart;
//import org.eclipse.zest.core.widgets.GraphConnection;
//import org.eclipse.zest.core.widgets.GraphNode;
//import org.eclipse.zest.core.widgets.ZestStyles;

import areva.content.ModelProvider;

/**
 * This view shows information for editing the java-code of the metrics
 * 
 * @author Sven von Höveling
 * @version 1.0
 * 
 */
public class EditingHelp extends ViewPart {


	public EditingHelp() {
		super();
	}

	@Override
	public void setFocus() {
	}

	@Override
	public void createPartControl(Composite parent) {
		
	
		ModelProvider.load();
		ExpandBar bar = new ExpandBar(parent, SWT.V_SCROLL);
		bar.setBackground(new Color(parent.getShell().getDisplay(), 255, 255,
				255));

		// First item
		Composite composite1 = new Composite(bar, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.marginLeft = layout.marginTop = layout.marginRight = layout.marginBottom = 10;
		layout.verticalSpacing = 10;
		composite1.setLayout(layout);

		Label label1 = new Label(composite1, SWT.NONE);
		label1.setText("Areva will try to run the method \"calcRating()\".\n"
				+ "Therefore please make sure, that the header is \"private static Double calcRating()\n"
				+ "and that the method returns the calculated rating");

		ExpandItem item1 = new ExpandItem(bar, SWT.NONE, 0);
		item1.setText("General information");
		item1.setHeight(composite1.computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
		item1.setControl(composite1);

		// Second item
		Composite composite2 = new Composite(bar, SWT.NONE);
		composite2.setLayout(layout);

		ExpandItem item2 = new ExpandItem(bar, SWT.NONE, 1);
		item2.setText("Available methods");
		item2.setHeight(composite2.computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
		item2.setControl(composite2);

		// Second item
		Composite composite3 = new Composite(bar, SWT.NONE);
		composite3.setLayout(layout);

		Label label3 = new Label(composite3, SWT.NONE);
		label3.setText("Use only different names for the metrics");

		ExpandItem item3 = new ExpandItem(bar, SWT.NONE, 2);
		item3.setText("Possible problems");
		item3.setHeight(composite3.computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
		item3.setControl(composite3);

	}
}
