import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class RPGCharLoad extends RPGCharCreate {

    RPGCharCurrency cc = new RPGCharCurrency();

    public RPGCharLoad(){
        super(name);
    }

    public void loadCharacter(int save) {
        try(Scanner sc = new Scanner(
            new File("saves\\save" + save + ".txt"))) {
                while (sc.hasNextLine()) {
                    String line = sc.nextLine();
                    String[] stats = line.split(" : ");
                    String line2 = sc.nextLine();
                    String[] currency = line2.split(" : ");

                    String storedName = stats[0];
                    double storedEXP = Double.parseDouble(stats[1]);
                    double storedMaxHealth = Double.parseDouble(stats[2]);
                    double storedHealth = Double.parseDouble(stats[3]);
                    double storedStrength = Double.parseDouble(stats[4]);
                    double storedDefence = Double.parseDouble(stats[5]);

                    double storedCopper = Double.parseDouble(currency[0]);
                    double storedSilver = Double.parseDouble(currency[1]);
                    double storedGold = Double.parseDouble(currency[2]);
                
                    name = storedName;
                    exp = storedEXP;
                    maxHealth = storedMaxHealth;
                    health = storedHealth;
                    strength = storedStrength;
                    defence = storedDefence;

                    RPGCharCurrency.copper = storedCopper;
                    RPGCharCurrency.silver = storedSilver;
                    RPGCharCurrency.gold = storedGold;
                    System.out.println("Welcome " + name + ".");
                }
            }
        catch(FileNotFoundException e) {
            System.out.println("Error!");
        }
    }
}