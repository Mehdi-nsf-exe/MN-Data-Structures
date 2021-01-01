package mnds.map;

import java.util.Iterator;

import mnds.list.ArrayList;
import mnds.list.LinkedList;
import mnds.list.List;

/**
 * The hash table implementation of the map ADT.
 * @author Mehdi Nasef.
 *
 * @param <K> The type of the keys of the map.
 * @param <V> The type of the values of the map.
 */
public class HashMap<K, V> implements Map<K, V> {
	
	private List<HashMapEntry>[] table;
	private int entriesCount;
	
	private static final int DEFAULT_INITIAL_CAPACITY = 128;
	
	/**
	 * Creates a HashMap with the indicated initial capacity.
	 * @param initialCapacity the initial capacity of the hashMap.
	 */
	public HashMap(int initialCapacity) {
		initialize(initialCapacity);
	}
	
	/**
	 *  Creates a Hash map with the default initial capacity.
	 */
	public HashMap() {
		initialize(DEFAULT_INITIAL_CAPACITY);
	}
	
	/**
	 * Does the common initialization of the constructors.
	 * It also can be used for resizing. 
	 * @param initialCapacity The initial capacity of the map.
	 */
	@SuppressWarnings("unchecked")
	private void initialize(int initialCapacity) {
		
		entriesCount = 0;
		table = new List[initialCapacity];
		
		for (int i = 0; i < table.length; i++) {
			table[i] = new LinkedList<HashMapEntry>();
		}
	}

	@Override
	public void put(K key, V value) {
		
		double ocupationLevel = (double) entriesCount / (double) table.length;
		if (ocupationLevel >= 1.0) {
			resize();
		}
		
		int index = hash(key);
		
		HashMapEntry entry = getFromList(table[index], key);
		
		if (entry == null) {
			entry = new HashMapEntry(key, value);
			table[index].add(0, entry);
			entriesCount++;
		} else {
			entry.value = value;
		}
	}

	@Override
	public void remove(K key) {
		
		int index = hash(key);
		
		boolean removed = removeFromList(table[index], key);
		
		if (removed) {
			entriesCount--;
		}
	}

	@Override
	public V get(K key) {
		
		int index = hash(key);
		
		HashMapEntry entry = getFromList(table[index], key);
		
		if (entry != null) {
			return entry.value;
		} else {
			return null;
		}
	}

	@Override
	public void clear() {
		
		for (List<HashMapEntry> list: table) {
			list.clear();
		}
		entriesCount = 0;
	}

	@Override
	public int size() {
		return entriesCount;
	}

	@Override
	public List<K> keys() {
		
		List<K> keysList = new ArrayList<K>(entriesCount);
		
		int i = 0;
		for (List<HashMapEntry> entriesList: table) {
			for (HashMapEntry e: entriesList) {
				keysList.add(i, e.key);
				i++;
			}
		}
		
		return keysList;
	}

	@Override
	public List<V> values() {
		
		List<V> valuesList = new ArrayList<V>(entriesCount);
		
		int i = 0; 
		for (List<HashMapEntry> entriesList: table) {
			for (HashMapEntry e: entriesList) {
				valuesList.add(i, e.value);
				i++;
			}
		}
		
		return valuesList;
	}

	@Override
	public List<Entry<K, V>> entries() {
		
		List<Entry<K, V>> entriesList = new ArrayList<Entry<K, V>>(entriesCount);
		
		int i = 0;
		for (List<HashMapEntry> entries: table) {
			for (HashMapEntry e: entries) {
				entriesList.add(i, e);
				i++;
			}
		}
		
		return entriesList;
	}
	
	/**
	 * Gets the hash value of the hey.
	 * @param key The key whose hash value to be got.
	 * @return The hash value of the key.
	 */
	private int hash(K key) {
		return Math.abs(key.hashCode()) % table.length;
	}
	
	/**
	 * Gets the entry with the indicated key form the list.
	 * @param list The list to get the entry form.
	 * @param key The key of the entry to be got.
	 * @return the entry with the indicated list or null if there is no such entry.
	 */
	private HashMapEntry getFromList(List<HashMapEntry> list, K key) {
		
		for (HashMapEntry e: list) {
			if (key.equals(e.key)) {
				return e;
			}
		}
		return null;
	}
	
	/**
	 * Removes the entry with the indicated key.
	 * @param list The list to remove the entry form.
	 * @param key The key of the entry to be removed.
	 * @return true if an entry have been removed, false otherwise.
	 */
	private boolean removeFromList(List<HashMapEntry> list, K key) {
		
		Iterator<HashMapEntry> iter = list.iterator();
		
		while(iter.hasNext()) {
			HashMapEntry entry = iter.next();
			if (entry.key.equals(key)) {
				iter.remove();
				return true;
			}
		}
		return false;
	}
	
	private void resize() {
		
		List<HashMapEntry>[] oldTable = table;
		
		// The map is reinitialized with double the capacity.
		this.initialize(oldTable.length * 2);
		
		for (List<HashMapEntry> entriesList: oldTable) {
			for (HashMapEntry e: entriesList) {
				this.put(e.key, e.value);
			}
		}
	}

	/**
	 * The entry of the HashMap.
	 *
	 */
	public class HashMapEntry implements Entry<K, V> {
		
		private K key;
		private V value;
		
		/**
		 * Creates an entry with the indicated key and value.
		 * @param key The key of the entry.
		 * @param value The value of the entry.
		 */
		private HashMapEntry(K key, V value) {
			this.key = key;
			this.value = value;
		}

		@Override
		public K key() {
			return key;
		}

		@Override
		public V value() {
			return value;
		}
	}
}
