package tech.mohammad.amir.model;

import java.io.Serializable;

/**
 * Created by Mohammad.Amir on 6/27/2018.
 */
public class Arena implements Serializable {
    private int id;
    private String name;
    private int opponentId;
    private GameCharacter opponent;

    public Arena(int id, String name, int opponentId) {
        this.id = id;
        this.name = name;
        this.opponentId = opponentId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getOpponentId() {
        return opponentId;
    }

    public GameCharacter getOpponent() {
        return opponent;
    }

    public void setOpponent(GameCharacter opponent) {
        this.opponent = opponent;
    }
}