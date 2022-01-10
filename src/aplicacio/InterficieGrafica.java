package aplicacio;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Arrays;
import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JSlider;
import javax.swing.ScrollPaneConstants;
import javax.swing.JScrollPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import llistes.LlistaPlantacions;
import llistes.LlistaPlantes;
import plantacions.Plantacions;
import plantacions.Plantes;

public class InterficieGrafica extends JFrame {

	private static final long serialVersionUID = 7775700307594930277L;
	public static final int AMPLADA=400, ALTURA=600;
	// Components
	private JButton[] botons;
	private JPanel panelPlantacions;
	private JLabel etiquetaAny;
	private JSlider selectorAny;
	private JScrollPane panelScrollPlantaciones;
	
	// Atributs
	private int anyActual;
	private int numPlantacions;
	
	public InterficieGrafica(LlistaPlantacions llistaPlantacions, LlistaPlantes llistaPlantes) {
		super("Plantacions Forestals");
		
		numPlantacions = llistaPlantacions.getNumElem();
		anyActual = llistaPlantacions.getMinAny();
		
		setPreferredSize(new Dimension(AMPLADA, ALTURA));
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		
		/*		Etiqueta que mostra l'any actual		@dependencies: none*/
		etiquetaAny = new JLabel(String.valueOf(anyActual));
		
		/*		Panel Plantacions (Viewport)		@dependencies: none*/
		panelPlantacions = new JPanel();
		panelPlantacions.setLayout(new GridLayout(0,2,5,5));
		
		/*		Botons		@dependencies: panelPlantacions		*/
		botons = new JButton[numPlantacions];
		//Agregar los botones al panel_principal
		for (int i = 0; i < botons.length; i++) {
			Plantacions plantacio = llistaPlantacions.get(i);
			botons[i] = new JButton(String.format("<html>Finca: %s<br>AnyPlantacio: %d<br>Total Superficie: %f<br>Plantes Arbustiques: %d<br>Plantes Arboreas: %d</html>",
					plantacio.getNomPartida(),
					plantacio.getAnyPlantacio(),
					plantacio.getSuperficieTotal(),
					plantacio.getUnitats(Plantes.ARBUSTICA),
					plantacio.getUnitats(Plantes.ARBOREA)
					));
			botons[i].setPreferredSize(new Dimension(100,100));
			botons[i].setBackground(new Color(0,0,0));
			botons[i].setEnabled(false);
			panelPlantacions.add(botons[i]);
		}
		
		/*		Selector d'any		@dependencies: botons, etiquetaAny		*/
		selectorAny = new JSlider(JSlider.HORIZONTAL, 0, llistaPlantes.getMaxEdad(), 0);
		selectorAny.setPaintLabels(true);
		selectorAny.setMajorTickSpacing(10);
		selectorAny.addChangeListener(new CanviAnyListener(llistaPlantes.getMaxEdad(), botons, etiquetaAny, llistaPlantacions));
		
		JPanel panelAny = new JPanel();
		panelAny.setLayout(new BorderLayout());
		panelAny.add(selectorAny, BorderLayout.WEST);
		panelAny.add(etiquetaAny, BorderLayout.EAST);
		
		/*		Panel Scroll de les Plantacions		@dependencies: panelPlantacions, selectorAny, etiquetaAny*/
		panelScrollPlantaciones = new JScrollPane();
		panelScrollPlantaciones.setViewportView(panelPlantacions);
		panelScrollPlantaciones.setColumnHeaderView(panelAny);
		panelScrollPlantaciones.setCorner(ScrollPaneConstants.UPPER_RIGHT_CORNER, etiquetaAny);
		panelScrollPlantaciones.getVerticalScrollBar().setUnitIncrement(16);
		add(panelScrollPlantaciones, BorderLayout.CENTER);
		
		pack();
		setVisible(true);
	}
	
}
