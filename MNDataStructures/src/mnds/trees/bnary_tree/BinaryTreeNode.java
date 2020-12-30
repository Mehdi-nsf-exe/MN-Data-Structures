package mnds.trees.bnary_tree;

/**
 * The operations that a node of a binary tree supports.
 * @author Mehdi Nasef
 *
 * @param <E> The type of the element that the node contains.
 */
public interface BinaryTreeNode<E> {

	/**
	 * Gets the element that the node contains.
	 * @return The element that the node contains.
	 */
	public E content();
	
	/**
	 * Sets the content of the node.
	 * @param content The new content of the node.
	 */
	public void setContent(E content);
	
	/**
	 * Gets the parent of the node.
	 * @return The parent of the node.
	 */
	public BinaryTreeNode<E> parent();
	
	/**
	 * Gets the right child of the node.
	 * @return The right child of the node.
	 */
	public BinaryTreeNode<E> rightChild();
	
	/**
	 * Gets the left child fo the node.
	 * @return The left child of hte node.
	 */
	public BinaryTreeNode<E> leftChild();
	
	/**
	 * Adds a right child to the node.
	 * @param element The content of the child to be added.
	 * @throws UnsupportedOperationException if the node already has a right child.
	 */
	public void addRightChild(E element) throws UnsupportedOperationException;
	
	/**
	 * Adds a left child to the node.
	 * @param element The content of the child to be added.
	 * @throws UnsupportedOperationException if the node already has a right child.
	 */
	public void addLeftChild(E element) throws UnsupportedOperationException;
	
	/**
	 * Adds a branch to the right of the node.
	 * @param branch the tree to be added as the branch of the node.
	 * @throws UnsupportedOperationException if the node already has a right child.
	 */
	public void addRightBranch(BinaryTree<E> branch) throws UnsupportedOperationException;
	
	/**
	 * Adds a branch to the left of the node.
	 * @param branch the tree to be added as the branch of the node.
	 * @throws UnsupportedOperationException if the node already has a left child.
	 */
	public void addLeftBranch(BinaryTree<E> branch) throws UnsupportedOperationException;
	
	/**
	 * Cuts and returns the branch to the right of the node.
	 * @return The right branch of the node.
	 */
	public BinaryTree<E> cutRightBranch();
	
	/**
	 * Cuts and returns the left branch of the node.
	 * @return The left branch of the node.
	 */
	public BinaryTree<E> cutLeftBranch();
	
	/**
	 * Goes over all the nodes in pre-order.
	 * @param visitor The object with the method visit.
	 */
	public void preorderVisit(NodeVisitor<E> visitor);
	
	/**
	 * Goes over all the nodes in in-order.
	 * @param visitor The object with the method visit.
	 */
	public void inorderVisit(NodeVisitor<E> visitor);
	
	/**
	 * Goes over all the nodes in post-order.
	 * @param visitor The object with the visit method.
	 */
	public void postorderVisit(NodeVisitor<E> visitor);
}
