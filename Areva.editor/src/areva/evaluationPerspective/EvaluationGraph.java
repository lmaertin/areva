package areva.evaluationPerspective;


import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.zest.core.viewers.AbstractZoomableViewer;
import org.eclipse.zest.core.viewers.GraphViewer;
import org.eclipse.zest.core.viewers.IZoomableWorkbenchPart;
import org.eclipse.zest.core.viewers.internal.ZoomManager;
import org.eclipse.zest.layouts.LayoutAlgorithm;
import org.eclipse.zest.layouts.LayoutStyles;
import org.eclipse.zest.layouts.algorithms.TreeLayoutAlgorithm;

import areva.evaluation.QADAGCon;
import areva.impl.CategoryImpl;
import areva.impl.ElementImpl;
import areva.impl.EvaluationImpl;
import areva.impl.EvaluationsImpl;

/**
 * View which shows the evaluationGraph and a selection box to change the graph
 * 
 * @author Sven
 * @version 1.0
 */
@SuppressWarnings("restriction")
public class EvaluationGraph extends ViewPart implements
		IZoomableWorkbenchPart, ISelectionListener {
	private GraphViewer viewer;
	private Combo combo;
	private String currentSelection = "Overview";
	NodeModelContentProvider model;
	//used to numerate the nodes, 
	int counter;

	@Override
	public void createPartControl(Composite parent) {
		getSite().getPage().addSelectionListener(this);
		GridLayout gridLayout = new GridLayout(1, false);
		parent.setLayout(gridLayout);
		combo = new Combo(parent, SWT.SINGLE | SWT.READ_ONLY);

		combo.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL));
		updateCombo();
		combo.setText("Overview");

		combo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				currentSelection = combo.getText();
				updateGraph();
			}
		});
		viewer = new GraphViewer(parent, SWT.BORDER);

		
		//Layout
		GridData gridData = new GridData();
		gridData.horizontalAlignment = GridData.FILL;
		gridData.grabExcessHorizontalSpace = true;
		gridData.verticalAlignment = GridData.FILL;
		gridData.grabExcessVerticalSpace = true;

		viewer.getGraphControl().setLayoutData(gridData);
		viewer.setContentProvider(new ZestNodeContentProvider());
		viewer.setLabelProvider(new ZestLabelProvider());

		model = new NodeModelContentProvider();
		updateGraph();
		viewer.setInput(model.getNodes());

		LayoutAlgorithm layout = setLayout();
		viewer.setLayoutAlgorithm(layout, true);
		viewer.applyLayout();
		fillToolBar();
	}

	/**
	 * 
	 * @return
	 */
	private LayoutAlgorithm setLayout() {
		LayoutAlgorithm layout;
		layout = new TreeLayoutAlgorithm(LayoutStyles.NO_LAYOUT_NODE_RESIZING);
		return layout;

	}

	/**
	 * refreshes the graph
	 */
	private void updateGraph() {
		counter = 0;
		//first delete all old
		model.deleteAll();
		EditingDomain ed = QADAGCon.getEditingDomain();
		if (ed != null) {
			EList<EObject> list = ed.getResourceSet().getResources().get(0)
					.getContents();

			EvaluationsImpl evaluationsImpl = (EvaluationsImpl) list.get(0);
			EvaluationImpl e = null;

			//Overview shows the different Evaluations in one graph
			if (currentSelection.equals("Overview")) {
				model.addEvaluations();
				counter++;
				for (int i = 0; i < evaluationsImpl.getEvaluations().size(); i++) {
					e = (EvaluationImpl) evaluationsImpl.getEvaluations()
							.get(i);
					model.addEvaluation(e, counter, 0);
					counter++;
				}

			//Otherwise recursion to show the structure of one Evaluation	
			} else {
				for (int i = 0; i < evaluationsImpl.getEvaluations().size(); i++) {
					e = (EvaluationImpl) evaluationsImpl.getEvaluations()
							.get(i);
					if (e.getName().equals(currentSelection)) {
						break;
					}
				}
				if (e != null) {
					model.addEvaluation(e, counter, -1);
					counter++;
					CategoryImpl c;
					for (int j = 0; j < e.getCategories().size(); j++) {
						c = (CategoryImpl) e.getCategories().get(j);
						model.addCategory(c, counter, 0);

						qadagRecursion(c, counter);
						counter++;
					}
				}
			}
		}
		model.connectNodes();
		LayoutAlgorithm layout = setLayout();
		viewer.setLayoutAlgorithm(layout, true);
		viewer.applyLayout();
		viewer.refresh();
		fillToolBar();
	}

	/**
	 * recursion through the qadag to build up the graph
	 * 
	 * @param c
	 * @param parent
	 */
	private void qadagRecursion(CategoryImpl c, int parent) {
		for (int i = 0; i < c.getElements().size(); i++) {
			counter++;
			model.addElement((ElementImpl) c.getElements().get(i), counter,
					parent);
		}
		counter++;
		for (int i = 0; i < c.getCategories().size(); i++) {
			counter++;
			CategoryImpl tmp = (CategoryImpl) c.getCategories().get(i);
			model.addCategory(tmp, counter, parent);
			qadagRecursion(tmp, counter);
		}
	}

	/**
	 * refresh the combo box
	 */
	private void updateCombo() {
		EditingDomain ed = QADAGCon.getEditingDomain();
		if (ed != null) {
			EList<EObject> list = ed.getResourceSet().getResources().get(0)
					.getContents();

			EvaluationsImpl evaluationsImpl = (EvaluationsImpl) list.get(0);

			EvaluationImpl eI;
			combo.removeAll();
			combo.add("Overview");
			for (int i = 0; i < evaluationsImpl.getEvaluations().size(); i++) {
				eI = (EvaluationImpl) evaluationsImpl.getEvaluations().get(i);
				combo.add(eI.getName());
			}

			if (selectionExists()) {
				combo.setText(currentSelection);
			} else {
				combo.setText("Overview");
			}
			currentSelection = combo.getText();
		}
	}

	/**
	 * to check, if a selection exists
	 * 
	 * @return
	 */
	private boolean selectionExists() {
		for (int i = 0; i < combo.getItemCount(); i++) {
			if (combo.getItem(i).equals(currentSelection)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void setFocus() {
	}

	/**
	 * sometimes the graph doesn't fit in the view. This reduces the size (to 90%)
	 * could be improved
	 *
	 */
	//TODO better scaling
	private void fillToolBar() {
		ZoomManager zoomManager2 = new ZoomManager(viewer.getGraphControl()
				.getRootLayer(), viewer.getGraphControl().getViewport());
		zoomManager2.setZoomAsText("90%");
	}

	@Override
	public AbstractZoomableViewer getZoomableViewer() {
		return viewer;
	}

	/**
	 * Refreshes the graph, if the selection changes
	 */
	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {

		{
			if (part.getClass().getName()
					.equals("areva.presentation.ArevaEditor")) {
				try {
					updateCombo();
					updateGraph();
				} catch (NullPointerException e) {

				}
			}
		}
	}

}
