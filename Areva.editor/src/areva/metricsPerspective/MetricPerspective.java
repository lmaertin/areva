
package areva.metricsPerspective;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

/**
 * Metric-perspective. Left: metrics-list. Middle: metrics-editor and console. Right: editing-help.
 * @author Sven von Höveling
 * @version 1.0
 */
public class MetricPerspective implements IPerspectiveFactory {
	@Override
	public void createInitialLayout(IPageLayout layout) {
	    defineActions(layout);
	    defineLayout(layout);
	}
	
	public void defineActions(IPageLayout layout) {
         //Add "new wizards".
        layout.addNewWizardShortcut("org.eclipse.ui.wizards.new.folder");
        layout.addNewWizardShortcut("org.eclipse.ui.wizards.new.file");

        // Add "show views".
        layout.addShowViewShortcut(IPageLayout.ID_BOOKMARKS);
        layout.addShowViewShortcut(IPageLayout.ID_OUTLINE);
        layout.addShowViewShortcut(IPageLayout.ID_PROP_SHEET);
        layout.addShowViewShortcut(IPageLayout.ID_TASK_LIST);
        
}
	public void defineLayout(IPageLayout layout) {
        // Editors are placed for free.
        String editorArea = layout.getEditorArea();
        IFolderLayout left =
                layout.createFolder("left", IPageLayout.LEFT, (float) 0.15, editorArea);
        IFolderLayout right =
                layout.createFolder("right", IPageLayout.RIGHT, (float) 0.60, editorArea);
        IFolderLayout top =
                layout.createFolder("top", IPageLayout.TOP, (float) 0.80, editorArea);
        IFolderLayout bottom =
        		layout.createFolder("bottom", IPageLayout.BOTTOM, (float) 0.20, editorArea);

        left.addView("areva.views.Metrics");
        
        right.addView("areva.views.EditingHelp");
        top.addView("areva.views.MetricEditor");
        bottom.addView("org.eclipse.ui.console.ConsoleView");


        layout.setEditorAreaVisible(false);
        
        


}
	public interface IPerspectivePlugin {
        /**
         * Plugin id.
         */
        public final static String PLUGIN_ID = "org.eclipse.ui.articles.perspective";
        
        /**
         * Test perspective id.
         */
        public final static String TEST_PERSPECTIVE_ID = PLUGIN_ID + ".Test0";
}
	
}
