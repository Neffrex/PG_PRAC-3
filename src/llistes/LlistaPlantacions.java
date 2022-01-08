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
}
