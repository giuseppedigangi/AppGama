package application.view;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import application.controller.DbConnection;
import application.model.PersonaleAmministrativo;
import application.model.PersonaleMedico;
import application.model.Turno;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.scene.control.TableCell;
import javafx.util.Callback;
public class TurnoCTRL implements Initializable {

	 	private DbConnection dc;

	    PreparedStatement preparedStatement;

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
	     * Oggetti FXML schermata "gestione turnazione"
	     */
	    @FXML
	    private Button buttonAggiungi;
	    @FXML
	    private Button buttonModifica;

	    @FXML
	    private MenuButton menuAmbulatorio;

	    @FXML
	    private TableView<Turno> tableTurni = new TableView<Turno>();

	    @FXML
	    private TableColumn<Turno, Integer> columnCod = new TableColumn<Turno, Integer>("Codice Turno");

	    @FXML
	    private TableColumn<Turno, Date> columnData =new TableColumn<Turno, Date>("Data");

	    @FXML
	    private TableColumn<Turno, Integer> columnOra =new TableColumn<Turno, Integer>("Orario");

	    @FXML
	    private TableColumn<Turno, Integer> columnAmbulatorio =new TableColumn<Turno, Integer>("Ambulatorio");

	    @FXML
	    private TableColumn<Turno, Integer> columnMedico =new TableColumn<Turno, Integer>("Medico");

	    @FXML
	    private TableColumn<Turno, Button> columnModifica =new TableColumn<Turno, Button>("Modifica");

	    @FXML
	    private TableView<PersonaleMedico> tableDottori= new TableView<PersonaleMedico>();

	    @FXML
	    private TableColumn<PersonaleMedico, String> columnNome= new TableColumn<PersonaleMedico, String>("Nome") ;

	    @FXML
	    private TableColumn<PersonaleMedico, String> columnCognome = new TableColumn<PersonaleMedico, String>("Cognome") ;

	    @FXML
	    private TableColumn<PersonaleMedico, Integer> columnCodice = new TableColumn<PersonaleMedico, Integer>("Cod") ;


	 public ObservableList<Turno> listTurni = FXCollections.observableArrayList();
	 public ObservableList<PersonaleMedico> listDottori = FXCollections.observableArrayList();


	 /*
     * funzioni utili nella schermata "Gestione TURNAZIONE"
     */
    @FXML
    void clickIndietroT(ActionEvent event) {
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
    void loadMedicinaGenerale(ActionEvent event) {

    	listTurni.clear();
    	listDottori.clear();
    	try{
    		Connection conn = dc.Connect();


    		String query = "SELECT * from TURNO where Ref_Ambulatorio='1'";
    		ResultSet rs = conn.prepareStatement(query).executeQuery();
    		while (rs.next()){
    			Turno med = new Turno();

    			listTurni.add(new Turno(rs.getInt(2),rs.getInt(1), rs.getInt(3), rs.getDate(4).toLocalDate(), rs.getInt(6)));


    		}
    			} catch(Exception ex) {
    				ex.printStackTrace();
    			}
    	tableTurni.setItems(null);
    	tableTurni.setItems(listTurni);

    	//inserire riempimento tabella dottori
    	try{
    		Connection conn = dc.Connect();


    		String query = "SELECT * from Personale_Medico where Ref_Ambulatorio='1'";
    		ResultSet rs = conn.prepareStatement(query).executeQuery();
    		while (rs.next()){
    			PersonaleMedico med = new PersonaleMedico();

    			listDottori.add(new PersonaleMedico(rs.getInt(1),rs.getString(3), rs.getString(4)));


    		}
    			} catch(Exception ex) {
    				ex.printStackTrace();
    			}
    	tableDottori.setItems(null);
    	tableDottori.setItems(listDottori);

    }
Turno t;
    @FXML
    void clickModifica(ActionEvent event) {
    	Connection conn= dc.Connect();
    	this.t= tableTurni.getSelectionModel().getSelectedItem();

    	t.getOrario();
    	System.out.println(t);

    }

    @FXML
    void clickAggiungi(ActionEvent event) {

    	Connection conn=dc.Connect();
		PersonaleMedico med = tableDottori.getSelectionModel().getSelectedItem();
System.out.println(med.getCodiceMedico());
System.out.println(t.getCodTurno());

		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Finestra Notifica");
		alert.setHeaderText("Turno Modificato Correttamente");
		alert.setContentText("COMPLIMENTI!!");
		alert.showAndWait();
String codM=String.valueOf(med.getCodiceMedico());
String codT=String.valueOf(t.getCodTurno());
		try {
			PreparedStatement pst =conn.prepareStatement("UPDATE TURNO SET Ref_medico='" +codM+"'"
					+ "where Codice_Turno='"+codT+"'");
			pst.executeUpdate();
			pst.close();

			listTurni.clear();
	    	listDottori.clear();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }


    @FXML
    void loadAllergologia(ActionEvent event) {
    	listTurni.clear();
    	listDottori.clear();
    	try{
    		Connection conn = dc.Connect();


    		String query = "SELECT * from TURNO where Ref_Ambulatorio='8'";
    		ResultSet rs = conn.prepareStatement(query).executeQuery();
    		while (rs.next()){
    			Turno med = new Turno();

    			listTurni.add(new Turno(rs.getInt(2),rs.getInt(1), rs.getInt(3), rs.getDate(4).toLocalDate(), rs.getInt(6)));


    		}
    			} catch(Exception ex) {
    				ex.printStackTrace();
    			}
    	tableTurni.setItems(null);
    	tableTurni.setItems(listTurni);

    	//inserire riempimento tabella dottori
    	try{
    		Connection conn = dc.Connect();


    		String query = "SELECT * from Personale_Medico where Ref_Ambulatorio='8'";
    		ResultSet rs = conn.prepareStatement(query).executeQuery();
    		while (rs.next()){
    			PersonaleMedico med = new PersonaleMedico();

    			listDottori.add(new PersonaleMedico(rs.getInt(1),rs.getString(3), rs.getString(4)));


    		}
    			} catch(Exception ex) {
    				ex.printStackTrace();
    			}
    	tableDottori.setItems(null);
    	tableDottori.setItems(listDottori);


    }

    @FXML
    void loadCardiologia(ActionEvent event) {
    	listTurni.clear();
    	listDottori.clear();
    	try{
    		Connection conn = dc.Connect();


    		String query = "SELECT * from TURNO where Ref_Ambulatorio='11'";
    		ResultSet rs = conn.prepareStatement(query).executeQuery();
    		while (rs.next()){
    			Turno med = new Turno();

    			listTurni.add(new Turno(rs.getInt(2),rs.getInt(1), rs.getInt(3), rs.getDate(4).toLocalDate(), rs.getInt(6)));


    		}
    			} catch(Exception ex) {
    				ex.printStackTrace();
    			}
    	tableTurni.setItems(null);
    	tableTurni.setItems(listTurni);

    	//inserire riempimento tabella dottori
    	try{
    		Connection conn = dc.Connect();


    		String query = "SELECT * from Personale_Medico where Ref_Ambulatorio='11'";
    		ResultSet rs = conn.prepareStatement(query).executeQuery();
    		while (rs.next()){
    			PersonaleMedico med = new PersonaleMedico();

    			listDottori.add(new PersonaleMedico(rs.getInt(1),rs.getString(3), rs.getString(4)));


    		}
    			} catch(Exception ex) {
    				ex.printStackTrace();
    			}
    	tableDottori.setItems(null);
    	tableDottori.setItems(listDottori);


    }

    @FXML
    void loadDiabetologia(ActionEvent event) {
    	listTurni.clear();
    	listDottori.clear();
    	try{
    		Connection conn = dc.Connect();


    		String query = "SELECT * from TURNO where Ref_Ambulatorio='9'";
    		ResultSet rs = conn.prepareStatement(query).executeQuery();
    		while (rs.next()){
    			Turno med = new Turno();

    			listTurni.add(new Turno(rs.getInt(2),rs.getInt(1), rs.getInt(3), rs.getDate(4).toLocalDate(), rs.getInt(6)));


    		}
    			} catch(Exception ex) {
    				ex.printStackTrace();
    			}
    	tableTurni.setItems(null);
    	tableTurni.setItems(listTurni);

    	//inserire riempimento tabella dottori
    	try{
    		Connection conn = dc.Connect();


    		String query = "SELECT * from Personale_Medico where Ref_Ambulatorio='9'";
    		ResultSet rs = conn.prepareStatement(query).executeQuery();
    		while (rs.next()){
    			PersonaleMedico med = new PersonaleMedico();

    			listDottori.add(new PersonaleMedico(rs.getInt(1),rs.getString(3), rs.getString(4)));


    		}
    			} catch(Exception ex) {
    				ex.printStackTrace();
    			}
    	tableDottori.setItems(null);
    	tableDottori.setItems(listDottori);


    }

    @FXML
    void loadEndocrinologia(ActionEvent event) {
    	listTurni.clear();
    	listDottori.clear();
    	try{
    		Connection conn = dc.Connect();


    		String query = "SELECT * from TURNO where Ref_Ambulatorio='6'";
    		ResultSet rs = conn.prepareStatement(query).executeQuery();
    		while (rs.next()){
    			Turno med = new Turno();

    			listTurni.add(new Turno(rs.getInt(2),rs.getInt(1), rs.getInt(3), rs.getDate(4).toLocalDate(), rs.getInt(6)));


    		}
    			} catch(Exception ex) {
    				ex.printStackTrace();
    			}
    	tableTurni.setItems(null);
    	tableTurni.setItems(listTurni);

    	//inserire riempimento tabella dottori
    	try{
    		Connection conn = dc.Connect();


    		String query = "SELECT * from Personale_Medico where Ref_Ambulatorio='6'";
    		ResultSet rs = conn.prepareStatement(query).executeQuery();
    		while (rs.next()){
    			PersonaleMedico med = new PersonaleMedico();

    			listDottori.add(new PersonaleMedico(rs.getInt(1),rs.getString(3), rs.getString(4)));


    		}
    			} catch(Exception ex) {
    				ex.printStackTrace();
    			}
    	tableDottori.setItems(null);
    	tableDottori.setItems(listDottori);


    }

    @FXML
    void loadErgometria(ActionEvent event) {
    	listTurni.clear();
    	listDottori.clear();
    	try{
    		Connection conn = dc.Connect();


    		String query = "SELECT * from TURNO where Ref_Ambulatorio='12'";
    		ResultSet rs = conn.prepareStatement(query).executeQuery();
    		while (rs.next()){
    			Turno med = new Turno();

    			listTurni.add(new Turno(rs.getInt(2),rs.getInt(1), rs.getInt(3), rs.getDate(4).toLocalDate(), rs.getInt(6)));


    		}
    			} catch(Exception ex) {
    				ex.printStackTrace();
    			}
    	tableTurni.setItems(null);
    	tableTurni.setItems(listTurni);

    	//inserire riempimento tabella dottori
    	try{
    		Connection conn = dc.Connect();


    		String query = "SELECT * from Personale_Medico where Ref_Ambulatorio='12'";
    		ResultSet rs = conn.prepareStatement(query).executeQuery();
    		while (rs.next()){
    			PersonaleMedico med = new PersonaleMedico();

    			listDottori.add(new PersonaleMedico(rs.getInt(1),rs.getString(3), rs.getString(4)));


    		}
    			} catch(Exception ex) {
    				ex.printStackTrace();
    			}
    	tableDottori.setItems(null);
    	tableDottori.setItems(listDottori);


    }

    @FXML
    void loadFisiatria(ActionEvent event) {
    	listTurni.clear();
    	listDottori.clear();
    	try{
    		Connection conn = dc.Connect();


    		String query = "SELECT * from TURNO where Ref_Ambulatorio='2'";
    		ResultSet rs = conn.prepareStatement(query).executeQuery();
    		while (rs.next()){
    			Turno med = new Turno();

    			listTurni.add(new Turno(rs.getInt(2),rs.getInt(1), rs.getInt(3), rs.getDate(4).toLocalDate(), rs.getInt(6)));


    		}
    			} catch(Exception ex) {
    				ex.printStackTrace();
    			}
    	tableTurni.setItems(null);
    	tableTurni.setItems(listTurni);

    	//inserire riempimento tabella dottori
    	try{
    		Connection conn = dc.Connect();


    		String query = "SELECT * from Personale_Medico where Ref_Ambulatorio='2'";
    		ResultSet rs = conn.prepareStatement(query).executeQuery();
    		while (rs.next()){
    			PersonaleMedico med = new PersonaleMedico();

    			listDottori.add(new PersonaleMedico(rs.getInt(1),rs.getString(3), rs.getString(4)));


    		}
    			} catch(Exception ex) {
    				ex.printStackTrace();
    			}
    	tableDottori.setItems(null);
    	tableDottori.setItems(listDottori);


    }

    @FXML
    void loadFisiopatologia(ActionEvent event) {
    	listTurni.clear();
    	listDottori.clear();
    	try{
    		Connection conn = dc.Connect();


    		String query = "SELECT * from TURNO where Ref_Ambulatorio='7'";
    		ResultSet rs = conn.prepareStatement(query).executeQuery();
    		while (rs.next()){
    			Turno med = new Turno();

    			listTurni.add(new Turno(rs.getInt(2),rs.getInt(1), rs.getInt(3), rs.getDate(4).toLocalDate(), rs.getInt(6)));


    		}
    			} catch(Exception ex) {
    				ex.printStackTrace();
    			}
    	tableTurni.setItems(null);
    	tableTurni.setItems(listTurni);

    	//inserire riempimento tabella dottori
    	try{
    		Connection conn = dc.Connect();


    		String query = "SELECT * from Personale_Medico where Ref_Ambulatorio='7'";
    		ResultSet rs = conn.prepareStatement(query).executeQuery();
    		while (rs.next()){
    			PersonaleMedico med = new PersonaleMedico();

    			listDottori.add(new PersonaleMedico(rs.getInt(1),rs.getString(3), rs.getString(4)));


    		}
    			} catch(Exception ex) {
    				ex.printStackTrace();
    			}
    	tableDottori.setItems(null);
    	tableDottori.setItems(listDottori);


    }

    @FXML
    void loadIsteroscopia(ActionEvent event) {
    	listTurni.clear();
    	listDottori.clear();
    	try{
    		Connection conn = dc.Connect();


    		String query = "SELECT * from TURNO where Ref_Ambulatorio='10'";
    		ResultSet rs = conn.prepareStatement(query).executeQuery();
    		while (rs.next()){
    			Turno med = new Turno();

    			listTurni.add(new Turno(rs.getInt(2),rs.getInt(1), rs.getInt(3), rs.getDate(4).toLocalDate(), rs.getInt(6)));


    		}
    			} catch(Exception ex) {
    				ex.printStackTrace();
    			}
    	tableTurni.setItems(null);
    	tableTurni.setItems(listTurni);

    	//inserire riempimento tabella dottori
    	try{
    		Connection conn = dc.Connect();


    		String query = "SELECT * from Personale_Medico where Ref_Ambulatorio='10'";
    		ResultSet rs = conn.prepareStatement(query).executeQuery();
    		while (rs.next()){
    			PersonaleMedico med = new PersonaleMedico();

    			listDottori.add(new PersonaleMedico(rs.getInt(1),rs.getString(3), rs.getString(4)));


    		}
    			} catch(Exception ex) {
    				ex.printStackTrace();
    			}
    	tableDottori.setItems(null);
    	tableDottori.setItems(listDottori);


    }



    @FXML
    void loadNeurologia(ActionEvent event) {
    	listTurni.clear();
    	listDottori.clear();
    	try{
    		Connection conn = dc.Connect();


    		String query = "SELECT * from TURNO where Ref_Ambulatorio='5'";
    		ResultSet rs = conn.prepareStatement(query).executeQuery();
    		while (rs.next()){
    			Turno med = new Turno();

    			listTurni.add(new Turno(rs.getInt(2),rs.getInt(1), rs.getInt(3), rs.getDate(4).toLocalDate(), rs.getInt(6)));


    		}
    			} catch(Exception ex) {
    				ex.printStackTrace();
    			}
    	tableTurni.setItems(null);
    	tableTurni.setItems(listTurni);

    	//inserire riempimento tabella dottori
    	try{
    		Connection conn = dc.Connect();


    		String query = "SELECT * from Personale_Medico where Ref_Ambulatorio='5'";
    		ResultSet rs = conn.prepareStatement(query).executeQuery();
    		while (rs.next()){
    			PersonaleMedico med = new PersonaleMedico();

    			listDottori.add(new PersonaleMedico(rs.getInt(1),rs.getString(3), rs.getString(4)));


    		}
    			} catch(Exception ex) {
    				ex.printStackTrace();
    			}
    	tableDottori.setItems(null);
    	tableDottori.setItems(listDottori);

    }


    @FXML
    void loadPsichiatria(ActionEvent event) {
    	listTurni.clear();
    	listDottori.clear();
    	try{
    		Connection conn = dc.Connect();


    		String query = "SELECT * from TURNO where Ref_Ambulatorio='3'";
    		ResultSet rs = conn.prepareStatement(query).executeQuery();
    		while (rs.next()){
    			Turno med = new Turno();

    			listTurni.add(new Turno(rs.getInt(2),rs.getInt(1), rs.getInt(3), rs.getDate(4).toLocalDate(), rs.getInt(6)));


    		}
    			} catch(Exception ex) {
    				ex.printStackTrace();
    			}
    	tableTurni.setItems(null);
    	tableTurni.setItems(listTurni);

    	//inserire riempimento tabella dottori
    	try{
    		Connection conn = dc.Connect();


    		String query = "SELECT * from Personale_Medico where Ref_Ambulatorio='3'";
    		ResultSet rs = conn.prepareStatement(query).executeQuery();
    		while (rs.next()){
    			PersonaleMedico med = new PersonaleMedico();

    			listDottori.add(new PersonaleMedico(rs.getInt(1),rs.getString(3), rs.getString(4)));


    		}
    			} catch(Exception ex) {
    				ex.printStackTrace();
    			}
    	tableDottori.setItems(null);
    	tableDottori.setItems(listDottori);


    }

    @FXML
    void loadTraumatologia(ActionEvent event) {
    	listTurni.clear();
    	listDottori.clear();
    	try{
    		Connection conn = dc.Connect();


    		String query = "SELECT * from TURNO where Ref_Ambulatorio='4'";
    		ResultSet rs = conn.prepareStatement(query).executeQuery();
    		while (rs.next()){
    			Turno med = new Turno();

    			listTurni.add(new Turno(rs.getInt(2),rs.getInt(1), rs.getInt(3), rs.getDate(4).toLocalDate(), rs.getInt(6)));


    		}
    			} catch(Exception ex) {
    				ex.printStackTrace();
    			}
    	tableTurni.setItems(null);
    	tableTurni.setItems(listTurni);

    	//inserire riempimento tabella dottori
    	try{
    		Connection conn = dc.Connect();


    		String query = "SELECT * from Personale_Medico where Ref_Ambulatorio='4'";
    		ResultSet rs = conn.prepareStatement(query).executeQuery();
    		while (rs.next()){
    			PersonaleMedico med = new PersonaleMedico();

    			listDottori.add(new PersonaleMedico(rs.getInt(1),rs.getString(3), rs.getString(4)));


    		}
    			} catch(Exception ex) {
    				ex.printStackTrace();
    			}
    	tableDottori.setItems(null);
    	tableDottori.setItems(listDottori);


    }

    @FXML
    void logout(ActionEvent event) {

    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		dc=new DbConnection();
		//this.loadDataTurni();
		//tabella turni
		columnCod.setCellValueFactory(new PropertyValueFactory<Turno, Integer>("codTurno"));
    	columnData.setCellValueFactory(new PropertyValueFactory<Turno, Date>("data"));
    	columnOra.setCellValueFactory(new PropertyValueFactory<Turno, Integer>("orario"));
    	columnAmbulatorio.setCellValueFactory(new PropertyValueFactory<Turno, Integer>("refAmbulatorio"));
    	columnMedico.setCellValueFactory(new PropertyValueFactory<Turno, Integer>("refMedico"));
//tabella dottori
		columnNome.setCellValueFactory(new PropertyValueFactory<PersonaleMedico,String>("nome"));
		columnCognome.setCellValueFactory(new PropertyValueFactory<PersonaleMedico,String>("cognome"));
		columnCodice.setCellValueFactory(new PropertyValueFactory<PersonaleMedico,Integer>("codiceMedico"));
	}

}
