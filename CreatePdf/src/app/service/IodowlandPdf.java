package app.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class IodowlandPdf {
	static HttpServletResponse response=null;
	static HttpServletRequest request=null;
	public static void main(String[]args) throws IOException{

		FileInputStream fis=new FileInputStream("D:\\test.pdf");
		byte[] b1=new byte[fis.available()];
		fis.read(b1);
		FileOutputStream out=new FileOutputStream("D:\\t.pdf");
		out.write(b1);
		fis.close();
		out.close();

		//		//获取要下载的文件名
		//        String filename ="test";
		//        //得到想客服端输出的输出流
		//        OutputStream outputStream=out;
		//        //输出文件用的字节数组，每次向输出流发送600个字节
		//        byte b[] = new byte[600];
		//        //要下载的文件
		//        File fileload = new File("D:\\",filename);        
		//        //客服端使用保存文件的对话框
		//        response.setHeader("Content-disposition", "attachment;filename="+filename);
		//        //通知客服文件的MIME类型
		//        response.setContentType("application/msword");
		//        //通知客服文件的长度
		//        long fileLength = fileload.length();
		//        String length = String.valueOf(fileLength);
		//        response.setHeader("Content_length", length);
		//        //读取文件，并发送给客服端下载
		//        FileInputStream inputStream = new FileInputStream(fileload);
		//        int n = 0;
		//        while((n=inputStream.read(b))!=-1){
		//        	outputStream.write(b,0,n);
		//        }
	}
}
