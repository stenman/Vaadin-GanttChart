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
@SuppressWarnings("serial")
@PreserveOnRefresh
public class MyVaadinUI extends UI {

	@Override
	protected void init(VaadinRequest request) {
		@SuppressWarnings("unused")
		DiscoveryNavigator navigator = new DiscoveryNavigator(this, this);
	}
}


