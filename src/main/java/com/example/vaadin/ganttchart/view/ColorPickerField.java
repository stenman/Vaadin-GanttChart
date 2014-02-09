package com.example.vaadin.ganttchart.view;

import com.vaadin.shared.ui.colorpicker.Color;
import com.vaadin.ui.ColorPicker;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomField;
import com.vaadin.ui.components.colorpicker.ColorChangeEvent;
import com.vaadin.ui.components.colorpicker.ColorChangeListener;

public class ColorPickerField extends CustomField<String> {

	private static final long serialVersionUID = 1L;

	private ColorPicker colorPicker;

	public ColorPickerField(String caption, Color initialColor) {
		this.colorPicker = new ColorPicker(caption, initialColor);
		this.colorPicker.setPosition(750, 200);
		this.colorPicker.addColorChangeListener(new ColorChangeListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void colorChanged(ColorChangeEvent event) {
				setValue(new String(event.getColor().getCSS()));
			}
		});

	}

	@Override
	protected Component initContent() {
		return this.colorPicker;
	}

	@Override
	public Class<? extends String> getType() {
		return String.class;
	}

	@Override
	public String getInternalValue() {
		return new String(this.colorPicker.getColor().getCSS());
	}

	@Override
	public void setInternalValue(String newFieldValue) {
		// Not needed, keep for history - remove after commit
		// this.colorPicker.setColor(new com.vaadin.shared.ui.colorpicker.Color(newFieldValue.getRed(), newFieldValue.getGreen(), newFieldValue
		// .getBlue(), newFieldValue.getAlpha()));
		super.setInternalValue(newFieldValue);
	}

}