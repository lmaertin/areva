package de.tubs.areva.script;

import java.util.Collection;
import org.eclipse.emf.ecore.EObject;

public interface IScriptExecutor {
	
	public void init(EObject root);

	public Object executeMethod(String methodName, EObject parameterObject);
	
	public Collection<?> getAvailableMethodNames(EObject node);
}
