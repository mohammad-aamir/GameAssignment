package tech.mohammad.amir.action;

import tech.mohammad.amir.common.Constants;
import tech.mohammad.amir.common.exceptions.GameException;
import tech.mohammad.amir.common.parsers.impl.*;
import tech.mohammad.amir.common.utils.FileUtils;
import tech.mohammad.amir.common.parsers.Parser;

import tech.mohammad.amir.io.Writer;
import tech.mohammad.amir.io.impl.ConsoleWriter;
import tech.mohammad.amir.model.Arena;
import tech.mohammad.amir.model.GameCharacter;
import tech.mohammad.amir.model.Power;

import java.util.Map;

public class MetadataLookup {
    private static MetadataLookup metadataLookup;
    private Writer writer = ConsoleWriter.getInstance();
    private Map<Integer, Power> powerMap;
    private Map<Integer, GameCharacter> gameCharacterMap;
    private Map<Integer, Arena> arenaMap;

    private MetadataLookup() {
        loadPowers();
        loadGameCharacters();
        loadArena();
    }

    public static MetadataLookup getInstance() {
        if(metadataLookup == null) {
            metadataLookup = new MetadataLookup();
        }

        return metadataLookup;
    }

    public Map<Integer, Power> getPowerMap() {
        return powerMap;
    }

    public Map<Integer, GameCharacter> getGameCharacterMap() {
        return gameCharacterMap;
    }

    public Map<Integer, Arena> getArenaMap() {
        return arenaMap;
    }

    public Power getPower(int id) {
        return powerMap.get(id);
    }

    public GameCharacter getGameCharacter(int id) {
        return gameCharacterMap.get(id);
    }

    public Arena getArena(int id) {
        return arenaMap.get(id);
    }

    private void loadArena() {
        try {
            Parser<Arena> arenaParser = new ArenaParser();
            arenaMap = arenaParser.parseList(FileUtils.readFileText(Constants.ARENA_CSV));
        } catch (GameException e) {
            writer.write(e.getMessage());
        }
    }

    private void loadPowers() {
        try {
            Parser<Power> powerIParser = new PowerParser();
            powerMap = powerIParser.parseList(FileUtils.readFileText(Constants.POWERS_CSV));
        } catch (GameException e) {
            writer.write(e.getMessage());
        }
    }

    private void loadGameCharacters() {
        try {
            Parser<GameCharacter> gameCharacterIParser = new GameCharacterParser();
            gameCharacterMap = gameCharacterIParser.parseList(FileUtils.readFileText(Constants.GAME_CHARACTER_CSV));
        } catch (GameException e) {
            writer.write(e.getMessage());
        }
    }
}