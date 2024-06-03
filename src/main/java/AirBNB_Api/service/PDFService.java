package AirBNB_Api.service;



import AirBNB_Api.entity.Booking;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import org.springframework.stereotype.Service;

import java.io.FileOutputStream;

@Service
public class PDFService {

    public String generateBookingDetailsPdf(Booking booking) {
        Document document = new Document();
        String filePath = "C://Users//KIIT//Desktop//AirbnbProject//PdfFile//booking-confirmation" + booking.getId() + ".pdf";

        try {
            PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();

            Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, BaseColor.BLACK);
            Font fontData = FontFactory.getFont(FontFactory.HELVETICA, 12, BaseColor.BLACK);

            // Title
            Paragraph title = new Paragraph("Booking Confirmation", fontTitle);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);
            document.add(Chunk.NEWLINE);

            // Create table
            PdfPTable table = new PdfPTable(2); // 2 columns
            table.setWidthPercentage(100);
            table.setSpacingBefore(10f);
            table.setSpacingAfter(10f);

            // Add table headers
            PdfPCell cell = new PdfPCell(new Phrase("Attribute", fontTitle));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Value", fontTitle));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            table.addCell(cell);

            // Add booking details
            addRow(table, "Guest Name", booking.getGuestName(), fontData);
            addRow(table, "Total Nights", String.valueOf(booking.getTotalNights()), fontData);
            addRow(table, "Total Price", String.valueOf(booking.getTotalPrice()), fontData);
            // Add more rows for other attributes as needed

            document.add(table);

        } catch (Exception e) {
            e.printStackTrace();
            return null; // Return null if there's an exception
        } finally {
            if (document != null) {
                document.close();
            }
        }

        return filePath; // Return the file path if PDF generation succeeds
    }

    private void addRow(PdfPTable table, String attribute, String value, Font font) {
        table.addCell(new Phrase(attribute, font));
        table.addCell(new Phrase(value, font));
    }
}
