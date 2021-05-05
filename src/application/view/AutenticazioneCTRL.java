package application.view;


import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.ResourceBundle;
import com.mysql.*;
import application.controller.DbConnection;
import application.controller.SendMail;
import application.model.Paziente;
import application.model.PersonaleAmministrativo;
import application.model.PersonaleMedico;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;


public class AutenticazioneCTRL implements Initializable {
	@FXML
    private GridPane SchermataPrincipale;

    @FXML
    private ImageView logo;

    @FXML
    private Label appBanner;

    @FXML
    private Text infoBanner;

    @FXML
    private Button buttonLoginPaziente;

    @FXML
    private Button buttonLoginMedico;

    @FXML
    private Button buttonLoginAmministrativo;
    /*
     * Oggetti FXML della schermata "FormLoginPaziente"
     */
    @FXML
    private TextField campoCFPaziente;

    @FXML
    private TextField campoNumeroTessera;

    /*
     * "Oggetti FXML dela schermata "FormLoginMedico"
     */

    @FXML
    private SplitPane formLoginMedico;

    @FXML
    private TextField campoUsernameMedico;

    @FXML
    private PasswordField campoPassword;

    @FXML
    private Button buttonOk;

    /*
     * "Oggetti FXML dela schermata "FormLoginamministrativp"
     */
    @FXML
    private TextField txtUsernameAmministrativo;

    @FXML
    private PasswordField txtPassword;


    private DbConnection dc;

 PreparedStatement preparedStatement;


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		dc=new DbConnection();
	}
	@FXML
	public void clickMedico(ActionEvent event) throws IOException{
		try{
		Parent tableViewParent= FXMLLoader.load(getClass().getResource("FormLoginMedico.fxml"));
		Scene tableViewScene = new Scene (tableViewParent);
		// indico le informazione dello Stage
		Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
		window.setScene(tableViewScene);
		window.show();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	@FXML
	public void clickPaziente(ActionEvent event) throws IOException {
		try {
			Parent tableViewParent= FXMLLoader.load(getClass().getResource("FormLoginPaziente.fxml"));
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
	public void clickAmministrativo(ActionEvent event){
		try {

			 Parent tableViewParent= FXMLLoader.load(getClass().getResource("FormLoginAmministrativo.fxml"));
				Scene tableViewScene = new Scene (tableViewParent);
				// indico le informazione dello Stage
				Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
				window.setScene(tableViewScene);
				window.show();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/* Metodi utili
	  * nella schermata
	  * FormLoginPaziente
	  */
	 @FXML
	    void clickLoginPaziente(ActionEvent event) {
		 try {
			 Connection conn= dc.Connect();

			String query = "SELECT * FROM PAZIENTE WHERE CODICE_FISCALE = '" + campoCFPaziente.getText().toUpperCase() + "'  AND NUMERO_TESSERA= '" + campoNumeroTessera.getText()+"'";
			System.err.printf("The con variable is not null: %b%n", conn);
			ResultSet resultSet = conn.prepareStatement(query).executeQuery();
			//preparedStatement = conn.prepareStatement(query);
			//boolean stato = preparedStatement.execute();

			if (resultSet.next()){
				Paziente paziente=new Paziente();
				paziente.setCodiceFiscale(resultSet.getString("Codice_Fiscale"));
				paziente.setCognome(resultSet.getString(4));
				paziente.setNome(resultSet.getString(3));

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

			} else{
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


	/* Metodi utili
	 * nella schermata
	 * FormLoginMedico
	 *
	 */
	 @FXML
	    void clickLoginMedico(ActionEvent event) {
		 try {
			 Connection conn= dc.Connect();

			 	String query = "SELECT * FROM PERSONALE_MEDICO WHERE USERNAME_MEDICO = '" + campoUsernameMedico.getText() + "' AND Password_Medico= '" + campoPassword.getText()+"'";
				ResultSet resultSet = conn.prepareStatement(query).executeQuery();

				if (resultSet.next()){
					PersonaleMedico medico = new PersonaleMedico();
					Paziente paziente=new Paziente();
					medico.setUsername(resultSet.getString("username_medico"));
					medico.setNome(resultSet.getNString("nome_medico"));
					medico.setCognome(resultSet.getString("cognome_medico"));
					paziente.setNome(resultSet.getString("nome_medico"));
					medico.setCodAmbulatorio(resultSet.getInt(8));


				FXMLLoader loader = new	FXMLLoader();
				loader.setLocation(getClass().getResource("SchermataHomeMedico.fxml"));
				Parent tableViewParent= loader.load();

				Scene tableViewScene = new Scene (tableViewParent);
				//accedo al controller e chiamo il metodo

				HomeMedicoCTRL controller = loader.getController();
				controller.logMedico(medico);
				controller.logPaziente(paziente);

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

	 /* Metodi utili
	  * nella schermata
	  * FormLoginAmministrativo
	  */

	 @FXML
	    void clickLoginAmministrativo(ActionEvent event) {
		 try {
			 Connection conn= dc.Connect();

				String query = "SELECT * FROM PERSONALE_AMMINISTRATIVO WHERE USERNAME_AMMINISTRATIVO = '" + txtUsernameAmministrativo.getText() + "'  AND PASSWORD_AMMINISTRATIVO = '" + txtPassword.getText()+"'";

				ResultSet resultSet = conn.prepareStatement(query).executeQuery();

				if (resultSet.next()){
					PersonaleAmministrativo amministrativo = new PersonaleAmministrativo();
					amministrativo.setUsernameAmministrativo(resultSet.getString("username_amministrativo"));
					amministrativo.setNomeAmministrativo(resultSet.getString("username_amministrativo"));
					amministrativo.setCognomeAmministrativo(resultSet.getString("username_amministrativo"));

					FXMLLoader loader = new	FXMLLoader();
					loader.setLocation(getClass().getResource("SchermataHomeAmministrativo.fxml"));
					Parent tableViewParent= loader.load();


				Scene tableViewScene = new Scene (tableViewParent);
				// indico le informazione dello Stage
				Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
				window.setScene(tableViewScene);
				window.show();



				LocalDate ld=LocalDate.now();
				ld.plusDays(2);
		    	DateTimeFormatter form=DateTimeFormatter.ofPattern("yyyy-MM-dd");
		    	String data=ld.format(form);
		    	System.out.println(data);
				String query1="SELECT nome_paziente,cognome_paziente,email_paziente,data_prenotazione FROM paziente, prenotazione WHERE paziente.codice_fiscale=prenotazione.ref_codice_paziente AND prenotazione.data_prenotazione='"+data+"'";

				ResultSet rs = conn.prepareStatement(query1).executeQuery();
					while(rs.next()){
					SendMail a=new SendMail();
					a.inviaMail(rs.getString(3), rs.getDate(4).toString(), rs.getString(1), rs.getString(2));
					}
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

}
