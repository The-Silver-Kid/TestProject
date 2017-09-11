package DevTSK.Units;

import java.util.Random;

public class Battle {
	private Unit[] a, e;
	private boolean[] aa, ea;
	private int[] ac, ec;

	public Battle(Unit[] allies, Unit[] opponents) {
		a = allies;
		e = opponents;
		aa = new boolean[a.length];
		ea = new boolean[e.length];
		ac = new int[a.length];
		ec = new int[e.length];
		for (int i = 0; i < a.length; i++) {
			aa[i] = true;
			ac[i] = a[i].getStat(Unit.HP);
		}
		for (int i = 0; i < e.length; i++) {
			ea[i] = true;
			ec[i] = e[i].getStat(Unit.HP);
		}
	}

	public void doBattle(int ally, int opponent, boolean allyAttack) {
		boolean ad = false, ed = false;

		int ahit = 0, ehit = 0, aev = 0, eev = 0;

		int edmg = 0, admg = 0, odmg = 0;

		int as = a[ally].getStat(Unit.SPEED), es = e[opponent].getStat(Unit.SPEED);
		if (as + 5 < es)
			ed = true;
		if (es + 5 < as)
			ad = true;

		if (ad) {
			odmg = (a[ally].getStat(Unit.ATTACK) + a[ally].getWeapon().atk) - e[opponent].getStat(Unit.DEFENSE);
		}
		if (ed) {
			odmg = (e[opponent].getStat(Unit.ATTACK) + e[opponent].getWeapon().atk) - a[ally].getStat(Unit.DEFENSE);
		}
		edmg = (e[opponent].getStat(Unit.ATTACK) + e[opponent].getWeapon().atk) - a[ally].getStat(Unit.DEFENSE);
		admg = (a[ally].getStat(Unit.ATTACK) + a[ally].getWeapon().atk) - e[opponent].getStat(Unit.DEFENSE);

		if (odmg < 0)
			odmg = 0;
		if (edmg < 0)
			edmg = 0;
		if (admg < 0)
			admg = 0;

		if (!a[ally].getWeapon().isMagic)
			ahit = a[ally].getStat(Unit.SKILL) + (int) (100 * a[ally].getWeapon().hit);
		else
			ahit = (int) (100 * a[ally].getWeapon().hit);

		if (!e[ally].getWeapon().isMagic)
			ehit = e[ally].getStat(Unit.SKILL) + (int) (100 * e[ally].getWeapon().hit);
		else
			ehit = (int) (100 * e[ally].getWeapon().hit);

		if (!ad && !ed)
			System.out.println(a[ally].getXlass().getName() + " " + a[ally].getNom() + " will do " + admg + " damgage while recieving " + edmg + " damage from the opposing " + e[opponent].getXlass().getName() + " " + e[opponent].getNom());
		if (ad && !ed)
			System.out.println(a[ally].getXlass().getName() + " " + a[ally].getNom() + " will do " + (admg + odmg) + " damgage with a double hit (" + admg + ") while recieving " + edmg + " damage from the opposing " + e[opponent].getXlass().getName() + " " + e[opponent].getNom());
		if (!ad && ed)
			System.out.println(a[ally].getXlass().getName() + " " + a[ally].getNom() + " will do " + admg + " damgage while recieving " + (edmg + odmg) + " damage with a double hit (" + edmg + ") from the opposing " + e[opponent].getXlass().getName() + " " + e[opponent].getNom());

		if (e[opponent].getWeapon().isMagic)
			aev = a[ally].getStat(Unit.SPEED) + a[ally].getStat(Unit.LUCK);
		else
			aev = a[ally].getStat(Unit.SPEED); //TODO + Terrain bonus
		if (a[ally].getWeapon().isMagic)
			eev = e[opponent].getStat(Unit.SPEED) + e[opponent].getStat(Unit.LUCK);
		else
			eev = e[opponent].getStat(Unit.SPEED); //TODO + Terrain bounus

		System.out.println(a[ally].getNom() + " has a " + (ahit - eev) + "% chance of landing an attack with " + a[ally].getWeapon().name + ".");
		System.out.println(e[opponent].getNom() + " has a " + (ehit - aev) + "% chance of landing an attack with " + e[opponent].getWeapon().name + ".");

		Random r = new Random();

		//boolean ah = true, eh = true, oh = true;

		if (allyAttack) {
			if (r.nextInt(100) < (ahit - eev))
				System.out.println(a[ally].getNom() + " hits!");
			else
				System.out.println(a[ally].getNom() + " misssed!");
			if (r.nextInt(100) < (ehit - aev))
				System.out.println(e[opponent].getNom() + " hits!");
			else
				System.out.println(e[opponent].getNom() + " misssed!");
			if (ad)
				if (r.nextInt(100) < (ahit - eev))
					System.out.println(a[ally].getNom() + " hits again!");
				else
					System.out.println(a[ally].getNom() + " misssed!");
			if (ed)
				if (r.nextInt(100) < (ehit - aev))
					System.out.println(e[opponent].getNom() + " hits again!");
				else
					System.out.println(e[opponent].getNom() + " misssed!");

		} else {
			if (r.nextInt(100) < (ehit - aev))
				System.out.println(e[opponent].getNom() + " hits!");
			else
				System.out.println(e[opponent].getNom() + " misssed!");
			if (r.nextInt(100) < (ahit - eev))
				System.out.println(a[ally].getNom() + " hits!");
			else
				System.out.println(a[ally].getNom() + " misssed!");
			if (ad)
				if (r.nextInt(100) < (ahit - eev))
					System.out.println(a[ally].getNom() + " hits again!");
				else
					System.out.println(a[ally].getNom() + " misssed!");
			if (ed)
				if (r.nextInt(100) < (ehit - aev))
					System.out.println(e[opponent].getNom() + " hits again!");
				else
					System.out.println(e[opponent].getNom() + " misssed!");

		}
	}
}
