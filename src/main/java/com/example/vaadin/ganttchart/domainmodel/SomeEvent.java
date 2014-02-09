package com.example.vaadin.ganttchart.domainmodel;

import org.joda.time.DateTime;
import org.tltv.gantt.client.shared.Step;

public class SomeEvent extends Step{

	private static final long serialVersionUID = 1L;

	private String name;
	private DateTime start;
	private DateTime end;

	public SomeEvent() {
	}

	public SomeEvent(String name, DateTime start, DateTime end) {
		this.setCaption(name);
		this.setStartDate(start.getMillis());
		this.setEndDate(end.getMillis());
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public DateTime getStart() {
		return start;
	}

	public void setStart(DateTime start) {
		this.start = start;
	}

	public DateTime getEnd() {
		return end;
	}

	public void setEnd(DateTime end) {
		this.end = end;
	}
}
