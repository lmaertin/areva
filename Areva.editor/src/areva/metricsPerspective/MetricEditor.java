package areva.metricsPerspective;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.ui.ISaveablePart;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.ViewPart;

import areva.content.ModelProvider;
import areva.persistence.Storage;

/**
 * The metric-editor view is for writing the code of the metrics
 * 
 * @author Sven von Höveling
 * @version 1.0
 */
public class MetricEditor extends ViewPart implements ISelectionListener,
		ISaveablePart {

	//number of the currently selected metric
	private int mPos;
	private StyledText text;
	//true if something is not saved
	private boolean dirty;
	
	JavaLineStyler lineStyler = new JavaLineStyler();

	FileDialog fileDialog;

	public MetricEditor() {
		super();
		// -1 -> no metric selected
		mPos = -1;
		dirty = false;
	}

	/**
	 * layout and control of the view
	 */
	@Override
	public void createPartControl(Composite parent) {
		ModelProvider.load();
		getSite().getPage().addSelectionListener(this);


		GridLayout layout = new GridLayout();
		layout.numColumns = 1;
		parent.setLayout(layout);
		
		createStyledText(parent);
		StyleRange styleRange = new StyleRange();
		styleRange.length = 30;
		styleRange.fontStyle = SWT.BOLD;
		styleRange = new StyleRange();
		styleRange.start = 75;
		styleRange.length = 11;
		styleRange.fontStyle = SWT.BOLD;
		
		addTextChangedEvent();
	}

	/**
	 * creates the editor area
	 * @param parent
	 */
	private void createStyledText(Composite parent) {
		text = new StyledText(parent, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL
				| SWT.H_SCROLL);
		Font font = new Font(parent.getDisplay(), "Consolas", 10, SWT.NORMAL);
	    text.setFont(font);
		GridData spec = new GridData();
		spec.horizontalAlignment = GridData.FILL;
		spec.grabExcessHorizontalSpace = true;
		spec.verticalAlignment = GridData.FILL;
		spec.grabExcessVerticalSpace = true;
		text.setLayoutData(spec);
		text.addLineStyleListener(lineStyler);
		text.setEditable(true);
	}

	/**
	 * see title
	 */
	private void addTextChangedEvent() {

		text.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e) {
				dirty = true;
				firePropertyChange(PROP_DIRTY);
				if (mPos != -1) {
					ModelProvider.getMetrics().get(mPos)
							.setCode(text.getText());
				}

			}

		});
	}

	/**
	 * sets the text of the editor to s
	 * @param s
	 */
	private void setText(String s) {
		if (s != null) {
			text.setText(s);
		}
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

	/**
	 * loads the code of the newly selected metric
	 */
	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		if (part instanceof Metrics && !text.isDisposed()) {
			String s = selection.toString().substring(1,
					selection.toString().length() - 1);
			mPos = ModelProvider.findMetricPosition(s);
			if (mPos != -1) {
				setText(ModelProvider.getMetrics().get(mPos).getCode());
			} else {
				setText("");
			}
		}

	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		Storage.saveMetricsCode();
		dirty = false;
		firePropertyChange(PROP_DIRTY);

	}

	@Override
	public void doSaveAs() {
	}

	@Override
	public boolean isDirty() {
		return this.dirty;
	}

	/**
	 * 
	 * @return number of metric, which is selected
	 */
	public int getSelectionNumber() {
		return mPos;
	}

	@Override
	public boolean isSaveAsAllowed() {
		return false;
	}

	@Override
	public boolean isSaveOnCloseNeeded() {
		return true;
	}

}
