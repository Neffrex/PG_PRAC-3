package plantacions;

/**
 * Classe que especifica la implementacio dun Rodal
 * @author Marc Fonseca Curto
 *
 */
public class Rodal {
	private String tipusTerreny;
    private double superficie;
    
    /**
     * Constructor dun Rodal
     * @param tipusTerreny
     * @param superficie
     */
    public Rodal (String tipusTerreny, float superficie){
        this.tipusTerreny = tipusTerreny;
        this.superficie = superficie;
    }

    /**
     * Getter tipusTerreny
     * @return tipusTerreny
     */
	public String getTipusTerreny() {
		return tipusTerreny;
	}

	/**
	 * Setter tipusTerreny
	 * @param tipusTerreny
	 */
	public void setTipusTerreny(String tipusTerreny) {
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
    
    
}
