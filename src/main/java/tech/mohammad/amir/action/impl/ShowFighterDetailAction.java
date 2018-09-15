package tech.mohammad.amir.action.impl;

import tech.mohammad.amir.action.Action;
import tech.mohammad.amir.action.ActionManager;

public class ShowFighterDetailAction implements Action {
    private ActionManager actionManager;

    public ShowFighterDetailAction(ActionManager actionManager) {
        this.actionManager = actionManager;
    }

    @Override
    public void execute() {
        actionManager.displayFighterDetails();
    }

    @Override
    public String toString() {
        return "Show Fighter Profile";
    }
}