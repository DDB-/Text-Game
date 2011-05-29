package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ReaderWriter {
	public static final String DEFAULT_BASE_ADVENTURE_DIR = "baseAdventures/";
	public static final String DEFAULT_SAVES_DIR = "saves/";
	public static final String MAP_FILE = "-map";
	public static final String INVENTORY_FILE = "-inv";
	public static final String XML_EXTENSION = ".xml";
	public static final String SPLIT_DELIMETER = "-";
	
	/**
	 * This method writes passed in XML to a specified save name
	 * in the default game save directory 
	 * 
	 * @param fileName Name of the file to save
	 * @param xmlContent XML Content to be written
	 * @return True on success, False on Exception/Error
	 */
	public static boolean writeSave(String fileName, Map<String, String> xmlContent) {
		File dir = new File(DEFAULT_SAVES_DIR + fileName.split(SPLIT_DELIMETER)[0]);
		
		if(!dir.isDirectory()) { // Make Directory if it doesn't exist.
			dir.mkdir();
		}
		
		//These are files for Map and Inventory data of a saved game
		File mapFile = new File(dir.getPath() + "/" + fileName + MAP_FILE + XML_EXTENSION);
		File invFile = new File(dir.getPath() + "/" + fileName + INVENTORY_FILE + XML_EXTENSION);
		
		if(mapFile.exists()){
			System.out.println("ERROR: Save already exists with this name");
			return false;
		}
		
		try {
			for(Map.Entry<String, String> entry : xmlContent.entrySet()){
				PrintWriter pw = entry.getKey().equals(MAP_FILE) ? 
						new PrintWriter(mapFile) : new PrintWriter(invFile);
				pw.flush();
				pw.print(entry.getValue());
				pw.close();
			}
		} catch (FileNotFoundException e) {
			System.out.println("Error writing save to file for " + fileName);
			return false;
		}
		
		return true;
	}
	
	/**
	 * This function loads all the data for a brand new game
	 * 
	 * @param adventureName Name of the adventure to be played
	 * @return XML Content representing that adventure or null on failure
	 */
	public static String loadNewGame(String adventureName) {
		File adventureFile = new File(DEFAULT_BASE_ADVENTURE_DIR + adventureName + XML_EXTENSION);
		
		if(!adventureFile.exists()){
			System.out.println("No such file exists with the name " + adventureFile.toString());
			return null;
		}
		
		if(!adventureFile.isFile()){
			System.out.println("File " + adventureFile.toString() + " is not a valid file");
			return null;
		}
		
		try {
			String xmlContent = new Scanner(adventureFile).useDelimiter("\\Z").next();
			return xmlContent;
		} catch (FileNotFoundException e) {
			System.out.println("File Not Found with name " + adventureFile.toString());;
			return null;
		}  
	}
	
	public static Map<String, String> loadExistingGame(String saveName) {
		Map<String, String> objectMap = new HashMap<String, String>();
		// Map and Inventory files to be loaded
		File mapFile = new File(DEFAULT_SAVES_DIR + saveName + "/" + saveName + MAP_FILE + XML_EXTENSION);
		File invFile = new File(DEFAULT_SAVES_DIR + saveName + "/" + saveName + INVENTORY_FILE + XML_EXTENSION);
		
		if(!mapFile.exists()){
			System.out.println("No such file exists with the name " + mapFile.toString());
			return null;
		}
		
		if(!mapFile.isFile()){
			System.out.println("File " + mapFile.toString() + " is not a valid file");
			return null;
		}
		
		try {
			String xmlContent = new Scanner(mapFile).useDelimiter("\\Z").next();
			objectMap.put(MAP_FILE, xmlContent);
			
			xmlContent = new Scanner(invFile).useDelimiter("\\Z").next();
			objectMap.put(INVENTORY_FILE, xmlContent);
			
			return objectMap;
		} catch (FileNotFoundException e) {
			System.out.println("File Not Found with name " + mapFile.toString());;
			return null;
		} 
	}
}
