package tech.mohammad.amir.action.impl;

import tech.mohammad.amir.action.Action;
import tech.mohammad.amir.action.ActionManager;

public class ExitToMainMenuAction implements Action {
    private ActionManager actionManager;

    public ExitToMainMenuAction(ActionManager actionManager) {
        this.actionManager = actionManager;
    }

    @Override
    public void execute() {
        actionManager.saveFighterProgress();
        actionManager.getGame().setCurrentState(actionManager.getGame().getGameStartState());
    }

    @Override
    public String toString() {
        return "Exit To Main Menu";
    }
}