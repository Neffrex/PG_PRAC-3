/**
 * Autor: Jialiang Chen, Jose Luis Pueyo
*/
package plantacions;

public abstract class Plantes {
	protected String nomCientific;
	protected boolean tipus;
	
	public static final boolean ARBUSTICA=false, ARBOREA=true;
	
	public Plantes (String nomCientific, boolean tipus) {
		this.nomCientific=nomCientific;
		this.tipus=tipus;
	}
	
	public String formatToString() {
		return null;
	}
	
	public abstract double getAbsorcio(int any);
	
	public abstract double getMaxCO2();
	
	public String getNomCientific() {
		return this.nomCientific;
	}
	
	public boolean getTipus() {
		return this.tipus;
	}
}
