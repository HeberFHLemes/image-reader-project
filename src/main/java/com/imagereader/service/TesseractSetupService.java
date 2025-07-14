package com.imagereader.service;

import net.sourceforge.tess4j.ITesseract;

public class TesseractSetupService {
    
    public static void setup(ITesseract tesseract){
        tesseract.setDatapath("tessdata");
        tesseract.setLanguage("por");
    }

    // tessdata/ only has portuguese for now
    public static void setup(ITesseract tesseract, String datapath, String language){
        tesseract.setDatapath(datapath);
        tesseract.setLanguage(language);
    }
}
