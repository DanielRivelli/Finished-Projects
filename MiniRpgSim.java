/*
MiniRpgSim.java
A beginner Java RPG simulator demonstrating:
- Methods
- Randomization
- Loops and conditionals
- Basic player vs. enemy combat
Created by: Daniel Rivelli
*/


import java.util.Scanner;
import java.util.Random;

public class MiniRpgSim {

        static String name;
        static int playerHealth = 0;
        static int attack = 0;
        static int defense = 0;
        static String mobName = "mob";
        static int mobHealth = 0;
        static int mobAttack = 0;
        static int mobDefense = 0;
        static int mobNumber = 0;
        static int phoenixRoll;
        static int moveSelection;
        static int healNumber;
        static int currentAttack;
        static int damage = 0;
        static int playerMaxHealth = 0;
        static int rollForHeal = 0;
        static int rollForBlock = 0;
        static boolean defending = false;
        static int rollForUlt = 0;
        static int mobAttackRoll = 0;
        static int mobDamage = 0;
        
        static Scanner keyboard = new Scanner(System.in);
        static Random rand = new Random();


    public static void main(String[] args) {
    
    characterSelection();
    
    while (playerHealth > 0){
        rollForMob();
        moves();
        
        if (playerHealth <=0){
        System.out.println("You died.");
        break;
        } 
        }
        keyboard.close();
        }

        // Select character based on their stats with one character that has all random stats. Establishes their stats and name for future use.
        public static void characterSelection() {
        System.out.println("Welcome to my Mini Rpg Simulator!");
        System.out.println();
        System.out.println("Choose your character!");
        System.out.println("1: Nate - Health: 60, Attack: 8, Defense: 8");
        System.out.println("2: Thomas - Health: 45, Attack: 12, Defense: 5");
        System.out.println("3: Duane - Health: 50, Attack: 10, Defense: 6");
        System.out.println("4: Tyler - All Random Stats");
        
        System.out.println("Please enter a number of which character you'd like to play as");
        int playerSelect = keyboard.nextInt();
        
        if (playerSelect == 1) {
        System.out.println("You chose Nate!");
        name = "Nate";
        playerMaxHealth = 60;
        attack = 8;
        defense = 8;
        playerHealth = 60;
      } else if (playerSelect == 2) {
        System.out.println("You chose Thomas!");
        name = "Thomas";
        playerMaxHealth = 45;
        attack = 12;
        defense = 5;
        playerHealth = 45;        
      } else if (playerSelect == 3) { 
        System.out.println("You chose Duane!");
        name = "Duane";
        playerMaxHealth = 50;
        attack = 10;
        defense = 6;
        playerHealth = 50;
      } else if (playerSelect == 4) {
        System.out.println("You chose Tyler!");
        name = "Tyler";
        playerMaxHealth = rand.nextInt(21)+40;
        attack = rand.nextInt(10)+5;
        defense = rand.nextInt(8)+2;
        playerHealth = playerMaxHealth;
      }
        
        System.out.print("Health: " + playerHealth);
        System.out.print(" Attack: " + attack);
        System.out.println(" Defense: " + defense);
        }
        
        //Rolls for a random mob out of 7, with a 1/100 chance of facing an overpowered mob.
        public static void rollForMob(){
        
        phoenixRoll = rand.nextInt(100)+1;
        
        if (phoenixRoll == 100) {
         System.out.println("You've encountered a 1/100 phoenix, good luck.");
         mobName = "Phoenix";
         mobHealth = 80;
         mobAttack = 15;
         mobDefense = 6;
        } else {
        
        mobNumber = rand.nextInt(6)+1;
        
        if (mobNumber == 1) {
        System.out.println();
        System.out.println("You've encountered a goblin!");
        mobName = "Goblin";
        mobHealth = 30;
        mobAttack = 8;
        mobDefense = 3;
        } else if (mobNumber == 2) {
        System.out.println();
        System.out.println("You've encountered a slime!");
        mobName = "Slime";
        mobHealth = 25;
        mobAttack = 6;
        mobDefense = 2;
        } else if (mobNumber == 3) {
        System.out.println();
        System.out.println("You've encountered a brute!");
        mobName = "Brute";
        mobHealth = 40;
        mobAttack = 10;
        mobDefense = 4;
        } else if (mobNumber == 4) {
        System.out.println();
        System.out.println("You've encountered a phantom!");
        mobName = "Phantom";
        mobHealth = 30;
        mobAttack = 12;
        mobDefense = 5;
        } else if (mobNumber == 5) {
        System.out.println();
        System.out.println("You've encountered a dragon!");
        mobName = "Dragon";
        mobHealth = 45;
        mobAttack = 13;
        mobDefense = 5;
        } else if (mobNumber == 6) {
        System.out.println("You've encountered a cave troll!");
        mobName = "Cave Troll";
        mobHealth = 42;
        mobAttack = 11;
        mobDefense = 6;
        }
        }
        }
        
        //// Player action choices and battle resolution
        public static void moves (){
        
        while (playerHealth > 0 && mobHealth > 0){
        System.out.println();
        System.out.println("Your health " + playerHealth);
        System.out.println(mobName + "'s health: " + mobHealth);
        System.out.println("What would you like to do?");
        System.out.println("1:Attack 2:Heal (50% chance) 3:Block (75% chance) 4: Ultimate Ability (25% chance)");
        moveSelection = keyboard.nextInt();
        defending = false;
        
        if (moveSelection == 1) {
        currentAttack = rand.nextInt(attack)+5;
         damage = currentAttack - mobDefense;
         if (damage < 0) damage = 1;
         mobHealth -= damage;
         System.out.println("You dealt " + damage + " damage to " + mobName + "!");
        } 
        else if (moveSelection == 2) {
         rollForHeal = rand.nextInt(2)+1;
         if (rollForHeal == 1) {
            healNumber = rand.nextInt(10)+5;
            playerHealth = playerHealth + healNumber;
            if (playerHealth > playerMaxHealth) playerHealth = playerMaxHealth;
            System.out.println("You healed " + healNumber);
        } else {
         System.out.println("Your healing failed!");
        } 
      }
        else if (moveSelection == 3) {
         rollForBlock = rand.nextInt(4)+1;
         if (rollForBlock == 4) {
        System.out.println("Your block failed!");
        } else {
         defending = true;
         System.out.println("You successfully blocked the enemy's attack!");
        }
        } else if (moveSelection == 4) {
        rollForUlt = rand.nextInt(4)+1;
        if (rollForUlt == 4) {
        currentAttack = rand.nextInt(attack)+1;
        currentAttack = currentAttack * 5;
        damage = currentAttack - mobDefense;
        if (damage < 0) damage = 0;
        mobHealth -= damage;
        System.out.println("You succeeded and did " + damage + " damage to the " + mobName);
        } else {
        System.out.println("Your ultimate failed.");
        }
      }
        mobAttackRoll = rand.nextInt(mobAttack) +6;
        mobDamage = mobAttackRoll - defense;
        if (mobDamage < 0){
        mobDamage = 1;
      }
        if (defending == true) {
        mobDamage = 0;
        defending = false;
        }
        playerHealth = playerHealth - mobDamage;
        System.out.println("The " + mobName + " did " + mobDamage + " damage to you!");
        }
        
        //If player kills mob, they get 20 health added (but not over their max health), and spawns a new mob
        playAgain();
        }
        public static void playAgain(){
        if (playerHealth>0){
        playerHealth = playerHealth + 20;
        System.out.println("You defeated the " + mobName + " and gained 20 health!");
        defending = false;
        } else if (playerHealth>playerMaxHealth) {
        playerHealth = playerMaxHealth;
        }
        }
        
   }       
