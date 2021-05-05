package application.view;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ResourceBundle;

import application.controller.DbConnection;
import application.model.Paziente;
import application.model.PersonaleAmministrativo;
import application.model.PersonaleMedico;
import application.model.Prenotazione;
import application.model.PrestazioneSanitaria;
import javafx.fxml.Initializable;

public class CartellaClinicaCTRL implements Initializable {

	   @FXML
	    private ImageView logo;

	    @FXML
	    private Label appBanner;

	    @FXML
	    private Text infoBanner;

	    @FXML
	    private Button buttonIndietro;

	    @FXML
	    private Button buttonLogout;
		/*
		 * Oggetti FXML schermata "Consultazione Cartella Clinica"
		 */
	    @FXML
	    private Button buttonControlloStorico;

	    @FXML
	    private Button buttonStampaCartellaClinica;

	    /*
	     * oggetti FXML schermata "Storico Visite"
	     */
	    @FXML
	    private TableView<Prenotazione> tablePrenotazioni=new TableView<Prenotazione>();

	    @FXML
	    private TableColumn<Prenotazione, LocalDate> columnDataS=new TableColumn<Prenotazione, LocalDate>("Data");

	    @FXML
	    private TableColumn<Prenotazione, String> columnPrestazioneS =new TableColumn<Prenotazione, String>("Prestazione");

	    @FXML
	    private TableColumn<Prenotazione, Integer> ColumnOrarioS =  new TableColumn<Prenotazione, Integer>("Orario");

	    @FXML
	    private TableColumn<Prenotazione, Integer> ColumnCodS =  new TableColumn<Prenotazione, Integer>("Cod");

	    @FXML
	    private TableView<PrestazioneSanitaria> tablePrestazioni= new TableView<PrestazioneSanitaria>();

	    @FXML
	    private TableColumn<PrestazioneSanitaria, String> columnAmbS = new TableColumn<PrestazioneSanitaria, String>("Ambulatorio");

	    @FXML
	    private TableColumn<PrestazioneSanitaria, Integer> columnCostoS= new TableColumn<PrestazioneSanitaria, Integer>("Costo");

	   /*
	    *  oggetti FXML schermata "Search Cartella Clinica"
	    */
	    @FXML
	    private Button buttonSearch;

	    @FXML
	    private TextArea txtCF;
	    /*
	     *  oggetti FXML schermata "stampa Cartella Clinica"
		 */
	    @FXML
	    private TableView<Prenotazione> tableCartellaCLinicaP = new TableView<Prenotazione>();

	    @FXML
	    private TableColumn<Prenotazione, String> ColimunVisitaP = new TableColumn<Prenotazione, String>("Visita");

	    @FXML
	    private TableColumn<Prenotazione, String> columnDottoreP = new TableColumn<Prenotazione, String>("Dottore");

	    @FXML
	    private TableColumn<Prenotazione, LocalDate> columnDate = new TableColumn<Prenotazione, LocalDate>("Data");

	    @FXML
	    private Button buttonStampa;
	    /*
	     * oggetti FXML schermata "Cartella Clinica Paziente"
	     */
	    @FXML
	    private TableView<?> tableCartellaCLinicaM;

	    @FXML
	    private TableColumn<?, ?> columnVisitaM;

	    @FXML
	    private TableColumn<?, ?> columnApriM;

	    @FXML
	    private TableColumn<?, ?> columnStampaM;

	    private Paziente paziente;

	    public void logPaziente(Paziente paziente){
	    	this.paziente=paziente;
	    }

	    private DbConnection dc;

	    PreparedStatement preparedStatement;

	    public ObservableList<Prenotazione> listPrenotazioni = FXCollections.observableArrayList();

		public ObservableList<PrestazioneSanitaria> listPrestazioni = FXCollections.observableArrayList();

		public ObservableList<Prenotazione> listCartellaClinica=FXCollections.observableArrayList();
/*
 * Metodi Utili in tutte le schermate
 */
		@FXML
		void logout(ActionEvent event) {

		}


		/*
		 * funzioni utili nella schermata "consultazione Cartella Clinica"
		 */
	    @FXML
	    void clickControlloStorico(ActionEvent event) {
	    	try {
	    		System.out.println(paziente.getCodiceFiscale());
	    		CartellaClinicaCTRL ctrl= new CartellaClinicaCTRL();
				FXMLLoader loader = new	FXMLLoader();

				loader.setController(ctrl);
				ctrl.logPaziente(this.paziente);
				loader.setLocation(getClass().getResource("SchermataStoricoVisite.fxml"));
				Parent tableViewParent= loader.load();


  				Scene tableViewScene = new Scene (tableViewParent);
  				//accedo al controller e chiamo il metodo

				// indico le informazione dello Stage
				Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
				window.setScene(tableViewScene);
				window.show();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
	    }


	    @FXML
	    void clickStampaCartellaClinica(ActionEvent event) {
	    	try {

	    		CartellaClinicaCTRL ctrl= new CartellaClinicaCTRL();
				FXMLLoader loader = new	FXMLLoader();

				loader.setController(ctrl);
				ctrl.logPaziente(this.paziente);
				loader.setLocation(getClass().getResource("SchermataStampaCartellaClinica.fxml"));
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
	  	    void clickIndietroC(ActionEvent event) {
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
	    	 * funzioni utili nella schermata "Storico Visite"
	    	 */
	    	@FXML
	    	 void clickIndietroS(ActionEvent event) {
	    		  try {
	    			  CartellaClinicaCTRL ctrl= new CartellaClinicaCTRL();
	  					FXMLLoader loader = new	FXMLLoader();

	  					loader.setController(ctrl);
	  					ctrl.logPaziente(this.paziente);
	  					loader.setLocation(getClass().getResource("SchermataConsultazioneCartellaClinica.fxml"));
	  					Parent tableViewParent= loader.load();

	    				Scene tableViewScene = new Scene (tableViewParent);
	    				//accedo al controller e chiamo il metodo


	  				Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
	  				window.setScene(tableViewScene);
	  				window.show();
	  			} catch (Exception ex) {
	  				ex.printStackTrace();
	  			}
	  	    }

			/*
			 * funzioni utili nella schermata "Cartella Clinica"
			 */
	    	  @FXML
	    	    void clickIndietroCC(ActionEvent event) {
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

	    	  @FXML
	    	    void search(ActionEvent event) {
	    		  try {

	    			  paziente.setCodiceFiscale(txtCF.getText());
	    			  CartellaClinicaCTRL ctrl= new CartellaClinicaCTRL();
	  				FXMLLoader loader = new	FXMLLoader();

	  				loader.setController(ctrl);
	  				ctrl.logPaziente(paziente);
	  				loader.setLocation(getClass().getResource("SchermataStampaCartellaClinica.fxml"));
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
	    	   * funzioni utili nella schermata "Stampa Cartella Clinica"
	    	   */
	    	  @FXML
	    	    void clickIndietro(ActionEvent event) {
	    		  try {
	    			  CartellaClinicaCTRL ctrl= new CartellaClinicaCTRL();
		  				FXMLLoader loader = new	FXMLLoader();

		  				loader.setController(ctrl);
		  				ctrl.logPaziente(this.paziente);
		  				loader.setLocation(getClass().getResource("SchermataConsultazioneCartellaClinica.fxml"));
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
	    	    void clickStampa(ActionEvent event) {
	    		  Prenotazione pre=tableCartellaCLinicaP.getSelectionModel().getSelectedItem();
	    		  String visita=pre.getRefPrestazioneSanitaria();
	    		  LocalDate data=pre.getDataPrenotazione();

	    		  File dest=new File("C:/Users/Giuseppe/eclipse-workspace/AppGama/cartella clinica/"+paziente.getCognome()+" "+ paziente.getNome() +"/"+""+visita + " "+data+".pdf");
	    		  try {
					java.awt.Desktop.getDesktop().open(dest);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    	    }
	    	  /*
	    	   * funzioni utili nella schermata "Cartella Clinica Paziente"
	    	   */
	    	   @FXML
	    	    void clickIndietroM(ActionEvent event) {
	    		   try {
		  				Parent tableViewParent= FXMLLoader.load(getClass().getResource("SchermataSearchCartellaClinica.fxml"));
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


	   	    		String query = "SELECT codice_prenotazione,data_prenotazione,ref_prestazione_sanitaria,ref_turno,costo_prestazione,nome_ambulatorio from PRENOTAZIONE, PRESTAZIONE_SANITARIA,AMBULATORIO where ref_codice_paziente='"+paziente.getCodiceFiscale()+"' AND Prestazione_sanitaria.Nome_prestazione_sanitaria=Prenotazione.ref_prestazione_sanitaria and prestazione_sanitaria.ref_ambulatorio=ambulatorio.Codice_Ambulatorio";
	   	    		ResultSet rs = conn.prepareStatement(query).executeQuery();

	   	    		while (rs.next()){
	   	    			Prenotazione pre = new Prenotazione();
	   	    			//pre.setRefPrestazioneSanitaria(rs.getString(5));
	   	    			//String prest= pre.getRefPrestazioneSanitaria();
	   	    			listPrestazioni.add(new PrestazioneSanitaria(rs.getString(6),rs.getInt(5)));
	   	    			tablePrestazioni.setItems(listPrestazioni);
	   	    			listPrenotazioni.add(new Prenotazione(rs.getInt(1),rs.getDate(2).toLocalDate(), rs.getString(3), rs.getInt(4)));
	   	    			tablePrenotazioni.setItems(listPrenotazioni);
	   	    			System.out.println(listPrenotazioni.toString());
	   	    		}


	   	    			} catch(Exception ex) {
	   	    				ex.printStackTrace();
	   	    			}
	    		   // schermata controllo storico
	    		   	columnDataS.setCellValueFactory(new PropertyValueFactory<Prenotazione, LocalDate>("dataPrenotazione"));
	    			columnPrestazioneS.setCellValueFactory(new PropertyValueFactory<Prenotazione, String>("refPrestazioneSanitaria"));
	    			ColumnOrarioS.setCellValueFactory(new PropertyValueFactory<Prenotazione, Integer>("turnoPrenotazione"));
	    			ColumnCodS.setCellValueFactory(new PropertyValueFactory<Prenotazione, Integer>("codicePrenotazione"));

	    			//schermata stampa cartella clinica
	    			//ColimunVisitaP
	    			columnAmbS.setCellValueFactory(new PropertyValueFactory<PrestazioneSanitaria, String>("nomePrestazione"));
	    			columnCostoS.setCellValueFactory(new PropertyValueFactory<PrestazioneSanitaria, Integer>("costo"));

	    	   }
	    	   void loadCartellaClinica(){

	    		   try {
					Connection conn=dc.Connect();
					   String query="select ref_prestazione_sanitaria, data_prenotazione, cognome_medico from prenotazione, personale_medico, prestazione_sanitaria where prenotazione.Ref_Codice_Paziente='"+paziente.getCodiceFiscale()+"' and prenotazione.Ref_Prestazione_Sanitaria=prestazione_sanitaria.Nome_Prestazione_Sanitaria and prestazione_sanitaria.Ref_Ambulatorio=personale_medico.ref_ambulatorio";
					   ResultSet rs = conn.prepareStatement(query).executeQuery();

					   while(rs.next()){
						   String medico=rs.getString(3);
						   System.out.println(medico);
						   listCartellaClinica.add(new Prenotazione(rs.getString(1),rs.getString(3),rs.getDate(2).toLocalDate()));
						   tableCartellaCLinicaP.setItems(listCartellaClinica);
					   }
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    			ColimunVisitaP.setCellValueFactory(new PropertyValueFactory<Prenotazione, String>("refPrestazioneSanitaria"));
	    			columnDottoreP.setCellValueFactory(new PropertyValueFactory<Prenotazione, String>("refCodicePaziente"));
	    			columnDate.setCellValueFactory(new PropertyValueFactory<Prenotazione,LocalDate>("dataPrenotazione"));
	    	   }
	    	  /*
	    	   * (non-Javadoc)
	    	   * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
	    	   */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		dc=new DbConnection();
		this.loadPrenotazioni();
		this.logPaziente(paziente);
		this.loadCartellaClinica();

	}

}
