package tech.mohammad.amir.action;

import tech.mohammad.amir.common.exceptions.InputException;
import tech.mohammad.amir.io.Reader;
import tech.mohammad.amir.io.Writer;
import tech.mohammad.amir.io.impl.ConsoleWriter;
import tech.mohammad.amir.io.impl.UserInputReader;
import tech.mohammad.amir.model.Fighter;
import tech.mohammad.amir.states.GameState;
import tech.mohammad.amir.states.impl.FightStartState;
import tech.mohammad.amir.states.impl.FightStoppedState;
import tech.mohammad.amir.states.impl.GameStartState;
import tech.mohammad.amir.states.impl.GameStoppedState;

public class Game {
    private Writer writer = ConsoleWriter.getInstance();
    private Reader reader = UserInputReader.getInstance();
    private ActionManager actionManager;
    private GameState gameStartState;
    private GameState fightStartState;
    private GameState fightStoppedState;
    private GameState gameStoppedState;
    private GameState currentState;
    private Fighter fighter;

    public Game() {
        this.actionManager = new ActionManager(this);

        this.gameStartState = new GameStartState(actionManager);
        this.fightStartState = new FightStartState(actionManager);
        this.fightStoppedState = new FightStoppedState(actionManager);
        this.gameStoppedState = new GameStoppedState(actionManager);

        this.currentState = gameStartState;
    }

    public void run() {
        while(currentState != gameStoppedState) {
            currentState.start();
            actionManager.showAvailableAction();

            try {
                int action = reader.readNumber();
                actionManager.doAction(action);
            } catch (InputException ie) {
                writer.write(ie.getMessage());
            }
        }
    }

    public void setCurrentState(GameState currentState) {
        this.currentState = currentState;
    }

    public void setFighter(Fighter fighter) {
        this.fighter = fighter;
    }

    public Fighter getFighter() {
        return fighter;
    }

    public GameState getGameStartState() {
        return gameStartState;
    }

    public GameState getFightStartState() {
        return fightStartState;
    }

    public GameState getFightStoppedState() {
        return fightStoppedState;
    }

    public GameState getGameStoppedState() {
        return gameStoppedState;
    }

    public void stopFight() {
        currentState = fightStoppedState;
        currentState.start();
    }
}