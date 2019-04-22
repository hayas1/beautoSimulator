package robot;

import field.Field;

/**
 * シミュレーション用ロボット なるべくCライクなコードを書く
 * 注意 フィールドの配列のインデックスは絶対0から始めた方が良い
 * @author hayas
 *
 */
public class CustomizeRobot extends NormalRobot {

	public CustomizeRobot(Field field) {
		super(field);
		//コンストラクタではセンサーの位置などを定義しておく
	}

	@Override
	public void run() {
		while(true) {

		}

	}


}
