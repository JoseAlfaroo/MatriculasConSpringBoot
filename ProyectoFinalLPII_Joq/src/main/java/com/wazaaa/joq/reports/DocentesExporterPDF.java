package com.wazaaa.joq.reports;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.wazaaa.joq.models.Docente;

import jakarta.servlet.http.HttpServletResponse;

public class DocentesExporterPDF {
	private List<Docente> listaDocentes;

	public DocentesExporterPDF(List<Docente> listaDocentes) {
		super();
		this.listaDocentes = listaDocentes;
	}
	
	private void escribirCabeceraDeLaTabla(PdfPTable tabla) {
		PdfPCell celda = new PdfPCell();
		
		//Estilos de celdas
		celda.setBackgroundColor(Color.DARK_GRAY);
		celda.setPadding(5);
		
		//Fuente y color
		Font fuente = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		fuente.setColor(Color.WHITE);
		
		//Titulos de cabecera
		celda.setPhrase(new Phrase("ID", fuente));
        celda.setHorizontalAlignment(Phrase.ALIGN_CENTER);
        celda.setVerticalAlignment(Phrase.ALIGN_MIDDLE);
        tabla.addCell(celda);
		
		celda.setPhrase(new Phrase("Nombre", fuente));
        celda.setHorizontalAlignment(Phrase.ALIGN_CENTER);
        celda.setVerticalAlignment(Phrase.ALIGN_MIDDLE);
        tabla.addCell(celda);
		
		celda.setPhrase(new Phrase("Apellido", fuente));
        celda.setHorizontalAlignment(Phrase.ALIGN_CENTER);
        celda.setVerticalAlignment(Phrase.ALIGN_MIDDLE);
        tabla.addCell(celda);
		
		celda.setPhrase(new Phrase("Teléfono", fuente));
        celda.setHorizontalAlignment(Phrase.ALIGN_CENTER);
        celda.setVerticalAlignment(Phrase.ALIGN_MIDDLE);
        tabla.addCell(celda);
        
		celda.setPhrase(new Phrase("Correo", fuente));
        celda.setHorizontalAlignment(Phrase.ALIGN_CENTER);
        celda.setVerticalAlignment(Phrase.ALIGN_MIDDLE);
        tabla.addCell(celda);
		
	}
	
    private PdfPCell centrarCelda(String contenido) {
        PdfPCell celda = new PdfPCell(new Phrase(contenido));
        celda.setHorizontalAlignment(Phrase.ALIGN_CENTER);
        celda.setVerticalAlignment(Phrase.ALIGN_MIDDLE);
        return celda;
    }
	
	private void escribirDatosDeLaTabla(PdfPTable tabla) {
		
		for(Docente docente : listaDocentes) {

			tabla.addCell(centrarCelda(String.valueOf(docente.getId())));
			tabla.addCell(centrarCelda(docente.getNombres()));
			tabla.addCell(centrarCelda(docente.getApellidos()));
			tabla.addCell(centrarCelda(docente.getTelefono()));
			tabla.addCell(centrarCelda(docente.getCorreo()));
		}
		
	}
	
	public void exportarDocentes(HttpServletResponse response) throws DocumentException, IOException {
		Document documento = new Document(PageSize.A4);
		PdfWriter.getInstance(documento, response.getOutputStream());
		
		documento.open();
		
		Font fuente = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		fuente.setStyle(Font.UNDERLINE);
		fuente.setColor(Color.BLACK);
		fuente.setSize(18);
		
		Paragraph titulo = new Paragraph("Lista de Docentes", fuente);
		titulo.setAlignment(Paragraph.ALIGN_CENTER);
		documento.add(titulo);
		
		/*PdfTable(numColumnas)*/
		PdfPTable tabla = new PdfPTable(5);
		tabla.setWidthPercentage(100);
		tabla.setSpacingBefore(15);
		
		
		// Un float en el array por cada columna, que todas sumen "20f"
		tabla.setWidths(new float[] {1f, 5f, 5f, 3f, 6f});
		tabla.setWidthPercentage(110);
		
		
		escribirCabeceraDeLaTabla(tabla);
		escribirDatosDeLaTabla(tabla);
		
		documento.add(tabla);
		documento.close();
		
	}
	
}

	

