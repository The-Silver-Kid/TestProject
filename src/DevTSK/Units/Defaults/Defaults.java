package DevTSK.Units.Defaults;

import DevTSK.Units.Weapon;
import DevTSK.Units.Xlass;

public class Defaults {

	public static Xlass[] getDefaultClasses() {
		//**************************************************
		//*Name   / BaseStats / Useable Weapons/ stat rates*
		//*String / int[]     / int[]          / double[]  *
		//**************************************************
		//*  HP / ATK / DEF / SKL / SPD / LUK / RES / MOV  *
		//**************************************************
		Xlass[] x = new Xlass[] {
				new Xlass("Fighter", new int[] { 28, 10, 6, 7, 6, 7, 4, 5 }, new int[] { Weapon.SWORD, Weapon.DIVINE }, new double[] { 1.5, .5, .5, .5, .5, 0, 0, 0 }).addClassUp("Hero", 20),
				new Xlass("Hero", new int[] { 36, 16, 10, 14, 14, 10, 4, 5 }, new int[] { Weapon.SWORD, Weapon.BOW, Weapon.DIVINE }, new double[] { 1.5, .5, .5, .5, .5, 0, 0, 0 }),
				new Xlass("Soldier", new int[] { 26, 10, 5, 1, 3, 0, 0, 4 }, new int[] { Weapon.SPEAR }, new double[] { .05, .05, .05, 0, -.1, .05, 0, 0 }).addClassUp("Knight", 10),
				new Xlass("Knight", new int[] { 34, 16, 12, 2, 2, 0, 1, 4 }, new int[] { Weapon.SPEAR }, new double[] { 1.2, .5, .5, .5, .4, 0, 0, 0 }).addClassUp("Baron", 10),
		};

		return x;
	}

	public static Weapon[] getDefaultWeapons() {
		//******************************************************************************************
		//* Name   / type / Range / Attack / crit chance / hit modifier / eff  /  magic  /  reaver *
		//* String / int  /  int  /   int  /     int     /    double    / null / boolean / boolean *
		//******************************************************************************************
		return new Weapon[] {
				new Weapon("Falchion", Weapon.DIVINE, 1, 10, 0, .8, null, false, false),
				new Weapon("Killing Edge", Weapon.SWORD, 1, 6, 25, .85, null, false, false),
		};
	}
}
