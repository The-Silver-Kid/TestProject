package DevTSK.Units;

import java.util.Random;

public class Battle {
	private Unit[] a, e;
	private int[] ac, ec;
	private boolean adead, edead;

	private static final int[][] EFFECT = new int[][] {
			new int[] { 0, 1, -1, 0, 0, 0, 0, -1, 0 }, //SWORD
			new int[] { -1, 0, 1, 0, 0, 0, 0, -1, 0 }, //AXE
			new int[] { 1, -1, 0, 0, 0, 0, 0, -1, 0 }, //SPEAR
			new int[] { 0, 0, 0, 0, 0, 0, 0, -1, 0 }, //BOW
			new int[] { 0, 0, 0, 0, 0, -1, 1, 1, 0 }, //ANIMA
			new int[] { 0, 0, 0, 0, 1, 0, -1, 1, 0 }, //DARK
			new int[] { 0, 0, 0, 0, -1, 1, 0, 1, 0 }, //LIGHT
			new int[] { 1, 1, 1, 1, -1, -1, -1, 0 }, //ENERGY
			new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 } //DIVINE
	};

	public Battle(Unit[] allies, Unit[] opponents) {
		a = allies;
		e = opponents;
		ac = new int[a.length];
		ec = new int[e.length];
		for (int i = 0; i < a.length; i++) {
			ac[i] = a[i].getStat(Unit.HP);
		}
		for (int i = 0; i < e.length; i++) {
			ec[i] = e[i].getStat(Unit.HP);
		}
	}

	public void doBattle(int ally, int opponent, boolean allyAttack) {
		adead = false;
		edead = false;
		boolean ad = false, ed = false, aw = a[ally].canUseWeapon(), ew = e[opponent].canUseWeapon(), eCrit = false, aCrit = false, oCrit = false;
		Random r = new Random();

		int ahit = 0, ehit = 0, aev = 0, eev = 0, aWep = a[ally].getWeapon().type, eWep = e[opponent].getWeapon().type;

		int edmg = 0, admg = 0, odmg = 0;

		int as = a[ally].getStat(Unit.SPEED), es = e[opponent].getStat(Unit.SPEED);
		if (as + 5 < es)
			ed = true;
		if (es + 5 < as)
			ad = true;
		//BONUS DAMAGE
		//ally
		if (ad)
			//can use weapon
			if (aw)
				odmg = allyDamage(ally, opponent, a[ally].getWeapon().isMagic);
			else
				odmg = 0;//(a[ally].getStat(Unit.ATTACK)) - e[opponent].getStat(Unit.DEFENSE);
		//opponent
		if (ed)
			if (ew)
				odmg = enemyDamage(ally, opponent, e[opponent].getWeapon().isMagic);
			else
				odmg = 0;//(e[opponent].getStat(Unit.ATTACK)) - a[ally].getStat(Unit.DEFENSE);

		//Normal damage
		//enemy attack
		if (ew)
			edmg = enemyDamage(ally, opponent, e[opponent].getWeapon().isMagic);
		else
			edmg = 0;//(e[opponent].getStat(Unit.ATTACK)) - a[ally].getStat(Unit.DEFENSE);
		//ally attack
		if (aw)
			admg = allyDamage(ally, opponent, a[ally].getWeapon().isMagic);
		else
			admg = 0;//(a[ally].getStat(Unit.ATTACK)) - e[opponent].getStat(Unit.DEFENSE);

		//weapon triangle

		admg += (5 * EFFECT[aWep][eWep]);
		edmg += (5 * EFFECT[eWep][aWep]);
		if (ad)
			odmg += (5 * EFFECT[aWep][eWep]);
		if (ed)
			odmg += (5 * EFFECT[eWep][aWep]);

		//prevent healing
		if (odmg < 0)
			odmg = 0;
		if (edmg < 0)
			edmg = 0;
		if (admg < 0)
			admg = 0;

		//debug
		if (!ad && !ed)
			System.out.println(a[ally].getXlass().getName() + " " + a[ally].getNom() + " will do " + admg + " Damage while receiving " + edmg + " damage from the opposing " + e[opponent].getXlass().getName() + " " + e[opponent].getNom());
		if (ad && !ed)
			System.out.println(a[ally].getXlass().getName() + " " + a[ally].getNom() + " will do " + (admg + odmg) + " Damage with a double hit (" + admg + ") while receiving " + edmg + " damage from the opposing " + e[opponent].getXlass().getName() + " " + e[opponent].getNom());
		if (!ad && ed)
			System.out.println(a[ally].getXlass().getName() + " " + a[ally].getNom() + " will do " + admg + " Damage while receiving " + (edmg + odmg) + " damage with a double hit (" + edmg + ") from the opposing " + e[opponent].getXlass().getName() + " " + e[opponent].getNom());

		//Handle Critical
		int aCritR, eCritR;
		if (aw)
			aCritR = ((a[ally].getStat(Unit.SKILL) / 2) + a[ally].getWeapon().crit) - e[opponent].getStat(Unit.LUCK);
		else
			aCritR = (a[ally].getStat(Unit.SKILL) / 2) - e[opponent].getStat(Unit.LUCK);
		if (ew)
			eCritR = ((e[opponent].getStat(Unit.SKILL) / 2) + e[opponent].getWeapon().crit) - a[ally].getStat(Unit.LUCK);
		else
			eCritR = (e[opponent].getStat(Unit.SKILL) / 2) - a[ally].getStat(Unit.LUCK);

		if (aCritR < 0)
			aCritR = 0;
		if (eCritR < 0)
			eCritR = 0;

		if (r.nextInt(100) < aCritR)
			aCrit = true;
		if (r.nextInt(100) < eCritR)
			eCrit = true;

		if (ad)
			if (r.nextInt(100) < aCritR)
				oCrit = true;
		if (ed)
			if (r.nextInt(100) < eCritR)
				oCrit = true;

		if (oCrit)
			odmg = odmg * 3;
		if (aCrit)
			admg = admg * 3;
		if (eCrit)
			edmg = edmg * 3;

		//debug
		System.out.println(a[ally].getXlass().getName() + " " + a[ally].getNom() + " has a " + aCritR + "% chance of critical.");
		System.out.println(e[opponent].getXlass().getName() + " " + e[opponent].getNom() + " has a " + eCritR + "% chance of critical.");

		//hit chance
		System.out.println(odmg + ":" + edmg + ":" + admg);

		if (!a[ally].getWeapon().isMagic)
			ahit = a[ally].getStat(Unit.SKILL) + (int) (100 * a[ally].getWeapon().hit);
		else
			ahit = (int) (100 * a[ally].getWeapon().hit);

		if (!e[ally].getWeapon().isMagic)
			ehit = e[ally].getStat(Unit.SKILL) + (int) (100 * e[ally].getWeapon().hit);
		else
			ehit = (int) (100 * e[ally].getWeapon().hit);

		//evasion
		if (e[opponent].getWeapon().isMagic)
			aev = a[ally].getStat(Unit.SPEED) + a[ally].getStat(Unit.LUCK);
		else
			aev = a[ally].getStat(Unit.SPEED); //TODO + Terrain bonus
		if (a[ally].getWeapon().isMagic)
			eev = e[opponent].getStat(Unit.SPEED) + e[opponent].getStat(Unit.LUCK);
		else
			eev = e[opponent].getStat(Unit.SPEED); //TODO + Terrain bounus

		//debug
		System.out.println(a[ally].getNom() + " has a " + (ahit - eev) + "% chance of landing an attack with " + a[ally].getWeapon().name + ".");
		System.out.println(e[opponent].getNom() + " has a " + (ehit - aev) + "% chance of landing an attack with " + e[opponent].getWeapon().name + ".");

		System.out.println(a[ally].getXlass().getName() + " " + a[ally].getNom() + " has " + ac[ally] + " HP.\n" + e[opponent].getXlass().getName() + " " + e[opponent].getNom() + " has " + ec[opponent] + " HP.");

		//ATTACK!
		if (allyAttack) {
			if (r.nextInt(100) < (ahit - eev)) {
				ec[opponent] -= admg;
				if (!aCrit)
					System.out.println(a[ally].getNom() + " hits!");
				else
					System.out.println(a[ally].getNom() + " gets a CRITICAL for " + admg);
			} else
				System.out.println(a[ally].getNom() + " Missed!");
			deathCheck(ally, opponent);
			if (!edead)
				if (r.nextInt(100) < (ehit - aev)) {
					ac[ally] -= edmg;
					if (!eCrit)
						System.out.println(e[opponent].getNom() + " hits!");
					else
						System.out.println(e[opponent].getNom() + " gets a CRITICAL for " + edmg);
				} else
					System.out.println(e[opponent].getNom() + " Missed!");

		} else {
			if (r.nextInt(100) < (ehit - aev)) {
				ac[ally] -= edmg;
				if (!eCrit)
					System.out.println(e[opponent].getNom() + " hits!");
				else
					System.out.println(e[opponent].getNom() + " gets a CRITICAL for " + edmg);
			} else
				System.out.println(e[opponent].getNom() + " Missed!");
			deathCheck(ally, opponent);
			if (!adead)
				if (r.nextInt(100) < (ahit - eev)) {
					ec[opponent] -= admg;
					if (!aCrit)
						System.out.println(a[ally].getNom() + " hits!");
					else
						System.out.println(a[ally].getNom() + " gets a CRITICAL for " + admg);
				} else
					System.out.println(a[ally].getNom() + " Missed!");

		}
		deathCheck(ally, opponent);
		if (!edead && !adead) {
			if (ad)
				if (r.nextInt(100) < (ahit - eev)) {
					ec[opponent] -= odmg;
					if (!oCrit)
						System.out.println(a[ally].getNom() + " hits!");
					else
						System.out.println(a[ally].getNom() + " gets a CRITICAL for " + odmg);
				} else
					System.out.println(a[ally].getNom() + " Missed!");
			if (ed)
				if (r.nextInt(100) < (ehit - aev)) {
					ac[ally] -= odmg;
					if (!oCrit)
						System.out.println(e[opponent].getNom() + " hits!");
					else
						System.out.println(e[opponent].getNom() + " gets a CRITICAL for " + odmg);
				} else
					System.out.println(e[opponent].getNom() + " Missed!");
			deathCheck(ally, opponent);
		}
		//doLooseCheck();
		//debug
		if (!edead && !adead)
			System.out.println(a[ally].getXlass().getName() + " " + a[ally].getNom() + " has " + ac[ally] + " HP left.\n" + e[opponent].getXlass().getName() + " " + e[opponent].getNom() + " has " + ec[opponent] + " HP left.");
		if (edead && !adead)
			System.out.println(a[ally].getXlass().getName() + " " + a[ally].getNom() + " has " + ac[ally] + " HP left.\n" + e[opponent].getXlass().getName() + " " + e[opponent].getNom() + " is dead.");
		if (!edead && adead)
			System.out.println(a[ally].getXlass().getName() + " " + a[ally].getNom() + " is dead.\n" + e[opponent].getXlass().getName() + " " + e[opponent].getNom() + " has " + ec[opponent] + " HP left.");

	}

	private int allyDamage(int ally, int opponent, boolean isMagic) {
		if (!isMagic)
			return (a[ally].getStat(Unit.ATTACK) + (a[ally].getWeapon().atk * isEffective(ally, opponent, false))) - e[opponent].getStat(Unit.DEFENSE);
		else
			return (a[ally].getStat(Unit.ATTACK) + (a[ally].getWeapon().atk * isEffective(ally, opponent, false))) - e[opponent].getStat(Unit.RESISTANCE);
	}

	private int enemyDamage(int ally, int opponent, boolean isMagic) {
		if (!isMagic)
			return (e[opponent].getStat(Unit.ATTACK) + (e[opponent].getWeapon().atk * isEffective(ally, opponent, true))) - a[ally].getStat(Unit.DEFENSE);
		else
			return (e[opponent].getStat(Unit.ATTACK) + (e[opponent].getWeapon().atk * isEffective(ally, opponent, true))) - a[ally].getStat(Unit.RESISTANCE);
	}

	private int isEffective(int ally, int opponent, boolean b) {
		int level = 1;
		if (!b) {
			for (int i = 0; i < a[ally].getWeapon().efns.length; i++)
				for (int o = 0; o < e[opponent].getType().length; o++)
					if (a[ally].getWeapon().efns[i] == e[opponent].getType()[o])
						level++;
		} else {
			for (int i = 0; i < e[opponent].getWeapon().efns.length; i++)
				for (int o = 0; o < a[ally].getType().length; o++)
					if (e[opponent].getWeapon().efns[i] == a[ally].getType()[o])
						level++;
		}
		return level;
	}

	private void deathCheck(int ally, int opponent) {
		if (ac[ally] < 1) {
			ac[ally] = 0;
			a[ally].deathToggle();
			adead = true;
		}
		if (ec[opponent] < 1) {
			ec[opponent] = 0;
			e[opponent].deathToggle();
			edead = true;
		}
	}
}
