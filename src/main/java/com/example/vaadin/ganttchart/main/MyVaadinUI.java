package com.example.vaadin.ganttchart.main;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

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
//		@SuppressWarnings("unused")
//		DiscoveryNavigator navigator = new DiscoveryNavigator(this, this);
		
	    final VerticalLayout layout = new VerticalLayout();
	    layout.setMargin(true);
	    setContent(layout);
	    
	    Button button = new Button("Click Me");
	    button.addClickListener(new Button.ClickListener() {
	        public void buttonClick(ClickEvent event) {
	            layout.addComponent(new Label("Thank you for clicking"));
	        }
	    });
	    layout.addComponent(button);
	}
}


