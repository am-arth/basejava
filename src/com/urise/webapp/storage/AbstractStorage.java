package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index < 0) {
            throw new NotExistStorageException(resume.getUuid());
        } else {
            updateResumeStorage(index, resume);
        }
    }

    public void save(Resume resume) {
        if (resume.getUuid() == null) return;
        int index = getIndex(resume.getUuid());
        if (index > -1) {
            throw new ExistStorageException(resume.getUuid());
        } else {
            addResumeStorage(index, resume);
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        return getResumeStorage(index);
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        } else {
            deleteResumeStorage(index);
        }
    }

    protected abstract int getIndex(String uuid);

    protected abstract void addResumeStorage(int index, Resume resume);

    protected abstract void deleteResumeStorage(int index);

    protected abstract void updateResumeStorage(int index, Resume resume);

    protected abstract Resume getResumeStorage(int index);

}