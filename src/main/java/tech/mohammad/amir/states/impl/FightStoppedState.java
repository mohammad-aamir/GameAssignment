package tech.mohammad.amir.states.impl;

import tech.mohammad.amir.action.ActionManager;
import tech.mohammad.amir.states.GameState;

public class FightStoppedState implements GameState {
    private ActionManager actionManager;

    public FightStoppedState(ActionManager actionManager) {
        this.actionManager = actionManager;
    }

    @Override
    public void start() {
        this.actionManager.populateFighterActions();
    }
}