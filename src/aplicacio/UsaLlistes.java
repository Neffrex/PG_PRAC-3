/**
 * Autor: Jialiang Chen, Jose Luis Pueyo
*/
package aplicacio;

import plantacions.*;
import java.io.*;
import llistes.*;
import java.util.Scanner;

import javax.swing.JFileChooser;

public class UsaLlistes {

	public static void main(String[] args) {
		LlistaPlantes llistaPlantes = new LlistaPlantes(50);
		LlistaPlantacions llistaPlantacions = new LlistaPlantacions(5);
		LlistaTerrenys llistaTerrenys = new LlistaTerrenys(2);
		comprobarLlista(llistaPlantes);
		comprobarLlista(llistaTerrenys);
		comprobarLlista(llistaPlantacions);
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
		llista.afegirPlanta(new Arbustos("Arbusto Draculorius", 500, 1000));
		llista.afegirPlanta(new Arbres("Arbre de Nadal", new int[] { 200, 400, 600 }, new int[] { 1, 3 }));
		llista.afegirPlanta(
				new Arbres("Pino Draculorius", new int[] { 50, 200, 600, 100 }, new int[] { 100, 120, 150 }));
		llista.afegirPlanta(new Arbustos("Planta Callejera", 10, 20));
		System.out.println(llista.toString());
	}

	public LlistaPlantes carregaLlistaPlantes(File aux) throws FileNotFoundException{
		Scanner sc = new Scanner(aux);
		String buffer;
		int mida = count_lines(aux), llistaAbsorcio[], llistaInterval[];
		LlistaPlantes llista = new LlistaPlantes(mida);
		llistaAbsorcio = new int[mida];
		llistaInterval = new int[mida];
		while (sc.hasNextLine()) {
			buffer = sc.nextLine();
			String[] llistaParametres = buffer.split(";");
			if (llistaParametres[0].equalsIgnoreCase("arbre")) {
				if (llistaParametres.length % 2 == 0) {
					for (int i = 0; i < (llistaParametres.length - 2) / 2; i++) {
						llistaAbsorcio[i] = Integer.parseInt(llistaParametres[i + 2]);
						llistaInterval[i] = Integer.parseInt(llistaParametres[i + 3]);
					}
					llista.afegirPlanta(new Arbres(llistaParametres[1], llistaInterval, llistaAbsorcio));
				} else {
					sc.close();
					return null;
				}
			} else {
				llista.afegirPlanta(new Arbustos(llistaParametres[0], Integer.parseInt(llistaParametres[1]),
						Integer.parseInt(llistaParametres[2])));
			}
		}
		sc.close();
		return llista;
	}

	public LlistaPlantacions carregaLlistaPlantacions(File aux) throws FileNotFoundException {
		Scanner sc = new Scanner(aux);
		String buffer;
		int mida=count_lines(aux);
		LlistaPlantacions llista = new LlistaPlantacions(mida);
		LlistaRodals llistaRodals = new LlistaRodals(mida);
		while (sc.hasNextLine()) {
			buffer = sc.nextLine();
			String[] llistaParametres = buffer.split(";");
			if (llistaParametres.length %2 ==0) {
				for (int i=0; i<(llistaParametres.length-2)/2; i++) {
					llistaRodals.afegir(new Rodal(llistaParametres[i+2], Float.parseFloat(llistaParametres[i+3])));
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
		seleccionador.changeToParentDirectory();
		if (seleccionador.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
			archivo = seleccionador.getSelectedFile();
		return archivo;
	}

	public int count_lines(File file) throws FileNotFoundException {
		Scanner sc = new Scanner(file);
		int lines = (int) sc.findAll("[\n]").count();
		sc.close();
		return lines;
	}
}
