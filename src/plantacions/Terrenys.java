package plantacions;

import java.util.Arrays;
import java.io.*;

/**
 * 
 * @author Joel Lacambra
 *
 */
public class Terrenys implements Serializable {
	private static final long serialVersionUID = 1;
	
	private String nomTerreny;
	private String[] plantes;
	private int[] numPlantes;
	
	/**
	 * Constructor de terreny
	 * @param nomTerreny
	 * @param plantes
	 * @param numPlantes
	 */
	public Terrenys (String nomTerreny, String[] plantes, int[] numPlantes) {
		this.nomTerreny = nomTerreny;
		this.plantes = new String[plantes.length];
		this.numPlantes = new int[numPlantes.length];
		
		for(int i=0; i<plantes.length; i++) {
			this.plantes[i] = plantes[i];
		}
		for(int i=0; i<numPlantes.length; i++) {
			this.numPlantes[i] = numPlantes[i];
		}
	}
	
	/**
	 * Getter
	 * @return - nom del terreny
	 */
	public String getNomTerreny() {
		return nomTerreny;
	}
	
	/**
	 * Setter
	 * @param nomTerreny
	 */
	public void setNomTerreny(String nomTerreny) {
		this.nomTerreny = nomTerreny;
	}
	
	/**
	 * Getter
	 * @return - totes les plantes
	 */
	public String[] getPlantes() {
		return plantes;
	}
	
	/**
	 *  Getter
	 * @param i
	 * @return - planta especifica
	 */
	public String getIessimaPlanta(int i) {
		return plantes[i];
	}
	
	/**
	 * Setter
	 * @param planta
	 * @param i
	 */
	public void setIessimaPlanta(String planta, int i) {
		plantes[i] = planta;
	}
	
	/**
	 * Getter
	 * @return - quantitat de plantes
	 */
	public int[] getNumPlantes() {
		return numPlantes;
	}
	
	/**
	 * Getter
	 * @param i
	 * @return - numero de planta especifica
	 */
	public int getIessimNumPlanta(int i) {
		return numPlantes[i];
	}
	/**
	 * Setter
	 * @param numPlanta
	 * @param i
	 */
	public void setIessimNumPlanta(int numPlanta, int i) {
		numPlantes[i] = numPlanta;
	}
	
	@Override
	public String toString() {
		return "nomTerreny = " + nomTerreny + ", plantes = " + Arrays.toString(plantes) + "\n\t\t\t\tnumPlantes = "
				+ Arrays.toString(numPlantes);
	}
}
