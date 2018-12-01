package Bateria4_Proyecto_FicherosTexto;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EX01_GeneradorXML {
	public static String logFileRoute = "";
	public static int fileRecount = 0;
	final static String csvFileExtension = ".csv";
	final static String textFileExtension = ".txt";
	final static String xmlFileExtension = ".xml";
	
	public static void main(String[] args) throws IOException {
	}
	
	public static boolean checkDirectory(File ruta) {
		String outputDirectory = "";
		if (ruta.isDirectory()) {
			logFileRoute = ruta.toString();
			writeLogFile(logFileRoute+"\\logFile.log", "Se trata de un direcotrio.");
			return true;
		} else {
			logFileRoute = ruta.getParent();
			writeLogFile(logFileRoute+"\\logFile.log", "Se trata de un archivo.");
			return false;
		}		
	}
	
	public static void searchCSV(File folder, String extension) {
		for (File inputFile : folder.listFiles()) {
			if (inputFile.isDirectory()) {
				searchCSV(inputFile, extension);
			} else {
				String fileName = inputFile.getName();

				if (fileName.endsWith(extension)) {
					System.out.println(fileName);
					fileRecount += 1;
					String completeDirectory = folder+fileName; 
				}	           
			}
		}
	}
	
	public static void writeLogFile (String mainDirectory, String registerText) {
		File logFile = new File (mainDirectory);

		try {
			if (!logFile.exists()) {
				logFile.createNewFile();
			}

			FileWriter log = new FileWriter(mainDirectory, true);
			DateFormat dateFormmat = new SimpleDateFormat("dd-MM-YYYY HH:mm:ss");
			Date date = new Date();
			log.write(dateFormmat.format(date) + " ---- " + registerText + "\r\n");
			log.close();

		} catch (Exception e) {
			System.out.println("Error salvaje aparecio!");
		}
	}
}
