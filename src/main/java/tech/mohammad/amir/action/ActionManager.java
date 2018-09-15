package tech.mohammad.amir.action;

import tech.mohammad.amir.action.impl.*;
import tech.mohammad.amir.common.Constants;
import tech.mohammad.amir.common.exceptions.GameException;
import tech.mohammad.amir.common.exceptions.GameFileMissingException;
import tech.mohammad.amir.common.exceptions.InputException;
import tech.mohammad.amir.common.utils.FileUtils;
import tech.mohammad.amir.io.Reader;
import tech.mohammad.amir.io.Writer;
import tech.mohammad.amir.io.impl.ConsoleWriter;
import tech.mohammad.amir.io.impl.UserInputReader;
import tech.mohammad.amir.model.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ActionManager {
    private MetadataLookup metadataLookup = MetadataLookup.getInstance();
    private Writer writer = ConsoleWriter.getInstance();
    private Reader reader = UserInputReader.getInstance();
    private Game game;
    private List<Action> actions;

    public ActionManager(Game game) {
        this.game = game;
        actions = new ArrayList<Action>();
    }

    public void initiateGameWithNewProfile() {
        GameCharacter gameCharacter = null;

        while (gameCharacter == null) {
            try {
                writer.write("Select A Game Character:");
                metadataLookup.getGameCharacterMap().values().forEach(gameCharacterObj -> {
                    writer.write(String.format("%d. %s", gameCharacterObj.getId(), gameCharacterObj.getName()));
                });

                gameCharacter = metadataLookup.getGameCharacter(reader.readNumber());
                if(gameCharacter == null) {
                    writer.write("Invalid input. Please try again");
                }
            } catch (InputException e) {
                writer.write(e.getMessage());
            }
        }

        gameCharacter.setPower(metadataLookup.getPower(gameCharacter.getPowerId()));
        writer.write("Enter your name: ");

        String name = reader.readString();
        writer.write(name);
        writer.write("Profile Completed.");

        Arena currentArena = metadataLookup.getArenaMap().values().iterator().next();
        currentArena.setOpponent(metadataLookup.getGameCharacter(currentArena.getOpponentId()));

        Fighter fighter = new Fighter.FighterBuilder()
                .setName(name)
                .setGameCharacter(gameCharacter)
                .setCurrentArena(currentArena)
                .build();
        game.setFighter(fighter);
    }

    private boolean savedProfileExist() {
        return new File(Constants.SAVE_GAME_PATH).exists();
    }

    public void loadSavedProfile() {
        try {
            game.setFighter((Fighter) FileUtils.readObjectFromFile(Constants.SAVE_GAME_PATH));
            writer.write("Fighter profile loaded");
        } catch (GameFileMissingException e) {
            writer.write(e.getMessage());
            initiateGameWithNewProfile();
        }
    }

    public void saveFighterProgress() {
        try {
            FileUtils.writeObjectToFile(game.getFighter(), Constants.SAVE_GAME_PATH);
        } catch (GameException e) {
            writer.write(e.getMessage());
        }
    }

    public void showAvailableAction() {
        writer.write(Constants.LINE+"\nSelect an action\n"+Constants.LINE);

        for(int i=0; i<actions.size() ; i++) {
            writer.write(String.format("%d. %s",i+1, actions.get(i)));
        }
    }

    public void populateGameStartActions() {
        actions.clear();
        actions.add(new NewGameAction(this));
        if(savedProfileExist()) {
            actions.add(new ResumeGameAction(this));
        }
        actions.add(new ExitGameAction(this));
    }

    public void doAction(int action) {
        if(actions.size() >= action) {
            actions.get(action -1).execute();
        } else {
            writer.write("Invalid Action");
        }
    }

    public Game getGame() {
        return game;
    }

    public void startFight() {
        Fighter fighter = game.getFighter();
        Arena currentArena = fighter.getCurrentArena();
        currentArena.getOpponent().setPower(metadataLookup.getPower(currentArena.getOpponent().getPowerId()));

        Fighter opponent = new Fighter.FighterBuilder()
                .setName(currentArena.getOpponent().getName())
                .setGameCharacter(currentArena.getOpponent())
                .setCurrentArena(currentArena)
                .build();

        writer.write(String.format("Fight Started in %s",currentArena.getName()));

        boolean fightRunning = true;
        boolean fightRoundRunning = true;

        while(fightRoundRunning) {
            writer.write(fighter.getName()
                    + "(Blood Level=" +fighter.getBloodLevel()+ ") vs "
                    + opponent.getName()
                    + "(Blood Level=" +opponent.getBloodLevel()+")");

            int i=1;
            writer.write(Constants.LINE+"\nSelect an action\n"+Constants.LINE);

            for(Power power : fighter.getPowers()) {
                writer.write(String.format("%d. Attack with  %s", i++, power.getName()));
            }

            writer.write("0. Quit Fight");

            int action = 0;
            try {
                action = reader.readNumber();

                if (action == 0) {
                    fightRunning = false;
                    fightRoundRunning = false;
                    game.stopFight();
                    saveFighterProgress();
                } else if (fighter.getPowers().size() >= action) {
                    Power powerUsed = (Power) fighter.getPowers().toArray()[action - 1];
                    opponent.setBloodLevel(opponent.getBloodLevel() - powerUsed.getDamageLevel());
                    writer.write(String.format("%s attacked %s with %s", fighter.getName(), opponent.getName(), powerUsed.getName())
                    );
                } else {
                    writer.write("Invalid Action");
                }

                if (opponent.getBloodLevel() <= 0) {
                    writer.write(String.format("%s won fight in %s", fighter.getName(), currentArena.getName()));
                    fighter.getPowers().add(opponent.getPowers().iterator().next());
                    fightRoundRunning = false;
                }
            } catch (InputException e) {
                writer.write(e.getMessage());
            }
        }

        if(fightRunning) {
            int currentArenaId = fighter.getCurrentArena().getId();

            Arena nextArena = metadataLookup.getArena(currentArenaId + 1);
            if (null != nextArena) {
                nextArena.setOpponent(metadataLookup.getGameCharacter(nextArena.getOpponentId()));
                fighter.setCurrentArena(nextArena);
                startFight();
            } else {
                game.stopFight();
                saveFighterProgress();
                writer.write(String.format("%s won all fights", fighter.getName()));
            }
        }
    }

    public void populateFighterActions() {
        actions.clear();
        actions.add(new StartFightAction(this));
        actions.add(new ShowFighterDetailAction(this));
        actions.add(new ExitToMainMenuAction(this));
    }

    public void displayFighterDetails() {
        writer.write(Constants.LINE +"\nFighter Profile\n" + Constants.LINE);
        writer.write(game.getFighter().toString());
    }
}