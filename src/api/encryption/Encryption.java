package api.encryption;

import java.util.ArrayList;
import java.util.Collections;

public interface Encryption {

    public String encryptMessage(String decryptedMessage);

    public String decryptMessage(String encryptedMessage);

    public static ArrayList<Character> getNewKey(){
        char character = ' ';
        ArrayList<Character> myKey = new ArrayList<Character>();
        for (int i = 32; i < 123; i++) {
            myKey.add(Character.valueOf(character));
            character++;
        }
        return myKey;
    }
    public static ArrayList<Character> shuffleKey(ArrayList<Character> notShuffled){
        ArrayList<Character> shuffled = new ArrayList<Character>();
        for (Character character : notShuffled) {
            shuffled.add(character);
        }
        Collections.shuffle(shuffled);
        return shuffled;
    }
}
