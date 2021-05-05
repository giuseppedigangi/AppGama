
package application.view;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.google.common.base.Optional;
import com.mysql.*;

import application.controller.DbConnection;
import application.controller.StampaTicket;
import application.model.Paziente;
import application.model.Prenotazione;
import application.model.PrestazioneSanitaria;
import application.model.Ticket;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class TicketCTRL implements Initializable {
	  @FXML
	    private ImageView logo;

	    @FXML
	    private Label appBanner;

	    @FXML
	    private Text infoBanner;

	    @FXML
	    private Label codiceFiscale = new Label();

	    @FXML
	    private Label nome;

	    @FXML
	    private Label cognome;

	    @FXML
	    private Label nomeVisita;

	    @FXML
	    private Label costo;

	    @FXML
	    private Button buttonLogout;

	    @FXML
	    private Button buttonHome;

	    @FXML
	    private Button buttonSalva;

	    private DbConnection dc;

	    Prenotazione prenotazione;
	    PrestazioneSanitaria pre;
	    Paziente paziente = new Paziente();

	    public void infoPrenotazione(Prenotazione prenotazione){
	    	this.prenotazione=prenotazione;
	    }

	    public void infoPrestazione(PrestazioneSanitaria prestazione){
	    	this.pre=prestazione;
	    }
	    public void logPaziente(Paziente paziente){
	    	this.paziente=paziente;
	    }

	    String cf=paziente.getCodiceFiscale();

	    /*
	     * funzioni utili schermata Ticket
	     */
	    @FXML
	    void logout(ActionEvent event) {
	    	 try {
	  				Parent tableViewParent= FXMLLoader.load(getClass().getResource("SchermataLogout.fxml"));
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
	    void clickSalva(ActionEvent event) throws IOException{


				StampaTicket ticket = new StampaTicket();
				ticket.setCognome(paziente.getCognome());
				ticket.setNome(paziente.getNome());
				ticket.setCodiceFiscale(paziente.getCodiceFiscale());
				ticket.setNomePrestazione(pre.getNomePrestazione());
				ticket.setCosto(pre.getCosto());
				ticket.stampaTicket();
				java.awt.Desktop.getDesktop().open(ticket.getDest());

				Ticket tic=new Ticket();
				tic.setRef_codiceFiscalePaziente(paziente.getCodiceFiscale());
				tic.setRef_codicePrenotazione(pre.getCodicePrestazioneSanitaria());
				try {
					createTicket();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}


	    }

	    public Ticket createTicket() throws SQLException{

			Ticket tic=new Ticket();
			 java.sql.Connection conn=dc.Connect();
			 int turn=prenotazione.getTurnoPrenotazione();
			 String query="SELECT codice_prenotazione FROM PRENOTAZIONE WHERE ref_codice_paziente='"+paziente.getCodiceFiscale()+"' AND ref_ricetta_elettronica= '"+prenotazione.getRefRicettaElettronica()+"' ";

			 ResultSet rs=conn.prepareStatement(query).executeQuery();
			 if(rs.next()){

				tic.setRef_codiceFiscalePaziente(paziente.getCodiceFiscale());

				tic.setRef_codicePrenotazione(rs.getInt(1));
				System.out.println(tic.getRef_codicePrenotazione());

				try {
					String query2="INSERT INTO ticket  (ref_codice_prenotazione,ref_codice_fiscale_paziente) VALUES (?,?)";
					PreparedStatement pst;
					pst=conn.prepareStatement(query2);
					pst.setInt(1, tic.getRef_codicePrenotazione());
					pst.setString(2, paziente.getCodiceFiscale());
					pst.execute();

				} catch (Exception e) {
					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("Finestra Notifica");
					alert.setHeaderText("Manipolazione Dati DBMS fallita");
					alert.setContentText("");
					alert.showAndWait();
					e.printStackTrace();
				}

			}

			return tic;
	    }





	    @FXML
	    void clickHome(ActionEvent event) {
	    	try {
				Parent tableViewParent= FXMLLoader.load(getClass().getResource("SchermataPrincipale.fxml"));
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


	    final void setLabel(Paziente paziente, PrestazioneSanitaria pre) {
	    	codiceFiscale.setText(paziente.getCodiceFiscale());
	    	nome.setText(paziente.getNome());
	    	cognome.setText(paziente.getCognome());
	    	costo.setText(String.valueOf(pre.getCosto()));
	    	nomeVisita.setText(pre.getNomePrestazione());


	    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		dc=new DbConnection();



	}

}
