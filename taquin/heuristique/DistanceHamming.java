package taquin.heuristique;

import java.util.List;

import taquin.arbre.Arbre;
import taquin.arbre.Grille;

/**
 * D�crit la distance Hamming (voir rapport).
 * 
 * @author GATTACIECCA Basti1
 * @author POLYDORAS Dimi3
 * @author DESCOTILS Juli8
 *
 */
public class DistanceHamming implements Heuristique {

	@Override
	public int computesWeight(Grille g) {
		List<Integer> ordre = g.getOrdre();
		int score = 0;

		for (int i = 0; i < Arbre.SIZE; i++)
			if (ordre.get(i) != i)
				score++;

		return score;
	}
	
	@Override
	public String toString() {
		return "[Heuristique= Distance de Hamming]";
	}
}