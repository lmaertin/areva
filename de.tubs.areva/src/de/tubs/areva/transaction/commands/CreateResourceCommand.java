package de.tubs.areva.transaction.commands;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.transaction.TransactionalEditingDomain;

public class CreateResourceCommand implements Command {

	TransactionalEditingDomain domain = null;
	String outputURIString = "";
	EObject resourceRoot;

	public CreateResourceCommand(TransactionalEditingDomain domain, String outputURIString, EObject resourceRoot) {
		this.domain = domain;
		this.outputURIString = outputURIString;
		this.resourceRoot = resourceRoot;
	}
	
	@Override
	public boolean canExecute() {
		
		return true;
	}

	@Override
	public void execute() {
		
		ResourceSet resourceSet = domain.getResourceSet();
		
		final Resource resource = resourceSet.createResource(
				URI.createPlatformResourceURI(outputURIString, true)
				);
		
		resource.getContents().add(resourceRoot);
		
		
		try {
			Map<Object, Object> saveOptions = ((XMLResource)resource).getDefaultSaveOptions();
		     saveOptions.put(XMLResource.OPTION_CONFIGURATION_CACHE, Boolean.TRUE);
		     saveOptions.put(XMLResource.OPTION_USE_CACHED_LOOKUP_TABLE, new ArrayList<Object>());
		    resource.save(Collections.EMPTY_MAP);
		} catch (IOException e) {
			System.out.println("ERROR IO Exception");
		    throw new RuntimeException(e);
		}
		
		
		System.out.println("Created new Resource");

	}

	@Override
	public boolean canUndo() {
		
		return false;
	}

	@Override
	public void undo() {
		

	}

	@Override
	public void redo() {
		

	}

	@Override
	public Collection<?> getResult() {
		
		return new HashSet<>();
	}

	@Override
	public Collection<?> getAffectedObjects() {
		
		return new HashSet<>();
	}

	@Override
	public String getLabel() {
		
		return "CreateResource";
	}

	@Override
	public String getDescription() {
		
		return "CreateResource";
	}

	@Override
	public void dispose() {
		

	}

	@Override
	public Command chain(Command command) {
		
		return command;
	}

}
