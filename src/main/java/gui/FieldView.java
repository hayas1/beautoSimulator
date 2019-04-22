package gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import controll.Lattice;
import field.Field;
import robot.Robot;

public class FieldView extends JPanel implements MouseListener {
	private final GUIMain guiMain;
	private final Field field;
	private final Robot robot;
	private int unit, xSurplus, ySurplus;
	private final int[] verticalLines, horizontalLines;

	private Lattice lastClicked;

	public FieldView(GUIMain guiMain, Field field, Robot robot) {
		this.guiMain = guiMain;
		this.field = field;
		this.robot = robot;

		addMouseListener(this);
		verticalLines = new int[field.getXSize()+1];
		horizontalLines = new int[field.getYSize()+1];
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setFont(new Font(g.getFont().getName(), g.getFont().getStyle(), 30));
		paintFrame(g);
		paintSquare(g);
		paintRobot(g);
	}

	public void paintFrame(Graphics g) {
		unit = Math.min((this.getWidth()-1)/field.getXSize(), (this.getHeight()-1)/field.getYSize());
		xSurplus = (this.getWidth() - unit * field.getXSize()) / 2;
		ySurplus = (this.getHeight() - unit * field.getYSize()) / 2;

		//画面表示が左上(0,0)の謎座標系なのでy座標は数字の大きい方から描画し、配列に保存する。
		verticalLines[0] = xSurplus;
		horizontalLines[0] = this.getHeight() - ySurplus;
		g.drawLine(xSurplus, horizontalLines[0], this.getWidth()-1-xSurplus, horizontalLines[0]);
		g.drawLine(verticalLines[0], ySurplus, verticalLines[0], this.getHeight()-1-ySurplus);
		for(int i=1; i<=field.getXSize(); i++) {
			verticalLines[i] = verticalLines[i-1] + unit;
			g.drawLine(verticalLines[i], ySurplus, verticalLines[i], this.getHeight()-1-ySurplus);
		}
		for(int i=1; i<=field.getYSize(); i++) {
			horizontalLines[i] = horizontalLines[i-1] - unit;
			g.drawLine(xSurplus, horizontalLines[i], this.getWidth()-1-xSurplus, horizontalLines[i]);
		}

	}

	public void paintSquare(Graphics g) {
		for(int i=0; i<field.getYSize(); i++) {
			for(int j=0; j<field.getXSize(); j++) {
					field.get(new Lattice(j+1, i+1))	//画面の格子線の座標は0からだがフィールドのマスの座標系は1から
					.paint(verticalLines[j], horizontalLines[i+1], verticalLines[j+1], horizontalLines[i], g);
			}
		}
	}

	public void paintRobot(Graphics g) {
		Dimension position = robot.getPosition().scaling(this.getSize(), field.getSize());
		int x = (int)position.getWidth() + xSurplus;
		int y = (int)position.getHeight() + ySurplus;
		Polygon robotForm = robot.form(unit/2.5, new Dimension(x, y));

		Graphics2D copy = (Graphics2D)g.create();
		copy.setColor(Color.RED);
		copy.fillPolygon(robotForm);
		copy.setColor(Color.BLACK);
		copy.setStroke(new BasicStroke(2));
		copy.drawPolygon(robotForm);
	}

	public int xToIndex(int x) {
		int i;
		for(i=0; i<=field.getXSize(); i++) {
			if(x < verticalLines[i]) {
				return i;
			}
		}
		return -1;
	}

	public int yToIndex(int y) {
		int i;
		for(i=0; i<=field.getYSize(); i++) {
			if(y > horizontalLines[i]) {		//yはインデックスが増えるほど数字が小さくなるので
				return i;
			}
		}
		return -1;
	}

	public Lattice getLastClicked() {
		return lastClicked;
	}

	@Override
	public void mouseClicked(MouseEvent e) {		//MouseEventは左上が(0,0)
		int lastClickedX = xToIndex(e.getX());
		int lastClickedY = yToIndex(e.getY());
		guiMain.viewClicked(lastClickedX, lastClickedY);
	}
	@Override
	public void mousePressed(MouseEvent e) {
	}
	@Override
	public void mouseReleased(MouseEvent e) {
	}
	@Override
	public void mouseEntered(MouseEvent e) {
	}
	@Override
	public void mouseExited(MouseEvent e) {
	}


}
