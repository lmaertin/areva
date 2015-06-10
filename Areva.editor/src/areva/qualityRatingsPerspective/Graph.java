package areva.qualityRatingsPerspective;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IViewReference;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.ViewPart;
import org.swtchart.Chart;
import org.swtchart.ILineSeries;
import org.swtchart.ILineSeries.PlotSymbolType;
import org.swtchart.ISeries.SeriesType;
import org.swtchart.LineStyle;
import org.swtchart.Range;

import areva.AConsole;
import areva.content.ModelProvider;
import areva.math.Interval;
import areva.math.PointsCalculator;

/**
 * Class for showing a graph for the QR
 * @author Sven
 * @version 1.0
 */
public class Graph extends ViewPart implements ISelectionListener {
	private Chart chart;
	private int qrPos;
	private Menu contextMenu;

	public Graph() {
		super();
	}

	@Override
	public void createPartControl(Composite parent) {
		ModelProvider.load();

		chart = new Chart(parent, SWT.NONE);
		addRightClickMenu();

		// adjust the axis range
		chart.getAxisSet().adjustRange();

		getSite().getPage().addSelectionListener(this);
	}

	/**
	 * right click menu consists of "redraw"
	 */
	private void addRightClickMenu() {
		contextMenu = new Menu(chart.getPlotArea());
		chart.getPlotArea().setMenu(contextMenu);
		final MenuItem redrawMenu = new MenuItem(contextMenu, SWT.NONE);
		redrawMenu.setText("redraw");

		redrawMenu.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					loadFunctions();
					chart.redraw();
				} catch (ArrayIndexOutOfBoundsException error) {
					AConsole.out.println("Not possible");
				}
			}
		});
	}

	/**
	 * if the selection is a qr, this method will change the content of the view (new graph)
	 */
	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		if (part instanceof QualityRatings && !chart.isDisposed()) {
			try {
				
				resetChart();
				String s = selection.toString().substring(1,
						selection.toString().length() - 1);

				qrPos = ModelProvider.findQualityRatingPosition(s);
				if (qrPos != -1) {
					chart.getTitle().setText(
							ModelProvider.getQualityRatings().get(qrPos)
									.getName());
					loadFunctions();
				} else {
					chart.getTitle().setText("No selection");
				}
				chart.getAxisSet().adjustRange();
				chart.redraw();
			} catch (Exception e) {
				System.out.println("Graph, selectionChanged");
				e.printStackTrace();
			}
		}
	}

	/**
	 * checks if the graph is part of the current perspective
	 * @return
	 */
	public boolean graphIsVisible() {
		IViewReference[] tmp = getSite().getPage().getViewReferences();
		for (int i = 0; i < tmp.length; i++) {

			if (tmp[i].getPartName().equals("Graph")) {
				return true;

			}
		}
		return false;

	}

	/**
	 * deletes all the lines of the graph
	 */
	private void resetChart() {
		int number = chart.getSeriesSet().getSeries().length;
		try {
			for (int i = 0; i < number; i++) {
				chart.getSeriesSet().deleteSeries("interval " + i);
			}
		} catch (IllegalArgumentException e) {
			System.out.println("fehler");

		}
	}

	/**
	 * used to set a custom range for the graph (e.g. by using the custom range view)
	 * @param d
	 */
	public void setCustomRange(Double[] d) {

		if (d[0] != null && d[1] != null) {
			chart.getAxisSet().getAxes()[0].setRange(new Range(d[0], d[1]));
		} else {
			chart.getAxisSet().getAxes()[0].adjustRange();
		}
		if (d[2] != null && d[3] != null) {
			chart.getAxisSet().getAxes()[1].setRange(new Range(d[2], d[3]));
		} else {
			chart.getAxisSet().getAxes()[1].adjustRange();
		}
		chart.redraw();
	}

	public Chart getChart() {
		return this.chart;
	}
	
	/**
	 * 5 different colors, color 6 is color 1,....
	 * @param numberOfLine
	 * @return
	 */
	public Color getColor(int numberOfLine) {
		Device device = Display.getCurrent();
		switch (numberOfLine % 5) { 
		case 0:
			return new Color (device, 255, 0, 0);
		case 1: 
			return new Color (device, 0, 0, 255);
		case 2:
			return new Color (device, 0, 255, 255);
		case 3: 
			return new Color (device, 0, 0, 0);
		case 4:
			return new Color (device, 255, 0, 255);
		}
		return new Color (device, 255, 0, 0);
	}

	/**
	 * loads the functions of the function-def. view and tries to draw the line for these
	 */
	private void loadFunctions() {
		try {
			int count = ModelProvider.getQualityRatings().get(qrPos)
					.getFunctions().size();
			double min = Double.MAX_VALUE;
			double max = Double.MIN_VALUE;
			Interval interval;

			// get the lowest and highest X-value
			for (int i = 0; i < count; i++) {
				interval = new Interval();
				interval.setInterval((ModelProvider.getQualityRatings()
						.get(qrPos).getFunctions().get(i).getInterval()));
				if (interval.getIntervalOK()) {
					if (interval.getA() < min) {
						min = interval.getA();
					}
					if (interval.getB() > max) {
						max = interval.getB();
					}
				}
			}

			//draw all lines
			for (int i = 0; i < count; i++) {
				interval = new Interval();
				interval.setInterval((ModelProvider.getQualityRatings()
						.get(qrPos).getFunctions().get(i).getInterval()));
				String function = ModelProvider.getQualityRatings().get(qrPos)
						.getFunctions().get(i).getTerm();
				
				//if the interval is correct and points can be calculated
				if (interval != null
						&& interval.getIntervalOK()
						&& PointsCalculator.testFunction(function,
								(interval.getB() - interval.getA()) / 2)) {
					double[] xseries = PointsCalculator.calcXSeries(interval);
					double[] yseries = PointsCalculator.calcYSeries(interval,
							function);

					ILineSeries aSeries = (ILineSeries) chart.getSeriesSet()
							.createSeries(SeriesType.LINE, "interval " + i);
					aSeries.getLabel().setVisible(false);
					aSeries.setLineColor(this.getColor(i));
					aSeries.setLineStyle(LineStyle.DASH);
					aSeries.setSymbolType(PlotSymbolType.NONE);
					aSeries.setXSeries(xseries);
					aSeries.setYSeries(yseries);
					chart.getAxisSet().adjustRange();
					
					chart.getSeriesSet().getSeries()[0].getLabel().setVisible(
							false);
					chart.redraw();

				}
			}
		} catch (Exception e) {
			//System.out.println("loadFunctions in Graph");
			e.printStackTrace();
		}

	}

	@Override
	public void setFocus() {
		chart.setFocus();

	}

}
