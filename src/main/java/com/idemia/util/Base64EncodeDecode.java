/**
 * 
 */
package com.idemia.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.codec.binary.Base64;



/**
 * @author G521980
 *
 */
public class Base64EncodeDecode {

	
	public static String encodeImageto64(byte[] bytes) {
	    String encodedfile = null;
	    try {       
	        encodedfile = new String(Base64.encodeBase64(bytes), "UTF-8");
	    }
	    catch (IOException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    }

	    return encodedfile;
	}
	
public static void decodImage(String imageString) throws IOException {
	
	//Base64 decoder = new Base64(); 
	FileOutputStream fos=null;
	try {
		fos = new FileOutputStream("C:\\Users\\G521980\\Desktop\\Image\\ReverseImage\\3mb.jpg");
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} //change path of image according to you
	byte[] byteArray = Base64.decodeBase64(imageString);
	fos.write(byteArray);
	fos.close();
	
	
	
	}


public static void main(String[] args) throws IOException {
	 File f =  new File("C:\\Users\\G521980\\Desktop\\Image\\IMG_4645_low.jpg");
     String encodstring = encodeFileToBase64Binary(f);
     System.out.println("Encoding Successfull.............\n");
    // decodImage(encodstring);
    // System.out.println("Decoding Image Successfull.............\n");
     //System.out.println(encodstring);
}

private static String encodeFileToBase64Binary(File file){
    String encodedfile = null;
    try {
    	long start1 = System.nanoTime();
        FileInputStream fileInputStreamReader = new FileInputStream(file);
        byte[] bytes = new byte[(int)file.length()];
        fileInputStreamReader.read(bytes);
       // = Base64.encodeBase64(bytes).toString();
        
        encodedfile = new String(Base64.encodeBase64(bytes), "UTF-8");
       // System.out.println(encodedfile);
        long end1 = System.nanoTime();
		   System.out.println("#######################################################");
		   System.out.println("Time in milliseconds......"+TimeUnit.NANOSECONDS.toMillis(end1 - start1));
    } catch (FileNotFoundException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }

    return encodedfile;
}
}

