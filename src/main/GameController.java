package main;

import static util.ReaderWriter.INVENTORY_FILE;
import static util.ReaderWriter.MAP_FILE;
import inv.Inventory;

import java.util.HashMap;

import map.GameMap;
import util.Loader;
import util.Saver;

public class GameController {
	
	public static void main(String[] args){
		/*GameMap map = new GameMap(5, 5);
		
		XStream x = new XStream(new DomDriver());
		
		String xml = x.toXML(map);
		Map<String, String> hashMap = new HashMap<String, String>();
		hashMap.put(MAP_FILE, xml);
		hashMap.put(INVENTORY_FILE, null);
		ReaderWriter.writeSave("smallSave", hashMap);
		java.util.Map<String, String> nXml = ReaderWriter.loadExistingGame("smallSave");
		GameMap newMap = (GameMap)x.fromXML(nXml.get(ReaderWriter.MAP_FILE));
		System.out.println(newMap.getCurrentTile());*/
		
		/*System.out.println("Enter a command: ");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String inputLine = null;
		try {
			inputLine = br.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Command: " + inputLine);*/
		
		Loader loader = new Loader();
		HashMap<String, Object> mappedObjects = loader.loadGame();
		GameMap gameMap = (GameMap)mappedObjects.get(MAP_FILE);
		Inventory inventory = (Inventory)mappedObjects.get(INVENTORY_FILE);
		
		Saver saver = new Saver();
		saver.saveGame(mappedObjects);
	}
	
}
