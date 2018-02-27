package BinaryTree;

public class Tree<AnyType> {

	private Node<AnyType> root = null; // Racine de l'arbre

	public void insert (AnyType elem) {
		Node<AnyType> newElem = new Node<AnyType>(elem);
		root = insert(root, newElem);
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

	}

	public String printTreePreOrder() {
		return "{" + this.printPreOrder(root) + "}";

	}

	private String printPreOrder(Node root) {
		String results = "";

		// A compléter
		
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

