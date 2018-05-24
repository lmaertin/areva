package de.tubs.areva.transaction.commands;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.transaction.TransactionalEditingDomain;

import de.tubs.areva.emf.model.darg.File;

public class CreateArgModelCommand implements Command {

	TransactionalEditingDomain domain = null;
	URI outputURI = null;
	ResourceSet resourceSet = null;
	
	File createdArg = null;
	File blueprintArg = null;
	
	public CreateArgModelCommand(TransactionalEditingDomain domain, URI outputURI, File arg) {
		
		this.domain = domain;
		this.outputURI = outputURI;
		
		resourceSet = domain.getResourceSet();
		
		blueprintArg = arg;
	}
	
	public de.tubs.areva.emf.model.darg.File getCreatedArg() {
		return createdArg;
	}
	
	@Override
	public boolean canExecute() {
		return true;
	}

	@Override
	public void execute() {
		createdArg = blueprintArg;
		
		final Resource resource = resourceSet.createResource(outputURI);
		resource.getContents().add(createdArg);
		
		try {
		    resource.save(Collections.EMPTY_MAP);
		} catch (IOException e) {
			System.out.println("ERROR IO Exception");
		    throw new RuntimeException(e);
		}
		
		System.out.println("Created new Arg");
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
		
		return "CreateArgModel";
	}

	@Override
	public String getDescription() {
		
		return "CreateArgModel";
	}

	@Override
	public void dispose() {
		
		
	}

	@Override
	public Command chain(Command command) {
		
		return command;
	}

}
