package main.model;

import java.util.HashMap;

public class Shield extends Item{

	public Shield(String name, ItemType itemT, ItemRarity itemR, boolean tradeable, int weight,HashMap<Stat, Integer> stats) {
		super(name, itemT, itemR, tradeable, weight, stats);
		
	}

	@Override
	public int calculateValue() {
		int basePrice=45;
		int calculatedPrice=this.getStats().entrySet().stream().mapToInt(x->x.getValue()*x.getKey().getGoldVal()).sum();
		return (int) ((basePrice+calculatedPrice)*this.getItemR().getRarityMultiplier());
	}

}
