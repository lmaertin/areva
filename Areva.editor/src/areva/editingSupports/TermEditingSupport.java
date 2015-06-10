package areva.editingSupports;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;

import areva.content.Function;

/**
 * 
 * This class offers to edit the terms e.g in functionDefinitionView. I
 * decided to make one field editable by using left click, the boolean editable
 * was made to make it depending on another action, and this is why it is
 * commented out at the moment
 * 
 * @author Sven von Höveling
 * @version 1.0
 *
 */
public class TermEditingSupport extends EditingSupport{
	private final TableViewer viewer;
	  private final CellEditor editor;
	  //private static boolean editable;

	  public TermEditingSupport(TableViewer viewer)  {
	    super(viewer);
	    this.viewer = viewer;
	    this.editor = new TextCellEditor(viewer.getTable());
	    //editable = false;

	  }

	  @Override
	  protected CellEditor getCellEditor(Object element) {
	    return editor;
	  }

	  @Override
	  protected boolean canEdit(Object element) {;
		  return true;
	  }
	  
	  //Not used because this table is always editable
	  public static void setEditable(boolean bool) {
		  //editable = bool;
	  }

	  @Override
	  protected Object getValue(Object element) {
			  return ((Function) element).getTerm();
	  }

	  @Override
	  protected void setValue(Object element, Object value) {
		  ((Function) element).setTerm(String.valueOf(value));
		  viewer.update(element, null);

	    
	  }
}
