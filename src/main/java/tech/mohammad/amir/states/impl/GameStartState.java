package tech.mohammad.amir.states.impl;

import tech.mohammad.amir.action.ActionManager;
import tech.mohammad.amir.states.GameState;

public class GameStartState implements GameState {
    private ActionManager actionManager;
    public GameStartState(ActionManager actionManager) {
        this.actionManager = actionManager;
    }

    @Override
    public void start() {
        actionManager.populateGameStartActions();
    }
}