package plantacions;

/**
 * Classe que especifica la implementacio dun Rodal
 * @author Marc Fonseca Curto
 *
 */
public class Rodal {
	private Terrenys tipusTerreny;
    private double superficie;
    
    /**
     * Constructor dun Rodal
     * @param tipusTerreny
     * @param superficie
     */
    public Rodal (Terrenys tipusTerreny, float superficie){
        this.tipusTerreny = tipusTerreny;
        this.superficie = superficie;
    }

    /**
     * Getter tipusTerreny
     * @return tipusTerreny
     */
	public Terrenys getTipusTerreny() {
		return tipusTerreny;
	}

	/**
	 * Setter tipusTerreny
	 * @param tipusTerreny
	 */
	public void setTipusTerreny(Terrenys tipusTerreny) {
		if (tipusTerreny != null)
			this.tipusTerreny = tipusTerreny;
	}

	/**
	 * Getter superficie
	 * @return superficie
	 */
	public double getSuperficie() {
		return superficie;
	}

	/**
	 * Setter superficie
	 * @param superficie
	 */
	public void setSuperficie(double superficie) {
		this.superficie = superficie;
	}
	
	@Override
	public String toString() {
		return "tipusTerreny = " + tipusTerreny + ", superficie = " + superficie;
		
	}
	
	/**
	 * Retorna el CO2 màxim que pot absorbir el rodal
	 * @return CO2 màxim
	 */
	public double getMaxCO2() {
		double maxCO2 = 0;
		Plantes planta;
		if (tipusTerreny != null) {
			for (int i = 0; i < tipusTerreny.getPlantes().length; i++) {
				planta = tipusTerreny.getIessimaPlanta(i);
				maxCO2 = Math.max(maxCO2, getUnitatsPlanta(i)*planta.getMaxCO2());
			}
		}
		return maxCO2;
	}
	
	/**
	 * Retorna quantes unitats d'un tipus de planta es poden plantar en el rodal
	 * @param i
	 * @return numero de plantes que es poden plantar del tipus de la planta iessima 
	 * @author Jose Luis
	 */
	public int getUnitatsPlanta(int i) {
		int unitatsPerHra = tipusTerreny.getIessimNumPlanta(i);
		return (int) Math.floor(unitatsPerHra*superficie);
	}
	
	/**
	 * Retorna quantes unitats d'un tipus de planta es poden plantar en el rodal
	 * @param i
	 * @return numero de plantes que es poden plantar del tipus de la planta iessima 
	 * @author Jose Luis
	 */
	public int getUnitatsPlanta(Plantes planta) {
		int unitatsPerHra = tipusTerreny.getNumPlanta(planta);
		return (unitatsPerHra != -1) ? (int) Math.floor(unitatsPerHra*superficie) : 0;
	}
	
	public int getUnitats(boolean tipus) {
		return tipusTerreny.getUnitats(tipus);
	}
	
	/**
	 * Retorna la absorció total del rodal en un any relatiu al any de la plantació
	 * e.g. si l'any de plantació es x llavors si any es y es retornará la absorció en l'any x+y
	 * @param any
	 * @return absorció total del rodal en un any
	 * @author Jose Luis
	 */
	public double getAbsorcioTotal(int any) {
		if (any < 0) return 0;
		double absorcioTotal = 0;
		
		for (int i = 0; i < tipusTerreny.getPlantes().length; i++) {
			absorcioTotal += getUnitatsPlanta(i) * tipusTerreny.getIessimaPlanta(i).getAbsorcio(any);
		}
		return absorcioTotal;
	}

	/**
	 * Retorna la absorció total d'una especie del rodal en un any relatiu al any de la plantació
	 * e.g. si l'any de plantació es x llavors si any es y es retornará la absorció en l'any x+y
	 * @param nomEspecie
	 * @param any
	 * @return absorció total d'una especie del rodal en un any
	 * @author Jose Luis
	 */
	public double getAbsorcioTotal(String nomEspecie, int any) {
		if (any < 0) return 0;
		Plantes planta = tipusTerreny.getPlanta(nomEspecie);
		return (planta != null) ? getUnitatsPlanta(planta) * planta.getAbsorcio(any) : 0;
	}
	
    
}
