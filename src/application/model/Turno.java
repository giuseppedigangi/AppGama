package application.model;

import java.time.LocalDate;

import javafx.scene.control.Button;



public class Turno {




	private int refMedico;
	private int codTurno;
	private int refAmbulatorio;
	private boolean occupato;
	private LocalDate data;
	private int orario;
private Button button;


	public Turno(int refMedico, int codTurno, int refAmbulatorio,  LocalDate data, int orario) {
		super();
		this.refMedico = refMedico;
		this.codTurno = codTurno;
		this.refAmbulatorio = refAmbulatorio;
		this.data = data;
		this.orario=orario;
		this.button=new Button("Modifica");
	}

	public Turno(int codTurno,int refMedico, int refAmbulatorio,  LocalDate data, int orario, boolean occupato) {
		super();
		this.codTurno=codTurno;
		this.refMedico = refMedico;
		this.refAmbulatorio = refAmbulatorio;
		this.data = data;
		this.orario=orario;
		this.occupato=occupato;
	}
	public Turno() {
		// TODO Auto-generated constructor stub
	}
	public void setOccupato(boolean occupato) {
		this.occupato = occupato;
	}

	public int getRefMedico() {
		return refMedico;
	}


	public void setRefMedico(int refMedico) {
		this.refMedico = refMedico;
	}


	public int getCodTurno() {
		return codTurno;
	}


	public void setCodTurno(int codTurno) {
		this.codTurno = codTurno;
	}


	public int getRefAmbulatorio() {
		return refAmbulatorio;
	}


	public void setRefAmbulatorio(int refAmbulatorio) {
		this.refAmbulatorio = refAmbulatorio;
	}


	public boolean isOccupato() {
		return occupato;
	}


	public void setTipo(boolean occupato) {
		this.occupato = occupato;
	}


	public LocalDate getData() {
		return data;
	}


	public void setData(LocalDate data) {
		this.data = data;
	}
	public int getOrario() {
		return orario;
	}
	public void setOrario(int orario) {
		this.orario = orario;
	}
	@Override
	 public String toString() {
	        return Integer.toString(codTurno)+ " "  +Integer.toString(refAmbulatorio)+  " " + Integer.toString(orario);
	    }
}
