package com.example.vaadin.ganttchart.view;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ru.xpoft.vaadin.VaadinView;

import com.example.vaadin.ganttchart.ganttchart.GanttChart;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.VerticalLayout;

@Component
@Scope("prototype")
@VaadinView(value = ExampleView.NAME, cached = true)
public class ExampleView extends VerticalLayout implements View {

	private static final long serialVersionUID = 1L;

	private static final Logger logger = LoggerFactory.getLogger(ExampleView.class);

	public static final String NAME = "";

	@Inject
	private GanttChart ganttChart;

	private HorizontalLayout menuLayout;
	private HorizontalLayout ganttChartLayout;

	private Button addEvent;

	public ExampleView() {
		setupButtons();
	}

	@PostConstruct
	public void PostConstruct() {
		initLayout();
		logger.info("View initialization completed.");
	}

	private void setupButtons() {
		addEvent = new Button("Add Gantt Event");
		addEvent.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				ganttChart.addGanttEvent();
				Notification.show("Even added!", Type.HUMANIZED_MESSAGE);
			}
		});
	}

	@Override
	public void enter(ViewChangeEvent event) {
	}

	private void initLayout() {
		this.setSizeFull();

		menuLayout = new HorizontalLayout();
		ganttChartLayout = new HorizontalLayout();

		menuLayout.setMargin(true);
		menuLayout.addComponent(addEvent);

		MarginInfo mi = new MarginInfo(false, true, false, true);
		ganttChartLayout.setMargin(mi);
		ganttChartLayout.addComponent(ganttChart);
		ganttChartLayout.setSizeFull();
		
		this.addComponent(menuLayout);
		this.addComponent(ganttChartLayout);

		this.setExpandRatio(ganttChartLayout, 1);
	}
}
