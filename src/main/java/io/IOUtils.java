package io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class IOUtils {

    public static void main(String[] args) {
        Application app = new Application();
        String filePath = app.getFileFromResourcesPath("playinfo.xml");
        String msg = readFromFile(filePath);
        System.out.println(msg);
    }

    public static String readFromFile(String filePath) {
        File inFile = new File(filePath);
        if (!inFile.exists()) {
            return null;
        } else {
            StringBuilder sb = new StringBuilder();
            BufferedReader bReader = null;
            FileReader fileReader = null;
            try {
                fileReader = new FileReader(inFile);
                bReader = new BufferedReader(fileReader);
                String e;
                while ((e = bReader.readLine()) != null) {
                    sb.append(e);
                }
            } catch (IOException var14) {
                var14.printStackTrace();
            } finally {
                try {
                    if (null != bReader) {
                        bReader.close();
                    }
                    if (null != fileReader) {
                        fileReader.close();
                    }
                } catch (Exception var13) {
                    var13.printStackTrace();
                }

            }
            return sb.toString();
        }
    }
}
