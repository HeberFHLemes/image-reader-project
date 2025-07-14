package com.imagereader.service;

import java.io.File;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class ImageReaderService {

    // (Tesseract class implements ITesseract interface)
    private ITesseract tesseract;

    public ImageReaderService(){
        this.tesseract = new Tesseract();
        TesseractSetupService.setup(this.tesseract);

    }

    // Process the folder path, get the image files, and try to read them.
    // Then output the content
    public void readImagesInFolder(String folderPath) {

        File folder = new File(folderPath);
    
        File[] imageFiles = listImageFiles(folder);

        if (imageFiles == null || imageFiles.length == 0) {
            System.out.println("No image files found in " + folderPath);
            return;
        }

        for (File image : imageFiles){
            try {

                System.out.println("Now processing " + image.getName() + " ...");
                
                // Calls tesseract method of OCR with the image
                String text = this.tesseract.doOCR(image);
                
                // Outputs the processed text (will be converted to external file (.txt?))
                System.out.println("Extracted text:\n" + text);

            } catch (TesseractException te){
                System.err.println("Error reading " + image.getName() + ": " + te.getMessage());
            }
        }
    }

    // List files in the selected folder and puts them into an array
    private File[] listImageFiles(File folder){
        return folder.listFiles((_, name) -> {
            String lower = name.toLowerCase();
            // Filtering .png, .jpg, .jpeg and .tif files
            return lower.endsWith(".png") || lower.endsWith(".jpg") || lower.endsWith(".jpeg") || lower.endsWith(".tif");
        });
    }
}
