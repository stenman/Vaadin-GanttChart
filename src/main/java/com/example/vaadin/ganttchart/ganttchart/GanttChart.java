package com.example.vaadin.ganttchart.ganttchart;

import java.util.Calendar;
import java.util.Date;

import javax.annotation.PostConstruct;

import org.joda.time.DateTime;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.tltv.gantt.Gantt;
import org.tltv.gantt.client.shared.Step;

import com.vaadin.ui.Notification;

@Scope("prototype")
@Component
public class GanttChart extends Gantt {

	private static final long serialVersionUID = 1L;

	@PostConstruct
	public void PostConstruct() {
		setupGanttChart();
		setupListeners();
		initStartAndEndDates();
	}

	public void addGanttEvent() {
		Step step1 = new Step("First step");
		step1.setStartDate(new DateTime().getMillis());
		step1.setEndDate(new DateTime().plusDays(14).getMillis());
		this.addStep(step1);
	}

	private void initStartAndEndDates() {
		this.setStartDate(new DateTime().toDate());
		this.setEndDate(new DateTime().plusYears(1).toDate());
	}

	private void setupListeners() {
		this.addClickListener(new Gantt.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void onGanttClick(org.tltv.gantt.Gantt.ClickEvent event) {
				Notification.show("Clicked" + event.getStep().getCaption());
			}
		});

		this.addMoveListener(new Gantt.MoveListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void onGanttMove(MoveEvent event) {
				Notification.show("Moved " + event.getStep().getCaption());
			}
		});

		this.addResizeListener(new Gantt.ResizeListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void onGanttResize(ResizeEvent event) {
				Notification.show("Resized " + event.getStep().getCaption());
			}
		});
	}

	private void setupGanttChart() {
		this.setWidth(100, Unit.PERCENTAGE);
		this.setHeight(500, Unit.PIXELS);
		this.setResizableSteps(true);
		this.setMovableSteps(true);
	}
}
