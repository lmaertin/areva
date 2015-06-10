package areva.defaultPerspective;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

/**
 * Areva-default-Perspective. On the left side is the project-browser, in the
 * middle the evaluation tree, the console and property-view. On the right side
 * the metrics and quality-ratings-lists.
 * 
 * @author Sven von H�veling
 * @version 1.0
 * 
 */
public class ArevaDefaultPerspective implements IPerspectiveFactory {
	@Override
	public void createInitialLayout(IPageLayout layout) {
		defineActions(layout);
		defineLayout(layout);
	}

	/**
	 * see name
	 * @param layout
	 */
	public void defineActions(IPageLayout layout) {
		// Add "new wizards".
		layout.addNewWizardShortcut("org.eclipse.ui.wizards.new.folder");
		layout.addNewWizardShortcut("org.eclipse.ui.wizards.new.file");

		// Add "show views".
		layout.addShowViewShortcut(IPageLayout.ID_BOOKMARKS);
		layout.addShowViewShortcut(IPageLayout.ID_OUTLINE);
		layout.addShowViewShortcut(IPageLayout.ID_PROP_SHEET);
		layout.addShowViewShortcut(IPageLayout.ID_TASK_LIST);
	}

	/**
	 * see name
	 * @param layout
	 */
	public void defineLayout(IPageLayout layout) {
		String editorArea = layout.getEditorArea();

		//defining the layouts, left, right and boottom
		
		IFolderLayout left = layout.createFolder("left", IPageLayout.LEFT,
				(float) 0.15, editorArea);
		IFolderLayout right = layout.createFolder("right", IPageLayout.RIGHT,
				(float) 0.80, editorArea);
		IFolderLayout bottom = layout.createFolder("bottom",
				IPageLayout.BOTTOM, (float) 0.80, editorArea);
		
		//PackageExplorer left
		left.addView("org.eclipse.jdt.ui.PackageExplorer");
		//ConsoleView bottom
		bottom.addView("org.eclipse.ui.console.ConsoleView");
		//PropertySheet bottom
		bottom.addView("org.eclipse.ui.views.PropertySheet");
		//TabFolder right
		right.addView("areva.views.TabFolderMQR");
		//XMLSelection
		right.addView("areva.views.XMLSelection");

	}

	/**
	 * see name
	 * @author Sven
	 *
	 */
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
