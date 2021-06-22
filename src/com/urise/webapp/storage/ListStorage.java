package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage<Integer> {

    private List<Resume> list = new ArrayList<>();

    public void clear() {
        list.clear();
    }

    public int size() {
        return list.size();
    }

    @Override
    public List<Resume> getAllResume() {
        return list;
    }

    @Override
    protected Integer getKey(String uuid) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getUuid().equals(uuid)) {
                return i;
            }
       }
        return -1;
    }

    @Override
    protected void updateResume(Resume resume, Integer searchKey) {
        list.set(searchKey, resume);
    }

    @Override
    protected void deleteResume(Integer searchKey) {
        list.remove(searchKey.intValue());
    }

    @Override
    protected Resume getResume(Integer searchKey) {
        return list.get(searchKey);
    }

    @Override
    protected void saveResume(Resume resume, Integer searchKey) {
        list.add(resume);
    }

    @Override
    protected boolean isExist(Integer searchKey) {
        return searchKey > -1;
    }
}