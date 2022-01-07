/**
 * Autor: Jialiang Chen, Jose Luis Pueyo
*/
package aplicacio;
import plantacions.*;

import java.io.FileNotFoundException;

import llistes.*;

public class UsaLlistes {

	public static void main(String[] args) {
		
		new InterficieGrafica();
		
		LlistaPlantes llistaPlantes = new LlistaPlantes(50);
		comprobarLlista(llistaPlantes);
		
		LlistaPlantacions llistaPlantacions = new LlistaPlantacions(5);
		comprobarLlista(llistaPlantacions);
		
		LlistaTerrenys llistaTerrenys = new LlistaTerrenys(2);
		comprobarLlista(llistaTerrenys);
		
	}
	
	public static void comprobarLlista(LlistaPlantacions llista) {
		String [] terreny = new String[3];
		terreny[0] = "CalcariSolana";
		terreny[1] = "CalcariObaga";
		terreny[2] = "CalcariSolana";
		float [] superficie = new float[3];
		superficie[0] = 32;
		LlistaRodals llistaRodals = new LlistaRodals(5);
		llistaRodals.afegir(new Rodal (terreny[0], superficie[0]));
		llistaRodals.afegir(new Rodal (terreny[1], 23));	
		llista.afegir(new Plantacions ("Marc", 2021, llistaRodals));
		System.out.println(llista.toString());
	}

	public static void comprobarLlista(LlistaTerrenys llista) {
		String nomTerreny1 = "CalcariSolana";
		String[] nomPlantes1 = {"Pinus sylvestris", "Pinus nigra", "Fraxinus excelsior", "Acer pseudoplatanus", "Populus nigra"};
		int[] numPlantes1 = {150, 130, 220, 210, 190};
		
		String nomTerreny2 = "CalcariObaga";
		String[] nomPlantes2 = {"Pinus sylvestris", "Corylus Avellana", "Spartium junceum", "Quercus ilex", "Betula alba"};
		int[] numPlantes2 = {190, 200, 150, 200, 160};
		
		llista.afegir(new Terrenys(nomTerreny1, nomPlantes1, numPlantes1));
		llista.afegir(new Terrenys(nomTerreny2, nomPlantes2, numPlantes2));
		System.out.println(llista.toString());
	}

	public static void comprobarLlista(LlistaPlantes llista) {
		// Arbustos
		llista.afegirPlanta(new Arbustos("Arbusto Draculorius", 500, 1000));
		llista.afegirPlanta(new Arbustos("Planta Callejera", 10, 20));
		
		// Arbres
		llista.afegirPlanta(new Arbres("Arbre de Nadal", new int[] {5, 8}, new double[] {235, 390}));
		llista.afegirPlanta(new Arbres("Pino Draculorius", new int[] {4,7,12}, new double[] {190, 324, 420}));
		llista.afegirPlanta(new Arbres("Pino Draculorius", new int[] {5,8,15}, new double[] {230, 300, 390}));
		llista.afegirPlanta(new Arbres("Pino Draculorius", new int[] {5,10}, new double[] {190, 270}));
		llista.afegirPlanta(new Arbres("Pino Draculorius", new int[] {5,10}, new double[] {180, 250}));
		llista.afegirPlanta(new Arbres("Pino Draculorius", new int[] {4,7}, new double[] {190, 340}));
		llista.afegirPlanta(new Arbres("Pino Draculorius", new int[] {4,6,10}, new double[] {210.28, 374.19, 401.94}));
		
		//Guardar Fichero
		try {
			if(llista.guardarEnFitxer())
				System.out.println("");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		System.out.println(llista.toString());
	}
	
}
 