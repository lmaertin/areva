/*******************************************************************************
 * Copyright (c) 2016 itemis AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Alexander Nyßen (itemis AG) - initial API and implementation
 *******************************************************************************/
package de.tubs.areva.ui.module;

import org.eclipse.gef4.geometry.planar.Dimension;
import org.eclipse.gef4.geometry.planar.Point;
import org.eclipse.gef4.graph.Node;
import org.eclipse.gef4.zest.fx.ZestProperties;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

import de.tubs.areva.emf.model.qadag.CompositeNode;

/**
 *
 * @author mwienand
 *
 */
public class QadagNodePropertySource implements IPropertySource {

	private static final String POSITION_DELIMITER = ", ";
	private static final String SIZE_DELIMITER = " x ";
	private static final IPropertyDescriptor POSITION_PROPERTY_DESCRIPTOR = new TextPropertyDescriptor(
			ZestProperties.POSITION__N, ZestProperties.POSITION__N);
	private static final IPropertyDescriptor SIZE_PROPERTY_DESCRIPTOR = new TextPropertyDescriptor(
			ZestProperties.SIZE__N, ZestProperties.SIZE__N);
	
	private static final IPropertyDescriptor NAME_PROPERTY_DESCRIPTOR = new TextPropertyDescriptor(
			"NodeName", "Name");
	private static final IPropertyDescriptor WEIGHT_PROPERTY_DESCRIPTOR = new TextPropertyDescriptor(
			"NodeWeight", "Weight");
	private static final IPropertyDescriptor VALUE_PROPERTY_DESCRIPTOR = new TextPropertyDescriptor(
			"NodeValue", "Value");
	
	private Node node;
	private Dimension initialSize;
	private Point initialPosition;

	/**
	 *
	 * @param node
	 *            {@link Node}
	 */
	public QadagNodePropertySource(Node node) {
		this.node = node;
		initialPosition = ZestProperties.getPosition(node);
		initialSize = ZestProperties.getSize(node);
	}

	@Override
	public Object getEditableValue() {
		return null;
	}

	@Override
	public IPropertyDescriptor[] getPropertyDescriptors() {
		return new IPropertyDescriptor[] { NAME_PROPERTY_DESCRIPTOR, WEIGHT_PROPERTY_DESCRIPTOR, VALUE_PROPERTY_DESCRIPTOR };
	}

	@Override
	public Object getPropertyValue(Object id) {
		
		if (NAME_PROPERTY_DESCRIPTOR.getId().equals(id)) {
			
			QADAGZestNode zestNode = (QADAGZestNode) node;
			return zestNode.getQadagNode().getName();
			
		} else if (WEIGHT_PROPERTY_DESCRIPTOR.getId().equals(id)) {
			
			QADAGZestNode zestNode = (QADAGZestNode) node;
			return ((CompositeNode)zestNode.getQadagNode()).getWeight();
			
		} else if (VALUE_PROPERTY_DESCRIPTOR.getId().equals(id)) {
			
			QADAGZestNode zestNode = (QADAGZestNode) node;
			return zestNode.getQadagNode().getValue();
		}
		
		return null;
	}

	@Override
	public boolean isPropertySet(Object id) {
		if (POSITION_PROPERTY_DESCRIPTOR.getId().equals(id)) {
			return ZestProperties.getPosition(node) != null;
		} else if (SIZE_PROPERTY_DESCRIPTOR.equals(id)) {
			return ZestProperties.getSize(node) != null;
		}
		return false;
	}

	@Override
	public void resetPropertyValue(Object id) {
		if (POSITION_PROPERTY_DESCRIPTOR.getId().equals(id)) {
			ZestProperties.setPosition(node, initialPosition);
		} else if (SIZE_PROPERTY_DESCRIPTOR.getId().equals(id)) {
			ZestProperties.setSize(node, initialSize);
		}
	}

	@Override
	public void setPropertyValue(Object id, Object value) {
		if (POSITION_PROPERTY_DESCRIPTOR.getId().equals(id)) {
			String[] xy = ((String) value).split(POSITION_DELIMITER);
			ZestProperties.setPosition(node, new Point(Double.parseDouble(xy[0]), Double.parseDouble(xy[1])));
		} else if (SIZE_PROPERTY_DESCRIPTOR.getId().equals(id)) {
			String[] wh = ((String) value).split(SIZE_DELIMITER);
			ZestProperties.setSize(node, new Dimension(Double.parseDouble(wh[0]), Double.parseDouble(wh[1])));
		}
	}

}
