package com.imagereader.service;

import net.sourceforge.tess4j.ITesseract;

public abstract class TesseractSetupService {
    
    /**
     * Sets up the Tesseract object.
     * 
     * <p>This is default method, using Portuguese as the language.</p>
     * <p>The datapath refers to the directory containing the language files 
     * (default: {@code "tessdata"})</p>
     * 
     * @param tesseract the Tesseract object to be configured
     */
    public static void setup(ITesseract tesseract){
        tesseract.setDatapath("tessdata");
        tesseract.setLanguage("por");
        
        // Define the dpi value as 300
        tesseract.setVariable("user_defined_dpi", "300");
    }

    /**
     * Sets up the Tesseract object, with a specific datapath and language.
     * 
     * @param tesseract the Tesseract object to be configured
     * @param datapath the path to the folder containing the selected language files 
     * @param language the Tesseract language code to use (e.g., "por" for Portuguese)
     */
    public static void setup(ITesseract tesseract, String datapath, String language){
        tesseract.setDatapath(datapath);
        tesseract.setLanguage(language);

        // Because it was reading the dpi of the images that are being treated as "1"
        tesseract.setVariable("user_defined_dpi", "300");
    }
}
