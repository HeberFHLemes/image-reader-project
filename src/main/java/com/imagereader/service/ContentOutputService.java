package com.imagereader.service;

import java.util.Map;
import java.util.Map.Entry;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Objects;

public class ContentOutputService {
    
    /**
     * Key: name of the file
     * Value: content to be written
     */
    private Map<String, String> files;

    private final Path outputDir = Path.of("output");

    public ContentOutputService(){
        this.files = new HashMap<>();
    }

    /**
     * Writes/creates files '.txt' inside the destinated output folder
     * according to the data inside the HashMap 'files'
     * @throws IOException
     */
    public void output() throws IOException{
        
        Files.createDirectories(outputDir); 
        
        for (Entry<String, String> entry : this.files.entrySet()){
            String fileName = entry.getKey() + ".txt";
            Path path = outputDir.resolve(fileName);
            Files.write(
                path,
                entry.getValue().getBytes(StandardCharsets.UTF_8)
            );
        }
    }

    /**
     * Optional method for addFile(String name, String content).
     * @param fileNames
     * @param fileContents
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
     * @param name the file name (e.g, "img.png")
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
