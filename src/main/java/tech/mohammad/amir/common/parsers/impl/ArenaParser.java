package tech.mohammad.amir.common.parsers.impl;

import tech.mohammad.amir.common.parsers.Parser;
import tech.mohammad.amir.model.Arena;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ArenaParser implements Parser<Arena> {
    @Override
    public Map<Integer, Arena> parseList(List<String> rowList) {
        return rowList.stream().map(rowText -> {
            String[] values = rowText.split(SEPARATOR);
            return new Arena(Integer.valueOf(values[0]), values[1], Integer.valueOf(values[2]));
        }).collect(Collectors.toMap(Arena::getId, arena -> arena));
    }
}