package tech.mohammad.amir.action.impl;

import tech.mohammad.amir.action.Action;
import tech.mohammad.amir.action.ActionManager;

public class ExitGameAction implements Action {
    private ActionManager actionManager;

    public ExitGameAction(ActionManager actionManager) {
        this.actionManager = actionManager;
    }

    @Override
    public void execute() {
        actionManager.getGame().setCurrentState(actionManager.getGame().getGameStoppedState());
    }

    @Override
    public String toString() {
        return "Exit Game";
    }
}