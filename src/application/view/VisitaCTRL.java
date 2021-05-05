package application.view;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.Optional;
import java.util.ResourceBundle;



import application.controller.DbConnection;
import application.controller.MemorizzaDatiVisitaPdf;
import application.model.PersonaleMedico;
import application.model.Ticket;
import javafx.fxml.Initializable;

public class VisitaCTRL implements Initializable {
	@FXML
    private ImageView logo;

    @FXML
    private Label appBanner;

    @FXML
    private Text infoBanner;

    @FXML
    private Button buttonLogout;
    @FXML
    private Button buttonIndietro;

   /*
    * Oggetti utili nella schermata " Memorizza Dati visita "
    */
    @FXML
    private TextArea formVisita;

    @FXML
    private TextField txtCodicePrenotazione;

    @FXML
    private TextField txtCF;

    @FXML
    private Button buttonSalva;

    @FXML
    private DatePicker scegliData;

    @FXML
    private TextArea textPrestazione;
/*
 * oggetti FXML schermata "visite giorno corrente"
 */
    @FXML
    private TableView<Ticket> tableTicket= new TableView<Ticket>() ;

    @FXML
    private TableColumn<Ticket, String> columnCF = new TableColumn<Ticket,String>("Codice fiscale");

    @FXML
    private TableColumn<Ticket, Integer> columnCodPre = new TableColumn<Ticket,Integer>("Codice Prenotazione");

    @FXML
    private TableColumn<Ticket, Boolean> columnPagato = new TableColumn<Ticket,Boolean>("Pagato");

    @FXML
    private Button buttonConfermaPagamento;

    private DbConnection dc;

    PreparedStatement preparedStatement;

    public ObservableList<Ticket> listTicket=FXCollections.observableArrayList();

/*
 * metodi generali
 */
    private PersonaleMedico medico;

    public void logMedico(PersonaleMedico medico){
    	this.medico= medico;
    }

	/* PersonaleMedico dottore;

	    void setDottore (PersonaleMedico dott) {
	    	dottore = new PersonaleMedico(dott);
	    	System.out.println(dottore.getNome() + " " + dottore.getCognome());
	    }
	    public PersonaleMedico getPersonaleMedico() {
	    	return this.dottore;
	    }*/

    @FXML
    void logout(ActionEvent event) {
    	try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SchermataLogout.fxml"));
			Parent root1 = (Parent) fxmlLoader.load();
			Stage stage=new Stage();
			stage.setScene(new Scene(root1));
			stage.setTitle("LOGOUT");
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.showAndWait();


		} catch (Exception ex) {
			ex.printStackTrace();
		}
    }
/*
 * funzioni utili nella schermata "Memorizza dati Visita"
 */
    @FXML
    void clickSalva(ActionEvent event) throws IOException {
    	Connection conn= dc.Connect();
    	Alert alert = new Alert(AlertType.CONFIRMATION);
    	alert.setTitle("Finestra Conferma");
    	alert.setHeaderText("Sei sicuro di continuare?");
    	alert.setContentText("Are you ok with this?");

    	Optional<ButtonType> result = alert.showAndWait();
    	if (result.get() == ButtonType.OK){

    	    // ... user chose OK
try{
    		String query = "SELECT codice_fiscale, nome_paziente, cognome_paziente"
    				+ " FROM paziente"
    				+"  WHERE CODICE_FISCALE = " + "'" + txtCF.getText() +"'";
    		System.err.printf("The con variable is not null: %b%n", conn);
			ResultSet resultSet = conn.prepareStatement(query).executeQuery();
    		MemorizzaDatiVisitaPdf pdf = new MemorizzaDatiVisitaPdf();
    		pdf.setCognomeDottore(medico.getCognome());
    		pdf.setNomeDottore(medico.getNome());
    		pdf.setDataOggi(scegliData.getValue());
    		pdf.setResoconto(formVisita.getText());
    		pdf.setNomeVisita(textPrestazione.getText());
    		if(resultSet.next()) {
    			pdf.setCfPaziente(resultSet.getString(1));
        		pdf.setNomePaziente(resultSet.getString(2));
        		pdf.setCognomePaziente(resultSet.getString(3));

    		}

    		//

    		pdf.memorizzaDatiVisitaPdf();
    		java.awt.Desktop.getDesktop().open(pdf.getDest());
}catch(SQLException e){

}
    	} else {
    	    // ... user chose CANCEL or closed the dialog
    	}


    }

    @FXML
    void clickIndietroM(ActionEvent event) {
    	try {

			FXMLLoader loader = new	FXMLLoader();
			loader.setLocation(getClass().getResource("SchermataHomeMedico.fxml"));
			Parent tableViewParent= loader.load();

			Scene tableViewScene = new Scene (tableViewParent);
			//accedo al controller e chiamo il metodo

			HomeMedicoCTRL controller = loader.getController();
			controller.logMedico(medico);
			// indico le informazione dello Stage
			Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
			window.setScene(tableViewScene);
			window.show();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
    }

    /*
     * funzioni utili nella schermata "visite giorno corrente"
     */
    @FXML
    void clickIndietro(ActionEvent event) {
    	try {
			Parent tableViewParent= FXMLLoader.load(getClass().getResource("SchermataHomeAmministrativo.fxml"));
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
    void clickConfermaPagamento(ActionEvent event) {
    	Connection conn=dc.Connect();
    	Ticket tick = tableTicket.getSelectionModel().getSelectedItem();
    	Alert alert = new Alert(AlertType.CONFIRMATION);
    	alert.setTitle("Finestra di Conferma");
    	alert.setHeaderText("Prenotazione giorno selezionato");
    	alert.setContentText("Sei sicuro di procedere?");

    	ButtonType buttonTypeSi = new ButtonType("SI");
    	ButtonType buttonTypeCancel = new ButtonType("NO", ButtonData.CANCEL_CLOSE);

    	alert.getButtonTypes().setAll(buttonTypeSi, buttonTypeCancel);
    	Optional<ButtonType> result = alert.showAndWait();
    	if (result.get() == buttonTypeSi){
    	try {
			PreparedStatement pst =conn.prepareStatement("UPDATE TICKET SET Conferma_pagamento='"+1+"'");
			pst.executeUpdate();
			pst.close();
		} catch (SQLException e) {
			Alert alert2 = new Alert(AlertType.WARNING);
			alert2.setTitle("Finestra Notifica");
			alert2.setHeaderText("Manipolazione Dati DBMS fallita");
			alert2.setContentText("");
			alert2.showAndWait();
			e.printStackTrace();
		}

		listTicket.clear();
    	listTicket.clear();
    	}
    }

    public void loadTicket(){
    	Connection conn=dc.Connect();
    	LocalDate ld=LocalDate.now();
    	DateTimeFormatter form=DateTimeFormatter.ofPattern("yyyy-MM-dd");
    	String giorno=ld.format(form);

    	try {
			String query="SELECT ref_codice_prenotazione,ref_codice_fiscale_paziente,conferma_pagamento "
					+ "FROM ticket, prenotazione where ticket.ref_codice_prenotazione=prenotazione.codice_prenotazione AND prenotazione.data_prenotazione='2019-02-07'";
			ResultSet rs=conn.prepareStatement(query).executeQuery();
			while(rs.next()){
				listTicket.add(new Ticket(rs.getInt(1),rs.getString(2),rs.getBoolean(3)));
				tableTicket.setItems(listTicket);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
    	columnCF.setCellValueFactory(new PropertyValueFactory<Ticket,String>("ref_codiceFiscalePaziente"));
    	columnCodPre.setCellValueFactory(new PropertyValueFactory<Ticket,Integer>("ref_codicePrenotazione"));
    	columnPagato.setCellValueFactory(new PropertyValueFactory<Ticket,Boolean>("confermaPagamento"));
    }
    /*
     * (non-Javadoc)
     * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
     */

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		dc=new DbConnection();
		this.loadTicket();

	}

}
