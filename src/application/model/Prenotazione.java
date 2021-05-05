package application.model;

import java.sql.Date;
import java.time.LocalDate;

public class Prenotazione {



	private int codicePrenotazione, refRicettaElettronica,turnoPrenotazione;
	private String refCodicePaziente;
	private LocalDate dataPrenotazione;
	private String refPrestazioneSanitaria;
	private boolean conferma;

	public Prenotazione() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Prenotazione(int intt,LocalDate date, String string, int int1) {
		this.codicePrenotazione=intt;
		this.dataPrenotazione=date;
		this.refPrestazioneSanitaria=string;
		this.turnoPrenotazione=int1;


	}

	public Prenotazione(String string, String string2, LocalDate localDate) {
		this.refPrestazioneSanitaria=string;
		this.refCodicePaziente=string2;
		this.dataPrenotazione=localDate;
	}
	public int getCodicePrenotazione() {
		return codicePrenotazione;
	}

	public void setCodicePrenotazione(int codicePrenotazione) {
		this.codicePrenotazione = codicePrenotazione;
	}

	public int getRefRicettaElettronica() {
		return refRicettaElettronica;
	}

	public void setRefRicettaElettronica(int refRicettaElettronica) {
		this.refRicettaElettronica = refRicettaElettronica;
	}

	public String getRefPrestazioneSanitaria() {
		return refPrestazioneSanitaria;
	}

	public void setRefPrestazioneSanitaria(String refPrestazioneSanitaria) {
		this.refPrestazioneSanitaria = refPrestazioneSanitaria;
	}

	public int getTurnoPrenotazione() {
		return turnoPrenotazione;
	}

	public void setTurnoPrenotazione(int turnoPrenotazione) {
		this.turnoPrenotazione = turnoPrenotazione;
	}

	public String getRefCodicePaziente() {
		return refCodicePaziente;
	}

	public void setRefCodicePaziente(String refCodicePaziente) {
		this.refCodicePaziente = refCodicePaziente;
	}

	public LocalDate getDataPrenotazione() {
		return dataPrenotazione;
	}

	public void setDataPrenotazione(LocalDate dataPrenotazione) {
		this.dataPrenotazione = dataPrenotazione;
	}

	  public String toString() {
	        return refPrestazioneSanitaria + String.valueOf(codicePrenotazione);
	    }

}
