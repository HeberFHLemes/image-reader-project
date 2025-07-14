package service;

import net.sourceforge.tess4j.Tesseract;

public class TessSetupService {
    public static void setup(Tesseract tesseract){
        tesseract.setDatapath("tesseract");
        tesseract.setLanguage("por");
    }
}
