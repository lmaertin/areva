package areva.qualityRatingsPerspective;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.ISaveablePart;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.ViewPart;

import areva.AConsole;
import areva.content.Function;
import areva.content.ModelProvider;
import areva.editingSupports.IntervalEditingSupport;
import areva.editingSupports.TermEditingSupport;
import areva.persistence.Storage;

/**
 * This class implements the function-definition-view
 * 
 * @author Sven von Höveling
 * @version 1.0
 * 
 */

public class FunctionDefinition extends ViewPart implements ISelectionListener,
		ISaveablePart, PropertyChangeListener {

	private TableViewer viewer;
	private Button addButton;
	private Menu contextMenu;
	//integer for the number of the selected qr
	private int qRPos;
	//true if there was an change
	private boolean dirty;
	private static FunctionDefinition instance;

	public FunctionDefinition() {
		super();
		//-1 -> no qr selected
		qRPos = -1;
		dirty = false;
		instance = this;
	}

	@Override
	public void setFocus() {
	}

	/**
	 * layout and control of the view
	 */
	@Override
	public void createPartControl(Composite parent) {
		ModelProvider.load();
		GridLayout layout = new GridLayout(1, false);
		parent.setLayout(layout);
		addAddButton(parent);
		createViewer(parent);
		addRightClickMenu();
		addListenerToFunctions();
		getSite().getPage().addSelectionListener(this);

	}

	/**
	 * adds listeners to every function (every line), so observers see changes
	 */
	private void addListenerToFunctions() {
		for (int i = 0; i < ModelProvider.getQualityRatings().size(); i++) {
			for (int j = 0; j < ModelProvider.getQualityRatings().get(i)
					.getFunctions().size(); j++) {
				ModelProvider.getQualityRatings().get(i).getFunctions().get(j)
						.addPropertyChangeListener("interval", instance);
				ModelProvider.getQualityRatings().get(i).getFunctions().get(j)
				.addPropertyChangeListener("term", instance);
			}
		}
	}

	/**
	 * adds and gives function to the add button
	 * @param parent
	 */
	private void addAddButton(Composite parent) {
		addButton = new Button(parent, SWT.PUSH);
		addButton.setText("add");
		addButton.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event e) {
				if (qRPos != -1) {
					Function tmp = new Function();
					tmp.addPropertyChangeListener("interval", instance);
					tmp.addPropertyChangeListener("term", instance);
					ModelProvider.getQualityRatings().get(qRPos).getFunctions()
							.add(tmp);
					dirty = true;
					firePropertyChange(PROP_DIRTY);
				}
				updateContentProvider();
				viewer.refresh();
			}
		});
	}

	/**
	 * rightClickMenu consists of the option "delete"
	 */
	private void addRightClickMenu() {
		contextMenu = new Menu(viewer.getTable());
		viewer.getTable().setMenu(contextMenu);
		final MenuItem deleteMenu = new MenuItem(contextMenu, SWT.NONE);
		deleteMenu.setText("delete");

		deleteMenu.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					ModelProvider.getQualityRatings().get(qRPos).getFunctions()
							.remove(viewer.getTable().getSelectionIndex());
					updateContentProvider();
					dirty = true;
					firePropertyChange(PROP_DIRTY);
					viewer.refresh();
				} catch (ArrayIndexOutOfBoundsException error) {
					AConsole.out.println("Not possible");
				} catch (NullPointerException error) {
					AConsole.out.println("Nothing selected");
				}
			}
		});
	}

	/**
	 * layouts the view
	 * @param parent
	 */
	private void createViewer(Composite parent) {
		viewer = new TableViewer(parent, SWT.MULTI | SWT.H_SCROLL
				| SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);
		createColumns(parent, viewer);
		final Table table = viewer.getTable();
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		updateContentProvider();
		GridData gridData = new GridData();
		gridData.verticalAlignment = GridData.FILL;
		gridData.horizontalSpan = 1;
		gridData.grabExcessHorizontalSpace = true;
		gridData.grabExcessVerticalSpace = true;
		gridData.horizontalAlignment = GridData.FILL;
		viewer.getControl().setLayoutData(gridData);
	}

	/**
	 * refreshes the content provider
	 */
	private void updateContentProvider() {
		if (qRPos != -1) {
			viewer.setContentProvider(new ArrayContentProvider());
			viewer.setInput(ModelProvider.getQualityRatings().get(qRPos)
					.getFunctions());
			getSite().setSelectionProvider(viewer);
			
		} else {
			viewer.setContentProvider(new ArrayContentProvider());
			viewer.setInput(new ArrayList<String>());
		}
		GridData gridData = new GridData();
		gridData.verticalAlignment = GridData.FILL;
		gridData.horizontalSpan = 1;
		gridData.grabExcessHorizontalSpace = true;
		gridData.grabExcessVerticalSpace = true;
		gridData.horizontalAlignment = GridData.FILL;
		viewer.getControl().setLayoutData(gridData);
	}

	public TableViewer getViewer() {
		return viewer;
	}

	/**
	 * only used by startup, sets the size and other properties of the columns
	 * @param parent
	 * @param viewer
	 */
	private void createColumns(final Composite parent, final TableViewer viewer) {
		String[] titles = { "Interval", "Term" };
		int[] bounds = { 150, 200 };

		// First column is for the first name
		TableViewerColumn col = createTableViewerColumn(titles[0], bounds[0], 0);
		col.setLabelProvider(new CellLabelProvider() {
			@Override
			public void update(ViewerCell cell) {
				cell.setText(((Function) cell.getElement()).getInterval());
			}
		});
		col.setEditingSupport(new IntervalEditingSupport(viewer));

		col = createTableViewerColumn(titles[1], bounds[1], 1);
		col.setLabelProvider(new CellLabelProvider() {
			@Override
			public void update(ViewerCell cell) {
				cell.setText(((Function) cell.getElement()).getTerm());
			}
		});
		col.setEditingSupport(new TermEditingSupport(viewer));
	}

	private TableViewerColumn createTableViewerColumn(String title, int bound,
			final int colNumber) {
		final TableViewerColumn viewerColumn = new TableViewerColumn(viewer,
				SWT.NONE);
		final TableColumn column = viewerColumn.getColumn();
		column.setText(title);
		column.setWidth(bound);
		column.setResizable(true);
		column.setMoveable(true);
		return viewerColumn;
	}

	/**
	 * if one qr is selected, this method changes the content of the view
	 */
	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		if (part instanceof QualityRatings && !addButton.isDisposed()) {
			String s = selection.toString().substring(1,
					selection.toString().length() - 1);
			qRPos = ModelProvider.findQualityRatingPosition(s);
			updateContentProvider();
		}
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		this.dirty = true;
		firePropertyChange(PROP_DIRTY);
		
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		this.dirty = false;
		firePropertyChange(PROP_DIRTY);
		Storage.saveFunctionDefintions();
		
	}

	@Override
	public void doSaveAs() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isDirty() {
		return this.dirty;
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
