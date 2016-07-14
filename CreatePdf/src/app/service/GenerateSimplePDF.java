package app.service;

import java.io.FileOutputStream;  
import java.io.IOException;  
import java.util.Random;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;  
import com.itextpdf.text.DocumentException;  
import com.itextpdf.text.Element;  
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;  
import com.itextpdf.text.pdf.PdfPCell;  
import com.itextpdf.text.pdf.PdfPTable;  
import com.itextpdf.text.pdf.PdfPTableEvent;  
import com.itextpdf.text.pdf.PdfWriter;  

import app.util.AlternatingBackground;

public class GenerateSimplePDF {
	/** 
	 * main:(这里用一句话描述这个方法的作用). <br/> 
	 *  
	 * @param args 
	 * @throws IOException  
	 * @throws DocumentException  
	 * @since
	 */  
	public static void main(String[] args) throws IOException, DocumentException {  

		String fileDesc = "";  
		FileOutputStream outr = null;// 创建输出流  
		// 生成随机数  
		Random random = new Random();  
		int x = random.nextInt();  
		fileDesc = "d:\\pdftest\\" + "_" + x + ".pdf";// 路径下一定要有此文件  
		BaseFont baseFontChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);  
		Font fontChinese = new Font(baseFontChinese, 12, Font.NORMAL);  
		Font text = new Font(baseFontChinese, 12, Font.NORMAL); 
		text.setColor(new BaseColor(235,235,235));
		// step 1  
		Document document = new Document();  
		// step 2  
		try {  
			outr = new FileOutputStream(fileDesc);  
			PdfWriter.getInstance(document, outr);  
			// step 3  
			document.open();  
			//设立文档对象的相关属性
			document.addTitle("pdf");
			document.addSubject("java creat pdf");
			document.addProducer();
			document.addAuthor("cnq");
			document.addKeywords("报表");
			document.addCreationDate();    
			//添加主体内容
			Paragraph p1=new Paragraph("txt1",text);
			document.add(p1);
			// step 4  
			PdfPTable table = new PdfPTable(3);  
			// the cell object  
			PdfPCell cell;  
			// we add a cell with colspan3  
			cell = new PdfPCell(new Phrase("Cell with colspan 3"));  
			cell.setColspan(3);  
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);//设置居中对齐
			table.addCell(cell);  
			//设置表格的相关属性
			float[] widths={0.40f,0.60f,0.30f};
			table.setWidths(widths);//设置表格的列宽
			table.setWidthPercentage(100);//设置表格的列宽百分比
			table.setTotalWidth(200);//设置表格的总宽
			// now we add a cell with rowspan2  
			cell = new PdfPCell(new Phrase("Cell with rowspan 2 跨行", fontChinese));  
			cell.setBackgroundColor(new BaseColor(235,235,235));
			cell.setBorderWidth(2);  
			cell.setRowspan(2);  
			// cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);  
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);  

			//加入隔行换色事件  
			PdfPTableEvent event = new AlternatingBackground();  
			table.setTableEvent(event);  
			//end  

			table.addCell(cell);  
			// we add the four remaining cells with addCell()  
			table.addCell("row 1-1; cell 1");  
			table.addCell("row 1-2; cell 2");  

			cell = new PdfPCell(new Phrase("Cell with rowspan 2 跨行", fontChinese));  
			table.addCell("row 2-1; cell 1");  
			table.addCell("row 2-2; cell 2");  

			table.addCell("row 3-1; cell 1");  
			table.addCell("row 3-2; cell 2");  
			table.addCell("row 3-3; cell 3");  		

			Image image = Image.getInstance ("F:\\WIN10\\Dropbox\\photo\\phone\\IMG_20160116_131247.JPG");
			image.scaleAbsolute(100,100);//图片大小
			image.setAbsolutePosition(10,600);//图片位置，xy坐标
			document.add(image); 

			document.add(table);  
			document.close();  
		} catch (DocumentException e1) {  
			e1.printStackTrace();  
		} finally {  
			if (outr != null) {  
				outr.close();  
			} 
		}  
	}  

} 
