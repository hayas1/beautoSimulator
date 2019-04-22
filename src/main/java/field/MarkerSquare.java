package field;

import java.awt.Graphics;

public class MarkerSquare extends FieldSquare {


	@Override
	public int getType() {
		return FieldSquare.MARKER;
	}

	@Override
	public boolean isMarker() {
		return true;
	}

	@Override
	public void paint(int x1, int y1, int x2, int y2, Graphics g) {
		final int xMid = (x1+x2) / 2;
		final int yMid = (y1+y2) / 2;
		g.drawLine(xMid, y2, xMid, y1);
		g.drawLine(x1, yMid, x2, yMid);
	}



}
