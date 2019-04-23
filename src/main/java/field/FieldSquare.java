package field;

import java.awt.Graphics;

public abstract class FieldSquare {
	public static final int EMPTY = 0;
	public static final int MARKER = 1;
	public static final int BOX = 2;

	private int status = 0;

	public abstract int getType();
	public abstract void paint(int x1, int y1, int x2, int y2, Graphics g);		//(x1,y1)左下、(x2,y2)右上

	public boolean isEmpty() {
		return false;
	}

	public boolean isMarker() {
		return false;
	}

	public boolean isBox() {
		return false;
	}


	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}


}
