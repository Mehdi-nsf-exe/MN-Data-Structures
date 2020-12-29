package mnds.stack;

import java.util.EmptyStackException;

/**
 * Interface with the operations the ADT stack supports.
 * @author Mehdi nasef
 *
 * @param <E> The type of the elements of the stack.
 */
public interface Stack<E> {
	
	/**
	 * Puts the element in the top of the stack.
	 * @param element The element to push onto the stack.
	 */
	public void push(E element);
	
	/**
	 * Gets and removes the element at the top of the stack.
	 * @return The popped element.
	 * @throws EmptyStackException If the stack is empty.
	 */
	public E pop() throws EmptyStackException;
	
	/**
	 * Gets the element at the top of the stack without removing it.
	 * @return The element at the top of the stack.
	 * @throws EmptyStackException If the stack is empty.
	 */
	public E top() throws EmptyStackException;
	
	/**
	 * Empties the stack.
	 */
	public void clear();
	
	/**
	 * Gets the number of elements the stack contains.
	 * @return The number of elements the stack contains.
	 */
	public int size();
}
