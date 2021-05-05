package application.model;

public class PrestazioneSanitaria {

	public PrestazioneSanitaria() {
		super();
		// TODO Auto-generated constructor stub
	}

	private int codicePrestazioneSanitaria;
	private String nomePrestazione,dipendenza, refAmbulatorio;
	private int costo;


	public PrestazioneSanitaria(String string, int int1) {
		this.nomePrestazione=string;
		this.costo=int1;
	}

	public int getCosto() {
		return costo;
	}

	public void setCosto(int costo) {
		this.costo = costo;
	}

	public int getCodicePrestazioneSanitaria() {
		return codicePrestazioneSanitaria;
	}

	public void setCodicePrestazioneSanitaria(int codicePrestazioneSanitaria) {
		this.codicePrestazioneSanitaria = codicePrestazioneSanitaria;
	}

	public String getDipendenza() {
		return dipendenza;
	}

	public void setDipendenza(String dipendenza) {
		this.dipendenza = dipendenza;
	}

	public String getNomePrestazione() {
		return nomePrestazione;
	}

	public void setNomePrestazione(String nomePrestazione) {
		this.nomePrestazione = nomePrestazione;
	}

}
