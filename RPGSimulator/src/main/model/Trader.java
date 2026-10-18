package main.model;

public class Trader {
	
	public static void makeTrade(Character p1, Character p2, Item item) {
		if(p1.getInv().getItems().contains(item)) {
			if((p2.getGold()-item.getValue()) >= 0 && item.isTradeable()) {
			p1.getInv().removeItem(item);
			p2.getInv().addItem(item);
			p2.setGold(p2.getGold()-item.getValue());
			p1.setGold(p1.getGold()+item.getValue());
		}
	}
	
	}
}
