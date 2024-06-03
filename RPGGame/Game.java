import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class Game {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        RPGCharStats cs = new RPGCharStats();

        System.out.println("Welcome to a simple text-based RPG Game!");

        boolean loop = true;
        
        while(loop) {
            try {
                System.out.println("\n1. Create a new character.");
                System.out.println("2. Load save file.");
                System.out.println("3. Exit game.");
                System.out.print("Enter the respective number of your decision: ");
                int choice = sc.nextInt();
    
                if(choice == 1) {
                    System.out.print("Enter character name: ");
                    String name = sc.next();
                    System.out.print("Enter save file (Number format (1, 2, etc.): ");
                    int newSave = sc.nextInt();
                    System.out.print("Do you want to override the save file? \n1. Yes\n2. No\nEnter the respective number of your decision: ");
                    int confirmation = sc.nextInt();
                    
                    switch(confirmation) {
                        case 1:
                            RPGCharCreate cn = new RPGCharCreate(name);
                            cn.newCharacter(newSave);
                            break;
                        case 2:
                            System.out.println("Returning to main menu.");
                            break;
                    }
                }
                else if(choice == 2) {
                    RPGCharLoad cl = new RPGCharLoad();

                    System.out.print("Load save file: ");
                    int loadSave = sc.nextInt();
                    
                    cl.loadCharacter(loadSave);

                    if(RPGCharCreate.name != null) {
                        boolean gameLoop = true;
                        while(gameLoop) {
                            System.out.println("\nWhat do you want to do?");
                            System.out.println("1. Check Character");
                            System.out.println("2. Upgrade Stats");
                            System.out.println("3. Battle!");
                            System.out.println("4. Heal");
                            System.out.println("5. Save Character");
                            System.out.println("6. Back to Main Menu");
                            System.out.print("Enter the respective number of your decision: ");
                            int userChoice = sc.nextInt();
    
                            switch (userChoice) {
                                case 1:
                                    System.out.println("Character: " + RPGCharLoad.name);
                                    System.out.printf("EXP: %.2f",RPGCharStats.exp); 
                                    System.out.printf("\nHealth: %.2f", RPGCharStats.health);
                                    System.out.printf("/%.2f", RPGCharStats.maxHealth);
                                    System.out.printf("\nStrength: %.2f", RPGCharStats.strength);
                                    System.out.printf("\nDefence: %.2f", RPGCharStats.defence);

                                    System.out.println("\n\nWallet:");
                                    System.out.printf("Copper: %.2f", RPGCharCurrency.copper);
                                    System.out.printf("\nSilver: %.2f", RPGCharCurrency.silver);
                                    System.out.printf("\nGold: %.2f", RPGCharCurrency.gold);
                                    System.out.println("");
                                    break;
                                case 2:
                                    System.out.println("What attribute do you want to increase?");
                                    System.out.println("1. Health");
                                    System.out.println("2. Strength");
                                    System.out.println("3. Defence");
                                    System.out.print("Enter the number respective to your decision: ");
                                    int upChoice = sc.nextInt();
    
                                    switch (upChoice) {
                                        case 1:
                                            System.out.print("Enter the desired amount (Minimum of 5 EXP. 5 EXP per 1 Health point): ");
                                            int upHealth = sc.nextInt();
                                            cs.upgradeMaxHealth(upHealth);
                                            break;
                                        case 2:
                                            System.out.print("Enter the desired amount (Minimum of 10 EXP. 10 EXP per 1 Strength point): ");
                                            int upStrength = sc.nextInt();
                                            cs.upgradeStrength(upStrength);
                                            break;
                                        case 3:
                                            System.out.print("Enter the desired amount (Minimum of 10 EXP. 10 EXP per 1 Defence point): ");
                                            int upDefence = sc.nextInt();
                                            cs.upgradeDefence(upDefence);
                                            break;
                                        default: 
                                            System.out.println("Invalid input!");
                                            break;
                                    }
                                    break;
                                case 3:
                                    System.out.print("Enter enemy level: ");
                                    int lvl = sc.nextInt();
                                    cs.battle(lvl);
                                    break;
                                case 4:
                                    System.out.print("Do you want to heal yourself? \n1. Yes \n2. No\nEnter the respective number of your decision: ");
                                    int heal = sc.nextInt();
                                    switch(heal) {
                                        case 1:
                                            if (RPGCharLoad.maxHealth <= 100) {
                                                double cost = (RPGCharLoad.maxHealth/10) * 10;
                                                RPGCharCurrency.copper -= cost;
                                                System.out.println("You have healed yourself!");
                                                RPGCharLoad.health = RPGCharLoad.maxHealth;
                                            }
                                            else if (RPGCharLoad.maxHealth <= 101) {
                                                double cost = (RPGCharLoad.maxHealth/100) * 1;
                                                RPGCharCurrency.silver -= cost;
                                                System.out.println("You have healed yourself!");
                                                RPGCharLoad.health = RPGCharLoad.maxHealth;
                                            }
                                            else if (RPGCharLoad.maxHealth <= 1001) {
                                                double cost = (RPGCharLoad.maxHealth/1000) * 1;
                                                RPGCharCurrency.gold -= cost;
                                                System.out.println("You have healed yourself!");
                                                RPGCharLoad.health = RPGCharLoad.maxHealth;
                                            }
                                            else {
                                                System.out.println("You have insufficient money!");
                                            }
                                            break;
                                        case 2:
                                            System.out.print("Suit yourself.");
                                            break;
                                    }
                                    break;
                                case 5:
                                    System.out.print("Select save file: ");
                                    int saveGame = sc.nextInt();
                                    System.out.print("Do you want to override the save file? \n1. Yes\n2. No\nEnter the respective number of your decision: ");
                                    int confirmation = sc.nextInt();
                                    switch(confirmation) {
                                        case 1:
                                            try(PrintWriter w = new PrintWriter(
                                                new FileWriter("saves\\save" + saveGame + ".txt", false))) {
                                                    w.println(RPGCharLoad.name + " : " + RPGCharStats.exp + " : " + RPGCharLoad.maxHealth + " : " + RPGCharLoad.health + " : " + RPGCharLoad.strength + " : " + RPGCharLoad.defence);
                                                    w.println(RPGCharCurrency.copper + " : " + RPGCharCurrency.silver + " : " + RPGCharCurrency.gold);
                                                    System.out.println("Character successfully saved!");
                                            }
                                            catch(IOException e) {
                                                System.out.println("Error!");
                                            }
                                            break;
                                        case 2:
                                            System.out.println("Returning to decisions.");
                                            break;
                                    }
                                    break;
                                case 6:
                                    System.out.print("You will be taken to the main menu. All progress might be lost if not saved. Continue? \n1. Yes \n2. No\nEnter the number respective to your decision: ");
                                    int exitChoice = sc.nextInt();
    
                                    switch (exitChoice) {
                                        case 1:
                                            gameLoop = false;
                                            main(args);
                                            break;
                                        case 2:
                                            System.out.println("Returning to game.");
                                            break;
                                        default:
                                            System.out.println("Invalid input!");
                                            break;
                                    }
                                        break;
                                default:
                                    System.out.println("Invalid input.");
                                    break;
                            }
                            loop = false;
                        }
                    }
                    else {
                        System.out.println("The save file is empty!");
                    }
                }
                else if(choice == 3) {
                    System.out.println("Thank you for playing!");
                    loop = false;
                }
                else {
                    System.out.println("Invalid input. Please try again.");
                }
            }
            catch (InputMismatchException ime) {
                System.out.println("Error detected. You will be put back to the main menu to avoid further errors.");
                main(args);
                loop = false;
            }
            catch (NoSuchElementException nsee) {
                System.out.println("Massive error detected.");
                loop = false;
            }
        }
        sc.close();
    }
}