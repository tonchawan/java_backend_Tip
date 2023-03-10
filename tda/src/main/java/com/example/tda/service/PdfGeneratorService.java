package com.example.tda.service;

import java.io.IOException;
// import java.util.Date;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

import com.example.tda.entity.Order;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

@Service
public class PdfGeneratorService {
        public void export(HttpServletResponse response, Order order) throws DocumentException, IOException {
                Document document = new Document(PageSize.A4);
                PdfWriter.getInstance(document, response.getOutputStream());
                document.open();
                // Font size for Header
                Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
                fontTitle.setSize(22);
                // Font size for normal text
                Font fontNormal = FontFactory.getFont(FontFactory.HELVETICA);
                fontNormal.setSize(15);
                // Header
                Paragraph header = new Paragraph("Insurance", fontTitle);
                header.setAlignment(Paragraph.ALIGN_CENTER);
                // Add space below the header
                header.setSpacingAfter(50);

                Paragraph customerDetail = new Paragraph("Customer Detail", fontTitle);
                Paragraph firstname = new Paragraph("FirstName :  " + order.getComment() + Chunk.NEWLINE, fontNormal);

              

                document.add(header);
                document.add(customerDetail);
                document.add(firstname);
                document.close();
        }
}
