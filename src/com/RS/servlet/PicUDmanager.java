package com.RS.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import jdk.internal.org.xml.sax.InputSource;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class PicUDmanager
 */
@WebServlet(urlPatterns={"/upload","/download"})
@MultipartConfig(fileSizeThreshold=1024*1024)
public class PicUDmanager extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 private boolean isMultipart;
   private String filePath="I:\\";
   private int maxFileSize = 50 * 1024;
   private int maxMemSize = 4 * 1024;
   private File file ;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PicUDmanager() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//doUpload
		Part uploadFile = request.getPart("file");
		File file = new File("I:\\testResult.jpg");
		FileOutputStream out = new FileOutputStream(file);
		InputStream in = uploadFile.getInputStream();
		if(!file.exists()){
			file.createNewFile();
		}else{
			file.delete();
			file.createNewFile();
		}
		byte[] buf = new byte[1024];
		int len;
		while((len = in.read(buf))!=-1){
			out.write(buf,0,len);
			out.flush();
		}
		out.close();
	}

}
