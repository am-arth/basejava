package com.urise.webapp.model;

import java.util.List;
import java.util.Objects;

public class PersonProgress extends AbstractSection {
    private static final long serialVersionUID = 1L;

    private final List<String> progress;

    public PersonProgress(List<String> items) {
        this.progress = items;
    }

    public List<String> getProgress() {
        return progress;
    }

    @Override
    public String toString() {
        return progress.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonProgress that = (PersonProgress) o;
        return progress.equals(that.progress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(progress);
    }
}