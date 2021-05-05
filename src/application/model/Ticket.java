package application.model;

public class Ticket {

	private int ref_codicePrenotazione;
	private String ref_codiceFiscalePaziente;
	private boolean confermaPagamento;

	public Ticket(){

	}



	public Ticket(int ref_codicePrenotazione, String ref_codiceFiscalePaziente, boolean confermaPagamento) {
		super();
		this.ref_codicePrenotazione = ref_codicePrenotazione;
		this.ref_codiceFiscalePaziente = ref_codiceFiscalePaziente;
		this.confermaPagamento = confermaPagamento;
	}



	public int getRef_codicePrenotazione() {
		return ref_codicePrenotazione;
	}

	public void setRef_codicePrenotazione(int ref_codicePrenotazione) {
		this.ref_codicePrenotazione = ref_codicePrenotazione;
	}

	public String getRef_codiceFiscalePaziente() {
		return ref_codiceFiscalePaziente;
	}

	public void setRef_codiceFiscalePaziente(String ref_codiceFiscalePaziente) {
		this.ref_codiceFiscalePaziente = ref_codiceFiscalePaziente;
	}

	public boolean isConfermaPagamento() {
		return confermaPagamento;
	}

	public void setConfermaPagamento(boolean confermaPagamento) {
		this.confermaPagamento = confermaPagamento;
	}


}
