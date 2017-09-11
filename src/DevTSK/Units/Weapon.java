package DevTSK.Units;

public class Weapon {
	public final int rng, atk, crit, type;
	public final double hit;
	public final String efns, name;
	public static final int SWORD = 0,
			AX = 1,
			SPEAR = 2,
			BOW = 3,
			ANIMA = 4,
			DARK = 5,
			LIGHT = 6,
			ENERGY = 7,
			DIVINE = 8;

	public final boolean isMagic, isReaver;

	public Weapon(String name, int type, int rng, int atk, int crit, double hit, String effectiveness, boolean magic, boolean reaver) {
		this.name = name;
		this.type = type;
		this.atk = atk;
		this.rng = rng;
		this.crit = crit;
		this.hit = hit;
		efns = effectiveness;
		isMagic = magic;
		isReaver = reaver;
	}
}
