package taquin.algorithme;

import java.util.Collection;

import taquin.arbre.Grille;

public class Algo_DepthFirst extends Algorithme {

	public Algo_DepthFirst(Grille etatInitial) {
		super(etatInitial);
	}

	@Override
	public int nextMove() {
		if (memoire.isEmpty()) {
			System.out.println("Memoire vide");
			return nbCoups;
		}
		
		Grille first = memoire.removeFirst();

		/*
		 * On calcule ses enfants et on les ajoute tous dans la liste tri�e.
		 */
		Collection<Grille> firstChildren = first.computesChildrenToThisNode();
		if (firstChildren == null)
			return nbCoups;

		for (Grille g : firstChildren) {
			first.insert(g, 0);
			memoire.addFirst(g);
		}

		nbCoups++;
		return nextMove();
	}

	@Override
	public String toString() {
		return "[Algo= Depth-first]";
	}
}
