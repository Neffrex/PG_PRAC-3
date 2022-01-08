package aplicacio;


import javax.swing.JFileChooser;
import plantacions.*;
import llistes.*;
import java.util.Scanner;
import java.io.*;


public class UsaLlistes {

	static Scanner teclat=new Scanner(System.in);
	
	public static void main(String[] args) throws IOException {
		int opcio;
		int any;
		
		LlistaPlantacions llistaPlantacions = new LlistaPlantacions();
		LlistaTerrenys llistaTerrenys = new LlistaTerrenys();
		LlistaPlantes llistaPlantes = new LlistaPlantes();
		do {
			System.out.println("En quin any estem? (Entre 1953 i 2022)");
			any = Integer.parseInt(teclat.nextLine());
		} while (any < 1952 || any > 2023);	
	
		
		do  {
			mostraMenu();
			opcio = Integer.parseInt(teclat.nextLine());
			switch (opcio) {
			case 1:
				System.out.println("Escull els fitxers per cada llista:");
				System.out.println("Carregant la llista de Plantacions.");
				llistaPlantacions = carregaLlistaPlantacions(escoger_archivo());
				System.out.println("Llista carregada!!!");
				System.out.println("Carregant la llista de Terrenys.");
				llistaTerrenys = carregaLlistaTerrenys(escoger_archivo());
				System.out.println("Llista carregada!!!");
				System.out.println("Carregant la llista de Plantes.");
				llistaPlantes = carregaLlistaPlantes(escoger_archivo()); 
				System.out.println("Llista carregada!!!");
				break;
			case 2:
				opcio2(llistaTerrenys);
				break;
			case 3:
				opcio3(llistaPlantacions);
				break;
			case 4:
				opcio4(llistaPlantacions);
				break;
			case 5:
				opcio5(llistaPlantacions,llistaTerrenys);
				break;
			case 6:
				opcio6(llistaPlantes);
				break;
			case 7: 
				opcio7(llistaPlantes);
				break;
			case 8: 
				opcio8(llistaPlantes);
				break;
			case 9: 
				opcio9(llistaPlantacions);
				break;
			case 10: 
				opcio10(llistaPlantacions);
				break;
			case 11: 
				opcio11(llistaPlantacions);
				break;
			case 12: 
				opcio12(any);
				break;
			case 13: 
				opcio13();
				break;
			case 14: 
				opcio14();
				break;
			case 15: 
				//opcio15(llistaPlantacions, llistaTerrenys, llistaPlantes);
				break;
			default:
				System.out.println("Aquesta no es una opcio disponible");
				break;
			} 
			
		} while (opcio != 15);
		
		
	}
	
	public static LlistaPlantacions carregaLlistaPlantacions(File aux) throws FileNotFoundException {
		Scanner sc = new Scanner(aux);
		String buffer;
		LlistaPlantacions llista = new LlistaPlantacions();
		while (sc.hasNextLine()) {
			buffer = sc.nextLine();
			String[] llistaParametres = buffer.split(";");			LlistaRodals llistaRodals = new LlistaRodals((llistaParametres.length -2)/2);
			if (llistaParametres.length % 2 == 0) {
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

	public static void guardaLlistaTerrenys (LlistaTerrenys llista) {
		ObjectOutputStream outputFile;

		try {
			outputFile = new ObjectOutputStream(new FileOutputStream("Terrenys.ser"));
			
			for (int i = 0; i < llista.getNumElem(); i++) {
				outputFile.writeObject(llista.getPos(i));
			}
			outputFile.close();
		}
		catch (IOException e) {
			System.out.println("Error arxiu de sortida");
		}
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
	
	/* 1. Carregar les dades dels fitxers
	public static void opcio1(LlistaPlantacions llistaPlantacions, LlistaTerrenys llistaTerrenys, LlistaPlantes llistaPlantes) throws FileNotFoundException{	
		System.out.println("Escull els fitxers per cada llista:");
		System.out.println("PLANTACIONS");
		llistaPlantacions = carregaLlistaPlantacions(escoger_archivo());
		System.out.println("TERRENYS");
		llistaTerrenys = carregaLlistaTerrenys(escoger_archivo());
		System.out.println("PLANTES");
		llistaPlantes = carregaLlistaPlantes(escoger_archivo()); 	
	}*/
	
	// 2. Llistar les dades de tots els tipus de terreny.
	public static void opcio2(LlistaTerrenys llista) {
		System.out.println(llista.toString());
	}
	
	// 3. Llistar les dades de totes les plantacions
	public static void opcio3(LlistaPlantacions llista) {
		System.out.println(llista.toString());
	}
	
	// 4. Llistar les dades de les plantacions que tenen algun rodal dun tipus de terreny
	public static void opcio4(LlistaPlantacions llista) {
		System.out.println("Quin tipus de terreny tinteressa?");
		String terreny = teclat.nextLine();
		System.out.println(llista.llistaAmbTipusTerreny(terreny).toString());
	}
	
	// 5. Donada una plantacio, mostrar quantes unitats de cada especie shi ha plantat
	public static void opcio5(LlistaPlantacions llistaPlantacions, LlistaTerrenys llistaTerrenys) {
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
	public static void opcio6(LlistaPlantes llista) {
		System.out.println(llista.toString());
	}
	
	// 7. Donada una especie i una edat, mostrar la quantitat de CO2 que permet absorbir
	public static void opcio7(LlistaPlantes llista) {
		System.out.println("Introdueix el nombre cientÃ­fic:");
		String nomCientific = teclat.nextLine();
		
		System.out.println("Introdueix l'edat de la planta:");
		int any = teclat.nextInt();
		
		if (llista.getPlanta(nomCientific)!=null) {
			if (llista.getPlanta(nomCientific).getTipus()==false) {
				Arbustos arbust = (Arbustos) llista.getPlanta(nomCientific);
				System.out.println("Absorcio de l'arbust "+arbust.getNomCientific()+"Ã©s:"+arbust.getAbsorcio());
			}
			else {
				Arbres arbre = (Arbres) llista.getPlanta(nomCientific);
				System.out.println("Absorcio de l'arbre "+arbre.getNomCientific()+" es: "+arbre.getAbsorcio(any));
			}
		}
		else {
			System.out.println("No existeix cap planta amb aquest nom en la llista.");
		}
	}
	
	// 8. Afegir una nova especie de planta
	public static void opcio8(LlistaPlantes llista) {
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
			llista.afegirPlanta(new Arbustos(nom, absorcio, edatMaxima));
			System.out.println("Arbust afegit!");
		}else {
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
	public static void opcio9(LlistaPlantacions llista) {
		System.out.println("Quina plantacio vols?");
		String nom = teclat.nextLine();
		if (llista.eliminar(nom)) {
			System.out.println("Plantacio eliminada");
		}
		else {
			System.out.println("No existeix aquesta plantacio");
		}
		
	}
	
	// 10. Modificar lany de plantacio duna plantacio
	public static void opcio10(LlistaPlantacions llista) {
		boolean trobat = false;
		int i = 0;
		
		System.out.println("Quina plantacio vols?");
		String nom = teclat.nextLine();
		
		for (; i < llista.getNumElem() && trobat == false; i++) {
			if (llista.getLlista()[i].getNomPartida().equalsIgnoreCase(nom)) {
				trobat = true;
			}
		}
		
		if (trobat == false) {
			System.out.println("No existeix aquesta plantacio");
		}
		else {
			System.out.println("A quin any vols cambiar? (Entre 1953 i 2022)");
			int any = Integer.parseInt(teclat.nextLine());
			if (llista.getLlista()[--i].getAnyPlantacio() == any) {
				System.out.println("Lany de la plantacio ja es aquest");
			}
			else if (any < 1952 || any > 2023){
				System.out.println("Aquest any no es valid");
			}
			else {
				llista.getLlista()[i].setAnyPlantacio(any);
				System.out.println("Any modificat correctament");
			}
		}
	}
	
	// 11. Modificar les dades dun rodal duna plantacio
	public static void opcio11(LlistaPlantacions llista) {
		boolean trobat = false;
		int i = 0;
		
		System.out.println("De quina plantacio vols modificar algun rodal?");
		String plantacio = teclat.nextLine();
		
		for (; i < llista.getNumElem() && trobat == false; i++) {
			if (llista.getLlista()[i].getNomPartida() == plantacio) {
				trobat = true;
			}
		}
		
		if (trobat == false) {
			System.out.println("No existeix aquesta plantacio");
		}
		else {
			System.out.println("Quin rodal vols modificar? (numero)");
			int j = Integer.parseInt(teclat.nextLine());
			
			if (llista.getLlista()[i].getRodals().getNumElem()-1 > j) {
				System.out.println("Aquest rodal no existeix");
			}
			else {
				System.out.println("Aquest rodal te: " +llista.getLlista()[i].getRodals().getLlistaRodals(j).toString());
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
					
					if (llista.getLlista()[i].getRodals().getLlistaRodals(j).getTipusTerreny().equalsIgnoreCase(terreny)) {
						System.out.println("El tipus de terreny ja es aquest");
					}
					else {
						llista.getLlista()[i].getRodals().getLlistaRodals(j).setTipusTerreny(terreny);
						System.out.println("Tipus de terreny modificat correctament");
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
					
					if (llista.getLlista()[i].getRodals().getLlistaRodals(j).getSuperficie() == superficie) {
						System.out.println("La superficie ja es aquesta");
					}
					else {
						llista.getLlista()[i].getRodals().getLlistaRodals(j).setSuperficie(superficie);;
						System.out.println("Suerficie modificada correctament");
					}
				}
			}
		}
	}
	
	// 12. Modificar lany en que ens trobem (valor que hem introduit a linici pero que volem
	// modificar per als calculs seguents)

	public static void opcio12(int any) {
		do {
			System.out.println("Introdueix a quin any vols cambiar (1953 - 2002):");
			any = Integer.parseInt(teclat.nextLine());
		} while (any < 1952 || any > 2023);	
	} 
	
	// 13. Mostrar la quantitat de CO2 que permet absorbir cada rodal duna plantacio en lany actual
	// (indicat a lentrar al programa). Indicarem el nom de la plantacio per teclat
	public static void opcio13() {
		
	}
	
	// 14. Mostrar la quantitat de CO2 que permet absorbir el conjunt dunitats plantades duna especie
	// (entre totes les plantacions que tenim guardades) en lany actual. Indicarem el nom de
	// lespecie per teclat.
	public static void opcio14() {
		
	}
	
	// 15. Sortir. Permetre sortir guardant la informacio de les classes als fitxers o no.
	public static void opcio15(LlistaPlantacions llistaPlantacions, LlistaTerrenys llistaTerrenys, LlistaPlantes llistaPlantes) {
		String opcio;
		do {
			System.out.println("Vols modificar el tipus de terreny? (0-Si  1-No)");
			opcio = teclat.nextLine();
			if (!(opcio.equalsIgnoreCase("Si") || opcio.equalsIgnoreCase("No"))) {
				System.out.println("No es una opcio valida");
			}
		}while (!(opcio.equalsIgnoreCase("Si") || opcio.equalsIgnoreCase("No")));
		if (teclat.nextLine().equals("Si")){
			System.out.println("Les dades es guardaran en els fitxers originals.");
			guardaLlistaTerrenys(llistaTerrenys);
			//guardaLlistaPlantacions(llistaPlantacions);
			//guardaLlistaPlantes(llistaPlantes);
		}
		else{
			System.out.println("Has sortit del programa.");
		}
	}

}

