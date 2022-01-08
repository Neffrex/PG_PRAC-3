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
