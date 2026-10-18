package main.model;

import java.util.HashMap;

public class Armor extends Item{

	private ArmorType armorT;
	
	public Armor(String name, ItemType itemT, ItemRarity itemR, boolean tradeable, int weight,HashMap<Stat, Integer> stats,ArmorType armorT) {
		super(name, itemT, itemR, tradeable, weight, stats);
		this.armorT=armorT;
	}

	@Override
	public int calculateValue() {
		int basePrice=60;
		int calculatedPrice=this.getStats().entrySet().stream().mapToInt(x->x.getValue()*x.getKey().getGoldVal()).sum();
		return (int) ((basePrice+calculatedPrice)*this.getItemR().getRarityMultiplier());
	}

}

enum ArmorType{
	BOOTS,LEGGINGS,CHESTPLATE,HELMET;
}
