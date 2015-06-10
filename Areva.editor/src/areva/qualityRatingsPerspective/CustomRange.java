package areva.qualityRatingsPerspective;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IViewReference;
import org.eclipse.ui.part.ViewPart;

import areva.content.ModelProvider;

/**
 * This view enables a custom range for the graph
 * @author Sven von Höveling
 * @version 1.0
 */
public class CustomRange extends ViewPart {

	private Text fromX;
	private Text toX;
	private Text fromY;
	private Text toY;
	private Button applyButton;
	private Button resetButton;

	/**
	 * layout and control of the view
	 */
	@Override
	public void createPartControl(Composite parent) {
		ModelProvider.load();
		GridLayout layout = new GridLayout(2, false);
		parent.setLayout(layout);

		//label title of x
		Label xTitle = new Label(parent, SWT.NONE);
		xTitle.setText("x-Axis");
		new Label(parent, SWT.NONE);

		//label "from x="
		Label l = new Label(parent, SWT.NONE);
		l.setText("From x = ");
		fromX = new Text(parent, SWT.ALL | SWT.BORDER);
		fromX.setBounds(50, 50, 0, 0);

		//label "to x="
		Label l2 = new Label(parent, SWT.NONE);
		l2.setText("To     x = ");
		toX = new Text(parent, SWT.ALL | SWT.BORDER);
		toX.setBounds(50, 50, 0, 0);

		new Label(parent, SWT.BORDER_DOT);
		new Label(parent, SWT.NONE);

		//label "y-axis"
		Label yTitle = new Label(parent, SWT.WRAP);
		yTitle.setText("y-Axis");
		new Label(parent, SWT.NONE);

		//label "from y="
		Label l3 = new Label(parent, SWT.NONE);
		l3.setText("From y = ");
		fromY = new Text(parent, SWT.ALL | SWT.BORDER);
		fromY.setBounds(50, 50, 0, 0);

		//label "to y="
		Label l4 = new Label(parent, SWT.NONE);
		l4.setText("To     y = ");
		toY = new Text(parent, SWT.ALL | SWT.BORDER);
		toY.setBounds(50, 50, 0, 0);

		new Label(parent, SWT.NONE);
		new Label(parent, SWT.NONE);

		applyButton = new Button(parent, SWT.NONE);
		applyButton.setText("apply");

		resetButton = new Button(parent, SWT.NONE);
		resetButton.setText("reset");

		addButtonFunctions();

	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

	/**
	 * title
	 */
	private void addButtonFunctions() {
		
		//function to apply button
		applyButton.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event e) {
				try {
					Graph g = null;
					CustomRange cr = null;
					IViewReference[] tmp = getSite().getPage()
							.getViewReferences();
					for (int i = 0; i < tmp.length; i++) {
						if (tmp[i].getPartName().equals("Graph")) {
							g = (Graph) tmp[i].getView(false);

						} else if (tmp[i].getPartName().equals("CustomRange")) {
							cr = (CustomRange) tmp[i].getView(false);
						}
					}
					if (g != null && cr != null) {
						g.setCustomRange(cr.getRangeArray());
					}
				} catch (Error f) {
					System.out.println("Error in CustomRangeButtonFunctions");
				}

			}

		});

		//function to reset button
		resetButton.addListener(SWT.Selection, new Listener() {

			@Override
			public void handleEvent(Event event) {
				try {
					Graph g = null;
					CustomRange cr = null;
					IViewReference[] tmp = getSite().getPage()
							.getViewReferences();
					for (int i = 0; i < tmp.length; i++) {
						if (tmp[i].getPartName().equals("Graph")) {
							g = (Graph) tmp[i].getView(false);

						} else if (tmp[i].getPartName().equals("CustomRange")) {
							cr = (CustomRange) tmp[i].getView(false);
						}
					}
					if (g != null && cr != null) {
						g.getChart().getAxisSet().adjustRange();
						g.getChart().redraw();
					}
				} catch (Error f) {
					System.out.println("Error in CustomRangeButtonFunctions");
				}

			}

		});

	}
	
	/**
	 * title
	 * @return
	 */
	private Double[] getRangeArray() {
		Double[] d = new Double[4];
		try {
			d[0] = Double.parseDouble(fromX.getText());
			d[1] = Double.parseDouble(toX.getText());
		} catch (NumberFormatException e) {
			fromX.setText("");
			toX.setText("");
			d[0] = null;
			d[1] = null;
		}
		try {
			d[2] = Double.parseDouble(fromY.getText());
			d[3] = Double.parseDouble(toY.getText());
		} catch (NumberFormatException e) {
			fromY.setText("");
			toY.setText("");
			d[2] = null;
			d[3] = null;
		}
		return d;
	}

}
