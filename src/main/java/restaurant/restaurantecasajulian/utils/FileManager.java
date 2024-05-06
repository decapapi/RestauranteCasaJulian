package restaurant.restaurantecasajulian.utils;

import java.io.*;
import java.util.*;

/**
 * Class to manage files
 * Read, write and append to files
 */

public class FileManager {

    /**
     * Check if a file exists
     * @param path the path to the file
     * @return true if the file exists, false otherwise
     */
    public static boolean exists(String path) {
        return new File(path).exists();
    }

    /**
     * Read all the lines from a file
     * @param path the path to the file
     * @return a list with all the lines
     */
    public static List<String> readAllLines(String path) {
        List<String> lines = new ArrayList<>();
        if (exists(path)) {
            try (BufferedReader inputFile = new BufferedReader(new FileReader(path))) {
                String line = inputFile.readLine();
                while (line != null) {
                    lines.add(line);
                    line = inputFile.readLine();
                }
            } catch (IOException fileError) {
                System.err.println("Error reading from file: " + fileError.getMessage());
            } catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
        return lines;
    }

    /**
     * Write a string to a file
     * @param path the path to the file
     * @param content the content to write
     */
    public static void write(String path, String content) {
        try (PrintWriter printWriter = new PrintWriter(path))
        {
            printWriter.println(content);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
        catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    /**
     * Write a list of strings to a file
     * @param path the path to the file
     * @param content the content to write
     */
    public static void write(String path, List<String> content) {
        try (PrintWriter printWriter = new PrintWriter(path)) {
            for (String line : content) {
                printWriter.println(line);
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    /**
     * Append a string to a file
     * @param path the path to the file
     * @param content the content to append
     */
    public static void append(String path, String content) {
        try (PrintWriter printWriter = new PrintWriter(new FileWriter(path, true)))
        {
            printWriter.println(content);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
