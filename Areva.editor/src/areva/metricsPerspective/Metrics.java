package areva.metricsPerspective;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

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
import areva.content.Metric;
import areva.content.ModelProvider;
import areva.defaultPerspective.TabFolderMQR;
import areva.editingSupports.NameEditingSupport;
import areva.persistence.Storage;

/**
 * Class for the metrics-table. Editable.
 * 
 * @author Sven von Höveling
 * @version 1.0
 * 
 */
public class Metrics extends ViewPart implements ISelectionListener,
		ISaveablePart, PropertyChangeListener {

	private TableViewer viewer;
	private Button addButton;
	private Menu contextMenu;
	//true if something is not saved
	private boolean dirty;
	private static Metrics instance;

	public Metrics() {
		super();
		dirty = false;
		instance = this;
	}

	@Override
	public void createPartControl(Composite parent) {
		ModelProvider.load();
		addListenerToMetrics();
		GridLayout layout = new GridLayout(1, false);
		parent.setLayout(layout);
		addAddButton(parent);
		createViewer(parent);
		getSite().getPage().addSelectionListener(this);
		addRightClickMenu();

	}

	/**
	 * add button with function to composite
	 * @param parent
	 */
	private void addAddButton(Composite parent) {
		addButton = new Button(parent, SWT.PUSH);
		addButton.setText("add");
		addButton.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event e) {
				Metric tmp = new Metric();
				tmp.addPropertyChangeListener("name", instance);
				try {
					tmp.addPropertyChangeListener("name",
							TabFolderMQR.getTabFolderMQRInstance());
				} catch (Exception f) {
				}
				ModelProvider.getMetrics().add(tmp);
				viewer.setInput(ModelProvider.getMetrics());
				dirty = true;
				firePropertyChange(PROP_DIRTY);
				viewer.refresh();

				try {
					TabFolderMQR.getTabFolderMQRInstance().refreshMetrics();
				} catch (Exception f) {

				}
			}
		});
	}

	/**
	 * creates rightClickMenu, edit and delete. 
	 */
	private void addRightClickMenu() {
		contextMenu = new Menu(viewer.getTable());
		viewer.getTable().setMenu(contextMenu);

		final MenuItem editMenu = new MenuItem(contextMenu, SWT.NONE);
		final MenuItem deleteMenu = new MenuItem(contextMenu, SWT.NONE);
		editMenu.setText("edit");
		deleteMenu.setText("delete");
		//adds function to the edit button
		editMenu.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					NameEditingSupport.setEditable(true);
					viewer.setInput(ModelProvider.getMetrics());
					viewer.refresh();
				} catch (ArrayIndexOutOfBoundsException error) {
					AConsole.out.println("Not possible");
				}
			}
		});
		//adds funtion to the delete button
		deleteMenu.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					ModelProvider.getMetrics().remove(
							viewer.getTable().getSelectionIndex());
					dirty = true;
					viewer.setInput(ModelProvider.getMetrics());
					firePropertyChange(PROP_DIRTY);
					viewer.refresh();
					TabFolderMQR.getTabFolderMQRInstance().refreshMetrics();
				} catch (ArrayIndexOutOfBoundsException error) {
					AConsole.out.println("Not possible");
				} catch (NullPointerException f) {

				}
			}

		});
	}

	/**
	 * see title
	 */
	private void addListenerToMetrics() {
		for (int i = 0; i < ModelProvider.getMetrics().size(); i++) {
			ModelProvider.getMetrics().get(i)
					.addPropertyChangeListener("name", this);
		}
	}

	/**
	 * title
	 * @param parent
	 */
	private void createViewer(Composite parent) {
		viewer = new TableViewer(parent, SWT.MULTI | SWT.H_SCROLL
				| SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);
		createColumns(parent, viewer);
		final Table table = viewer.getTable();
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		viewer.setContentProvider(new ArrayContentProvider());

		viewer.setInput(ModelProvider.getMetrics());

		getSite().setSelectionProvider(viewer);

		// Layout the viewer
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
	 * title
	 * @param parent
	 * @param viewer
	 */
	private void createColumns(final Composite parent, final TableViewer viewer) {
		String[] titles = { "Name" };
		int[] bounds = { 300 };

		// First column is for the first name
		TableViewerColumn col = createTableViewerColumn(titles[0], bounds[0], 0);
		col.setLabelProvider(new CellLabelProvider() {
			@Override
			public void update(ViewerCell cell) {
				cell.setText(((Metric) cell.getElement()).getName());
			}
		});
		col.setEditingSupport(new NameEditingSupport(viewer));
	}

	/**
	 * 
	 * @param title
	 * @param bound
	 * @param colNumber
	 * @return
	 */
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

	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		NameEditingSupport.setEditable(false);
	}

	@Override
	public void setFocus() {
		// .setFocus();
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		dirty = false;
		firePropertyChange(PROP_DIRTY);
		Storage.saveMetrics();
	}

	@Override
	public void doSaveAs() {
		this.dirty = false;
		firePropertyChange(PROP_DIRTY);

	}

	@Override
	public boolean isDirty() {
		// TODO Auto-generated method stub
		return this.dirty;
	}

	@Override
	public boolean isSaveAsAllowed() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isSaveOnCloseNeeded() {
		return true;
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		this.dirty = true;
		firePropertyChange(PROP_DIRTY);
	}

}
