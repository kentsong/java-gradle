package io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

/**
 * 從項目中 resouces 目錄獲取檔案讀寫
 * https://mkyong.com/java/java-read-a-file-from-resources-folder/
 */
public class Application {

    public static void main(String[] args) throws IOException {

        Application main = new Application();
        File file = main.getFileFromResources("playinfo.xml");

        printFile(file);
    }

    // get file from classpath, resources folder
    public File getFileFromResources(String fileName) {

        ClassLoader classLoader = getClass().getClassLoader();

        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("file is not found!");
        } else {
            System.out.println("URL="+resource.getFile());
            return new File(resource.getFile());
        }

    }

    public String getFileFromResourcesPath(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("file is not found!");
        } else {
            return resource.getFile();
        }
    }

    public static void printFile(File file) throws IOException {

        if (file == null) return;

        try (FileReader reader = new FileReader(file);
             BufferedReader br = new BufferedReader(reader)) {

            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        }
    }
}
