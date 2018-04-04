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
	
	public Monceau(ArrayList<Node> arbres) {
		this.arbres = new ArrayList<Node>();
		this.arbres = arbres;
	}

	public void fusion(Monceau autre) throws DifferentOrderTrees {
		// A completer
		try {
			Node arbreRetenu = null;
			int ordreMax = ordreMax(autre, arbreRetenu);
			
			for (int j = 0; j <= ordreMax; j++) {
				ArrayList<Node> arbresOrdrej = ArbreOrdreI(j, autre, arbreRetenu);
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
				ordreMax = ordreMax(autre, arbreRetenu);
				
			}
		}catch (DifferentOrderTrees exception) {
			System.out.println(exception);
			return;
		}
	}
	
	private ArrayList<Node> ArbreOrdreI(int ordre, Monceau autre, Node retenue) {
		ArrayList<Node> arbresOrdreI = new ArrayList<Node>();
		for (Node arbre : this.arbres) {
			if (arbre.ordre == ordre) {
				arbresOrdreI.add(arbre);
				this.arbres.remove(arbre);
				break;	//On peut break puisqu il ne peut y avoir qu un arbre au maximum d ordre j
			}
		}
		for (Node arbre : autre.arbres) {
			if (arbre.ordre == ordre) {
				arbresOrdreI.add(arbre);
				autre.arbres.remove(arbre);
				break;
			}
		}
		if(retenue != null && retenue.ordre == ordre) {
			arbresOrdreI.add(retenue);
		}
		return arbresOrdreI;
		
	}

	private int ordreMax(Monceau autre, Node retenue) {
		int max = 0;
		for (Node node : this.arbres) {
			if(node.ordre > max) {
				max = node.ordre;
			}
		}
		for (Node node : autre.arbres) {
			if(node.ordre > max) {
				max = node.ordre;
			}
		}
		if(retenue != null && retenue.ordre > max) { max = retenue.ordre; }
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
		boolean deleted = false;
		try {
			for(Node arbre : this.arbres) {
				Node nodeDelete = arbre.findValue(val);
				while(nodeDelete != null) {
					deleted = true;
					Monceau resteDelete = new Monceau(nodeDelete.delete());
					this.arbres.remove(arbre);
					this.fusion(resteDelete);
					nodeDelete = arbre.findValue(val);
				}
			}
		}
		catch(DifferentOrderTrees erreur) {
			System.out.println(erreur);
		}
		return deleted;
	}

	public void print() {
		for (Node node : arbres) {
			System.out.println("\n\nordre : " + node.ordre);
			node.print();
		}
	}

}
