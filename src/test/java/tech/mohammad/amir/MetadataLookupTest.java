package tech.mohammad.amir;

import org.junit.Assert;
import org.junit.Test;
import tech.mohammad.amir.action.MetadataLookup;
import tech.mohammad.amir.model.Arena;
import tech.mohammad.amir.model.GameCharacter;
import tech.mohammad.amir.model.Power;

import java.util.Map;

public class MetadataLookupTest {
    MetadataLookup metadataLookup = MetadataLookup.getInstance();

    @Test
    public void testPowerMap() {
        Map<Integer, Power> powerMap = metadataLookup.getPowerMap();
        Assert.assertNotNull(powerMap);
        Assert.assertEquals(5, powerMap.entrySet().size());
    }

    @Test
    public void testPowerLookup() {
        Power power = metadataLookup.getPower(3);
        Assert.assertNotNull(power);
        Assert.assertEquals(3, power.getId());
        Assert.assertEquals("Thunder", power.getName());
    }

    @Test
    public void testGameCharacterMap() {
        Map<Integer, GameCharacter> gameCharacterMap = metadataLookup.getGameCharacterMap();
        Assert.assertNotNull(gameCharacterMap);
        Assert.assertEquals(5, gameCharacterMap.entrySet().size());
    }

    @Test
    public void testGameCharacterLookup() {
        GameCharacter gameCharacter = metadataLookup.getGameCharacter(5);
        Assert.assertNotNull(gameCharacter);
        Assert.assertEquals(5, gameCharacter.getId());
        Assert.assertEquals("Smoke", gameCharacter.getName());
    }

    @Test
    public void testArenaMap() {
        Map<Integer, Arena> arenaMap = metadataLookup.getArenaMap();
        Assert.assertNotNull(arenaMap);
        Assert.assertEquals(5, arenaMap.entrySet().size());
    }

    @Test
    public void testArenaLookup() {
        Arena arena = metadataLookup.getArena(1);
        Assert.assertNotNull(arena);
        Assert.assertEquals(1, arena.getId());
        Assert.assertEquals("Iceland", arena.getName());
    }
}