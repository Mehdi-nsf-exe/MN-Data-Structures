package mnds.list;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class LinkedList<E> implements List<E> {
	
	private static class Cell<E> {
		private E content;
		private Cell<E> next;
		private Cell<E> previous;
		
		public Cell(E content) {
			this.content = content;
		}
	}
	
	private Cell<E> front;
	private Cell<E> end;
	private int size;
	
	public LinkedList() {
		front = new Cell<E>(null);	// The front and the end are empty cells.
		end = new Cell<E>(null);
		
		front.next = end;
		end.previous = front;
		
		front.previous = null;
		end.next = null;
		
		size = 0;
	}

	@Override
	public void add(int index, E element) throws IndexOutOfBoundsException {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}
		
		Cell<E> newCell = new Cell<E>(element);
		Cell<E> previousCell = getCellAtIndex(index - 1);
		newCell.previous = previousCell;
		newCell.next = previousCell.next;
		previousCell.next = newCell;
		newCell.next.previous = newCell;
		
		size++;
	}

	@Override
	public E get(int index) throws IndexOutOfBoundsException {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		
		Cell<E> cell = getCellAtIndex(index);
		return cell.content;
	}

	@Override
	public E remove(int index) throws IndexOutOfBoundsException {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		
		Cell<E> cell = getCellAtIndex(index);
		cell.previous.next = cell.next;
		cell.next.previous = cell.previous;
		size--;
		
		return cell.content;
	}

	@Override
	public int indexOf(E element) {
		Cell<E> aux = front.next;
		for (int i = 0; i < size; i++) {
			if (element.equals(aux.content)) {
				return i;
			}
			aux = aux.next;
		}
		return -1;
	}

	@Override
	public void clear() {
		front.next = end;
		end.previous = front;
		size = 0;
	}

	@Override
	public int size() {
		return size;
	}
	
	private static class LinkedListIterator<E> implements ListIterator<E> {
		
		LinkedList<E> list;
		Cell<E> next;
		Cell<E> current;
		int nextIndex = 0;
		
		/**
		 * Creates a new Iterator for the indicated list.
		 * @param list The list that the iterator will be iterating over.
		 */
		public LinkedListIterator(LinkedList<E> list) {
			this.list = list;
			this.next = list.front.next;
			this.current = null;
		}

		@Override
		public void add(E element) {
			Cell<E> newCell = new Cell<E>(element);
			newCell.next = next;
			newCell.previous = next.previous;
			newCell.previous.next = newCell;
			next.previous = newCell;
			current = null;

			nextIndex++;
			list.size++;
		}

		@Override
		public boolean hasNext() {
			return next != list.end;
		}

		@Override
		public boolean hasPrevious() {
			return next.previous != list.end;
		}

		@Override
		public E next() throws NoSuchElementException {
			if (!hasNext()) {
				throw new NoSuchElementException("No next element");
			}
			current = next;
			next = next.next;
			nextIndex++;
			return current.content;
		}

		@Override
		public int nextIndex() {
			return nextIndex;
		}

		@Override
		public E previous() {
			if (!hasPrevious()) {
				throw new NoSuchElementException("No previous element");
			}
			next = next.previous;
			current = next;
			nextIndex--;
			return current.content;
		}

		@Override
		public int previousIndex() {
			return nextIndex - 1;
		}

		@Override
		public void remove() throws IllegalStateException {
			if (current == null) {
				throw new IllegalStateException();
			}
			current.previous.next = current.next;
			current.next.previous = current.previous;
			current = null;
		}

		@Override
		public void set(E element) throws IllegalStateException {
			if (current == null) {
				throw new IllegalStateException();
			}
			
			current.content = element;
		}
	}

	@Override
	public Iterator<E> iterator() {
		return new LinkedListIterator<E>(this);
	}
	
	/**
	 * Gets the cell at the indicated index.
	 * @param index The index of te cell to get.
	 * @return The cell at the indicated index.
	 */
	private Cell<E> getCellAtIndex(int index) {
		Cell<E> aux;
		if (index <= (size / 2)) {
			aux = front;
			for (int i = 0; i <= index; i++) {
				aux = aux.next;
			}
		} else {
			aux = end;
			for (int i = size - 1; i >= index; i--) {
				aux = aux.previous;
			}
		}
		return aux;
	}
}
