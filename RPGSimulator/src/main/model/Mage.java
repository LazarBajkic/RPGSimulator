package main.model;

import java.util.HashMap;
import java.util.List;

public class Mage extends Character{
	
	public Mage(String name, int level, int experience, int health,int mana, int maxHealth, int gold,
			HashMap<Stat, Integer> stats, Inventory inv,List<Ability> abilities,List<Item> equipment) {
		super(name, level,mana, experience, health, maxHealth, gold, stats, inv, abilities,equipment);
		
	}

	@Override
	public int attack() {
		int damage = this.getEquipment().stream().mapToInt(x->x.getStats().getOrDefault(Stat.ATTACK,0)).sum();
		damage += this.getStats().get(Stat.INTELLIGENCE);
		return damage;
	}

	@Override
	public void block(int damageAmount) {
		if(damageAmount < 0) throw new IllegalArgumentException();
		
		int blocked = this.getEquipment().stream().mapToInt(x->x.getStats().getOrDefault(Stat.DEFENCE,0)).sum();
		blocked += this.getStats().get(Stat.CONSTITUTION);
		if(damageAmount - blocked <= 0) {
			this.takeDamage(0);
		}else {
			this.takeDamage(damageAmount-blocked);
		}
	}

	@Override
	public void levelUp() {
		 this.getStats().merge(Stat.STRENGTH, 2, Integer::sum);
		 int newConst = this.getStats().merge(Stat.CONSTITUTION, 2, Integer::sum);
		 this.setMaxHealth(newConst+2);
	}

}
