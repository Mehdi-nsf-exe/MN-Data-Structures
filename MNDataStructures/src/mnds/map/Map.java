package mnds.map;

import mnds.list.List;

/**
 * The definition of he operations of the map ADT.
 * @author Mehdi Nasef
 *
 * @param <K> The type of the key of the map.
 * @param <V> The type of the value of the map.
 */
public interface Map<K, V> {
	
	/**
	 * The entry of the map. contains the associated key and value.
	 *
	 * @param <K> The type of the key.
	 * @param <V> The type of the value.
	 */
	public interface Entry<K, V> {
		
		/**
		 * Gets the value of the entry
		 * @return The key of the value.
		 */
		public K key();
		
		/**
		 * Gets the value of the entry.
		 * @return The value of the entry.
		 */
		public V value();
	}
	
	/**
	 * If the map doesn't contain an entry with the key a new entry
	 * is created with the new key and value. If the an entry with
	 * the key already exists the value associated to the key is
	 * replaced with the new one.
	 * @param key The key associated to the value.
	 * @param value The value to be put in the map.
	 */
	public void put(K key, V value);
	
	/**
	 * Removes the entry of the value with the associated key.
	 * @param key the key of the entry to be removed.
	 */
	public void remove(K key);
	
	/**
	 * Gets the value with the associated key.
	 * @param key The key associated to the value to be got.
	 * @return The value associated to the indicated key.
	 */
	public V get(K key);
	
	/**
	 * Removes all the entries of the map.
	 */
	public void clear();
	
	/**
	 * Gets the number of entries of the map.
	 * @return The number of entries of the map.
	 */
	public int size();
	
	/**
	 * Gets all the keys of the entries of the map.
	 * @return A list with all the keys of the entries of the map.
	 */
	public List<K> keys();
	
	/**
	 * Gets all the values of the entries of the map.
	 * @return A list of all the values of the entries of the map.
	 */
	public List<V> values();
	
	/**
	 * Gets all the entries of the map.
	 * @return A list of all the entries of the map.
	 */
	public List<Entry<K, V>> entries();
}
