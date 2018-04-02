package de.tubs.areva.util;

import org.palladiosimulator.pcm.repository.BasicComponent;

public class DoFBasicComponent{
	private String dofType;
	private String dofName;
	private BasicComponent comp;
	
	public DoFBasicComponent(String dofType, String dofName, BasicComponent comp) {
		this.dofType = dofType;
		this.dofName = dofName;
		this.comp = comp;
	}

	public String getDofType() {
		return dofType;
	}

	public String getDofName() {
		return dofName;
	}

	public BasicComponent getComp() {
		return comp;
	}
	
}
