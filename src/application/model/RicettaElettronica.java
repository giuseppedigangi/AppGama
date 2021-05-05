package application.model;

public class RicettaElettronica {

	private int nre, esenzione;
	private String cfPaziente, priorita, prescrizione;
	public int getNre() {
		return nre;
	}
	public void setNre(int nre) {
		this.nre = nre;
	}
	public String getPrescrizione() {
		return prescrizione;
	}
	public void setPrescrizione(String prescrizione) {
		this.prescrizione = prescrizione;
	}
	public int getEsenzione() {
		return esenzione;
	}
	public void setEsenzione(int esenzione) {
		this.esenzione = esenzione;
	}
	public String getCfPaziente() {
		return cfPaziente;
	}
	public void setCfPaziente(String cfPaziente) {
		this.cfPaziente = cfPaziente;
	}
	public String getPriorita() {
		return priorita;
	}
	public void setPriorita(String priorita) {
		this.priorita = priorita;
	}


}
