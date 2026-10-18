package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import main.model.*;
import main.model.Character;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("=========================================");
        System.out.println("       STARTING RPG SIMULATOR TEST       ");
        System.out.println("=========================================\n");

  
        System.out.println(">>> STEP 1: Initializing Items and Inventory...");

        HashMap<Stat, Integer> swordStats = new HashMap<>();
        swordStats.put(Stat.ATTACK, 15);

        HashMap<Stat, Integer> armorStats = new HashMap<>();
        armorStats.put(Stat.DEFENCE, 10);

        Item ironSword = new Item("Iron Sword", ItemType.WEAPON, ItemRarity.COMMON, true, 5, swordStats) {
            @Override
            public int calculateValue() {
                return 50; 
            }
        };

        Item ironArmor = new Item("Iron Armor", ItemType.ARMOR, ItemRarity.COMMON, true, 15, armorStats) {
            @Override
            public int calculateValue() {
                return 100;
            }
        };

        List<Item> startingItems = new ArrayList<>();
        startingItems.add(ironSword);
        startingItems.add(ironArmor);

        Inventory inventory = new Inventory(startingItems);
        System.out.println("Inventory initialized with " + inventory.getItems().size() + " items.\n");

     
        System.out.println(">>> STEP 2: Creating Character (Mage)...");

        HashMap<Stat, Integer> mageStats = new HashMap<>();
        mageStats.put(Stat.STRENGTH, 8);
        mageStats.put(Stat.CONSTITUTION, 10);
        mageStats.put(Stat.DEXTERITY, 12);
        mageStats.put(Stat.INTELLIGENCE, 14);


        List<Ability> abilities = new ArrayList<>();
        abilities.add(new Ability("Fireball", 25, 40));

        List<Item> equipment = new ArrayList<>();
        
        
        
        Character gandalf = new Mage(
                "Gandalf", 1, 100, 80, 80, 80, 300,
                mageStats, inventory, abilities, equipment
        );

 
        
        System.out.println("Character created: " + gandalf.getName() + " (Level " + gandalf.getLevel() + ")");
        System.out.println("Health: " + gandalf.getHealth() + "/" + gandalf.getMaxHealth());
        System.out.println("Mana: 100\n");

        System.out.println(">>> STEP 3: Testing Character Status and Combat functions...");

        System.out.println("Is " + gandalf.getName() + " alive? -> " + gandalf.isAlive());

        System.out.println("Base attack power: " + gandalf.attack());

        try {
            Ability fireball = gandalf.getAbilities().get(0);
            int damage = gandalf.castAbility(fireball);
            System.out.println("Cast '" + fireball.getName() + "' successfully! Dealing " + damage + " damage.");
        } catch (Exception e) {
            System.out.println("Failed to cast ability: " + e.getMessage());
        }
        System.out.println();

     
        System.out.println(">>> STEP 4: Testing Equipment System...");

        System.out.println("Items in inventory before equipping: " + gandalf.getInv().getItems().size());
        
        try {
            gandalf.changeEquipment(ironArmor);
            System.out.println("Successfully equipped: " + ironArmor.getName());
            System.out.println("Items in inventory after equipping: " + gandalf.getInv().getItems().size());
            System.out.println("Items currently worn as equipment: " + gandalf.getEquipment().size());
        } catch (Exception e) {
            System.out.println("Error changing equipment: " + e.getMessage());
        }

    
        System.out.println(">>> STEP 5: Testing Combat System...");

        HashMap<Stat, Integer> enemyStats = new HashMap<>();
        enemyStats.put(Stat.STRENGTH, 10);
        enemyStats.put(Stat.CONSTITUTION, 8);
        enemyStats.put(Stat.DEXTERITY, 10); // Manja brzina od Gandolfa (Gandolf je 12)

        Character goblin = new Warrior(
                "Goblin Scout", 1, 0, 0, 40, 40, 20,
                enemyStats, new Inventory(new ArrayList<>()), new ArrayList<>(), new ArrayList<>()
        );

        System.out.println(gandalf.getName() + " HP: " + gandalf.getHealth() + " vs " + goblin.getName() + " HP: " + goblin.getHealth());

        CombatSystem combatSystem = new CombatSystem();
        
     
        try {
            System.out.println("--- COMBAT STARTED ---");
            combatSystem.combat(gandalf, goblin);
            System.out.println("--- COMBAT ENDED ---");
            System.out.println("Is goblin alive? -> " + goblin.isAlive());
        } catch (Exception e) {
            System.out.println("Combat error: " + e.getMessage());
        }

   
        System.out.println("\n>>> STEP 6: Testing Trader System...");

        HashMap<Stat, Integer> potionStats = new HashMap<>();
        Item healthPotion = new Item("Health Potion", ItemType.MOB_DROP, ItemRarity.COMMON, true, 1, potionStats) {
            @Override
            public int calculateValue() {
                return 25; // Cena napitka je 25 zlata
            }
        };

      
        List<Item> traderItems = new ArrayList<>();
        traderItems.add(healthPotion);
        
        HashMap<Stat, Integer> traderStats = new HashMap<>();
        traderStats.put(Stat.STRENGTH, 5);
        traderStats.put(Stat.CONSTITUTION, 5);
        traderStats.put(Stat.DEXTERITY, 5);

        Character merchant = new Warrior(
                "Bob the Trader", 5, 0, 0, 100, 100, 1000,
                traderStats, new Inventory(traderItems), new ArrayList<>(), new ArrayList<>()
        );

        System.out.println("Gandalf gold before trade: " + gandalf.getGold());
        System.out.println("Merchant gold before trade: " + merchant.getGold());
        System.out.println("Merchant inventory items count: " + merchant.getInv().getItems().size());
        System.out.println("Gandalf inventory items count: " + gandalf.getInv().getItems().size());

       
        try {
            Trader.makeTrade(merchant, gandalf, healthPotion);
            System.out.println("\n--- TRADE EXECUTED ---");
            
            System.out.println("Gandalf gold after trade: " + gandalf.getGold());
            System.out.println("Merchant gold after trade: " + merchant.getGold());
            System.out.println("Merchant inventory items count: " + merchant.getInv().getItems().size());
            System.out.println("Gandalf inventory items count: " + gandalf.getInv().getItems().size());
        } catch (Exception e) {
            System.out.println("Trade error: " + e.getMessage());
        }
        
        System.out.println("\n=========================================");
        System.out.println("         TEST SUCCESSFULLY FINISHED      ");
        System.out.println("=========================================");
    }
}

