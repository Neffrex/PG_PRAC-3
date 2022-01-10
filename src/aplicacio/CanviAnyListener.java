package aplicacio;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import llistes.LlistaPlantacions;

public class CanviAnyListener implements ChangeListener {
	
	// Components swing
	private JButton[] botons;
	private JLabel etiquetaAny;
	private LlistaPlantacions llistaPlantacions;
	// Atributs
	private int maxAny, anyActual, gammaVerd;
	private double maxCO2;
	
	public CanviAnyListener(int maxAny, JButton[] botons, JLabel etiquetaAny, LlistaPlantacions llistaPlantacions) {
		this.maxAny = maxAny;
		this.botons = botons;
		this.etiquetaAny = etiquetaAny;
		this.llistaPlantacions = llistaPlantacions;
		maxCO2 = llistaPlantacions.getMaxCO2();
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		JSlider slider = (JSlider) e.getSource();
		this.anyActual = slider.getValue();
		
		for (int i = 0; i < botons.length; i++) {
			gammaVerd = (int)Math.floor((llistaPlantacions.getAbsorcio(i, anyActual)/maxCO2)*255);
			botons[i].setBackground(new Color(0,gammaVerd,0));
		}

	}

}
