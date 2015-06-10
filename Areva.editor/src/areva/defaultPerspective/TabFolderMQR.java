package areva.defaultPerspective;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.WorkbenchException;
import org.eclipse.ui.internal.Workbench;
import org.eclipse.ui.part.ViewPart;

import areva.content.Metric;
import areva.content.ModelProvider;
import areva.content.QualityRating;

/**
 * Metrics and QR-lists in the default-view
 * 
 * @author Sven von Höveling
 * @version 1.0
 * 
 */
@SuppressWarnings("restriction")
public class TabFolderMQR extends ViewPart implements PropertyChangeListener {

	private TabFolder tabFolder;
	private Button editMetricsButton;
	private Button editQualityRatingsButton;
	//tableViewer for metrics
	private TableViewer viewer;
	//tableViewer for qr
	private TableViewer viewer2;
	private static TabFolderMQR instance;

	public TabFolderMQR() {
		super();
		instance = this;
	}

	/**
	 * sets the layout and the control of the view
	 */
	@Override
	public void createPartControl(Composite parent) {
		//load metrics and qr before
		ModelProvider.load();

		tabFolder = new TabFolder(parent, SWT.NONE);

		TabItem metricsItem = new TabItem(tabFolder, SWT.NONE);
		metricsItem.setText("metrics");

		Composite metricsComposite = new Composite(tabFolder, SWT.NONE);
		metricsComposite.setLayout(new GridLayout(1, false));

		editMetricsButton = new Button(metricsComposite, SWT.PUSH);
		editMetricsButton.setText("edit");
		metricsItem.setControl(metricsComposite);

		TabItem qualityRatingsItem = new TabItem(tabFolder, SWT.NONE);
		qualityRatingsItem.setText("qualityRatings");

		Composite qualityRatingsComposite = new Composite(tabFolder, SWT.NONE);
		qualityRatingsComposite.setLayout(new GridLayout(1, true));

		editQualityRatingsButton = new Button(qualityRatingsComposite, SWT.PUSH);
		editQualityRatingsButton.setText("edit");
		addFunctionToButtons();

		GridLayout layout = new GridLayout(1, false);
		parent.setLayout(layout);

		createMetricsViewer(metricsComposite);
		createQualityRatingsViewer(qualityRatingsComposite);

		qualityRatingsItem.setControl(qualityRatingsComposite);

		addListenerToMetrics();
		addListenerToQualityRatings();

		tabFolder.pack();
	}
	
	
	/**
	 *  see title
	 */
	private void addFunctionToButtons() {

		//this button changes view to metricPerspective
		editMetricsButton.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event e) {
				try {

					Workbench.getInstance().showPerspective(
							"areva.views.MetricPerspective",
							Workbench.getInstance().getActiveWorkbenchWindow());

				} catch (WorkbenchException f) {
					System.out.println("ActionsOfButtons in TabFolderMQR");
					f.printStackTrace();

				}

			}
		});
		//this button changes the view to the qrPerspective
		editQualityRatingsButton.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event e) {
				try {
					Workbench.getInstance().showPerspective(
							"areva.views.QualityRatingsPerspective",
							Workbench.getInstance().getActiveWorkbenchWindow());

				} catch (WorkbenchException f) {
					System.out.println("ActionsOfButtons in TabFolderMQR");
					f.printStackTrace();

				}

			}
		});
	}
	
	/**
	 * see title
	 * @param parent
	 */
	private void createMetricsViewer(Composite parent) {
		viewer = new TableViewer(parent, SWT.MULTI | SWT.H_SCROLL
				| SWT.V_SCROLL | SWT.FULL_SELECTION);
		createColumns(parent, viewer);
		final Table table = viewer.getTable();
		table.setHeaderVisible(false);
		table.setLinesVisible(false);

		viewer.setContentProvider(new ArrayContentProvider());
		refreshMetrics();

	}
	
	/** 
	 * @param parent
	 */
	private void createQualityRatingsViewer(Composite parent) {
		viewer2 = new TableViewer(parent, SWT.MULTI | SWT.H_SCROLL
				| SWT.V_SCROLL | SWT.FULL_SELECTION);
		createColumns2(parent, viewer2);
		final Table table = viewer2.getTable();
		table.setHeaderVisible(false);
		table.setLinesVisible(false);

		viewer2.setContentProvider(new ArrayContentProvider());
		refreshQualityRatings();

	}

	/**
	 * sets the input of the viewer
	 * and resizes
	 */
	public void refreshMetrics() {
		viewer.setInput(ModelProvider.getMetrics());

		// Layout the viewer
		GridData gridData = new GridData();
		gridData.verticalAlignment = GridData.FILL;
		gridData.horizontalSpan = 1;
		gridData.grabExcessHorizontalSpace = true;
		gridData.grabExcessVerticalSpace = true;
		gridData.horizontalAlignment = GridData.FILL;
		viewer.getControl().setLayoutData(gridData);
		viewer.refresh();
	}

	/**
	 * sets the input of the viewer and resizes
	 */
	public void refreshQualityRatings() {
		viewer2.setInput(ModelProvider.getQualityRatings());

		// Layout the viewer
		GridData gridData = new GridData();
		gridData.verticalAlignment = GridData.FILL;
		gridData.horizontalSpan = 1;
		gridData.grabExcessHorizontalSpace = true;
		gridData.grabExcessVerticalSpace = true;
		gridData.horizontalAlignment = GridData.FILL;
		viewer2.getControl().setLayoutData(gridData);
		viewer2.refresh();
	}

	/**
	 * 
	 * @return 
	 */
	public static TabFolderMQR getTabFolderMQRInstance() {
		return instance;
	}

	
	/**
	 * 
	 * @param parent
	 * @param viewer
	 * 
	 * creates the column of the metrics list
	 */
	private void createColumns(final Composite parent, final TableViewer viewer) {
		String[] titles = { "Name" };
		int[] bounds = { 300 };

		TableViewerColumn col = createTableViewerColumn(titles[0], bounds[0], 0);
		col.setLabelProvider(new CellLabelProvider() {
			@Override
			public void update(ViewerCell cell) {
				cell.setText(((Metric) cell.getElement()).getName());
			}
		});
	}

	/**
	 * 
	 * @param parent
	 * @param viewer
	 * 
	 * creates the column of the qualityRatings list
	 */
	private void createColumns2(final Composite parent, final TableViewer viewer) {
		String[] titles = { "Name" };
		int[] bounds = { 300 };

		TableViewerColumn col = createTableViewerColumn2(titles[0], bounds[0],
				0);
		col.setLabelProvider(new CellLabelProvider() {
			@Override
			public void update(ViewerCell cell) {
				cell.setText(((QualityRating) cell.getElement()).getName());
			}
		});
	}
	
	/**
	 * called by createColumns, part of creating the list of the metrics
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

	/**
	 * called by createColumns2, part of creating the list of the quality ratings
	 * @param title
	 * @param bound
	 * @param colNumber
	 * @return
	 */
	private TableViewerColumn createTableViewerColumn2(String title, int bound,
			final int colNumber) {
		final TableViewerColumn viewerColumn = new TableViewerColumn(viewer2,
				SWT.NONE);
		final TableColumn column = viewerColumn.getColumn();
		column.setText(title);
		column.setWidth(bound);
		column.setResizable(true);
		column.setMoveable(true);
		return viewerColumn;
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		refreshMetrics();
	}
	
	
	/**
	 * registrate on metric elements to be aware of changes
	 */
	private void addListenerToMetrics() {
		for (int i = 0; i < ModelProvider.getMetrics().size(); i++) {
			ModelProvider.getMetrics().get(i)
					.addPropertyChangeListener("name", this);
		}
	}

	/**
	 * registrate on qr elements to be aware of changes
	 */
	private void addListenerToQualityRatings() {
		for (int i = 0; i < ModelProvider.getQualityRatings().size(); i++) {
			ModelProvider.getQualityRatings().get(i)
					.addPropertyChangeListener("name", this);
		}
	}

	@Override
	public void setFocus() {
		tabFolder.setFocus();
	}

}
