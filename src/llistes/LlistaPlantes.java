package llistes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import plantacions.Plantes;
import util.Util;

/**
 * Classe que implementa la interficie TADLlistaPlantes que especifica la implementació d'una llista de la classe Plantes
 * @author Jialiang Chen
 * @author Jose Luis Pueyo
 */
public class LlistaPlantes implements TADLlistaPlantes{
	private int nElem;
    private Plantes llistaPlantes[];
    
    public LlistaPlantes (int mida) {
        this.nElem=0;
        llistaPlantes= new Plantes[mida];
    }
    
    public boolean afegirPlanta(Plantes planta) {
    	if (nElem<llistaPlantes.length) {
    		llistaPlantes[nElem++]= planta;
    		return true;
    	}
    	return false;
    }
	
	@Override
	public boolean eliminarPlanta(Plantes planta) {
		return eliminar(getPosicio(planta));
	}

	private boolean eliminar(int pos){
		if (pos >= nElem || pos < 0) return false;
		
		nElem--;
		for (int i = 0; i < nElem; i++){
			llistaPlantes[i] = llistaPlantes[i+1];
		}
		return true;
	}

	@Override
	public int getNumElem() {
		return nElem;
	}

	@Override
	public boolean pertany(Plantes planta) {
		for (Plantes plantaLlista : llistaPlantes){
			if (plantaLlista.equals(planta)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public int getPosicio(Plantes planta) {
		int pos = -1;
		for(int i = 0; i<llistaPlantes.length; i++){
			if (llistaPlantes[i].equals(planta)){	
				pos = i;
				break;
			}
		}
		return pos;
	}

	@Override
	public Plantes getPlanta(String nomCientific){
		for (int i = 0; i < nElem; i++){
			if (llistaPlantes[i].getNomCientific().equals(nomCientific)){
				return llistaPlantes[i];
			}
		}
		return null;
	}

	public boolean guardarEnFitxer() throws FileNotFoundException {
		File directori = Util.escogerDirectorio();
		if (directori == null) return false;
		
		File archiu = new File(directori.getAbsolutePath()+"\\LlistaPlantes.csv");
		try {
			PrintWriter pw = new PrintWriter(archiu);
			pw.println(formatToString());
			pw.close();
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static LlistaPlantes carregarDeFitxer(File fitxer){
		
		try {
				
		} catch (Exception e) {
			
		}
		
		return null;
	}

	public String formatToString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < nElem; i++) {
			sb.append(llistaPlantes[i].formatToString()+'\n');
		}
		return sb.toString(); 
	}
	
	
	@Override
	public String toString(){
		String buffer = "Llista Plantes: \n";
		for (int i = 0; i < nElem; i++){
			buffer += i + "\t" + llistaPlantes[i].toString() + "\n";
		}
		return buffer;
	}
}
