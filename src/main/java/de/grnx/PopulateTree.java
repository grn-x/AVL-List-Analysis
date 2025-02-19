package de.grnx;

import de.grnx.compiled.Lexikoneintrag;


import java.util.*;

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


    public static ContentDTO populateListNames(int numEntries){
        var compiled = new ArrayList<de.grnx.compiled.Lexikoneintrag>();
        var interpreted = new ArrayList<de.grnx.interpreted.Lexikoneintrag>();
        var interpretedAVL = new ArrayList<de.grnx.interpretedAVL.Lexikoneintrag>();


        for (String name : FileHandler.getInstance().getRandomNames(numEntries)) {
            compiled.add(new de.grnx.compiled.Lexikoneintrag(name));
            interpreted.add(new de.grnx.interpreted.Lexikoneintrag(name));
            interpretedAVL.add(new de.grnx.interpretedAVL.Lexikoneintrag(name));
        }

        return new ContentDTO(compiled, interpreted, interpretedAVL);
    }


    public static ContentDTO populateListNamesUnique(int numEntries){
        var compiled = new ArrayList<de.grnx.compiled.Lexikoneintrag>();
        var interpreted = new ArrayList<de.grnx.interpreted.Lexikoneintrag>();
        var interpretedAVL = new ArrayList<de.grnx.interpretedAVL.Lexikoneintrag>();


        var stringSet = new HashSet<String>(FileHandler.getInstance().getRandomNames(numEntries));

        for(String name : stringSet) {
            compiled.add(new de.grnx.compiled.Lexikoneintrag(name));
            interpreted.add(new de.grnx.interpreted.Lexikoneintrag(name));
            interpretedAVL.add(new de.grnx.interpretedAVL.Lexikoneintrag(name));
        }


        return new ContentDTO(compiled, interpreted, interpretedAVL);
    }


    public static ContentDTO populateListRandomUnique(int numEntries, int lenEntries){
        var compiled = new ArrayList<de.grnx.compiled.Lexikoneintrag>();
        var interpreted = new ArrayList<de.grnx.interpreted.Lexikoneintrag>();
        var interpretedAVL = new ArrayList<de.grnx.interpretedAVL.Lexikoneintrag>();

        Random random = new Random();
        var stringSet = new HashSet<String>();

        for (int i = 0; i < numEntries; i++) {
            StringBuilder nameBuilder = new StringBuilder(lenEntries);
            for (int j = 0; j < lenEntries; j++) {
                char randomChar = (char) ('a' + random.nextInt(26)); // Generate random lowercase letter
                nameBuilder.append(randomChar);
            }

            stringSet.add(nameBuilder.toString());
        }

        for(String name : stringSet) {
            compiled.add(new de.grnx.compiled.Lexikoneintrag(name));
            interpreted.add(new de.grnx.interpreted.Lexikoneintrag(name));
            interpretedAVL.add(new de.grnx.interpretedAVL.Lexikoneintrag(name));
        }

        return new ContentDTO(compiled, interpreted, interpretedAVL);
    }


    public static ContentDTO populateListRandom(int numEntries, int lenEntries){
        var compiled = new ArrayList<de.grnx.compiled.Lexikoneintrag>();
        var interpreted = new ArrayList<de.grnx.interpreted.Lexikoneintrag>();
        var interpretedAVL = new ArrayList<de.grnx.interpretedAVL.Lexikoneintrag>();

        Random random = new Random();

        for (int i = 0; i < numEntries; i++) {
            StringBuilder nameBuilder = new StringBuilder(lenEntries);
            for (int j = 0; j < lenEntries; j++) {
                char randomChar = (char) ('a' + random.nextInt(26)); // Generate random lowercase letter
                nameBuilder.append(randomChar);
            }

            String name = nameBuilder.toString();
            compiled.add(new de.grnx.compiled.Lexikoneintrag(name));
            interpreted.add(new de.grnx.interpreted.Lexikoneintrag(name));
            interpretedAVL.add(new de.grnx.interpretedAVL.Lexikoneintrag(name));

        }


        return new ContentDTO(compiled, interpreted, interpretedAVL);
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
