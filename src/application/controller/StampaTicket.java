package application.controller;

import java.io.File;
import java.io.IOException;

import javax.swing.GroupLayout.Alignment;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;

public class StampaTicket {

	private String nome, cognome, codiceFiscale, nomePrestazione;
	private float costo;



	public StampaTicket() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	public String getNomePrestazione() {
		return nomePrestazione;
	}

	public void setNomePrestazione(String nomePrestazione) {
		this.nomePrestazione = nomePrestazione;
	}

	public float getCosto() {
		return costo;
	}

	public void setCosto(float costo) {
		this.costo = costo;
	}

	public String getCodiceBarre() {
		return codiceBarre;
	}

	public void setCodiceBarre(String codiceBarre) {
		this.codiceBarre = codiceBarre;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}
	String dest;

	public File getDest() {
		return new File(dest);
	}

	public void setDest(String dest) {
		this.dest = dest;
	}



	public StampaTicket(String nome, String cognome, String codiceFiscale, String nomePrestazione, float costo) {
		this.nome = nome;
		this.cognome = cognome;
		this.codiceFiscale = codiceFiscale;
		this.costo = costo;
		this.nomePrestazione = nomePrestazione;
	}

	private String codiceBarre = "../AppGama/image/codiceBarre.jpg";
	private String logo = "../AppGama/image/GAMA1.png";

	public void stampaTicket() {

		try {
			boolean success = (new File("C:/Users/Giuseppe/eclipse-workspace/AppGama/ticket")).mkdir();
			dest = "C:/Users/Giuseppe/eclipse-workspace/AppGama/ticket/"+ cognome +" "+ nome +" " + nomePrestazione + ".pdf";

			PdfWriter writer = new PdfWriter(dest);
			PdfDocument doc = new PdfDocument(writer);

			doc.addNewPage();

			Document document = new Document(doc);

			ImageData obj1 = ImageDataFactory.create(logo);
			Image logo1 = new Image(obj1);
			logo1.setFixedPosition(200, 650);
			logo1.scaleToFit(200, 200);

			document.add(logo1);


			ImageData obj2 = ImageDataFactory.create(codiceBarre);
			Image barre = new Image(obj2);

			document.add(new Paragraph("\n"));
			document.add(new Paragraph("\n"));
			document.add(new Paragraph("\n"));
			document.add(new Paragraph("\n"));
			document.add(new Paragraph("\n"));
			document.add(new Paragraph("\n"));
			document.add(new Paragraph("\n"));

			String para = "TICKET VISITA: " + nomePrestazione;
			Paragraph paragrafo = new Paragraph(para);
			paragrafo.setFontColor(Color.RED);
			document.add(paragrafo);

			document.add(new Paragraph("\n"));
			document.add(new Paragraph("\n"));


			float [] attributi = {450F};
			Table tabella = new Table (attributi);
			tabella.addCell(new Cell().add(nomePrestazione).setTextAlignment(TextAlignment.CENTER));

			float [] attributi2 = {225F, 225F};
			Table tabella2 = new Table(attributi2);
			tabella2.addCell(new Cell().add("Cognome:").setTextAlignment(TextAlignment.CENTER));
			tabella2.addCell(new Cell().add(cognome).setTextAlignment(TextAlignment.CENTER));
			tabella2.addCell(new Cell().add("Nome:").setTextAlignment(TextAlignment.CENTER));
			tabella2.addCell(new Cell().add(nome).setTextAlignment(TextAlignment.CENTER));
			tabella2.addCell(new Cell().add("Codice fiscale:").setTextAlignment(TextAlignment.CENTER));
			tabella2.addCell(new Cell().add(codiceFiscale).setTextAlignment(TextAlignment.CENTER));
			tabella2.addCell(new Cell().add("Prezzo:").setTextAlignment(TextAlignment.CENTER));
			tabella2.addCell(new Cell().add("€: " + String.valueOf(costo)).setTextAlignment(TextAlignment.CENTER));

			tabella.addCell(tabella2);
			tabella.setFixedPosition(70, 400, 450);
			document.add(tabella);

			barre.scaleToFit(400, 100);
			barre.setFixedPosition(180, 100);
			document.add(barre);

			document.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}
