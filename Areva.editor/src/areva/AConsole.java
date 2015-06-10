package areva;


import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.IConsoleManager;
import org.eclipse.ui.console.MessageConsole;
import org.eclipse.ui.console.MessageConsoleStream;


/**
 * AReva-Console
 * @author Sven
 * @version 1.0
 * Areva Console
 *
 */

public class AConsole {
	private static MessageConsole neu = null;
	
	public static MessageConsoleStream out;
	
	/**
	 * initializes the console
	 */
	public static void init() {
		if (neu == null) {
			neu = new MessageConsole("Areva", null);
			ConsolePlugin plugin = ConsolePlugin.getDefault();
			IConsoleManager conMan = plugin.getConsoleManager();
			conMan.addConsoles(new IConsole[]{neu});
			out = neu.newMessageStream();
		}
		
	}
	
	
}

