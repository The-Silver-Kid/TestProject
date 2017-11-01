package DevTSK.Units;

import java.util.Random;

public class Unit {
	private int hp = 0, atk = 0, def = 0, res = 0, spd = 0, skl = 0, luck = 0, mov = 0, level = 0;
	private Weapon w;
	private Xlass c;
	private final String nom;
	private boolean dead = false;
	public int[] statUp = new int[7];
	private int[] isType;
	public final boolean isCommander;
	private final String deathState;

	public static final int LEVEL = 0, ATTACK = 1, DEFENSE = 2, RESISTANCE = 3, SPEED = 4, SKILL = 5, LUCK = 6, MOVEMENT = 7, HP = 8;
	public static final int ARMOURED = 0, FLYER = 1, INFANTRY = 2, DRAGON = 3, MOUNTED = 4;

	public Unit(String name, Xlass clas, Weapon wep, boolean isCommander, int[] types) {
		w = wep;
		c = clas;
		nom = name;
		this.isCommander = isCommander;
		deathState = "";
		isType = types;
	}

	public Unit(String name, Xlass clas, Weapon wep, boolean isCommander, int[] statGains, int[] types) {
		w = wep;
		c = clas;
		nom = name;
		level = statGains[0];
		atk = statGains[1];
		def = statGains[2];
		res = statGains[3];
		spd = statGains[4];
		skl = statGains[5];
		luck = statGains[6];
		mov = statGains[7];
		hp = statGains[8];
		this.isCommander = isCommander;
		deathState = "";
		isType = types;
	}

	public Unit(String name, Xlass clas, Weapon wep, boolean isCommander, String death) {
		w = wep;
		c = clas;
		nom = name;
		this.isCommander = isCommander;
		deathState = death;
	}

	public Unit(String name, Xlass clas, Weapon wep, boolean isCommander, int[] statGains, String death) {
		w = wep;
		c = clas;
		nom = name;
		level = statGains[0];
		atk = statGains[1];
		def = statGains[2];
		res = statGains[3];
		spd = statGains[4];
		skl = statGains[5];
		luck = statGains[6];
		mov = statGains[7];
		hp = statGains[8];
		this.isCommander = isCommander;
		deathState = death;
	}

	public void doLevel() {
		level++;
		hp += getUp(Xlass.HP);
		atk += getUp(Xlass.ATTACK);
		def += getUp(Xlass.DEFENSE);
		res += getUp(Xlass.RESISTANCE);
		spd += getUp(Xlass.SPEED);
		skl += getUp(Xlass.SKILL);
		luck += getUp(Xlass.LUCK);
		//TODO use statUp
	}

	private int getUp(int stat) {
		//TODO HANDLE INDIVIDUAL GROWTH RATES
		int chance = (int) (100 * c.getGrowthRate(stat)), ret = 0;
		if (chance > 100)
			do {
				chance -= 100;
				ret++;
			} while (chance > 100);
		Random r = new Random();
		if (r.nextInt(100) < (chance - 1))
			ret++;
		return ret;
	}

	public void doClass() {
		//TODO : Handle this
	}

	public void deathToggle() {
		if (!dead)
			System.out.println(deathState);
		dead = !dead;
	}

	public boolean isDead() {
		return dead;
	}

	public void doLevel(int time) {
		int[] totals = new int[7];
		for (int i = 0; i < time; i++) {
			doLevel();
			for (int ii = 0; ii < statUp.length; ii++)
				totals[ii] += statUp.length;
		}
		statUp = totals;
	}

	public int getStat(int statNumber) {
		switch (statNumber) {
		case 0:
			return level;
		case 1:
			return atk + c.getBaseStat(Xlass.ATTACK);
		case 2:
			return def + c.getBaseStat(Xlass.DEFENSE);
		case 3:
			return res + c.getBaseStat(Xlass.RESISTANCE);
		case 4:
			return spd + c.getBaseStat(Xlass.SPEED);
		case 5:
			return skl + c.getBaseStat(Xlass.SKILL);
		case 6:
			return luck + c.getBaseStat(Xlass.LUCK);
		case 7:
			return mov + c.getBaseStat(Xlass.MOVEMENT);
		case 8:
			return hp + c.getBaseStat(Xlass.HP);
		}
		return 0;
	}

	public int getRawStat(int statNumber) {
		switch (statNumber) {
		case 0:
			return level;
		case 1:
			return atk;
		case 2:
			return def;
		case 3:
			return res;
		case 4:
			return spd;
		case 5:
			return skl;
		case 6:
			return luck;
		case 7:
			return mov;
		case 8:
			return hp;
		}
		return 0;
	}

	public Weapon getWeapon() {
		return w;
	}

	public void setWeapon(Weapon w) {
		this.w = w;
	}

	public String getNom() {
		return nom;
	}

	public Xlass getXlass() {
		return c;
	}

	public boolean canUseWeapon() {
		return c.canUseWeapon(w);
	}

	public int[] getType() {
		return isType;
	}
}
