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
	 * Inserts an element in the indicated index.
	 * @param index The index where to insert the element.
	 * @param element The element to be added to the list.
	 */
	public void add(int index, E element);
	
	/**
	 * Gets the element at the indicated index.
	 * @param index The index of the element to be got.
	 * @return the element at the indicated index.
	 */
	public E get(int index);
	
	/**
	 * Removes the element at the indicated index.
	 * @param index The index of the element to be removed.
	 * @return The removed element.
	 */
	public E remove(int index);
	
	/**
	 * Gets the index of the first occurrence of the element.
	 * @param element the element its index is to be got.
	 * @return The index of the indicated element.
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
