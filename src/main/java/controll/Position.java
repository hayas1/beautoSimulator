package controll;

import java.awt.Dimension;

public class Position {
	private double x,y;

	public Position(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}

	@Override
	public String toString() {
		return "(" + getX() + ", " + getY() + ")";
	}

	//副作用がある
	public Position plus(Position position) {
		setX(getX() + position.getX());
		setY(getY() + position.getY());
		return this;
	}

	//副作用がある
	public Position minus(Position position) {
		setX(getX() - position.getX());
		setY(getY() - position.getY());
		return this;
	}

	public Lattice toLattice() {
		return new Lattice((int)getX()+1, (int)getY()+1);
	}

	//画面は左上が(0,0)なのでyのスケーリング注意
	public Dimension scaling(Dimension screenSize, Lattice fieldSize) {
		final int x = (int)(getX() * screenSize.getWidth() / fieldSize.getX());
		final int y = (int)((fieldSize.getY()*screenSize.getHeight() - getY()*screenSize.getHeight()) / fieldSize.getY());

		return new Dimension(x, y);
	}

}
