import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

//The following class is used as a file reader for the game. It's purpose is to list all the characters to the user. It
//can be used to reduce repetition of code whenever the game needs to list certain things to the user
public class Character {

    public void listCharacters() {
        //An instance of br is created here which is initialized to null
        BufferedReader br = null;
        //A try catch log is used to handle exceptions
        try {
            //BufferedReader will look for a txt ile named Characters which will be read and displayed
            //in the main method in the InteractiveFantasyStory class
            br = new BufferedReader(new FileReader("Character.txt"));
            String line;
            //As long as there is a line, it will read it
            while ((line = br.readLine()) != null) {
                JOptionPane.showMessageDialog(null, line);
            }

        } catch (
                IOException e) {

        } finally {

        }
    }
}

