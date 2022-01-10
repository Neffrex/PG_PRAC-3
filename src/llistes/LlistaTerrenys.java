package llistes;

import plantacions.Terrenys;

/**
 * 
 * @author Joel Lacambra
 *
 */
public class LlistaTerrenys  {
	
	private Terrenys[] llista;
	private int numElem;
	
	/**
	 * Constructor
	 */
	public LlistaTerrenys() {
		llista = new Terrenys[20];
		numElem = 0;
	}
	
	/**
	 * Afegir un terreny
	 * @param terreny
	 */
	public void afegir (Terrenys terreny) {
		if (numElem < llista.length) {
			llista[numElem] = terreny;
			numElem++;
		}
	}
	
	/**
	 * Getter
	 * @param i
	 * @return - terreny especific
	 */
	public Terrenys getPos (int i) {
		return llista[i];
	}
	
	/**
	 * Getter
	 * @return - num de elements
	 */
	public int getNumElem() {
		return numElem;
	}
	
	public String toString() {
		String aux;
		if ( numElem == 0) {
			aux = "No hi ha cap terreny a la llista";
		}
		else {
			aux = "----------------Informacio dels terrenys----------------\n";
			for (int i=0; i < numElem; i++) {
				aux = aux + "\n" + ++i + " - " + llista[--i].toString();
			}
		}
		return aux;
	}
	
}
