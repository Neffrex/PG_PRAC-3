/**
 * @author Jialiang Chen
 * @author Jose Luis Pueyo
 */
package plantacions;

public class Arbres extends Plantes{
	// TODO Canvi en el tipus de emmagatzematge de absorcio i rangEdats de forma que estiguin relacionades
	private int[] intervalsAbsorcions;
	private double[] absorcions;

	/**
	 * 
	 * @param nomCientific
	 */
	public Arbres(String nomCientific) {
		super(nomCientific, Plantes.ARBOREA);
	}
	
	/**
	 * 
	 * @param nomCientific
	 * @param intervals
	 * @param absorcions
	 */
	public Arbres(String nomCientific, int[] intervals, double[] absorcions) {
		super(nomCientific, Plantes.ARBOREA);
		setAbsorcions(intervals, absorcions);
	}
	
	/**
	 * Retorna l'absorció de l'arbre en un any
	 * @param any Any de l'arbre 
	 * @return La absorció de l'arbre a l'<code>any</code> donat
	 */
	public double getAbsorcio(int any) {
		return (any > intervalsAbsorcions[0]) ? absorcions[getIndexIntervalInf(any)] : 0;
	}
	
	/**
	 * Retorna la absorci� m�s gran que pot absorbir la planta
	 * @return absorci� m�xima
	 */
	public double getMaxCO2() {
		double maxCO2 = 0;
		for (int i = 0; i < intervalsAbsorcions.length; i++) {
			maxCO2 = Math.max(maxCO2, getAbsorcio(intervalsAbsorcions[i]));
		}
		return maxCO2;
	}

	/**
	 * Canvía la absorció de l'interval al que pertanyi un any
	 * @param absorcio Nova absorció de l'interval
	 * @param any Any pertinent a l'interval a canviar
	 * @return <code>true</code> si l'any perteneix a un interval y s'ha pogut actualitzar l'absorci� o
	 * <code>fals</code> en cas contrari.
	 */
	public boolean setAbsorcio(double absorcio, int any) {
		if (absorbeixCO2(any)) {
			this.absorcions[getIndexIntervalInf(any)] = absorcio;
			return true;
		}
		return false;
	}
	
	/**
	 * Canvía totes les absorcions y intervals 
	 * @param intervals Vector amb els intervals de cada absorció
	 * @param absorcions Vector amb les absorcions
	 * @implNote Es dona per suposat que els intervals són contigus, per tant el vector será del tipus {x_1, ..., x_n} 
	 * on els intervals serán {x_1, x_2}, {x_2, x_3}, ... {x_n-1, x_n}, {x_n, infinit}.</br>
	 * Si <code>intervals</code> no está ordenat es considerará un error y retornará false sense efectuar cap canvi.
	 * @return <code>true</code> si s'ha efectuat el canvi amb èxit o <code>false</code> si ha hagut un error.
	 */
	public boolean setAbsorcions(int[] intervals, double[] absorcions) {
		
		this.absorcions = absorcions;
		this.intervalsAbsorcions = intervals;
		
		return true;
	}
	
	/**
	 * Retorna la posici� del limit inferior de l'interval al que pertany un any
	 * @param any Any pertinent al interval a tractar
	 * @return Posici� del limit inferior del interval al que pertany <code>any</code> o -1 si no pertany a cap
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
	
	/**
	 * Retorna si l'arbre produeix CO2 en un any
	 * @param any l'any a veure si produeix CO2 l'arbre
	 * @return <code>true</code> si produeix CO2 en l'any donat o <code>fals</code> en cas contrari
	 */
	public boolean absorbeixCO2(int any) {
		return any > intervalsAbsorcions[0];
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
