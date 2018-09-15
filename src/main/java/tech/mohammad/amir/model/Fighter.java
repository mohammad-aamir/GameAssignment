package tech.mohammad.amir.model;

import tech.mohammad.amir.common.Constants;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

public class Fighter implements Serializable {
    private String name;
    private GameCharacter gameCharacter;
    private int bloodLevel;
    private Arena currentArena;
    private Set<Power> powers;

    private Fighter(String name, GameCharacter gameCharacter, Arena currentArena) {
        this.name = name;
        this.gameCharacter = gameCharacter;
        this.currentArena = currentArena;
        this.bloodLevel = Constants.DEFAULT_FIGHTER_BLOOD_LEVEL;
        this.powers = new LinkedHashSet<Power>();
        this.powers.add(gameCharacter.getPower());
    }

    public String getName() {
        return name;
    }

    public int getBloodLevel() {
        return bloodLevel;
    }

    public void setBloodLevel(int bloodLevel) {
        this.bloodLevel = bloodLevel;
    }

    public GameCharacter getGameCharacter() {
        return gameCharacter;
    }

    public Arena getCurrentArena() {
        return currentArena;
    }

    public void setCurrentArena(Arena currentArena) {
        this.currentArena = currentArena;
    }

    public Set<Power> getPowers() {
        return powers;
    }

    public void addPower(Power power) {
        this.powers.add(power);
    }

    public static class FighterBuilder {
        private String name;
        private GameCharacter gameCharacter;
        private Arena currentArena;

        public FighterBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public FighterBuilder setGameCharacter(GameCharacter gameCharacter) {
            this.gameCharacter = gameCharacter;
            return this;
        }

        public FighterBuilder setCurrentArena(Arena currentArena) {
            this.currentArena = currentArena;
            return this;
        }

        public Fighter build() {
            return new Fighter(name, gameCharacter, currentArena);
        }
    }

    @Override
    public String toString() {
        return "Fighter name:" + name
                + "\nCharacter: " + gameCharacter +
                "\nBlood Level: " + bloodLevel +
                "\nCurrent Arena: " + currentArena.getName() +
                "\nPowers: " + powers;
    }
}