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
				if(this.valeur >= autre.valeur) {
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
		Node tempParent = this.parent;
		ArrayList<Node> tempEnfants = tempParent.enfants;
		
		//Grand-parent
		if(this.parent.parent != null) {
			this.parent = this.parent.parent;
			this.parent.removeEnfant(tempParent);
			this.parent.addEnfant(this);
		} else {
			this.parent = null;
		}
		
		//Parent
		tempParent.removeEnfant(this);
		tempParent.enfants = this.enfants;
		int tempOrdre = tempParent.ordre;
		tempParent.ordre = this.ordre;
		
		//this
		this.enfants = tempEnfants;
		this.removeEnfant(this);
		this.addEnfant(tempParent);
		this.ordre = tempOrdre;
		
		tempParent.parent = this;
		
		for(Node enfant : this.enfants) {
			enfant.parent = this;
		}
		for(Node enfant : tempParent.enfants) {
			enfant.parent = tempParent;
		}
	}

	public ArrayList<Node> delete() {
		// A completer
		while(this.parent != null) {
			this.moveUp();
		}
		for(Node enfant : this.enfants) {
			enfant.parent = null;
		}
		return this.enfants;
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
		ArrayList<Integer> tableauTrie = new ArrayList<Integer>();
		ArrayList<Node> tableauNodeRestant = this.enfants;
		tableauTrie.add(this.valeur);
		
		while(tableauNodeRestant.size() != 0) {
			Node nodeMax = nodeMax(tableauNodeRestant);
			tableauTrie.add(nodeMax.valeur);
			tableauNodeRestant.remove(nodeMax);
			if(nodeMax.enfants != null) {
				tableauNodeRestant.addAll(nodeMax.enfants);
			}
		}
		
		
		return tableauTrie;
	}
	
	public Node nodeMax(ArrayList<Node> list) {
		if(list.size() == 0) {
			return null;
		}
		Node max = list.get(0);
		for(Node node : list) {
			if(node.valeur > max.valeur) {
				max = node;
			}
		}
		return max;
	}

	public void print() {
        print("", true);
    }

    private void print(String prefix, boolean isTail) {
        System.out.println(prefix + (isTail ? "'-- " : "|-- ") + valeur + "(" + ordre + ")");
        for (int i = 0; i < enfants.size() - 1; i++) {
            enfants.get(i).print(prefix + (isTail ? "    " : "|   "), false);
        }
        if (enfants.size() > 0) {
            enfants.get(enfants.size() - 1)
                    .print(prefix + (isTail ?"    " : "|   "), true);
        }
    }
}
