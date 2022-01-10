package aplicacio;

import javax.swing.JFileChooser;
import plantacions.*;
import llistes.*;
import java.util.Scanner;
import java.io.*;

public class UsaLlistes {

	static Scanner teclat=new Scanner(System.in);
	
	static LlistaPlantacions llistaPlantacions = new LlistaPlantacions();
	static LlistaTerrenys llistaTerrenys = new LlistaTerrenys();
	static LlistaPlantes llistaPlantes = new LlistaPlantes();
	static int any;
	
	public static void main(String[] args) throws IOException {
		
		int opcio;
		
		
		
		do {
			System.out.println("En quin any estem? (Entre 1953 i 2022)");
			any = Integer.parseInt(teclat.nextLine());
		} while (any < 1952 || any > 2023);	
	
		
		do  {
			mostraMenu();
			opcio = Integer.parseInt(teclat.nextLine());
			switch (opcio) {
			case 1:
				System.out.println("Llista carregada!!!");
				System.out.println("Carregant la llista de Plantes.");
				llistaPlantes = carregaLlistaPlantes(new File("./src/Plantas.csv")); 
				System.out.println("Escull els fitxers per cada llista:");
				System.out.println("Carregant la llista de Terrenys.");
				//llistaTerrenys = carregaLlistaTerrenys(new File("./src/Terrenys.ser"));
				llistaTerrenys = afegirDades("src/prueba.txt");
				System.out.println("Llista carregada!!!");
				System.out.println("Carregant la llista de Plantacions.");
				llistaPlantacions = carregaLlistaPlantacions(new File("./src/Plantaciones.csv"));
				System.out.println("Llista carregada!!!");
				break;
			case 2:
				opcio2();
				break;
			case 3:
				opcio3();
				break;
			case 4:
				opcio4();
				break;
			case 5:
				opcio5();
				break;
			case 6:
				opcio6();
				break;
			case 7: 
				opcio7();
				break;
			case 8: 
				opcio8();
				break;
			case 9: 
				opcio9();
				break;
			case 10: 
				opcio10();
				break;
			case 11: 
				opcio11();
				break;
			case 12: 
				opcio12();
				break;
			case 13: 
				opcio13();
				break;
			case 14: 
				opcio14();
				break;
			case 15: 
				opcio15();
				break;
			default:
				System.out.println("Aquesta no es una opcio disponible");
				break;
			} 
			
		} while (opcio != 15);
		
		new InterficieGrafica(llistaPlantacions, llistaPlantes);
		
	}
	
	/**
	 * Carregar la llista de plantacions
	 * @param aux
	 * @return - llista carregada amb les plantacions y les seves dades
	 * @throws FileNotFoundException
	 */
	public static LlistaPlantacions carregaLlistaPlantacions(File aux) throws FileNotFoundException {
		Scanner sc = new Scanner(aux);
		String buffer;
		LlistaPlantacions llista = new LlistaPlantacions();
		while (sc.hasNextLine()) {
			buffer = sc.nextLine();
			String[] llistaParametres = buffer.split(";");			
			LlistaRodals llistaRodals = new LlistaRodals((llistaParametres.length -2)/2);
			if (llistaParametres.length % 2 == 0) {
				for (int i=0; i<(llistaParametres.length-2); i+=2) { 
					
					llistaRodals.afegir(new Rodal(llistaTerrenys.get(llistaParametres[i+3]), Float.parseFloat(llistaParametres[i+2].replace(',' , '.'))));
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
	
	/**
	 * Carregar la llista de plantes
	 * @param aux
	 * @return - llista amb les plantes y les seves dades
	 * @throws FileNotFoundException
	 */
	public static LlistaPlantes carregaLlistaPlantes(File aux) throws FileNotFoundException {
		Scanner sc = new Scanner(aux);
		String buffer;
		int llistaInterval[];
		double llistaAbsorcio[];
		LlistaPlantes llista = new LlistaPlantes();
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
	
	/**
	 * Carrega la llista de terrenys
	 * @param aux
	 * @return - llista amb terrenys y les seves dades
	 * @throws IOException
	 */
	public static LlistaTerrenys carregaLlistaTerrenys (File aux) throws IOException {
		LlistaTerrenys llista = new LlistaTerrenys();
		ObjectInputStream inputFile;
		boolean llegit = false;
		
		try {
			inputFile = new	 ObjectInputStream(new FileInputStream(aux));
			
			while (!llegit) {
				llista.afegir((Terrenys) inputFile.readObject());
			}
						
		}
		catch (EOFException e) {
			llegit = true;
		}
		catch (IOException e){
			System.out.println("El arxiu de entrada introduit no existeix.");
		}
		catch (ClassNotFoundException e) {
			System.out.println("La classe no ha estat trobada.");
		}
		
		catch (ClassCastException e) {
			System.out.println("El format de arxiu no es correcte.");
		}
		
		return llista;
	}		

	/**
	 * Funcio per guardar la llista de plantacions
	 * @param llista
	 */
	public static void guardaLlistaPlantacions(){
		try {																										
			File fit = new File("Plantacions23.csv");			
			if (!fit.exists()) {						
				fit.createNewFile();
			}
			PrintWriter pw = new PrintWriter(fit);		
		
			for (int i=0; i<llistaPlantacions.getNumElem(); i++) {	
				pw.printf("%s;%d", llistaPlantacions.getLlista()[i].getNomPartida(), llistaPlantacions.getLlista()[i].getAnyPlantacio());
				for (int j = 0; j < llistaPlantacions.getLlista()[i].getRodals().getNumElem(); j++) {
					pw.printf(";%.2f;%s", llistaPlantacions.getLlista()[i].getRodals().getRodal(j).getSuperficie(), llistaPlantacions.getLlista()[i].getRodals().getRodal(j).getTipusTerreny());
				}
				pw.printf("%n");
			}
			pw.close();
		}
		catch (IOException e) {
			e.getStackTrace();
		}
	}

	/**
	 * Funcio per guardar la llista de terrenys
	 * @param llista
	 */
	public static void guardaLlistaTerrenys () {
		ObjectOutputStream outputFile;

		try {
			outputFile = new ObjectOutputStream(new FileOutputStream("Terrenys.ser"));
			
			for (int i = 0; i < llistaTerrenys.getNumElem(); i++) {
				outputFile.writeObject(llistaTerrenys.getPos(i));
			}
			outputFile.close();
		}
		catch (IOException e) {
			System.out.println("Error arxiu de sortida");
		}
	}
	
	
	/**
	 * 
	 * @param aux
	 * @return
	 * @throws FileNotFoundException
	 */
	public static LlistaTerrenys afegirDades (String aux) throws FileNotFoundException {

        LlistaTerrenys llista = new LlistaTerrenys();

        Scanner f = new Scanner (new File (aux));
        String[] delimitador1, delimitador2, delimitador3;
        int[] array;
        Plantes[] plantes;
        
        while (f.hasNextLine()) {
            delimitador1 = f.nextLine().split(";");
            delimitador2 = delimitador1[1].split(",");
            delimitador3 = delimitador1[2].split(",");
            array = new int[delimitador3.length];
            for(int i=0; i<delimitador3.length; i++) {
                array[i] = Integer.parseInt(delimitador3[i]);
            }
            plantes = new Plantes[delimitador2.length];
            for (int i = 0; i < delimitador2.length; i++) {
            	plantes[i] = llistaPlantes.getPlanta(delimitador2[i]);
            }
            
            llista.afegir(new Terrenys(delimitador1[0], plantes, array));

        }

        return llista;
    }
    
	
	/**
	 * Funcio per escullir un arxiu
	 * @return
	 */
	public static File escoger_archivo() {
		JFileChooser seleccionador = new JFileChooser();
		File archivo = null;
		seleccionador.setCurrentDirectory(new File ("."));
		seleccionador.setFileSelectionMode(JFileChooser.FILES_ONLY);
		if (seleccionador.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
			archivo = seleccionador.getSelectedFile();
		return archivo;
	}

	public static void mostraMenu() {
		System.out.println("\n\nOpcions del menu:");
		System.out.println("\n\t1.  Carregar les dades dels fitxers");
		System.out.println("\t2.  Llistar les dades de tots els tipus de terreny.");
		System.out.println("\t3.  Llistar les dades de totes les plantacions");
		System.out.println("\t4.  Llistar les dades de les plantacions que tenen algun rodal dun tipus de terreny");
		System.out.println("\t5.  Donada una plantacio, mostrar quantes unitats de cada esprcie shi ha plantat");
		System.out.println("\t6.  Llistar les dades de totes les especies");
		System.out.println("\t7.  Donada una especie i una edat, mostrar la quantitat de CO2 que permet absorbir");
		System.out.println("\t8.  Afegir una nova especie de planta");
		System.out.println("\t9.  Esborrar les dades duna plantacio");
		System.out.println("\t10. Modificar lany de plantacio duna plantacio");
		System.out.println("\t11. Modificar les dades dun rodal duna plantacio");
		System.out.println("\t12. Modificar lany en que ens trobem");
		System.out.println("\t13. Mostrar la quantitat de CO2 que permet absorbir cada rodal duna plantacio en lany actual ");
		System.out.println("\t14. Mostrar la quantitat de CO2 que permet absorbir el conjunt dunitats plantades dâuna especie en lany actual");
		System.out.println("\t15. Sortir");
		System.out.print("\n\t\t\tIndica opcio:\n");
	}
	
	// 2. Llistar les dades de tots els tipus de terreny.
	public static void opcio2() {
		System.out.println(llistaTerrenys.toString());
	}
	
	// 3. Llistar les dades de totes les plantacions
	public static void opcio3() {
		System.out.println(llistaPlantacions.toString());
	}
	
	// 4. Llistar les dades de les plantacions que tenen algun rodal dun tipus de terreny
	public static void opcio4() {
		System.out.println("Quin tipus de terreny tinteressa?");
		String terreny = teclat.nextLine();
		System.out.println(llistaPlantacions.llistaAmbTipusTerreny(terreny).toString());
	}
	
	// 5. Donada una plantacio, mostrar quantes unitats de cada especie shi ha plantat
	public static void opcio5() {
		
		
		
		boolean trobat = false;
		int i = 0;
		
		System.out.println("Quina plantacio vols?");
		String nom = teclat.nextLine();
		
		for (; i < llistaPlantacions.getNumElem() && trobat == false; i++) {
			if (llistaPlantacions.getLlista()[i].getNomPartida().equalsIgnoreCase(nom)) {
				trobat = true;
			}
		}
		
		if (trobat == false) {
			System.out.println("No existeix aquesta plantacio");
		}
		else {
			System.out.println("No sabem calcular tot aixo, perdo :(.");
		}
		
	}
	
	// 6. Llistar les dades de totes les especies.
	public static void opcio6() {
		System.out.println(llistaPlantes.toString());
	}
	
	// 7. Donada una especie i una edat, mostrar la quantitat de CO2 que permet absorbir
	public static void opcio7() {
		System.out.println("Introdueix el nom cient�fic:");
		String nomCientific = teclat.nextLine();
		
		try {
			System.out.println("Introdueix l'edat de la planta:");
			int edad = Integer.parseInt(teclat.nextLine());
			
			Plantes planta = llistaPlantes.getPlanta(nomCientific);
			
			if (planta!=null) {
				System.out.printf("La planta pot absorbir: %f CO2", planta.getAbsorcio(edad));
			}
			else {
				System.out.println("No existeix cap planta amb aquest nom en la llista.");
			}
		} catch (NumberFormatException e) {
			System.out.println("Edad invalida");
		}
	}
	
	// 8. Afegir una nova especie de planta
	public static void opcio8() {
		int tipusPlanta=-1;
		
		while (!((tipusPlanta==0) || (tipusPlanta==1))) {
			System.out.println("Introdueix si la nova especie es Arbustica o Arborea (Introdueix 0 per Arbustica i 1 per Arborea :");
			tipusPlanta=Integer.parseInt(teclat.nextLine());
		}
		
		System.out.println("Introdueix el nom de la nova especie:");
		String nom=teclat.nextLine();
		
		int edatMaxima;
		if (tipusPlanta==0) {
			int absorcio;
			System.out.println("Introdueix l'absorcio de l'arbust:");
			absorcio=Integer.parseInt(teclat.nextLine());
			System.out.println("Introdeix l'edat maxima:");
			edatMaxima=Integer.parseInt(teclat.nextLine());
			llistaPlantes.afegirPlanta(new Arbustos(nom, absorcio, edatMaxima));
			System.out.println("Arbust afegit!");
		}
		else {
			int n_intervals, absorcio[], intervals[];
			System.out.println("Quants intervals te aquest arbre?");
			n_intervals=Integer.parseInt(teclat.nextLine());
			absorcio = new int[n_intervals];
			intervals = new int[n_intervals];
			for (int i=0; i<n_intervals;i++) {
				System.out.println("Introdueix l'absorcio "+i+": ");
				absorcio[i]=Integer.parseInt(teclat.nextLine());
				System.out.println("Introdueix l'interval "+i+": ");
				intervals[i]=Integer.parseInt(teclat.nextLine());
			}
			//llista.afegirPlanta(new Arbres(nom, absorcio, intervals));
			System.out.println("Arbre afegit!");
		}
	}
	
	// 9. Esborrar les dades duna plantacio
	public static void opcio9() {
		System.out.println("Quina plantacio vols?");
		String nom = teclat.nextLine();
		if (llistaPlantacions.eliminar(nom)) {
			System.out.println("Plantacio eliminada");
		}
		else {
			System.out.println("No existeix aquesta plantacio");
		}
		
	}
	
	// 10. Modificar lany de plantacio duna plantacio
	public static void opcio10() {
		boolean trobat = false;
		int i = 0;
		
		System.out.println("Quina plantacio vols?");
		String nom = teclat.nextLine();
		
		for (; i < llistaPlantacions.getNumElem() && trobat == false; i++) {
			if (llistaPlantacions.getLlista()[i].getNomPartida().equalsIgnoreCase(nom)) {
				trobat = true;
			}
		}
		
		if (trobat == false) {
			System.out.println("No existeix aquesta plantacio");
		}
		else {
			System.out.println("A quin any vols cambiar? (Entre 1953 i 2022)");
			int any = Integer.parseInt(teclat.nextLine());
			if (llistaPlantacions.getLlista()[--i].getAnyPlantacio() == any) {
				System.out.println("Lany de la plantacio ja es aquest");
			}
			else if (any < 1952 || any > 2023){
				System.out.println("Aquest any no es valid");
			}
			else {
				llistaPlantacions.getLlista()[i].setAnyPlantacio(any);
				System.out.println("Any modificat correctament");
			}
		}
	}
	
	// 11. Modificar les dades dun rodal duna plantacio
	public static void opcio11() {
		
		boolean trobat = false;
		int i = 0;
		
		System.out.println("De quina plantacio vols modificar algun rodal?");
		String plantacio = teclat.nextLine();
		 
		for (; i < llistaPlantacions.getNumElem() && trobat == false; i++) {
			if (llistaPlantacions.getLlista()[i].getNomPartida() == plantacio) {
				trobat = true;
			}
		}
		
		if (trobat == false) {
			System.out.println("No existeix aquesta plantacio");
		}
		else {
			System.out.println("Quin rodal vols modificar? (numero)");
			int j = Integer.parseInt(teclat.nextLine());
			
			if (llistaPlantacions.getLlista()[i].getRodals().getNumElem()-1 > j) {
				System.out.println("Aquest rodal no existeix");
			}
			else {
				System.out.println("Aquest rodal te: " +llistaPlantacions.getLlista()[i].getRodals().getRodal(j).toString());
				String opcio = "";
				
				do {
					System.out.println("Vols modificar el tipus de terreny? (0-Si  1-No)");
					opcio = teclat.nextLine();
					if (!(opcio.equalsIgnoreCase("Si") || opcio.equalsIgnoreCase("No"))) {
						System.out.println("No es una opcio valida");
					}
				}while (!(opcio.equalsIgnoreCase("Si") || opcio.equalsIgnoreCase("No")));
				
				if (opcio.equalsIgnoreCase("Si")) {
					System.out.println("Quin es el nou tipus de terreny?");
					String terreny = teclat.nextLine();
					
					if (llistaPlantacions.get(i).getRodal(i).getTipusTerreny().getNomTerreny().equalsIgnoreCase(terreny)) {
						System.out.println("El tipus de terreny ja es aquest");
					}
					else {
						Terrenys tipusTerreny = llistaTerrenys.get(terreny);
						if (tipusTerreny != null) {
							llistaPlantacions.getLlista()[i].getRodals().getRodal(j).setTipusTerreny(tipusTerreny);
							System.out.println("Tipus de terreny modificat correctament");
						} else {
							System.out.println("Aquest tipus de terreny no existeix");
						}
					}
				}	
				
				System.out.println("Vols modificar la superficie? (0-Si  1-No)");
				opcio = teclat.nextLine();
				
				do {
					System.out.println("Vols modificar el tipus de terreny? (0-Si  1-No)");
					opcio = teclat.nextLine();
					if (!(opcio.equalsIgnoreCase("Si") || opcio.equalsIgnoreCase("No"))) {
						System.out.println("No es una opcio valida");
					}
				}while (!(opcio.equalsIgnoreCase("Si") || opcio.equalsIgnoreCase("No")));
				
				if (opcio.equalsIgnoreCase("Si")) {
					System.out.println("Quina es la nova superficie? (Els decimals amb '.')");
					double superficie = Double.parseDouble(teclat.nextLine());
					
					if (llistaPlantacions.getLlista()[i].getRodals().getRodal(j).getSuperficie() == superficie) {
						System.out.println("La superficie ja es aquesta");
					}
					else {
						llistaPlantacions.getLlista()[i].getRodals().getRodal(j).setSuperficie(superficie);
						System.out.println("Suerficie modificada correctament");
					}
				}
			}
		}
		
	}
	
	
	
	// 12. Modificar lany en que ens trobem (valor que hem introduit a linici pero que volem
	// modificar per als calculs seguents)

	public static void opcio12() {
		do {
			System.out.println("Introdueix a quin any vols cambiar (1953 - 2022):");
			any = Integer.parseInt(teclat.nextLine());
		} while (any < 1952 || any > 2023);	
	} 
	
	
	
	// 13. Mostrar la quantitat de CO2 que permet absorbir cada rodal duna plantacio en lany actual
	// (indicat a lentrar al programa). Indicarem el nom de la plantacio per teclat
	public static void opcio13() {
		
		Plantacions plantacio = llistaPlantacions.get(teclat.nextLine());
		if (plantacio != null) {
			for (int i = 0; i < plantacio.getRodals().getNumElem(); i++) {
				System.out.printf("Absorci� rodal %d = %d\n", i, plantacio.getRodals().getRodal(i).getAbsorcioTotal(plantacio.getAnyPlantacio()-any)); 
			}
		}
		
	}
	
	/*		CODI ANTERIOR AMB RETORN DE STRINGS EN LES CLASSES RODALS I TERRENYS, NO TENIR EN COMPTE
	String nom, nomPlanta;
	Plantacions plantacions=null;
	LlistaRodals llistaRodals=null;
	int sumaTotalAbsorcio=0;
	System.out.println("Indica el nom de la plantaciÃ³:");
	nom = teclat.nextLine();
	for (int i=0; i<llistaPlantacio.getNumElem();i++) {
		if (llistaPlantacio.getLlista()[i].getNomPartida().equalsIgnoreCase(nom)) {
			plantacions=llistaPlantacio.getLlista()[i];
			llistaRodals=plantacions.getRodals();
			System.out.println("Planta trobada!!!"+llistaPlantacio.getLlista()[i].getNomPartida());
		}
	}
	if (plantacions == null) {
		System.out.println("No existeix aquesta plantacio.");
		return;
	}
	for (int i=0;i<llistaRodals.getNumElem();i++) {
		for (int j=0; j<llistaTerrenys.getNumElem();j++) {
			if (llistaRodals.getRodal(i).getTipusTerreny().equalsIgnoreCase(llistaTerrenys.getPos(j).getNomTerreny())) {
				for (int x=0; x<llistaTerrenys.getPos(j).getPlantes().length;x++) {
					nomPlanta=llistaTerrenys.getPos(j).getPlantes()[x];
					sumaTotalAbsorcio=sumaTotalAbsorcio + (llistaTerrenys.getPos(j).getNumPlantes()[x] * opcio7(llistaPlantes, nomPlanta, any-plantacions.getAnyPlantacio()));
				}
			}
		}
	}
	System.out.println("La quantitat de CO2 que permet absorbir en aquesta plantaciÃ³ Ã©s de "+sumaTotalAbsorcio);
	*/
	
	
	
	
	// 14. Mostrar la quantitat de CO2 que permet absorbir el conjunt dunitats plantades duna especie
	// (entre totes les plantacions que tenim guardades) en lany actual. Indicarem el nom de
	// lespecie per teclat.
	public static void opcio14() {
		System.out.println("Introdueix el nom de la especie: ");
		String nomEspecie = teclat.nextLine();
		double absorcioTotal = 0;
		
		for(int i = 0; i < llistaPlantacions.getNumElem(); i++) {
			absorcioTotal += llistaPlantacions.getAbsorcio(i, nomEspecie, any);
		}
		
		System.out.printf("Absorci� que permet absorbir la especie %s = %f\n", nomEspecie, absorcioTotal);
	}
	
	/*	CODI ANTIC AMB LES CLASSES RODALS I TERRENYS DESACTUALITZAT
		String especie=null;
		int sumaTotalAbsorcio=0;
		System.out.println("Introdueix el nom de l'especie:");
		especie = teclat.nextLine();
		LlistaRodals llistaRodals=null;
		for (int i=0; i<llistaPlantacions.getNumElem();i++) {	//NOS DA PLANTACIONS
			for (int j=0; j<llistaPlantacions.getLlista()[i].getRodals().getNumElem();j++) {	//NOS DA LISTA RODALES
				llistaRodals=llistaPlantacions.getLlista()[i].getRodals();
				for (int k=0;k<llistaTerrenys.getNumElem();k++) {				//NOS DA TERRENYS
					if (llistaRodals.getRodal(j).getTipusTerreny().equalsIgnoreCase(llistaTerrenys.getPos(k).getNomTerreny())) {					
						for (int x=0;x<llistaTerrenys.getPos(k).getNumPlantes().length;x++) {
							if (especie.equalsIgnoreCase(llistaTerrenys.getPos(k).getPlantes()[x])) {
								sumaTotalAbsorcio+=(llistaTerrenys.getPos(k).getNumPlantes()[x] * opcio7(llistaPlantes, especie, any - llistaPlantacions.getLlista()[i].getAnyPlantacio()));
							}
						}
					}
				}
			}
		}
		System.out.println("La quantitat de CO2 absorbeix aquesta especie en les plantacions es de:"+sumaTotalAbsorcio);
	}
	*/
	
	
	
	// 15. Sortir. Permetre sortir guardant la informacio de les classes als fitxers o no.
	public static void opcio15() {
		String opcio;
		do {
			System.out.println("Vols guardar els fitxers modificats? (0-Si  1-No)");
			opcio = teclat.nextLine();
			if (!(opcio.equalsIgnoreCase("Si") || opcio.equalsIgnoreCase("No"))) {
				System.out.println("No es una opcio valida");
			}
		}while (!(opcio.equalsIgnoreCase("Si") || opcio.equalsIgnoreCase("No")));
		if (opcio.equals("Si")){
			System.out.println("Les dades es guardaran en els fitxers originals.");
			guardaLlistaPlantacions();
			guardaLlistaTerrenys();
			llistaPlantes.guardarEnFitxer();
		}
		else{
			System.out.println("Has sortit del programa.");
		}
	}

}

