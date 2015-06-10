package areva.editingSupports;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;

import areva.content.Metric;
import areva.content.QualityRating;


/**
 * This class offers to edit the name/strings in table views e.g. metrics-view
 * @author Sven von Höveling
 * @version 1.0
 *
 */
public class NameEditingSupport extends EditingSupport {

	private final TableViewer viewer;
	private final CellEditor editor;
	private static boolean editable;

	public NameEditingSupport(TableViewer viewer) {
		super(viewer);
		this.viewer = viewer;
		this.editor = new TextCellEditor(viewer.getTable());
		editable = false;

	}

	@Override
	protected CellEditor getCellEditor(Object element) {
		return editor;
	}

	@Override
	protected boolean canEdit(Object element) {
		return NameEditingSupport.editable;
	}

	public static void setEditable(boolean bool) {
		editable = bool;
	}

	@Override
	protected Object getValue(Object element) {
		if (element.getClass() == QualityRating.class) {
			return ((QualityRating) element).getName();
		} else if (element.getClass() == Metric.class) {
			System.out.println("muhaha");
			return ((Metric) element).getName();
		}
		return null;

	}

	@Override
	protected void setValue(Object element, Object value) {
		if (element.getClass() == QualityRating.class) {
			((QualityRating) element).setName(String.valueOf(value));
			viewer.update(element, null);
		} else if (element.getClass() == Metric.class) {
			((Metric) element).setName(String.valueOf(value));
			viewer.update(element, null);
		}

	}

}
