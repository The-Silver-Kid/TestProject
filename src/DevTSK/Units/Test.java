package DevTSK.Units;

import DevTSK.Units.Defaults.Defaults;

public class Test {

	public static void main(String[] args) {
		Weapon[] weapons = Defaults.getDefaultWeapons();
		Xlass[] xlasses = Defaults.getDefaultClasses();
		/*for (int i = 0; i < xlasses.length; i++)
			System.err.println(i + ":" + xlasses[i].getName());
		for (int i = 0; i < weapons.length; i++)
			System.err.println(i + ":" + weapons[i].name);*/
		Unit chrom = new Unit("Chrom", xlasses[1], weapons[0], true, new int[] { 1, 5, 4, 3, 10, 1, 4, 9, 4 });
		Unit alm = new Unit("Alm", xlasses[0], weapons[1], true, new int[] { 1, 5, 4, 3, 10, 1, 4, 9, 4 });

		Battle b = new Battle(new Unit[] { chrom }, new Unit[] { alm });
		b.doBattle(0, 0, true);
	}

}
