package tech.mohammad.amir.action.impl;

import tech.mohammad.amir.action.Action;
import tech.mohammad.amir.action.ActionManager;

public class NewGameAction implements Action {
    private ActionManager actionManager;

    public NewGameAction(ActionManager actionManager) {
        this.actionManager = actionManager;
    }

    @Override
    public void execute() {
        actionManager.initiateGameWithNewProfile();
        actionManager.getGame().setCurrentState(actionManager.getGame().getFightStoppedState());
    }

    @Override
    public String toString() {
        return "New Game";
    }
}