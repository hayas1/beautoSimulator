package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import robot.Robot;

public class StartButton extends JButton implements ActionListener {
	private final GUIMain guiMain;
	private final Robot robot;
	//private final Thread thread;

	public StartButton(GUIMain guiMain, Robot robot) {
		super("Start");
		this.guiMain = guiMain;
		this.robot = robot;
		//this.thread = new Thread(robot);
		addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		guiMain.buttonLock();
		robot.run();
		//TODO 本気でやるなら停止とか再開組み込み
	}


}
