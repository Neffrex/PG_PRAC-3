package aplicacio;

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
	private int maxAny, maxCO2, anyActual, gammaVerd;
	
	public CanviAnyListener(int maxAny, int maxCO2, JButton[] botons, JLabel etiquetaAny, LlistaPlantacions llistaPlantacions) {
		this.maxAny = maxAny;
		this.maxCO2 = maxCO2;
		this.botons = botons;
		this.etiquetaAny = etiquetaAny;
		this.llistaPlantacions = llistaPlantacions;
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		JSlider slider = (JSlider) e.getSource();
		this.anyActual = slider.getValue();
		
		

	}

}
