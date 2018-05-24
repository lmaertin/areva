package de.tubs.areva.transaction.commands;

import java.util.Collection;
import java.util.HashSet;

import org.eclipse.emf.common.command.Command;

public class GenericCommand implements Command {

	@Override
	public boolean canExecute() {
		
		return true;
	}

	@Override
	public void execute() {
		

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
		
		return "Generic Command";
	}

	@Override
	public String getDescription() {
		
		return "Generic Command";
	}

	@Override
	public void dispose() {
		
		
	}

	@Override
	public Command chain(Command command) {
		
		return command;
	}

}
