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
		for (int i = 0; i < tipusTerreny.getPlantes().length; i++) {
			planta = tipusTerreny.getIessimaPlanta(i);
			maxCO2 = Math.max(maxCO2, getUnitatsPlanta(i)*planta.getMaxCO2());
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
	 * Retorna la absorció total del rodal en un any
	 * @param any
	 * @return absorció total del rodal en un any
	 * @author Jose Luis
	 */
	public double getAbsorcioTotal(int any) {
		double absorcioTotal = 0;
		for (int i = 0; i < tipusTerreny.getPlantes().length; i++) {
			absorcioTotal = Math.max(absorcioTotal, getUnitatsPlanta(i) * tipusTerreny.getIessimaPlanta(i).getAbsorcio(any));
		}
		return absorcioTotal;
	}
	
    
}
