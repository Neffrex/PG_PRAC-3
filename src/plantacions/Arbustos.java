/**
 * @author Jialiang Chen
 * @author Jose Luis Pueyo
 */
package plantacions;

public class Arbustos extends Plantes{
	private int absorcio, edatMaxima;
	public Arbustos(String nomCientific, int absorcio, int edatMaxima) {
		super(nomCientific, Plantes.ARBUSTICA);
		this.absorcio=absorcio;
		this.edatMaxima=edatMaxima;
	}
	
	public int getAbsorcio() {
		return this.absorcio;
	}

	public int getEdatMaxima() {
		return edatMaxima;
	}

	public void setEdatMaxima(int edatMaxima) {
		this.edatMaxima = edatMaxima;
	}

	public void setAbsorcio(int absorcio) {
		this.absorcio = absorcio;
	}
	
	@Override
	public String formatToString() {
		return "Arbust;" + nomCientific + ';' + absorcio + ';' + edatMaxima;
	}
	
	@Override
	public String toString() {
		return "Arbustos [absorcio("+this.getNomCientific()+")=" + absorcio + ", edatMaxima=" + edatMaxima + "]";
	}
	
}
