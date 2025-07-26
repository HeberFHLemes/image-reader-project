<h1 align='center'>
Image Reader Project
</h1>

### Project with Java and Tess4J to extract text from images and export it to `.txt` using OCR - Optical Character Recognition

---

<h3 align='center'>
  Usage
</h3>

1. Place the images you want to process inside the `images/` folder;
2. Run the application. 
3. The output `.txt` files will be inside the `output/` folder, with the same name as the original image.

---
<h3 align='center'>
Configuration
</h3>

#### 1. Clone this repository with:
```git
git clone https://github.com/HeberFHLemes/image-reader-project.git
```

#### 2. Open the folder in your IDE and load Maven's `pom.xml` or package the JAR with Maven:
```bash
mvn clean package
```
With `mvn package`, it will create an uber-jar inside `target/`, using the dependencies cited in this project.

#### 3. Run the application with the IDE or run:
```bash
java -jar .\target\image-reader-project-1.0-SNAPSHOT.jar
```

---

<h3 align='center'>
Language Support
</h3>

The default OCR language used in this project is **Portuguese**.

To use another language:
-  Visit [https://github.com/tesseract-ocr/tessdata](https://github.com/tesseract-ocr/tessdata)
-  Download the desired language file
-  Add the file inside the `tessdata/` folder
-  Update the code to use the new language as needed

---
<h3 align='center'>
References
</h3>

- [Tess4J](https://github.com/nguyenq/tess4j) – Java wrapper for Tesseract OCR
- [tessdata](https://github.com/tesseract-ocr/tessdata) – Official Tesseract language files
- [slf4j](https://github.com/qos-ch/slf4j) - Simple Logging Facade for Java
- [Apache Maven](https://maven.apache.org/) - Build tool for Java projects

> [!NOTE]
> Tess4J, tessdata and Apache Maven are licensed under the [Apache License 2.0](LICENSES/Apache-2.0.txt). slf4j is licensed under the [MIT License](LICENSES/MIT-slf4j.txt).
