package mnds.list;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * The array implementation of the List ADT.
 * @author Mehdi Nasef
 *
 * @param <E> The type of he element of the list.
 */
public class ArrayList<E> implements List<E> {

	E[] elements;
	int size = 0;
	
	public static final int INITIAL_CAPACITY = 8;
	
	/**
	 * Creates an ArrayList with the default initial capacity. 
	 */
	@SuppressWarnings("unchecked")
	public ArrayList() {
		elements = (E[]) new  Object[INITIAL_CAPACITY];
	}
	
	/**
	 * Creates an ArrayList with the indicated initial capacity.
	 * @param initialCapacity The initial capacity of the ArrayList.
	 */
	@SuppressWarnings("unchecked")
	public ArrayList(int initialCapacity) {
		elements = (E[]) new  Object[initialCapacity];
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void add(int index, E element) throws IndexOutOfBoundsException {
		
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}
		// Shift the elements to an upper index.
		if (size < elements.length) {
			for (int i = size; i > index; i--) {
				elements[i] = elements[i - 1];
			}
		} else { // Resize the array
			E[] newArray = (E[]) new Object[2 * elements.length];
			int i = 0;
			while (i < index) {
				newArray[i] = elements[i];
				i++;
			}
			// A gap is left for the new element
			while (i <= size) {
				i++;
				newArray[i]  = elements[i];
			}
			elements = newArray;
		}
		elements[index] = element;
		size++;
	}

	@Override
	public E get(int index) throws IndexOutOfBoundsException {
		if (index >= size) {
			throw new IndexOutOfBoundsException();
		}
		return elements[index];
	}

	@Override
	public E remove(int index) throws IndexOutOfBoundsException {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		
		E element = elements[index];
		
		// Shift the elements to a lower index to cover the gap of the removed element.
		for (int i = index + 1; i < size; i++) {
			elements[i - 1] = elements[i];
		}
		elements[size - 1] = null;
		size--;
		
		return element;
	}

	@Override
	public int indexOf(E element) {
		for (int i = 0; i < size; i++) {
			if (element.equals(elements[i])) {
				return i;
			}
		}
		return -1;
	}

	@Override
	public void clear() {
		for (int i = 0; i < size; i++) {
			elements[i] = null;
		}
		size = 0;
	}

	@Override
	public int size() {
		return size;
	}
	
	public static class ArrayListIterator<E> implements ListIterator<E> {
		
		private ArrayList<E> list;
		private int next = 0;
		private int current = INVALID_CURRENT;
		
		private static final int INVALID_CURRENT = -1;
		
		/**
		 * Creates a new iterator of ArrayList.
		 * @param list The list the iterator will iterate on.
		 */
		private ArrayListIterator(ArrayList<E> list) {
			this.list = list;
		}

		@Override
		public void add(E element) {
			list.add(next, element);
			current = INVALID_CURRENT;
			next++;
		}

		@Override
		public boolean hasNext() {
			return next < list.size;
		}

		@Override
		public boolean hasPrevious() {
			return next - 1 >= 0;
		}

		@Override
		public E next() throws NoSuchElementException {
			if (!hasNext()) {
				throw new NoSuchElementException("No next element");
			}
			current = next;
			next++;
			return list.elements[current];
		}

		@Override
		public int nextIndex() {
			return next;
		}

		@Override
		public E previous() throws NoSuchElementException {
			if (!hasPrevious()) {
				throw new NoSuchElementException("No previous element");
			}
			current = next - 1;
			next--;
			return list.elements[current];
		}

		@Override
		public int previousIndex() {
			return next - 1;
		}
		
		
		@Override
		public void remove() throws IllegalStateException {
			if (current == INVALID_CURRENT) {
				throw new IllegalStateException();
			}
			
			list.remove(current);
			current = INVALID_CURRENT;
		}

		@Override
		public void set(E element) throws IllegalStateException {
			if (current == INVALID_CURRENT) {
				throw new IllegalStateException();
			}
			list.elements[current] = element;
		}
		
	}
	
	@Override
	public Iterator<E> iterator() {
		return new ArrayListIterator<E>(this);
	}
}
