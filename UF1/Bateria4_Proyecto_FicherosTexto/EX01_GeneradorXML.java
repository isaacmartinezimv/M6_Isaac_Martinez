package Bateria4_Proyecto_FicherosTexto;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.*;
import java.nio.file.Files;

public class EX01_GeneradorXML {
	
	public static String logFileRoute = "";
	public static int fileRecount = 0;
	final static String csvFileExtension = ".csv";
	final static String textFileExtension = ".txt";
	final static String xmlFileExtension = ".xml";
	public static String mainDirectory = "";
	
	
	public static void main(String[] args) throws IOException {
				// Pedimos el directorio o la ruta del arvhico csv
				System.out.println("Directorio del archivo/s CSV: ");
				BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
				String csvDirectory = reader.readLine();
				
				/* Para saber como convertir, comprobamos si se trata de una ruta que contine
				 * archivos csv o se trata de un fichero csv directamente */
				File fileCSV = new File(csvDirectory);
				Boolean csvIsDirectory = checkDirectory(fileCSV);
				
				// Creamos y empezamos a escribir en el log, en la misma ruta que el fichero
				writeLogFile(logFileRoute+"\\logFile.log", "Directorio del archivo/s CSV guardado.");

				// Introducimos el directorio donde se encuentra el archivo con etiquetas
				System.out.println("Ruta al archivo de etiquetas (sin extension): ");
				String tagFileDirectory = reader.readLine();
				File tagFile = new File(tagFileDirectory+textFileExtension);
				
				// Actualizamos el log
				writeLogFile(logFileRoute+"\\logFile.log", "Directorio de etiquetas guardado.");

				// Establecemos un directorio de salida
				System.out.println("Nombre fichero/s de salida (sin extension): ");
				String outputFileName = reader.readLine();
				
				//TODO : Que cambie el archivo CSV (multi CSV)
				// Hacemos la conversión en función de si se trata de un directorio o fichero
				if (csvIsDirectory == true) {
					searchCSV(fileCSV, csvFileExtension);
					File csvFile = new File (mainDirectory);
					for (int i=0; i < fileRecount; i++) {
						File outputDirectory = new File(csvDirectory+"\\"+outputFileName+(i+1)+xmlFileExtension);
						xmlConversion(tagFile, csvFile, outputDirectory);	
					}
					
				} else {
					String directory = fileCSV.getParent();
					File outputDirectory = new File(directory+"\\"+outputFileName+xmlFileExtension);
					xmlConversion(tagFile, fileCSV,outputDirectory);
				}
				
				writeLogFile(logFileRoute+"\\logFile.log", "Guardando directorio de salida...");
			}
	
	
	public static boolean checkDirectory(File ruta) {
		String outputDirectory = "";
		if (ruta.isDirectory()) {
			logFileRoute = ruta.toString();
			writeLogFile(logFileRoute+"\\logFile.log", "Es un direcotrio.");
			return true;
		} else {
			logFileRoute = ruta.getParent();
			writeLogFile(logFileRoute+"\\logFile.log", "Es un archivo.");
			return false;
		}		
	}
	
	public static void searchCSV(File folder, String extension) {
		for (File inputFile : folder.listFiles()) {
				String fileName = inputFile.getName();

				if (fileName.endsWith(extension)) {
					System.out.println(fileName);
					fileRecount += 1;
					mainDirectory = folder+"\\"+fileName;
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
	
	private static String[] pickUpLabels(BufferedReader labelReader) throws IOException {
		// Guardamos las etiquetas del archivo especificado
    	String label = null;
    	String labels = "";
    	while ((label = labelReader.readLine()) != null) {
    		labels += label + ",";
    	}
		return labels.split(",");
	}
	
	private static void xmlConversion(File labelFile, File dataFile, File outputFile) throws IOException {
		System.out.println("Iniciando conversion. Por favor, espere...");
		writeLogFile(logFileRoute+"\\logFile.log", "Start Conversion");
		
		BufferedReader labelsReader = new BufferedReader(new FileReader(labelFile));
		BufferedReader dataReader = new BufferedReader(new FileReader(dataFile));
		BufferedWriter outputWriter = new BufferedWriter(new FileWriter(outputFile));
		
		// Generamos la etiqueta raiz
		outputWriter.write(String.format("<%s>", "raiz") + "\n");
		
		String[] labels = pickUpLabels(labelsReader);
		String csvLine = null;
		int lineNumber = 0;
		String textLine = ""; 
		String element = "";
		
		while ((csvLine = dataReader.readLine()) != null) {
			// Lee linea a linea el archivo y guarda la informacion
			String[] csvFullData = csvLine.split(",\\s?");
			
			int max = csvFullData.length;
			if (labels.length > max) {
				max = labels.length;
			}
			
			String elements = "";
			for (int i = 0; i < max; i++) {
				String currentData = "";
				try {
					currentData = csvFullData[i];
				} catch(Exception e) {}
				currentData = csvFullData[i];
				
				String currentLabel = "otro";
				try {
					currentLabel = labels[i];
				} catch(Exception e) {}
				 
				elements += "\t" + "\t" + String.format("<%s>", currentLabel);
				elements += currentData + String.format("</%s>\n", currentLabel);
			}
			
			element = "elem nr=\"" + ++lineNumber + "\"";
			textLine = "\t" + String.format("<%s>", element) + "\n";
			textLine += elements + "\t" + (String.format("</%s>", "elem") + "\n");
			outputWriter.write(textLine);
		}
		
		// Se general la etiqueta de cierre del elemento raiz
		outputWriter.write(String.format("</%s>", "raiz"));
		writeLogFile(logFileRoute+"\\logFile.log", "Conversion realizada con exito.");
		System.out.println("Â¡Â¡Conversion realizada!!\n");
	}
}
