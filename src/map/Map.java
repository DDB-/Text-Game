package map;

public class Map {
	private Tile[][] tiles;
	int height, width;
	
	public Map(int _height, int _width){
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
}
