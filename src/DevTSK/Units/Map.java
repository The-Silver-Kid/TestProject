package DevTSK.Units;

public class Map {

	private Tile[][] tiles;

	private String music, name;

	public Map(String name, Tile[][] tiles, String music) {
		this.name = name;
		this.tiles = tiles;
		this.music = music;
	}

	public String getTrack() {
		return music;
	}

	public String getName() {
		return name;
	}

	public Tile[][] getTiles() {
		return tiles;
	}

	public Tile getTile(int x, int y) {
		return tiles[x][y];
	}
	//other data. cutscenes. music flags etc.
}
