package application.controller;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.util.Optional;

public class FinestraConferma extends Alert {

    private final ButtonType tastoSi = new ButtonType("Si");
    private final ButtonType tastoNo = new ButtonType("No");

    //Costruttore
    /**
     * Inizializza un nuovo oggetto FinestraConferma impostando i valori passati come parametri
     * @param owner - Stage proprietario della finestra
     * @param testo - Stringa da visualizzare sulla finestra
     * @param titolo - Titolo della finestra
     */
    public FinestraConferma(Stage owner, String testo, String titolo) {
        super(AlertType.CONFIRMATION, testo);
        this.setHeaderText(null);
        this.setTitle(titolo);
        this.initOwner(owner);
        this.getButtonTypes().setAll(tastoSi,tastoNo);
    }

    //Costruttore
    /**
     * Inizializza un nuovo oggetto FinestraConferma impostando i valori passati come parametri
     * @param owner - Stage proprietario della finestra
     * @param testo - Stringa da visualizzare sulla finestra
     */
    public FinestraConferma(Stage owner, String testo){
        this(owner,testo,"Conferma");
    }

    /**
     *
     * @return true se è stato premuto il tasto "Conferma", false altrimenti
     */
    public boolean ottieniConferma(){
        Optional<ButtonType> scelta = this.showAndWait();
        return this.tastoSi.equals(scelta.get());
    }

}