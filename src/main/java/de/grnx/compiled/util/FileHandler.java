package de.grnx.compiled.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

/**
 * Singleton
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

    public static FileHandler getInstance() {
        if (instance == null) {
            instance = new FileHandler();
        }
        return instance;
    }

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

    private ArrayList<String> readFromFile(String filename) throws IOException {
        return Files.lines(Paths.get(filename))
                .flatMap(this::streamCriteria)
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }

    private Stream<String> streamCriteria(String line) {
        if (line.startsWith("#")||line.startsWith("//")||line.contains("-")||line.isBlank()||line.toLowerCase().contains("jean")) {
            return Stream.empty();
        }else{
            return Stream.of(line.substring(0, 1).toUpperCase()+line.substring(1)); //Capitalize Name
            // how much does this call cost and how expensive is it to insert the returned into the original stream using flatmap?
            // Alternative is to use filter first and then map but that seems less elegant
        }
    }
}
