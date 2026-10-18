package main.model;

public class Ability {
	
	private String name;
	private int manaCost,damage;
	
	public Ability(String name, int manaCost, int damage) {
		super();
		this.name = name;
		this.manaCost = manaCost;
		this.damage = damage;
	
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getManaCost() {
		return manaCost;
	}
	public void setManaCost(int manaCost) {
		this.manaCost = manaCost;
	}
	public int getDamage() {
		return damage;
	}
	public void setDamage(int damage) {
		this.damage = damage;
	}

	@Override
	public String toString() {
		return "Ability [name=" + name + ", manaCost=" + manaCost + ", damage=" + damage + "]";
	}
	
	
}
