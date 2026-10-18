package main.model;

import java.util.HashMap;

public abstract class Item {
	private String name;
	private ItemType itemT;
	private ItemRarity itemR;
	private boolean tradeable;
	private int weight;
	private HashMap<Stat,Integer> stats;
	private int value;
	
	public Item(String name, ItemType itemT, ItemRarity itemR, boolean tradeable, int weight,
			HashMap<Stat, Integer> stats) {
		super();
		this.name = name;
		this.itemT = itemT;
		this.itemR = itemR;
		this.tradeable = tradeable;
		this.weight = weight;
		this.stats = stats;
		this.value=this.calculateValue();
	}

	public abstract int calculateValue();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ItemType getItemT() {
		return itemT;
	}

	public void setItemT(ItemType itemT) {
		this.itemT = itemT;
	}

	public ItemRarity getItemR() {
		return itemR;
	}

	public void setItemR(ItemRarity itemR) {
		this.itemR = itemR;
	}

	public boolean isTradeable() {
		return tradeable;
	}

	public void setTradeable(boolean tradeable) {
		this.tradeable = tradeable;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public HashMap<Stat, Integer> getStats() {
		return stats;
	}

	public void setStats(HashMap<Stat, Integer> stats) {
		this.stats = stats;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
	
}
