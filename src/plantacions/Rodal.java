// Marc Fonseca
package plantacions;

public class Rodal {
	private String tipusTerreny;
    private double superficie;
    
    public Rodal (String tipusTerreny, float superficie){
        this.tipusTerreny = tipusTerreny;
        this.superficie = superficie;
    }

	public String getTipusTerreny() {
		return tipusTerreny;
	}

	public void setTipusTerreny(String tipusTerreny) {
		this.tipusTerreny = tipusTerreny;
	}

	public double getSuperficie() {
		return superficie;
	}

	public void setSuperficie(double superficie) {
		this.superficie = superficie;
	}

	@Override
	public String toString() {
		return "tipusTerreny = " + tipusTerreny + ", superficie = " + superficie;
		
	}
    
    
}