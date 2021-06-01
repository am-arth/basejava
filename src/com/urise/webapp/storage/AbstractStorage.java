package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    public void update(Resume resume) {
        Object searchKey = getKeyExists(resume.getUuid());
        updateResume(resume, searchKey);
    }

    public void save(Resume resume) {
        Object searchKey = getKeyNoExists(resume.getUuid());
        saveResume(resume, searchKey);
    }

    public Resume get(String uuid) {
        Object searchKey = getKeyExists(uuid);
        return getResume(searchKey);
    }

    public void delete(String uuid) {
        Object searchKey = getKeyExists(uuid);
            deleteResume(searchKey);
    }

    private Object getKeyExists(String uuid) {
        Object searchKey = getKey(uuid);
        if (!isExist(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    private Object getKeyNoExists(String uuid) {
        Object searchKey = getKey(uuid);
        if (isExist(searchKey)) {
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }

    protected abstract Object getKey(String uuid);

    protected abstract void updateResume(Resume resume, Object searchKey);

    protected abstract boolean isExist(Object searchKey);

    protected abstract void saveResume(Resume resume, Object searchKey);

    protected abstract void deleteResume(Object searchKey);

    protected abstract Resume getResume(Object searchKey);

}