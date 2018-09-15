package tech.mohammad.amir;

import org.junit.Assert;
import org.junit.Test;
import tech.mohammad.amir.common.parsers.Parser;
import tech.mohammad.amir.common.parsers.impl.*;
import tech.mohammad.amir.model.Arena;
import tech.mohammad.amir.model.GameCharacter;
import tech.mohammad.amir.model.Power;

import java.util.Arrays;
import java.util.Map;

public class ParsingTest {
    @Test
    public void testParsePower() {
        Parser<Power> powerParser = new PowerParser();
        Map<Integer, Power> powerMap = powerParser.parseList(Arrays.asList("1,Power1,10", "2,Power2,20"));
        Assert.assertNotNull(powerMap);
        Assert.assertEquals(2, powerMap.entrySet().size());
        Assert.assertEquals("Power1", powerMap.get(1).getName());
        Assert.assertEquals("Power2", powerMap.get(2).getName());
        Assert.assertNull(powerMap.get(3));
    }

    @Test
    public void testParseGameCharacter() {
        Parser<GameCharacter> gameCharacterParser = new GameCharacterParser();
        Map<Integer, GameCharacter> gameCharacterMap = gameCharacterParser.parseList(Arrays.asList("1,GChar1,1", "2,GChar2,2"));
        Assert.assertNotNull(gameCharacterMap);
        Assert.assertEquals(2, gameCharacterMap.entrySet().size());
        Assert.assertEquals("GChar1", gameCharacterMap.get(1).getName());
        Assert.assertEquals("GChar2", gameCharacterMap.get(2).getName());
        Assert.assertNull(gameCharacterMap.get(3));
    }

    @Test
    public void testParseArena() {
        Parser<Arena> arenaParser = new ArenaParser();
        Map<Integer, Arena> arenaMap = arenaParser.parseList(Arrays.asList("1,Arena1,10", "2,Arena2,20"));
        Assert.assertNotNull(arenaMap);
        Assert.assertEquals(2, arenaMap.entrySet().size());
        Assert.assertEquals("Arena1", arenaMap.get(1).getName());
        Assert.assertEquals("Arena2", arenaMap.get(2).getName());
        Assert.assertNull(arenaMap.get(3));
    }
}