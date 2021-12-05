package taquin.arbre;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;

/**
 * 
 * @author basti
 *
 */
public class FenetreArbre extends JFrame {
	private static final long serialVersionUID = 1L;

	private Arbre arbre;
	
	public static void main(String[] args) {
		new FenetreArbre();
	}

	public FenetreArbre() {
		arbre = new Arbre();
		initialize();
		setVisible(true);
	}
	
	private void initialize() {
		setSize(500, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		JTree tree = new JTree(arbre.getEntree());
		MatrixCell customCell = new MatrixCell();
		tree.setCellRenderer(customCell);
		
		scrollPane.setViewportView(tree);
	}
	
	private final class MatrixCell extends DefaultTreeCellRenderer {
		private static final long serialVersionUID = 1L;

		@Override
		public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded,
				boolean leaf, int row, boolean hasFocus) {
			Grille g = (Grille)value;
			JPanel pan = new JPanel(new GridLayout(g.getDim(), g.getDim(), 15, 1));
			for (int i : g.getOrdre()) {
				JLabel lab = new JLabel(String.valueOf(i));
				
				if (selected) {
					pan.setForeground(textSelectionColor);
					pan.setBackground(backgroundSelectionColor);
				}else {
					pan.setForeground(textNonSelectionColor);
					pan.setBackground(backgroundNonSelectionColor);
				}
				
				if (i == g.getDim()*g.getDim() - 1)
					lab.setForeground(Color.RED);
				pan.add(lab);
			}
			return pan;
		}
		
	}

}