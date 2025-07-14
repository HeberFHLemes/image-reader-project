package com.imagereader;

import com.imagereader.service.ImageReaderService;

public class Main {
    public static void main(String[] args) {
        ImageReaderService imageReaderService = new ImageReaderService();
        imageReaderService.readImagesInFolder("imgreader/images");
    }
}