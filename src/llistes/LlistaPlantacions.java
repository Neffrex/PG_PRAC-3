package llistes;

import plantacions.*;

/**
 * 
 * @author Marc Fonseca Curto
 *
 */
public class LlistaPlantacions {
	private Plantacions[] llista;
	private int numElem;
	
	/**
	 * Constructor de la llista de plantacions
	 */
	public LlistaPlantacions() {
		llista = new Plantacions[100];
		numElem = 0;
	}
	
	/**
	 * Constructor de la llista de plantacions
	 */
	public LlistaPlantacions(int mida) {
		llista = new Plantacions[mida];
		numElem = 0;
	}
	
	/**
	 * Afegir una plantacio
	 * @param plantacions
	 */
	public void afegir (Plantacions plantacions) {
		boolean trobat = false;
		
		for (int i = 0; i < numElem && trobat == false; i++) {
			if (llista[i].getNomPartida().equals(plantacions.getNomPartida())) {
				trobat = true;
			}
		}
		if (numElem < llista.length && trobat == false) {
			llista[numElem] = plantacions;
			numElem++;
		}
	}

	/**
	 * Getter
	 * @return - totes les plantacions
	 */
	public Plantacions[] getLlista() {
		return llista;
	}

	/**
	 * Getter
	 * @return - nombre de elements
	 */
	public int getNumElem() {
		return numElem;
	}

	/**
	 * Setter
	 * @param numElem
	 */
	public void setNumElem(int numElem) {
		this.numElem = numElem;
	}
	
	/**
	 * Comrpobar si te un tipus de terreny
	 * @param terreny
	 * @return - llista 
	 */
	public LlistaPlantacions llistaAmbTipusTerreny (String terreny) {
		LlistaPlantacions llistaAux = new LlistaPlantacions();
		
		for (int i = 0; i < numElem; i++) {
			if (llista[i].hay_tipusTerreny(terreny) ) {
				llistaAux.afegir(llista[i]);
			}
		}
		
		return llistaAux;
	}
	
	/**
	 * Eliminar un nom de partida
	 * @param nom
	 * @return - true o false
	 */
	public boolean eliminar (String nom) {
		boolean eliminat = false;
		
		for (int i = 0; i < numElem; i++) {
			if (llista[i].getNomPartida().equals(nom)) {
				for (int j = i; j < numElem-1; j++) {
					llista[j] = llista[j+1];
				}
				llista[numElem-1] = null;
				numElem--;
				eliminat = true;
			}	
		}
		
		return eliminat;
	}
	
	@Override
	public String toString() {
		String aux;
		if (numElem == 0) {
			aux = "No hi ha cap Plantacio.";
		}
		else {
			aux = "-------------------------------------Llista de Plantacions-------------------------------------\n";
			for (int i = 0; i < numElem; i++) {
					aux = aux +"\n"+ ++i + "- " +llista[--i].toString();
			}
		}
		return aux;
	}
	
	/**
	 * Retorna el CO2 màxim de totes les plantacions
	 * @return CO2 màxim
	 * @author Jose Luis
	 */
	public double getMaxCO2() {
		double maxCO2 = 0;
		for (int i = 0; i < numElem; i++) {
			maxCO2 = Math.max(maxCO2, llista[i].getMaxCO2());
		}
		return maxCO2;
	}
	
	/**
	 * Retorna la plantació iessima
	 * @param i
	 * @return plantació iessima
	 * @author Jose Luis
	 */
	public Plantacions get(int i) {
		return (i < numElem && i > 0) ? llista[i] : null;
	}
	
	/**
	 * Retorna la plantació a partir del seu nom
	 * @param nom nom de la plantació a retornar
	 * @return plantació amb el nom donat
	 * @author Jose Luis
	 */
	public Plantacions get(String nom) {
		for (int i = 0; i < numElem; i++) {
			if (llista[i].getNomPartida().equals(nom)) {
				return llista[i];
			}
		}
		return null;
	}
	
	/**
	 * Retorna la absorció de la plantació iessima en un any
	 * @param i
	 * @param any
	 * @return absorció de la plantació iessima en un any
	 */
	public double getAbsorcio(int i, int any) {
		return llista[i].getAbsorcioTotal(any);
	}
	
	/**
	 * Retorna la absorció total en un any
	 * @param any
	 * @return absorció total en un any
	 */
	public double getAbsorcioTotal(int any) {
		double absorcioTotal = 0;
		for (int i = 0; i < numElem; i++) {
			absorcioTotal += llista[i].getAbsorcioTotal(any);
		}
		return absorcioTotal;
	}
	
	/**
	 * Retorna el any més gran que influeix en la absorció de CO2 de les plantes de la plantació
	 * @return any màxim en que hi ha un canvi en la cuantitat de CO2 que absorbeix una planta
	 */
	public int getMaxAny() {
		// TODO Implementar getMaxAny() para canviar el maximo del JSlider en la Interfaz Grafica
		return 0;
	}
	
}
