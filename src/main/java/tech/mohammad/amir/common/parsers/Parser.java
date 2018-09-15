package tech.mohammad.amir.common.parsers;

import java.util.List;
import java.util.Map;

@FunctionalInterface
public interface Parser<T> {
    String SEPARATOR = ",";
    Map<Integer, T> parseList(List<String> rowList);
}