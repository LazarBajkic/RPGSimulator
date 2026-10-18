package main.model;

import java.util.Scanner;

public class CombatSystem {
	
	private Scanner s = new Scanner(System.in);
	
	public void combat(Character c1, Character c2) {
		int c1Speed = c1.getStats().get(Stat.DEXTERITY);
		int c2Speed = c2.getStats().get(Stat.DEXTERITY);
		
		while(c1.isAlive() && c2.isAlive()) {
			if(c1Speed >= c2Speed) {
				chooseAttackType(c1,c2);
					if(c2.isAlive()) {
						chooseAttackType(c2,c1);
					}
			}else{
				chooseAttackType(c2,c1);
					if(c1.isAlive()) {
						chooseAttackType(c1,c2);
					}
			}
			
		}
		
	}
	
	public void chooseAttackType(Character c,Character opp) {
		System.out.println("Please enter choose an attack type by typing the respected number\n 1.Basic attack\n 2.Ability");
		int attackChoice = s.nextInt();	
		chooseAttack(c,opp,attackChoice);
	}
	
	public void chooseAttack(Character c,Character opp,int choice) {
		int damageAmount = 0;
		switch(choice) {
		case 1:
			damageAmount = c.attack();
			break;
		case 2:
			c.getAbilities().stream().forEach(x->System.out.println(x.toString()));
			int abilityChoice = s.nextInt();
			if (abilityChoice < 1 || abilityChoice > c.getAbilities().size()) {
			    throw new IndexOutOfBoundsException("Invalid ability choice.");
			}
			Ability ab = c.getAbilities().get(abilityChoice-1);
			damageAmount = c.castAbility(ab);
			break;
		
		}
		opp.takeDamage(damageAmount);
	}
	
}
