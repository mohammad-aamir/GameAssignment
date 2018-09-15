package tech.mohammad.amir.common;

import java.io.File;

public class Constants {
    public static final int DEFAULT_FIGHTER_BLOOD_LEVEL = 100;
    public static final String SAVE_GAME_PATH = System.getProperty("user.home")+ File.separator+ "FighterGame"+ File.separator+ "savedData.sfg";
    public static final String POWERS_CSV = "powers.csv";
    public static final String GAME_CHARACTER_CSV = "game_characters.csv";
    public static final String ARENA_CSV = "arena.csv";
    public static final String LINE = "=====================================================================================================================================================";
}

