package de.grnx.compiled.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

/**
 * Singleton class to handle reading the file and getting random names
 * This class is called by {@link PopulateTree#preInit()} to prepare the FileHandler and avoid distorting measurement times
 * Subsequently instatiating the singleton{@link FileHandler#FileHandler()} and reading the file into the names ArrayList field {@link FileHandler#readFromFile(String)}
 */
public class FileHandler {
    private static final String FILE_PATH = "src/assets/MostPopularEuropeanForeNames.txt";
    private static FileHandler instance = null;
    private final ArrayList<String> names;

    private FileHandler() {
        try {
            this.names = readFromFile(FILE_PATH);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("File not found");
        }
    }

    /**
     * @return The singleton instance of the FileHandler
     * This method is optionally called by {@link PopulateTree#preInit()} to prepare the FileHandler and avoid distorting measurement times
     * Subsequently instatiating the singleton{@link FileHandler#FileHandler()} and reading the file into the names ArrayList field {@link FileHandler#readFromFile(String)}
     */
    public static FileHandler getInstance() {
        if (instance == null) {
            instance = new FileHandler();
        }
        return instance;
    }

    /**
     * @param numEntries The number of random names to be returned
     * @return List of random names from the names ArrayList field
     * This method is called by {@link PopulateTree#populateListRef(List, int, int)} to get random names to populate the list
     */
    public List<String> getRandomNames(int numEntries) {
        //return names.stream().unordered().parallel().limit(numEntries).collect(Collectors.toList());
        //doesnt work and may actually be slower than direct access
        Random random = new Random();
        List<String> randomNames = new ArrayList<>(numEntries);
        int size = names.size();
        for (int i = 0; i < numEntries; i++) {
            randomNames.add(names.get(random.nextInt(size)));
        }
        return randomNames;
    }

    /**
     * @param filename The name of the file to be read
     * @return ArrayList of Strings read from the file
     * @throws IOException If the file is not found
     *                     This method gets called when the singleton is first created {@link FileHandler#FileHandler()}
     *                     and reads the file into the final names ArrayList field.
     *                     This method calls {@link FileHandler#streamCriteria(String)} to filter the read lines
     */
    private ArrayList<String> readFromFile(String filename) throws IOException {
        return Files.lines(Paths.get(filename)).flatMap(this::streamCriteria).collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }

    /**
     * @param line The current line read from the file, passed by the Stream and returned as a Stream of Strings to be collected
     * @return Stream of Strings to be collected; Stream because im using flatmap to avoid calling filter and then map, and have a single method to pass
     * Method to be passed into {@link FileHandler#readFromFile(String)}'s Stream's Flatmap function to filter and prepare the OS read lines
     */
    private Stream<String> streamCriteria(String line) {
        if (line.startsWith("#") || line.startsWith("//") || line.contains("-") || line.isBlank() || line.toLowerCase().contains("jean")) {
            return Stream.empty();
        } else {
            return Stream.of(line.substring(0, 1).toUpperCase() + line.substring(1)); //Capitalize Name
            // how much does this call cost and how expensive is it to insert the returned into the original stream using flatmap?
            // Alternative is to use filter first and then map but that seems less elegant
        }
    }
}
