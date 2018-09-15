package tech.mohammad.amir.common.parsers.impl;

import tech.mohammad.amir.common.parsers.Parser;
import tech.mohammad.amir.model.Power;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PowerParser implements Parser<Power> {
    @Override
    public Map<Integer, Power> parseList(List<String> rowList) {
        return rowList.stream().map(rowText -> {
            String[] values = rowText.split(SEPARATOR);
            return new Power(Integer.valueOf(values[0]), values[1], Integer.valueOf(values[2]));
        }).collect(Collectors.toMap(Power::getId, power -> power));
    }
}