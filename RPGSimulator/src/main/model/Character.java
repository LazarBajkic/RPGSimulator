package main.model;

import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public abstract class Character {
	
	private String name;
	private int level,experience,health,maxHealth,gold,currLevelCap,mana;
	private HashMap<Stat, Integer> stats;
	private Inventory inv;
	private List<Ability> abilities;
	private List<Item> equipment;
	
	
	public Character(String name, int level, int mana, int experience, int health, int maxHealth, int gold, 
            HashMap<Stat, Integer> stats, Inventory inv, List<Ability> abilities, List<Item> equipment) {
				super();
				this.name = name;
				this.level = level;
				this.mana = mana;
				this.experience = experience;
				this.health = health;
				this.maxHealth = maxHealth;
				this.gold = gold;
				this.stats = stats;
				this.inv = inv;
				this.currLevelCap = 100;
				this.abilities = abilities;
				this.equipment = equipment;
	}

	public void takeDamage(int amount) {
			
			if(amount < 0) throw new IllegalArgumentException();
			if(!this.isAlive()) throw new IllegalStateException();

				health -= amount;
				if(health < 0) {
					health = 0;
			}
		
	}
	
	public void healDamage(int amount) {
		
		if(amount < 0) throw new IllegalArgumentException();
		if(!this.isAlive()) throw new IllegalStateException();
		
			health += amount;
			if(health > maxHealth) {
				health = maxHealth;
			}
		}
	
	public boolean isAlive() {
		return this.health > 0;
	}
	
	public void addExp(int amount) {
		
		if(!this.isAlive()) throw new IllegalStateException();
		if(amount < 0) throw new IllegalArgumentException();
		
			this.experience += amount;
			if(this.experience>=this.currLevelCap) {
				this.levelUp();
				this.experience-=this.currLevelCap;
				this.setCurrLevelCap((int) (this.currLevelCap * 1.15));
			}
		}
		
	public int castAbility(Ability ability) {
		if(!this.abilities.contains(ability)) throw new NoSuchElementException();
		
		if((this.mana-ability.getManaCost()) >= 0) {
			this.mana-=ability.getManaCost();
		}else {
			System.out.println("Not enough mana to cast that ability!");
			return 0;
		}
		
		return ability.getDamage();
	}
	
	public void changeEquipment(Item item) {
		if(this.getInv().getItems().contains(item)) {
			Optional<Item> equippedOpt = getEquipment().stream()
	                .filter(x -> x.getItemT() == item.getItemT())
	                .findFirst();
	        
	        if (equippedOpt.isPresent()) {
	            Item equipped = equippedOpt.get();
	            this.getEquipment().remove(equipped);
	            this.getInv().addItem(equipped); 
	        }
	        this.getEquipment().add(item);      
	        this.getInv().removeItem(item);
		
		}else {
			throw new IllegalArgumentException("No such item in your inventory.");
		}
	}

	public abstract int attack();
	public abstract void block(int damageAmount);
	public abstract void levelUp();
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getMaxHealth() {
		return maxHealth;
	}

	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}

	public int getCurrLevelCap() {
		return currLevelCap;
	}

	public void setCurrLevelCap(int currLevelCap) {
		this.currLevelCap = currLevelCap;
	}

	public HashMap<Stat, Integer> getStats() {
		return stats;
	}

	public void setStats(HashMap<Stat, Integer> stats) {
		this.stats = stats;
	}

	public int getGold() {
		return gold;
	}

	public void setGold(int gold) {
		this.gold = gold;
	}

	public Inventory getInv() {
		return inv;
	}

	public void setInv(Inventory inv) {
		this.inv = inv;
	}

	public List<Ability> getAbilities() {
		return abilities;
	}

	public void setAbilities(List<Ability> abilities) {
		this.abilities = abilities;
	}
	public List<Item> getEquipment() {
		return equipment;
	}

	public void setEquipment(List<Item> equipment) {
		this.equipment = equipment;
	}

	public int getMana() {
		return mana;
	}

	public void setMana(int mana) {
		this.mana = mana;
	}
	
}
