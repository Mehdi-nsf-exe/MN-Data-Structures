package mnds.stack;

import java.util.EmptyStackException;

/**
 * The linked implementation of the stack ADT.
 * @author Mehdi Nasef.
 *
 * @param <E> The type of the elements of the stack.
 */
public class LinkedStack<E> implements Stack<E> {
	
	private Cell<E> top = null;
	private int elementCount = 0;
	
	private static class Cell<E> {
		private E content;
		private Cell<E> next = null;
		
		public Cell(E element) {
			content = element;
		}
	}

	@Override
	public void push(E element) {
		
		Cell<E> newCell = new Cell<E>(element);
		
		if (top == null) {
			top = newCell;
		} else {
			newCell.next = top;
			top = newCell;
		}
		elementCount++;
	}

	@Override
	public E pop() throws EmptyStackException {
		
		if (elementCount == 0) {
			throw new EmptyStackException();
		}
		E topElement = top.content;
		top = top.next;
		elementCount--;
		return topElement;
	}

	@Override
	public E top() throws EmptyStackException {
		
		if (elementCount == 0) {
			throw new EmptyStackException();
		}
		return top.content;
	}

	@Override
	public void clear() {
		top = null;
		elementCount = 0;
	}

	@Override
	public int size() {
		return elementCount;
	}
}
