package com.urise.webapp.model;

import java.util.Objects;

public class PersonPosition extends AbstractSection {
    private static final long serialVersionUID = 1L;
    private final String position;

    public PersonPosition(String position) {
        Objects.requireNonNull(position, "position must not be null");
        this.position = position;
    }

    public String getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return "PersonPosition{" +
                "position='" + position + '\'' +
                '}';
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