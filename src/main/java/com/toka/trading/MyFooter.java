package com.toka.trading;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

class MyFooter extends PdfPageEventHelper {

	Font ffont1 = new Font(Font.FontFamily.UNDEFINED, 12, Font.ITALIC);

	Font ffont2 = new Font(Font.FontFamily.UNDEFINED, 16, Font.ITALIC);

	public void onEndPage(PdfWriter writer, Document document) {
		PdfContentByte cb = writer.getDirectContent();
		Phrase header = new Phrase("");
		Phrase footer = new Phrase("                                   @Copyright TOKA TRADING...!\n", ffont2);
		ColumnText.showTextAligned(cb, Element.ALIGN_CENTER, header,
				(document.right() - document.left()) / 2 + document.leftMargin(), document.top() + 10, 0);
		ColumnText.showTextAligned(cb, Element.ALIGN_CENTER, footer,
				(document.right() - document.left()) / 2 + document.leftMargin(), document.bottom() - 10, 0);
	}
}