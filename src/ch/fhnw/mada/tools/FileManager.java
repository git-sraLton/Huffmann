package ch.fhnw.mada.tools;

import java.io.*;
import java.util.Scanner;

public class FileManager {
    public static void writeFile(String filePath, String content) {
        try {
            FileWriter writer = new FileWriter(filePath);
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String readFile(String filePath) {
        StringBuilder fileString = new StringBuilder();
        File file = new File(filePath);
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                fileString.append(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return fileString.toString();
    }

    public static void writeByteArray(String filePath, byte[] byteArray) {
        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            fos.write(byteArray);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static byte[] readByteArray(String filePath) {
        File file = new File(filePath);
        byte[] bFile = new byte[(int) file.length()];
        try (FileInputStream fis = new FileInputStream(file)) {
            fis.read(bFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return bFile;
    }
}
