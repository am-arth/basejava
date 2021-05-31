package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {

    private List<Resume> List = new ArrayList<>();

    public void clear() {
        List.clear();
    }

    public int size() {
        return List.size();
    }

    public Resume[] getAll() {
        return List.toArray(new Resume[List.size()]);
    }

    @Override
    protected int getKey(String uuid) {
        for (int i = 0; i < List.size(); i++) {
            if (List.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected void updateResume(Resume resume, Object index) {
        List.set((Integer) index, resume);
    }

    @Override
    protected void deleteResume(Object index) {
        List.remove(((Integer) index).intValue());
    }

    @Override
    protected Resume getResume(Object index) {
        return List.get((Integer) index);
    }

    @Override
    protected void saveResume(Resume resume, Object index) {
        List.add(resume);
    }

    @Override
    protected boolean isExist(Object index) {
        return (Integer) index > -1;
    }
}