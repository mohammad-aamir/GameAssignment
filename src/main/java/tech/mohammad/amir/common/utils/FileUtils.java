package tech.mohammad.amir.common.utils;

import tech.mohammad.amir.common.exceptions.GameException;
import tech.mohammad.amir.common.exceptions.GameFileMissingException;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Mohammad.Amir on 6/28/2018.
 */
public class FileUtils {
    public static List<String> readFileText(String fileName) throws GameException {
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(FileUtils.class.getClassLoader().getResourceAsStream(fileName)));
            return bufferedReader.lines().skip(1).collect(Collectors.toList());
        } catch (Exception e) {
            throw new GameException("Error reading csv");
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (IOException e) {
            }
        }
    }

    public static boolean checkFileExist(String filePath) {
        return new File(filePath).exists();
    }

    public static Object readObjectFromFile(String filePath) throws GameFileMissingException {
        ObjectInputStream objectInputStream = null;

        try {
            objectInputStream = new ObjectInputStream(new FileInputStream(filePath));
            return objectInputStream.readObject();
        } catch (Exception e) {
            throw new GameFileMissingException("Error while loading file");
        } finally {
            try {
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
            } catch (IOException e) {}
        }
    }

    public static void writeObjectToFile(Object object, String filePath) throws GameException {
        ObjectOutputStream objectOutputStream = null;

        try{
            File file = new File(filePath);
            if(!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }

            objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
            objectOutputStream.writeObject(object);
        } catch (Exception ex) {
            throw new GameException("Error while saving game");
        } finally {
            try {
                if (objectOutputStream != null) {
                    objectOutputStream.close();
                }
            } catch (IOException e) {
            }
        }
    }
}