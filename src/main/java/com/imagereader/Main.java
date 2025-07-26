package com.imagereader;

import com.imagereader.service.ImageReaderService;

public class Main {
    public static void main(String[] args) {
        ImageReaderService imageReaderService = new ImageReaderService();
        imageReaderService.readImagesInFolder("images");

        // Use env variables if wanted
        //imageReaderService.readImagesInFolder(System.getenv("IMAGES_FOLDER"));
    }
}