package DevTSK.Units;

public class Tile {
	private int type;
	private String name;
	private TileName tilename;

	public static final int GROUND = 0, LOW_WALL = 1, WATER = 2, TREE = 3, FORT = 4, WALL = 5;

	public Tile(String name, TileName tilename, int type) {
		this.name = (name);
		this.type = (type);
		this.tilename = (tilename);
	}

	public TileName getTilename() {
		return tilename;
	}

	public String getName() {
		return name;
	}

	public int getType() {
		return type;
	}
}
