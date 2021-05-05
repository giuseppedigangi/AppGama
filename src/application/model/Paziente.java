package application.model;

import java.time.LocalDate;

public class Paziente {

	private String codiceFiscale;
	private String numeroTessera;
	private String nome;
	private String cognome;
	private String cellulare;
	private LocalDate dataNascita;
	private String email;
	private String esenzione;


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEsenzione() {
		return esenzione;
	}

	public void setEsenzione(String esenzione) {
		this.esenzione = esenzione;
	}

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	public String getNumeroTessera() {
		return numeroTessera;
	}

	public void setNumeroTessera(String numeroTessera) {
		this.numeroTessera = numeroTessera;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getCellulare() {
		return cellulare;
	}

	public void setCellulare(String cellulare) {
		this.cellulare = cellulare;
	}

	public LocalDate getDataNascita() {
		return dataNascita;
	}

	public void setDataNascita(LocalDate dataNascita) {
		this.dataNascita = dataNascita;
	}


}
