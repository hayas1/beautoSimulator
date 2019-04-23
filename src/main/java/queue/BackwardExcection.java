package queue;

import robot.NormalRobot;
import robot.Robot;

public class BackwardExcection implements QueueElement {

	@Override
	public void excecute(Robot robot) {
		if(robot instanceof NormalRobot) {
			return ;		//例外定義
		} else {
			((NormalRobot)robot).forward();
		}

	}


}
