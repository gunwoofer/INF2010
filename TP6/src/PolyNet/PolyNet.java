package PolyNet;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;

public class PolyNet {
    private PolyNetNode[] nodes;

    public PolyNet(PolyNetNode[] nodes) {
        this.nodes = nodes;
    }

    // MST (using Prim's algorithm).
    public int computeTotalCableLength() {
        int totalCableLength = 0;
        PriorityQueue<PolyNetConnection> knownConnections = new PriorityQueue<>();
        HashSet<PolyNetNode> visitedNodes = new HashSet<>();
        // À compléter
        
        visitedNodes.add(this.nodes[0]);
        /*
         * G Graphe de bas
         * T Arbre couvrant minimal
         */
        while (!allNodesVisited(visitedNodes)) {
        	ArrayList<PolyNetConnection> fromTtoNotT = this.fromTtoNotT(knownConnections, visitedNodes);
        	PolyNetConnection minimum = findMinimumConnection(fromTtoNotT);
        	knownConnections.add(minimum);
        	visitedNodes.add(minimum.getConnectedNode());
        	totalCableLength += minimum.getDistance();
        }

        return totalCableLength;
    }
    
    private boolean allNodesVisited(HashSet<PolyNetNode> visitedNodes) {
    	for (PolyNetNode node : this.nodes) {
    		if (!visitedNodes.contains(node)) {
    			return false;
    		}
    	}
    	return true;
    }
    
    private ArrayList<PolyNetConnection> fromTtoNotT(PriorityQueue<PolyNetConnection> Tconnections, HashSet<PolyNetNode> Tnodes) {
    	ArrayList<PolyNetConnection> fromTtoNotT = new ArrayList<PolyNetConnection>();
    	for (PolyNetNode node : Tnodes) {
    		for(PolyNetConnection connection : node.getConnections()) {
    			if (!Tnodes.contains(connection.getConnectedNode())) {
    				fromTtoNotT.add(connection);
    			}
    		}
    	}
    	return fromTtoNotT;
    }
    
    private PolyNetConnection findMinimumConnection(ArrayList<PolyNetConnection> listeConnections) {
    	if (listeConnections.size() == 0) {
    		return null;
    	}
    	PolyNetConnection min = listeConnections.get(0);
    	for (PolyNetConnection co : listeConnections) {
    		if (co.getDistance() < min.getDistance()) {
    			min = co;
    		}
    	}
    	return min;
    }
}
