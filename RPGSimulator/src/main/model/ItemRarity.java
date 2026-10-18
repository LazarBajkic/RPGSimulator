package main.model;

public enum ItemRarity {
	COMMON(1.0),UNCOMMON(1.5),RARE(2.0),EPIC(2.5),LEGENDARY(3.0);
	private double rarityMultiplier;
	
	ItemRarity(double rarityMultiplier){
		this.rarityMultiplier=rarityMultiplier;
	}

	public double getRarityMultiplier() {
		return rarityMultiplier;
	}

	public void setRarityMultiplier(double rarityMultiplier) {
		this.rarityMultiplier = rarityMultiplier;
	}
}
