package application.controller;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;

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

public class MemorizzaDatiVisitaPdf {

	private String cognomePaziente, nomePaziente, cfPaziente;
	private String cognomeDottore, nomeDottore;
	private LocalDate dataOggi;
	private String nomeVisita;
	private String resoconto;

	public MemorizzaDatiVisitaPdf(String cognomePaziente,String nomePaziente,String cfPaziente,String cognomeDottore,String nomeDottore, String nomeVisita, String resoconto, LocalDate dataOggi) {
		setCognomePaziente(cognomePaziente);
		setNomePaziente(nomePaziente);
		setCfPaziente(cfPaziente);
		setCognomeDottore(cognomeDottore);
		setNomeDottore(nomeDottore);
		setDataOggi(dataOggi);
		setNomeVisita(nomeVisita);
		setResoconto(resoconto);
	}

	public MemorizzaDatiVisitaPdf(MemorizzaDatiVisitaPdf m) {
		this.cognomePaziente = m.getCognomePaziente();
		this.nomePaziente = m.getNomePaziente();
		this.cfPaziente = m.getCfPaziente();
		this.cognomeDottore = m.getCognomeDottore();
		this.nomeDottore = m.getNomeDottore();
		this.dataOggi = m.getDataOggi();
		this.nomeVisita = m.getNomeVisita();
		this.resoconto = m.getResoconto();
	}

	public MemorizzaDatiVisitaPdf() {
		setCognomePaziente(null);
		setNomePaziente(null);
		setCfPaziente(null);
		setCognomeDottore(null);
		setNomeDottore(null);
		setDataOggi(null);
		setNomeVisita(null);
		setResoconto(null);
	}

	public String getCognomePaziente() {
		return cognomePaziente;
	}

	public void setCognomePaziente(String cognomePaziente) {
		this.cognomePaziente = cognomePaziente;
	}

	public String getNomePaziente() {
		return nomePaziente;
	}

	public void setNomePaziente(String nomePaziente) {
		this.nomePaziente = nomePaziente;
	}

	public String getCfPaziente() {
		return cfPaziente;
	}

	public void setCfPaziente(String cfPaziente) {
		this.cfPaziente = cfPaziente;
	}

	public String getCognomeDottore() {
		return cognomeDottore;
	}

	public void setCognomeDottore(String cognomeDottore) {
		this.cognomeDottore = cognomeDottore;
	}

	public String getNomeDottore() {
		return nomeDottore;
	}

	public void setNomeDottore(String nomeDottore) {
		this.nomeDottore = nomeDottore;
	}

	public LocalDate getDataOggi() {
		return dataOggi;
	}

	public void setDataOggi(LocalDate dataOggi) {
		this.dataOggi = dataOggi;
	}

	public String getNomeVisita() {
		return nomeVisita;
	}

	public void setNomeVisita(String nomeVisita) {
		this.nomeVisita = nomeVisita;
	}

	public String getResoconto() {
		return resoconto;
	}

	public void setResoconto(String resoconto) {
		this.resoconto = resoconto;
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

	private String logo = "../AppGama/image/GAMA1.png";

	public void memorizzaDatiVisitaPdf() {

		try {

			String directory = cognomePaziente +" "+ nomePaziente;
			boolean success = (new File("C:/Users/Giuseppe/eclipse-workspace/AppGama/cartella clinica/"+directory)).mkdir();
			dest = "C:/Users/Giuseppe/eclipse-workspace/AppGama/cartella clinica/"+ cognomePaziente +" "+ nomePaziente +"/"+""+ nomeVisita + " " + dataOggi + ".pdf";

			PdfWriter writer = new PdfWriter(dest);
			PdfDocument doc = new PdfDocument(writer);

			doc.addNewPage();

			Document document = new Document(doc);

			ImageData obj1 = ImageDataFactory.create(logo);
			Image logo1 = new Image(obj1);
			logo1.setFixedPosition(200, 650);
			logo1.scaleToFit(200, 200);

			document.add(logo1);


			document.add(new Paragraph("\n"));
			document.add(new Paragraph("\n"));
			document.add(new Paragraph("\n"));
			document.add(new Paragraph("\n"));
			document.add(new Paragraph("\n"));
			document.add(new Paragraph("\n"));
			document.add(new Paragraph("\n"));

			String para = "DATI VISITA: " + nomeVisita;
			Paragraph paragrafo = new Paragraph(para);
			paragrafo.setFontColor(Color.RED);
			document.add(paragrafo);

			document.add(new Paragraph("\n"));
			document.add(new Paragraph("\n"));


			float [] attributi = {450F};
			Table tabella = new Table (attributi);
			tabella.addCell(new Cell().add(nomeVisita).setTextAlignment(TextAlignment.CENTER));

			float [] attributi2 = {225F, 225F};
			Table tabella2 = new Table(attributi2);
			tabella2.addCell(new Cell().add("Cognome pazinete:").setTextAlignment(TextAlignment.CENTER));
			tabella2.addCell(new Cell().add(cognomePaziente).setTextAlignment(TextAlignment.CENTER));
			tabella2.addCell(new Cell().add("Nome paziente:").setTextAlignment(TextAlignment.CENTER));
			tabella2.addCell(new Cell().add(nomePaziente).setTextAlignment(TextAlignment.CENTER));
			tabella2.addCell(new Cell().add("Codice fiscale:").setTextAlignment(TextAlignment.CENTER));
			tabella2.addCell(new Cell().add(cfPaziente).setTextAlignment(TextAlignment.CENTER));
			tabella2.addCell(new Cell().add("Cognome Dottore:").setTextAlignment(TextAlignment.CENTER));
			tabella2.addCell(new Cell().add(cognomeDottore).setTextAlignment(TextAlignment.CENTER));
			tabella2.addCell(new Cell().add("Nome dottore:").setTextAlignment(TextAlignment.CENTER));
			tabella2.addCell(new Cell().add(nomeDottore).setTextAlignment(TextAlignment.CENTER));

			tabella.addCell(tabella2);
			document.add(tabella);


			document.add(new Paragraph("\n"));
			document.add(new Paragraph("\n"));
			document.add(new Paragraph("Diagnosi Medica:"));
			Paragraph paragrafo1 = new Paragraph(resoconto);
			document.add(paragrafo1);

			document.add(new Paragraph("\n"));
			document.add(new Paragraph("\n"));
			document.add(new Paragraph("Firma: " + nomeDottore + " " + cognomeDottore));

			document.close();


		}catch(IOException e){
			e.printStackTrace();
		}

	}


}
