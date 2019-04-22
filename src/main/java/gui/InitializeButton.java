package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import field.Field;
import robot.Robot;

public class InitializeButton extends JButton implements ActionListener {
	private final Field field;
	private final Robot robot;

	public InitializeButton(Field field, Robot robot) {
		super("Init");
		this.field = field;
		this.robot = robot;
		addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		field.init();
		robot.init();
	}


}
