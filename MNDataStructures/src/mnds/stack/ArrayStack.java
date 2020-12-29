package mnds.stack;

import java.util.EmptyStackException;

/**
 * The array implementation of the stack ADT.
 * @author Mehdi Nasef
 *
 * @param <E> The type of he elements of the stack.
 */
public class ArrayStack<E> implements Stack<E> {
	
	private E[] elements;
	private int top = -1;
	
	private static final int DEFAULT_INITIAL_CAPACITY = 8;
	
	/**
	 * Creates a new ArrayStack with the default initial capacity.
	 */
	@SuppressWarnings("unchecked")
	public ArrayStack() {
		elements = (E[]) new Object[DEFAULT_INITIAL_CAPACITY];
	}
	
	/**
	 * Creates an ArrayStack with the indicated initial capacity.
	 * @param initialCapacity The initial capacity of the ArrayStack.
	 */
	@SuppressWarnings("unchecked")
	public ArrayStack(int initialCapacity) {
		elements = (E[]) new Object[initialCapacity];
	}

	@SuppressWarnings("unchecked")
	@Override
	public void push(E element) {
		
		// Resize if the elements count exceeds the capacity of the array.
		if ((top + 1) >= elements.length) {
			E[] newArray = (E[]) new Object[elements.length * 2];
			
			for (int i = 0; i <= top; i++) {
				newArray[i] = elements[i];
			}
			elements = newArray;
		}
		top++;
		elements[top] = element;
	}

	@Override
	public E pop() throws EmptyStackException {
		
		if (top < 0) {
			throw new EmptyStackException();
		}
		E topElement = elements[top];
		elements[top] = null;
		top--;
		return topElement;
	}

	@Override
	public E top() throws EmptyStackException {
		if (top < 0) {
			throw new EmptyStackException();
		}
		return elements[top];
	}

	@Override
	public void clear() {
		for (int i = 0; i <= top; i++) {
			elements[i] = null;
		}
		top = -1;
	}

	@Override
	public int size() {
		return top + 1;
	}
}
