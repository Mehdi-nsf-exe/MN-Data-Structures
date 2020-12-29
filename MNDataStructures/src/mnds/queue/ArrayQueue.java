package mnds.queue;

import java.util.NoSuchElementException;

/**
 * The array based implementation of the ADT queue.
 * @author Mehdi Nasef.
 *
 * @param <E> The Type of the elements of the queue.
 */
public class ArrayQueue<E> implements Queue<E> {
	
	private int elementsCount = 0;
	private int front = 0;;
	private int end;
	private E[] elements;
	
	//The inicial capacity of the array of elements if no initial capacity is specified 
	private static final int DEFAULT_INITIAL_CAPACITY = 8;
	
	/**
	 * Creates a new ArrayQueue with the default initial capacity.
	 */
	@SuppressWarnings("unchecked")
	public ArrayQueue() {
		elements = (E[]) new Object[DEFAULT_INITIAL_CAPACITY];
		end = elements.length - 1;
	}
	
	/**
	 * Creates a new ArrayQueue with the indicated initial array capacity.
	 * @param intialCapacity The initial capacity of the ArrayQueue.
	 */
	@SuppressWarnings("unchecked")
	public ArrayQueue(int intialCapacity) {
		elements = (E[]) new Object[intialCapacity];
		end = elements.length - 1;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void enqueue(E element) {
		
		// Resize the array if full.
		if (elementsCount >= elements.length) {
			E[] newArray = (E[]) new Object[elements.length * 2];
			
			for (int i = 0; i < elementsCount; i++) {
				newArray[i] = elements[(front + i) % elements.length];
			}
			front = 0;
			end = front + elementsCount - 1;
		}
		
		elementsCount++;
		end = (end + 1) % elements.length;
		elements[end] = element;
	}

	@Override
	public E dequeue() throws NoSuchElementException {
		if (elementsCount == 0) {
			throw new NoSuchElementException("Empty queue");
		}
		elementsCount--;
		E frontElement = elements[front];
		elements[front] = null;
		front = (front + 1) % elements.length;
		return frontElement;
	}

	@Override
	public E front() throws NoSuchElementException {
		if (elementsCount == 0) {
			throw new NoSuchElementException();
		}
		return elements[front];
	}

	@Override
	public void clear() {
		for (int i = 0; i < elementsCount; i++) {
			elements[(front + i) % elements.length] = null;
		}
		front = 0;
		end = elements.length - 1;
		elementsCount = 0;
	}

	@Override
	public int size() {
		return elementsCount;
	}
}
