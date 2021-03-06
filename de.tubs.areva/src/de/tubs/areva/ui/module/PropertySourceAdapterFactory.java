/*******************************************************************************
 * Copyright (c) 2016 itemis AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Matthias Wienand (itemis AG) - initial API and implementation
 *
 *******************************************************************************/
package de.tubs.areva.ui.module;

import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.gef4.graph.Node;
import org.eclipse.gef4.graph.Edge;
import org.eclipse.ui.views.properties.IPropertySource;

/**
 *
 * @author mwienand
 *
 */
public class PropertySourceAdapterFactory implements IAdapterFactory {

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Object getAdapter(Object adaptableObject, Class adapterType) {
		if (IPropertySource.class.equals(adapterType)) {
			if (adaptableObject instanceof DARGZestNode) {
				return new NodePropertySource((Node) adaptableObject);
			} else if(adaptableObject instanceof QADAGZestNode) {
				return new QadagNodePropertySource((Node) adaptableObject);
			} else if(adaptableObject instanceof DARGZestEdge) {
				return new EdgePropertySource((Edge) adaptableObject);
			}
		}
		return null;
	}

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Class[] getAdapterList() {
		return new Class[] { IPropertySource.class };
	}

}
