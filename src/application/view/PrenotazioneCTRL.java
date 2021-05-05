package application.view;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Optional;
import java.util.ResourceBundle;

import application.controller.DbConnection;
import application.model.Paziente;
import application.model.PersonaleMedico;
import application.model.Prenotazione;
import application.model.PrestazioneSanitaria;
import application.model.RicettaElettronica;
import application.model.Turno;
import javafx.fxml.Initializable;

public class PrenotazioneCTRL implements Initializable {

	@FXML
    private ImageView logo;

    @FXML
    private Label appBanner;

    @FXML
    private Text infoBanner;

    /*
     * Oggetti FXML schermata calendario
     *
     */
    @FXML
    private DatePicker calendario;

    @FXML
    private Button buttonMostraDisponibilta;

    @FXML
    private TableView<Turno> tabTurni = new TableView<Turno>();

    @FXML
    private TableColumn<Turno, Date> data = new TableColumn<Turno, Date>();

    @FXML
    private TableColumn<Turno, Integer> orario = new TableColumn<Turno, Integer>();

    @FXML
    private TableColumn<Turno, Integer> ambulatorio = new TableColumn<Turno, Integer>();

    @FXML
    private TableColumn<Turno, Integer> medico = new TableColumn<Turno, Integer>();

    @FXML
    private TableColumn<Turno, Boolean> occupato = new TableColumn<Turno, Boolean>();

    public ObservableList<Turno> listTurni = FXCollections.observableArrayList();

    @FXML
    private Button buttonAvanti;

    @FXML
    private Button buttonPrenota;
    /*
     * Oggetti FXML schermata Gestione Prenotazione
     */
    @FXML
    private Button buttonCompletaPrenotazione;

    @FXML
    private Button buttonAnnullPrenotazione;

    /*
     * oggetti FXML schermata Form Dati Prenotazione
     */
    @FXML
    private TextField txtCF;

    @FXML
    private Button buttonConferma;

    @FXML
    private TextField txtNRE;


    /*
     * oggetti FXML schermata completa Prenotazione
     */
    @FXML
    private TableView<Prenotazione> tablePrenotazioniC = new  TableView<Prenotazione>();

    @FXML
    private TableColumn<Prenotazione, String> columnCompletePre = new TableColumn<Prenotazione, String>("Elenco Prenotazioni");

    @FXML
    private TableColumn<Prenotazione, LocalDate> columnDatePre= new TableColumn<Prenotazione, LocalDate>("Data");

    @FXML
    private Button buttonCompleta;

    @FXML
    private TextField txtPrenotazione;
    /*
     * oggetti FXML schermata annulla Prenotazione
     */
    @FXML
    private TableView<Prenotazione> tablePrenotazioniA = new TableView<Prenotazione>();

    @FXML
    private TableColumn<Prenotazione,String> columnAnnullaPre= new TableColumn<Prenotazione, String>();;

    @FXML
    private TableColumn<Prenotazione, LocalDate> columnDataPre= new TableColumn<Prenotazione, LocalDate>("Data");

    /*
     * oggettiFXML schermata Modifica Prenotazione(lato amministrativo)
     */

    @FXML
    private Button buttonSpostaPrenotazione;

    @FXML
    private Button buttonAnnullaPrenotazione;

    @FXML
    private Button buttonAnnulla;

    @FXML
    private TextField txtPrenotazioneA;
    private Prenotazione prenotazione=new Prenotazione();
    private RicettaElettronica ricetta=new RicettaElettronica() ;
    private PrestazioneSanitaria preDip=new PrestazioneSanitaria();
    private DbConnection dc;
    private PersonaleMedico med=new PersonaleMedico();

    private boolean dipendenza;
    private Paziente paziente=new Paziente();

    public ObservableList<Prenotazione> listPrenotazioni = FXCollections.observableArrayList();

    //PrenotazioneCTRL controller = new PrenotazioneCTRL();
    /*
     * funzioni generali
     */
    @FXML
    void logout(ActionEvent event) {

    }
    public void infoRicetta(RicettaElettronica ricetta){
    	this.ricetta=ricetta;
    }
    public void logPaziente(Paziente paziente){
    	this.paziente=paziente;
    }
    public void prestazioneDipendente(PrestazioneSanitaria preDip){
    	this.preDip=preDip;
    }
    public void logMedico(PersonaleMedico medico){
    	this.med=medico;
    }
/*
 * funzioni utili schermata calendario
 */
    @FXML
    void checkDisponibilita(ActionEvent event) {

    	System.out.println(ricetta.getCfPaziente());
listTurni.clear();
    	try{
    		Connection conn = dc.Connect();
    		String pre = ricetta.getPrescrizione();
    		Date data = Date.valueOf(calendario.getValue());
    		System.out.println(data);
    		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    		String d = df.format(data);
    		String query = "SELECT turno.* from turno, Ricetta_elettronica, Prestazione_sanitaria where Prestazione_Sanitaria.nome_prestazione_sanitaria='" + pre + "' and prestazione_sanitaria.ref_ambulatorio = turno.Ref_Ambulatorio and turno.data_turno ='"+d+"' limit 10";
    		ResultSet rs = conn.prepareStatement(query).executeQuery();
    		while (rs.next()){
    			Turno t=new Turno();
    			//System.out.println(t.getRefAmbulatorio());
    			// and turno.data_turno ='"+data+"'

    			listTurni.add(new Turno(rs.getInt(1),rs.getInt(2), rs.getInt(3), rs.getDate(4).toLocalDate(), rs.getInt(6), rs.getBoolean(5)));


    		}
    			} catch(Exception ex) {
    				ex.printStackTrace();
    			}
    	tabTurni.setItems(null);
    	tabTurni.setItems(listTurni);
    }

    Boolean controlloPriorita(){
    	boolean prio;
    	String priorita = ricetta.getPriorita();
System.out.println(priorita);
    		if(priorita.equals("Urgente")){
    			prio=true;
    		}else{
    			prio=false;
    		}
    		System.out.println(prio);

return prio;
    }
    @FXML
    void clickPrenota(ActionEvent event) {
    	Turno m=tabTurni.getSelectionModel().getSelectedItem();
    	if(m.getOrario()==12 || m.getOrario()==18){
    		if(controlloPriorita()==true){
    	if(m.isOccupato()== true){
    	Connection conn=dc.Connect();
    	Alert alert = new Alert(AlertType.CONFIRMATION);
    	alert.setTitle("Finestra di Conferma");
    	alert.setHeaderText("Prenotazione giorno selezionato");
    	alert.setContentText("Sei sicuro di procedere?");

    	ButtonType buttonTypeSi = new ButtonType("SI");
    	ButtonType buttonTypeCancel = new ButtonType("NO", ButtonData.CANCEL_CLOSE);

    	alert.getButtonTypes().setAll(buttonTypeSi, buttonTypeCancel);
    	Optional<ButtonType> result = alert.showAndWait();
    	if (result.get() == buttonTypeSi){
    		//Turno m=tabTurni.getSelectionModel().getSelectedItem();
    		int cod=m.getCodTurno();
    		int ora=m.getOrario();
    		int nre=ricetta.getNre();
    		String cf=ricetta.getCfPaziente();
    		String pre=ricetta.getPrescrizione();
    		Date data = Date.valueOf(calendario.getValue());
    		DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
    		String d = df.format(data);
    		try {
    			PreparedStatement pst = conn.prepareStatement("UPDATE turno SET occupato = '0' WHERE Codice_turno ='"+cod+"'");
    			pst.execute();
    			String query="INSERT INTO prenotazione (ref_ricetta_elettronica, ref_codice_paziente, data_prenotazione, ref_prestazione_sanitaria, ref_turno) VALUES (?,?,?,?,?)";
    			pst.clearParameters();

    			pst=conn.prepareStatement(query);
    			pst.setInt(1,nre);
    			pst.setString(2, cf);
    			pst.setString(3,d);
    			pst.setString(4, pre);
    			pst.setInt(5, ora);
    			pst.execute();

    			this.prenotazione.setRefCodicePaziente(cf);
    			this.prenotazione.setRefPrestazioneSanitaria(pre);
    			this.prenotazione.setTurnoPrenotazione(cod);
    			this.prenotazione.setRefRicettaElettronica(nre);

    					if(controlloDipendenza()==true){

    				PrenotazioneCTRL ctrl= new PrenotazioneCTRL();
    				FXMLLoader loader = new	FXMLLoader();

    				loader.setController(ctrl);
    				ctrl.infoRicetta(this.ricetta);
    				loader.setLocation(getClass().getResource("SchermataCalendarioPrenotazione.fxml"));
    				Parent tableViewParent;
    				try {
    					tableViewParent = loader.load();
    					Scene tableViewScene = new Scene (tableViewParent);
    					// indico le informazione dello Stage
    					Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
    					window.setScene(tableViewScene);
    					window.show();
    				} catch (IOException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				}
    			}else{
    				listTurni.clear();
    				buttonMostraDisponibilta.setDisable(true);
    			}
    		} catch (SQLException e) {
    			Alert alert2 = new Alert(AlertType.WARNING);
    			alert2.setTitle("Finestra Notifica");
    			alert2.setHeaderText("Si è verificato un problema");
    			alert2.setContentText("Manipolazione Dati DBMS Fallita" );
    			alert2.showAndWait();
    				}
    	} else {
    	    // ... user chose CANCEL or closed the dialog
    	}
    	}//ocio
    	else{
    		Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Finestra Notifica");
			alert.setHeaderText("Il turno selezionato è già occupato");
			alert.setContentText("Scegli un altro turno o un'altra data" );
			alert.showAndWait();
    		}
    		}else{
    			Alert alert = new Alert(AlertType.WARNING);
    			alert.setTitle("Finestra Notifica");
    			alert.setHeaderText("Turno selezionato non è disponibile per la sua priotità");
    			alert.setContentText("Scegli un altro turno" );
    			alert.showAndWait();
    		}
    	}else{
    		if(m.isOccupato()== true){
    	    	Connection conn=dc.Connect();
    	    	Alert alert = new Alert(AlertType.CONFIRMATION);
    	    	alert.setTitle("Finestra di Conferma");
    	    	alert.setHeaderText("Prenotazione giorno selezionato");
    	    	alert.setContentText("Sei sicuro di procedere?");

    	    	ButtonType buttonTypeSi = new ButtonType("SI");
    	    	ButtonType buttonTypeCancel = new ButtonType("NO", ButtonData.CANCEL_CLOSE);

    	    	alert.getButtonTypes().setAll(buttonTypeSi, buttonTypeCancel);
    	    	Optional<ButtonType> result = alert.showAndWait();
    	    	if (result.get() == buttonTypeSi){
    	    		//Turno m=tabTurni.getSelectionModel().getSelectedItem();
    	    		int cod=m.getCodTurno();
    	    		int ora=m.getOrario();
    	    		int nre=ricetta.getNre();
    	    		String cf=ricetta.getCfPaziente();
    	    		String pre=ricetta.getPrescrizione();
    	    		Date data = Date.valueOf(calendario.getValue());
    	    		DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
    	    		String d = df.format(data);
    	    		try {
    	    			PreparedStatement pst = conn.prepareStatement("UPDATE turno SET occupato = '0' WHERE Codice_turno ='"+cod+"'");
    	    			pst.execute();
    	    			String query="INSERT INTO prenotazione (ref_ricetta_elettronica, ref_codice_paziente, data_prenotazione, ref_prestazione_sanitaria, ref_turno) VALUES (?,?,?,?,?)";
    	    			pst.clearParameters();

    	    			pst=conn.prepareStatement(query);
    	    			pst.setInt(1,nre);
    	    			pst.setString(2, cf);
    	    			pst.setString(3,d);
    	    			pst.setString(4, pre);
    	    			pst.setInt(5, ora);
    	    			pst.execute();

    	    			this.prenotazione.setRefCodicePaziente(cf);
    	    			this.prenotazione.setRefPrestazioneSanitaria(pre);
    	    			this.prenotazione.setTurnoPrenotazione(cod);
    	    			this.prenotazione.setRefRicettaElettronica(nre);

    	    					if(controlloDipendenza()==true){

    	    				PrenotazioneCTRL ctrl= new PrenotazioneCTRL();
    	    				FXMLLoader loader = new	FXMLLoader();

    	    				loader.setController(ctrl);
    	    				ctrl.infoRicetta(this.ricetta);
    	    				loader.setLocation(getClass().getResource("SchermataCalendarioPrenotazione.fxml"));
    	    				Parent tableViewParent;
    	    				try {
    	    					tableViewParent = loader.load();
    	    					Scene tableViewScene = new Scene (tableViewParent);
    	    					// indico le informazione dello Stage
    	    					Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
    	    					window.setScene(tableViewScene);
    	    					window.show();
    	    				} catch (IOException e) {
    	    					// TODO Auto-generated catch block
    	    					e.printStackTrace();
    	    				}
    	    			}else{
    	    				listTurni.clear();
    	    				buttonMostraDisponibilta.setDisable(true);
    	    			}
    	    		} catch (SQLException e) {
    	    			Alert alert2 = new Alert(AlertType.WARNING);
    	    			alert2.setTitle("Finestra Notifica");
    	    			alert2.setHeaderText("Si è verificato un problema");
    	    			alert2.setContentText("Manipolazione Dati DBMS Fallita" );
    	    			alert2.showAndWait();
    	    				}
    	    	} else {
    	    	    // ... user chose CANCEL or closed the dialog
    	    	}
    	    	}//ocio
    	    	else{
    	    		Alert alert = new Alert(AlertType.WARNING);
    				alert.setTitle("Finestra Notifica");
    				alert.setHeaderText("Il turno selezionato è già occupato");
    				alert.setContentText("Scegli un altro turno o un'altra data" );
    				alert.showAndWait();
    	    		}
    	}
    }
    @FXML
	void clickAvanti(ActionEvent event) {
		 try {
				Connection conn=dc.Connect();
				String query1="SELECT * FROM PAZIENTE WHERE codice_fiscale='"+ricetta.getCfPaziente()+"'";
				ResultSet rs1 = conn.prepareStatement(query1).executeQuery();
				String query2="SELECT * FROM PRESTAZIONE_SANITARIA WHERE nome_prestazione_sanitaria='"+ricetta.getPrescrizione()
				+"'";
				ResultSet rs2 = conn.prepareStatement(query2).executeQuery();
				while(rs1.next()&&rs2.next()){
					this.paziente.setCodiceFiscale(rs1.getString(1));
					this.paziente.setNome(rs1.getString(3));
					this.paziente.setCognome(rs1.getString(4));
					this.paziente.setEsenzione(rs1.getString(7));
					//PrestazioneSanitaria prestazione= new PrestazioneSanitaria();
					preDip.setCosto(rs2.getInt(4));
					preDip.setNomePrestazione(rs2.getString(2));

				}

				FXMLLoader loader = new	FXMLLoader();
				loader.setLocation(getClass().getResource("SchermataTicket.fxml"));
				Parent tableViewParent= loader.load();

				Scene tableViewScene = new Scene (tableViewParent);
				//accedo al controller e chiamo il metodo

				TicketCTRL controller = loader.getController();
				controller.logPaziente(paziente);
				controller.infoPrestazione(preDip);
				controller.infoPrenotazione(prenotazione);
				controller.setLabel(paziente, preDip);
				// indico le informazione dello Stage
				Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
				window.setScene(tableViewScene);
				window.show();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
	}


    boolean controlloDipendenza(){

    	String pre=ricetta.getPrescrizione();

    	try {
			Connection conn=dc.Connect();
			String query="SELECT dipendenza from prestazione_sanitaria,ricetta_elettronica where prestazione_sanitaria.Nome_Prestazione_Sanitaria='"+pre+"'";
			ResultSet rs = conn.prepareStatement(query).executeQuery();

			if(rs.next()){
				PrestazioneSanitaria ps= new PrestazioneSanitaria();
				ps.setDipendenza(rs.getString(1));
				String dp=ps.getDipendenza();
				String query2="select Nome_prestazione_sanitaria from prestazione_sanitaria where Codice_prestazione_Sanitaria = '"+dp+"' ";
				ResultSet rs2 = conn.prepareStatement(query2).executeQuery();
					if(rs2.next()){
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Finestra Notifica");
				alert.setHeaderText("La prestazione che si vuole prenotare è dipendente da un' altra");
				alert.setContentText("Prenota Ora '"+rs2.getString(1)+"'!");
				alert.showAndWait();
				this.ricetta.setPrescrizione(rs2.getString(1));
				this.dipendenza=true;
					}
			}else{
				this.dipendenza=false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return dipendenza;
    	}

    /*
     * funzioni utili schermata "Gestione Prenotazione"
     */
    @FXML
    void clickAnnullPrenotazione(ActionEvent event) {
    	 try {
    		 	PrenotazioneCTRL ctrl= new PrenotazioneCTRL();
				FXMLLoader loader = new	FXMLLoader();

				loader.setController(ctrl);
				ctrl.logPaziente(paziente);
				loader.setLocation(getClass().getResource("SchermataAnnullaPrenotazione.fxml"));
				Parent tableViewParent= loader.load();

				Scene tableViewScene = new Scene (tableViewParent);
				// indico le informazione dello Stage
				Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
				window.setScene(tableViewScene);
				window.show();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
    }

    @FXML
    void clickCompletaPrenotazione(ActionEvent event) {
    	 try {
    		 	PrenotazioneCTRL ctrl= new PrenotazioneCTRL();
				FXMLLoader loader = new	FXMLLoader();

				loader.setController(ctrl);
				ctrl.logPaziente(paziente);
				loader.setLocation(getClass().getResource("SchermataCompletaPrenotazione.fxml"));
				Parent tableViewParent= loader.load();
				Scene tableViewScene = new Scene (tableViewParent);
				// indico le informazione dello Stage
				Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
				window.setScene(tableViewScene);
				window.show();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
    }
    @FXML
    void clickIndietroP(ActionEvent event) {
    	 try {
    		 	FXMLLoader loader = new	FXMLLoader();
				loader.setLocation(getClass().getResource("SchermataHomePaziente.fxml"));
				Parent tableViewParent= loader.load();

				Scene tableViewScene = new Scene (tableViewParent);
				//accedo al controller e chiamo il metodo

				HomePazienteCTRL controller = loader.getController();
				controller.logPaziente(paziente);

				// indico le informazione dello Stage
				Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
				window.setScene(tableViewScene);
				window.show();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
    }
    /*
     * funzioni utili nella schermata "Form Dati Prenotazione"
     */
    @FXML
    void clickConferma(ActionEvent event) {

    	try {
    		  Connection conn= dc.Connect();

  			String query = "SELECT * FROM RICETTA_ELETTRONICA WHERE CODICE_FISCALE_PAZIENTE = '" + txtCF.getText().toUpperCase() + "'  AND NRE= '" + txtNRE.getText()+"'";
  			String query2 = "SELECT * FROM PRENOTAZIONE WHERE ref_ricetta_elettronica='" + txtNRE.getText()+"'";
  			//System.err.printf("The con variable is not null: %b%n", conn);
  			ResultSet resultSet = conn.prepareStatement(query).executeQuery();
  			ResultSet rs = conn.prepareStatement(query2).executeQuery();

  			if (resultSet.next()==true && rs.next()==false){

				this.ricetta.setNre(resultSet.getInt(1));
				this.ricetta.setCfPaziente(resultSet.getString(2));
				this.ricetta.setPriorita(resultSet.getString(3));
				this.ricetta.setPrescrizione(resultSet.getString(4));

				//System.out.println(ricetta.getCfPaziente());

				PrenotazioneCTRL ctrl= new PrenotazioneCTRL();
				FXMLLoader loader = new	FXMLLoader();

				loader.setController(ctrl);
				ctrl.infoRicetta(this.ricetta);
				loader.setLocation(getClass().getResource("SchermataCalendarioPrenotazione.fxml"));
				Parent tableViewParent= loader.load();

				//controlloDipendenza();

				//Parent tableViewParent= FXMLLoader.load(getClass().getResource("SchermataCalendarioPrenotazione.fxml"));
				Scene tableViewScene = new Scene (tableViewParent);
				// indico le informazione dello Stage
				Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
				window.setScene(tableViewScene);
				window.show();

  			}else{
  				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Finestra Notifica");
				alert.setHeaderText("Dati inseriti non corretti");
				alert.setContentText("Inserisci nuovamente");

				alert.showAndWait();
  			}
			} catch (Exception ex) {
				ex.printStackTrace();
			}


    }
    /*
     * funzioni utili nella schermata "Form Dati Paziente"
     */
    @FXML
    void clickConfermaC(ActionEvent event) {

    		 try {
       		  Connection conn= dc.Connect();

     			String query = "SELECT * FROM PAZIENTE WHERE CODICE_FISCALE = '" + txtCF.getText().toUpperCase() + "'";

     			String query2 = "SELECT Nome_prestazione_sanitaria FROM PERSONALE_MEDICO,PRESTAZIONE_SANITARIA WHERE Personale_medico.username_medico='" + med.getUsername()+"' AND prestazione_sanitaria.ref_ambulatorio='"+med.getCodAmbulatorio()+"' ";
     			//System.err.printf("The con variable is not null: %b%n", conn);
     			ResultSet resultSet = conn.prepareStatement(query).executeQuery();
     			ResultSet rs = conn.prepareStatement(query2).executeQuery();

     			if (resultSet.next()==true && rs.next()==true){

   				//this.ricetta.setNre(resultSet.getInt(1));
   				this.ricetta.setCfPaziente(resultSet.getString(1));
   				this.ricetta.setPrescrizione(rs.getString(1));
   				this.ricetta.setNre(med.getCodAmbulatorio());
   				this.ricetta.setPriorita("Programmata");

   				//System.out.println(ricetta.getCfPaziente());

   				PrenotazioneCTRL ctrl= new PrenotazioneCTRL();
   				FXMLLoader loader = new	FXMLLoader();

   				loader.setController(ctrl);
   				ctrl.infoRicetta(this.ricetta);
   				loader.setLocation(getClass().getResource("SchermataCalendarioPrenotazione.fxml"));
   				Parent tableViewParent= loader.load();

   				//controlloDipendenza();

   				//Parent tableViewParent= FXMLLoader.load(getClass().getResource("SchermataCalendarioPrenotazione.fxml"));
   				Scene tableViewScene = new Scene (tableViewParent);
   				// indico le informazione dello Stage
   				Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
   				window.setScene(tableViewScene);
   				window.show();

     			}else{
     				Alert alert = new Alert(AlertType.WARNING);
   				alert.setTitle("Finestra Notifica");
   				alert.setHeaderText("Dati inseriti non corretti");
   				alert.setContentText("Inserisci nuovamente");

   				alert.showAndWait();
     			}
   			} catch (Exception ex) {
   				ex.printStackTrace();
   			}

    }
    @FXML
    void clickIndietroF(ActionEvent event){
    	 try {
				Parent tableViewParent= FXMLLoader.load(getClass().getResource("SchermataHomeMedico.fxml"));
				Scene tableViewScene = new Scene (tableViewParent);
				// indico le informazione dello Stage
				Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
				window.setScene(tableViewScene);
				window.show();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
    }
    /*
     * funzioni utili nella schermata "Completa Prenotazione"
     */
    @FXML
    void clickCompleta(ActionEvent event) {
    	Connection conn=dc.Connect();
    	Prenotazione p=tablePrenotazioniC.getSelectionModel().getSelectedItem();

    	try {
			PreparedStatement pst =conn.prepareStatement("UPDATE PRENOTAZIONE SET Conferma_prenotazione ='1'"
					+ "where ref_codice_paziente='"+paziente.getCodiceFiscale()+"' AND codice_prenotazione='"+p.getCodicePrenotazione() +"'");
			if(pst.executeUpdate()!=0){
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Finestra Notifica");
				alert.setHeaderText("Prenotazione Confermata");
				alert.setContentText("COMPLIMENTI!!");
				alert.showAndWait();
			}else {
				System.out.println("doh");
			}

			pst.close();


		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void clickIndietroC(ActionEvent event) {
    	 try {
    		 	PrenotazioneCTRL ctrl= new PrenotazioneCTRL();
				FXMLLoader loader = new	FXMLLoader();

				loader.setController(ctrl);
				ctrl.logPaziente(paziente);
				loader.setLocation(getClass().getResource("SchermataGestionePrenotazione.fxml"));
				Parent tableViewParent= loader.load();
				Scene tableViewScene = new Scene (tableViewParent);
				// indico le informazione dello Stage
				Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
				window.setScene(tableViewScene);
				window.show();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
    }


    /*
     *  funzioni utili nella schermata "Annulla Prenotazione"
     */

    @FXML
    void clickAnnulla(ActionEvent event) {
    	Connection conn=dc.Connect();
    	Prenotazione p=tablePrenotazioniA.getSelectionModel().getSelectedItem();

    	try {
			PreparedStatement pst =conn.prepareStatement("DELETE FROM PRENOTAZIONE WHERE Codice_prenotazione ='"+p.getCodicePrenotazione()+"'");
			if(pst.executeUpdate()!=0){
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Finestra Notifica");
				alert.setHeaderText("Prenotazione Annullata Correttamente");
				alert.setContentText("Good Job");
				alert.showAndWait();
			}else {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Finestra Notifica");
				alert.setHeaderText("Riprova");
				alert.setContentText("");
				alert.showAndWait();
			}

			pst.close();


		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void clickIndietroE(ActionEvent event) {
    	try {
    		PrenotazioneCTRL ctrl= new PrenotazioneCTRL();
			FXMLLoader loader = new	FXMLLoader();

			loader.setController(ctrl);
			ctrl.logPaziente(paziente);
			loader.setLocation(getClass().getResource("SchermataGestionePrenotazione.fxml"));
			Parent tableViewParent= loader.load();
			Scene tableViewScene = new Scene (tableViewParent);
			// indico le informazione dello Stage
			Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
			window.setScene(tableViewScene);
			window.show();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
    }

    /*
     * funzioni schermata modifica prenotazione(lato amministrativo)
     */


    @FXML
    void clickSpostaPrenotazione(ActionEvent event) {
    	try {
    		PrenotazioneCTRL ctrl=new PrenotazioneCTRL();
			FXMLLoader loader = new	FXMLLoader();
			loader.setLocation(getClass().getResource("FormDatiPrenotazone.fxml"));
			loader.setController(ctrl);
			Parent tableViewParent= loader.load();

			Scene tableViewScene = new Scene (tableViewParent);
			// indico le informazione dello Stage
			Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
			window.setScene(tableViewScene);
			window.show();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
    }
    @FXML
    void clickIndietroS(ActionEvent event) {
    	Parent tableViewParent;
		try {
			tableViewParent = FXMLLoader.load(getClass().getResource("SchermataHomeAmministrativo.fxml"));

			Scene tableViewScene = new Scene (tableViewParent);
			// indico le informazione dello Stage
			Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
			window.setScene(tableViewScene);
			window.show();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    }



    @FXML
    void clickAnnullaPrenotazione(ActionEvent event) {
    	try {
			Parent tableViewParent= FXMLLoader.load(getClass().getResource("FormPrenotazione.fxml"));
			Scene tableViewScene = new Scene (tableViewParent);
			// indico le informazione dello Stage
			Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
			window.setScene(tableViewScene);
			window.show();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
    }
/*
 * funzioni utili schermata form Prenotazione
 */
	public void clickConfermaA(ActionEvent event) throws SQLException{
		Connection conn=dc.Connect();

		String nre= txtNRE.getText();
		String cf = txtCF.getText();

		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Finestra di Conferma");
		alert.setHeaderText("Stai eliminando definitivamente questa Prenotazione");
		alert.setContentText("Sei sicuro di procedere?");

		ButtonType buttonTypeSi = new ButtonType("SI");
		ButtonType buttonTypeCancel = new ButtonType("NO", ButtonData.CANCEL_CLOSE);

		alert.getButtonTypes().setAll(buttonTypeSi, buttonTypeCancel);
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == buttonTypeSi){
			PreparedStatement pst =conn.prepareStatement("DELETE FROM PRENOTAZIONE WHERE Ref_ricetta_elettronica ='"+nre+"' AND ref_codice_paziente='"+cf+"'");
			if(pst.executeUpdate()==0){
				Alert alert2= new Alert(AlertType.WARNING);
				alert2.setTitle("Finestra Notifica");
				alert2.setHeaderText("Non è stato possibile eliminare la Prenotazione");
				alert2.setContentText("Assicurati di aver inserito i dati corretti di una Prenotazione ancora valida");
				alert2.showAndWait();
			}
		}

	}

	@FXML
	void clickIndietroA(ActionEvent event) {
		try {
			Parent tableViewParent= FXMLLoader.load(getClass().getResource("SchermataModificaPrenotazione.fxml"));
			Scene tableViewScene = new Scene (tableViewParent);
			// indico le informazione dello Stage
			Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
			window.setScene(tableViewScene);
			window.show();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}



    void loadPrenotazioni(){
    	 try{
	    		Connection conn = dc.Connect();


	    		String query = "SELECT data_prenotazione,ref_prestazione_sanitaria,ref_turno,codice_prenotazione from PRENOTAZIONE where ref_codice_paziente='"+paziente.getCodiceFiscale()+"'";
	    		ResultSet rs = conn.prepareStatement(query).executeQuery();

	    		while (rs.next()){
	    			Prenotazione pre = new Prenotazione();
	    			//pre.setRefPrestazioneSanitaria(rs.getString(5));
	    			//String prest= pre.getRefPrestazioneSanitaria();
	    			listPrenotazioni.add(new Prenotazione(rs.getInt(4),rs.getDate(1).toLocalDate(),rs.getString(2),rs.getInt(3)));

	    			tablePrenotazioniC.setItems(listPrenotazioni);
	    			tablePrenotazioniA.setItems(listPrenotazioni);

	    		}


	    			} catch(Exception ex) {
	    				ex.printStackTrace();
	    			}
    	 	columnDataPre.setCellValueFactory(new PropertyValueFactory<Prenotazione, LocalDate>("dataPrenotazione"));
    	 	columnDatePre.setCellValueFactory(new PropertyValueFactory<Prenotazione, LocalDate>("dataPrenotazione"));
			columnCompletePre.setCellValueFactory(new PropertyValueFactory<Prenotazione, String>("refPrestazioneSanitaria"));
			columnAnnullaPre.setCellValueFactory(new PropertyValueFactory<Prenotazione, String>("refPrestazioneSanitaria"));
			/*ColumnOrarioS.setCellValueFactory(new PropertyValueFactory<Prenotazione, Integer>("turnoPrenotazione"));
			ColumnCodS.setCellValueFactory(new PropertyValueFactory<Prenotazione, Integer>("codicePrenotazione"));*/
    }

    @FXML
    void clickConfermaM(ActionEvent event) {
    	try {
  		  Connection conn= dc.Connect();

			String query = "SELECT * FROM RICETTA_ELETTRONICA WHERE CODICE_FISCALE_PAZIENTE = '" + txtCF.getText().toUpperCase() + "'  AND NRE= '" + txtNRE.getText()+"'";
			String query2 = "SELECT * FROM PRENOTAZIONE WHERE ref_ricetta_elettronica='" + txtNRE.getText()+"'";
			//System.err.printf("The con variable is not null: %b%n", conn);
			ResultSet resultSet = conn.prepareStatement(query).executeQuery();
			ResultSet rs = conn.prepareStatement(query2).executeQuery();

			if (resultSet.next()==true && rs.next()==true){
				PreparedStatement pst=conn.prepareStatement("DELETE FROM TICKET where ref_codice_prenotazione='"+rs.getInt(1)+"'");
				pst.execute();
				PreparedStatement ps=conn.prepareStatement("DELETE FROM PRENOTAZIONE WHERE ref_ricetta_elettronica='" + txtNRE.getText()+"' ");

					if(ps.execute()==true){

						this.ricetta.setNre(resultSet.getInt(1));
						this.ricetta.setCfPaziente(resultSet.getString(2));
						this.ricetta.setPriorita(resultSet.getString(3));
						this.ricetta.setPrescrizione(resultSet.getString(4));
						}else{
							Alert alert = new Alert(AlertType.WARNING);
							alert.setTitle("Finestra Notifica");
							alert.setHeaderText("Manipolazione Dati DBMS fallita");
							alert.setContentText("");
							alert.showAndWait();

						}

				//System.out.println(ricetta.getCfPaziente());

				PrenotazioneCTRL ctrl= new PrenotazioneCTRL();
				FXMLLoader loader = new	FXMLLoader();

				loader.setController(ctrl);
				ctrl.infoRicetta(this.ricetta);
				loader.setLocation(getClass().getResource("SchermataCalendarioPrenotazione.fxml"));
				Parent tableViewParent= loader.load();

				//controlloDipendenza();

				//Parent tableViewParent= FXMLLoader.load(getClass().getResource("SchermataCalendarioPrenotazione.fxml"));
				Scene tableViewScene = new Scene (tableViewParent);
				// indico le informazione dello Stage
				Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
				window.setScene(tableViewScene);
				window.show();

			}else{
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Finestra Notifica");
				alert.setHeaderText("Dati inseriti non corretti");
				alert.setContentText("Inserisci nuovamente");

				alert.showAndWait();
			}
			} catch (Exception ex) {
				ex.printStackTrace();
			}

    }
/*
 * (non-Javadoc)
 * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		dc=new DbConnection();
		this.loadPrenotazioni();
		occupato.setCellValueFactory(new PropertyValueFactory<Turno, Boolean>("occupato"));
    	data.setCellValueFactory(new PropertyValueFactory<Turno, Date>("data"));
    	orario.setCellValueFactory(new PropertyValueFactory<Turno, Integer>("orario"));
    	ambulatorio.setCellValueFactory(new PropertyValueFactory<Turno, Integer>("refAmbulatorio"));
    	medico.setCellValueFactory(new PropertyValueFactory<Turno, Integer>("refMedico"));

	}

}
