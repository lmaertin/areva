package de.tubs.areva.ui.view;

import org.eclipse.gef4.graph.Graph;
import org.eclipse.gef4.zest.fx.ui.ZestFxUiModule;
import org.eclipse.gef4.zest.fx.ui.parts.ZestFxUiView;

import com.google.inject.Guice;
import com.google.inject.util.Modules;

import de.tubs.areva.ui.module.DARGModule;

public class DARGZestView extends ZestFxUiView {

	public DARGZestView() {
		super(Guice.createInjector(Modules.override(new DARGModule()).with(new ZestFxUiModule())));
		setGraph(new Graph());
	}
}
