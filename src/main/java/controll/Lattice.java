package controll;

public class Lattice {
	private int x,y;

	public Lattice(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	@Override
	public String toString() {
		return "(" + getX() + ", " + getY() + ")";
	}

	//副作用がある
	public Lattice plus(Lattice lattice) {
		setX(getX() + lattice.getX());
		setY(getY() + lattice.getY());
		return this;
	}

	//副作用がある
	public Lattice minus(Lattice lattice) {
		setX(getX() - lattice.getX());
		setY(getY() - lattice.getY());
		return this;
	}

	public Position toPosition() {
		return new Position(getX()-0.5, getY()-0.5);
	}


}
