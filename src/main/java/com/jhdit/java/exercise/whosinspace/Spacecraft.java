package com.jhdit.java.exercise.whosinspace;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Represents a maned spaceship.
 */

public class Spacecraft {
    private String name;

    private Set<Astronaut> crew = new HashSet<>();

    public Spacecraft(String name) {
        this.name = name;
    }

    public Set<Astronaut> getCrew() {
        return crew;
    }

    public void addCrewMember(final Astronaut astronaut) {
        if (null == astronaut) {
            throw new IllegalArgumentException("NULL astronaut");
        }

        if (!this.crew.contains(astronaut)) {
            this.crew.add(astronaut);
        }
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return "Spacecraft{" +
                "name='" + name + '\'' +
                ", crew=" + crew +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Spacecraft that = (Spacecraft) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
