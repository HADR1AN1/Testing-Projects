public class RPGCharStats {
    
    protected static double exp;
    protected static double health;
    protected static double maxHealth;
    protected static double strength;
    protected static double defence;

    public RPGCharStats() {
        RPGCharStats.exp = 0;
        RPGCharStats.health = 10;
        RPGCharStats.maxHealth = 10;
        RPGCharStats.strength = 3;
        RPGCharStats.defence = 3;
    }

    public void upgradeMaxHealth(int amount) {
        if(exp >= amount && amount >= 5) {
            maxHealth += (amount/5);
            exp -= amount;

            System.out.println("You have gotten healthier!");
            System.out.println("New health: " + maxHealth);
        }
        else {
            System.out.println("You have insufficient EXP!");
        }
    }
    
    public void upgradeStrength(int amount) {
        if(exp >= amount && amount >= 10) {
            strength += (amount/10);
            exp -= amount;

            System.out.println("You have gotten stronger!");
            System.out.println("New strength: " + strength);
        }
        else {
            System.out.println("You have insufficient EXP!");
        }
    }
    
    public void upgradeDefence(int amount) {
        if(exp >= amount && amount >= 10) {
            defence += (amount/10);
            exp -= amount;

            System.out.println("You have gotten more durable!");
            System.out.println("New defence: " + defence);
        }
        else {
            System.out.println("You have insufficient EXP!");
        }
    }

    public void battle(int level) {
        int turn = 0;

        if(level > 0){
            double enemyHealth = ((Math.random() * 5) + 1) * level;
            double enemyStrength = ((Math.random() * 2) + 1) + (level/2);
            double enemyDefence = ((Math.random() * 2) + 1) + (level/2);
            boolean complete = true;

            while(complete) {
                double damage = ((Math.random() * 3) + 1) + strength - enemyDefence;
                if(damage < 0) {
                    damage = 0;
                }

            double enemyDamage = ((Math.random() * 3) + 1) + enemyStrength - defence;
                if(enemyDamage < 0) {
                    enemyDamage = 0;
                }

                health -= enemyDamage;
                
                enemyHealth -= damage;

                System.out.println("\nTurn " + turn);
                System.out.printf("You hit enemy for %.2f", damage);
                System.out.print(" damage!");
                System.out.printf("\nEnemy hit you for %.2f", enemyDamage);
                System.out.print(" damage!\n");
                
                turn++;
                
                if(health <= 0) {
                    System.out.println("You have lost!");
                    health = 1;
                    complete = false;
                }
                else if(enemyHealth <= 0) {
                    double earnedEXP = ((Math.random() * 3) + 1) * level;
                    exp += earnedEXP;
                    System.out.printf("\nYou have won %.2f", earnedEXP);
                    System.out.print(" EXP!\n");

                    if(level <= 10) {
                        double earnedCopper = ((Math.random() * 6) + 1) * level;
                        RPGCharCurrency.copper += earnedCopper;
                        System.out.printf("You have earned %.2f", earnedCopper);
                        System.out.print(" copper!\n");
                    }
                    else if(level <= 11) {
                        double earnedSilver = ((Math.random() * 1) + 1) * level;
                        RPGCharCurrency.silver += earnedSilver;
                        System.out.printf("You have earned %.2f", earnedSilver);
                        System.out.print(" silver!\n");
                    }
                    else if(level <= 21) {
                        double earnedGold = ((Math.random() * 1) + 1) * level;
                        RPGCharCurrency.gold += earnedGold;
                        System.out.printf("You have earned %.2f", earnedGold);
                        System.out.print(" gold!\n");
                    }
                    complete = false;
                }

                if(turn == 50) {
                    complete = false;
                    System.out.println("Turn limit reached! Ending the battle in a draw.");
                }
            }
        }
        else {
            System.out.println("Enemy level cannot be 0 or negative!");
        }
    }
}