package field;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.math3.exception.OutOfRangeException;

import controll.Lattice;
import controll.Position;

/**
 * Lattice(int)
 * ySize=5
 * 5ロロロロロロロロ
 * 4ロロロロロロロロ
 * 3ロロロロロロロロ
 * 2ロロロロロロロロ
 * 1ｓロロロロロロロ
 *   1 2 3 4 5 6 7 8 xSize=8
 *
 * Position(double)
 * ySize=5
 * 5
 *  ロ ロ ロ ロ ロ ロ ロ ロ
 * 4
 *  ロ ロ ロ ロ ロ ロ ロ ロ
 * 3
 *  ロ ロ ロ ロ ロ ロ ロ ロ
 * 2
 *  ロ ロ ロ ロ ロ ロ ロ ロ
 * 1
 *  ｓ ロ ロ ロ ロ ロ ロ ロ
 * 0  1  2  3  4  5  6  7  8 xSize=8
 *
 */

//TODO ここはオブジェクト指向でいいかもしれないのでfieldのiterator定義した方がよかったかもしれない
public class Field {
	public static final int DEFAULT_X = 8;
	public static final int DEFAULT_Y = 5;
	public static final int BOX_NUM = 3;
	public static final int MAKER_BOX = 2;
	public static final int ANSWER_POINT = 50;
	public static final int SAME_X_POINT = 20;
	public static final int SAME_Y_POINT = 10;
	public static final int BONUS_POINT = 20;

	private final List<FieldListener> listeners = new ArrayList<>();
	private final int xSize, ySize;
	private final FieldSquare[][] field;	//こっちのインデックは0スタートなので注意

	public Field(Lattice lattice) {
		xSize = lattice.getX();
		ySize = lattice.getY();
		field = new FieldSquare[ySize][xSize];

		init();
	}


	public boolean rangeCheck(Lattice lattice) {
		final int x = lattice.getX();
		final int y = lattice.getY();
		if(x<=0 || xSize<x) {
			throw new OutOfRangeException(x, 1, xSize);
		} else if(y<=0 || ySize<y) {
			throw new OutOfRangeException(y, 1, ySize);
		} else {
			return true;
		}
	}

	public boolean rangeCheck(Position position) {
		final double x = position.getX();
		final double y = position.getY();
		if(x<0 || xSize<x) {
			throw new OutOfRangeException(x, 1, xSize);
		} else if(y<0 || ySize<y) {
			throw new OutOfRangeException(y, 1, ySize);
		} else {
			return true;
		}
	}

	public void init() {
		for(int i=0; i<ySize; i++) {
			for(int j=0; j<xSize; j++) {
				field[i][j] = new EmptySquare();
			}
		}
		fieldUpdate();
	}

	public void initEmpty() {
		for(int i=1; i<=ySize; i++) {
			for(int j=1; j<=xSize; j++) {
				Lattice lattice = new Lattice(j, i);
				if(isEmpty(lattice)) {
					((EmptySquare)get(lattice)).initPoint();
				}
			}
		}
		fieldUpdate();
	}

	public void addFieldListener(FieldListener listener) {
		listeners.add(listener);
	}

	public void fieldUpdate() {		//repaint
		listeners.forEach(listener -> listener.updated(this));
	}

	public int getXSize() {
		return xSize;
	}

	public int getYSize() {
		return ySize;
	}

	public Lattice getSize() {
		return new Lattice(getXSize(), getYSize());
	}

	//全マスemptyで初期化されている
	public FieldSquare get(Lattice lattice) {
		rangeCheck(lattice);		//throw Exception

		return field[lattice.getY()-1][lattice.getX()-1];
	}

	//前のものを返す
	public FieldSquare set(Lattice lattice, FieldSquare element) {
		rangeCheck(lattice);

		final FieldSquare prev = get(lattice);
		field[lattice.getY()-1][lattice.getX()-1] = element;

		fieldUpdate();
		return prev;
	}

	public FieldSquare setBox(Lattice lattice) {
		rangeCheck(lattice);

		final FieldSquare prev = get(lattice);
		if(prev.isBox()) {
			return set(lattice, new EmptySquare());
		} else {
			return set(lattice, new BoxSquare());
		}
	}

	public FieldSquare setMarker(Lattice lattice) {
		rangeCheck(lattice);

		final FieldSquare prev = get(lattice);
		if(prev.isMarker()) {
			return set(lattice, new EmptySquare());
		} else {
			return set(lattice, new MarkerSquare());
		}
	}

	public boolean isBox(Lattice lattice) {
		rangeCheck(lattice);

		return get(lattice).isBox();
	}

	public boolean isMarker(Lattice lattice) {
		rangeCheck(lattice);

		return get(lattice).isMarker();
	}

	public boolean isEmpty(Lattice lattice) {
		rangeCheck(lattice);

		return get(lattice).isEmpty();
	}

	public List<Lattice> getBoxes() {
		final List<Lattice> boxes = new ArrayList<>();
		for(int i=1; i<=getYSize(); i++) {
			for(int j=1; j<=getXSize(); j++) {
				final Lattice lattice = new Lattice(j, i);
				if(isBox(lattice)) {
					boxes.add(lattice);
				}
			}
		}
		return boxes;
	}

	public List<Lattice> getMarkers() {
		final List<Lattice> markers = new ArrayList<>();
		for(int i=1; i<=getYSize(); i++) {
			for(int j=1; j<=getXSize(); j++) {
				final Lattice lattice = new Lattice(j, i);
				if(isMarker(lattice)) {
					markers.add(lattice);
				}
			}
		}
		return markers;
	}

	public boolean startable() {
		final List<Lattice> boxes = getBoxes();
		final List<Lattice> markers = getMarkers();

		if(boxes.size() != BOX_NUM) {
			return false;		//箱の数が3個でなければfalse
		}

		final Set<Integer> xBox = boxes
				.stream()
				.map(box -> box.getX())
				.collect(Collectors.toSet());
		final Set<Integer> yBox = boxes
				.stream()
				.map(box -> box.getY())
				.collect(Collectors.toSet());
		if(xBox.size()==BOX_NUM && yBox.size()==BOX_NUM) {
			return false;		//箱の座標にxもyもかぶりがなければfalse
		}

		final boolean isSafeMarker = markers
				.stream()
				.allMatch(marker -> isCross2Box(marker));
		if(!isSafeMarker) {
			return false;		//縦横に2つ以上箱がないマーカーがあるとfalse
		}

		return true;		//全てケアしていればtrue
	}

	public boolean isCross2Box(Lattice lattice) {
		return countCrossBox(lattice) >= MAKER_BOX;
	}


	//TODO より効率よいプログラムかける
	public List<Lattice> decideAnswer() {		//箱が一番多い空マス(得点計算なのでCの練習)
		initEmpty();

		int max = 0;
		for(int i=1; i<=getYSize(); i++) {		//まず全探索して各マスの箱の数設定
			for(int j=1; j<=getXSize(); j++) {
				final Lattice lattice = new Lattice(j, i);
				if(isEmpty(lattice)) {
					final int boxNum = countCrossBox(lattice);		//ここつまり4重ループになってる
					((EmptySquare)get(lattice)).setBoxNum(boxNum);
					max = Math.max(max, boxNum);
				}
			}
		}

		final List<Lattice> lattices = new ArrayList<>();
		for(int i=1; i<=getYSize(); i++) {
			for(int j=1; j<=getXSize(); j++) {		//次に全探索して箱の数最大値のやつをリストアップ
				final Lattice lattice = new Lattice(j, i);
				if(isEmpty(lattice) && ((EmptySquare)get(lattice)).getBoxNum()==max) {
					lattices.add(lattice);
					((EmptySquare)get(lattice)).setPoint(ANSWER_POINT);
				}
			}
		}
		decideScore(lattices);


		return lattices;
	}

	public int countCrossBox(Lattice lattice) {
		final int x = lattice.getX();
		final int y = lattice.getY();
		boolean sameX = false;
		boolean sameY = false;

		int count = 0;
		for(int i=1; i<=getYSize(); i++) {
			if(isBox(new Lattice(x, i))) {
				sameX = true;
				count++;
			}
		}
		for(int i=1; i<=getXSize(); i++) {
			if(isBox(new Lattice(i, y))) {
				sameY = true;
				count++;
			}
		}

		if(get(lattice).isEmpty()) {
			((EmptySquare)get(lattice)).setBonus(sameX && sameY);
		}

		return count;
	}

	public void decideScore(List<Lattice> lattices) {		//正解の縦と横(得点計算なのでCの練習)
		final Integer[] answerX = lattices
				.stream()
				.map(lattice -> lattice.getX())
				.toArray(Integer[]::new);
		final Integer[] answerY = lattices
				.stream()
				.map(lattice -> lattice.getY())
				.toArray(Integer[]::new);

		for(int i=1; i<=this.getYSize(); i++) {
			for(int j=0; j<answerX.length; j++) {
				Lattice lattice = new Lattice(answerX[j], i);
				if(isEmpty(lattice)) {
					((EmptySquare)get(lattice)).setPoint(SAME_X_POINT);
				}

			}
		}

		for(int i=1; i<=this.getXSize(); i++) {
			for(int j=0; j<answerY.length; j++) {
				Lattice lattice = new Lattice(i, answerY[j]);
				if(isEmpty(lattice)) {
					((EmptySquare)get(lattice)).setPoint(SAME_Y_POINT);
				}
			}
		}

	}


}
