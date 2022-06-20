//Concepts are adapted from the exercises provided at....
//(2013). Massive online open course. [online]
//Available at: https://materiaalit.github.io/2013-oo-programming/part1/week-1/ [Accessed 6 Jul. 2019].

import javax.swing.JOptionPane;

import java.io.IOException;
import java.util.Random;

//The following program is a modification of my Assignment 2 program to introduce the concepts of multiple classes, objects,
//object member variables, constructors, accessors, mutators, file reading and conditional execution and repetition

public class InteractiveFantasyStory {

    /*The variables for the player are initialized here. Having different methods with different levels of difficulty where the game gets more difficult as the player progresses
     could have been a beneficial addition to the game*/
    private int playerHP;
    private int playerDamage;
    private int numberOfHealthPotions;
    private int healthPotionHealAmount;
    private int healthPotionDropChance;
    private int numberOfPlayerDefeats;

    public int enemyDamage = 45;
    public int enemyHP = 85;


    //The following is an accessor to get the value of the player's HP
    public int getValues() {
        return playerHP;
    }

    //The following is the mutator with parameters to set the values for the variables of the player. This can be used
    //to reduce repetition of code if the player decides to choose another character and the variables need to be set to
    //new values

    public void setValues(int playerHP, int playerDamage, int numberOfHealthPotions, int healthPotionHealAmount, int healthPotionDropChance, int numberOfPlayerDefeats) {

        //The .this statement is used to refer to the member variables of this object
        this.playerHP = playerHP;
        this.playerDamage = playerDamage;
        this.numberOfHealthPotions = numberOfHealthPotions;
        this.healthPotionHealAmount = healthPotionHealAmount;
        this.healthPotionDropChance = healthPotionDropChance;
        this.numberOfPlayerDefeats = numberOfPlayerDefeats;
    }

    public void gamePlay() {

        //As we want the least amount of work in the main method which is static, the game play of the
        // program is in another method which is non static. The purpose of this method is to execute the game play of the program.
        // To provide a better experience to the user, there can be multiple game play methods where each one has
        // a different story with different enemies*/

        Random rand = new Random();

        /*Boolean is used to control the flow of this while loop*/
        boolean flag = true;
        /*The below while loop is set with the condition that as long as the HP of the player is above 0, the player is able to continue on
         * with their adventure*/
        while (playerHP > 0) {
            /*The below while loop is set so as long as the variable flag is true, the game will continue to loop until the player is defeated or
             * chooses to exit the game*/
            while (flag) {
                /*The player is presented with 3 options when they encounter an enemy. If, else if and else statements are used
                to determine which output to display depending on what the user chooses*/
                int answer = Integer.parseInt(JOptionPane.showInputDialog("What would you like to do? \n1. Attack zombie" + "\n2. Drink health potion\n3. Run!!!"));
            /*If the player selects option 1, the random method chooses a number between 0 and the value assigned to the respective variable and that is
            how much damage is dealt by player and enemy*/
                if (answer == 1) {
                    int damageDealt = rand.nextInt(playerDamage);
                    int damageTaken = rand.nextInt(enemyDamage);
                    /*A calculation is performed to subtract damage dealt from the player and enemy to their HP*/
                    enemyHP -= damageDealt;
                    playerHP -= damageTaken;

                    JOptionPane.showMessageDialog(null,
                            "The zombie lunges towards you and attacks. You attack it back.\nThe enemy looses " + damageDealt + " points and you loose " + damageTaken
                                    + " points.");
                    JOptionPane.showMessageDialog(null, "Your HP: " + playerHP + "  Enemy HP: " + enemyHP);

                    /*Within the while loop for the game, if the player's HP reaches 0 or below, they have been defeated. The user is presented with how
                     * many enemies they had defeated*/
                    if (playerHP <= 0) {
                        JOptionPane.showMessageDialog(null, "Game over! You take too much damage from the zombie and are too weak to go on.");
                        JOptionPane.showMessageDialog(null, "You defeated " + numberOfPlayerDefeats + " zombie(s)");
                        JOptionPane.showMessageDialog(null, "Thank you for playing!");
                        /*The player was defeated and the game is over so the flag below is set to false to end the while loop*/
                        flag = false;
                    }
                    /*Whenever the enemy's HP reaches 0 or below, the following code block executes*/
                    /*There is a bug here. If both the enemy and the player is defeated, the above code executes as well as the code below. The game
                     * continues on even if the player was defeated. Perhaps this can be fixed by changing the conditions or inserting an extra if function
                     * for this scenario however I was unable to get it to work.*/
                    if (enemyHP <= 0) {
                        /*Number of enemies the player defeated is increased by 1*/
                        numberOfPlayerDefeats = numberOfPlayerDefeats + 1;

                        /*A random number is selected between 0 and 100. If that number is less than 65 (number set in player variables for healthPotionDropChance,
                         *the number of health potions is increased by 1. The higher the variable set for healthPotionDropChance, the higher the chance the player
                         * will receive a health potion upon defeating an enemy*/
                        if (rand.nextInt(100) < healthPotionDropChance)
                            numberOfHealthPotions++;

                        JOptionPane.showMessageDialog(null, "The enemy has been defeated! You have "
                                + numberOfHealthPotions + " potion(s).");

                        int answer2 = Integer.parseInt(JOptionPane.showInputDialog(
                                "Would you like to: 1. Continue with your adventure or 2. Exit the woods?"));
                        /*If the player chooses option one to continue on with their adventure, the enemy's health is reset back to 85 and it will loop
                        from the beginning again with a new enemy.*/
                        if (answer2 == 1) {
                            enemyHP = 85;
                        }
                        /*If the user selections option two to exit the woods, they are presented with how many enemies they defeated and the flag is set to
                         * false to terminate the loop*/
                        else if (answer2 == 2) { //
                            JOptionPane.showMessageDialog(null,
                                    "You defeated " + numberOfPlayerDefeats + " enemie(s)! You are a hero.");
                            JOptionPane.showMessageDialog(null, "Thank you for playing!");
                            flag = false;
                        }
                    }
                }
 /*If the user selects option 2, the following code executes. Calculation is created where numHealthPotion is deducted by 1 if they have any in their
        inventory. */
                else if (answer == 2) {

                    if (numberOfHealthPotions > 0) {
                        playerHP += healthPotionHealAmount;
                        numberOfHealthPotions--;

                        JOptionPane.showMessageDialog(null,
                                "You drink the health potion. You now have " + playerHP
                                        + " health points and " + numberOfHealthPotions
                                        + " health potions left.");
                        // If player does not have any potions, the following code executes
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "You do not have any health potions left. If you defeat an enemy, the enemy may drop a health potion");
                    }
                    /*If the user selections option 3, the flag is set the false and the game is ended*/
                } else if (answer == 3) {

                    JOptionPane.showMessageDialog(null,
                            "You run out of the woods as there are too many enemies, you decide to fight another day.");
                    JOptionPane.showMessageDialog(null, "Thank you for playing!");
                    flag = false;

                    // End of story
                }
            }
        }
    }

    public void mainApplication() {

        /*This is a method to present an introduction to the player. The method gamePlay is then called.*/
        //The following is a constructor to initialize the gamePlay object
        InteractiveFantasyStory ifs = new InteractiveFantasyStory();
        //The following is a constructor to initialize the listCharacters object from the Character class. A code requirement
        //was breached here as it does not take any minimum information. It was not needed to have any minimum information
        // from the class to read files from the Character class which is why it was not included
        Character listCharactersObject = new Character();
        //The values are set for the variables of the player from the mutator method
        ifs.setValues(100, 60, 0, 45, 65, 0);
        Random rand = new Random();
        /*An array is created here where a random weapon is chosen from a list. The chosen weapon is then used for the story of the game play*/
        String[] weapons = {"dagger", "wand", "broomstick", "sword", "axe", "balloon", "pineapple"};
        String weapon = weapons[rand.nextInt(weapons.length)];

        String username = (JOptionPane.showInputDialog("Choose a username: "));
        JOptionPane.showMessageDialog(null, "Here is a list of characters you can play as: ");
        //The following method is used to list the type of characters the player can choose during the begining of the game
        listCharactersObject.listCharacters();
        String character = (JOptionPane.showInputDialog("Choose your character: "));
        JOptionPane.showMessageDialog(null, "Welcome " + username + "! " + "You are playing as a " + character);
        JOptionPane.showMessageDialog(null, "This is an interactive game. Zombies will appear and you must defeat them.\nEvery time you defeat an enemy there is a chance that it will drop a health potion which will heal 20 health points.");
        JOptionPane.showMessageDialog(null, "You enter a dark, scary forest and find a " + weapon + " on the ground. You pick it up to use for protection.");

        ifs.gamePlay(); //Game play is invoked


    }

    public static void main(String[] args) throws IOException {

        //This is the main method of the application and it's purpose it just to run the program
        // The following is a constructor to initialize the mainApplicaton object
        InteractiveFantasyStory ifs = new InteractiveFantasyStory();
        //The main application is called here
        ifs.mainApplication();

        /*A code requirement that was not met is 'Loop condition must describe all situations under which the loop will repeat and condition must fail eventually'
         * When the user inputs a string or number other than 1, 2 or 3 when presented with the 3 options when encountering an enemy, the game simply terminates
         * when they should be presented with an error message to "Please enter 1, 2 or 3". I had attempted to do this however a bug was persistent where no
         * matter what the user entered, the game still looped with the first option.*/


    }
}



