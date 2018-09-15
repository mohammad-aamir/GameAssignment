package tech.mohammad.amir.action;

import tech.mohammad.amir.model.Fighter;

public class Fight {
    private Fighter fighter;
    private ActionManager actionManager;

    public Fight(Fighter fighter, ActionManager actionManager) {
        this.fighter = fighter;
        this.actionManager = actionManager;
    }

    public void start() {
        actionManager.startFight();;
    }
}
