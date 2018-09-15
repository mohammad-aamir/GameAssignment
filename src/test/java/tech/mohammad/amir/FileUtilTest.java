package tech.mohammad.amir;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import tech.mohammad.amir.action.Fight;
import tech.mohammad.amir.common.Constants;
import tech.mohammad.amir.common.exceptions.GameException;
import tech.mohammad.amir.common.exceptions.GameFileMissingException;
import tech.mohammad.amir.common.utils.FileUtils;
import tech.mohammad.amir.model.Arena;
import tech.mohammad.amir.model.Fighter;
import tech.mohammad.amir.model.GameCharacter;

import java.io.File;
import java.util.List;

public class FileUtilTest {
    @Test
    public void testReadFileValid() throws Exception {
        List<String> strings = FileUtils.readFileText(Constants.GAME_CHARACTER_CSV);
        Assert.assertNotNull(strings);
        Assert.assertEquals(5, strings.size());
    }

    @Test(expected = GameException.class)
    public void testReadFileInvalid() throws Exception {
        FileUtils.readFileText("invalidFile.csv");
    }

    @Test(expected = GameFileMissingException.class)
    public void testReadMissingObjectFromFile() throws Exception{
        File file = new File(Constants.SAVE_GAME_PATH);

        if(file.getParentFile().exists() && file.exists()) {
            file.delete();
        }

        FileUtils.readObjectFromFile(Constants.SAVE_GAME_PATH);
    }

    @Test
    public void testSaveObjectToFileAndCheckAndRead() throws Exception {
        Fighter fighter = new Fighter.FighterBuilder()
                .setName("Amir")
                .setGameCharacter(new GameCharacter(1, "Scorpian", 25))
                .setCurrentArena(new Arena(1,"Skyland", 1))
                .build();

        FileUtils.writeObjectToFile(fighter, Constants.SAVE_GAME_PATH);

        Assert.assertTrue(FileUtils.checkFileExist(Constants.SAVE_GAME_PATH));

        Fighter profileFromFile = (Fighter) FileUtils.readObjectFromFile(Constants.SAVE_GAME_PATH);

        Assert.assertNotNull(profileFromFile);
        Assert.assertEquals("Amir", profileFromFile.getName());
    }
}