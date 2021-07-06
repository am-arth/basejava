package com.urise.webapp.model;

import java.util.List;
import java.util.Objects;

public class PersonPosition extends AbstractSection {
    private final List<String> position;

    public PersonPosition(List<String> position) {
        Objects.requireNonNull(position, "items must not be null");
        this.position = position;
    }

    public List<String> getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return position.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonPosition that = (PersonPosition) o;
        return position.equals(that.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position);
    }
}