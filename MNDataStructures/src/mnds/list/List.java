package mnds.list;

/**
 * The definition of the operations that can be performed on the ADT list.
 * @author Mehdi Nasef
 * @version 24/dic/2020
 *
 * @param <E> The type of the element that the list contains.
 */
public interface List<E> extends Iterable<E> {
	
	/**
	 * Inserts an element in the indicated index. All the elements at greater indexes are
	 * shifted to an upper index to make a gap for the new one.
	 * @param index The index where to insert the element.
	 * @param element The element to be added to the list.
	 * @throws IndexOutOfBoundsException if the index is negative or it's greater than the
	 * size of the list.
	 */
	public void add(int index, E element) throws IndexOutOfBoundsException;
	
	/**
	 * Gets the element at the indicated index.
	 * @param index The index of the element to be got.
	 * @return the element at the indicated index.
	 * @throws IndexOutOfBoundsException if the index is negative or it's greater than the
	 * size of the list.
	 */
	public E get(int index) throws IndexOutOfBoundsException;
	
	/**
	 * Removes the element at the indicated index. All elements with greater indexes are shifted to a lower
	 * index to cover the gap of the removed element.
	 * @param index The index of the element to be removed.
	 * @return The removed element.
	 * @throws IndexOutOfBoundsException if the index is negative or it's greater than the
	 * size of the list.
	 */
	public E remove(int index) throws IndexOutOfBoundsException;
	
	/**
	 * Gets the index of the first occurrence of the element.
	 * @param element the element its index is to be got.
	 * @return The index of the indicated element or -1 if there was no occurrence of the element.
	 */
	public int indexOf(E element);
	
	/**
	 * Makes the list empty.
	 */
	public void clear();
	
	/**
	 * Gets the number of elements that the list has.
	 * @return the number of elements that the list has.
	 */
	public int size();
}
