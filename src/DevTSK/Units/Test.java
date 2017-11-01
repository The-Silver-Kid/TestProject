package DevTSK.Units;

import DevTSK.Units.Defaults.Data;

public class Test {

	public static void main(String[] args) {
		Weapon[] weapons = Data.getDefaultWeapons();
		Xlass[] xlasses = Data.getDefaultClasses();
		/*for (int i = 0; i < xlasses.length; i++)
			System.err.println(i + ":" + xlasses[i].getName());
		for (int i = 0; i < weapons.length; i++) 
			System.err.println(i + ":" + weapons[i].name);*/
		Unit chrom = new Unit("Chrom", xlasses[2], weapons[1], true, new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 4 });
		Unit alm = new Unit("Alm", xlasses[2], weapons[0], true, new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 4 });

		for (int i = 0; i < 5; i++) {
			chrom.doLevel();
			alm.doLevel();
		}
		System.out.println(alm.getStat(Unit.RESISTANCE) + "/" + alm.getStat(Unit.DEFENSE) + ":" + chrom.getStat(Unit.ATTACK));
		Battle b = new Battle(new Unit[] { chrom }, new Unit[] { alm });
		b.doBattle(0, 0, true);
	}

}
