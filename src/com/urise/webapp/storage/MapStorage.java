package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.HashMap;

public class MapStorage extends AbstractStorage {

    private HashMap<String, Resume> map = new HashMap<>();

    public int size() {
        return map.size();
    }

    public void clear() {
        map.clear();
    }

    public Resume[] getAll() {
        Resume[] resumes = new Resume[map.size()];
        return map.values().toArray(resumes);
    }

    @Override
    protected int getKey(String uuid) {
        return Integer.parseInt(uuid);
    }

    @Override
    protected void saveResume(Resume resume, Object key) {
        map.put(key.toString(), resume);
    }

    @Override
    protected void deleteResume(Object key) {
        map.remove(key.toString());
    }

    @Override
    protected void updateResume(Resume resume, Object key) {
        map.replace(key.toString(), resume);
    }

    @Override
    protected boolean isExistKey(Object index) {
        return map.containsKey(index);
    }

    @Override
    protected Resume getResume(Object key) {
        return map.get(key.toString());
    }

}