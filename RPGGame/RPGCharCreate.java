import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class RPGCharCreate extends RPGCharStats {
    protected static String name;

    RPGCharCurrency cc = new RPGCharCurrency();

    public RPGCharCreate(String name){
        setCharName(name);
    }
    public void setCharName(String name) {
        RPGCharCreate.name = name;
    }
    public String getCharName(){
        return name;
    }

    public void newCharacter(int save){
        try(PrintWriter w = new PrintWriter(        
            new FileWriter("saves\\save" + save + ".txt", false))) {
                w.println(name + " : " + exp + " : " + maxHealth +" : " + health + " : " + strength + " : " + defence);
                w.println(RPGCharCurrency.copper + " : " + RPGCharCurrency.silver + " : " + RPGCharCurrency.gold);
                System.out.println("Character successfully created!");
            }
        catch(IOException e) {
                System.out.println("Error!");
        }
    }
}