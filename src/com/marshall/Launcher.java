package com.marshall;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.nio.file.*;

import javax.swing.JOptionPane;

public class Launcher {

	public static final String
	CLIENT_PARENT = System.getProperty("user.home"),
	CLIENT_PATH = String.format("%s/BlissScapeClient.jar", CLIENT_PARENT),
	CLIENT_ROUTE = "https://blissscape.com/downloads/BlissScapeClient.jar",
	CLIENT_EXECUTE = String.format("java -jar %s", CLIENT_PATH);
	
	
	
	public static void downloadFile(String fileURL, String savePath) throws IOException {
		URL url = new URL(fileURL);
		Path targetPath = Paths.get(savePath);
		Files.copy(url.openStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);
	}
	
	public static String transpileStackTrace(Throwable throwable) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		throwable.printStackTrace(pw);
		return sw.toString();
	}
   
	public static void main(String[] args) {
		
		try {
			downloadFile(CLIENT_ROUTE, CLIENT_PATH);
			Runtime.getRuntime().exec(CLIENT_EXECUTE);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, String.format("Failed to download client!", transpileStackTrace(e)), "BlissScape - Headless", JOptionPane.ERROR_MESSAGE);
		}
	}

}
