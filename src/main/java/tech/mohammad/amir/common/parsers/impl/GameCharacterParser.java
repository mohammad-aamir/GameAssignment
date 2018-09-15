package tech.mohammad.amir.common.parsers.impl;

import tech.mohammad.amir.common.parsers.Parser;
import tech.mohammad.amir.model.GameCharacter;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GameCharacterParser implements Parser<GameCharacter> {
    @Override
    public Map<Integer, GameCharacter> parseList(List<String> rowList) {
        return rowList.stream().map(rowText -> {
            String[] values = rowText.split(SEPARATOR);
            return new GameCharacter(Integer.valueOf(values[0]), values[1], Integer.valueOf(values[2]));
        }).collect(Collectors.toMap(GameCharacter::getId, gameCharacter -> gameCharacter));
    }
}
