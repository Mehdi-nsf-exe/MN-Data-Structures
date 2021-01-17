package mnds.map;

import mnds.list.ArrayList;
import mnds.list.LinkedList;
import mnds.list.List;

/**
 * The BST (Binary Search Tree) implementation of the sorted map ADT.
 * The BST is auto-balanced using the AVL technique
 * @author Mehdi Nasef.
 *
 * @param <K> The type of the key of the map.
 * @param <V> The type of the values of the map.
 */
public class AvlTreeMap<K extends Comparable<K>, V> implements SortedMap<K, V> {
	
	private Node root = null;
	private int entryCount = 0;

	@Override
	public void put(K key, V value) {
		
		if (root == null) {
			root = new Node(new AvlTreeMapEntry(key, value), 1);
			entryCount++;
			return;
		}
		
		Node currentNode = root;
		Node previousNode = null;
		boolean sameKeyFound = false;
		
		do {
			previousNode = currentNode;
			if (key.equals(currentNode.entry.key)) {
				sameKeyFound = true;
			} else if (key.compareTo(currentNode.entry.key) < 0) {
				currentNode = currentNode.leftChild;
			} else {
				currentNode = currentNode.rightChild;
			}
		} while (currentNode != null && !sameKeyFound);
		
		if (sameKeyFound) {
			previousNode.entry.value = value;
		} else if (key.compareTo(previousNode.entry.key) < 0) {
			previousNode.leftChild = new Node(new AvlTreeMapEntry(key, value), 1);
			entryCount++;
		} else {
			previousNode.rightChild = new Node(new AvlTreeMapEntry(key, value), 1);
			entryCount++;
		}
	}

	@Override
	public void remove(K key) {
		root = removeRec(root, key);
	}
	
	/**
	 * The recursive method to remove a node.
	 * @param node The root of the subtree.
	 * @param key The key of the node to be removed.
	 * @return The new root of the subtree or the tree.
	 */
	private Node removeRec(Node node, K key) {
		if (node == null) {
			return null;
		}
		
		int comparasionResult = key.compareTo(node.entry.key);
		
		if (comparasionResult == 0) {
			entryCount--;
			if (node.rightChild != null && node.leftChild != null) {
				Node leastNode = getLeast(node.rightChild);
				node.rightChild = removeRec(node.rightChild, key);
				node.entry = leastNode.entry;
				entryCount++;
			} else if (node.leftChild != null) {
				node = node.leftChild;
			} else {
				node = node.rightChild;
			}
		} else if (comparasionResult < 0) {
			node.leftChild = removeRec(node.leftChild, key);
		} else {
			node.rightChild = removeRec(node.rightChild, key);
		}
		
		return node;
	}

	@Override
	public V get(K key) {
		
		Node currentNode = root;
		V returnValue = null;
		boolean found = false;
		
		while (currentNode != null && !found) {
			int comparasionResult = key.compareTo(currentNode.entry.key);
			if (comparasionResult == 0) {
				found = true;
				returnValue = currentNode.entry.value;
			} else if (comparasionResult < 0) {
				currentNode = currentNode.leftChild;
			} else {
				currentNode = currentNode.rightChild;
			}
		}
		
		return returnValue;
	}

	@Override
	public void clear() {
		root = null;
		entryCount = 0;
	}

	@Override
	public int size() {
		return entryCount;
	}

	@Override
	public List<K> keys() {
		List<K> keysList = new ArrayList<K>(entryCount);
		
		List<Entry<K, V>> entriesList = entries();
		
		for (int i = 0; i < entryCount; i++) {
			keysList.add(i, entriesList.get(i).key());
		}
		
		return keysList;
	}

	@Override
	public List<V> values() {
		List<V> valuesList = new ArrayList<V>(entryCount);
		
		List<Entry<K, V>> entriesList = entries();
		
		for (int i = 0; i < entryCount; i++) {
			valuesList.add(i, entriesList.get(i).value());
		}
		return valuesList;
	}

	@Override
	public List<Entry<K, V>> entries() {
		List<Entry<K, V>> entriesList = new ArrayList<Entry<K, V>>(entryCount);
		entriesPreorderRec(root, entriesList);
		return entriesList;
	}
	
	public void entriesPreorderRec(Node node, List<Entry<K, V>> list) {
		if (node == null) {
			return;
		}
		list.add(list.size() - 1, node.entry);
		entriesPreorderRec(node.leftChild, list);
		entriesPreorderRec(node.rightChild, list);
	}

	@Override
	public Entry<K, V> firstEntry() {
		Node leastNode = getLeast(root);
		return leastNode.entry;
	}

	@Override
	public Entry<K, V> lastEntry() {
		Node greatestNode = getGreatest(root);
		return greatestNode.entry;
	}

	@Override
	public List<Entry<K, V>> entriesInInterval(K least, K greatest) {
		List<Entry<K, V>> entriesList = new LinkedList<Entry<K, V>>();
		entriesInIntervalRec(root, least, greatest, entriesList);
		return entriesList;
	}
	
	/**
	 * Gets the entries in the indicated interval.
	 * @param node The node to be processed.
	 * @param least The least key of the interval.
	 * @param greatest The greatest key of the interval.
	 * @param list The list in which the entries are put.
	 */
	private void entriesInIntervalRec(Node node, K least, K greatest, List<Entry<K , V>> list) {
		if (node == null) {
			return;
		}
		if (least.compareTo(node.entry.key) <= 0 && greatest.compareTo(node.entry.key) >= 0) {
			list.add(list.size() - 1, node.entry);
		}
		if (node.leftChild != null && least.compareTo(node.leftChild.entry.key) <= 0) {
			entriesInIntervalRec(node.leftChild, least, greatest, list);
		}
		if (node.rightChild != null && greatest.compareTo(node.rightChild.entry.key) >= 0) {
			entriesInIntervalRec(node.rightChild, least, greatest, list);
		}
	}

	@Override
	public List<Entry<K, V>> ascendentsUpTo(K key) {
		List<Entry<K, V>> entriesList = new LinkedList<Entry<K, V>>();
		ascendentsUpToRec(root, key, entriesList);
		return entriesList;
	}
	
	/**
	 * Gets The entries with keys up to the indicated key.
	 * @param node the node to be processed.
	 * @param key The delimiter key.
	 * @param list The list in which to deposit the entries.
	 */
	public void ascendentsUpToRec(Node node, K key, List<Entry<K, V>> list) {
		
		if (node == null) {
			return;
		}
		
		if (key.compareTo(node.entry.key) >= 0) {
			list.add(list.size() - 1, node.entry);
		}
		
		ascendentsUpToRec(node.leftChild, key, list);
		
		if (node.rightChild != null && key.compareTo(node.rightChild.entry.key) >= 0) {
			ascendentsUpToRec(node.rightChild, key, list);
		}
	}
 
	@Override
	public List<Entry<K, V>> descendentsDownTo(K key) {
		return null;
	}
	
	/**
	 * The recursive method to get the entries with keys less than the indicated one.
	 * @param node
	 * @param key
	 * @param list
	 */
	public void descendentsDownToRec(Node node, K key, List<Entry<K, V>> list) {
		if (node == null) {
			return;
		}
		
		if (key.compareTo(node.entry.key) <= 0) {
			list.add(list.size() - 1, node.entry);
		}
		
		if (node.leftChild != null && key.compareTo(node.leftChild.entry.key) <= 0) {
			descendentsDownToRec(node.leftChild, key, list);
		}
		descendentsDownToRec(node.rightChild, key, list);
	}
	
	/**
	 * Gets the least node of the subtree of the indicated node.
	 * @param node The root of the subtree to get its least node.
	 * @return The node with the least key.
	 */
	private Node getLeast(Node node) {
		while (node.leftChild != null) {
			node = node.leftChild;
		}
		return node;
	}
	
	/**
	 * Gets the greatest node of the sub tree of the indicated node.
	 * @param node The root of the subtree to get its greatest node.
	 * @return The node with the greatest key in the subtree.
	 */
	private Node getGreatest(Node node) {
		while(node.rightChild != null) {
			node = node.rightChild;
		}
		return node;
	}
	
	/**
	 * The entry of the AvlTreeMap.
	 *
	 */
	private class AvlTreeMapEntry implements Entry<K, V> {
		
		private K key;
		private V value;
		
		/**
		 * Creates an entry with the indicated key and value.
		 * @param key The key of the entry.
		 * @param value The value of the entry.
		 */
		private AvlTreeMapEntry(K key, V value) {
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
	
	private class Node {
		
		private AvlTreeMapEntry entry;
		private Node rightChild = null;
		private Node leftChild = null;
		private int height;
		
		/**
		 * Creates a node with the indicated height.
		 * @param Height The height of the node.
		 */
		private Node(AvlTreeMapEntry entry, int height) {
			this.entry = entry;
			this.height = height;
		}
	}
}
