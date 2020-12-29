package mnds.queue;

import java.util.NoSuchElementException;

/**
 * The Linked implementation of the Queue ADT.
 * @author Mehdi Nasef.
 *
 * @param <E> The type of the elements of the queue.
 */
public class LinkedQueue<E> implements Queue<E> {

	private Cell<E> front = null;
	private Cell<E> end = null;
	private int elementCount = 0;
	
	private static class Cell<E> {
		private E content;
		private Cell<E> next = null;
		
		/**
		 * Creates a new Cell with the indicated content.
		 * @param content The content of the Cell.
		 */
		public Cell(E content) {
			this.content = content;
		}
	}

	@Override
	public void enqueue(E element) {
		
		Cell<E> newCell = new Cell<E>(element);
		
		if (front == null) {
			front = newCell;
			end = newCell;
		} else {
			end.next = newCell;
			end = newCell;
		}
		elementCount++;
	}

	@Override
	public E dequeue() throws NoSuchElementException {
		
		if (elementCount == 0) {
			throw new NoSuchElementException("Empty queue");
		}
		
		E frontElement = front.content;
		front = front.next;
		if (front == null) {
			end = null;
		}
		elementCount--;
		return frontElement;
	}

	@Override
	public E front() throws NoSuchElementException {
		if (elementCount == 0) {
			throw new NoSuchElementException("Empty queue");
		}
		return front.content;
	}

	@Override
	public void clear() {
		front = null;
		end = null;
		elementCount = 0;
	}

	@Override
	public int size() {
		return elementCount;
	}
}
