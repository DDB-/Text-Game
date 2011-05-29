package util;

import static util.ReaderWriter.MAP_FILE;
import static util.ReaderWriter.INVENTORY_FILE;
import inv.Inventory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import map.GameMap;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class Saver {
	
	BufferedReader br;
	XStream xstream;
	String inputLine;
	
	public Saver() {
		br = new BufferedReader(new InputStreamReader(System.in));
		xstream = new XStream(new DomDriver());
		inputLine = null;
	}
	
	public boolean saveGame(Map<String, Object> mappedObjects){			
		try {
			System.out.print("\nEnter the name of your new game save: ");
			inputLine = br.readLine();
		} catch (IOException e) {
			System.out.println("ERROR: Cannot parse user input.");
			return false;
		}
		
		if(inputLine == null)
			return false;
		
		HashMap<String, String> xmlToWrite = new HashMap<String, String>();
		
		for(Map.Entry<String, Object> entry : mappedObjects.entrySet()){
			String content = null;
			if(entry.getKey().equals(MAP_FILE)) {
				content = xstream.toXML((GameMap)entry.getValue());
				xmlToWrite.put(MAP_FILE, content);
			}
			else {
				content = xstream.toXML((Inventory)entry.getValue());
				xmlToWrite.put(INVENTORY_FILE, content);
			}
			ReaderWriter.writeSave(inputLine, xmlToWrite);
		}
		return true;
	}
}
