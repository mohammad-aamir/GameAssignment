package tech.mohammad.amir.action.impl;

import tech.mohammad.amir.action.Action;
import tech.mohammad.amir.action.ActionManager;

public class StartFightAction implements Action {
    private ActionManager actionManager;

    public StartFightAction(ActionManager actionManager) {
        this.actionManager = actionManager;
    }

    @Override
    public void execute() {
        actionManager.getGame().setCurrentState(actionManager.getGame().getFightStartState());
    }

    @Override
    public String toString() {
        return "Start Fight";
    }
}