package application.model;

public class Letto {

	private int codiceLetto;
	private boolean disponibilita;
	private int refStanza;

	public Letto() {
		// TODO Auto-generated constructor stub
	}


	public int getCodiceLetto() {
		return codiceLetto;
	}

	public void setCodiceLetto(int codiceLetto) {
		this.codiceLetto = codiceLetto;
	}

	public boolean isDisponibilita() {
		return disponibilita;
	}

	public void setDisponibilita(boolean disponibilita) {
		this.disponibilita = disponibilita;
	}

	public int getRef_Stanza() {
		return refStanza;
	}

	public void setRef_Stanza(int refStanza) {
		this.refStanza = refStanza;
	}
}
