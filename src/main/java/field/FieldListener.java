package field;

import gui.FieldView;

public class FieldListener {
	private final FieldView view;

	public FieldListener(FieldView view) {
		this.view = view;
	}

	public void updated(Field f) {
		view.repaint();
	}

}
