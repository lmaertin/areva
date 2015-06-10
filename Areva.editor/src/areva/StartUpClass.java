package areva;

import org.eclipse.ui.IStartup;

import areva.content.ModelProvider;

/**
 * This class loads the AConsole and ModelProvider on Startup
 * unfortunately ModelProvider loads to late, and this is why
 * the views are loading the ModelProvider too.
 * 
 * But this class could be important in the future.
 * 
 * @author Sven von Höveling
 * @version 1.0
 *
 */
public class StartUpClass implements IStartup {

	@Override
	public void earlyStartup() {
		ModelProvider.load();
		AConsole.init();
		
	}
}
