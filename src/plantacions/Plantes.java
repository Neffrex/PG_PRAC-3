/**
 * Autor: Jialiang Chen, Jose Luis Pueyo
*/
package plantacions;

import util.Util;

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

	public String getNomCientific() {
		return this.nomCientific;
	}
	
	public boolean getTipus() {
		return this.tipus;
	}
	
}
