// Marc: 3, 4, 5, 9, 10, 11, 13
package aplicacio;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import llistes.*;
import plantacions.*;

public class UsaLlistes {

	static Scanner teclat=new Scanner(System.in);
	
	public static void main(String[] args) throws FileNotFoundException {
		int opcio;
		int any;
		
		System.out.println("En quin any estem?");
		any = Integer.parseInt(teclat.nextLine());
		
		do  {
			mostraMenu();
			opcio = Integer.parseInt(teclat.nextLine());
			switch (opcio) {
			case 1:
				opcio1();
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
		
		
	}
	
	public static void mostraMenu() {
		System.out.println("\n\nOpcions del menu:");
		System.out.println("\n\t1.  Carregar les dades dels fitxers");
		System.out.println("\t2.  Llistar les dades de tots els tipus de terreny.");
		System.out.println("\t3.  Llistar les dades de totes les plantacions");
		System.out.println("\t4.  Llistar les dades de les plantacions que tenen algun rodal dâ€™un tipus de terreny");
		System.out.println("\t5.  Donada una plantaciÃ³, mostrar quantes unitats de cada esprcie sâ€™hi ha plantat");
		System.out.println("\t6.  Llistar les dades de totes les especies");
		System.out.println("\t7.  Donada una especie i una edat, mostrar la quantitat de CO2 que permet absorbir");
		System.out.println("\t8.  Afegir una nova especie de planta");
		System.out.println("\t9.  Esborrar les dades dâ€™una plantacio");
		System.out.println("\t10. Modificar lâ€™any de plantacio dâ€™una plantacio");
		System.out.println("\t11. Modificar les dades dâ€™un rodal dâ€™una plantacio");
		System.out.println("\t12. Modificar lâ€™any en que ens trobem");
		System.out.println("\t13. Mostrar la quantitat de CO2 que permet absorbir cada rodal dâ€™una plantacio en lâ€™any actual ");
		System.out.println("\t14. Mostrar la quantitat de CO2 que permet absorbir el conjunt dâ€™unitats plantades dâ€™una especie en l'any actual");
		System.out.println("\t15. Sortir");
		System.out.print("\n\t\t\tIndica opcio:\n");
	}
	
	// 1. Carregar les dades dels fitxers
	public static void opcio1() {	

	}
	
	// 2. Llistar les dades de tots els tipus de terreny.
	public static void opcio2() {
		
	}
	
	// 3. Llistar les dades de totes les plantacions
	public static void opcio3() {
		
	}
	
	// 4. Llistar les dades de les plantacions que tenen algun rodal dun tipus de terreny
	public static void opcio4() {
		
	}
	
	// 5. Donada una plantacio, mostrar quantes unitats de cada especie shi ha plantat
	public static void opcio5() {
		
	}
	
	// 6. Llistar les dades de totes les especies.
	public static void opcio6() {
	
	}
	
	// 7. Donada una especie i una edat, mostrar la quantitat de CO2 que permet absorbir
	public static void opcio7() {
		
	}
	
	// 8. Afegir una nova especie de planta
	public static void opcio8() {
		
	}
	
	// 9. Esborrar les dades dâ€™una plantacio
	public static void opcio9() {
		
	}
	
	// 10. Modificar lany de plantacio duna plantacio
	public static void opcio10() {
		
	}
	
	// 11. Modificar les dades dun rodal duna plantacio
	public static void opcio11() {
		
	}
	
	// 12. Modificar lany en que ens trobem (valor que hem introduit a linici pero que volem
	// modificar per als calculs seguents)

	public static void opcio12() {
		
	}
	
	// 13. Mostrar la quantitat de CO2 que permet absorbir cada rodal duna plantacio en lany actual
	// (indicat a lentrar al programa). Indicarem el nom de la plantacio per teclat
	public static void opcio13() {
		
	}
	
	// 14. Mostrar la quantitat de CO2 que permet absorbir el conjunt dunitats plantades duna especie
	// (entre totes les plantacions que tenim guardades) en lâ€™any actual. Indicarem el nom de
	// lâ€™especie per teclat.
	public static void opcio14() {
		
	}
	
	// 15. Sortir. Permetre sortir guardant la informaciÃ³ de les classes als fitxers o no.
	public static void opcio15() {
		
	}

}