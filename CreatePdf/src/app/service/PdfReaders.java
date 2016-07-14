package app.service;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

public class PdfReaders{
	public static void main(String[]args){
		try {
			readPDF("d:\\ _1181809909.pdf");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void readPDF(String path) throws Exception {  
		try {  
			PdfReader reader = new PdfReader(path);  
			int n = reader.getNumberOfPages();  
			System.out.println("page number = " + n);  
			String str = PdfTextExtractor.getTextFromPage(reader, n);
			System.out.println(str);  
		} catch (Exception e) {  
			System.out.println(e);  
		}  
	}  
}
