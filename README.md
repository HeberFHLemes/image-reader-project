# Image Reader Project

### Java + Tess4J

### Extract text from images and export it to `.txt`
OCR - Optical Character Recognition

---

### Usage: 
1. Place the images you want to process inside the `images/` folder;
2. Run the application. 
3. The output `.txt` files will be inside the `output/` folder, with the same name as the original image.

---

### Language Support

The default OCR language used in this project is **Portuguese**.

To use another language:
-  Visit [https://github.com/tesseract-ocr/tessdata](https://github.com/tesseract-ocr/tessdata)
-  Download the desired language file
-  Add the file inside the `tessdata/` folder
-  Update the code to use the new language as needed

---

### References
- [Tess4J](https://github.com/nguyenq/tess4j) – Java wrapper for Tesseract OCR
- [tessdata](https://github.com/tesseract-ocr/tessdata) – Official Tesseract language files
- [slf4j](https://github.com/qos-ch/slf4j) - Simple Logging Facade for Java

> Both Tess4J and tessdata are licensed under the [Apache License 2.0](LICENSES/Apache-2.0.txt)
> 
> slf4j is licensed under the [MIT License](LICENSES/MIT-slf4j.txt)
