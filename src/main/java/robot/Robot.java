package robot;

import java.awt.Dimension;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.List;

import controll.Position;
import field.Field;

/**
 * ロボット全般 継承して用いる
 * 基本動作
 *  フィールド取得
 *  現在地
 *  現在角度
 *
 */


public abstract class Robot implements Runnable {
	private final Field field;
	private final List<Parts> parts = new ArrayList<>();


	public Robot(Field field) {
		this.field = field;
	}

	public abstract void init();
	public abstract void initAngle(Angle angle);
	public abstract Position getPosition();
	public abstract Angle getAngle();

	public Field getField() {
		return field;
	}

	@Override
	public void run() {
		//TODO パーツ全部動かす
	}

	//半径rの五角形をpositionを中心に作成
	public Polygon form(double r, Dimension position) {
		int[]  x = new int[5];
		int[]  y = new int[5];
		double[] angle = {0, 60, 140, 220, 300};		//60, 80, 80, 80, 60

		double Delta = getAngle().toRadian();
		for (int i=0; i < 5; i++) {
			x[i] = (int)(r*Math.cos(Math.toRadians(angle[i])+Delta) + position.getWidth());
			y[i] = (int)(r*Math.sin(Math.toRadians(angle[i])+Delta) + position.getHeight());
		}
		return new Polygon(x,y,5);
	}

	public void addParts(Parts parts) {
		this.parts.add(parts);
	}

	public List<Parts> getParts() {
		return parts;
	}

	//普通にboolean値で取得する方向で進める
//	public void sensed(Parts parts) {
//		return;		//センサーが反応したときの動作を記述する
//	}

}
