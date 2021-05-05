package application.view;


import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
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
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import application.controller.DbConnection;
import application.controller.FinestraConferma;
import application.model.PersonaleAmministrativo;
import application.model.PersonaleMedico;

public class GestionePersonaleCTRL implements Initializable {

	   @FXML
	    private ImageView logo;

	    @FXML
	    private Label appBanner;

	    @FXML
	    private Text infoBanner;
/*
 * oggetti FXML schermata gestione personale ospedaliero
 */
	    @FXML
	    private Button buttonRegistraPersonaleMedico;

	    @FXML
	    private Button buttonRegistraPersonaleAmministrativo;

	    @FXML
	    private Button buttonEliminaAccount;

	    /*
	     * oggetti FXML "schermata ELimina Account"
	     *
	     */
	    @FXML
	    private TableView<PersonaleAmministrativo> tableAmministrativo = new TableView<PersonaleAmministrativo>();

	    @FXML
	    private TableColumn<PersonaleAmministrativo, String> nomeAmm=new TableColumn<PersonaleAmministrativo, String>("Nome Amministrativo");

	    @FXML
	    private TableColumn<PersonaleAmministrativo, String> cognomeAmm = new TableColumn<PersonaleAmministrativo, String>("Cognome Amministrativo");

	    @FXML
	    private TableColumn<PersonaleAmministrativo, String> nickAmm = new TableColumn<PersonaleAmministrativo, String>("Nickname");

	    @FXML
	    private TableView<PersonaleMedico> tableMedico= new TableView<PersonaleMedico>();

	    @FXML
	    private TableColumn<PersonaleMedico, String> nomeMed = new TableColumn <PersonaleMedico,String>("Nome Medico");

	    @FXML
	    private TableColumn<PersonaleMedico, String> cognomeMed =new TableColumn <PersonaleMedico,String>("Cognome Medico");;

	    @FXML
	    private TableColumn<PersonaleMedico, String> nickMed = new TableColumn <PersonaleMedico,String>("Nickname");

	    @FXML
	    private Button buttonEliminaAmministrativo;

	    @FXML
	    private TextField txtAmministrativo;

	    @FXML
	    private TextField txtMedico;

	    @FXML
	    private Button buttonEliminaMedico;


	    /*
	     * Oggetti FXML schermata "Form Dati Amministrativo"
	     */
	    @FXML
	    private TextField txtNomeAMM;

	    @FXML
	    private Text txtCognAMM;

	    @FXML
	    private TextField txtCellulareAMM;

	    @FXML
	    private DatePicker data;

	    @FXML
	    private TextField dataNascitaAMM;

	    @FXML
	    private PasswordField txtPasswordAMM;

	    @FXML
	    private Button buttonConfermaAMM;

	    @FXML
	    private TextField txtUsernameAMM;

	    @FXML
	    private Button buttonLogout;

	    /*
	     * Oggetti FXML schermata "Form Dati Medico"
	     */

	    @FXML
	    private TextField txtNomeMED;

	    @FXML
	    private TextField txtCognomeMED;

	    @FXML
	    private TextField txtCellulareMED;

	    @FXML
	    private TextField txtEmailMED;

	    @FXML
	    private PasswordField txtPasswordMED;

	    @FXML
	    private Button buttonConfermaMED;

	    @FXML
	    private TextField txtUsernameMED;

	    @FXML
	    private RadioButton radioPsi;

	    @FXML
	    private ToggleGroup ambulatorio;

	    @FXML
	    private RadioButton radioTra;

	    @FXML
	    private RadioButton radioEnd;

	    @FXML
	    private RadioButton radioFisRe;

	    @FXML
	    private RadioButton radioAll;

	    @FXML
	    private RadioButton radioDia;

	    @FXML
	    private RadioButton radioIst;

	    @FXML
	    private RadioButton radioFis;

	    @FXML
	    private RadioButton radioCard;

	    @FXML
	    private RadioButton radioErgo;

	    @FXML
	    private RadioButton radioMed;

	    @FXML
	    private RadioButton radioNeu;


	    @FXML
	    private Button buttonIndietro;


	    private DbConnection dc;

	    PreparedStatement preparedStatement;


	    @FXML
	    void logout(ActionEvent event) {

	    }

	    @FXML
	    void clickIndietroG(ActionEvent event) {
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
	    public ObservableList<PersonaleAmministrativo> listAmministrativo = FXCollections.observableArrayList();

	   public ObservableList<PersonaleMedico> listMedico = FXCollections.observableArrayList();

/*
 * funzioni utili schermat gestione personale ospedaliero
 */
	    @FXML
	    void clickEliminaAccount(ActionEvent event) {
	    	try {

	    		Parent tableViewParent= FXMLLoader.load(getClass().getResource("SchermataELiminaAccount.fxml"));
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
	    void clickRegistraPersonaleAmministrativo(ActionEvent event) {
	    	try {
				Parent tableViewParent= FXMLLoader.load(getClass().getResource("FormDatiAmministrativo.fxml"));
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
	    void clickRegistraPersonaleMedico(ActionEvent event) {
	    	try {
				Parent tableViewParent= FXMLLoader.load(getClass().getResource("FormDatiMedico.fxml"));
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
	     * funzioni utili nella schermata elimina Account
	     */

	    @FXML
	    void clickEliminaAmministrativo(ActionEvent event) {
	    	 Connection conn= dc.Connect();


	    	Alert alert = new Alert(AlertType.CONFIRMATION);
	    	alert.setTitle("Finestra di Conferma");
	    	alert.setHeaderText("Stai eliminando definitivamente questo Account");
	    	alert.setContentText("Sei sicuro di procedere?");

	    	ButtonType buttonTypeSi = new ButtonType("SI");
	    	ButtonType buttonTypeCancel = new ButtonType("NO", ButtonData.CANCEL_CLOSE);

	    	alert.getButtonTypes().setAll(buttonTypeSi, buttonTypeCancel);
	    	Optional<ButtonType> result = alert.showAndWait();
	    	if (result.get() == buttonTypeSi){
	    		//String query = "SELECT * FROM PERSONALE_MEDICO WHERE USERNAME_MEDICO = '" + campoUsernameMedico.getText() + "' AND Password_Medico= '" + campoPassword.getText()+"'";
				//ResultSet resultSet = conn.prepareStatement(query).executeQuery();
				//if (resultSet.next())

          PersonaleAmministrativo u = tableAmministrativo.getSelectionModel().getSelectedItem();
         // txtAmministrativo.setText(u.getUsernameAmministrativo());
          System.out.println(u.getUsernameAmministrativo());

try {
	PreparedStatement pst = conn.prepareStatement("DELETE FROM PERSONALE_AMMINISTRATIVO WHERE username_Amministrativo='"+u.getUsernameAmministrativo().toString()+"'");
	pst.execute();
	tableAmministrativo.getItems().removeAll(tableAmministrativo.getSelectionModel().getSelectedItem());
} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
		}
	    	} else {
	    	    // ... user chose CANCEL or closed the dialog
	    	}


	    }

	    @FXML
	    void clickEliminaMedico(ActionEvent event) {
	    	Connection conn = dc.Connect();
	    	Alert alert = new Alert(AlertType.CONFIRMATION);
	    	alert.setTitle("Finestra di Conferma");
	    	alert.setHeaderText("Stai eliminando definitivamente questo Account");
	    	alert.setContentText("Sei sicuro di procedere?");

	    	ButtonType buttonTypeSi = new ButtonType("SI");
	    	ButtonType buttonTypeCancel = new ButtonType("NO", ButtonData.CANCEL_CLOSE);

	    	alert.getButtonTypes().setAll(buttonTypeSi, buttonTypeCancel);
	    	Optional<ButtonType> result = alert.showAndWait();
	    	if (result.get() == buttonTypeSi){
	    		PersonaleMedico m=tableMedico.getSelectionModel().getSelectedItem();

	    		try {
	    			PreparedStatement pst = conn.prepareStatement("DELETE FROM PERSONALE_MEDICO WHERE username_medico ='"+m.getUsername().toString()+"'");
	    			pst.execute();
	    			tableMedico.getItems().removeAll(tableMedico.getSelectionModel().getSelectedItem());
	    		} catch (SQLException e) {
	    			// TODO Auto-generated catch block
	    			e.printStackTrace();
	    				}
	    	} else {
	    	    // ... user chose CANCEL or closed the dialog
	    	}
	    }

	    @FXML
	    void clickIndietroE(ActionEvent event) {
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
	    /*
	     * funzioni utili in "Form Dati AMministrativo"
	     */
	    @FXML
	    void clickConfermaAMM(ActionEvent event) {
	    	 Connection conn= dc.Connect();

				String nome = txtNomeAMM.getText();
				String cognome = txtCognAMM.getText();
				String username = txtUsernameAMM.getText();
				String password = txtPasswordAMM.getText();
				String cellulare = txtCellulareAMM.getText();
				Date nascita = Date.valueOf(data.getValue());

		String query = "INSERT INTO PERSONALE_AMMINISTRATIVO (USERNAME_AMMINISTRATIVO, PASSWORD_AMMINISTRATIVO,NOME_AMMINISTRATIVO,COGNOME_AMMINISTRATIVO,DATA_NASCITA_AMMINISTRATIVO,CELLULARE_AMMINISTRATIVO)"
				+ "		VALUES (?,?,?,?,?,?)";
		PreparedStatement pst;
			try {
				pst = conn.prepareStatement(query);
				pst.setString(1, username);
				pst.setString(2, password);
				pst.setString(3,nome);
				pst.setString(4, cognome);
				pst.setDate(5, nascita);
				pst.setString(6, cellulare);
				pst.execute();


					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("Finestra Notifica");
					alert.setHeaderText("Personale Amministrativo aggiunto Correttamente");
					alert.setContentText("COMPLIMENTI!!");

					alert.showAndWait();

					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}



}
	    @FXML
	    void clickIndietroA(ActionEvent event) {
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

	    /*
	     * funzioni utili in "Form Dati Medico"
	     */
	    @FXML
	    void clickConfermaMED(ActionEvent event) {
	    	Connection conn= dc.Connect();

			String nome = txtNomeMED.getText();
			String cognome = txtCognomeMED.getText();
			String username = txtUsernameMED.getText();
			String password = txtPasswordMED.getText();
			String cellulare = txtCellulareMED.getText();
			String mail = txtEmailMED.getText();


	String query = "INSERT INTO PERSONALE_MEDICO (USERNAME_MEDICO,NOME_MEDICO,COGNOME_MEDICO, PASSWORD_MEDICO,EMAIL_MEDICO,CELLULARE_MEDICO, REF_AMBULATORIO)"
			+ "		VALUES (?,?,?,?,?,?,?)";
	PreparedStatement pst;
		try {
			pst = conn.prepareStatement(query);
			pst.setString(1, username);
			pst.setString(2,nome);
			pst.setString(3, cognome);
			pst.setString(4, password);
			pst.setString(5, mail);
			pst.setString(6, cellulare);
			pst.setInt(7, this.amb);
			pst.execute();


				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Finestra Notifica");
				alert.setHeaderText("Personale Medico aggiunto Correttamente");
				alert.setContentText("COMPLIMENTI!!");

				alert.showAndWait();

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	    }
	    	int amb;
	    @FXML
	    void radioSelect(ActionEvent event) {
	    	if(radioMed.isSelected()){
	    		amb=1;

	    	}
	    	if(radioFis.isSelected()){
	    		amb=2;
	    	}
	    	if(radioPsi.isSelected()){
	    		amb=3;
	    	}
	    	if(radioTra.isSelected()){
	    		amb=4;
	    	}
	    	if(radioNeu.isSelected()){
	    		amb=5;
	    	}
	    	if(radioEnd.isSelected()){
	    		amb=6;
	    	}
	    	if(radioFisRe.isSelected()){
	    		amb=7;
	    	}
	    	if(radioAll.isSelected()){
	    		amb=8;
	    	}
	    	if(radioDia.isSelected()){
	    		amb=9;
	    	}
	    	if(radioIst.isSelected()){
	    		amb=10;
	    	}
	    	if(radioCard.isSelected()){
	    		amb=11;
	    	}
	    	if(radioErgo.isSelected()){
	    		amb=12;
	    	}
	    }
	    @FXML
	    void clickIndietroM(ActionEvent event) {
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


/*
 * connesione DB
 */
	    public void loadDataMedico (){
	    	try{
	    		Connection conn = dc.Connect();


	    		String query = "SELECT * from PERSONALE_MEDICO";
	    		ResultSet rs = conn.prepareStatement(query).executeQuery();
	    		while (rs.next()){
	    			PersonaleMedico med = new PersonaleMedico();

	    			listMedico.add(new PersonaleMedico(rs.getString(2).toString(), rs.getString(3).toString(), rs.getString(4).toString()));
	    			//System.out.println(listMedico);
	    			tableMedico.setItems(listMedico);
	    		}
	    			} catch(Exception ex) {
	    				ex.printStackTrace();
	    			}
	    	nomeMed.setCellValueFactory(new PropertyValueFactory<PersonaleMedico, String>("nome"));
	    	cognomeMed.setCellValueFactory(new PropertyValueFactory<PersonaleMedico, String>("cognome"));
	    	nickMed.setCellValueFactory(new PropertyValueFactory<PersonaleMedico, String>("username"));
	    	tableMedico.setItems(null);
	    	tableMedico.setItems(listMedico);
	    }

	    public void loadDataAmministrativo(){
	    	try{
	    		Connection conn = dc.Connect();
	    		System.err.printf("The con variable is not null: %b%n", conn);
	    		//tableAmministrativo.getColumns().addAll(nomeAmm,cognomeAmm,nickAmm);

	    		String query = "SELECT * from PERSONALE_AMMINISTRATIVO";
	    		ResultSet rs = conn.prepareStatement(query).executeQuery();
	    		while (rs.next()){
	    			PersonaleAmministrativo am = new PersonaleAmministrativo();
	    			/*am.setUsernameAmministrativo(rs.getString(1));
	    			am.setNomeAmministrativo(rs.getString(3));
	    			am.setCognomeAmministrativo(rs.getString(4));
	    			//System.out.println(am.getUsernameAmministrativo() + am.getCognomeAmministrativo()+ am.getNomeAmministrativo());
*/
	    			listAmministrativo.add(new PersonaleAmministrativo(rs.getString(1).toString(), rs.getString(3).toString(), rs.getString(4).toString()));
	    			tableAmministrativo.setItems(listAmministrativo);
	    		}
	    			} catch(Exception ex) {
	    				ex.printStackTrace();
	    			}
	    	nomeAmm.setCellValueFactory(new PropertyValueFactory<PersonaleAmministrativo, String>("nomeAmministrativo"));
	    	cognomeAmm.setCellValueFactory(new PropertyValueFactory<PersonaleAmministrativo, String>("cognomeAmministrativo"));
	    	nickAmm.setCellValueFactory(new PropertyValueFactory<PersonaleAmministrativo, String>("usernameAmministrativo"));
	    	tableAmministrativo.setItems(null);
	    	tableAmministrativo.setItems(listAmministrativo);
	    		}


	    /*

	     * (non-Javadoc)
	     * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
	     */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		dc=new DbConnection();
		this.loadDataMedico();
		this.loadDataAmministrativo();
	}

}
