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
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
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
		return "CreateArgModel";
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return "CreateArgModel";
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
