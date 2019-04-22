package field;

import java.awt.Color;
import java.awt.Graphics;

public class EmptySquare extends FieldSquare {
	private int point = 0;
	private int boxNum = 0;
	private boolean isBonus = false;	//通ると加算のマス

	@Override
	public int getType() {
		return FieldSquare.EMPTY;
	}

	@Override
	public boolean isEmpty() {
		return true;
	}

	public int getPoint() {
		return point;
	}

	//より小さいポイントが付与されるときは無視(加算かもしれないので後で仕様確認)(そういうパターン存在しないかもしれない)
	public void setPoint(int point) {
		if(this.point < point) {
			this.point = point;
		}
	}

	public void initPoint() {
		point = 0;
		isBonus = false;
	}

	public int getBoxNum() {
		return boxNum;
	}

	public void setBoxNum(int boxNum) {
		this.boxNum = boxNum;
	}

	public boolean isBonus() {
		return isBonus;
	}

	public void setBonus(boolean isBonus) {
		this.isBonus = isBonus;
	}

	@Override
	public void paint(int x1, int y1, int x2, int y2, Graphics g) {
		final int xMid = (x1+x2) / 2;
		final int yMid = (y1+y2) / 2;

		final int fontSize = g.getFont().getSize();
		final String point = Integer.toString(getPoint());
		final int xShift = fontSize * point.length() / 2;

		if(isBonus()) {
			final Graphics gCopy = g.create();
			gCopy.setColor(Color.CYAN);
			gCopy.drawString(point + "+" + Field.BONUS_POINT, xMid-xShift, yMid);
		} else {
			g.drawString(point, xMid-xShift, yMid);
		}
	}



}
