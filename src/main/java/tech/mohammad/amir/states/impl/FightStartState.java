package tech.mohammad.amir.states.impl;

import tech.mohammad.amir.action.ActionManager;
import tech.mohammad.amir.states.GameState;

public class FightStartState implements GameState {
    private ActionManager actionManager;

    public FightStartState(ActionManager actionManager) {
        this.actionManager = actionManager;
    }

    @Override
    public void start() {
        actionManager.startFight();
    }
}