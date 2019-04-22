package robot;

import controll.Lattice;
import controll.UndefinedException;

/**
 *
 * ロボット: 右が0、そこから反時計回りに360で一周
 * パーツ:ロボットの進む方向が0、そこから反時計回りに360で一周
 *
 */
public class Angle {
	public static final int EAST = 0;
	public static final int SOUTH = 90;
	public static final int WEST = 180;
	public static final int NORTH = -90;
	public static final int FORWARD = 0;
	public static final int LEFT = 90;
	public static final int BEHIND = 180;
	public static final int RIGHT = -90;

	private int angle;

	public Angle(int angle) {
		this.angle = angle;
	}


	public int getAngle() {
		return angle;
	}

	public int getNormalization() {
		if(angle>180) {
			return angle -= 360;
		} else {
			return angle;
		}
	}

	public void setAngle(int angle) {
		this.angle = angle;
	}

	//副作用がある
	public Angle plus(Angle angle) {
		setAngle(getAngle() + angle.getAngle());
		return this;
	}

	//副作用がある
	public Angle minus(Angle angle) {
		setAngle(getAngle() - angle.getAngle());
		return this;
	}

	public Angle normalization() {
		setAngle(getNormalization());
		return this;
	}

	//0->(1.0), 90->(0,1), 180->(-1,0), -90->(0,-1)
	public Lattice toLattice() {		//TODO 現在上下左右しか考えていない
		if(getNormalization() == EAST) {
			return new Lattice(1, 0);
		} else if(getNormalization() == SOUTH) {
			return new Lattice(0, 1);
		} else if(getNormalization() == WEST) {
			return new Lattice(-1, 0);
		} else if(getNormalization() == NORTH) {
			return new Lattice(0, -1);
		} else {
			throw new UndefinedException("undefined arg to dimension: " + getNormalization());
		}
	}

	public double toRadian() {
		return Math.toRadians(getNormalization());
	}


}
