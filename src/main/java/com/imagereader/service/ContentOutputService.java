package com.imagereader.service;

import java.util.Map;
import java.util.Map.Entry;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;

public class ContentOutputService {
    
    // Key: name of the file
    // Value: content to be written
    private Map<String, String> files;

    public ContentOutputService(){
        this.files = new HashMap<>();
    }

    // Writes/creates files .txt inside the output/ folder
    // according to the data inside the HashMap 'files';
    public void output() throws IOException{
        Path dir = Path.of("output");
        Files.createDirectories(dir); 
        
        for (Entry<String, String> entry : this.files.entrySet()){
            String fileName = entry.getKey() + ".txt";
            Path path = dir.resolve(fileName);
            Files.write(
                path,
                entry.getValue().getBytes(StandardCharsets.UTF_8)
            );
        }
    }

    public void addFiles(String[] fileNames, String[] fileContents){
        if (fileNames.length != fileContents.length){
            System.out.println("The number of filenames and the number of contents does not match");
            return;
        }
        for (int i = 0; i < fileNames.length; i++){
            this.files.put(formatFileName(fileNames[i]), fileContents[i]);
        }
    }

    public void addFile(String name, String content){
        this.files.put(formatFileName(name), content);
    }

    // Remove any unwanted extension (not double ones though)
    // Since it will later be used with the addition of ".txt"
    private String formatFileName(String fileName){
        return fileName.contains(".") 
            ? fileName.substring(0, fileName.lastIndexOf('.')) 
            : fileName;
    }
}
