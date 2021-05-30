package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    public void update(Resume resume) {
        Object searchKey = getKey(resume.getUuid());
        if (!isExistKey(searchKey)) {
            throw new NotExistStorageException(resume.getUuid());
        } else {
            updateResume(resume, searchKey);
        }
    }

    public void save(Resume resume) {
        Object searchKey = getKey(resume.getUuid());
        if (isExistKey(searchKey)) {
            throw new ExistStorageException(resume.getUuid());
        } else {
            saveResume(resume, searchKey);
        }
    }

    public Resume get(String uuid) {
        Object searchKey = getKey(uuid);
        if (!isExistKey(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
        return getResume(searchKey);
    }

    public void delete(String uuid) {
        Object searchKey = getKey(uuid);
        if (!isExistKey(searchKey)) {
            throw new NotExistStorageException(uuid);
        } else {
            deleteResume(searchKey);
        }
    }

    protected abstract int getKey(String uuid);

    protected abstract void updateResume(Resume resume, Object searchKey);

    protected abstract boolean isExistKey(Object searchKey);

    protected abstract void saveResume(Resume resume, Object searchKey);

    protected abstract void deleteResume(Object searchKey);

    protected abstract Resume getResume(Object searchKey);
}