package field;

import java.awt.Graphics;

public class BoxSquare extends FieldSquare {


	@Override
	public int getType() {
		return FieldSquare.BOX;
	}

	@Override
	public boolean isBox() {
		return true;
	}

	@Override
	public void paint(int x1, int y1, int x2, int y2, Graphics g) {		//(x1,y1)左下、(x2,y2)右上 (x1<x2, y2<y1)
		final int width = x2 - x1;
		final int height = y2 - y1;
		g.fillRect(x1, y1, width, height);
	}



}
