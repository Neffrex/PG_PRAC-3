package aplicacio;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Insets;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Arrays;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.BorderLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JSlider;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.AttributeSet.ColorAttribute;

import llistes.LlistaPlantacions;

import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

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
	private int maxCO2;
	private LlistaPlantacions llistaPlantacions;
	
	public InterficieGrafica(LlistaPlantacions llistaPlantacions) {
		super("Plantacions Forestals");
		this.llistaPlantacions = llistaPlantacions;
		
		numPlantacions = llistaPlantacions.getNumElem();
		anyActual = 0;
		maxCO2 = 100; //TODO maxCO2 ponerlo al CO2 maximo de todas las plantaciones
		
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
			botons[i] = new JButton(String.valueOf(i));
			botons[i].setPreferredSize(new Dimension(100,100));
			botons[i].setBackground(new Color(0,64,0));
			panelPlantacions.add(botons[i]);
		}
		
		System.out.println();
		System.out.println(Arrays.toString(botons));
		
		/*		Selector d'any		@dependencies: botons, etiquetaAny		*/
		selectorAny = new JSlider(JSlider.HORIZONTAL, 0, numPlantacions, 0);
		selectorAny.setPaintLabels(true);
		selectorAny.setMajorTickSpacing(10);
		selectorAny.addChangeListener(new CanviAnyListener(numPlantacions, maxCO2, botons, etiquetaAny, llistaPlantacions));
		
		/*		Panel Scroll de les Plantacions		@dependencies: panelPlantacions, selectorAny, etiquetaAny*/
		panelScrollPlantaciones = new JScrollPane();
		panelScrollPlantaciones.setViewportView(panelPlantacions);
		panelScrollPlantaciones.setColumnHeaderView(selectorAny);
		panelScrollPlantaciones.setCorner(ScrollPaneConstants.UPPER_RIGHT_CORNER, etiquetaAny);
		panelScrollPlantaciones.getVerticalScrollBar().setUnitIncrement(16);
		add(panelScrollPlantaciones, BorderLayout.CENTER);
		
		pack();
		setVisible(true);
	}
	
}
