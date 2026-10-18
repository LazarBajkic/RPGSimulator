package main.model;

import java.util.List;
import java.util.stream.Collectors;

public class Inventory {
	private List<Item> items;
	
	public boolean addItem(Item item) {
		if(item==null) throw new NullPointerException();
			items.add(item);
			return true;
	}
	
	public Inventory(List<Item> items) {
		this.items=items;
	}
	
	public boolean removeItem(Item item) {
		if(items.contains(item)) {
			items.remove(item);
			return true;
		}
		return false;
	}
	
	public List<Item> findItems(String itemName) {
		List<Item> matchItems = this.items.stream().filter(x->x.getName().contains(itemName)).collect(Collectors.toList());
		if(matchItems.isEmpty()) {
			System.out.println("There are no items matching this description");
		}
		return matchItems;
	}
		

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}
	
}

