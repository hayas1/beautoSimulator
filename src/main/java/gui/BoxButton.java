package gui;

import java.util.List;

import javax.swing.JToggleButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class BoxButton extends JToggleButton implements ChangeListener {
	private final List<JToggleButton> toggleButtons;

	public BoxButton(List<JToggleButton> toggleButtons) {
		super("Box");
		this.toggleButtons = toggleButtons;
		addChangeListener(this);
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		toggleButtons
		.stream()
		.filter(button -> !button.equals(this))
		.forEach(button -> button.setSelected(false));
	}


}
