package queue;

import java.util.AbstractQueue;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class EcxecuteQueue<E extends QueueElement> extends AbstractQueue<QueueElement> {
	private final Queue<QueueElement> queue = new LinkedList<>();

	@Override
	public boolean offer(QueueElement e) {
		return true;		//容量制限なし
	}

	@Override
	public QueueElement poll() {
		return queue.poll();
	}

	@Override
	public QueueElement peek() {
		return queue.peek();
	}

	@Override
	public Iterator<QueueElement> iterator() {
		return queue.iterator();
	}

	@Override
	public int size() {
		return queue.size();
	}



}
