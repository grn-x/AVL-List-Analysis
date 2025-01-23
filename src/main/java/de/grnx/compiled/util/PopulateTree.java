package de.grnx.compiled.util;

import de.grnx.compiled.Lexikoneintrag;

import java.io.File;
import java.util.List;
import java.util.Random;

public class PopulateTree {

    /**
     * Call this to prepare the FileHandler and avoid skewed time results on the first run
     * {@link FileHandler#getInstance()}
     * -> Calling
     * {@link FileHandler#FileHandler()}
     * -> Calling
     * {@link FileHandler#readFromFile(String)}
     *
     * @see FileHandler
     */
    public static void preInit() {
        FileHandler.getInstance();
    }

    /**
     * Populates the passed List Object with random names
     *
     * @param reference  The list to be populated
     * @param numEntries The number of entries to be added
     * @param nameLength Artifact from the original code OnlineIDE Code, not used
     */
    public static void populateListRef(List<Lexikoneintrag> reference, int numEntries, int nameLength) {
        for (String name : FileHandler.getInstance().getRandomNames(numEntries)) {
            reference.add(new Lexikoneintrag(name));
        }
    }

    /**
     * @deprecated Might implement later
     */
    public static void shuffleListRef() {
        //shuffle
    }

    /**
            * @deprecated Artifact from the original code OnlineIDE Code, not used
     */
    private static int getRandomInt() {
        return getRandomInt(0, 10);
    }

    /**
     * Define Boundaries for Random Integer, used by {@link #getRandomString(int)}, {@link #getRandomChar()}
     * @param min Minimum Integer
     * @param max Maximum Integer
     * @return Random Integer between min and max
     */
    private static int getRandomInt(int min, int max) {
        var r = new Random();
        return r.nextInt(max - min) + min;//r.randint(min, max); //OnlineIDE Code

    }

    /**
     * Get a random character, calling {@link #getRandomInt(int, int)} with ASCII value bounds for A and z
     * @return Random Character between A and z (ASCII 65-122) including some special characters in between
     * */
    private static char getRandomChar() {
        return (char) getRandomInt(65, 122);
    }

    /**
     * Get a random String of a given length, calling {@link #getRandomChar()} for each character
     * @param length Length of the String
     * @return Random String of the given length
     * @deprecated Artifact from the original code OnlineIDE Code, not used
     */
    private static String getRandomString(int length) {
        String returns = "";
        while (length > 0) {
            length--;
            returns += getRandomChar();
        }
        return returns;
    }


}
