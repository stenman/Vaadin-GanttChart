package com.example.vaadin.ganttchart.ganttchart;
import static org.junit.Assert.*;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import org.tltv.gantt.client.shared.Step;

import com.example.vaadin.ganttchart.ganttchart.GanttChart;

@RunWith(MockitoJUnitRunner.class)
public class GanttChartTest {

	@InjectMocks
	private GanttChart ganttChart;

	private DateTime now;

	@Before
	public void setUp() throws Exception {
		now = new DateTime();
	}

	@Test
	public void AddGanttStep_CaptionAndValidStartDateTimeAndValidEndDateTime_StepAdded() {
		// Arrange
		String expectedCaption = "test";
		long expectedStartDate = now.getMillis();
		long expectedEndDate = now.plusDays(14).getMillis();

		// Act
		ganttChart.addGanttStep("test", now, now.plusDays(14));

		// Assert
		for (Step step : ganttChart.getSteps()) {
			assertEquals(expectedCaption, step.getCaption());
			assertEquals(expectedStartDate, step.getStartDate());
			assertEquals(expectedEndDate, step.getEndDate());
		}
	}

}
