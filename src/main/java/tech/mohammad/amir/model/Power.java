package tech.mohammad.amir.model;

import java.io.Serializable;
import java.util.Objects;

/**
 * Created by Mohammad.Amir on 6/27/2018.
 */
public class Power implements Serializable {
    private int id;
    private String name;
    private int damageLevel;

    public Power(int id, String name, int damageLevel) {
        this.id = id;
        this.name = name;
        this.damageLevel = damageLevel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDamageLevel() {
        return damageLevel;
    }

    public void setDamageLevel(int damageLevel) {
        this.damageLevel = damageLevel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Power power = (Power) o;
        return id == power.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return name + "(Damage Level:" + damageLevel + ')';
    }
}