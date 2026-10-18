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

        // =========================================
        // STEP 1: CREATE ITEMS AND INVENTORY
        // =========================================
        System.out.println(">>> STEP 1: Initializing Items and Inventory...");

        // Item stats configuration
        HashMap<Stat, Integer> swordStats = new HashMap<>();
        swordStats.put(Stat.ATTACK, 15);

        HashMap<Stat, Integer> armorStats = new HashMap<>();
        armorStats.put(Stat.DEFENCE, 10);

        // Since Item is abstract, we instantiate it using an anonymous class implementing calculateValue()
        Item ironSword = new Item("Iron Sword", ItemType.WEAPON, ItemRarity.COMMON, true, 5, swordStats) {
            @Override
            public int calculateValue() {
                return 50; // Base value multiplied or set directly
            }
        };

        Item ironArmor = new Item("Iron Armor", ItemType.ARMOR, ItemRarity.COMMON, true, 15, armorStats) {
            @Override
            public int calculateValue() {
                return 100;
            }
        };

        // Initialize inventory with starting items
        List<Item> startingItems = new ArrayList<>();
        startingItems.add(ironSword);
        startingItems.add(ironArmor);

        Inventory inventory = new Inventory(startingItems);
        System.out.println("Inventory initialized with " + inventory.getItems().size() + " items.\n");

        // =========================================
        // STEP 2: CREATE CHARACTER (MAGE)
        // =========================================
        System.out.println(">>> STEP 2: Creating Character (Mage)...");

        HashMap<Stat, Integer> mageStats = new HashMap<>();
        mageStats.put(Stat.STRENGTH, 8);
        mageStats.put(Stat.CONSTITUTION, 10);
        mageStats.put(Stat.DEXTERITY, 12);
        mageStats.put(Stat.INTELLIGENCE, 14);


        List<Ability> abilities = new ArrayList<>();
        abilities.add(new Ability("Fireball", 25, 40));

        // Empty equipment list initially
        List<Item> equipment = new ArrayList<>();
        
        
        
        // Create Mage instance
        Character gandalf = new Mage(
                "Gandalf", 1, 100, 80, 80, 80, 300,
                mageStats, inventory, abilities, equipment
        );

 
        
        System.out.println("Character created: " + gandalf.getName() + " (Level " + gandalf.getLevel() + ")");
        System.out.println("Health: " + gandalf.getHealth() + "/" + gandalf.getMaxHealth());
        System.out.println("Mana: 100\n");

        // =========================================
        // STEP 3: TESTING BASIC CHARACTER FUNCTIONS
        // =========================================
        System.out.println(">>> STEP 3: Testing Character Status and Combat functions...");

        // Test isAlive()
        System.out.println("Is " + gandalf.getName() + " alive? -> " + gandalf.isAlive());

        // Test attack calculation (without weapon equipped yet)
        System.out.println("Base attack power: " + gandalf.attack());

        // Test casting an ability
        try {
            Ability fireball = gandalf.getAbilities().get(0);
            int damage = gandalf.castAbility(fireball);
            System.out.println("Cast '" + fireball.getName() + "' successfully! Dealing " + damage + " damage.");
        } catch (Exception e) {
            System.out.println("Failed to cast ability: " + e.getMessage());
        }
        System.out.println();

        // =========================================
        // STEP 4: TESTING EQUIPMENT MANAGEMENT
        // =========================================
        System.out.println(">>> STEP 4: Testing Equipment System...");

        System.out.println("Items in inventory before equipping: " + gandalf.getInv().getItems().size());
        
        // Equip the Iron Armor from inventory
        try {
            gandalf.changeEquipment(ironArmor);
            System.out.println("Successfully equipped: " + ironArmor.getName());
            System.out.println("Items in inventory after equipping: " + gandalf.getInv().getItems().size());
            System.out.println("Items currently worn as equipment: " + gandalf.getEquipment().size());
        } catch (Exception e) {
            System.out.println("Error changing equipment: " + e.getMessage());
        }

     // =========================================
        // STEP 5: TESTING COMBAT SYSTEM
        // =========================================
        System.out.println(">>> STEP 5: Testing Combat System...");

        // Kreiramo protivnika (npr. drugog ratnika ili zlikovca) za test borbe
        HashMap<Stat, Integer> enemyStats = new HashMap<>();
        enemyStats.put(Stat.STRENGTH, 10);
        enemyStats.put(Stat.CONSTITUTION, 8);
        enemyStats.put(Stat.DEXTERITY, 10); // Manja brzina od Gandolfa (Gandolf je 12)

        Character goblin = new Warrior(
                "Goblin Scout", 1, 0, 0, 40, 40, 20,
                enemyStats, new Inventory(new ArrayList<>()), new ArrayList<>(), new ArrayList<>()
        );

        System.out.println(gandalf.getName() + " HP: " + gandalf.getHealth() + " vs " + goblin.getName() + " HP: " + goblin.getHealth());

        // Inicijalizujemo CombatSystem
        CombatSystem combatSystem = new CombatSystem();
        
        // PAŽNJA: combat() metoda koristi Scanner.nextInt() u konzoli! 
        // Kada pokreneš ovo, konzola će tražiti od tebe da uneseš izbor (1 za Basic attack, 2 za Ability).
        // Testiraj da ukucaš 1 pa pritisni Enter, pa opet 1, itd. dok goblin ne umre.
        try {
            System.out.println("--- COMBAT STARTED ---");
            combatSystem.combat(gandalf, goblin);
            System.out.println("--- COMBAT ENDED ---");
            System.out.println("Is goblin alive? -> " + goblin.isAlive());
        } catch (Exception e) {
            System.out.println("Combat error: " + e.getMessage());
        }

        // =========================================
        // STEP 6: TESTING TRADER SYSTEM
        // =========================================
        System.out.println("\n>>> STEP 6: Testing Trader System...");

        // Kreiramo item koji trgovac prodaje (ili ga stavljamo kod drugog lika)
        HashMap<Stat, Integer> potionStats = new HashMap<>();
        Item healthPotion = new Item("Health Potion", ItemType.MOB_DROP, ItemRarity.COMMON, true, 1, potionStats) {
            @Override
            public int calculateValue() {
                return 25; // Cena napitka je 25 zlata
            }
        };

        // Gandalf ima 300 zlata, ali nema napitak u inventaru
        // Kreiramo drugog lika (Trgovca) koji ima napitak u svom inventaru
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

        // Pozivamo Trader klasu (proveri da li je makeTrade metoda u Trader klasi public!)
        // Trgovina: Gandalf (p2) kupuje healthPotion od Merchant-a (p1)
        // Definicija u Trader klasi: makeTrade(Character p1 (seller), Character p2 (buyer), Item item)
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

