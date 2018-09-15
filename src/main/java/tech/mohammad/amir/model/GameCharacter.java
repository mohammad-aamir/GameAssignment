package tech.mohammad.amir.model;

import java.io.Serializable;

/**
 * Created by Mohammad.Amir on 6/27/2018.
 */
public class GameCharacter implements Serializable {
    private int id;
    private String name;
    private int powerId;
    private Power power;

    public GameCharacter(int id, String name, int powerId) {
        this.id = id;
        this.name = name;
        this.powerId = powerId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPowerId() {
        return powerId;
    }

    public Power getPower() {
        return power;
    }

    public void setPower(Power power) {
        this.power = power;
    }

    @Override
    public String toString() {
        return name + "(Default Power:" + power +')';
    }
}