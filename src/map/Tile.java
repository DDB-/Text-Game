package map;

/**
 * Tile class represents a text based tile that will be part of a 
 * Map of Tiles. Each Tile can have up to 4 obstacles on it, 1 on 
 * each of its 4 sides. It also has various event lines that are
 * optionally filled with values.
 * 
 * @author Ryan Petty
 *
 */
public class Tile {
	/** Obstacles represents the various obstacles on the various directions
	 * of a Tile. The indices 0, 1, 2, 3 will correspond to the directions
	 * North, East, South, West or Up, Right, Down, Left respectively
	 */
	private String[] obstacles;
	/**
	 * @param beforeEventLine is the line of text for the tile before the tile significant event happens<br/>
	 * @param afterEventLine is the line of text for the tile after the tile significant event happens<br/>
	 * @param tileItem is the item located on the tile<br/>
	 * @see NOTE: For all values, a NULL string denotes there is none for that tile
	 */
	private String beforeEventLine, afterEventLine, tileItem;
	
	private boolean currentTile, canDig;
	
	private int xCoordinate, yCoordinate;
	
	public Tile(int _xCoordinate, int _yCoordinate){
		obstacles = new String[4];
		obstacles[0] = "North"; obstacles[1] = "East"; obstacles[2] = "South"; obstacles[3] = "West";
		beforeEventLine = "Event hasn't happened";
		afterEventLine = "Event has happened";
		tileItem = "Item";
		
		currentTile = false;
		canDig = false;
		
		this.xCoordinate = _xCoordinate;
		this.yCoordinate = _yCoordinate;
		//TODO: Setup the values here initially. Not sure where the values will come from yet
	}
	
	public String[] getObstacles(){
		return this.obstacles;
	}
	
	public void setObstacles(String[] _obstacles){
		this.obstacles = _obstacles;
	}
	
	public String getBeforeEventLine(){
		return this.beforeEventLine;
	}
	
	public void setBeforeEventLine(String _beforeEventLine){
		this.beforeEventLine = _beforeEventLine;
	}
	
	public String getAfterEventLine(){
		return this.afterEventLine;
	}
	
	public void setAfterEventLine(String _afterEventLine){
		this.afterEventLine = _afterEventLine;
	}
	
	public String getTileItem(){
		return this.tileItem;
	}
	
	public void setTileItem(String _tileItem){
		this.tileItem = _tileItem;
	}
	
	public boolean getDigLine(){
		return this.canDig;
	}
	
	public void setDigLine(boolean _canDig){
		this.canDig = _canDig;
	}
	
	public boolean getCurrentTile() {
		return this.currentTile;
	}
	
	public void setCurrentTile(boolean _currentTile){
		this.currentTile = _currentTile;
	}
}
