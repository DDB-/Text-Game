package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import map.GameMap;
import map.Tile;
import util.Loader;
import util.ReaderWriter;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class GameController {
	
	public static void main(String[] args){
		/*Map map = new Map(5, 5);
		
		XStream x = new XStream(new DomDriver());
		
		x.alias("tile", Tile.class);
		x.alias("map", Map.class);
		
		String xml = x.toXML(map);
		ReaderWriter.writeSave("smallSave", xml);
		java.util.Map<String, String> nXml = ReaderWriter.loadExistingGame("smallSave");
		Map newMap = (Map)x.fromXML(nXml.get(ReaderWriter.MAP_FILE));
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
		loader.loadGame();
	}
	
}
