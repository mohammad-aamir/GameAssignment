package tech.mohammad.amir.io.impl;

import tech.mohammad.amir.io.Writer;

/**
 * Created by Mohammad.Amir on 6/27/2018.
 */
public class ConsoleWriter implements Writer {
    private static Writer writer;
    private ConsoleWriter() {
    }

    public static Writer getInstance() {
        if(writer == null) {
            writer = new ConsoleWriter();
        }

        return writer;
    }

    public void write(String value) {
        System.out.println(value);
    }
}