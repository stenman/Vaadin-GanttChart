package com.example.vaadin.ganttchart.main;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import ru.xpoft.vaadin.DiscoveryNavigator;

import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;

/**
 * Entry point of the application. A Navigator is used to redirect between different views.
 * 
 * @author Roger
 * 
 */
@Controller
@Scope("prototype")
@PreserveOnRefresh
public class MyVaadinUI extends UI {

	private static final long serialVersionUID = 1L;

	@Override
	protected void init(VaadinRequest request) {
		@SuppressWarnings("unused")
		DiscoveryNavigator navigator = new DiscoveryNavigator(this, this);
	}
}


