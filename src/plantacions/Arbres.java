/**
 * @author Jialiang Chen
 * @author Jose Luis Pueyo
 */
package plantacions;

import java.util.Arrays;

import util.Util;

public class Arbres extends Plantes {
	private int[] intervalsAbsorcions;
	private double[] absorcions;

	public Arbres(String nomCientific) {
		super(nomCientific, Plantes.ARBOREA);
	}
	
	public Arbres(String nomCientific, int[] intervals, double[] absorcions) {
		super(nomCientific, Plantes.ARBOREA);
		setAbsorcions(intervals, absorcions);
	}
	
	/**
	 * Retorna l'absorci� de l'arbre en un any
	 * @param any Any de l'arbre 
	 * @return La absorci� de l'arbre a l'<code>any</code> donat
	 */
	public double getAbsorcio(int any) {
		return absorcions[getIndexIntervalInf(any)];
	}

	/**
	 * Canv�a la absorci� de l'interval al que pertanyi un any
	 * @param absorcio Nova absorci� de l'interval
	 * @param any Any pertinent a l'interval a canviar
	 */
	public void setAbsorcio(double absorcio, int any) {
		this.absorcions[getIndexIntervalInf(any)] = absorcio;
	}
	
	/**
	 * Canv�a totes les absorcions y intervals 
	 * @param intervals Vector amb els intervals de cada absorci�
	 * @param absorcions Vector amb les absorcions
	 * @implNote Es dona per suposat que els intervals s�n contigus, per tant el vector ser� del tipus {x_1, ..., x_n} 
	 * on els intervals ser�n {x_1, x_2}, {x_2, x_3}, ... {x_n-1, x_n}, {x_n, infinit}.</br>
	 * Si <code>intervals</code> no est� ordenat es considerar� un error y retornar� false sense efectuar cap canvi.
	 * @return <code>true</code> si s'ha efectuat el canvi amb �xit o <code>false</code> si ha hagut un error.
	 */
	public boolean setAbsorcions(int[] intervals, double[] absorcions) {
		if (absorcions.length != intervals.length || !Util.isOrdered(intervals, true)) return false;
		
		this.absorcions = absorcions;
		this.intervalsAbsorcions = intervals;
		
		return true;
	}
	
	/**
	 * Retorna la posici� del limit inferior de l'interval al que pertany un any
	 * @param any Any pertinent al interval a tractar
	 * @return Posici� del limit inferior del interval al que pertany <code>any</code>
	 */
	private int getIndexIntervalInf(int any) {
		int pos = intervalsAbsorcions.length-1;
		
		for (int i = -1; i < intervalsAbsorcions.length-1; i++) {
			if(intervalsAbsorcions[i+1] > any) {
				pos = i;
			}
		}
		
		return pos;
	}
	
	@Override
	public String formatToString() {
		StringBuilder sb = new StringBuilder("Arbre;");
		sb.append(nomCientific + ';');
		for (int i = 0; i < intervalsAbsorcions.length; i++) {
			sb.append(absorcions[i] + ';' + intervalsAbsorcions[i] + ';');
		}
		return sb.toString();
		
	}
	
	@Override
	public String toString() {
		return "Arbres [absorcio=" + Arrays.toString(absorcions) + ", rangEdats=" + Arrays.toString(intervalsAbsorcions) + "]";
	}

	
	
}
