package mnds.queue;

import java.util.NoSuchElementException;

/**
 * Interface with the operations a queue supports.
 * @author Mehdi Nasef.
 *
 * @param <E> The type of elements the queue contains.
 */
public interface Queue<E> {
	
	/**
	 * Adds an element to the end of the queue.
	 * @param element The element to be added to be enqueued.
	 */
	public void enqueue(E element);
	
	/**
	 * Gets and removes the element at the front of the queue.
	 * @return The dequeued element.
	 * @throws NoSuchElementException If the queue is empty.
	 */
	public E dequeue() throws NoSuchElementException;
	
	/**
	 * Gets without removing the element at the front of the queue.
	 * @return The element at the front of the queue.
	 * @throws NoSuchElementException If the queue is empty.
	 */
	public E front() throws NoSuchElementException;
	
	/**
	 * Removes all the element from the queue.
	 */
	public void clear();
	
	/**
	 * Gets the number of elements the queue contains.
	 * @return The number of elements the queue has.
	 */
	public int size();
}
