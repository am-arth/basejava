package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    public void update(Resume resume) {
        Object sKey = getKey(resume.getUuid());
        if (!isExistKey(sKey)) {
            throw new NotExistStorageException(resume.getUuid());
        } else {
            updateResume(resume, sKey);
        }
    }

    public void save(Resume resume) {
        Object sKey = getKey(resume.getUuid());
        if (isExistKey(sKey)) {
            throw new ExistStorageException(resume.getUuid());
        } else {
            saveResume(resume, sKey);
        }
    }

    public Resume get(String uuid) {
        Object sKey = getKey(uuid);
        if (!isExistKey(sKey)) {
            throw new NotExistStorageException(uuid);
        }
        return getResume(sKey);
    }

    public void delete(String uuid) {
        Object sKey = getKey(uuid);
        if (!isExistKey(sKey)) {
            throw new NotExistStorageException(uuid);
        } else {
            deleteResume(sKey);
        }
    }

    protected abstract int getKey(String uuid);

    protected abstract void updateResume(Resume resume, Object sKey);

    protected abstract boolean isExistKey(Object sKey);

    protected abstract void saveResume(Resume resume, Object sKey);

    protected abstract void deleteResume(Object sKey);

    protected abstract Resume getResume(Object sKey);
}