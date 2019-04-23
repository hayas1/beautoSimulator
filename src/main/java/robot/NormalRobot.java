package robot;

import controll.Lattice;
import controll.Position;
import field.Field;

/**
 *
 * マス目をまたがずに動くロボット
 * 基本動作
 *  現在地(整数値のみ)
 *  現在角度(継承)
 * 	前進
 *  後進
 *  回転(90度ずつ)
 *
 */
public abstract class NormalRobot extends Robot {
	private Lattice dimension;
	private Angle angle;

	public NormalRobot(Field field) {
		super(field);
		init();
	}

	@Override
	public void init() {
		dimension = new Lattice(1, 1);
		angle = new Angle(Angle.EAST);
	}

	@Override
	public void initAngle(Angle angle) {
		this.angle = angle;
	}

	@Override
	public Position getPosition() {
		return getDimension().toPosition();
	}

	@Override
	public Angle getAngle() {
		return angle;
	}

	public Lattice getDimension() {
		return dimension;
	}

	//TODO キューの操作
	public void forward() {		//1マス前進
		dimension = dimension.plus(getAngle().toLattice());
		getField().rangeCheck(getDimension());
		getField().fieldUpdate();
	}

	public void backward() {		//1マス後進
		dimension = dimension.minus(getAngle().toLattice());
		getField().rangeCheck(getDimension());
		getField().fieldUpdate();
	}

	public void clockwise() {		//時計回りに90度回転
		angle = angle.plus(new Angle(90));
		getField().fieldUpdate();
	}

	public void counterClockwise() {		//反時計回りに90度回転
		angle = angle.minus(new Angle(90));
		getField().fieldUpdate();
	}



}
