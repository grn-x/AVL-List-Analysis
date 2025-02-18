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
     * Temporary solution to get the same list of random names for both the compiled and interpreted code
     * fix everything
     *
     * @param reference  The list to be populated
     * @param numEntries The number of entries to be added
     * @param nameLength Artifact from the original code OnlineIDE Code, not used
     * @return List of Lexikoneintrags with the same names as the compiled code
     */
    public static List<de.grnx.interpreted.Lexikoneintrag> populateListRef_dual(List<Lexikoneintrag> reference, int numEntries, int nameLength) {
        var returns = new ArrayList<de.grnx.interpreted.Lexikoneintrag>();
        for (String name : FileHandler.getInstance().getRandomNames(numEntries)) {
            reference.add(new Lexikoneintrag(name));
            returns.add(new de.grnx.interpreted.Lexikoneintrag(name));
        }
        return returns;
    }

    /**
     * Temporary solution to get the same list of random names for both the compiled and interpreted code
     * fix everything
     *
     * @param reference  The list to be populated
     * @param numEntries The number of entries to be added
     * @param nameLength Artifact from the original code OnlineIDE Code, not used
     * @return List of Lexikoneintrags with the same names as the compiled code
     */
    public static List<de.grnx.interpreted.Lexikoneintrag> populateListRef_Duplicates_dual(List<Lexikoneintrag> reference, int numEntries, int nameLength) {
        var returns = new ArrayList<de.grnx.interpreted.Lexikoneintrag>();

        Set<String> addedNames = new HashSet<>(FileHandler.getInstance().getRandomNames(numEntries));

        for (String name : addedNames) {
            reference.add(new Lexikoneintrag(name));
            returns.add(new de.grnx.interpreted.Lexikoneintrag(name));
        }

        //solve this with streams or add all

        return returns;
    }


    /**
     * Temporary solution to get the same list of random names for both the compiled and interpreted code
     * fix everything
     *
     * @param reference  The list to be populated
     * @param numEntries The number of entries to be added
     * @param nameLength Artifact from the original code OnlineIDE Code, not used
     * @return List of Lexikoneintrags with the same names as the compiled code
     */
    public static List<de.grnx.interpreted.Lexikoneintrag> populateListRef_Duplicates_dual_large(List<Lexikoneintrag> reference, int numEntries, int nameLength) {
        var returns = new ArrayList<de.grnx.interpreted.Lexikoneintrag>();
        Random random = new Random();

        for (int i = 0; i < numEntries; i++) {
            StringBuilder nameBuilder = new StringBuilder(nameLength);
            for (int j = 0; j < nameLength; j++) {
                char randomChar = (char) ('a' + random.nextInt(26)); // Generate random lowercase letter
                nameBuilder.append(randomChar);
            }
            String name = nameBuilder.toString();
            reference.add(new Lexikoneintrag(name));
            returns.add(new de.grnx.interpreted.Lexikoneintrag(name));
        }

        return returns;
    }


    public static Object[] populateListRef_Duplicates_trio_large(List<de.grnx.compiled.Lexikoneintrag> reference, int numEntries, int nameLength) {
        var returns = new ArrayList<de.grnx.interpreted.Lexikoneintrag>();
        var returns_avl = new ArrayList<de.grnx.interpretedAVL.Lexikoneintrag>();
        Random random = new Random();
        var stringSet = new HashSet<String>();

        for (int i = 0; i < numEntries; i++) {
            StringBuilder nameBuilder = new StringBuilder(nameLength);
            for (int j = 0; j < nameLength; j++) {
                char randomChar = (char) ('a' + random.nextInt(26)); // Generate random lowercase letter
                nameBuilder.append(randomChar);
            }

            stringSet.add(nameBuilder.toString());
        }

        for(String name : stringSet) {
            reference.add(new Lexikoneintrag(name));
            returns.add(new de.grnx.interpreted.Lexikoneintrag(name));
            returns_avl.add(new de.grnx.interpretedAVL.Lexikoneintrag(name));
        }
        //return null;
        return new Object[] {(Object)returns, (Object)returns_avl};
        //return new ArrayList<List<de.grnx.interpreted.Lexikoneintrag>>[]{returns, returns_avl};
    }

    public static ContentDTO populateLists(int numEntries, int nameLength){
        var returns = new ArrayList<de.grnx.interpreted.Lexikoneintrag>();
        var returns_avl = new ArrayList<de.grnx.interpretedAVL.Lexikoneintrag>();
        var reference = new ArrayList<de.grnx.compiled.Lexikoneintrag>();
        Random random = new Random();
        var stringSet = new HashSet<String>();

        for (int i = 0; i < numEntries; i++) {
            StringBuilder nameBuilder = new StringBuilder(nameLength);
            for (int j = 0; j < nameLength; j++) {
                char randomChar = (char) ('a' + random.nextInt(26)); // Generate random lowercase letter
                nameBuilder.append(randomChar);
            }

            stringSet.add(nameBuilder.toString());
        }

        for(String name : stringSet) {
            reference.add(new Lexikoneintrag(name));
            returns.add(new de.grnx.interpreted.Lexikoneintrag(name));
            returns_avl.add(new de.grnx.interpretedAVL.Lexikoneintrag(name));
        }

        return new ContentDTO(reference, returns, returns_avl);
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
