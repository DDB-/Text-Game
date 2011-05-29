package util;

import static util.ReaderWriter.DEFAULT_BASE_ADVENTURE_DIR;
import static util.ReaderWriter.DEFAULT_SAVES_DIR;
import static util.ReaderWriter.INVENTORY_FILE;
import static util.ReaderWriter.MAP_FILE;
import static util.ReaderWriter.XML_EXTENSION;

import inv.Inventory;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import map.GameMap;
import map.Tile;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class Loader {
	
	public static final String GO_BACK = "go back";
	public static final String LIST_FILES = "show list";
	public static final String HELP = "help";
	
	// Reader to read from user
	BufferedReader br;
	
	// Boolean if it is a new game or not
	boolean isNewGame;
	
	// XStream stream to use for this class
	XStream xstream;
	
	// Base Directory which the adventures are looked at
	File baseDir;
	
	public Loader() {
		br = new BufferedReader(new InputStreamReader(System.in));
		isNewGame = isNewGame();
		xstream = new XStream(new DomDriver());
		
		baseDir = isNewGame ? new File(DEFAULT_BASE_ADVENTURE_DIR)
									: new File(DEFAULT_SAVES_DIR);
	}
	
	public HashMap<String, Object> loadGame(){
		// Directory to be used
		File dir;
		
		// Input from User
		String inputLine = null;
		
		// Object mappings to return
		HashMap<String, Object> objectMappings;
		
		for(;;){
			try {
				System.out.print("Enter a game to load: ");
				inputLine = br.readLine();
				dir = isNewGame ? new File(DEFAULT_BASE_ADVENTURE_DIR + inputLine + XML_EXTENSION) 
									: new File(DEFAULT_SAVES_DIR + inputLine);
				
				if(inputLine.equals(HELP)){
					printErrorHelp();
					continue;
				}
				
				if(LineParser.parseLine(inputLine).length > 1){
					if(!checkCases(inputLine, baseDir)){
						System.out.println("\nERROR: Incorrect Command or Too Many Argument for a game name");
						printErrorHelp();
					}
					continue;
				}
				
				if(dir.exists()){
					if(isNewGame){
						String content = ReaderWriter.loadNewGame(dir.getName().substring(0, dir.getName().indexOf(".")));
						objectMappings = makeHashMap(content);
					}
					else {
						Map<String, String> content = ReaderWriter.loadExistingGame(dir.getName());
						objectMappings = makeHashMap(content);
					}
					System.out.println("Successfully loaded " + inputLine);
					return objectMappings;
				}
				else {
					System.out.println("\nERROR: No such game exists with this name.");
					printErrorHelp();
				}

			} catch (IOException e) {
				System.out.println("Error reading command.");
			}
		}
	}
	
	/**
	 * This code is run at the start of the loader to determine if
	 * the user is attempting to load a new game or an existing game.
	 * 
	 * @return True if loading a new game, False is loading an existing one
	 */
	private boolean isNewGame() {
		String inputLine = null;
		
		for(;;){
			try {
				System.out.print("(N)ew game or (E)xisting game? ");
				inputLine = br.readLine();
				if(inputLine.equalsIgnoreCase("N")){
					return true;
				}
				else if(inputLine.equalsIgnoreCase("E")){
					return false;
				}
			} catch (IOException e) {
				System.out.println("Error reading command. Please try again.");
			}
		}
	}
	
	/**
	 * Makes a hash map to return for loadGame that has GameMap and 
	 * Inventory objects in it. Because it is a new game, the Inventory
	 * object will be a new inventory.
	 * 
	 * @param content String of Map XML content
	 * @return HashMap of Name of type of object with the Object it
	 */
	private HashMap<String, Object> makeHashMap(String content){
		if(content == null)
			return null;
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		map.put(MAP_FILE, (GameMap)xstream.fromXML(content));
		map.put(INVENTORY_FILE, new Inventory());
		
		return map;
	}
	
	
	/**
	 * Makes a hash map to return for loadGame that has GameMap and 
	 * Inventory objects in it.
	 * 
	 * @param content Map of XML Content type to XML Content
	 * @return HashMap of Name of type of object with the Object it
	 */
	private HashMap<String, Object> makeHashMap(Map<String, String> content){
		if (content == null)
			return null;
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		for(Map.Entry<String, String> entry : content.entrySet()){
			if(entry.getKey().equals(MAP_FILE))
				map.put(MAP_FILE, (GameMap)xstream.fromXML(entry.getValue()));
			else 
				map.put(INVENTORY_FILE, new Inventory());
		}
		
		return map;
	}
	
	/**
	 * Check the "go back" and "show list" cases. If those were input then
	 * I take the appropriate action and return true. Otherwise I return false
	 * as nothing good was entered.
	 * 
	 * @param input User input to be checked for cases
	 * @param dir Directory to scan if the "show list" action used
	 * @return True if a case happened, False if one didn't
	 */
	private boolean checkCases(String input, File dir) {
		if(input.trim().equals(GO_BACK)){
			isNewGame = isNewGame();
			return true;
		}
		else if(input.trim().equals(LIST_FILES)){
			System.out.println("\nLIST OF FILES:");
			for(File f : dir.listFiles()){
				System.out.println(f.getName().replaceFirst(XML_EXTENSION, ""));
			}
			System.out.println();
			return true;
		}
		else
			return false;
	}
	
	private void printErrorHelp(){
		System.out.println("- To load a game, type the name of the game to load");
		System.out.println("- To show a list of all possible games to load, type \"show list\"");
		System.out.println("- To go back to the New/Esiting select screen, type \"go back\"");
		System.out.println("- To view this message, type \"help\"\n");
	}
}
