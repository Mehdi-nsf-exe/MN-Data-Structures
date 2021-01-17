package mnds.map;

import mnds.list.List;

/**
 * The definition of the operations of the sorted map ADT.
 * @author Mehdi Nasef.
 *
 * @param <K> The type of the keys of the map.
 * @param <V> The type of the values that will be contained in the map.
 */
public interface SortedMap<K extends Comparable<K>, V> extends Map<K, V> {

	/**
	 * Gets the entry with the least key.
	 * @return The entry with the least key.
	 */
	Entry<K, V> firstEntry();
	
	/**
	 * Gets the entry with the greatest key.
	 * @return The entry with the greatest key.
	 */
	Entry<K, V> lastEntry();
	
	/**
	 * Gets the entries with keys in the indicated interval.
	 * @param least The least key of the interval.
	 * @param greatest The greatest key of the interval.
	 * @return An ordered list of the entries with keys in the indicated interval.
	 */
	List<Entry<K, V>> entriesInInterval(K least, K greatest);
	
	/**
	 * Gets the entries with keys less than or equal to the indicated.
	 * @param key The key which delimits the returned entries.
	 * @return An ordered list of the entries with keys up to the indicated.
	 */
	List<Entry<K, V>> ascendentsUpTo(K key);
	
	/**
	 * Gets the entries with keys greater than or equal to the indicated.
	 * @param key The key that delimits the returned entries.
	 * @return An ordered list of the entries with keys down to the indicated.
	 */
	List<Entry<K, V>> descendentsDownTo(K key);
}
