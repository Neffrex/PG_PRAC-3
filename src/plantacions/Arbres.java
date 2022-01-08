/**
 * @author Jose Luis Pueyo
 */
package plantacions;

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
		//if (absorcions.length != intervals.length) return false;
		
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
	
	/**
	 * Retorna si tots els elements d'un vector segueixen un ordre.
	 * @param a Vector que conté els elements a determinar la ordenació
	 * @param high <code>true</code> si l'ordre es de menor a major, <code>false</code> en cas contrari 
	 * @return <code>true</code> si tots els elements estàn en ordre o <code>fals</code> si no ho estàn
	 */
	private static boolean isOrdered(int[] a, boolean high) {
		if (high) {
			for (int i = 1; i < a.length; i++) {
				if (a[i-1] > a[i]) return false;
			}
		} else {
			for (int i = 1; i < a.length; i++) {
				if (a[i-1] < a[i]) return false;
			}
		}
		
		return true;
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
		StringBuilder sb = new StringBuilder("Arbre:\nEdad");
		
		int i;
		for (i = 0; i < absorcions.length-1; i++){
			sb.append(String.format("[%d, %d) -> %,.2f CO2\n", intervalsAbsorcions[i], intervalsAbsorcions[i+1], absorcions[i]));
		}
		sb.append(String.format("[%d, inf) -> %,.2f CO2", intervalsAbsorcions[i], absorcions[i]));
		
		return sb.toString();
	}

	
	
}
