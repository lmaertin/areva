package attempts;

import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.IConsoleManager;
import org.eclipse.ui.console.MessageConsole;
import org.eclipse.ui.console.MessageConsoleStream;


/**
 * 
 * @author Sven
 * @version 0.7
 * Stellt Konsole für das Evaluation bereit
 * Bei Verwendung intialisiert
 *
 */

public class EvaluationConsole {
	private static MessageConsole neu = null;
	
	public static MessageConsoleStream out;
	
	public static void init() {
		if (neu == null) {
			neu = new MessageConsole("ArevaEvaluation", null);
			ConsolePlugin plugin = ConsolePlugin.getDefault();
			IConsoleManager conMan = plugin.getConsoleManager();
			conMan.addConsoles(new IConsole[]{neu});
			out = neu.newMessageStream();
		}
		
	}
	
	
}