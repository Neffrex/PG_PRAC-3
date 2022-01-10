package plantacions;

import llistes.*;

/**
 * Classe que especifica la implementacio duna Plantacions
 * @author Marc Fonseca Curto
 *
 */
public class Plantacions {
	private String nomPartida;
    private int anyPlantacio;
    private LlistaRodals rodals;
    
    /**
     * Contrusctor de Plantacions
     * @param nomPartida 
     * @param anyPlantacio
     * @param rodals
     */
    public Plantacions (String nomPartida, int anyPlantacio, LlistaRodals rodals){
        this.nomPartida = nomPartida;
        this.anyPlantacio = anyPlantacio;
        this.rodals = rodals;
       /* for (int i = 0; i < tipusTerreny.length; i++) {
        	rodals.afegir(new Rodal(tipusTerreny[i], superficie[i]));
        }*/
    }

    /**
     * Getter
     * @return - nom de partida
     */
	public String getNomPartida() {
		return nomPartida;
	}

	/**
	 * Setter
	 * @param nomPartida
	 */
	public void setNomPartida(String nomPartida) {
		this.nomPartida = nomPartida;
	}
	
	/**
	 * Getter
	 * @return - any de plantacio
	 */
	public int getAnyPlantacio() {
		return anyPlantacio;
	}

	/**
	 * Setter
	 * @param anyPlantacio
	 */
	public void setAnyPlantacio(int anyPlantacio) {
		this.anyPlantacio = anyPlantacio;
	}
	
	/**
	 * Getter
	 * @return - numero de rodals
	 */ 
	public LlistaRodals getRodals() {
		return rodals;
	}

	/**
	 * Setter
	 * @param rodals
	 */
	public void setRodals(LlistaRodals rodals) {
		this.rodals = rodals;
	}

	/**
	 * Comprobar si exsteix un tipus de terreny
	 * @param terreny
	 * @return - true o false
	 */
	public boolean hay_tipusTerreny (String terreny) {
		boolean aux = false;
		
		for (int i = 0; i < rodals.getNumElem() && aux == false; i++) {
			if (rodals.getRodal(i).getTipusTerreny().equals(terreny)) {
				aux = true;
			}
		}
		
		return aux;
	}

	@Override
	public String toString() {
		String aux ="";
		
		aux = "nomPartida = " + nomPartida + ", anyPlantacio = " + anyPlantacio;
		aux += "\t\t"+rodals.toString();
		
		
		return aux;
	}

	/**
	 * Retorna el rodal iessim 
	 * @param i
	 * @return rodal iessim
	 * @author Jose Luis
	 */
	public Rodal getRodal(int i) {
		return rodals.getRodal(i);
	}
	
	
	/**
	 * Retorna el CO2 màxim de la plantacio
	 * @return CO2 màxim
	 * @author Jose Luis
	 */
	public double getMaxCO2() {
		double maxCO2 = 0;
		for (int i = 0; i < rodals.getNumElem(); i++) {
			maxCO2 = Math.max(maxCO2, rodals.getRodal(i).getMaxCO2());
		}
		return maxCO2;
	}
	
	/**
	 * Retorna la absorció total de la plantació en un any
	 * @param any
	 * @return absorció total de la plantació en un any
	 * @author Jose Luis
	 */
	public double getAbsorcioTotal(int any) {
		int absorcioTotal = 0;
		for (int i = 0; i < rodals.getNumElem(); i++) {
			absorcioTotal += rodals.getRodal(i).getAbsorcioTotal(any);
		}	
		return absorcioTotal;
	}
	

}
