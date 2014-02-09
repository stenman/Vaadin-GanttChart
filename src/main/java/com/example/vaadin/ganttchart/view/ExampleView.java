package com.example.vaadin.ganttchart.view;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.tltv.gantt.client.shared.Step;

import ru.xpoft.vaadin.VaadinView;

import com.example.vaadin.ganttchart.domainmodel.SomeEvent;
import com.example.vaadin.ganttchart.ganttchart.GanttChart;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.converter.DateToLongConverter;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.shared.ui.colorpicker.Color;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ColorPicker;
import com.vaadin.ui.DateField;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

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

	private void setupButtons() {
		addEvent = new Button("Add Gantt Event");
		addEvent.addClickListener(new ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				SomeEvent someEvent = new SomeEvent("", new DateTime(), new DateTime().plusDays(14));
				openStepEditor(someEvent);
			}
		});
	}

	private void openStepEditor(Step step) {
		final Window win = new Window("Step Editor");
		win.center();

		BeanItem<Step> item = new BeanItem<Step>(step);

		final FieldGroup group = new FieldGroup(item);
		group.setBuffered(true);

		TextField captionField = new TextField("Caption");
		captionField.setNullRepresentation("");
		group.bind(captionField, "caption");

		Color initialColor = new Color(55, 99, 177);
		ColorPickerField colorPicker = new ColorPickerField("Select Color", initialColor);
		group.bind(colorPicker, "backgroundColor");

		DateField startDate = new DateField("Start date");
		startDate.setDateFormat("yyyy-MM-dd");
		startDate.setConverter(new DateToLongConverter());
		group.bind(startDate, "startDate");

		DateField endDate = new DateField("End date");
		endDate.setDateFormat("yyyy-MM-dd");
		endDate.setConverter(new DateToLongConverter());
		group.bind(endDate, "endDate");

		VerticalLayout content = new VerticalLayout();
		content.setMargin(true);
		content.setSpacing(true);
		win.setContent(content);

		content.addComponent(captionField);
		content.addComponent(colorPicker);
		content.addComponent(startDate);
		content.addComponent(endDate);

		HorizontalLayout buttons = new HorizontalLayout();
		content.addComponent(buttons);

		Button ok = new Button("Ok", new ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				try {
					group.commit();
					@SuppressWarnings("unchecked")
					Step step = ((BeanItem<Step>) group.getItemDataSource()).getBean();
					if (!ganttChart.getSteps().contains(step)) {
						ganttChart.addStep(step);
					}
					win.close();
				} catch (CommitException e) {
					Notification.show("Commit failed", e.getMessage(), Type.ERROR_MESSAGE);
					e.printStackTrace();
				}
			}
		});
		Button cancel = new Button("Cancel", new ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				group.discard();
				win.close();
			}
		});
		buttons.addComponent(ok);
		buttons.addComponent(cancel);
		win.setClosable(true);

		getUI().addWindow(win);
	}

}
