package aplicacio;

import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JSlider;
import javax.swing.JScrollBar;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class InterficieGrafica extends JFrame {

	private static final long serialVersionUID = 7775700307594930277L;
	public static final int AMPLADA=400, ALTURA=600;
	// Componentes
	private JButton[] buttons;
	private JPanel panelPrincipal;
	private JScrollBar scroller = new JScrollBar(1);
	
	private int anyActual = 0;
	
	public InterficieGrafica() {
		super("Plantacions Forestals");
		
		setMinimumSize(new Dimension(AMPLADA, ALTURA));
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout();
		
		panelPrincipal = new JPanel();
		panelPrincipal.setLayout(new GridLayout(0,2));
		
		//TODO Obtenir el numero de plantacions
		int num_plantacions = 6;
		buttons = new JButton[num_plantacions];
		
		//Agregar los botones al panel_principal
		for (JButton button : buttons) {
			button = new JButton("NomFinca\nAny de plantació\nSuperfície Total\nTotal d’arbres\nTotal d’arbustives");
			panelPrincipal.add(button);
		}
		
		add(panelPrincipal);
		pack();
		setVisible(true);
	}
	
}
