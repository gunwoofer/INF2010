package BinaryTree;

import java.util.ArrayList;
import java.util.Random;

public class mainExo1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Test perso de l arbre de recherche Exercice 1 : ");
		Tree<Integer> monarbre = new Tree<Integer>();
		
		int nbreElements = 20;
		ArrayList<Integer> listerandom = randomIntegers(nbreElements);
		for(int i = 0; i < nbreElements; i++) {
			monarbre.insert(listerandom.get(i));
		}
		String preordre = monarbre.printTreePreOrder();
		System.out.println();
		System.out.println("Affichage pre ordre de l arbre : ");
		System.out.println(preordre);
		System.out.println();
		
		int hauteur = monarbre.getHauteur();
		System.out.println("Taille de l arbre : " + hauteur);
	}
	
   public static ArrayList<Integer> randomIntegers(int length)
   {
	   Random generator = new Random( System.nanoTime() );
	   ArrayList<Integer> listerandom = new ArrayList<Integer>(length);
	   for (int i = 0; i < length; i++) {
		   int n = generator.nextInt(100);
		   if(!listerandom.contains(n)) {
			   listerandom.add(n);
		   } else {
			   i--;
		   }
	   }
	   return listerandom;
   }	

}
