/**
 * @author Jialiang Chen
 * @author Jose Luis Pueyo
 */
package plantacions;

public class Arbustos extends Plantes{
	private int edatMaxima;
	private double absorcio;
	
	public Arbustos(String nomCientific, double absorcio, int edatMaxima) {
		super(nomCientific, Plantes.ARBUSTICA);
		this.absorcio=absorcio;
		this.edatMaxima=edatMaxima;
	}
	
	public double getAbsorcio(int edat) {
		if ((edat>5)&&(edat<edatMaxima)) {
			return this.absorcio;
		}else {
			return 0;
		}
	}

	public int getEdatMaxima() {
		return edatMaxima;
	}

	public void setEdatMaxima(int edatMaxima) {
		this.edatMaxima = edatMaxima;
	}

	public void setAbsorcio(double absorcio) {
		this.absorcio = absorcio;
	}
	
	@Override
	public String formatToString() {
		return "Arbust;" + nomCientific + ';' + absorcio + ';' + edatMaxima;
	}
	
	@Override
	public String toString() {
		return "Arbustos [absorcio=" + absorcio + ", edatMaxima=" + edatMaxima + "]";
	}

	@Override
	public double getMaxCO2() {
		return absorcio;
	}
	
}
