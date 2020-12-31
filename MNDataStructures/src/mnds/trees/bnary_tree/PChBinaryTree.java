package mnds.trees.bnary_tree;

/**
 * The parent-children linked implementation of the binary tree ADT
 * @author Mehdi Nasef
 *
 * @param <E> The type of the elements of the tree.
 */
public class PChBinaryTree<E> implements BinaryTree<E> {
	
	private int nodesCount = 0;
	private Node root = null;

	@Override
	public BinaryTreeNode<E> root() {
		return root;
	}

	@Override
	public void addRoot(E rootContent) throws UnsupportedOperationException {
		if (root != null) {
			throw new UnsupportedOperationException();
		}
		
		root = new Node(rootContent, null);
	}

	@Override
	public int size() {
		return nodesCount;
	}

	@Override
	public void clear() {
		root = null;
		nodesCount = 0;
	}

	@Override
	public void preorderVisit(NodeVisitor<E> visitor) {
		preorderVisitRec(visitor, this.root);
	}
	
	/**
	 * Goes over all the nodes recursively in pre-order.
	 * @param visitor The visitor of the nodes with the visitor method.
	 * @param node The node to be visited.
	 */
	private void preorderVisitRec(NodeVisitor<E> visitor, Node node) {
		
		if (node == null) {
			return;
		}
		visitor.visit(node.content);
		preorderVisitRec(visitor, node.leftChild);
		preorderVisitRec(visitor, node.rightChild);
	}

	@Override
	public void inorderVisit(NodeVisitor<E> visitor) {
		inorderVisitRec(visitor, this.root);
	}
	
	/**
	 * Goes over all the nodes recursively in in-order.
	 * @param visitor The visitor of the nodes with the visit method.
	 * @param node The node to be visited.
	 */
	private void inorderVisitRec(NodeVisitor<E> visitor, Node node) {
		if (node == null) {
			return;
		}
		inorderVisitRec(visitor, node.leftChild);
		visitor.visit(node.content);
		inorderVisitRec(visitor, node.rightChild);
	}

	@Override
	public void postorderVisit(NodeVisitor<E> visitor) {
		postorderVisit(visitor, this.root);
	}
	/**
	 * Goes over all the nodes recursively in post-order.
	 * @param visitor The visitor of the nodes with the visit method.
	 * @param node The node to be visited.
	 */
	private void postorderVisit(NodeVisitor<E> visitor, Node node) {
		if (node == null) {
			return;
		}
		postorderVisit(visitor, node.leftChild);
		postorderVisit(visitor, node.rightChild);
		visitor.visit(node.content);
	}

	/**
	 * The node of he tree implemented using a Parent-Children linked structure.
	 */
	public class Node implements BinaryTreeNode<E> {
		
		E content;
		Node parent;
		Node rightChild = null;
		Node leftChild = null;
		
		private Node(E content, Node parent) {
			this.content = content;
			this.parent = parent;
		}

		@Override
		public E content() {
			return content;
		}

		@Override
		public void setContent(E content) {
			this.content = content;
		}

		@Override
		public BinaryTreeNode<E> parent() {
			return parent;
		}

		@Override
		public BinaryTreeNode<E> rightChild() {
			return rightChild;
		}

		@Override
		public BinaryTreeNode<E> leftChild() {
			return leftChild;
		}

		@Override
		public void addRightChild(E element) throws UnsupportedOperationException {
			if (this.rightChild != null) {
				throw new UnsupportedOperationException();
			}
			this.rightChild = new Node(element, this);
			nodesCount++;
		}

		@Override
		public void addLeftChild(E element) throws UnsupportedOperationException {
			if (this.leftChild != null) {
				throw new UnsupportedOperationException();
			}
			this.leftChild = new Node(element, this);
			nodesCount++;
		}

		@Override
		public void addRightBranch(BinaryTree<E> branch) throws UnsupportedOperationException {
			if (this.rightChild != null) {
				throw new UnsupportedOperationException();
			}
			if (branch.root() != null) {
				this.rightChild = new Node(branch.root().content(), this);
				nodesCount++;
				addBranchRec(branch.root(), this.rightChild);
			}
		}

		@Override
		public void addLeftBranch(BinaryTree<E> branch) throws UnsupportedOperationException {
			if (this.leftChild != null) {
				throw new UnsupportedOperationException();
			}
			if (branch.root() != null) {
				this.leftChild = new Node(branch.root().content(), this);
				nodesCount++;
				addBranchRec(branch.root(), this.leftChild);
			}
		}
		
		/**
		 * Adds a branch as a child of a node recursively.
		 * @param branchNode The node of the branch to be added.
		 * @param nodeChild The child of the node which will contain the content of the root
		 * of the branch.
		 */
		private void addBranchRec(BinaryTreeNode<E> branchNode, Node nodeChild) {
			
			if (branchNode.leftChild() != null) {
				nodeChild.leftChild = new Node(branchNode.leftChild().content(), nodeChild);
				nodesCount++;
				addBranchRec(branchNode.leftChild(), nodeChild.leftChild);
			}
			if (branchNode.rightChild() != null) {
				nodeChild.rightChild = new Node(branchNode.rightChild().content(), nodeChild);
				nodesCount++;
				addBranchRec(branchNode.rightChild(), nodeChild.rightChild);
			}
		}

		@Override
		public BinaryTree<E> cutRightBranch() {
			
			BinaryTree<E> branch = cutBranch(this.rightChild);
			this.rightChild = null;
			
			return branch;
		}

		@Override
		public BinaryTree<E> cutLeftBranch() {
			
			BinaryTree<E> branch = cutBranch(this.leftChild);
			this.leftChild = null;
			
			return branch;
		}
		
		/**
		 * Cuts the branch whose root is he node passed as a parameter.
		 * @param branchRoot The root of the branch to cut.
		 * @return The cut branch.
		 */
		private BinaryTree<E> cutBranch(Node branchRoot) {
			
			PChBinaryTree<E> branch = new PChBinaryTree<E>();
			branch.root = branchRoot;
			
			NodesCounter<E> nodesCounter = new NodesCounter<E>();
			branch.preorderVisit(nodesCounter);
			branch.nodesCount = nodesCounter.getAndResetNodesCount();
			nodesCount -= branch.nodesCount;
			
			return branch;
		}
	}
	
	private static class NodesCounter<E> implements NodeVisitor<E> {
		
		int nodesCount = 0;

		@Override
		public void visit(E nodeContent) {
			nodesCount++;
		}
		
		/**
		 * Gets and resets the nodes' count to 0.
		 * @return The count of the nodes visited.
		 */
		public int getAndResetNodesCount() {
			int temp = nodesCount;
			nodesCount = 0;
			return temp;
		}
	}
}
