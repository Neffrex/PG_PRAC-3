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
	private Plantes[] plantes;
	private int[] numPlantes;
	
	/**
	 * Constructor de terreny
	 * @param nomTerreny
	 * @param plantes
	 * @param numPlantes
	 */
	public Terrenys (String nomTerreny, Plantes[] plantes, int[] numPlantes) {
		this.nomTerreny = nomTerreny;
		this.plantes = plantes;
		this.numPlantes = numPlantes;
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
	public Plantes[] getPlantes() {
		return plantes;
	}
	
	/**
	 *  Getter
	 * @param i
	 * @return - planta especifica
	 */
	public Plantes getIessimaPlanta(int i) {
		return plantes[i];
	}
	
	/**
	 * Setter
	 * @param planta
	 * @param i
	 */
	public void setIessimaPlanta(Plantes planta, int i) {
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
	
	/**
	 * Retorna la absorció total del terreny en un any
	 * @param any
	 * @return absorció total del terreny en un any
	 * @author Jose Luis
	 */
	public double getAbsorcioTotal(int any) {
		for (int i = 0; i < plantes.length; i++) {
			plantes[i].getAbsorcio(any);
		}
		return 0;
	}
}
