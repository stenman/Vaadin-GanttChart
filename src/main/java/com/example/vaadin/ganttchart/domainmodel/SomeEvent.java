package com.example.vaadin.ganttchart.domainmodel;

import org.joda.time.DateTime;

public class SomeEvent {

	private String name;
	private DateTime start;
	private DateTime end;

	public SomeEvent() {
	}

	public SomeEvent(String name, DateTime start, DateTime end) {
		this.name = name;
		this.start = start;
		this.end = end;
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