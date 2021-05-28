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
    protected void updateResume(Resume resume, Object sKey) {
        List.set((Integer) sKey, resume);
    }
    @Override
    protected void deleteResume(Object sKey) {
        List.remove((Integer) sKey);
    }

    @Override
    protected Resume getResume(Object sKey) {
        return List.get((Integer) sKey);
    }

    @Override
    protected void saveResume(Resume resume, Object sKey) {
        List.set((Integer) sKey, resume);
    }

    @Override
    protected boolean isExistKey(Object sKey) {
        return (Integer) sKey >= 0;
    }
}