package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ListStorage extends AbstractStorage {

    private List<Resume> storage = new ArrayList<>();

    public void clear() {
        storage.clear();
    }

    public int size() {
        return storage.size();
    }

    public Resume[] getAll() {
        Resume[] resumes = new Resume[storage.size()];
        return storage.toArray(resumes);
    }

    @Override
    protected int getIndex(String uuid) {
        for (Resume resume : storage) {
            if (Objects.equals(resume.getUuid(), uuid)) {
                return storage.indexOf(resume);
            }
        }
        return -1;
    }

    @Override
    protected void addResumeStorage(int index, Resume resume) {
        storage.add(resume);
    }

    @Override
    protected void deleteResumeStorage(int index) {
        storage.remove(index);
    }

    @Override
    protected void updateResumeStorage(int index, Resume resume) {
        storage.set(index, resume);
    }

    @Override
    protected Resume getResumeStorage(int index) {
        return storage.get(index);
    }

}