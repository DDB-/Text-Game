package main;

import xml.XMLUtil;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import map.Map;
import map.Tile;

public class GameController {
	
	public static void main(String[] args){
		Map map = new Map(5, 5);
		
		XStream x = new XStream(new DomDriver());
		
		x.alias("tile", Tile.class);
		x.alias("map", Map.class);
		
		String xml = x.toXML(map);
		XMLUtil.WriteSave("smallAdv", xml);
	}
	
}
