package application.model;

public class PersonaleMedico {



	public PersonaleMedico() {
		super();
		// TODO Auto-generated constructor stub
	}
	private int codiceMedico;
	private String username;
	private String password;
	private String nome, cognome, email, cellulare;
	private int codAmbulatorio;


	public int getCodiceMedico() {
		return codiceMedico;
	}

	public void setCodiceMedico(int codiceMedico) {
		this.codiceMedico = codiceMedico;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCellulare() {
		return cellulare;
	}

	public void setCellulare(String cellulare) {
		this.cellulare = cellulare;
	}
	public PersonaleMedico(int codiceMedico, String username, String password, String nome, String cognome,
			String email, String cellulare) {
		super();
		this.codiceMedico = codiceMedico;
		this.username = username;
		this.password = password;
		this.nome = nome;
		this.cognome = cognome;
		this.email = email;
		this.cellulare = cellulare;
	}

	public PersonaleMedico(String username, String nome, String cognome) {
		this.username = username;
		this.nome = nome;
		this.cognome = cognome;
		}

	public PersonaleMedico(int codiceMedico, String nome, String cognome) {
		this.codiceMedico = codiceMedico;
		this.nome = nome;
		this.cognome = cognome;	}

	public PersonaleMedico(PersonaleMedico dott) {
		// TODO Auto-generated constructor stub
	}

	public int getCodAmbulatorio() {
		return codAmbulatorio;
	}

	public void setCodAmbulatorio(int codAmbulatorio) {
		this.codAmbulatorio = codAmbulatorio;
	}
		}
