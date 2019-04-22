package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import controll.Lattice;
import field.Field;
import field.FieldListener;
import robot.Robot;

public class GUIMain implements Runnable {
	public static final int NORMAL_MODE = 0;
	public static final int BOX_MODE = 1;
	public static final int MARKER_MODE = 2;

	private final Field field;
	private final Robot robot;
	private final FieldView view;
	private final List<JToggleButton> toggleButtons = new ArrayList<>();
	private final List<AbstractButton> buttons = new ArrayList<>();
	private final BoxButton boxButton = new BoxButton(toggleButtons);
	private final MarkerButton markerButton = new MarkerButton(toggleButtons);
	private final StartButton startButton;


	public GUIMain(Field field, Robot robot) {
		this.field = field;
		this.robot = robot;

		startButton = new StartButton(this,robot);
		startButton.setEnabled(false);
		view = new FieldView(this, field, robot);
	}

	@Override
	public void run() {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screenSize = toolkit.getScreenSize();
		int screenWidth = screenSize.width;
		int screenHeight = screenSize.height;

		//frame setting
		JFrame frame = new JFrame("Beauto Simulator");
		frame.setLocationByPlatform(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});
		frame.setPreferredSize(new Dimension(screenWidth/2, screenHeight/2));
		frame.setLayout(new BorderLayout());

		//base setting
		JPanel base = new JPanel();
		frame.setContentPane(base);
		base.setPreferredSize(new Dimension(500,400));
		frame.setMinimumSize(new Dimension(400,200));
		base.setLayout(new BorderLayout());

		//field view setting
		base.add(view, BorderLayout.CENTER);
		field.addFieldListener(new FieldListener(view));

		//button panel setting
		JPanel buttonPanel = new JPanel();
		base.add(buttonPanel, BorderLayout.SOUTH);
		buttonPanel.setLayout(new FlowLayout());

		//init button setting
		InitializeButton initializeButton = new InitializeButton(field, robot);
		buttons.add(initializeButton);
		buttonPanel.add(initializeButton);

		//box button setting
		toggleButtons.add(boxButton);
		buttons.add(boxButton);
		buttonPanel.add(boxButton);

		//marker button setting
		toggleButtons.add(markerButton);
		buttons.add(markerButton);
		buttonPanel.add(markerButton);

		//start button setting
		buttons.add(startButton);
		buttonPanel.add(startButton);

		frame.pack();
		frame.setVisible(true);

	}

	public void viewClicked(int xIndex, int yIndex) {
		if(boxButton.isSelected()) {
			field.setBox(new Lattice(xIndex, yIndex));
		} else if(markerButton.isSelected()) {
			field.setMarker(new Lattice(xIndex, yIndex));
			field.decideAnswer();
		}

		if(field.startable()) {
			field.decideAnswer();
			startButton.setEnabled(true);
		} else {
			field.initEmpty();
			startButton.setEnabled(false);
		}
	}

	public void buttonLock() {
		buttons.forEach(button -> button.setEnabled(false));
	}

}
