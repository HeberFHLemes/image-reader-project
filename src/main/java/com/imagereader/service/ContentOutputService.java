package com.imagereader.service;

import java.util.Map;
import java.util.Map.Entry;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Objects;

/**
 * Responsible for taking the file names and
 * the content read from the OCR and then output
 * the content on an output folder inside a
 * text file with the same name as the image.
 */
public class ContentOutputService {
    
    /**
     * Key: name of the file
     * Value: content to be written
     */
    private final Map<String, String> files = new HashMap<>();

    private final Path outputDir = Path.of("output");
    // Use env variables if wanted
    // private final Path outputDir = Path.of(System.getenv("OUTPUT_FOLDER"));

    public ContentOutputService(){}

    /**
     * Writes/creates files '.txt' inside the output folder
     * according to the data inside the HashMap 'files'
     * @throws IOException when it tries to create directories or write into the output files
     */
    public void output() throws IOException{
        
        Files.createDirectories(outputDir); 
        
        for (Entry<String, String> entry : this.files.entrySet()){
            String fileName = entry.getKey() + ".txt";
            Path path = outputDir.resolve(fileName);
            Files.writeString(
                path, entry.getValue()
            );
        }
    }

    /**
     * Optional method for addFile(String name, String content).
     * @param fileNames a String array of the file names (e.g., {"img.png", "img2.png"})
     * @param fileContents a String array of what was read in the OCR operation made on the files
     */
    public void addFiles(String[] fileNames, String[] fileContents){
        if (fileNames.length != fileContents.length){
            System.out.println("The number of filenames and the number of contents does not match");
            return;
        }
        for (int i = 0; i < fileNames.length; i++){
            addFile(fileNames[i], fileContents[i]);
        }
    }

    /**
     * Adds file information to the 'files' HashMap, always checking for null values
     * @param name the file name (e.g., "img.png")
     * @param content what was read in the OCR operation made on the file
     */
    public void addFile(String name, String content){
        this.files.put(
            formatFileName(Objects.requireNonNull(name, "Filename must not be null")), 
            Objects.requireNonNull(content, "File content must not be null")
        );
    }

    /**
     * Remove any unwanted extension (not double ones though),
     * since it will be added ".txt". (e.g., "img.png" -> "img")
     * @param fileName the original file name
     * @return the file name without its last extension
     */
    private String formatFileName(String fileName){
        return fileName.contains(".") 
            ? fileName.substring(0, fileName.lastIndexOf('.')) 
            : fileName;
    }
}
