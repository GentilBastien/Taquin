package taquin;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Classe qui d�crit l'interface d'une grille de Taquin.
 * 
 * @author GATTACIECCA Basti1
 * @author POLYDORAS Dimi3
 * @author DESCOTILS Juli8
 *
 */
public class GrilleGraphique extends Grille {

	public static void main(String[] args) {
		new GrilleGraphique(5 * 5, "img//dimi.png");
	}

	/**
	 * La JFrame utilis�e.
	 */
	private JFrame frame;
	/**
	 * Le JPanel contenu par la JFrame, et qui contient les cases du Taquin.
	 */
	private JPanel panel;
	/**
	 * Tableau d'images qui sert � stocker les images pour initialiser les cases. �
	 * noter qu'on utilise ce tableau uniquement pour l'initialisation des cases
	 * avec leur image. Donc ce tableau n'est jamais modifi� par le programme, et
	 * les images sont stock�es dans l'ordre.
	 */
	private ImageIcon[] imgs;
	/**
	 * Tableau de cases qui sert � initialiser les cases dans l'ordre. Quand on
	 * d�place une case, et pour mettre � jour la grille graphique, on vide le panel
	 * puis on rajoute toutes les cases dans le bon ordre.
	 */
	private Case[] cases;
	/**
	 * Chemin de l'image finale du Taquin.
	 */
	private final String imagePath;

	/**
	 * Construit une GrilleGraphique � partir de la taille totale de cases.
	 * 
	 * @param size Le nombre de cases total.
	 */
	public GrilleGraphique(int size, final String path) {
		super(size);
		imagePath = path;
		imgs = new ImageIcon[size];
		cases = new Case[size];

		initFrame();
	}

	private void initFrame() {
		frame = new JFrame("Jeu du Taquin");
		frame.setSize(500, 500);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		panel = new JPanel(new GridLayout(dim, dim, 1, 1));
		decouperImage();

		for (int i = 0; i < size; i++) {
			cases[i] = new Case(i, imgs[i]);
		}

		updateGrille();
		frame.getContentPane().add(panel);
		frame.setVisible(true);
	}

	/**
	 * D�coupe l'image totale originale en fonction de la dimension et stocke chaque
	 * petite image dans le tableau d'image.
	 */
	private void decouperImage() {
		if (imagePath == null)
			throw new IllegalArgumentException("L'image est null.");

		ImageIcon img = new ImageIcon(imagePath);
		if (img.getIconWidth() != img.getIconHeight())
			throw new IllegalArgumentException("L'image n'est pas carr�e.");

		int preferredSize = 600, l = preferredSize / dim;

		BufferedImage buff = new BufferedImage(preferredSize, preferredSize, BufferedImage.TYPE_INT_RGB);
		Graphics g = buff.createGraphics();
		g.drawImage(img.getImage(), 0, 0, null);

		for (int i = 0; i < dim; i++)
			for (int j = 0; j < dim; j++) {
				int idx = i * dim + j;
				imgs[idx] = idx == size - 1 ? new ImageIcon("") : new ImageIcon(buff.getSubimage(j * l, i * l, l, l));
			}
	}

	/**
	 * Met � jour la grille en repla�ant toutes les cases dans le bon ordre.
	 */
	public void updateGrille() {
		panel.removeAll();
		for (int i = 0; i < size; i++)
			panel.add(cases[ordre.get(i)]);
		panel.revalidate();
	}
}