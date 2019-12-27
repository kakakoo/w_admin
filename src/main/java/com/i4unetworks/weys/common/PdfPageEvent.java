package com.i4unetworks.weys.common;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

public class PdfPageEvent extends PdfPageEventHelper {

	Phrase [] header = new Phrase[1];
	int pageNumber;
	
	@Override
	public void onOpenDocument(PdfWriter writer, Document document) {
		this.header[0] = new Phrase(Utils.getTodayDate("yyyy.MM.dd HH:mm:ss"));
	}
	
	@Override
	public void onChapter(PdfWriter writer, Document document, float paragraphPosition, Paragraph title) {
		this.pageNumber = 1;
	}
	
	@Override
	public void onStartPage(PdfWriter writer, Document document) {
		this.pageNumber += 1;
	}
	
	@Override
	public void onEndPage(PdfWriter writer, Document document) {
		Rectangle rect = writer.getBoxSize("groupPdf");
		
		ColumnText.showTextAligned(writer.getDirectContent()
				, Element.ALIGN_RIGHT, header[0], rect.getRight()
				, rect.getTop() + 10 , 0);
		
		ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER
				, new Phrase(String.format("%d", this.pageNumber)), (rect.getLeft() + rect.getRight())/2
				, rect.getBottom() - 20, 0);
	}
}
