import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author maitr
 */
public class Node {

	public int ordre;
	public Node parent;

	private int valeur;
	private ArrayList<Node> enfants;

	public Node(int valeur) {
		this.valeur = valeur;
		ordre = 0;
		this.parent = null;
		enfants = new ArrayList<Node>();
	}

	public Node(int valeur, Node parent) {
		ordre = 0;
		this.valeur = valeur;
		this.parent = parent;
		enfants = new ArrayList<Node>();
	}

	public int getVal() {
		return valeur;
	}

	public ArrayList<Node> getEnfants() {
		return enfants;
	}

	public void addEnfant(Node enfant) {
		enfants.add(enfant);
	}

	public boolean removeEnfant(Node enfant) {
		if (enfants.contains(enfant)) {
			enfants.remove(enfant);
			return true;
		}
		return false;
	}

	public void removeEnfants(ArrayList<Node> enfants) {
		this.enfants.removeAll(enfants);
	}

	public Node fusion(Node autre) throws DifferentOrderTrees {
		// A completer
		if(this.ordre != autre.ordre) {
			throw new DifferentOrderTrees();
		}
		else {
			if (this.parent == null && autre.parent == null) {
				if(this.valeur > autre.valeur) {
					this.addEnfant(autre);
					autre.parent = this;
					this.ordre++;
					return this;
				}
				else if(autre.valeur > this.valeur) {
					autre.addEnfant(this);
					this.parent = autre;
					autre.ordre++;
					return autre;
				}
			}
		}
		return null;
	}

	private void moveUp() {
		// A completer
		if (this.parent == null) {
			return;
		}
		Node temp = this.parent;
		
		this.parent.valeur = this.valeur;
		this.valeur = temp.valeur;
		
	}

	public ArrayList<Node> delete() {
		// A completer
		return null;
	}

	public Node findValue(int valeur) {
		// A completer
		if (this.getVal() == valeur) {
			return this;
		}
		if (this.getVal() > valeur) {
			for (int i=0; i < this.enfants.size();i++) {
				if (this.enfants.get(i).getVal()== valeur) {
					return this.enfants.get(i);
				}
				else {
					if (this.enfants.get(i).getVal()>valeur) {
						Node nodeFound = this.enfants.get(i).findValue(valeur);
						if (nodeFound != null)
							return nodeFound;
					}
				}
			}
		}
		return null;
	}

	public ArrayList<Integer> getElementsSorted() {
		// A completer
		return null;
	}

	public void print() {
        print("", true);
    }

    private void print(String prefix, boolean isTail) {
        System.out.println(prefix + (isTail ? "'-- " : "|-- ") + valeur);
        for (int i = 0; i < enfants.size() - 1; i++) {
            enfants.get(i).print(prefix + (isTail ? "    " : "|   "), false);
        }
        if (enfants.size() > 0) {
            enfants.get(enfants.size() - 1)
                    .print(prefix + (isTail ?"    " : "|   "), true);
        }
    }
}
