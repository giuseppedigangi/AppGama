package application.view;

import java.net.URL;
import java.util.ResourceBundle;

import application.model.Paziente;
import application.model.PersonaleMedico;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class HomeMedicoCTRL implements Initializable {


    @FXML
    private ImageView logo;

    @FXML
    private Label appBanner;

    @FXML
    private Text infoBanner;

    @FXML
    private Button buttonMemorizzaDatiVisita;

    @FXML
    private Button buttonConsultaCartellaClinicaPaziente;

    @FXML
    private Button buttonPrenotaNuovaPrestazioneSanitariaM;

    @FXML
    private Button buttonLogout;

    private PersonaleMedico medico;
    private Paziente paziente;

    public void logMedico(PersonaleMedico medico){
    	this.medico= medico;
    }
    public void logPaziente(Paziente paziente){
    	this.paziente=paziente;
    }


    @FXML
    void clickConsultaCartellaClinicaPaziente(ActionEvent event) {
    	try {
    		CartellaClinicaCTRL ctrl= new CartellaClinicaCTRL();
			FXMLLoader loader = new	FXMLLoader();

			loader.setController(ctrl);
			ctrl.logPaziente(this.paziente);
			loader.setLocation(getClass().getResource("SchermataSearchCartellaClinica.fxml"));
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
    void clickMemorizzaDatiVisita(ActionEvent event) {
    	try {
    		System.out.println(medico.getNome());

			FXMLLoader loader = new	FXMLLoader();
			loader.setLocation(getClass().getResource("SchermataMemorizzaDatiVisita.fxml"));
			Parent tableViewParent= loader.load();

			Scene tableViewScene = new Scene (tableViewParent);
			//accedo al controller e chiamo il metodo

			VisitaCTRL controller = loader.getController();
			controller.logMedico(medico);

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

    		PrenotazioneCTRL ctrl= new PrenotazioneCTRL();
			FXMLLoader loader = new	FXMLLoader();

			loader.setController(ctrl);
			ctrl.logMedico(this.medico);
			loader.setLocation(getClass().getResource("FormDatiPaziente.fxml"));
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