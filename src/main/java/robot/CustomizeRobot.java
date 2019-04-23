package robot;

import field.Field;

/**
 * シミュレーション用ロボット なるべくCライクなコードを書く
 * 注意 フィールドの配列のインデックスは絶対0から始めた方が良い
 * @author hayas
 *
 */
public class CustomizeRobot extends NormalRobot {
	private final Senser senser1 = new Senser(this, new Angle(Angle.FORWARD));

	public CustomizeRobot(Field field) {
		super(field);
		//コンストラクタではセンサーの位置などを定義しておく
		initAngle(new Angle(Angle.NORTH));
		addParts(senser1);

	}

	@Override
	public void run() {
		super.run();
		while(true) {
			if(senser1.isSensed()) {
				System.out.println();
			}

		}

	}

//	@Override
//	public void sensed(Parts parts) {
//		final Senser senser = (Senser)parts;
//
//		senser.getAngle();
//	}


}
