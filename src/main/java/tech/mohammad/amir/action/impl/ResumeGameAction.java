package tech.mohammad.amir.action.impl;

import tech.mohammad.amir.action.Action;
import tech.mohammad.amir.action.ActionManager;

public class ResumeGameAction implements Action {
    private ActionManager actionManager;
    public ResumeGameAction(ActionManager actionManager) {
        this.actionManager = actionManager;
    }

    @Override
    public void execute() {
        actionManager.loadSavedProfile();
        actionManager.getGame().setCurrentState(actionManager.getGame().getFightStoppedState());
    }

    @Override
    public String toString() {
        return "Resume Game";
    }
}