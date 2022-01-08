/** 
 * Classe Terrenys
 * 
 * @author Joel lacambra Chen
 * @version 1.0
 * 
 */
package plantacions;

import java.util.Arrays;
import java.io.*;

public class Terrenys implements Serializable {
	private static final long serialVersionUID = 1;
	
	private String nomTerreny;
	private String[] plantes;
	private int[] numPlantes;
	
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
	
	public String getNomTerreny() {
		return nomTerreny;
	}
	
	public void setNomTerreny(String nomTerreny) {
		this.nomTerreny = nomTerreny;
	}
	
	public String[] getPlantes() {
		return plantes;
	}
	
	public String getIessimaPlanta(int i) {
		return plantes[i];
	}
	
	public void setIessimaPlanta(String planta, int i) {
		plantes[i] = planta;
	}
	
	public int[] getNumPlantes() {
		return numPlantes;
	}
	
	public int getIessimNumPlanta(int i) {
		return numPlantes[i];
	}
	
	public void setIessimNumPlanta(int numPlanta, int i) {
		numPlantes[i] = numPlanta;
	}
	
	@Override
	public String toString() {
		return "nomTerreny = " + nomTerreny + ", plantes = " + Arrays.toString(plantes) + "\n\t\t\t\tnumPlantes = "
				+ Arrays.toString(numPlantes);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}