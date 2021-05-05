package application.model;

public class Ambulatorio {

	private int codiceAmbulatorio, refReparto, refMedico;
	private String nomeAmbulatorio;


	public int getCodiceAmbulatorio() {
		return codiceAmbulatorio;
	}

	public void setCodiceAmbulatorio(int codiceAmbulatorio) {
		this.codiceAmbulatorio = codiceAmbulatorio;
	}

	public int getRefReparto() {
		return refReparto;
	}

	public void setRefReparto(int refReparto) {
		this.refReparto = refReparto;
	}

	public int getRefMedico() {
		return refMedico;
	}

	public void setRefMedico(int refMedico) {
		this.refMedico = refMedico;
	}

	public String getNomeAmbulatorio() {
		return nomeAmbulatorio;
	}

	public void setNomeAmbulatorio(String nomeAmbulatorio) {
		this.nomeAmbulatorio = nomeAmbulatorio;
	}



}
