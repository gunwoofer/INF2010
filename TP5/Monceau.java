import java.util.ArrayList;

public class Monceau {
	ArrayList<Node> arbres;

	public Monceau() {
		arbres = new ArrayList<Node>();
	}
	
	public Monceau(Node racine) {
		arbres = new ArrayList<Node>();
		arbres.add(racine);
	}

	public void fusion(Monceau autre) throws DifferentOrderTrees {
		// A completer
		try {
			int ordreMax = ordreMax();
			Node arbreRetenu = null;
			
			for (int j = 0; j < ordreMax; j++) {
				ArrayList<Node> arbresOrdrej = ArbreOrdreI(j, arbreRetenu);
				int nombreArbresOrdreJ = arbresOrdrej.size(); 
				if (nombreArbresOrdreJ == 1) {
					this.arbres.addAll(arbresOrdrej);
				}
				else if (nombreArbresOrdreJ == 2) {
					arbreRetenu = arbresOrdrej.get(0).fusion(arbresOrdrej.get(1));
				}
				else if (nombreArbresOrdreJ == 3) {
					arbreRetenu = arbresOrdrej.get(0).fusion(arbresOrdrej.get(1));
					this.arbres.add(arbresOrdrej.get(2));
				}
			}
		}catch (DifferentOrderTrees exception) {
			System.out.println(exception);
			return;
		}
	}
	
	private ArrayList<Node> ArbreOrdreI(int ordre, Node retenue) {
		ArrayList<Node> arbresOrdreI = new ArrayList<Node>();
		for (Node arbre : this.arbres) {
			if (arbre.ordre == ordre) {
				arbresOrdreI.add(arbre);
			}
		}
		if(retenue != null && retenue.ordre == ordre) {
			arbresOrdreI.add(retenue);
		}
		return arbresOrdreI;
		
	}

	private int ordreMax() {
		if (this.arbres.size() == 0) {
			return -1;
		}
		int max = this.arbres.get(0).ordre;
		for (Node node : this.arbres) {
			if(node.ordre > max) {
				max = node.ordre;
			}
		}
		return max;
	}
	
	public void insert(int val) {
		// A completer
		Monceau arbre = new Monceau(new Node(val));
		try {
			this.fusion(arbre);
		} catch (DifferentOrderTrees e) {
			System.out.println(e);
		}
	}

	public boolean delete(int val) {
		// A completer
		return false;
	}

	public void print() {
		for (Node node : arbres) {
			System.out.println("\n\nordre : " + node.ordre);
			node.print();
		}
	}

}
