package mnds.trees.bnary_tree;

/**
 * The definition of the operations of the Tree ADT.
 * @author Mehdi Nasef.
 *
 * @param <E> The type of the elements to be contained the 
 * BinaryTree data structure
 */
public interface BinaryTree<E> {
	
	/**
	 * Gets the root of the tree.
	 * @return The root of the tree or null if the tree is empty.
	 */
	public BinaryTreeNode<E> root();

	/**
	 * Adds a root to an empty tree.
	 * @param rootContent The content of the root.
	 * @throws UnsupportedOperationException If the tree already has a root.
	 */
	public void addRoot(E rootContent) throws UnsupportedOperationException;
	
	/**
	 * Gets the number of nodes of the tree.
	 * @return The number of nodes of the tree.
	 */
	public int size();
	
	/**
	 * Clears all the nodes of the tree.
	 */
	public void clear();
	
	public void preorderVisit();
}
