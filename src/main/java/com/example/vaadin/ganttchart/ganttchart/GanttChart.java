package com.example.vaadin.ganttchart.ganttchart;

import java.util.Calendar;
import java.util.Date;

import javax.annotation.PostConstruct;

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
		Calendar cal = Calendar.getInstance();

		cal.setTime(new Date());
		Step step1 = new Step("First step");
		step1.setStartDate(cal.getTime().getTime());
		cal.add(Calendar.MONTH, 2);
		step1.setEndDate(cal.getTime().getTime());

		this.addStep(step1);
	}

	private void initStartAndEndDates() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		this.setStartDate(cal.getTime());
		cal.add(Calendar.YEAR, 1);
		this.setEndDate(cal.getTime());
	}

	private void setupListeners() {
		this.addClickListener(new Gantt.ClickListener() {
			@Override
			public void onGanttClick(org.tltv.gantt.Gantt.ClickEvent event) {
				Notification.show("Clicked" + event.getStep().getCaption());
			}
		});

		this.addMoveListener(new Gantt.MoveListener() {

			@Override
			public void onGanttMove(MoveEvent event) {
				Notification.show("Moved " + event.getStep().getCaption());
			}
		});

		this.addResizeListener(new Gantt.ResizeListener() {

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
