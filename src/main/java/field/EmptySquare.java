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
		final int yShift = fontSize/2;

		final Graphics gCopy = g.create();

		if(getPoint() == Field.ANSWER_POINT) {
			gCopy.setColor(Color.ORANGE);
		} else if(getPoint() == Field.SAME_X_POINT) {
			gCopy.setColor(Color.GREEN);
		} else if(getPoint() == Field.SAME_Y_POINT) {
			gCopy.setColor(Color.BLUE);
		}
		gCopy.drawString(point, xMid-xShift/2, yMid - yShift);

		if(isBonus()) {
			gCopy.setColor(Color.CYAN);
			gCopy.drawString("+" + Field.BONUS_POINT, xMid-xShift, yMid+yShift);
		}
	}



}
