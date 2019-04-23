package robot;

import controll.Lattice;

public class Senser extends Parts {
	private final Angle angle;
	private boolean isSensed;

	public Senser(Robot robot, Angle angle) {
		super(robot);
		this.angle = angle;
	}

	@Override
	public void run() {
		while(true) {
			sense();
		}

	}

	public Angle getAngle() {
		return angle;
	}

	public void sense() {
		final Lattice senseLattice = getRobot().getPosition().toLattice().plus(getAngle().toLattice());
		if(getRobot().getField().isBox(senseLattice)) {
			isSensed = true;
			//getRobot().sensed(this);
		} else {
			isSensed = false;
		}
	}

	public boolean isSensed() {
		return isSensed;
	}


}
