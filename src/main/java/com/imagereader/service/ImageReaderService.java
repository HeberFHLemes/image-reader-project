package com.imagereader.service;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class ImageReaderService {

    // (Tesseract class implements ITesseract interface)
    private ITesseract tesseract;
    private ContentOutputService contentOutputService;

    private static final Logger logger = LoggerFactory.getLogger(ImageReaderService.class);

    public ImageReaderService(){
        this.tesseract = new Tesseract();
        TesseractSetupService.setup(this.tesseract);
        this.contentOutputService = new ContentOutputService();
    }

    /**
     * Process the folder path, get the image files, and try to read them.
     * Then outputs the content.
     * @param folderPath
     */
    public void readImagesInFolder(String folderPath) {

        File folder = new File(folderPath);
    
        File[] imageFiles = listImageFiles(folder);

        if (imageFiles == null || imageFiles.length == 0) {
            System.out.println("No image files found in " + folderPath);
            return;
        }

        for (File image : imageFiles){
            
            logger.info("Now processing " + image.getName() + " ...");

            try {
                // Calls tesseract method of OCR with the image
                String text = this.tesseract.doOCR(image);
                
                // adds the file name and content read to the class responsible to output it.
                this.contentOutputService.addFile(image.getName(), text.trim());
                
                logger.info(image.getName() + " text extracted successfully");

            } catch (TesseractException te){
                logger.error(te.getMessage(), te);
            }
        }
        outputFiles();
        
        logger.info("All operations finished.");
    }

    /**
     * List files in the selected folder and puts them into an array
     * @param folder the folder to have its files listed
     * @return an array of files that ends with either ".png". "jpg", ".jpeg" or ".tif"
     */
    private File[] listImageFiles(File folder){
        return folder.listFiles((_, name) -> {
            String lower = name.toLowerCase();
            // Filtering .png, .jpg, .jpeg and .tif files
            return lower.endsWith(".png") || lower.endsWith(".jpg") || lower.endsWith(".jpeg") || lower.endsWith(".tif");
        });
    }

    /**
     * Calls the {@code output()} method from {@code ContentOutputService}.
     */
    private void outputFiles(){
        try {
            this.contentOutputService.output();
        } catch (IOException ioe){
            logger.error(ioe.getMessage(), ioe);
        }
    }
}
