package main.model;

import java.util.HashMap;

public class Weapon extends Item{
	private WeaponType weaponT;
	
	public Weapon( String name,ItemType itemT,ItemRarity itemR,boolean tradeable,int weight,HashMap<Stat,Integer> stats,WeaponType weaponT) {
		super(name,itemT,itemR,tradeable,weight,stats);
		this.weaponT=weaponT;
	}

	@Override
	public int calculateValue() {
		int basePrice=50;
		int calculatedPrice=this.getStats().entrySet().stream().mapToInt(x->x.getValue()*x.getKey().getGoldVal()).sum();
		return (int) ((basePrice+calculatedPrice)*this.getItemR().getRarityMultiplier());
	}
	
}

enum WeaponType{
	SWORD,DAGGERS,GREAT_SWORD,CROSSBOW,BOW,TOME,HAMMER,MACE,STAFF,GAUNTLETS
}