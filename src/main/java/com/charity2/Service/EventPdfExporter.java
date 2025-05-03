package com.charity2.Service;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import com.charity2.entities.Event; // Make sure this import is correct

import org.springframework.beans.factory.annotation.Value; // For potential logo path injection
import org.springframework.stereotype.Component;

// Add imports for Font, Color, Image, PdfPTable, PdfPCell etc. as needed for formatting [2]
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Element;
import com.lowagie.text.Image;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import java.awt.Color;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Component // Make it a Spring-managed bean [1]
public class EventPdfExporter {

    // Example: Inject logo path from application.properties if needed [1]
    @Value("${app.logo.path}")
    private String logoPath;

    public ByteArrayOutputStream generateEventListPdf(List<Event> events) throws DocumentException, IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        // Use A4 page size, you can change this [2]
        Document document = new Document(PageSize.A4);

        try {
            PdfWriter.getInstance(document, baos);
            document.open();

            // --- Start PDF Content Generation ---

            // TODO: Add Logo (See Section IV.C.3 in the report) [1, 9]
             addLogo(document, logoPath); // Uncomment and implement if needed

            // Add Report Title/Header (See Section IV.C.4) [1, 2, 9]
            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, Color.DARK_GRAY);
            Paragraph title = new Paragraph("Events Report", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            title.setSpacingAfter(20f);
            document.add(title);

            // Consider using a PdfPTable for better formatting (See Section IV.C.5) [1, 2, 9]
            // Instead of just paragraphs, use a table for structured data
            // Example: addEventTable(document, events); // Implement this method below

            // --- Fallback: Simple Paragraph Output (as in original code) ---
            // If not using a table, keep the original paragraph logic:
            Font eventFont = FontFactory.getFont(FontFactory.HELVETICA, 10);
            for (Event event : events) {
                document.add(new Paragraph("-------------------------"));
                document.add(new Paragraph("Title: " + (event.getTitle()!= null? event.getTitle() : ""), eventFont));
                document.add(new Paragraph("Description: " + (event.getDescription()!= null? event.getDescription() : ""), eventFont));
                // Format date appropriately if needed
                document.add(new Paragraph("Date: " + (event.getDate()!= null? event.getDate().toString() : ""), eventFont));
                document.add(new Paragraph("Location: " + (event.getLocation()!= null? event.getLocation() : ""), eventFont));
                // Handle category display (assuming toString() is suitable)
                document.add(new Paragraph("Category: " + (event.getCategories()!= null? event.getCategories().toString() : ""), eventFont));
            }
            document.add(new Paragraph("-------------------------"));
            // --- End Fallback ---


            // TODO: Add Footer (See Section IV.C.6) [1, 9]
             addFooter(document); // Uncomment and implement if needed

            // --- End PDF Content Generation ---

        } finally {
            if (document.isOpen()) {
                document.close(); // Ensures document is closed even if errors occur [2]
            }
        }

        return baos;
    }

    // --- Optional: Private Helper Methods ---


    // Example implementation for adding a logo [1]
    private void addLogo(Document document, String logoPath) throws DocumentException, IOException {
        try {
            // Resolve the actual path correctly (e.g., from classpath or filesystem)
            String resolvedLogoPath = "classpath:static/images/my_logo.png"; // Example classpath
            Image logo = Image.getInstance(logoPath); // Use the injected path
            logo.scaleToFit(100f, 50f); // Adjust size as needed
            logo.setAlignment(Element.ALIGN_RIGHT);
            document.add(logo);
        } catch (Exception e) {
            System.err.println("Error adding logo: " + e.getMessage());
            // Handle missing logo file gracefully if necessary
        }
    }


    /*
    // Example implementation for adding a table [1, 2]
    private void addEventTable(Document document, List<Event> events) throws DocumentException {
        PdfPTable table = new PdfPTable(5); // Adjust column count based on fields (Title, Desc, Date, Loc, Cat)
        table.setWidthPercentage(100f);
        // Optional: Set relative column widths
        // table.setWidths(new float{2f, 3f, 1.5f, 2f, 1.5f});
        table.setSpacingBefore(10f);

        // Header Cell Style
        Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 11, Color.WHITE);
        PdfPCell headerCell = new PdfPCell();
        headerCell.setBackgroundColor(Color.GRAY);
        headerCell.setPadding(5);
        headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        headerCell.setVerticalAlignment(Element.ALIGN_MIDDLE);

        // Data Cell Style
        Font dataFont = FontFactory.getFont(FontFactory.HELVETICA, 10, Color.BLACK);
        PdfPCell dataCell = new PdfPCell();
        dataCell.setPadding(5);
        dataCell.setHorizontalAlignment(Element.ALIGN_LEFT);
        dataCell.setVerticalAlignment(Element.ALIGN_MIDDLE);

        // Add Header Row
        addHeaderCellToTable(table, headerCell, headerFont, "Title");
        addHeaderCellToTable(table, headerCell, headerFont, "Description");
        addHeaderCellToTable(table, headerCell, headerFont, "Date");
        addHeaderCellToTable(table, headerCell, headerFont, "Location");
        addHeaderCellToTable(table, headerCell, headerFont, "Category");

        // Add Data Rows
        for (Event event : events) {
            addDataCellToTable(table, dataCell, dataFont, event.getTitle());
            addDataCellToTable(table, dataCell, dataFont, event.getDescription());
            addDataCellToTable(table, dataCell, dataFont, event.getDate()!= null? event.getDate().toString() : "");
            addDataCellToTable(table, dataCell, dataFont, event.getLocation());
            addDataCellToTable(table, dataCell, dataFont, event.getCategories()!= null? event.getCategories().toString() : "");
        }

        document.add(table);
    }

    private void addHeaderCellToTable(PdfPTable table, PdfPCell templateCell, Font font, String text) {
        templateCell.setPhrase(new Phrase(text, font));
        table.addCell(templateCell);
    }

     private void addDataCellToTable(PdfPTable table, PdfPCell templateCell, Font font, String text) {
        templateCell.setPhrase(new Phrase(text!= null? text : "", font));
        table.addCell(templateCell);
    }
    */


    // Example implementation for adding a footer [1]
    private void addFooter(Document document) throws DocumentException {
        Font footerFont = FontFactory.getFont(FontFactory.HELVETICA, 8, Color.GRAY);
        Paragraph footer = new Paragraph("Generated on: " + new java.util.Date(), footerFont);
        footer.setAlignment(Element.ALIGN_CENTER);
        // For fixed bottom footer, more complex page events might be needed.
        // This adds it at the end of the content flow.
        document.add(footer);
    }

}