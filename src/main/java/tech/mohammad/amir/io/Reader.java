package tech.mohammad.amir.io;

import tech.mohammad.amir.common.exceptions.InputException;

public interface Reader {
    int readNumber() throws InputException;
    String readString();
}