package llistes;

import plantacions.*;

/**
 * Classe que especifica la implementacio duna llista de Rodals
 * @author Marc Fonseca Curto
 *
 */
public class LlistaRodals {
	private Rodal[] llistaRodals;
	private int numElem;
	
	/**
	 * Constructor llistaRodals
	 * @param num - tamany de la llista 
	 */
	public LlistaRodals(int num) {
		llistaRodals = new Rodal[num];
		numElem = 0;
	}
	
	/**
	 * Afegir un nou rodal
	 * @param rodal
	 */
	public void afegir (Rodal rodal) {
		if (numElem < llistaRodals.length) {
			llistaRodals[numElem] = rodal;
			numElem++;
		}	
	}

	/**
	 * Getter llistaRodal[i]
	 * @param i
	 * @return llistaRodal[i]
	 */
	public Rodal getRodal(int i) {
		return llistaRodals[i];
	}

	/**
	 * Getter numElem
	 * @return numElem
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

	@Override
	public String toString() {
		String aux = "";
		if (numElem == 0) {
			aux = "No hi ha cap Rodal.";
		}
		else {
			for (int i = 0; i < numElem; i++) {
					aux += "\n\t\t Rodal "+ i + ": " +llistaRodals[i].toString();
			}
		}
		return aux;
	}
	
}
