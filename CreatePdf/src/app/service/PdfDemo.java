package app.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.imageio.stream.FileImageInputStream;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPTableEvent;
import com.itextpdf.text.pdf.PdfWriter;

import app.util.AlternatingBackground;

public class PdfDemo {
	public static void main(String[] args) throws IOException, DocumentException{
		String fileDesc = "";  
		FileOutputStream outr = null;// 创建输出流  
		// 生成随机数  
		Random random = new Random();  
		int x = random.nextInt();  
		fileDesc = "d:\\ _" + x + ".pdf";
		Date date=new Date();
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		BaseFont baseFontChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);  
		BaseFont font1 = BaseFont.createFont("c:\\Windows\\Fonts\\simkai.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
		Font title1 = new Font(font1,12, Font.BOLD); 
		title1.setColor(new BaseColor(150,32,0));
		Font title2 = new Font(baseFontChinese,12,  Font.NORMAL); 
		Font title3 = new Font(baseFontChinese,12, Font.NORMAL); 
		Font title4 = new Font(baseFontChinese,8, Font.NORMAL); 
		Font fontChinese = new Font(baseFontChinese, 12, Font.NORMAL);  

		int y_line1 = 650;
		int y_line2 = y_line1 - 50;
		int y_line3 = y_line2 - 50;

		// step 1  
		Document document = new Document();  
		try {
			// step 2  
			outr = new FileOutputStream(fileDesc);  
			PdfWriter writer =PdfWriter.getInstance(document, outr);  
			writer.setEncryption(null, null,PdfWriter.ALLOW_PRINTING, PdfWriter.ENCRYPTION_AES_128);//不能修改
			// step 3  
			document.open();  
			/* 获得文本写入器 */
			PdfContentByte cb =writer.getDirectContent();
			//设立文档对象的相关属性
			document.addTitle("报表");
			document.addSubject("报表模板");
			document.addAuthor("nelson");
			document.addKeywords("123456");
			document.addCreationDate();
			//PDF版本(默认1.4)  
			writer.setPdfVersion(PdfWriter.PDF_VERSION_1_2);  
			//页面大小  
			Rectangle rect = new Rectangle(PageSize.B5.rotate());  
			//页面背景色  
			rect.setBackgroundColor(BaseColor.WHITE);  
			//页边空白  
			document.setMargins(10, 20, 30, 40);  
			// step 4  
			//添加主体内容
			Paragraph p1=new Paragraph("管理费用明细表",title1);
			p1.setAlignment(Element.ALIGN_CENTER);//设置居中对齐
			document.add(p1);
			Paragraph p2=new Paragraph("Cost Management Schedule",title2);
			p2.setAlignment(Element.ALIGN_CENTER);
			document.add(p2);
			Paragraph p3=new Paragraph(format.format(date),title3);
			p3.setAlignment(Element.ALIGN_CENTER);
			//设置上端的空白距离
			p3.setSpacingBefore(15);
			//设置下端的空白距离
			p3.setSpacingAfter(15);
			document.add(p3);

			Paragraph p4=new Paragraph("02附表",title4);
			p4.setAlignment(Element.ALIGN_RIGHT);
			document.add(p4);
			cb.beginText();
			cb.setFontAndSize(baseFontChinese, 12);
			cb.showTextAligned(PdfContentByte.ALIGN_CENTER, "02附表",100, 500, 0);
			cb.endText();

			Paragraph p5=new Paragraph("单位：元",title4);
			p5.setAlignment(Element.ALIGN_RIGHT);
			document.add(p5);

			PdfPTable table = new PdfPTable(5);   
			// we add a cell with colspan5  
			PdfPCell cell = new PdfPCell(new Phrase("项目",fontChinese));  
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);//设置居中对齐
			cell.setBackgroundColor(new BaseColor(75,141,218));
			PdfPCell cell2 = new PdfPCell(new Phrase("行次",fontChinese));  
			cell2.setHorizontalAlignment(Element.ALIGN_CENTER);//设置居中对齐
			PdfPCell cell3 = new PdfPCell(new Phrase("计划",fontChinese));  
			cell3.setHorizontalAlignment(Element.ALIGN_CENTER);//设置居中对齐
			cell3.setBackgroundColor(new BaseColor(75,141,218));
			PdfPCell cell4 = new PdfPCell(new Phrase("实际",fontChinese));  
			cell4.setHorizontalAlignment(Element.ALIGN_CENTER);//设置居中对齐
			PdfPCell cell5 = new PdfPCell(new Phrase("对比",fontChinese));  
			cell5.setHorizontalAlignment(Element.ALIGN_CENTER);//设置居中对齐
			cell5.setBackgroundColor(new BaseColor(75,141,218));

			table.addCell(cell);  
			table.addCell(cell2);  
			table.addCell(cell3);  
			table.addCell(cell4);  
			table.addCell(cell5);  

			//设置表格的相关属性
			float[] widths={0.40f,0.20f,0.20f,0.20f,0.20f};
			table.setWidths(widths);//设置表格的列宽
			table.setWidthPercentage(100);//设置表格的列宽百分比
			table.setTotalWidth(300);//设置表格的总宽
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);  

			//加入隔行换色事件  
			PdfPTableEvent event = new AlternatingBackground();  
			table.setTableEvent(event);  

			// we add the four remaining cells with addCell()  
			table.addCell("row 1-1;");  
			table.addCell("row 1-2;");  
			table.addCell("row 1-3;"); 
			table.addCell("row 1-4;"); 
			table.addCell("row 1-5;"); 

			table.addCell("row 2-1;");  
			table.addCell("row 2-2;"); 
			table.addCell("row 2-3;");
			table.addCell("row 2-4;");
			table.addCell("row 2-5;");

			table.addCell("row 3-1;");  
			table.addCell("row 3-2;");  
			table.addCell("row 3-3;");  
			table.addCell("row 3-4;");  
			table.addCell("row 3-5;");  

			table.writeSelectedRows(0,-1,300,600,cb);

			//document.add(table);  

			Paragraph p6=new Paragraph("数字签名：",title3);
			p6.setAlignment(Element.ALIGN_LEFT);

			document.add(p6);

			Image image = Image.getInstance ("http://7xpwa8.com1.z0.glb.clouddn.com/image/res/QQ%E5%9B%BE%E7%89%8720160531150356.png");
			image.scaleAbsolute(300,100);//图片大小
			image.setAbsolutePosition(10,600);//图片位置，xy坐标
			document.add(image); 

			File imageFile = new File("F:\\WIN10\\Dropbox\\photo\\DoPhoto\\_MG_2827.jpg");
			byte[] b = setImageToByteArray(imageFile);
			Image image2= Image.getInstance(b);
			image2.scaleAbsolute(100,60);
			image2.setSpacingBefore(25);
			image2.setSpacingAfter(25);
			document.add(image2);

			document.newPage();

			cb.setLineWidth(0f);
			cb.moveTo(250, y_line3 - 100);
			cb.lineTo(250, y_line1 + 100);
			cb.moveTo(50, y_line1);
			cb.lineTo(400, y_line1);
			cb.moveTo(50, y_line2);
			cb.lineTo(400, y_line2);
			cb.moveTo(50, y_line3);
			cb.lineTo(400, y_line3);
			cb.stroke();
			cb.beginText();
			cb.setFontAndSize(baseFontChinese, 12);
			String text = "Sample text for alignment";
			cb.showTextAligned(PdfContentByte.ALIGN_CENTER, text + " Center", 250, y_line1, 0);
			cb.showTextAligned(PdfContentByte.ALIGN_RIGHT, text + " Right", 250, y_line2, 0);
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT, text + " Left", 250, y_line3, 0);
			cb.endText();

			document.close();  
		} catch (DocumentException e1) {  
			e1.printStackTrace();  
		} finally {  
			if (outr != null) {  
				outr.close();  
			} 
		}  
	}
	//将图片文件转换成二进制流
	public static byte[] setImageToByteArray(File filename){
		byte[] image = null;
		try {
			FileImageInputStream fis;
			fis = new FileImageInputStream(filename);
			int streamLength = (int) fis.length();
			image = new byte[streamLength];
			fis.read(image,0,streamLength);
			fis.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return image;
	}
}
