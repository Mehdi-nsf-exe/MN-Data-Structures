package mnds.trees.bnary_tree;

/**
 * Definition of a method that is used to iterate over the nodes of a tree
 * using the methods of the tree.
 * @author Mehdi Nasef
 *
 * @param <E> The type of the elements of the tree.
 */
public interface NodeVisitor<E> {

	/**
	 * Visits the content of the node.
	 * @param nodeContent The content of the node to visit.
	 */
	public void visit(E nodeContent);
}
