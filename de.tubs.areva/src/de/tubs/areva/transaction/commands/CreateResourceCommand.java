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
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void undo() {
		// TODO Auto-generated method stub

	}

	@Override
	public void redo() {
		// TODO Auto-generated method stub

	}

	@Override
	public Collection<?> getResult() {
		// TODO Auto-generated method stub
		return new HashSet<>();
	}

	@Override
	public Collection<?> getAffectedObjects() {
		// TODO Auto-generated method stub
		return new HashSet<>();
	}

	@Override
	public String getLabel() {
		// TODO Auto-generated method stub
		return "CreateResource";
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return "CreateResource";
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public Command chain(Command command) {
		// TODO Auto-generated method stub
		return command;
	}

}
