package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Scanner;

import javax.swing.JFileChooser;

import llistes.LlistaTerrenys;
import plantacions.Terrenys;

public class Util {
	
	/**
	 * Cerca d'un valor en un vector mitjançant una cerca dicotómica/binaria
	 * @implNote La implementació del algoritme de cerca dicotómica es fa de forma iterativa
	 * @param n - valor a cercar
	 * @param a - vector on cercar
	 * @return posició del valor <b>n</b> en el vector <b>a</b> o -1 si no es troba el valor 
	 */
	public static int cerca_dicotomica(int n, int[] a) {
		int esquerra = 0, mig, dreta = a.length;
		
		do {
			mig = (esquerra + dreta) / 2;
			
			if (n < a[mig]) 
				dreta = mig;
			else 
				esquerra = mig;
		} while(a[mig] != n);
		
		return mig;
	}
	
	/**
	 * Retorna si tots els elements d'un vector segueixen un ordre.
	 * @param a Vector que conté els elements a determinar la ordenació
	 * @param high <code>true</code> si l'ordre es de menor a major, <code>false</code> en cas contrari 
	 * @return <code>true</code> si tots els elements estàn en ordre o <code>fals</code> si no ho estàn
	 */
	public static boolean isOrdered(int[] a, boolean high) {
		if (high) {
			for (int i = 1; i < a.length; i++) {
				if (a[i-1] > a[i]) return false;
			}
		} else {
			for (int i = 1; i < a.length; i++) {
				if (a[i-1] < a[i]) return false;
			}
		}
		
		return true;
	}
	
	public long count_lines(File file) throws FileNotFoundException {
		Scanner sc = new Scanner(file);
		long lines = sc.findAll("[\n]").count();
		sc.close();
		return lines;
	}
	
	public static File escogerArchivo() {
		JFileChooser seleccionador = new JFileChooser();
		File archivo = null;
		seleccionador.setCurrentDirectory(new File("."));
		seleccionador.setFileSelectionMode(JFileChooser.FILES_ONLY);
		if (seleccionador.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
			archivo = seleccionador.getSelectedFile();
		return archivo;
	}
	
	public static File escogerDirectorio() {
		JFileChooser seleccionador = new JFileChooser();
		File archivo = null;
		seleccionador.setCurrentDirectory(new File("."));
		seleccionador.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		if (seleccionador.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
			archivo = seleccionador.getSelectedFile();
		return archivo;
	}
	
	
}
