package de.tubs.areva.ui.module;

import org.eclipse.gef4.common.adapt.inject.AdapterInjectionSupport;
import org.eclipse.gef4.zest.fx.ZestFxModule;

public class DARGModule extends ZestFxModule {

	@Override
	protected void enableAdapterMapInjection() {
		install(new AdapterInjectionSupport());
	}
}
