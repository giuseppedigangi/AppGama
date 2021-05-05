package application.model;

import java.time.LocalDate;

public class PersonaleAmministrativo {




	public PersonaleAmministrativo() {
		super();
		// TODO Auto-generated constructor stub
	}


	private String usernameAmministrativo;
	private String passwordAmministrativo;
	private String nomeAmministrativo;
	private String cognomeAmministrativo;
	private LocalDate dataNascita;


	public LocalDate getDataNascita() {
		return dataNascita;
	}


	public void setDataNascita(LocalDate dataNascita) {
		this.dataNascita = dataNascita;
	}


	public String getCellullare() {
		return cellullare;
	}


	public void setCellullare(String cellullare) {
		this.cellullare = cellullare;
	}


	private String cellullare;



	public PersonaleAmministrativo(String usernameAmministrativo,
			String nomeAmministrativo, String cognomeAmministrativo) {
		super();

		this.nomeAmministrativo = nomeAmministrativo;
		this.cognomeAmministrativo = cognomeAmministrativo;
		this.usernameAmministrativo = usernameAmministrativo;
	}


	public String getUsernameAmministrativo() {
		return usernameAmministrativo;
	}


	public void setUsernameAmministrativo(String usernameAmministrativo) {
		this.usernameAmministrativo = usernameAmministrativo;
	}


	public String getPasswordAmministrativo() {
		return passwordAmministrativo;
	}


	public void setPasswordAmministrativo(String passwordAmministrativo) {
		this.passwordAmministrativo = passwordAmministrativo;
	}


	public String getNomeAmministrativo() {
		return nomeAmministrativo;
	}


	public void setNomeAmministrativo(String nomeAmministrativo) {
		this.nomeAmministrativo = nomeAmministrativo;
	}


	public String getCognomeAmministrativo() {
		return cognomeAmministrativo;
	}


	public void setCognomeAmministrativo(String cognomeAmministrativo) {
		this.cognomeAmministrativo = cognomeAmministrativo;
	}
	@Override
    public String toString() {
        return nomeAmministrativo;
    }

}
