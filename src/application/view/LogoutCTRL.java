package application.view;

import java.io.IOException;
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
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.geometry.Pos;

public class LogoutCTRL implements Initializable {


	@FXML
    private Button buttonConferma;

    @FXML
    private Button buttonAnnulla;

    @FXML
    void clickAnnulla(ActionEvent event) throws IOException {
    	Stage stage =  (Stage) buttonAnnulla.getScene().getWindow();
    	stage.close();
    	try {
			Parent tableViewParent= FXMLLoader.load(getClass().getResource("SchermataPrincipale.fxml"));
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
    void clickConferma(ActionEvent event) {
    	try {
			Parent tableViewParent= FXMLLoader.load(getClass().getResource("SchermataPrincipale.fxml"));
			Scene tableViewScene = new Scene (tableViewParent);
			// indico le informazione dello Stage
			Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
			window.setScene(tableViewScene);
			window.show();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}


	}
