package controll;

import javax.swing.SwingUtilities;

import field.Field;
import gui.GUIMain;
import robot.CustomizeRobot;
import robot.Robot;

public class Main {

	public static void main(String[] args) {
		final Field field = new Field(new Lattice(Field.DEFAULT_X, Field.DEFAULT_Y));

		final Robot robot = new CustomizeRobot(field);

		SwingUtilities.invokeLater(new GUIMain(field, robot));



//		robot.clockwise();
//		while(true) {
//			robot.forward();
//			System.out.println("ok");
//		}
	}
}
