package application.view;

import java.net.URL;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ResourceBundle;

import javafx.fxml.Initializable;

public class HomeAmministrativoCTRL implements Initializable {
	 private static final String TicketCTRL = null;

	 	@FXML
	    private ImageView logo;

	    @FXML
	    private Label appBanner;

	    @FXML
	    private Text infoBanner;
	    @FXML
	    private Button buttonLogout;

	   /*
	    * oggetti FXML schermata Home Amministrativo
	    */
	    @FXML
	    private Button buttonGestionePersonale;

	    @FXML
	    private Button buttonCaricaFattura;

	    @FXML
	    private Button buttonModificaPrenotazioneSanitaria;

	    @FXML
	    private Button buttonPrenotaNuovaPrestazioneSanitaria;

	    @FXML
	    private Button buttonGestioneTurnazione;



	    GestionePersonaleCTRL personale = new GestionePersonaleCTRL();

	    @FXML
	    void clickCaricaFattura(ActionEvent event) {
	    	try {
	    		 FXMLLoader loader = new FXMLLoader(getClass().getResource("SchermataVisiteGiornoCorrente.fxml"));
				Parent tableViewParent = loader.load();
				loader.setController(personale);
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
	    void clickGestionePersonale(ActionEvent event) {
	    	try {
				Parent tableViewParent= FXMLLoader.load(getClass().getResource("SchermataGestionePersonaleOspedaliero.fxml"));
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
	    void clickModificaPrenotazioneSanitaria(ActionEvent event) {
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

	    @FXML
	    void clickPrenotaNuovaPrestazioneSanitaria(ActionEvent event) {
	    	try {
	    		PrenotazioneCTRL ctrl=new PrenotazioneCTRL();
				FXMLLoader loader = new	FXMLLoader();
				loader.setLocation(getClass().getResource("FormDatiPrenotazione.fxml"));
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
	    void clickGestioneTurnazione(ActionEvent event) {
	    	try {
				Parent tableViewParent= FXMLLoader.load(getClass().getResource("SchermataGestioneTurnazione.fxml"));
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
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

}
