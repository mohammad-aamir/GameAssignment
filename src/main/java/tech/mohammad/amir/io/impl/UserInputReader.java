package tech.mohammad.amir.io.impl;

import tech.mohammad.amir.common.exceptions.InputException;
import tech.mohammad.amir.io.Reader;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UserInputReader implements Reader {
    private static Reader reader;
    private static Scanner scanner;

    private UserInputReader(){
        scanner = new Scanner(System.in);
    }

    public static Reader getInstance() {
        if(reader == null) {
            reader = new UserInputReader();
        }

        return reader;
    }

    @Override
    public int readNumber() throws InputException{
        try {
            return scanner.nextInt();
        } catch (InputMismatchException ime) {
            scanner.nextLine();//to consume extra new line character
            throw new InputException("Invalid input.");
        }
    }

    @Override
    public String readString() {
        scanner.nextLine();//to consume extra new line character
        return scanner.nextLine();
    }
}