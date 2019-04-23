package robot;

public abstract class Parts implements Runnable {
	private final Robot robot;

	//TODO 詳しくやるなら
	public Parts(Robot robot) {
		this.robot = robot;
	}

	public Robot getRobot() {
		return robot;
	}

}
