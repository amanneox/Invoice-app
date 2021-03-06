package com.paperless.app.service;

import com.paperless.app.model.InvoiceModel;
import com.paperless.app.model.UserInvoice;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class DocumentService {
    public PDDocument createPDF(InvoiceModel data, UserInvoice invoice){
        PDDocument doc = new PDDocument();
        PDPage blankPage = new PDPage(PDRectangle.A4);
        doc.addPage( blankPage );
        PDPage page = doc.getPage(0);
        PDDocumentInformation pdd = doc.getDocumentInformation();
        PDRectangle mediabox = page.getMediaBox();
        float leading = 1.5f * 12;
        float margin = 72;
        float width = mediabox.getWidth() - 2*margin;
        float startX = mediabox.getLowerLeftX() + margin;
        float startY = mediabox.getUpperRightY() - margin;
        float endX = mediabox.getUpperRightX() - margin;
        float endY = mediabox.getLowerLeftY() + margin;
        pdd.setAuthor(data.getCname());
        pdd.setTitle(data.getName());
        pdd.setCreator("Paperless");
        pdd.setSubject("User Invoice");
        try {
            PDPageContentStream contentStream = new PDPageContentStream(doc, page);
            contentStream.beginText();
            contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
            contentStream.newLineAtOffset(startX, startY);
            contentStream.showText(String.valueOf(data.getCname()));
            contentStream.newLineAtOffset(0, -leading);
            contentStream.showText(String.valueOf(data.getAddress()));

            contentStream.newLineAtOffset(0, -leading);
            contentStream.showText(String.valueOf(data.getEmail()));

            contentStream.newLineAtOffset(0, -leading);
            contentStream.showText(String.valueOf(data.getNumber()));

            contentStream.endText();

            contentStream.moveTo(startX,startY-4*leading);
            contentStream.lineTo(endX,startY-4*leading);
            contentStream.stroke();

            contentStream.beginText();
            contentStream.newLineAtOffset(startX, startY-5*leading);
            contentStream.showText("Customer Name:"+String.valueOf(invoice.getUser_name()));

            contentStream.newLineAtOffset(0, -leading);
            contentStream.showText("Invoice Date:"+String.valueOf(data.getCreatedAt()));

            contentStream.newLineAtOffset(0, -leading);
            contentStream.showText("Total Items:"+String.valueOf(invoice.getItems().size()));

            contentStream.newLineAtOffset(0, -leading);
            contentStream.showText("Total Amount:"+String.valueOf(invoice.getTotal_value()));

            contentStream.endText();

            contentStream.beginText();
            contentStream.newLineAtOffset(startX,startY-11*leading);
            contentStream.showText("Items Ordered");

            for (int i = 0;i<invoice.getItems().size();++i){
                contentStream.newLineAtOffset(0, -leading);
                contentStream.showText("("+(i+1)+")"+"Item Name:"+invoice.getItems().get(i).getName());
                contentStream.newLineAtOffset(0, -leading);
                contentStream.showText("Item Price:"+invoice.getItems().get(i).getPrice());
                contentStream.newLineAtOffset(0, -leading);
                contentStream.showText("Item Description:"+invoice.getItems().get(i).getDesc());
                contentStream.newLineAtOffset(0, -leading);
            }
            contentStream.setFont(PDType1Font.TIMES_BOLD, 12);
            contentStream.newLineAtOffset(0, -leading);
            contentStream.showText("Terms:"+data.getTerms());
            contentStream.endText();
            contentStream.close();
         //   doc.save(new File("new.pdf"));
          //  doc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return doc;
    }
}
