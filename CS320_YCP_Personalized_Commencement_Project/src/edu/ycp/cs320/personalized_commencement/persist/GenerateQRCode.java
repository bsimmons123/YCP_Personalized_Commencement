package edu.ycp.cs320.personalized_commencement.persist;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;  
import java.util.Map;  
import com.google.zxing.BarcodeFormat;  
import com.google.zxing.EncodeHintType;  
import com.google.zxing.MultiFormatWriter;  
import com.google.zxing.NotFoundException;  
import com.google.zxing.WriterException;  
import com.google.zxing.client.j2se.MatrixToImageWriter;  
import com.google.zxing.common.BitMatrix;  
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;  

public class GenerateQRCode {
	
	/**
	 * Static function that creates QR Code  
	 * 
	 * @param data		String of data to store (for this project it will be the student's id number in the database tables).
	 * @param path		String of the path to store the code in
	 * @param charset	String of the charset type to be used (commonly UTF-8)
	 * @param map		Map containing EncodeHintType and ErrorCorrectionLevel
	 * @param h			int Height of the code
	 * @param w			int Width of the code
	 * 
	 * @throws WriterException		
	 * @throws IOException
	 */
	public static void generateQRcode(String data, String path, String charset, Map<EncodeHintType, ErrorCorrectionLevel> map, int h, int w) throws WriterException, IOException {  
	
		// convert string path to path object
		Path dataPath = Paths.get(path); 
		
		// The BitMatrix class represents the 2D matrix of bits
		BitMatrix matrix = new MultiFormatWriter().encode(new String(data.getBytes(charset), charset), BarcodeFormat.QR_CODE, w, h);
		
		// MultiFormatWriter is a factory class that finds the appropriate Writer subclass for the BarcodeFormat requested and encodes the barcode with the supplied contents.    
		MatrixToImageWriter.writeToPath(matrix, path.substring(path.lastIndexOf('.') + 1), dataPath);  
	}  
	
	/**
	 * Main method.  Just an example to run if unsure of exactly what data goes into the qr code.
	 */
	public static void main(String args[]) throws WriterException, IOException, NotFoundException {  
	
		//data that we want to store in the QR code 
		String str= "https://www.twitch.tv/b0bgurtWoog";  
		
		//path where we want to get QR Code
		String path = "C:\\Users\\robertWood\\git\\YCP_Personalized_Commencement\\CS320_YCP_Personalized_Commencement_Project\\war\\browser-images\\LiveStreamQR.png";  
		
		//Encoding charset to be used  
		String charset = "UTF-8";  
		Map<EncodeHintType, ErrorCorrectionLevel> hashMap = new HashMap<EncodeHintType, ErrorCorrectionLevel>();  
		
		//generates QR code with Low level(L) error correction capability  
		hashMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);  
		
		//invoking the user-defined method that creates the QR code  
		generateQRcode(str, path, charset, hashMap, 200, 200); // increase or decrease height and width accodingly   
		
		//prints if the QR code is generated   
		System.out.println("QR Code created successfully.");  
	}  
}
