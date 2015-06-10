package areva.qualityRatingsPerspective;

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
import areva.content.ModelProvider;
import areva.content.QualityRating;
import areva.defaultPerspective.TabFolderMQR;
import areva.editingSupports.NameEditingSupport;
import areva.persistence.Storage;

/**
 * Qualityratings-table-view. Editable.
 * 
 * @author Sven von Höveling
 * @version 1.0
 * 
 */
public class QualityRatings extends ViewPart implements ISelectionListener,
		ISaveablePart, PropertyChangeListener {

	private TableViewer viewer;
	private Button addButton;
	private Menu contextMenu;
	//true, if something is not saved
	private boolean dirty;
	private static QualityRatings instance;

	public QualityRatings() {
		super();
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
		addListenerToQualityRatings();
		addRightClickMenu();
		getSite().getPage().addSelectionListener(this);

	}

	
	/**
	 * add listeners to qr, so observers see changes
	 */
	private void addListenerToQualityRatings() {
		for (int i = 0; i < ModelProvider.getQualityRatings().size(); i++) {
			ModelProvider.getQualityRatings().get(i)
					.addPropertyChangeListener("name", this);
		}
	}

	/**
	 * adds and gives a function to the add button
	 * @param parent
	 */
	private void addAddButton(Composite parent) {
		addButton = new Button(parent, SWT.PUSH);
		addButton.setText("add");
		addButton.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event e) {
				QualityRating tmp = new QualityRating();
				tmp.addPropertyChangeListener("name", instance);
				try {
					tmp.addPropertyChangeListener("name",
							TabFolderMQR.getTabFolderMQRInstance());
				} catch (Exception f) {
				}
				ModelProvider.getQualityRatings().add(tmp);
				dirty = true;
				firePropertyChange(PROP_DIRTY);
				viewer.setInput(ModelProvider.getQualityRatings());
				viewer.refresh();
				try {
					TabFolderMQR.getTabFolderMQRInstance()
							.refreshQualityRatings();
				} catch (Exception f) {

				}
			}
		});
	}

	/**
	 * rightClickMenu consists of "edit" and "delete"
	 */
	private void addRightClickMenu() {
		contextMenu = new Menu(viewer.getTable());
		viewer.getTable().setMenu(contextMenu);
		final MenuItem editMenu = new MenuItem(contextMenu, SWT.NONE);
		final MenuItem deleteMenu = new MenuItem(contextMenu, SWT.NONE);
		editMenu.setText("edit");
		deleteMenu.setText("delete");

		editMenu.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					NameEditingSupport.setEditable(true);
					viewer.setInput(ModelProvider.getQualityRatings());
					viewer.refresh();
				} catch (ArrayIndexOutOfBoundsException error) {
					AConsole.out.println("Not possible");
				}
			}
		});
		deleteMenu.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					ModelProvider.getQualityRatings().remove(
							viewer.getTable().getSelectionIndex());
					dirty = true;
					firePropertyChange(PROP_DIRTY);
					viewer.setInput(ModelProvider.getQualityRatings());
					viewer.refresh();
					TabFolderMQR.getTabFolderMQRInstance()
							.refreshQualityRatings();
				} catch (ArrayIndexOutOfBoundsException error) {
					AConsole.out.println("Not possible");
				} catch (NullPointerException f) {

				}
			}
		});
	}

	/**
	 * layout
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
		// Get the content for the viewer, setInput will call getElements in the
		// contentProvider
		viewer.setInput(ModelProvider.getQualityRatings());
		// make the selection available to other views
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
	 * only used at beginning, sets properties for the columns
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
				cell.setText(((QualityRating) cell.getElement()).getName());
			}
		});
		col.setEditingSupport(new NameEditingSupport(viewer));
	}

	/**
	 * 
	 * @param title
	 * @param bound size
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
	public void propertyChange(PropertyChangeEvent arg0) {
		this.dirty = true;
		firePropertyChange(PROP_DIRTY);

	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		Storage.saveQualityRatings();
		dirty = false;
		firePropertyChange(PROP_DIRTY);

	}

	@Override
	public void doSaveAs() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isDirty() {
		return dirty;
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