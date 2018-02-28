package BinaryTree;

public class Tree<AnyType> {

	private String results = "";	// Je mets results en attribut sinon il est reinitialiser a chaque appel recursif
	private Node<AnyType> root = null; // Racine de l'arbre

	public void insert (AnyType elem) {
		if (root == null) {
			root = new Node<AnyType>(elem);
		}
		else {
			Node<AnyType> node = new Node<AnyType>(elem);
			node = insert(root, node);
		}
		
	}

	@SuppressWarnings("unchecked")
	private Node<AnyType> insert (Node<AnyType> root, Node<AnyType> newelement) {
		// A compléter
		if (((Comparable) newelement.val).compareTo(root.val) > 0) {
			if (root.right == null) {
				root.right = new Node<AnyType>(newelement.val);
				return root.right;
			} else {
				return insert(root.right, newelement);
			}
		}
		if (((Comparable) newelement.val).compareTo(root.val) < 0) {
			if (root.left == null) {
				root.left = new Node<AnyType>(newelement.val);
				return root.left;
			} else {
				return insert(root.left, newelement);
			}
		}
		else if (((Comparable) newelement.val).compareTo(root.val) == 0) {
			System.out.println("Ne peut pas inserer un doublon !");
		}
		return null;  //N a pas trop de sens mais il faut un retour je laisse ca pour l instant Louis
	}

	public int getHauteur () {
		return this.getHauteur(root);
	}

	private int getHauteur(Node<AnyType> tree) {
		// A compléter 
		if (tree == null) {
			return 0;
		}
		return 1 + Math.max(getHauteur(tree.left), getHauteur(tree.right));
	}

	public String printTreePreOrder() {
		return "{" + this.printPreOrder(root) + "}";

	}

	private String printPreOrder(Node root) {
		// A compléter
		if (root != null) {
			results += root.val + ", ";
		}
		if (root.left != null) {
			printPreOrder(root.left);
		}
		if (root.right != null) {
			printPreOrder(root.right);
		}
		
		return results;
		
	}

	private class Node<AnyType> {
		AnyType val; // Valeur du noeud
		Node right; // fils droite
		Node left; // fils gauche

		public Node (AnyType val) {
			this.val = val;
			right = null;
			left = null;
		}

	}


}

