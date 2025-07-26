package com.imagereader.service;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

/**
 * Responsible for the OCR operation, then calls
 * the output service to send the content read to
 * a respective text file.
 */
public class ImageReaderService {

    // (Tesseract class implements ITesseract interface)
    private final ITesseract tesseract = new Tesseract();
    private final ContentOutputService contentOutputService = new ContentOutputService();

    private static final Logger logger = LoggerFactory.getLogger(ImageReaderService.class);

    public ImageReaderService(){
        TesseractSetupService.setup(this.tesseract);
    }

    /**
     * Process the folder path, get the image files, and try to read them.
     * Then outputs the content.
     * @param folderPath path to the folder with the images files
     */
    public void readImagesInFolder(String folderPath) {

        File folder = new File(folderPath);
    
        File[] imageFiles = listImageFiles(folder);

        if (imageFiles == null || imageFiles.length == 0) {
            System.out.println("No image files found in " + folderPath);
            return;
        }

        for (File image : imageFiles){

            logger.info("Now processing {} ...", image.getName());

            try {
                // Calls tesseract method of OCR with the image
                String text = this.tesseract.doOCR(image);
                
                // adds the file name and content read to the class responsible to output it.
                this.contentOutputService.addFile(image.getName(), text.trim());

                logger.info("{} text extracted successfully", image.getName());

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
        return folder.listFiles((dir, name) -> {
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
