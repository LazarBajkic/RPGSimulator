package main.model;

public enum Stat {
	ATTACK(10),
	DEFENCE(15),
	ATTACK_SPEED(5),
	MAGIC_POWER(20),
	MANA_COST(10),
	STRENGTH(0),
	DEXTERITY(0),
	CONSTITUTION(0),
	WISDOM(0),
	CHARISMA(0),
	INTELLIGENCE(0);

	private int goldVal;
	
	Stat(int goldVal) {
		this.goldVal=goldVal;
	}

	public int getGoldVal() {
		return goldVal;
	}

	public void setGoldVal(int goldVal) {
		this.goldVal = goldVal;
	}
	
	
}
