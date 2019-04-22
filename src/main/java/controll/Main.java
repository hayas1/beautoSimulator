package controll;

import javax.swing.SwingUtilities;

import field.Field;
import gui.GUIMain;
import robot.NormalRobot;
import robot.Robot;

public class Main {

	public static void main(String[] args) {
		final Field field = new Field(new Lattice(Field.DEFAULT_X, Field.DEFAULT_Y));

		final Robot robot = new NormalRobot(field) {
			@Override
			public void run() {
				// TODO 自動生成されたメソッド・スタブ
			}


		};

		SwingUtilities.invokeLater(new GUIMain(field, robot));



//		robot.clockwise();
//		while(true) {
//			robot.forward();
//			System.out.println("ok");
//		}
	}
}
