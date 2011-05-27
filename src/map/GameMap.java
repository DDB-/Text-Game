package map;

public class GameMap {
	private Tile[][] tiles;
	int height, width;
	
	public GameMap(int _height, int _width){
		this.height = _height;
		this.width = _width;
		initializeTiles();
		
	}
	
	private void initializeTiles(){
		tiles = new Tile[height][width];
		for(int i = 0; i < height; i++){
			for(int j = 0; j < width; j++){
				tiles[i][j] = new Tile(i, j);
			}
		}
	}
	
	public Tile getCurrentTile(){
		for(Tile[] ts : this.tiles){
			for(Tile t : ts){
				if(t.isCurrentTile())
					return t;
			}
		}
		return null;
	}
}
