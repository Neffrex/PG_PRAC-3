/**
 * Autor: Jialiang Chen, Jose Luis Pueyo
*/
package aplicacio;

import plantacions.*;
import java.io.FileNotFoundException;
import java.io.*;
import llistes.*;
import java.util.Scanner;

import javax.swing.JFileChooser;

public class UsaLlistes {

	public static void main(String[] args) {
		
		LlistaPlantes llistaPlantes;
		LlistaPlantacions llistaPlantacions;
		try {
			llistaPlantes = carregaLlistaPlantes(escoger_archivo());
			llistaPlantacions = carregaLlistaPlantacions(escoger_archivo());
			System.out.println(llistaPlantes.toString());
			System.out.println(llistaPlantacions.toString());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void comprobarLlista(LlistaPlantacions llista) {
		String[] terreny = new String[3];
		terreny[0] = "CalcariSolana";
		terreny[1] = "CalcariObaga";
		terreny[2] = "CalcariSolana";
		float[] superficie = new float[3];
		superficie[0] = 32;
		LlistaRodals llistaRodals = new LlistaRodals(5);
		llistaRodals.afegir(new Rodal(terreny[0], superficie[0]));
		llistaRodals.afegir(new Rodal(terreny[1], 23));
		llista.afegir(new Plantacions("Marc", 2021, llistaRodals));
		System.out.println(llista.toString());
	}

	public static void comprobarLlista(LlistaTerrenys llista) {
		String nomTerreny1 = "CalcariSolana";
		String[] nomPlantes1 = { "Pinus sylvestris", "Pinus nigra", "Fraxinus excelsior", "Acer pseudoplatanus",
				"Populus nigra" };
		int[] numPlantes1 = { 150, 130, 220, 210, 190 };

		String nomTerreny2 = "CalcariObaga";
		String[] nomPlantes2 = { "Pinus sylvestris", "Corylus Avellana", "Spartium junceum", "Quercus ilex",
				"Betula alba" };
		int[] numPlantes2 = { 190, 200, 150, 200, 160 };

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

	public static LlistaPlantes carregaLlistaPlantes(File aux) throws FileNotFoundException{
		Scanner sc = new Scanner(aux);
		String buffer;
		int mida = count_lines(aux), llistaInterval[];
		double llistaAbsorcio[];
		LlistaPlantes llista = new LlistaPlantes(mida);
		while (sc.hasNextLine()) {
			buffer = sc.nextLine();
			String[] llistaParametres = buffer.split(";");
			llistaAbsorcio = new double[100];
			llistaInterval = new int[100];
			if (llistaParametres[0].equalsIgnoreCase("arbre")) {
				if (llistaParametres.length % 2 == 0) {
					for (int i = 0; i < ((llistaParametres.length) - 2) / 2; i++) {
						llistaAbsorcio[i] = Integer.parseInt(llistaParametres[i + 2]);
						llistaInterval[i] = Integer.parseInt(llistaParametres[i + 3]);
					}
					llista.afegirPlanta(new Arbres(llistaParametres[1], llistaInterval, llistaAbsorcio));
				} else {
					sc.close();
					return null;
				}
			} else {
				llista.afegirPlanta(new Arbustos(llistaParametres[1], Integer.parseInt(llistaParametres[2]),
						Integer.parseInt(llistaParametres[3])));
			}
		}
		sc.close();
		return llista;
	}

	public static LlistaPlantacions carregaLlistaPlantacions(File aux) throws FileNotFoundException {
		Scanner sc = new Scanner(aux);
		String buffer;
		int mida=count_lines(aux);
		LlistaPlantacions llista = new LlistaPlantacions(mida);
		int j=1;
		while (sc.hasNextLine()) {
			buffer = sc.nextLine();
			String[] llistaParametres = buffer.split(";");
			System.out.println(j+"  "+llistaParametres.length);
			j++;
			LlistaRodals llistaRodals = new LlistaRodals((llistaParametres.length -2)/2);
			if (llistaParametres.length % 2== 0) {
				for (int i=0; i<(llistaParametres.length-2); i+=2) { 
					llistaRodals.afegir(new Rodal(llistaParametres[i+3], Float.parseFloat(llistaParametres[i+2])));
				}
				llista.afegir(new Plantacions(llistaParametres[0], Integer.parseInt(llistaParametres[1]), llistaRodals));	
			}else {
				sc.close();
				return null;
			}			
		}
		sc.close();
		return llista;
	}

	public static File escoger_archivo() {
		JFileChooser seleccionador = new JFileChooser();
		File archivo = null;
		seleccionador.setCurrentDirectory(new File ("."));
		seleccionador.setFileSelectionMode(JFileChooser.FILES_ONLY);
		if (seleccionador.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
			archivo = seleccionador.getSelectedFile();
		return archivo;
	}

	public static int count_lines(File file) throws FileNotFoundException {
		Scanner sc = new Scanner(file);
		int lines = (int) sc.findAll("[\n]").count();
		sc.close();
		return lines;
	}

}
