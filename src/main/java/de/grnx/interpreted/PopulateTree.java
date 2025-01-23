package de.grnx.interpreted;

import de.grnx.compiled.Lexikoneintrag;

import java.util.List;
import java.util.Random;

public class PopulateTree {


    public static void populateListRef(List<Lexikoneintrag> reference, int numEntries, int nameLength) {
        while (numEntries-- > 0) {
            reference.add(new Lexikoneintrag(getRandomString(nameLength)));
        }
    }

    public static void shuffleListRef() {
        //shuffle
    }


    private static int getRandomInt() {
        return getRandomInt(0, 10);
    }

    private static int getRandomInt(int min, int max) {
        var r = new Random();
        return r.nextInt(max-min)+min;//r.randint(min, max);

    }

    private static char getRandomChar() {
        return(char)getRandomInt(65, 122);
    }

    private static String getRandomString(int length) {
        String returns = "";
        while (length > 0) {
            length--;
            returns += getRandomChar();
        }
        return returns;
    }


}
